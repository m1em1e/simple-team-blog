package org.genntii.mkdir.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.genntii.mkdir.domain.entity.Category;
import org.genntii.mkdir.domain.param.CategorySaveParam;
import org.genntii.mkdir.domain.vo.Category4bVO;
import org.genntii.mkdir.domain.vo.CategoryVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 分类服务接口
 * 提供分类相关业务操作方法
 *
 * @author mkdir
 * @since 2025/08/22 14:04
 */
@Service
public interface CategoryService extends IService<Category> {
    /**
     * 保存或更新分类信息
     *
     * @param param 分类保存参数对象
     * @return 保存成功返回true，否则返回false
     */
    boolean categorySave(CategorySaveParam param);

    /**
     * 根据分类ID列表获取分类信息展示对象列表
     *
     * @param ids 分类ID列表
     * @return 分类信息展示对象列表
     */
    List<CategoryVO> getCategoryVOList(List<Long> ids);

    /**
     * 获取所有分类的详细信息展示对象列表
     *
     * @return 分类详细信息展示对象列表
     */
    List<Category4bVO> getCategory4bVOList();

}
