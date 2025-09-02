package org.genntii.mkdir.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 文章分类关联实体类
 * 对应数据库中的文章分类关联表，用于建立文章与分类之间的多对多关系
 *
 * @author mkdir
 * @since 2025/08/22 14:00
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleCategory implements Serializable {
    /**
     * 关联记录唯一标识ID
     */
    private Long id;

    /**
     * 文章ID，关联文章表
     */
    private Long articleId;

    /**
     * 分类ID，关联分类表
     */
    private Long categoryId;

    /**
     * 关联创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
