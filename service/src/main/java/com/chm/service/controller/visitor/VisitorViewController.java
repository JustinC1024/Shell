package com.chm.service.controller.visitor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/visitor")
public class VisitorViewController {

    @RequestMapping("/index")
    public String index(){
        return "cus/index";
    }

    @RequestMapping("/lease")
    public String lease(){
        return "cus/lease";
    }

    @RequestMapping("/village")
    public String village(){
        return "cus/village";
    }

    @RequestMapping("/sellDetail/{pk}")
    public String sellDetail(@PathVariable("pk") String pk, Model model){
        model.addAttribute("pk", pk);
        return "cus/sell_detail";
    }

    @RequestMapping("/leaseDetail/{pk}")
    public String leaseDetail(@PathVariable("pk") String pk, Model model) {
        model.addAttribute("pk", pk);
        return "cus/lease_detail";
    }

    @RequestMapping("/villageDetail/{pk}")
    public String villageDetail(@PathVariable("pk") String pk, Model model) {
        model.addAttribute("pk", pk);
        return "cus/village_detail";
    }

    @RequestMapping("/register")
    public String register(){
        return "cus/register";
    }

}
