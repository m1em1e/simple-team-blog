package org.genntii.mkdir.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.genntii.mkdir.domain.entity.Image;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author mkdir
 * @since 2025/08/22 15:19
 */
public interface ImageService extends IService<Image> {


    String cosUploadImage(MultipartFile file);

    String getFileUrl(String key);
}
