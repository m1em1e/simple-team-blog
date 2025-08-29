package org.genntii.mkdir.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.genntii.mkdir.domain.entity.Image;
import org.springframework.web.multipart.MultipartFile;

/**
 * 图片服务接口
 * 提供图片相关业务操作方法
 *
 * @author mkdir
 * @since 2025/08/22 15:19
 */
public interface ImageService extends IService<Image> {

    /**
     * 上传图片文件到云存储服务
     *
     * @param file 要上传的图片文件
     * @return 上传成功后的文件访问URL
     */
    String cosUploadImage(MultipartFile file);

    /**
     * 根据文件键值获取文件访问URL
     *
     * @param key 文件键值/路径
     * @return 文件访问URL
     */
    String getFileUrl(String key);
}
