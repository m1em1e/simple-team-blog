package org.genntii.mkdir.mapper;

import cn.hutool.db.PageResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.genntii.mkdir.domain.entity.Article;
import org.genntii.mkdir.domain.vo.ArticleInfoVO;

import java.util.List;

/**
 *
 *
 * @author mkdir
 * @since 2025/08/22 13:56
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    List<ArticleInfoVO> getArticleListById(List<Long> articleIds);

    @Select("select id, cover_id, author, title, introduction, update_time " +
            "from article " +
            "where title like concat('*', #{keyword}, '*') " +
            "order by update_time desc " +
            "limit #{index}, #{size}")
    PageResult<ArticleInfoVO> getArticleList(@Param("index") int index,@Param("size") int size,@Param("keyword") String keyword);

    @Select("select id, cover_id, author, title, introduction, update_time " +
            "from article " +
            "order by update_time desc " +
            "limit #{index}, #{size}")
    PageResult<ArticleInfoVO> getArticleListWithoutKeyword(@Param("index") int index,@Param("size") int size);
}
