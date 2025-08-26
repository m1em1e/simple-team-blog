package org.genntii.mkdir.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author mkdir
 * @since 2025/08/25 09:33
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryVO implements Serializable {
    private Long id;
    private String categoryName;
}
