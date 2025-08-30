package org.genntii.mkdir.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户登录信息展示对象 (View Object)
 * 用于向前端返回用户登录后的基本信息和认证令牌，实现Serializable接口支持序列化
 *
 * @author mkdir
 * @since 2025/08/26 10:49
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginVO implements Serializable {
    /**
     * 用户唯一标识ID
     */
    private String id;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户性别
     */
    private String sex;

    /**
     * 用户生日
     */
    private LocalDate birthday;

    /**
     * 用户头像URL
     */
    private String avatar;

    /**
     * 用户标签列表
     */
    private List<String> tags;

    /**
     * 用户个人描述/简介
     */
    private String description;

    /**
     * 登录时间
     */
    private LocalDateTime localDateTime;

    /**
     * 用户认证令牌(JWT)
     */
    private String token;
}

