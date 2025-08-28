package org.genntii.mkdir.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 *
 *
 * @author mkdir
 * @since 2025/08/26 10:49
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginVO implements Serializable {
    private String id;
    private String nickname;
    private String sex;
    private LocalDate birthday;
    private String avatar;
    private List<String> tags;
    private String description;
    private LocalDateTime localDateTime;
    private String token;
}
