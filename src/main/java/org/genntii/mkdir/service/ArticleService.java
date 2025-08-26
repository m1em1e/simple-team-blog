package org.genntii.mkdir.service;

import cn.hutool.db.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;
import org.genntii.mkdir.domain.entity.Article;
import org.genntii.mkdir.domain.param.PageQueryParam;
import org.genntii.mkdir.domain.vo.ArticleDetailVO;
import org.genntii.mkdir.domain.vo.ArticleInfoVO;

import java.util.List;

/**
 * @author mkdir
 * @since 2025/08/22 14:03
 */
public interface ArticleService extends IService<Article> {

    List<ArticleInfoVO> getArticleInfoListById(List<Long> ids);

    PageResult<ArticleInfoVO> getArticleInfoList(PageQueryParam param);

    ArticleDetailVO getArticleDetail(Long id);

}
