package org.genntii.mkdir.domain.param;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * 用户信息更新参数对象
 * 用于接收前端传递的用户个人信息更新请求参数
 *
 * @author mkdir
 * @since 2025/08/22 17:12
 */
@Data
public class UserMessageUpdateParam {
    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户头像URL
     */
    private String avatar;

    /**
     * 用户性别 (0:未知 1:男 2:女)
     */
    private Byte sex;

    /**
     * 用户生日
     */
    private LocalDate birthday;

    /**
     * 用户标签列表
     */
    private List<String> tags;

    /**
     * 用户个人描述/简介
     */
    private String description;
}
