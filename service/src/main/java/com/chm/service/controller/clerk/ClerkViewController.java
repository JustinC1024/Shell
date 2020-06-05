package com.chm.service.controller.clerk;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clerk")
public class ClerkViewController {

    @RequestMapping(value = "/bill")
    public String getBill() {
        return "approver";
    }

}
