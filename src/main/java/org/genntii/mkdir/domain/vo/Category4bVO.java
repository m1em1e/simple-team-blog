package org.genntii.mkdir.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 分类信息展示对象 (View Object)
 * 用于向前端展示分类基本信息，实现Serializable接口支持序列化
 *
 * @author mkdir
 * @since 2025/08/25 09:34
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category4bVO implements Serializable {
    /**
     * 分类唯一标识ID
     */
    private Long id;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 分类描述信息
     */
    private String description;

    /**
     * 分类状态 (0:禁用 1:启用)
     */
    private Byte status;
}

