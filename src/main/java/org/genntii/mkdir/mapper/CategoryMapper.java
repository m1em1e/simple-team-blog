package org.genntii.mkdir.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.genntii.mkdir.domain.entity.Category;
import org.genntii.mkdir.domain.vo.Category4bVO;
import org.genntii.mkdir.domain.vo.CategoryVO;

import java.util.List;

/**
 * @author mkdir
 * @since 2025/08/22 14:02
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
    List<CategoryVO> getCategoryVOList(List<Long> ids);

    @Select("select id, category_name, description, status from category")
    List<Category4bVO> getCategory4bVOList();
}
