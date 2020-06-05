package com.chm.service.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin")
@Controller
public class AdminViewController {

    @RequestMapping("/bill")
    public String getBill(){
        return "bill";
    }

    @RequestMapping("/info")
    public String getInfo(){
        return "info";
    }

    @RequestMapping("/user")
    public String getUser(){
        return "user";
    }

    @RequestMapping("/detail/{type}/{pk}/{status}")
    public String detail(@PathVariable("type") String type, @PathVariable("pk") String pk,
                         @PathVariable("status") String status, Model model){
        model.addAttribute("pk", pk);
        model.addAttribute("s", status);
        if ("lease".equals(type)){
            return "lease_detail";
        }
        return "sell_detail";
    }

    @RequestMapping("/village")
    public String village(){
        return "village";
    }

    @RequestMapping("/villageDetail/{pk}")
    public String villageDetail(@PathVariable("pk") String pk, Model model) {
        model.addAttribute("pk", pk);
        return "village_detail";
    }

    @RequestMapping("/newVillageDetail")
    public String villageDetail() {
        return "new_village_detail";
    }

}
