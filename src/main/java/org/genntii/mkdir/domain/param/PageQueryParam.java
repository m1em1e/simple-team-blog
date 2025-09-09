package org.genntii.mkdir.domain.param;

import lombok.Data;

/**
 * 分页查询参数对象
 * 用于接收前端传递的分页查询请求参数
 *
 * @author mkdir
 * @since 2025/08/25 11:44
 */
@Data
public class PageQueryParam {
    /**
     * 页码索引（从1开始）
     */
    private Integer pageIndex;

    /**
     * 每页显示记录数
     */
    private Integer pageSize;

    /**
     * 查询关键字
     */
    private String keyword;

    /**
     * 用户id
     */
    private Long userId;
}
