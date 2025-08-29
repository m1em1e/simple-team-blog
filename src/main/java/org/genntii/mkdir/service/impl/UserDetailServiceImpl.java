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
 * 用户详情服务实现类
 * 实现Spring Security的UserDetailsService接口和自定义UserService接口，
 * 提供用户认证、注册、信息查询和更新等核心业务逻辑
 *
 * @author mkdir
 * @since 2025/08/20 10:24
 */
@Service
public class UserDetailServiceImpl extends ServiceImpl<UserMapper, User> implements UserDetailsService, UserService {

    /**
     * 图片文件工具类，用于处理用户头像等图片资源
     */
    @Resource
    private ImageFileUtil imageFileUtil;

    /**
     * JWT工具类，用于生成和解析JWT令牌
     */
    @Resource
    private JwtCommonUtil jwtCommonUtil;

    /**
     * 根据用户名加载用户详情，实现Spring Security的UserDetailsService接口
     *
     * @param username 用户名
     * @return 用户详情对象
     * @throws UsernameNotFoundException 当用户不存在时抛出异常
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        // 从数据库查询用户信息
        User user = baseMapper.getUserByUsername(username);

        // 检查用户是否存在
        if (ObjectUtil.isNull(user.getPassword())) {
            throw new UsernameNotFoundException("未找到用户");
        }

        // 返回用户详情对象
        return new UserDetailsImpl(user);
    }

    /**
     * 用户注册功能
     *
     * @param username 用户名
     * @param password 原始密码
     */
    @Override
    public void register(String username, String password) {
        User user = new User();
        // 生成密码盐值
        user.setSalt(BCrypt.gensalt());

        user.setUsername(username);
        // 使用BCrypt对密码进行加密
        user.setPassword(BCrypt.hashpw(password, user.getSalt()));

        // 插入用户到数据库
        baseMapper.insert(user);
    }

    /**
     * 用户登录验证功能
     *
     * @param username 用户名
     * @param unhandledPassword 未处理的原始密码
     * @return 验证成功返回true
     * @throws UsernameNotFoundException 当用户名不存在时抛出异常
     * @throws LoginFailedException 当密码错误时抛出异常
     */
    @Override
    public boolean login(String username, String unhandledPassword) {
        // 根据用户名查询用户信息
        User user = baseMapper.getUserByUsername(username);

        // 检查用户是否存在
        if (ObjectUtil.isNull(user.getPassword())) {
            throw new UsernameNotFoundException("用户名错误");
        }

        // 验证密码是否正确
        boolean isPass = BCrypt.checkpw(unhandledPassword, user.getPassword());

        // 密码错误则抛出异常
        if (!isPass) {
            throw new LoginFailedException("密码错误");
        }

        return true;
    }

    /**
     * 根据用户名获取用户实体对象
     *
     * @param username 用户名
     * @return 用户实体对象
     * @throws UsernameNotFoundException 当用户不存在时抛出异常
     */
    @Override
    public User getUserByUsername(String username) {
        // 从数据库查询用户信息
        User user = baseMapper.getUserByUsername(username);

        // 检查用户是否存在
        if (ObjectUtil.isNull(user.getPassword())) {
            throw new UsernameNotFoundException("未找到用户");
        }

        return user;
    }

    /**
     * 根据用户ID获取用户信息展示对象
     *
     * @param id 用户ID
     * @return 用户信息展示对象
     */
    @Override
    public UserVO getUserMessage(Long id) {
        // 根据ID查询用户信息
        User user = baseMapper.selectUser(id);

        // 转换为用户信息展示对象
        return getUserVO(user);
    }

    /**
     * 更新用户信息
     *
     * @param param 用户信息更新参数
     * @param id 用户ID
     * @return 更新后的用户信息展示对象
     */
    @Override
    public UserVO userUpdate(UserMessageUpdateParam param, Long id) {
        // 根据ID查询用户信息
        User user = baseMapper.selectById(id);

        // 逐个检查并更新用户信息字段
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
            // 将标签列表转换为逗号分隔的字符串存储
            StringBuilder tagString = new StringBuilder();
            for (String tag : param.getTags()) {
                tagString.append(tag);
                tagString.append(",");
            }
            user.setTags(tagString.toString());
        }

        // 更新用户信息到数据库
        baseMapper.updateById(user);

        // 返回更新后的用户信息展示对象
        return getUserVO(user);
    }

    /**
     * 根据JWT令牌获取用户登录信息
     *
     * @param token JWT令牌
     * @return 用户登录信息展示对象
     * @throws LoginFailedException 当令牌异常时抛出异常
     */
    @Override
    public UserLoginVO loginByToken(String token) {
        // 验证JWT令牌是否有效
        if (!jwtCommonUtil.checkJwt(token)) {
            throw new LoginFailedException("token异常");
        }

        // 解析JWT令牌获取用户ID
        Long id = jwtCommonUtil.parseJwt(token);

        // 查询用户信息并更新最后登录时间
        User user = baseMapper.selectById(id);
        user.setLastLoginTime(LocalDateTime.now());

        // 构建用户登录信息展示对象
        UserLoginVO userLoginVO = new UserLoginVO();
        userLoginVO.setId(String.valueOf(user.getId()));

        // 处理用户标签
        if (ObjectUtil.isNotNull(user.getTags()) && ObjectUtil.isNotEmpty(user.getTags())) {
            List<String> tags = Arrays.stream(user.getTags().split(",")).toList();
            userLoginVO.setTags(tags);
        }

        // 设置用户昵称
        if (ObjectUtil.isNotNull(user.getNickname()) && ObjectUtil.isNotEmpty(user.getNickname())) {
            userLoginVO.setNickname(user.getNickname());
        }

        // 设置用户性别（转换为中文显示）
        if (ObjectUtil.isNotNull(user.getSex())) {
            userLoginVO.setSex(user.getSex() == 1 ? "男" : "女");
        }

        // 设置用户生日
        if (ObjectUtil.isNotNull(user.getBirthday())) {
            userLoginVO.setBirthday(user.getBirthday());
        }

        // 设置用户头像URL（通过工具类获取完整URL）
        if (ObjectUtil.isNotNull(user.getAvatar()) && ObjectUtil.isNotEmpty(user.getAvatar())) {
            userLoginVO.setAvatar(imageFileUtil.getImgUrl(user.getAvatar()));
        }

        // 设置用户描述
        if (ObjectUtil.isNotNull(user.getDescription()) && ObjectUtil.isNotEmpty(user.getDescription())) {
            userLoginVO.setDescription(user.getDescription());
        }

        // 设置最后登录时间
        if (ObjectUtil.isNotNull(user.getLastLoginTime())) {
            userLoginVO.setLocalDateTime(user.getLastLoginTime());
        }

        // 生成新的JWT令牌
        userLoginVO.setToken(jwtCommonUtil.createJwt(id));

        return userLoginVO;
    }

    /**
     * 将User实体对象转换为UserVO展示对象
     *
     * @param user 用户实体对象
     * @return 用户信息展示对象
     */
    private UserVO getUserVO(User user) {
        UserVO userVO = new UserVO();
        userVO.setId(String.valueOf(user.getId()));

        // 设置用户昵称
        if (ObjectUtil.isNotNull(user.getNickname()) && ObjectUtil.isNotEmpty(user.getNickname())) {
            userVO.setNickname(user.getNickname());
        }

        // 设置用户性别
        if (ObjectUtil.isNotNull(user.getSex())) {
            userVO.setSex(user.getSex());
        }

        // 设置用户生日
        if (ObjectUtil.isNotNull(user.getBirthday())) {
            userVO.setBirthday(user.getBirthday());
        }

        // 设置用户描述
        if (ObjectUtil.isNotNull(user.getDescription()) && ObjectUtil.isNotEmpty(user.getDescription())) {
            userVO.setDescription(user.getDescription());
        }

        // 处理用户标签
        if (ObjectUtil.isNotNull(user.getTags()) && ObjectUtil.isNotEmpty(user.getTags())) {
            List<String> tags = Arrays.stream(user.getTags().split(",")).toList();
            userVO.setTags(tags);
        }

        // 设置用户头像URL（通过工具类获取完整URL）
        if (ObjectUtil.isNotNull(user.getAvatar()) && ObjectUtil.isNotEmpty(user.getAvatar())) {
            userVO.setAvatar(imageFileUtil.getImgUrl(user.getAvatar()));
        }

        // 设置最后登录时间
        if (ObjectUtil.isNotNull(user.getLastLoginTime())) {
            userVO.setLastLoginTime(user.getLastLoginTime());
        }

        return userVO;
    }

}
