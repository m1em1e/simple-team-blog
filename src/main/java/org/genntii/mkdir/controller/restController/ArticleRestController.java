package org.genntii.mkdir.controller.restController;

import cn.hutool.core.util.ObjectUtil;
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
import org.genntii.mkdir.domain.param.ArticleUpdateDetailParam;
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

    /**
     * 插入、更新文章详情接口
     * @param param 文章更新参数对象，包含封面ID、标题、内容和分类ID列表
     * @param token 用户身份认证令牌
     * @return 操作结果，成功返回Result.success()
     */
    @PostMapping("/detail")
    public Result update(@RequestBody ArticleUpdateDetailParam param, @RequestHeader("Authorization") String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        // 解析JWT获取用户ID
        Long id = jwtCommonUtil.parseJwt(token);
        // 构造文章对象并设置基本信息
        Article article = new Article();
        article.setAuthor(id);
        article.setCoverId(param.getCoverId());
        article.setTitle(param.getTitle());
        // 设置文章简介，如果内容长度小于等于10则使用全部内容，否则截取前7个字符加省略号
        article.setIntroduction(param.getContent().length()<=10? param.getContent() : param.getContent().substring(0, 7) + "...");
        article.setContent(param.getContent());
        article.setStatus((byte) 1);
        articleService.insert(article);

        // 构造文章分类关联列表
        List<ArticleCategory> articleCategories = new ArrayList<>();
        for (Long l : param.getCategoryId()) {
            ArticleCategory articleCategory = new ArticleCategory();
            articleCategory.setArticleId(article.getId());
            articleCategory.setCategoryId(l);
            articleCategories.add(articleCategory);
        }
        // 批量保存文章分类关联关系
        articleCategoryService.getBaseMapper().insert(articleCategories);

        return Result.success();
    }



    /**
     * 获取文章详情
     *
     * @param id 文章ID
     * @return 文章详情信息
     */
    @GetMapping("/detail/{id}")
    public Result<ArticleDetailVO> getArticleDetail(@PathVariable("id") Long id) {
        ArticleDetailVO articleDetail = articleService.getArticleDetail(id);

        List<Long> categoryIdList = articleCategoryService.getCategoryListByArticleId(articleDetail.getId());

        List<CategoryVO> categoryVOList = categoryService.getCategoryVOList(categoryIdList);

        User user = userService.getById(articleDetail.getAuthor());
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
    public Result<PageResult<ArticleInfoVO>> getPageResult(PageQueryParam param) {
        PageResult<ArticleInfoVO> articleInfoList = articleService.getArticleInfoList(param);
        for (ArticleInfoVO article : articleInfoList) {
            List<Long> categoryIdList = articleCategoryService.getCategoryListByArticleId(Long.valueOf(article.getId()));
            if (ObjectUtil.isNotEmpty(categoryIdList) && ObjectUtil.isNotNull(categoryIdList)) {
                List<CategoryVO> categoryVOList = categoryService.getCategoryVOList(categoryIdList);
                article.setCategoryList(categoryVOList);
            }
            User user = userService.getBaseMapper().selectUser(Long.valueOf(article.getAuthor()));
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
            User user = userService.getById(article.getAuthor());
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
