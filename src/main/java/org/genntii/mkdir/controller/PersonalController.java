package org.genntii.mkdir.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 *
 *
 * @author mkdir
 * @since 2025/08/27 11:00
 */
@Slf4j
@Controller
public class PersonalController {

    @GetMapping("/manager/{id}")
    public String message(@PathVariable("id") Long id) {
        log.info("访问manager页面 对象用户ID：{}",id);
        return "manager"; // 对应 manager.html
    }


}
