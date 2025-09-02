package org.genntii.mkdir.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.genntii.mkdir.domain.entity.ArticleCategory;

import java.util.List;

/**
 * 文章分类关联服务接口
 * 提供文章与分类关联关系的相关业务操作方法
 *
 * @author mkdir
 * @since 2025/08/22 14:04
 */
public interface ArticleCategoryService extends IService<ArticleCategory> {

    /**
     * 根据分类ID获取该分类下的所有文章ID列表
     *
     * @param categoryId 分类ID
     * @return 文章ID列表
     */
    List<Long> getArticleListByCategoryId(Long categoryId);

    /**
     * 根据文章ID获取该文章所属的所有分类ID列表
     *
     * @param articleId 文章ID
     * @return 分类ID列表
     */
    List<Long> getCategoryListByArticleId(Long articleId);

    void insertBatch(List<ArticleCategory> articleCategories);
}
