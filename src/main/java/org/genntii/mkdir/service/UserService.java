package org.genntii.mkdir.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.genntii.mkdir.domain.entity.User;
import org.genntii.mkdir.domain.param.UserMessageUpdateParam;
import org.genntii.mkdir.domain.vo.UserLoginVO;
import org.genntii.mkdir.domain.vo.UserVO;

/**
 * 用户服务接口
 * 提供用户相关业务操作方法
 *
 * @author mkdir
 * @since 2025/08/20 10:25
 */
public interface UserService extends IService<User> {

    /**
     * 用户注册
     *
     * @param username 用户名
     * @param password 密码
     */
    void register(String username, String password);

    /**
     * 用户登录验证
     *
     * @param username 用户名
     * @param unhandledPassword 未加密的原始密码
     * @return 登录成功返回true，否则返回false
     */
    boolean login(String username, String unhandledPassword);

    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return 用户实体对象
     */
    User getUserByUsername(String username);

    /**
     * 根据用户ID获取用户信息展示对象
     *
     * @param id 用户ID
     * @return 用户信息展示对象
     */
    UserVO getUserMessage(Long id);

    /**
     * 更新用户信息
     *
     * @param param 用户信息更新参数
     * @param id 用户ID
     * @return 更新后的用户信息展示对象
     */
    UserVO userUpdate(UserMessageUpdateParam param, Long id);

    /**
     * 根据令牌获取用户登录信息
     *
     * @param token 用户认证令牌
     * @return 用户登录信息展示对象
     */
    UserLoginVO loginByToken(String token);
}
