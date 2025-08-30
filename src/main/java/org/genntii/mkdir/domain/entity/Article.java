package org.genntii.mkdir.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 文章实体类
 * 对应数据库中的文章表，存储文章的基本信息和内容
 *
 * @author mkdir
 * @since 2025/08/22 13:56
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article implements Serializable {
    /**
     * 文章唯一标识ID
     */
    private Long id;

    /**
     * 作者用户ID，关联用户表
     */
    private Long author;

    /**
     * 封面图片ID，关联文件表
     */
    private String coverId;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章简介/摘要
     */
    private String introduction;

    /**
     * 文章正文内容
     */
    private String content;

    /**
     * 文章状态 (0:草稿 1:发布 2:删除)
     */
    private Byte status;

    /**
     * 文章创建时间
     */
    private LocalDateTime createTime;

    /**
     * 文章最后更新时间
     */
    private LocalDateTime updateTime;
}

