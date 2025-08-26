package org.genntii.mkdir.controller.restController;

import jakarta.annotation.Resource;
import org.genntii.mkdir.common.Exception.LoginFailedException;
import org.genntii.mkdir.common.result.Result;
import org.genntii.mkdir.common.util.JwtCommonUtil;
import org.genntii.mkdir.domain.entity.User;
import org.genntii.mkdir.domain.param.LoginParam;
import org.genntii.mkdir.service.impl.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * 认证授权控制器
 * 提供用户登录认证相关接口
 *
 * @author mkdir
 * @since 2025/08/20 15:27
 */
@RestController
@RequestMapping("/api/auth")
public class AuthorizeRestController {

    @Autowired
    private UserDetailServiceImpl userService;

    @Resource
    private JwtCommonUtil jwtCommonUtil;

    /**
     * 测试接口
     * 验证用户登录凭据是否正确
     *
     * @param param 登录参数(用户名和密码)
     * @return 登录成功返回"hello world"，失败返回null
     */
    @PostMapping("/helloworld")
    public Result<String> test(@RequestBody LoginParam param) {
        if (userService.login(param.getUsername(), param.getPassword())) {
            return Result.success("hello world");
        }

        return null;
    }

        /**
     * 用户登录接口
     * 验证用户身份并生成JWT令牌
     *
     * @param param 登录参数(用户名和密码)
     * @return 登录成功返回JWT令牌，失败抛出LoginFailedException异常
     */
    @PostMapping("/login")
    public Result<String> login(@RequestBody LoginParam param) {
        try{
            // 验证用户登录凭证
            userService.login(param.getUsername(), param.getPassword());

            // 加载用户详细信息
            UserDetails userDetails = userService.loadUserByUsername(param.getUsername());

            // 创建认证对象并设置到安全上下文
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // 更新用户最后登录时间
            User user = userService.getUserByUsername(param.getUsername());
            user.setLastLoginTime(LocalDateTime.now());
            userService.updateById(user);

            // 生成并返回JWT令牌
            return Result.success(jwtCommonUtil.createJwt(user.getId()));

        } catch (RuntimeException e) {
            throw new LoginFailedException(e.getMessage());
        }
    }


}
