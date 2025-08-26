package org.genntii.mkdir.domain.param;

import lombok.Data;

/**
 * @author mkdir
 * @since 2025/08/25 08:38
 */
@Data
public class CategorySaveParam {
    private Long id;
    private String categoryName;
    private Byte status;
    private String description;
}
