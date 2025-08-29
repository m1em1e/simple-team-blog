package org.genntii.mkdir.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcloud.cos.COSClient;
import jakarta.annotation.Resource;
import org.genntii.mkdir.common.util.ImageFileUtil;
import org.genntii.mkdir.domain.entity.Image;
import org.genntii.mkdir.mapper.ImageMapper;
import org.genntii.mkdir.service.ImageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author mkdir
 * @since 2025/08/22 15:20
 */
@Service
public class ImageServiceImpl extends ServiceImpl<ImageMapper, Image> implements ImageService {

    @Value("${tencent.cos.bucket-name}")
    private String bucketName;

    @Resource
    private COSClient cosClient;

    @Resource
    private ImageFileUtil imageFileUtil;


    @Override
    public String cosUploadImage(MultipartFile file) {
        return imageFileUtil.upload(file);
    }


    @Override
    public String getFileUrl(String key) {
        return imageFileUtil.getImgUrl(key);
    }
}
