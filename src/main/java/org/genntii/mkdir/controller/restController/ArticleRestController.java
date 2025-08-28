package org.genntii.mkdir.controller.restController;

import cn.hutool.db.PageResult;
import jakarta.annotation.Resource;
import org.genntii.mkdir.common.result.Result;
import org.genntii.mkdir.common.util.ImageFileUtil;
import org.genntii.mkdir.common.util.JwtCommonUtil;
import org.genntii.mkdir.domain.entity.Article;
import org.genntii.mkdir.domain.entity.ArticleCategory;
import org.genntii.mkdir.domain.entity.User;
import org.genntii.mkdir.domain.param.PageQueryParam;
import org.genntii.mkdir.domain.vo.ArticleDetailVO;
import org.genntii.mkdir.domain.vo.ArticleInfoVO;
import org.genntii.mkdir.domain.vo.ArticleUpdateDetailParam;
import org.genntii.mkdir.domain.vo.CategoryVO;
import org.genntii.mkdir.service.ArticleCategoryService;
import org.genntii.mkdir.service.ArticleService;
import org.genntii.mkdir.service.CategoryService;
import org.genntii.mkdir.service.impl.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 文章相关接口控制器
 * 提供文章详情、分页查询、分类查询等功能
 *
 * @author mkdir
 * @since 2025/08/25 13:34
 */
@RestController
@RequestMapping("/api/article")
public class ArticleRestController {

    @Resource
    private ArticleService articleService;

    @Resource
    private CategoryService categoryService;

    @Resource
    private ArticleCategoryService articleCategoryService;

    @Resource
    private JwtCommonUtil jwtCommonUtil;

    @Resource
    private UserDetailServiceImpl userService;
    @Autowired
    private ImageFileUtil imageFileUtil;

    @PostMapping("/detail")
    public Result update(@RequestBody ArticleUpdateDetailParam param, @RequestHeader("Authorization") String token) {
        Long id = jwtCommonUtil.parseJwt(token);
        Article article = new Article();
        article.setAuthorId(id);
        article.setCoverId(param.getCoverId());
        article.setTitle(param.getTitle());
        article.setIntroduction(param.getContent().length()<=10? param.getContent() : param.getContent().substring(0, 7) + "...");
        article.setContent(param.getContent());
        article.setStatus((byte) 1);
        articleService.save(article);

        List<ArticleCategory> articleCategories = new ArrayList<>();
        for (Long l : param.getCategoryId()) {
            ArticleCategory articleCategory = new ArticleCategory();
            articleCategory.setArticleId(article.getId());
            articleCategory.setCategoryId(l);
            articleCategories.add(articleCategory);
        }
        articleCategoryService.saveBatch(articleCategories);

        return Result.success();
    }


    /**
     * 获取文章详情
     *
     * @param id 文章ID
     * @return 文章详情信息
     */
    @GetMapping("/detail/{id}")
    public Result<ArticleDetailVO> getArticleDetail(@PathVariable Long id) {
        ArticleDetailVO articleDetail = articleService.getArticleDetail(id);

        List<Long> categoryIdList = articleCategoryService.getCategoryListByArticleId(articleDetail.getId());

        List<CategoryVO> categoryVOList = categoryService.getCategoryVOList(categoryIdList);

        User user = userService.getById(articleDetail.getAuthorId());
        articleDetail.setAuthorName(user.getNickname());
        articleDetail.setAuthorAvatarUrl(imageFileUtil.getImgUrl(user.getAvatar()));

        articleDetail.setCategoryList(categoryVOList);

        return Result.success(articleDetail);
    }

    /**
     * 分页获取文章列表
     *
     * @param param 分页查询参数
     * @return 分页文章信息列表
     */
    @GetMapping("/info")
    public Result<PageResult<ArticleInfoVO>> getPageResult(@RequestParam PageQueryParam param) {
        PageResult<ArticleInfoVO> articleInfoList = articleService.getArticleInfoList(param);
        for (ArticleInfoVO article : articleInfoList) {
            User user = userService.getById(article.getAuthorId());
            article.setAuthorName(user.getNickname());
            article.setAuthorAvatarUrl(imageFileUtil.getImgUrl(user.getAvatar()));
        }
        return Result.success(articleInfoList);
    }

    /**
     * 根据分类ID获取文章列表
     *
     * @param id 分类ID
     * @return 该分类下的文章信息列表
     */
    @GetMapping("/category/{id}")
    public Result<List<ArticleInfoVO>> getList(@PathVariable Long id) {
        List<Long> articleIds = articleCategoryService.getArticleListByCategoryId(id);
        List<ArticleInfoVO> articleInfoVOList = articleService.getArticleInfoListById(articleIds);
        for (ArticleInfoVO article : articleInfoVOList) {
            User user = userService.getById(article.getAuthorId());
            article.setAuthorName(user.getNickname());
            article.setAuthorAvatarUrl(imageFileUtil.getImgUrl(user.getAvatar()));
        }
        return Result.success(articleInfoVOList);
    }


    /**
     * 删除指定ID的文章
     *
     * @param id 要删除的文章ID
     * @return 操作结果，删除成功返回成功状态
     */
    @DeleteMapping("/article/{id}")
    public Result deleteArticle(@PathVariable Long id) {
        // 执行文章删除操作
        articleService.removeById(id);
        return Result.success();
    }


}
