package org.genntii.mkdir.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.genntii.mkdir.common.Exception.LoginFailedException;
import org.genntii.mkdir.common.util.ImageFileUtil;
import org.genntii.mkdir.common.util.JwtCommonUtil;
import org.genntii.mkdir.domain.dto.UserDetailsImpl;
import org.genntii.mkdir.domain.entity.User;
import org.genntii.mkdir.domain.param.UserMessageUpdateParam;
import org.genntii.mkdir.domain.vo.UserLoginVO;
import org.genntii.mkdir.domain.vo.UserVO;
import org.genntii.mkdir.mapper.UserMapper;
import org.genntii.mkdir.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * @author mkdir
 * @since 2025/08/20 10:24
 */
@Service
public class UserDetailServiceImpl extends ServiceImpl<UserMapper, User> implements UserDetailsService, UserService {


    @Resource
    private ImageFileUtil imageFileUtil;

    @Resource
    private JwtCommonUtil jwtCommonUtil;


    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = baseMapper.getUserByUsername(username);

        if (ObjectUtil.isNull(user.getPassword())) {
            throw new UsernameNotFoundException("未找到用户");
        }

        return new UserDetailsImpl(user);
    }

    @Override
    public void register(String username, String password) {
        User user = new User();
        user.setSalt(BCrypt.gensalt());

        user.setUsername(username);
        user.setPassword(BCrypt.hashpw(password, user.getSalt()));

        baseMapper.insert(user);
    }

    @Override
    public boolean login(String username, String unhandledPassword) {

        User user = baseMapper.getUserByUsername(username);

        if (ObjectUtil.isNull(user.getPassword())) {
            throw new UsernameNotFoundException("用户名错误");
        }

        boolean isPass = BCrypt.checkpw(unhandledPassword, user.getPassword());

        if (!isPass) {
            throw new LoginFailedException("密码错误");
        }


        return true;
    }


    @Override
    public User getUserByUsername(String username) {
        User user = baseMapper.getUserByUsername(username);

        if (ObjectUtil.isNull(user.getPassword())) {
            throw new UsernameNotFoundException("未找到用户");
        }

        return user;
    }

    @Override
    public UserVO getUserMessage(Long id) {
        User user = baseMapper.selectUser(id);

        return getUserVO(user);
    }




    @Override
    public UserVO userUpdate(UserMessageUpdateParam param, Long id) {
        User user = baseMapper.selectById(id);

        if (ObjectUtil.isNotNull(param.getNickname()) && ObjectUtil.isNotEmpty(param.getNickname())) {
            user.setNickname(param.getNickname());
        }

        if (ObjectUtil.isNotNull(param.getAvatar()) && ObjectUtil.isNotEmpty(param.getAvatar())) {
            user.setAvatar(param.getAvatar());
        }

        if (ObjectUtil.isNotNull(param.getSex()) && ObjectUtil.isNotEmpty(param.getSex())) {
            user.setSex(param.getSex());
        }

        if (ObjectUtil.isNotNull(param.getBirthday()) && ObjectUtil.isNotEmpty(param.getBirthday())) {
            user.setBirthday(param.getBirthday());
        }

        if (ObjectUtil.isNotNull(param.getDescription()) && ObjectUtil.isNotEmpty(param.getDescription())) {
            user.setDescription(param.getDescription());
        }

        if (ObjectUtil.isNotNull(param.getTags()) && ObjectUtil.isNotEmpty(param.getTags())) {
            StringBuilder tagString = new StringBuilder();
            for (String tag : param.getTags()) {
                tagString.append(tag);
            }
            user.setTags(tagString.toString());
        }

        baseMapper.updateById(user);

        return getUserVO(user);
    }


    @Override
    public UserLoginVO loginByToken(String token) {
        if (!jwtCommonUtil.checkJwt(token)) {
            throw new LoginFailedException("token异常");
        }
        Long id = jwtCommonUtil.parseJwt(token);

        User user = baseMapper.selectById(id);
        user.setLastLoginTime(LocalDateTime.now());
        UserLoginVO userLoginVO = new UserLoginVO();
        if (ObjectUtil.isNotNull(user.getTags()) && ObjectUtil.isNotEmpty(user.getTags())) {
            List<String> tags = Arrays.stream(user.getTags().split(",")).toList();
            userLoginVO.setTags(tags);
        }
        if (ObjectUtil.isNotNull(user.getNickname()) && ObjectUtil.isNotEmpty(user.getNickname())) {
            userLoginVO.setNickname(user.getNickname());
        }
        if (ObjectUtil.isNotNull(user.getSex())) {
            userLoginVO.setSex(user.getSex() == 1 ? "男" : "女");
        }
        if (ObjectUtil.isNotNull(user.getBirthday())) {
            userLoginVO.setBirthday(user.getBirthday());
        }
        if (ObjectUtil.isNotNull(user.getAvatar()) && ObjectUtil.isNotEmpty(user.getAvatar())) {
            userLoginVO.setAvatar(imageFileUtil.getImgUrl(user.getAvatar()));
        }
        if (ObjectUtil.isNotNull(user.getDescription()) && ObjectUtil.isNotEmpty(user.getDescription())) {
            userLoginVO.setDescription(user.getDescription());
        }
        if (ObjectUtil.isNotNull(user.getLastLoginTime())) {
            userLoginVO.setLocalDateTime(user.getLastLoginTime());
        }
        userLoginVO.setToken(jwtCommonUtil.createJwt(id));

        return userLoginVO;
    }


    private UserVO getUserVO(User user) {
        UserVO userVO = new UserVO();
        if (ObjectUtil.isNotNull(user.getNickname()) && ObjectUtil.isNotEmpty(user.getNickname())) {
            userVO.setNickname(user.getNickname());
        }
        if (ObjectUtil.isNotNull(user.getSex())) {
            userVO.setSex(user.getSex() == 1 ? "男" : "女");
        }
        if (ObjectUtil.isNotNull(user.getBirthday())) {
            userVO.setBirthday(user.getBirthday());
        }
        if (ObjectUtil.isNotNull(user.getDescription()) && ObjectUtil.isNotEmpty(user.getDescription())) {
            userVO.setDescription(user.getDescription());
        }
        if (ObjectUtil.isNotNull(user.getTags()) && ObjectUtil.isNotEmpty(user.getTags())) {
            List<String> tags = Arrays.stream(user.getTags().split(",")).toList();
            userVO.setTags(tags);
        }
        if (ObjectUtil.isNotNull(user.getAvatar()) && ObjectUtil.isNotEmpty(user.getAvatar())) {
            userVO.setAvatar(imageFileUtil.getImgUrl(user.getAvatar()));
        }
        if (ObjectUtil.isNotNull(user.getLastLoginTime())) {
            userVO.setLastLoginTime(user.getLastLoginTime());
        }

        return userVO;
    }


}
