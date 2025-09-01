package org.genntii.mkdir.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.genntii.mkdir.domain.entity.Image;

/**
 * @author mkdir
 * @since 2025/08/22 15:20
 */
@Mapper
public interface ImageMapper extends BaseMapper<Image> {

    @Select("select * from image where `key` = #{key}")
    Image geyHeightByKey(@Param("key") String key);

}
