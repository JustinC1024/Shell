package com.chm.service.controller.nom;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/nom")
@Controller
public class NomViewController {

    @RequestMapping(value = "/login")
    public String login(){
        return "clerk_login";
    }

    @RequestMapping(value = "/personal/{account}", method = RequestMethod.GET)
    public String getPersonal(@PathVariable("account") String account, Model model){
        model.addAttribute("account", account);
        return "personal";
    }

}
