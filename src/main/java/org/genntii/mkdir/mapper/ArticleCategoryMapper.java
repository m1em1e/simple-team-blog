package org.genntii.mkdir.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.genntii.mkdir.domain.entity.ArticleCategory;

import java.util.List;

/**
 * @author mkdir
 * @since 2025/08/22 14:03
 */
@Mapper
public interface ArticleCategoryMapper extends BaseMapper<ArticleCategory> {


    @Select("select article_id from article_category where category_id = #{categoryId}")
    List<Long> getArticleListByCategoryId(Long categoryId);


    @Select("select category_id from article_category where article_id = #{articleId}")
    List<Long> getCategoryListByArticleId(Long articleId);


    void insertBatch(List<ArticleCategory> articleCategories);
}
