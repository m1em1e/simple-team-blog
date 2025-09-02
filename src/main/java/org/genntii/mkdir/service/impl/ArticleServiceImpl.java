package org.genntii.mkdir.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.db.PageResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.genntii.mkdir.common.util.ImageFileUtil;
import org.genntii.mkdir.domain.entity.Article;
import org.genntii.mkdir.domain.entity.Image;
import org.genntii.mkdir.domain.param.PageQueryParam;
import org.genntii.mkdir.domain.vo.ArticleDetailVO;
import org.genntii.mkdir.domain.vo.ArticleInfoVO;
import org.genntii.mkdir.mapper.ArticleMapper;
import org.genntii.mkdir.mapper.ImageMapper;
import org.genntii.mkdir.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文章服务实现类
 * 实现文章相关业务操作的具体逻辑，继承自MyBatis-Plus的ServiceImpl并实现ArticleService接口
 *
 * @author mkdir
 * @since 2025/08/22 14:05
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    /**
     * 图片文件工具类，用于处理文章封面等图片资源
     */
    @Resource
    private ImageFileUtil imageFileUtil;

    /**
     * 图片映射器，用于查询图片相关信息
     */
    @Resource
    private ImageMapper imageMapper;
    @Autowired
    private ArticleMapper articleMapper;

    /**
     * 根据文章ID列表获取文章信息展示对象列表
     *
     * @param ids 文章ID列表
     * @return 文章信息展示对象列表
     */
    @Override
    public List<ArticleInfoVO> getArticleInfoListById(List<Long> ids) {
        // 根据ID列表查询文章信息
        List<ArticleInfoVO> articleList = baseMapper.getArticleListById(ids);

        // 遍历文章列表，处理封面图片URL
        for (ArticleInfoVO article : articleList) {
            // 如果封面ID为空则跳过
            if (ObjectUtil.isNull(article.getCoverId())) continue;

            // 获取封面图片的完整URL
            String coverUrl = imageFileUtil.getImgUrl(article.getCoverId());
            article.setCoverId(coverUrl);
        }
        return articleList;
    }

    /**
     * 根据分页查询参数获取文章信息展示对象的分页结果
     *
     * @param param 分页查询参数
     * @return 文章信息展示对象的分页结果
     */
    @Override
    public PageResult<ArticleInfoVO> getArticleInfoList(PageQueryParam param) {
        PageResult<ArticleInfoVO> articleList;

        // 根据是否有关键字执行不同的查询方法
        if (ObjectUtil.isNull(param.getKeyword()) || ObjectUtil.isEmpty(param.getKeyword())) {
            // 无关键字查询
            articleList = baseMapper.getArticleListWithoutKeyword((param.getPageIndex() - 1) * param.getPageSize(), param.getPageSize());
        } else {
            // 有关键字查询
            articleList = baseMapper.getArticleList((param.getPageIndex() - 1) * param.getPageSize(), param.getPageSize(), param.getKeyword());
        }

        // 构建查询条件用于统计总数
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.like("title", param.getKeyword());

        // 设置分页信息
        articleList.setPage(param.getPageIndex());
        articleList.setPageSize(param.getPageSize());
        articleList.setTotal(Math.toIntExact(baseMapper.selectCount(wrapper)));
        articleList.setTotalPage(articleList.getTotal() / param.getPageSize() + 1);

        // 处理每篇文章的封面信息
        for (ArticleInfoVO article : articleList) {
            // 获取封面图片高度
            Image image = imageMapper.geyHeightByKey(article.getCoverId());

            article.setCoverHeight(image.getHeight());
            article.setCoverWidth(image.getWidth());

            // 如果封面ID为空则跳过
            if (ObjectUtil.isNull(article.getCoverId())) continue;

            // 获取封面图片的完整URL
            String coverUrl = imageFileUtil.getImgUrl(article.getCoverId());
            article.setCoverId(coverUrl);
        }
        return articleList;
    }

    /**
     * 根据文章ID获取文章详情展示对象
     *
     * @param id 文章ID
     * @return 文章详情展示对象
     */
    @Override
    public ArticleDetailVO getArticleDetail(Long id) {
        // 根据ID查询文章实体
        Article article = baseMapper.selectById(id);

        // 构建文章详情展示对象
        ArticleDetailVO articleDetailVO = ArticleDetailVO.builder()
                .id(article.getId())
                .title(article.getTitle())
                .author(article.getAuthor())
                .content(article.getContent())
                .updateTime(article.getUpdateTime())
                .build();

        // 如果文章有封面，则设置封面图片的完整URL
        if (ObjectUtil.isNotNull(article.getCoverId())) {
            articleDetailVO.setCover(imageFileUtil.getImgUrl(article.getCoverId()));
        }

        return articleDetailVO;
    }

    @Override
    public void insert(Article article) {
        articleMapper.insert(article);
    }
}
