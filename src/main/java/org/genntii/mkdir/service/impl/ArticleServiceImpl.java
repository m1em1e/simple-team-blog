package org.genntii.mkdir.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.db.PageResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.genntii.mkdir.common.util.ImageFileUtil;
import org.genntii.mkdir.domain.entity.Article;
import org.genntii.mkdir.domain.param.PageQueryParam;
import org.genntii.mkdir.domain.vo.ArticleDetailVO;
import org.genntii.mkdir.domain.vo.ArticleInfoVO;
import org.genntii.mkdir.mapper.ArticleMapper;
import org.genntii.mkdir.service.ArticleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mkdir
 * @since 2025/08/22 14:05
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Resource
    private ImageFileUtil imageFileUtil;

    @Override
    public List<ArticleInfoVO> getArticleInfoListById(List<Long> ids) {
        List<ArticleInfoVO> articleList = baseMapper.getArticleListById(ids);
        for (ArticleInfoVO article : articleList) {
            if (ObjectUtil.isNull(article.getCover())) continue;
            String coverUrl = imageFileUtil.getImgUrl(article.getCover());
            article.setCover(coverUrl);
        }
        return articleList;
    }

    @Override
    public PageResult<ArticleInfoVO> getArticleInfoList(PageQueryParam param) {
        PageResult<ArticleInfoVO> articleList = baseMapper.getArticleList((param.getPageIndex() - 1) * param.getPageSize(), param.getPageSize(), param.getKeyword());

        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.like("title", param.getKeyword());

        articleList.setPage(param.getPageIndex());
        articleList.setPageSize(param.getPageSize());
        articleList.setTotal(Math.toIntExact(baseMapper.selectCount(wrapper)));
        articleList.setTotalPage(articleList.getTotal() / param.getPageSize() + 1);

        for (ArticleInfoVO article : articleList) {
            if (ObjectUtil.isNull(article.getCover())) continue;
            String coverUrl = imageFileUtil.getImgUrl(article.getCover());
            article.setCover(coverUrl);
        }
        return articleList;
    }

    @Override
    public ArticleDetailVO getArticleDetail(Long id) {
        Article article = baseMapper.selectById(id);

        ArticleDetailVO articleDetailVO = ArticleDetailVO.builder()
                .id(article.getId())
                .title(article.getTitle())
                .author(article.getAuthor())
                .content(article.getContent())
                .updateTime(article.getUpdateTime())
                .build();

        if (ObjectUtil.isNotNull(article.getCoverId())) {
            articleDetailVO.setCover(imageFileUtil.getImgUrl(article.getCoverId()));
        }

        return articleDetailVO;
    }
}
