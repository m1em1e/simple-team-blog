package org.genntii.mkdir.controller;

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
@Controller("/manager")
public class PersonalController {

    @GetMapping("/{id}")
    public String message(@PathVariable("id") Long id) {
        return "manager";
    }

}
