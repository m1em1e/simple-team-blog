package org.genntii.mkdir.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author mkdir
 * @since 2025/08/22 13:59
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Serializable {
    private Long id;
    private String categoryName;
    private Byte status;
    private String description;
    private LocalDateTime createTime;
}
