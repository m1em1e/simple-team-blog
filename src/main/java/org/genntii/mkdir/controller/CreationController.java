package org.genntii.mkdir.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 *
 *
 * @author mkdir
 * @since 2025/08/27 10:56
 */
@Controller("/creation")
public class CreationController {

    @GetMapping("/editor")
    public String editor() {
        return "editor";
    }

    @GetMapping("/manage")
    public String manage() {
        return "manage";
    }


}
