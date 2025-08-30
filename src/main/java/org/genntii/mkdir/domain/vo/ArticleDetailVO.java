package org.genntii.mkdir.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 文章详情展示对象 (View Object)
 * 用于向前端展示文章的完整详情信息
 *
 * @author mkdir
 * @since 2025/08/25 11:53
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDetailVO {
    /**
     * 文章唯一标识ID
     */
    private Long id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 作者用户ID
     */
    private Long author;

    /**
     * 作者头像URL地址
     */
    private String authorAvatarUrl;

    /**
     * 作者名称
     */
    private String authorName;

    /**
     * 文章封面图片URL
     */
    private String cover;

    /**
     * 文章正文内容
     */
    private String content;

    /**
     * 文章最后更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 文章分类列表
     */
    private List<CategoryVO> categoryList;
}
