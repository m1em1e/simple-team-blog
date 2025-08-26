package org.genntii.mkdir.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 主页面控制器
 * 处理系统主页面的路由请求
 *
 * @author mkdir
 * @since 2025/08/26 11:43
 */
@Controller
public class MainController {

    /**
     * 主页面路由
     * 处理根路径"/"的GET请求，返回主页面视图
     *
     * @return 返回"main"视图名称，对应templates/main.html模板
     */
    @GetMapping("/")
    public String main() {
        return "main";
    }

}

