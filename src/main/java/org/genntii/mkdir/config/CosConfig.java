package org.genntii.mkdir.config;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;
import jakarta.annotation.Resource;
import org.genntii.mkdir.common.properties.CosProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mkdir
 * @since 2025/08/22 16:30
 */
@Configuration
public class CosConfig {

    @Resource
    private CosProperties cosProperties;

    @Bean
    public COSClient cosClient() {
        COSCredentials cred = new BasicCOSCredentials(cosProperties.getSecretId(),cosProperties.getSecretKey());
        Region region = new Region(cosProperties.getRegion());
        ClientConfig clientConfig = new ClientConfig(region);

        return new COSClient(cred, clientConfig);
    }

}
