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
 * @since 2025/08/25 10:23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleInfoVO {
    private Long id;
    private Long author;
    private String authorName;
    private String authorAvatarUrl;
    private String coverId;
    private Integer coverHeight;
    private String title;
    private String introduction;
    private List<CategoryVO> categoryList;
    private LocalDateTime updateTime;
}
