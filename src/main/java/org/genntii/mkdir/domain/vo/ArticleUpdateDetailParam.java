package org.genntii.mkdir.domain.vo;

import lombok.Data;

import java.util.List;

/**
 *
 *
 *
 * @author mkdir
 * @since 2025/08/27 10:12
 */
@Data
public class ArticleUpdateDetailParam {
    private String coverId;
    private String title;
    private String content;
    private List<Long> categoryId;
}
