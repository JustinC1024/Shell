package com.chm.service.controller.nom;

import com.chm.service.pojo.human.Clerk;
import com.chm.service.service.ClerkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nom_data")
public class NomDataController {

    @Autowired
    private ClerkService clerkService;

    /**
     * 个人信息
     * @param account
     * @return
     */
    @RequestMapping(value = "/clerk", method = RequestMethod.POST)
    public Clerk getClerk(String account) {
        if (account == null || "".equals(account)) {
            return null;
        }
        return clerkService.getClerkByAccount(account);
    }

    /**
     * 验证密码
     * @param account
     * @param password
     * @return
     */
    @RequestMapping(value = "/password", method = RequestMethod.POST)
    public int getClerkByPw(String account, String password) {
        if (account == null || password == null) {
            return 0;
        }
        Clerk clerk = clerkService.getClerkByAccount(account);
        String pw = clerk.getPassword();
        if (password.equals(pw)) {
            return 1;
        }
        return 0;
    }

    /**
     * 修改密码
     * @param pk
     * @param password
     * @return
     */
    @RequestMapping(value = "/update_password", method = RequestMethod.POST)
    public String updatePassword(String pk,String password) {
        if (pk == null || password == null) {
            return "传入数据不能为空";
        }
        int flag;
        Clerk clerk = new Clerk();
        clerk.setPkClerk(pk);
        clerk.setPassword(password);
        try {
            flag = clerkService.updateClerk(clerk);
        } catch (Exception e) {
            e.printStackTrace();
            return "修改失败";
        }
        if (flag == 0) {
            return "修改失败";
        }else {
            return "修改成功";
        }
    }

}
