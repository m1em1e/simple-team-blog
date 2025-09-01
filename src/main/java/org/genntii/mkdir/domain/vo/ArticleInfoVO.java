package org.genntii.mkdir.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 文章信息展示对象 (View Object)
 * 用于向前端展示文章的详细信息
 *
 * @author mkdir
 * @since 2025/08/25 10:23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleInfoVO {
    /**
     * 文章唯一标识ID
     */
    private Long id;

    /**
     * 作者用户ID
     */
    private Long author;

    /**
     * 作者名称
     */
    private String authorName;

    /**
     * 作者头像URL地址
     */
    private String authorAvatarUrl;

    /**
     * 封面图片ID
     */
    private String coverId;

    /**
     * 封面图片高度
     */
    private Integer coverHeight;

    /**
     * 封面图片宽度
     */
    private Integer coverWidth;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章简介/摘要
     */
    private String introduction;

    /**
     * 文章分类列表
     */
    private List<CategoryVO> categoryList;

    /**
     * 文章最后更新时间
     */
    private LocalDateTime updateTime;
}
