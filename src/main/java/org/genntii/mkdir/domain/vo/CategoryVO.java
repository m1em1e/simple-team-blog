package org.genntii.mkdir.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 分类信息简单展示对象 (View Object)
 * 用于向前端展示基础分类信息，实现Serializable接口支持序列化
 *
 * @author mkdir
 * @since 2025/08/25 09:33
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryVO implements Serializable {
    /**
     * 分类唯一标识ID
     */
    private Long id;

    /**
     * 分类名称
     */
    private String categoryName;
}
