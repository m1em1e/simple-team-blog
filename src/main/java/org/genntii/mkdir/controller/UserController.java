package org.genntii.mkdir.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 *
 *
 * @author mkdir
 * @since 2025/09/03 15:51
 */
@Controller
public class UserController {

    @GetMapping("/user/detail/{id}")
    public String userDetail(@PathVariable("id") Long id) {
        return "userDetail";
    }

}
