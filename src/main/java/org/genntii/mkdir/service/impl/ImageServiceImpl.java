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
 * 图片服务实现类
 * 实现图片相关业务操作的具体逻辑
 *
 * @author mkdir
 * @since 2025/08/22 15:20
 */
@Service
public class ImageServiceImpl extends ServiceImpl<ImageMapper, Image> implements ImageService {

    /**
     * 腾讯云COS存储桶名称，从配置文件中读取
     */
    @Value("${tencent.cos.bucket-name}")
    private String bucketName;

    /**
     * 腾讯云COS客户端，用于操作云存储服务
     */
    @Resource
    private COSClient cosClient;

    /**
     * 图片文件工具类，提供图片上传和获取URL的功能
     */
    @Resource
    private ImageFileUtil imageFileUtil;

    /**
     * 上传图片文件到腾讯云COS存储服务
     *
     * @param file 要上传的图片文件
     * @return 上传成功后的文件访问URL
     */
    @Override
    public String cosUploadImage(MultipartFile file) {
        return imageFileUtil.upload(file);
    }

    /**
     * 根据文件键值获取图片文件的访问URL
     *
     * @param key 文件键值/路径
     * @return 文件访问URL
     */
    @Override
    public String getFileUrl(String key) {
        return imageFileUtil.getImgUrl(key);
    }
}
