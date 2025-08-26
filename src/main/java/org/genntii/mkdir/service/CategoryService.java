package org.genntii.mkdir.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.genntii.mkdir.domain.entity.Category;
import org.genntii.mkdir.domain.param.CategorySaveParam;
import org.genntii.mkdir.domain.vo.Category4bVO;
import org.genntii.mkdir.domain.vo.CategoryVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mkdir
 * @since 2025/08/22 14:04
 */
@Service
public interface CategoryService extends IService<Category> {
    boolean categorySave(CategorySaveParam param);

    List<CategoryVO> getCategoryVOList(List<Long> ids);

    List<Category4bVO> getCategory4bVOList();

}
