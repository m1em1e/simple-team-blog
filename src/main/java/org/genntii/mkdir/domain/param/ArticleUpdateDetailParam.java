package org.genntii.mkdir.domain.param;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 文章更新详情参数对象
 * 用于接收前端传递的文章更新请求参数
 *
 * @author mkdir
 * @since 2025/08/27 10:12
 */
@Data
@NoArgsConstructor
public class ArticleUpdateDetailParam {
    /**
     * 封面图片ID
     */
    private String coverId;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章正文内容
     */
    private String content;

    /**
     * 文章分类ID列表
     */
    private List<Long> categoryId;
}
