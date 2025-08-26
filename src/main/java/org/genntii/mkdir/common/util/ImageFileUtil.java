package org.genntii.mkdir.common.util;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.genntii.mkdir.common.Exception.ImageErrorException;
import org.genntii.mkdir.common.properties.CosProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.Date;
import java.util.UUID;

/**
 * @author mkdir
 * @since 2025/08/22 16:48
 */
@Data
@Component
@AllArgsConstructor
public class ImageFileUtil {

    @Resource
    private COSClient cosClient;

    @Resource
    private CosProperties cosProperties;

    public String upload(MultipartFile file) {
    try {
        // 1. 验证文件
        validateFile(file);

        // 2. 安全处理文件名
        String originalFilename = file.getOriginalFilename();
        String safeFilename = sanitizeFilename(originalFilename);
        String key = "image/" + UUID.randomUUID() + "-" + safeFilename;

        // 3. 设置元数据
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());

        // 4. 上传文件
        PutObjectRequest putObjectRequest = new PutObjectRequest(
                cosProperties.getBucketName(), key, file.getInputStream(), metadata);

        cosClient.putObject(putObjectRequest);
        return key;
    } catch (Exception e) {
        throw new ImageErrorException("图片上传失败: " + e.getMessage());
    }
}

/**
 * 验证上传文件的安全性
 */
private void validateFile(MultipartFile file) {
    // 检查文件是否为空
    if (file.isEmpty()) {
        throw new ImageErrorException("上传文件不能为空");
    }

    // 检查文件大小（例如限制为5MB）
    long maxSize = 5 * 1024 * 1024;
    if (file.getSize() > maxSize) {
        throw new ImageErrorException("文件大小不能超过5MB");
    }

    // 检查文件类型
    String contentType = file.getContentType();
    if (contentType == null || !isAllowedImageType(contentType)) {
        throw new ImageErrorException("不支持的文件类型");
    }
}

/**
 * 检查是否为允许的图片类型
 */
private boolean isAllowedImageType(String contentType) {
    return "image/jpeg".equals(contentType) ||
           "image/png".equals(contentType) ||
           "image/gif".equals(contentType) ||
           "image/webp".equals(contentType);
}

/**
 * 清理文件名，防止路径遍历等安全问题
 */
private String sanitizeFilename(String filename) {
    if (filename == null || filename.isEmpty()) {
        return "unnamed";
    }

    // 移除路径信息，只保留文件名
    String cleanName = filename;
    if (filename.contains("\\")) {
        cleanName = filename.substring(filename.lastIndexOf('\\') + 1);
    }
    if (cleanName.contains("/")) {
        cleanName = cleanName.substring(cleanName.lastIndexOf('/') + 1);
    }

    // 限制文件名长度
    if (cleanName.length() > 100) {
        int dotIndex = cleanName.lastIndexOf('.');
        if (dotIndex > 0) {
            String name = cleanName.substring(0, dotIndex);
            String extension = cleanName.substring(dotIndex);
            if (name.length() > 80) {
                name = name.substring(0, 80);
            }
            cleanName = name + extension;
        } else {
            cleanName = cleanName.substring(0, 100);
        }
    }

    // 移除危险字符
    cleanName = cleanName.replaceAll("[<>:\"/\\\\|?*]", "_");

    return cleanName;
}



    public String getImgUrl(String key) {
        Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000);
        URL url = cosClient.generatePresignedUrl(cosProperties.getBucketName(), key, expiration);
        return url.toString();
    }

}
