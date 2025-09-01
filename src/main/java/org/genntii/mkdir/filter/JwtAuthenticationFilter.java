package org.genntii.mkdir.filter;

import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.genntii.mkdir.common.util.JwtCommonUtil;
import org.genntii.mkdir.domain.entity.User;
import org.genntii.mkdir.service.impl.UserDetailServiceImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * JWT认证过滤器
 * 用于拦截请求并验证JWT令牌，实现无状态认证机制
 *
 * @author mkdir
 * @since 2025/08/25 14:50
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    /**
     * 用户详情服务实现类，用于获取用户信息
     */
    @Resource
    private UserDetailServiceImpl userService;

    /**
     * JWT工具类，用于JWT令牌的解析和验证
     */
    @Resource
    private JwtCommonUtil jwtCommonUtil;

    /**
     * 过滤器核心方法，用于处理每个HTTP请求的JWT认证逻辑
     *
     * @param request     HTTP请求对象
     * @param response    HTTP响应对象
     * @param filterChain 过滤器链
     * @throws ServletException Servlet异常
     * @throws IOException      IO异常
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            // 从请求头中获取Authorization令牌
            String token = request.getHeader("Authorization");

            // 如果令牌不为空且非空字符串
            if (token != null && !token.isEmpty()) {
                // 检查JWT令牌是否过期
                boolean expiration = jwtCommonUtil.checkJwt(token);

                // 如果令牌已过期，则直接进入下一个过滤器
                if (expiration) {
                    filterChain.doFilter(request, response);
                    return;
                }

                // 解析JWT令牌获取用户ID
                Long id = jwtCommonUtil.parseJwt(token);

                // 根据用户ID获取用户信息
                User user = userService.getById(id);

                // 加载用户详细信息（包括权限信息）
                UserDetails userDetails = userService.loadUserByUsername(user.getUsername());

                // 创建认证对象并设置到Spring Security的安全上下文
                Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);

                // 更新用户最后登录时间
                user.setLastLoginTime(LocalDateTime.now());
                userService.updateById(user);

            }
        } catch (Exception e) {
            // 发生异常时直接进入下一个过滤器
            filterChain.doFilter(request, response);
            return;
        }

        // 如果token为空直接进入下一个过滤器，此时安全上下文中无用户信息，所有在后续认证环节会失败
        filterChain.doFilter(request, response);
    }

}
