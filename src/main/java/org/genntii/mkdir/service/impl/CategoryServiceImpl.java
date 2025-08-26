package org.genntii.mkdir.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.genntii.mkdir.domain.entity.Category;
import org.genntii.mkdir.domain.param.CategorySaveParam;
import org.genntii.mkdir.domain.vo.Category4bVO;
import org.genntii.mkdir.domain.vo.CategoryVO;
import org.genntii.mkdir.mapper.ArticleCategoryMapper;
import org.genntii.mkdir.mapper.CategoryMapper;
import org.genntii.mkdir.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mkdir
 * @since 2025/08/22 14:06
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Override
    public boolean categorySave(CategorySaveParam param) {
        Category category = new Category();
        if (ObjectUtil.isNotNull(param.getCategoryName())) {
            category.setCategoryName(param.getCategoryName());
        }
        if (ObjectUtil.isNotNull(param.getStatus())) {
            category.setStatus(param.getStatus());
        }
        if (ObjectUtil.isNotNull(param.getDescription())) {
            category.setDescription(param.getDescription());
        }

        try {
            if (ObjectUtil.isNotNull(param.getId())) {
                category.setId(param.getId());
                baseMapper.updateById(category);
                return true;
            }else {
                baseMapper.insert(category);
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }


    @Override
    public List<CategoryVO> getCategoryVOList(List<Long> ids) {
        return baseMapper.getCategoryVOList(ids);
    }

    @Override
    public List<Category4bVO> getCategory4bVOList() {
        return baseMapper.getCategory4bVOList();
    }


}
