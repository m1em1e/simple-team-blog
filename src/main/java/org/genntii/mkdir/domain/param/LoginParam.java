package org.genntii.mkdir.domain.param;

import lombok.Data;

/**
 * 用户登录参数对象
 * 用于接收前端传递的用户登录请求参数
 *
 * @author mkdir
 * @since 2025/08/20 15:28
 */
@Data
public class LoginParam {
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
