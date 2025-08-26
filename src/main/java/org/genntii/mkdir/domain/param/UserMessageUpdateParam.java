package org.genntii.mkdir.domain.param;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * @author mkdir
 * @since 2025/08/22 17:12
 */
@Data
public class UserMessageUpdateParam {
    private Long id;
    private String nickname;
    private String avatar;
    private Byte sex;
    private LocalDate birthday;
    private List<String> tags;
    private String description;
}
