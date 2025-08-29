package org.genntii.mkdir.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 图片实体类
 * 对应数据库中的图片表，存储图片的基本信息
 *
 * @author mkdir
 * @since 2025/08/29 09:26
 */
@Data
@NoArgsConstructor
public class Image {
    /**
     * 图片唯一标识ID
     */
    private Long id;

    /**
     * 图片高度（像素）
     */
    private int height;

    /**
     * 图片存储键值/路径
     */
    private String key;

    /**
     * 图片类型/格式（如：jpg, png, gif等）
     */
    private String type;

}
