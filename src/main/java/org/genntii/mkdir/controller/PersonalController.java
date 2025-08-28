package org.genntii.mkdir.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 个人管理控制器
 * 处理与用户个人页面相关的请求
 *
 * @author mkdir
 * @since 2025/08/27 11:00
 */
@Slf4j
@Controller
public class PersonalController {

    /**
     * 处理访问管理者页面的GET请求
     * 根据用户ID跳转到对应的管理者页面
     *
     * @param id 用户唯一标识符，通过URL路径传入
     * @return 返回manager页面视图名称
     */
    @GetMapping("/manager/{id}")
    public String message(@PathVariable("id") Long id) {
        log.info("访问manager页面 对象用户ID：{}",id);
        return "manager"; // 对应 manager.html
    }

}
