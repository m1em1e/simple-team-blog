package org.genntii.mkdir.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author mkdir
 * @since 2025/08/22 14:00
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleCategory implements Serializable {
    private Long id;
    private Long articleId;
    private Long categoryId;
    private LocalDateTime createTime;
}
