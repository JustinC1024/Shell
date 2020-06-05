package com.chm.customer.controller.customer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/customer")
public class CustomerViewController {

    @RequestMapping(value = "/personal/{account}", method = RequestMethod.GET)
    public String personal(@PathVariable("account") String account, Model model) {
        model.addAttribute("account", account);
        return "personal";
    }

    @RequestMapping("/sell")
    public String sell(){
        return "new_sell_detail";
    }

    @RequestMapping("/new_compartment/{pk}")
    public String newCompartment(@PathVariable("pk") String pk, Model model){
        model.addAttribute("pk", pk);
        return "new_compartment";
    }

    @RequestMapping("/compartment/{pk}")
    public String compartment(@PathVariable("pk") String pk, Model model){
        model.addAttribute("pk", pk);
        return "compartment";
    }

    @RequestMapping("/detail/{type}/{pk}")
    public String detail(@PathVariable("type") String type, @PathVariable("pk") String pk, Model model){
        model.addAttribute("pk", pk);
        if ("lease".equals(type)){
            return "new_lease_detail";
        }
        return "new_sell_detail";
    }

    @RequestMapping("/lease")
    public String lease(){
        return "new_lease_detail";
    }

}
