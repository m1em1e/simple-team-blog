package org.genntii.mkdir.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.genntii.mkdir.domain.entity.User;

/**
 * @author mkdir
 * @since 2025/08/20 10:16
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {


    @Select("select id, nickname, username, password, salt, " +
            "sex, birthday, avatar, tags, description, create_time, update_time " +
            "from user " +
            "where username = #{username}")
    User getUserByUsername(String username);

    @Select("select * from user where id = #{id}")
    User selectUser(Long id);
}
