package org.genntii.mkdir.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 *
 *
 * @author mkdir
 * @since 2025/09/02 10:31
 */
@Controller
public class UploadController {

    @GetMapping("/upload")
    public String upload() {
        return "upload";
    }

}
