package org.genntii.mkdir.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.genntii.mkdir.domain.entity.ArticleCategory;
import org.genntii.mkdir.mapper.ArticleCategoryMapper;
import org.genntii.mkdir.service.ArticleCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文章分类关联服务实现类
 * 实现文章与分类关联关系相关业务操作的具体逻辑，
 * 继承自MyBatis-Plus的ServiceImpl并实现ArticleCategoryService接口
 *
 * @author mkdir
 * @since 2025/08/22 14:07
 */
@Service
public class ArticleCategoryServiceImpl extends ServiceImpl<ArticleCategoryMapper, ArticleCategory> implements ArticleCategoryService {

    /**
     * 根据文章ID获取该文章所属的所有分类ID列表
     *
     * @param articleId 文章ID
     * @return 分类ID列表
     */
    @Override
    public List<Long> getCategoryListByArticleId(Long articleId) {
        return baseMapper.getCategoryListByArticleId(articleId);
    }

    /**
     * 根据分类ID获取该分类下的所有文章ID列表
     *
     * @param categoryId 分类ID
     * @return 文章ID列表
     */
    @Override
    public List<Long> getArticleListByCategoryId(Long categoryId) {
        return baseMapper.getArticleListByCategoryId(categoryId);
    }
}
