package org.genntii.mkdir.config;

import jakarta.annotation.Resource;
import org.genntii.mkdir.filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring Security配置类
 * 配置应用的安全策略，包括认证、授权、密码加密等
 *
 * @author mkdir
 * @since 2025/08/20 09:51
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Resource
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    /**
     * 配置密码编码器
     * 使用BCrypt算法进行密码加密
     *
     * @return BCrypt密码编码器实例
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置安全过滤器链
     * 定义HTTP安全策略，包括CSRF、CORS、授权规则等
     *
     * @param httpSecurity HTTP安全配置构建器
     * @param jwtAuthenticationFilter JWT认证过滤器
     * @return 安全过滤器链
     * @throws Exception 配置异常
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity, JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {

        // 禁用CSRF保护
        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        // 禁用CORS
        httpSecurity.cors(AbstractHttpConfigurer::disable);

        // 配置请求授权规则
        httpSecurity.authorizeHttpRequests(auth -> auth
                // 其他所有请求都允许访问
                .anyRequest().permitAll()
        );

        // 禁用表单登录
        httpSecurity.formLogin(AbstractHttpConfigurer::disable);

        // 禁用HTTP基本认证
        httpSecurity.httpBasic(AbstractHttpConfigurer::disable);

        // 添加JWT认证过滤器到UsernamePasswordAuthenticationFilter位置
        httpSecurity.addFilterAt(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        // 构建并返回安全过滤器链
        return httpSecurity.build();

    }

}
