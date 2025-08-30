package org.genntii.mkdir.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 *
 *
 * @author mkdir
 * @since 2025/08/29 16:37
 */
@Controller
public class DetailController {

    @GetMapping("/detail/{id}")
    public String getDetail(@PathVariable("id") Long id) {
        return "detail";
    }

}
