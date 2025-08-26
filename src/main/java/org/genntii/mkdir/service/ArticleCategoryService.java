package org.genntii.mkdir.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.genntii.mkdir.domain.entity.ArticleCategory;

import java.util.List;

/**
 * @author mkdir
 * @since 2025/08/22 14:04
 */
public interface ArticleCategoryService extends IService<ArticleCategory> {

    List<Long> getArticleListByCategoryId(Long categoryId);


    List<Long> getCategoryListByArticleId(Long articleId);

}
