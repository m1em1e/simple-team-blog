package org.genntii.mkdir.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.genntii.mkdir.domain.entity.User;
import org.genntii.mkdir.domain.param.UserMessageUpdateParam;
import org.genntii.mkdir.domain.vo.UserLoginVO;
import org.genntii.mkdir.domain.vo.UserVO;

/**
 * @author mkdir
 * @since 2025/08/20 10:25
 */
public interface UserService extends IService<User> {

    void register(String username, String password);

    boolean login(String username, String unhandledPassword);

    User getUserByUsername(String username);

    UserVO getUserMessage(Long id);

    UserVO userUpdate(UserMessageUpdateParam param, Long id);

    UserLoginVO loginByToken(String token);
}
