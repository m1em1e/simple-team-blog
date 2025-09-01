package org.genntii.mkdir.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 登录页面控制器
 * 处理用户登录相关页面的路由请求
 *
 * @author mkdir
 * @since 2025/08/26 10:25
 */
@Controller
public class LoginController {

    /**
     * 登录页面路由
     * 处理"/login"路径的GET请求，返回登录页面视图
     *
     * @return 返回"login"视图名称，对应templates/login.html模板
     */
    @GetMapping("/login")
    public String loginPage(@RequestParam("returnUrl") String returnUrl) {
        return "login";
    }

}

