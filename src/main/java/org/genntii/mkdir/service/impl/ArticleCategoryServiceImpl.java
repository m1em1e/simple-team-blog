package org.genntii.mkdir.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.genntii.mkdir.domain.entity.ArticleCategory;
import org.genntii.mkdir.mapper.ArticleCategoryMapper;
import org.genntii.mkdir.service.ArticleCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mkdir
 * @since 2025/08/22 14:07
 */
@Service
public class ArticleCategoryServiceImpl extends ServiceImpl<ArticleCategoryMapper, ArticleCategory> implements ArticleCategoryService {

    @Override
    public List<Long> getCategoryListByArticleId(Long articleId) {
        return baseMapper.getCategoryListByArticleId(articleId);
    }

    @Override
    public List<Long> getArticleListByCategoryId(Long categoryId) {
        return baseMapper.getArticleListByCategoryId(categoryId);
    }
}
