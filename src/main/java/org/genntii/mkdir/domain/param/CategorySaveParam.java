package org.genntii.mkdir.domain.param;

import lombok.Data;

/**
 * 分类保存参数对象
 * 用于接收前端传递的分类保存/更新请求参数
 *
 * @author mkdir
 * @since 2025/08/25 08:38
 */
@Data
public class CategorySaveParam {
    /**
     * 分类唯一标识ID，新增时可为空，更新时必填
     */
    private Long id;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 分类状态 (0:禁用 1:启用)
     */
    private Byte status;

    /**
     * 分类描述信息
     */
    private String description;
}
