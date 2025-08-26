package org.genntii.mkdir.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 *
 * @author mkdir
 * @since 2025/08/25 11:53
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDetailVO {
    private Long id;
    private String title;
    private String author;
    private String cover;
    private String content;
    private LocalDateTime updateTime;
    private List<CategoryVO> categoryList;
}
