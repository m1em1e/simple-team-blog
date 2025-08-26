package org.genntii.mkdir.controller.restController;

import jakarta.annotation.Resource;
import org.genntii.mkdir.common.result.Result;
import org.genntii.mkdir.domain.param.UserMessageUpdateParam;
import org.genntii.mkdir.domain.vo.UserLoginVO;
import org.genntii.mkdir.domain.vo.UserVO;
import org.genntii.mkdir.service.ImageService;
import org.genntii.mkdir.service.impl.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 用户信息控制器
 * 提供用户信息查询和更新功能
 *
 * @author mkdir
 * @since 2025/08/22 11:40
 */
@RestController
@RequestMapping("/api")
public class UserRestController {

    @Resource
    private UserDetailServiceImpl userService;
    @Autowired
    private ImageService imageService;

    /**
     * 获取当前用户信息
     * 返回当前登录用户的基本信息
     *
     * @return 用户信息VO对象
     */
    @GetMapping("/user/{id}")
    public Result<UserVO> getUserMessage(@PathVariable("id")Long id) {
        return Result.success(userService.getUserMessage(id));
    }

    /**
     * 更新用户信息
     * 根据传入的参数更新当前用户的信息
     *
     * @param param 用户信息更新参数
     * @return 更新后的用户信息VO对象
     */
    @PutMapping("/user")
    public Result<UserVO> updateUserMessage(@RequestParam UserMessageUpdateParam param) {
        return Result.success(userService.userUpdate(param));
    }

    /**
     * 上传用户头像
     * @param id 用户ID
     * @param file 上传的头像文件
     * @return 包含更新后用户信息的结果对象
     */
    @PostMapping("/avatar/upload/{id}")
    public Result<UserVO> uploadAvatar(@PathVariable("id") Long id, @RequestParam("file") MultipartFile file) {
        // 上传图片到云存储服务并获取文件key
        String key = imageService.cosUploadImage(file);

        // 构造用户信息更新参数并执行更新操作
        UserMessageUpdateParam param = new UserMessageUpdateParam();
        param.setId(id);
        param.setAvatar(key);
        return Result.success(userService.userUpdate(param));
    }

    /**
     * 通过token登录接口
     * @param token 登录凭证token
     * @return 登录结果，包含用户登录信息的Result对象
     */
    @GetMapping("/login/{token}")
    public Result<UserLoginVO> loginByToken(@PathVariable("token") String token) {
        // 调用用户服务通过token登录，并返回成功结果
        return Result.success(userService.loginByToken(token));
    }



}
