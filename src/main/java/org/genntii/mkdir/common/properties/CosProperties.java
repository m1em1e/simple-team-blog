package org.genntii.mkdir.common.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author mkdir
 * @since 2025/08/22 16:49
 */
@Data
@Component
@ConfigurationProperties(prefix = "tencent.cos")
public class CosProperties {
    private String bucketName;
    private String region;
    private String secretId;
    private String secretKey;
}
