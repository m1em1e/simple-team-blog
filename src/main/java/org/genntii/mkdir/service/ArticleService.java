package org.genntii.mkdir.service;

import cn.hutool.db.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;
import org.genntii.mkdir.domain.entity.Article;
import org.genntii.mkdir.domain.param.PageQueryParam;
import org.genntii.mkdir.domain.vo.ArticleDetailVO;
import org.genntii.mkdir.domain.vo.ArticleInfoVO;

import java.util.List;

/**
 * 文章服务接口
 * 提供文章相关业务操作方法
 *
 * @author mkdir
 * @since 2025/08/22 14:03
 */
public interface ArticleService extends IService<Article> {

    /**
     * 根据文章ID列表获取文章信息展示对象列表
     *
     * @param ids 文章ID列表
     * @return 文章信息展示对象列表
     */
    List<ArticleInfoVO> getArticleInfoListById(List<Long> ids);

    /**
     * 根据分页查询参数获取文章信息展示对象的分页结果
     *
     * @param param 分页查询参数
     * @return 文章信息展示对象的分页结果
     */
    PageResult<ArticleInfoVO> getArticleInfoList(PageQueryParam param);

    /**
     * 根据文章ID获取文章详情展示对象
     *
     * @param id 文章ID
     * @return 文章详情展示对象
     */
    ArticleDetailVO getArticleDetail(Long id);

}
