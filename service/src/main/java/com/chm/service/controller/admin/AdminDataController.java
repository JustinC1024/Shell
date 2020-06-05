package com.chm.service.controller.admin;

import com.chm.service.pojo.building.LeaseRoom;
import com.chm.service.pojo.building.SellRoom;
import com.chm.service.pojo.building.Village;
import com.chm.service.pojo.building.VillageImg;
import com.chm.service.pojo.human.Clerk;
import com.chm.service.pojo.human.Customer;
import com.chm.service.pojo.human.Jurisdiction;
import com.chm.service.pojo.record.Bill;
import com.chm.service.pojo.record.Identity;
import com.chm.service.pojo.record.Owner;
import com.chm.service.service.*;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/admin_data")
public class AdminDataController {

    @Autowired
    private BillService billService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ClerkService clerkService;
    @Autowired
    private IdentityService identityService;
    @Autowired
    private OwnerService ownerService;
    @Autowired
    private JurisdictionService jurisdictionService;
    @Autowired
    private VillageService villageService;
    @Autowired
    private VillageImgService villageImgService;
    @Autowired
    private SellRoomService sellRoomService;
    @Autowired
    private LeaseRoomService leaseRoomService;

    /**
     * 用户管理页面
     *
     * @param ts
     * @param nowPage
     * @return
     */
    @RequestMapping(value = "/identity", method = RequestMethod.POST)
    public List<Identity> getIdentity(String ts, String nowPage) {
        Identity identity = new Identity();
        identity.setTs(ts);
        return identityService.getListIdentity(identity, Integer.parseInt(nowPage));
    }

    /**
     * 用户管理页面页数
     *
     * @param ts
     * @return
     */
    @RequestMapping(value = "/identity_page", method = RequestMethod.POST)
    public int getIdentity(String ts) {
        Identity identity = new Identity();
        identity.setTs(ts);
        PageInfo pageInfo = identityService.getListidentityPage(identity);
        return pageInfo.getPages();
    }

    /**
     * 用户详情
     *
     * @param pk
     * @return
     */
    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public Customer getCustomer(String pk) {
        // 参数判断
        if (pk != null && "".equals(pk)) {
            return null;
        }
        return customerService.getCustomerByPk(pk);
    }

    /**
     * 改用户认证
     *
     * @param pkCustomer
     * @param clerkName
     * @return
     */
    @RequestMapping(value = "/update_identity", method = RequestMethod.POST)
    public int updateIdentity(String pkCustomer, String clerkName) {
        int flag = 0;
        // 参数判断
        if (pkCustomer == null || "".equals(pkCustomer) || clerkName == null
                || "".equals(clerkName)) {
            return flag;
        }
        Identity identity = new Identity();
        Customer customer = new Customer();
        customer.setPkCustomer(pkCustomer);
        identity.setPkCustomer(customer);
        Clerk clerk = clerkService.getClerkByAccount(clerkName);
        identity.setPkClerk(clerk);
        identity.setStatus(1);
        try {
            flag = identityService.updateIdentity(identity);
        } catch (Exception e) {
            e.printStackTrace();
            return flag;
        }
        return flag;
    }

    /**
     * 员工列表
     *
     * @param ts
     * @param pk
     * @param nowPage
     * @return
     */
    @RequestMapping(value = "/clerk", method = RequestMethod.POST)
    public List<Clerk> getClerk(String ts, String pk, String nowPage) {
        // 条件判断
        if (pk == null || "".equals(pk) || nowPage == null || "".equals(nowPage)) {
            return null;
        }
        Clerk clerk = new Clerk();
        clerk.setPkClerk(pk);
        clerk.setTs(ts);
        return clerkService.getListClerk(clerk, Integer.parseInt(nowPage));
    }

    /**
     * 员工列表页数
     *
     * @param ts
     * @param pk
     * @return
     */
    @RequestMapping(value = "/clerk_page", method = RequestMethod.POST)
    public int getClerk(String ts, String pk) {
        // 条件判断
        if (pk == null || "".equals(pk)) {
            return 0;
        }
        Clerk clerk = new Clerk();
        clerk.setPkClerk(pk);
        clerk.setTs(ts);
        PageInfo pageInfo = clerkService.getListClerkPage(clerk);
        return pageInfo.getPages();
    }

    /**
     * 新增员工
     *
     * @param account
     * @param password
     * @param jur
     * @return
     */
    @RequestMapping(value = "/do_clerk", method = RequestMethod.POST)
    public int doClerk(String account, String password, String jur) {
        int flag = 0;
        Clerk clerk = new Clerk();
        Jurisdiction jurisdiction = new Jurisdiction();
        jurisdiction.setPkJurisdiction(jur);
        clerk.setAccount(account);
        clerk.setPassword(password);
        clerk.setPkJurisdiction(jurisdiction);
        try {
            flag = clerkService.doClerk(clerk);
        } catch (Exception e) {
            e.printStackTrace();
            return flag;
        }
        return flag;
    }

    /**
     * 删除员工
     *
     * @param pk
     * @return
     */
    @RequestMapping(value = "/delete_clerk", method = RequestMethod.POST)
    public int deleteClerk(String pk) {
        int flag = 0;
        try {
            flag = clerkService.deleteClerk(pk);
        } catch (Exception e) {
            e.printStackTrace();
            return flag;
        }
        return flag;
    }

    /**
     * 订单
     *
     * @param ts
     * @param nowPage
     * @param request
     * @return
     */
    @RequestMapping(value = "/bill", method = RequestMethod.POST)
    public List<Bill> getBill(String ts, String nowPage, HttpServletRequest request) {
        // 参数判断
        if (nowPage != null && "".equals(nowPage)) {
            return null;
        }
        Bill bill = new Bill();
        bill.setTs(ts);
        return billService.getListBill(bill, Integer.parseInt(nowPage));
    }

    /**
     * 订单页面
     *
     * @param ts
     * @return
     */
    @RequestMapping(value = "/bill_page", method = RequestMethod.POST)
    public int getBill(String ts) {
        Bill bill = new Bill();
        bill.setTs(ts);
        PageInfo pageInfo = billService.getListBillPage(bill);
        return pageInfo.getPages();
    }

    /**
     * 信息
     *
     * @param ts
     * @param nowPage
     * @return
     */
    @RequestMapping(value = "/owner", method = RequestMethod.POST)
    public List<Owner> getOwner(String ts, String nowPage) {
        Owner owner = new Owner();
        owner.setTs(ts);
        return ownerService.getListOwner(owner, Integer.parseInt(nowPage));
    }

    /**
     * 信息页数
     *
     * @param ts
     * @return
     */
    @RequestMapping(value = "/owner_page", method = RequestMethod.POST)
    public int getOwner(String ts) {
        Owner owner = new Owner();
        owner.setTs(ts);
        PageInfo pageInfo = ownerService.getListOwnerPage(owner);
        return pageInfo.getPages();
    }

    /**
     * 角色
     *
     * @return
     */
    @RequestMapping(value = "/jurisdiction", method = RequestMethod.POST)
    public List<Jurisdiction> getJurisdiction() {
        return jurisdictionService.getListJurisdiction();
    }

    /**
     * 信息认证
     *
     * @param pk
     * @param status
     * @param clerkName
     * @param style
     * @return
     */
    @RequestMapping(value = "/update_owner", method = RequestMethod.POST)
    public int updateOwner(String pk, String status, String clerkName, String style) {
        // 参数判断
        if (pk == null || "".equals(pk) || status == null || "".equals(status) || clerkName == null
                || "".equals(clerkName) || style == null || "".equals(style)) {
            return 0;
        }
        int flag = 0;
        Owner owner = ownerService.getOwnerByRoom(pk, style);
        owner.setStatus(Integer.parseInt(status));
        owner.setPkClerk(clerkService.getClerkByAccount(clerkName));
        try {
            flag = ownerService.updateOwner(owner);
            if ("sell".equals(style)) {
                SellRoom sellRoom = new SellRoom();
                sellRoom.setPkSellRoom(pk);
                sellRoom.setStatus(Integer.parseInt(status));
                flag = sellRoomService.updateSellRoomStatus(sellRoom);
            }
            if ("lease".equals(style)) {
                LeaseRoom leaseRoom = new LeaseRoom();
                leaseRoom.setPkLeaseRoom(pk);
                leaseRoom.setStatus(Integer.parseInt(status));
                flag = leaseRoomService.updateLeaseRoomStatus(leaseRoom);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return flag;
        }
        return flag;
    }

    /**
     * 账号是否存在
     *
     * @param account
     * @return
     */
    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public int getAccount(String account) {
        // 参数判断
        if (account == null || "".equals(account)) return 0;
        // 是否存在
        if (clerkService.getClerkByAccount(account) == null) return 0;
        else return 1;
    }

    /**
     * 上传小区图
     *
     * @param file
     * @return
     */
    @RequestMapping(value = "/upload_img", method = RequestMethod.POST)
    public String uploadVillageImg(MultipartFile file) {
        try {
            MultipartFile f = file;
            InputStream inputStream = file.getInputStream();
            byte[] pic = new byte[(int) file.getSize()];
            inputStream.read(pic);
            VillageImg villageImg = new VillageImg();
            villageImg.setImg(pic);
            return villageImgService.doVillageImg(villageImg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 加小区
     *
     * @param village
     * @return
     */
    @RequestMapping(value = "/do_village", method = RequestMethod.POST)
    public int doVillage(@Valid Village village) {
        int flag = 0;
        if (village != null) {
            try {
                flag = villageService.doVillage(village);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    /**
     * 改小区
     *
     * @param village
     * @return
     */
    @RequestMapping(value = "/update_village", method = RequestMethod.POST)
    public int updateVillage(Village village) {
        int flag = 0;
        if (village != null) {
            try {
                // 删除图片
                if (village.getDeleteImg() != null) {
                    for (String s : village.getDeleteImg()) {
                        flag = villageImgService.deleteVillageImg(s);
                    }
                }
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                village.setUpdateTs(format.format(new Date().getTime()));
                flag = villageService.updateVillage(village);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    /**
     * 删小区
     *
     * @param pk
     * @return
     */
    @RequestMapping(value = "/delete_village", method = RequestMethod.POST)
    public int deleteVillage(String pk) {
        int flag = 0;
        if (pk != null || "".equals(pk)) {
            try {
                flag = villageService.deleteVillage(pk);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    /**
     * 标题是否存在
     *
     * @param title
     * @return
     */
    @RequestMapping(value = "/village_title", method = RequestMethod.POST)
    public int getSellByTitle(String title) {
        // 参数判断
        if (title == null || "".equals(title)) return 0;
        // 是否存在
        if (villageService.getVillageByTitle(title) == null) return 0;
        else return 1;
    }

}
