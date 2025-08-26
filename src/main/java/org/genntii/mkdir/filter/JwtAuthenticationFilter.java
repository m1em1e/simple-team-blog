package org.genntii.mkdir.filter;

import cn.hutool.jwt.Claims;
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
 *
 *
 * @author mkdir
 * @since 2025/08/25 14:50
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Resource
    private UserDetailServiceImpl userService;

    @Resource
    private JwtCommonUtil jwtCommonUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = request.getHeader("Authorization");
            if (token != null && !token.isEmpty()) {
                boolean expiration = jwtCommonUtil.checkJwt(token);

                if (expiration) {
                    filterChain.doFilter(request, response);
                    return;
                }
                Long id  = jwtCommonUtil.parseJwt(token);
                User user = userService.getById(id);

                // 加载用户详细信息
                UserDetails userDetails = userService.loadUserByUsername(user.getUsername());

                // 创建认证对象并设置到安全上下文
                Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);

                // 更新用户最后登录时间
                user.setLastLoginTime(LocalDateTime.now());
                userService.updateById(user);

            }
        } catch (Exception e) {
            filterChain.doFilter(request, response);
            return;
        }


        // 如果token为空直接下一步过滤器，此时上线文中无用户信息，所有在后续认证环节失败
        filterChain.doFilter(request, response);
    }

}
