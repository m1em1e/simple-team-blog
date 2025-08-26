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
 * @author mkdir
 * @since 2025/08/19 14:03
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @TableField(fill = FieldFill.INSERT)
    private Long id;
    private String nickname;
    private String username;
    private String password;
    private String salt;
    private Byte sex;
    private LocalDate birthday;
    private String avatar;
    private String tags;
    private String description;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    private LocalDateTime lastLoginTime;
}
