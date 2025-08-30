package org.genntii.mkdir.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.genntii.mkdir.domain.entity.Category;
import org.genntii.mkdir.domain.param.CategorySaveParam;
import org.genntii.mkdir.domain.vo.Category4bVO;
import org.genntii.mkdir.domain.vo.CategoryVO;
import org.genntii.mkdir.mapper.CategoryMapper;
import org.genntii.mkdir.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 分类服务实现类
 * 实现分类相关业务操作的具体逻辑，继承自MyBatis-Plus的ServiceImpl并实现CategoryService接口
 *
 * @author mkdir
 * @since 2025/08/22 14:06
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    /**
     * 保存或更新分类信息
     *
     * @param param 分类保存参数对象
     * @return 保存成功返回true，否则返回false
     */
    @Override
    public boolean categorySave(CategorySaveParam param) {
        // 创建分类实体对象
        Category category = new Category();

        // 设置分类名称（如果参数中存在）
        if (ObjectUtil.isNotNull(param.getCategoryName())) {
            category.setCategoryName(param.getCategoryName());
        }

        // 设置分类状态（如果参数中存在）
        if (ObjectUtil.isNotNull(param.getStatus())) {
            category.setStatus(param.getStatus());
        }

        // 设置分类描述（如果参数中存在）
        if (ObjectUtil.isNotNull(param.getDescription())) {
            category.setDescription(param.getDescription());
        }

        try {
            // 如果参数中包含ID，则执行更新操作
            if (ObjectUtil.isNotNull(param.getId())) {
                category.setId(param.getId());
                baseMapper.updateById(category);
                return true;
            } else {
                // 否则执行插入操作
                baseMapper.insert(category);
                return true;
            }
        } catch (Exception e) {
            // 发生异常时返回false
            return false;
        }
    }

    /**
     * 根据分类ID列表获取分类信息展示对象列表
     *
     * @param ids 分类ID列表
     * @return 分类信息展示对象列表
     */
    @Override
    public List<CategoryVO> getCategoryVOList(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return new ArrayList<>();
        }
        return baseMapper.getCategoryVOList(ids);
    }

    /**
     * 获取所有分类的详细信息展示对象列表
     *
     * @return 分类详细信息展示对象列表
     */
    @Override
    public List<Category4bVO> getCategory4bVOList() {
        return baseMapper.getCategory4bVOList();
    }

}
