package org.genntii.mkdir.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import jakarta.annotation.Resource;
import org.genntii.mkdir.common.Exception.ImageErrorException;
import org.genntii.mkdir.common.util.ImageFileUtil;
import org.genntii.mkdir.domain.entity.Image;
import org.genntii.mkdir.mapper.ImageMapper;
import org.genntii.mkdir.repo.ImageRepo;
import org.genntii.mkdir.service.ImageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

/**
 * @author mkdir
 * @since 2025/08/22 15:20
 */
@Service
public class ImageServiceImpl extends ServiceImpl<ImageMapper, Image> implements ImageService {

    @Value("${tencent.cos.bucket-name}")
    private String bucketName;

    @Resource
    private ImageRepo imageRepo;

    @Resource
    private COSClient cosClient;

    @Resource
    private ImageFileUtil imageFileUtil;

    @Override
    public Image storeImage(MultipartFile file) {
        Image image = new Image();
        try {
            image.setName(file.getOriginalFilename() + UUID.randomUUID());
            image.setType(file.getContentType());
            image.setData(file.getBytes());
        } catch (IOException e) {
            throw new ImageErrorException("图片上传失败");
        }
        return imageRepo.save(image);
    }


    @Override
    public Image getImage(Long id) {
        return imageRepo.findById(id).orElseThrow(() -> new ImageErrorException("图片下载失败"));
    }


    @Override
    public String cosUploadImage(MultipartFile file) {
        return imageFileUtil.upload(file);
    }


    @Override
    public String getFileUrl(String key) {
        return imageFileUtil.getImgUrl(key);
    }
}
