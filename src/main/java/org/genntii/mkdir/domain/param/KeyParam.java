package org.genntii.mkdir.domain.param;

import lombok.Data;

/**
 * 关键字参数对象
 * 用于接收前端传递的关键字查询参数
 *
 * @author mkdir
 * @since 2025/08/26 10:01
 */
@Data
public class KeyParam {
    /**
     * 查询关键字
     */
    private String key;
}
