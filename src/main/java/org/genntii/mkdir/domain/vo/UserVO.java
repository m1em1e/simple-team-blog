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
 * 用户信息展示对象 (View Object)
 * 用于向前端展示用户基本信息，实现Serializable接口支持序列化
 *
 * @author mkdir
 * @since 2025/08/22 11:44
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserVO implements Serializable {
    /**
     * 用户唯一标识ID
     */
    private String id;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户性别 (0:未知 1:男 2:女)
     */
    private Byte sex;

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
     * 个人背景图片
     */
    private String backgroundImageUrl;

    /**
     * 用户最后登录时间
     */
    private LocalDateTime lastLoginTime;
}
