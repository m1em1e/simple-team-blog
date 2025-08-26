package org.genntii.mkdir.controller.restController;

import jakarta.annotation.Resource;
import org.genntii.mkdir.common.Exception.DataSaveFaiedException;
import org.genntii.mkdir.common.result.Result;
import org.genntii.mkdir.domain.param.CategorySaveParam;
import org.genntii.mkdir.domain.vo.Category4bVO;
import org.genntii.mkdir.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 分类管理控制器
 * 提供分类的增删查功能
 *
 * @author mkdir
 * @since 2025/08/22 17:27
 */
@RestController
@RequestMapping("/api/category")
public class CategoryRestController {

    @Resource
    private CategoryService categoryService;

    /**
     * 添加或更新分类
     * 保存分类信息，如果保存失败则抛出异常
     *
     * @param param 分类保存参数
     * @throws DataSaveFaiedException 数据保存失败异常
     */
    @PostMapping("/save")
    public Result addCategory(CategorySaveParam param) {
        if (!categoryService.categorySave(param)) {
            throw new DataSaveFaiedException("标签更新失败");
        }
        return Result.success();
    }

    /**
     * 删除指定ID的分类
     *
     * @param id 要删除的分类ID
     */
    @DeleteMapping("/{id}")
    public Result deleteCategory(@PathVariable Long id) {
        categoryService.removeById(id);
        return Result.success();
    }

    /**
     * 获取所有分类列表
     *
     * @return 分类信息列表
     */
    @GetMapping("/list")
    public Result<List<Category4bVO>> getCategory4bVOList() {
        List<Category4bVO> category4bVOList = categoryService.getCategory4bVOList();

        return Result.success(category4bVOList);
    }

}
