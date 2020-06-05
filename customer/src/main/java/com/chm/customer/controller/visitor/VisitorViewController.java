package com.chm.customer.controller.visitor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/visitor")
public class VisitorViewController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/lease")
    public String lease(){
        return "lease";
    }

    @RequestMapping("/village")
    public String village(){
        return "village";
    }

    @RequestMapping("/sellDetail/{pk}")
    public String sellDetail(@PathVariable("pk") String pk, Model model){
        model.addAttribute("pk", pk);
        return "sell_detail";
    }

    @RequestMapping("/leaseDetail/{pk}")
    public String leaseDetail(@PathVariable("pk") String pk, Model model) {
        model.addAttribute("pk", pk);
        return "lease_detail";
    }

    @RequestMapping("/villageDetail/{pk}")
    public String villageDetail(@PathVariable("pk") String pk, Model model) {
        model.addAttribute("pk", pk);
        return "village_detail";
    }

    @RequestMapping("/register")
    public String register(){
        return "register";
    }

}
