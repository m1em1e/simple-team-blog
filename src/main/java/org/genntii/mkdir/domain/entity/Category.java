package org.genntii.mkdir.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 分类实体类
 * 对应数据库中的分类表，存储分类的基本信息
 *
 * @author mkdir
 * @since 2025/08/22 13:59
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Serializable {
    /**
     * 分类唯一标识ID
     */
    private Long id;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 分类状态 (0:禁用 1:启用)
     */
    private Byte status;

    /**
     * 分类描述信息
     */
    private String description;

    /**
     * 分类创建时间
     */
    private LocalDateTime createTime;
}
