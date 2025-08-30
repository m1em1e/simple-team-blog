package org.genntii.mkdir.controller.restController;

import jakarta.annotation.Resource;
import org.genntii.mkdir.common.result.Result;
import org.genntii.mkdir.domain.entity.Image;
import org.genntii.mkdir.domain.param.KeyParam;
import org.genntii.mkdir.service.ImageService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 图片管理控制器
 * 提供图片上传、获取等功能接口
 *
 * @author mkdir
 * @since 2025/08/22 15:37
 */
@RestController
@RequestMapping("/api/image")
public class ImageRestController {

    @Resource
    private ImageService imageService;

    /**
     * 上传文件到云存储服务
     * 将文件上传到COS并返回访问URL
     *
     * @param file 上传的文件
     * @return 文件访问URL
     */
    @PostMapping("/cos/upload")
    public Result<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String key = imageService.cosUploadImage(file);

        return Result.success(key);
    }

    /**
     * 根据文件key获取文件访问URL
     *
     * @param param 包含文件key的参数对象，包含 文件的唯一标识符
     * @return 包含文件访问URL的结果对象
     */
    @PostMapping("/cos")
    public Result<String> getFileUrl(@RequestBody KeyParam param) {
        // 调用imageService获取文件URL
        String url = imageService.getFileUrl(param.getKey());
        return Result.success(url);
    }

}
