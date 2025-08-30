package org.genntii.mkdir.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 用户实体类
 * 对应数据库中的用户表，存储用户的基本信息和账户信息
 *
 * @author mkdir
 * @since 2025/08/19 14:03
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    /**
     * 用户唯一标识ID
     * 使用MyBatis Plus的自动填充功能，在插入时自动生成
     */
    @TableField(fill = FieldFill.INSERT)
    private Long id;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户名（登录账号）
     */
    private String username;

    /**
     * 用户密码（加密存储）
     */
    private String password;

    /**
     * 密码盐值，用于加密密码
     */
    private String salt;

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
     * 用户标签（JSON格式存储）
     */
    private String tags;

    /**
     * 用户个人描述/简介
     */
    private String description;

    /**
     * 用户创建时间
     * 使用MyBatis Plus的自动填充功能，在插入时自动设置
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 用户信息更新时间
     * 使用MyBatis Plus的自动填充功能，在插入和更新时自动设置
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 用户最后登录时间
     */
    private LocalDateTime lastLoginTime;
}

