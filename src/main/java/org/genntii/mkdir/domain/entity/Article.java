package org.genntii.mkdir.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author mkdir
 * @since 2025/08/22 13:56
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article implements Serializable {
    private Long id;
    private String author;
    private String coverId;
    private String title;
    private String introduction;
    private String content;
    private Byte status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
