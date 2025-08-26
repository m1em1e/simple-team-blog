package org.genntii.mkdir.domain.param;

import lombok.Data;

/**
 *
 *
 * @author mkdir
 * @since 2025/08/25 11:44
 */
@Data
public class PageQueryParam {
    private Integer pageIndex;
    private Integer pageSize;
    private String keyword;
}
