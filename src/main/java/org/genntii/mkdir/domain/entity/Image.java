package org.genntii.mkdir.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 *
 *
 * @author mkdir
 * @since 2025/08/29 09:26
 */
@Data
@NoArgsConstructor
public class Image {
    private Long id;
    private int height;
    private String key;
    private String type;

}
