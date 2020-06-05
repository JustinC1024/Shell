package com.chm.service.controller.customer;

import com.chm.service.mapper.human.ClerkMapper;
import com.chm.service.pojo.building.*;
import com.chm.service.pojo.human.Clerk;
import com.chm.service.pojo.human.Customer;
import com.chm.service.pojo.record.Bill;
import com.chm.service.pojo.record.Identity;
import com.chm.service.pojo.record.Owner;
import com.chm.service.pojo.record.TempImg;
import com.chm.service.service.*;
import com.github.pagehelper.PageInfo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/customer_data")
public class CustomerDataController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private ClerkService clerkService;
    @Autowired
    private TempImgService tempImgService;
    @Autowired
    private BillService billService;
    @Autowired
    private RoomImgService roomImgService;
    @Autowired
    private SellRoomService sellRoomService;
    @Autowired
    private OwnerService ownerService;
    @Autowired
    private LeaseRoomService leaseRoomService;
    @Autowired
    private VillageService villageService;
    @Autowired
    private IdentityService identityService;

    /**
     * 个人信息页
     *
     * @param account
     * @return
     */
    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public Customer getCustomer(String account) {
        return customerService.getCustomerByAccount(account);
    }

    /**
     * 修改个人信息
     *
     * @param customer
     * @return
     */
    @RequestMapping(value = "/update_customer", method = RequestMethod.POST)
    public String updateCustomer(Customer customer) {
        int flag;
        try {
            flag = customerService.updateCustomer(customer);
        } catch (Exception e) {
            e.printStackTrace();
            return "修改失败";
        }
        if (flag == 0) {
            return "修改失败";
        } else {
            return "修改成功";
        }
    }

    /**
     * 验证密码
     *
     * @param account
     * @param password
     * @return
     */
    @RequestMapping(value = "/password", method = RequestMethod.POST)
    public int getCustomerByPw(String account, String password) {
        if (account == null || password == null) {
            return 0;
        }
        Customer customer = customerService.getCustomerByAccount(account);
        String pw = customer.getPassword();
        if (password.equals(pw)) {
            return 1;
        }
        return 0;
    }

    /**
     * 修改密码
     *
     * @param pk
     * @param password
     * @return
     */
    @RequestMapping(value = "/update_password", method = RequestMethod.POST)
    public String updatePassword(String pk, String password) {
        if (pk == null || password == null) {
            return "传入数据不能为空";
        }
        int flag;
        Customer customer = new Customer();
        customer.setPkCustomer(pk);
        customer.setPassword(password);
        try {
            flag = customerService.updateCustomer(customer);
        } catch (Exception e) {
            e.printStackTrace();
            return "修改失败";
        }
        if (flag == 0) {
            return "修改失败";
        } else {
            return "修改成功";
        }
    }

    /**
     * 所有业务员
     *
     * @return
     */
    @RequestMapping(value = "/clerk", method = RequestMethod.POST)
    public List<Clerk> getClerk() {
        return clerkService.getAllClerk();
    }

    /**
     * 获取头像
     *
     * @param pk
     * @return
     */
    @RequestMapping(value = "/img/{pk}", produces = MediaType.IMAGE_JPEG_VALUE, method = RequestMethod.GET)
    public byte[] getImg(@PathVariable("pk") String pk) {
        byte[] img = tempImgService.getTempImgByPk(pk).getImg();
        InputStream inputStream = new ByteArrayInputStream(img);
        try {
            inputStream.read(img, 0, inputStream.available());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    @RequestMapping(value = "/customerImg/{pk}", method = RequestMethod.GET)
    public TempImg getVillageImg(@PathVariable("pk") String pk) {
        return tempImgService.getTempImgByPk(pk);
    }


    /**
     * 下订
     *
     * @param pkClerk
     * @param pkCustomer
     * @param pkRoom
     * @param type
     * @return
     */
    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public int book(String pkClerk, String pkCustomer, String pkRoom, String type) {
        // 参数判断
        if (pkClerk == null || "".equals(pkClerk) || pkCustomer == null || "".equals(pkCustomer) ||
                pkRoom == null || "".equals(pkRoom) || type == null || "".equals(type)) {
            return 0;
        }
        Bill bill = new Bill();
        bill.setStatus(0);
        Clerk clerk = new Clerk();
        clerk.setPkClerk(pkClerk);
        bill.setPkClerk(clerk);
        Customer customer = customerService.getCustomerByAccount(pkCustomer);
        bill.setPkCustomer(customer);
        // 房屋类型
        if ("sell".equals(type)) {
            SellRoom sellRoom = new SellRoom();
            sellRoom.setPkSellRoom(pkRoom);
            bill.setPkSellRoom(sellRoom);
        } else if ("lease".equals(type)) {
            LeaseRoom leaseRoom = new LeaseRoom();
            leaseRoom.setPkLeaseRoom(pkRoom);
            bill.setPkLeaseRoom(leaseRoom);
        }
        try {
            return billService.doBill(bill);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 上传图片
     *
     * @param file
     * @return
     */
    @RequestMapping(value = "/do_img", method = RequestMethod.POST)
    public String doImg(@RequestParam("file") MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            byte[] pic = new byte[(int) file.getSize()];
            inputStream.read(pic);
            RoomImg roomImg = new RoomImg();
            roomImg.setImg(pic);
            return roomImgService.doRoomImg(roomImg);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 上传compartment IMG
     *
     * @param file
     * @return
     */
    @RequestMapping(value = "/do_compartment", method = RequestMethod.POST)
    public String doCompartment(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            byte[] pic = new byte[(int) file.getSize()];
            inputStream.read(pic);
            SellRoomCompartment sellRoomCompartment = new SellRoomCompartment();
            sellRoomCompartment.setImg(pic);
            return sellRoomService.doSellRoomCompartment(sellRoomCompartment);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 增sellRoom
     *
     * @param sellRoom
     * @return
     */
    @RequestMapping(value = "/do_sell_room", method = RequestMethod.POST)
    public String doSellRoom(@Valid SellRoom sellRoom) {
        // 参数判断
        if (sellRoom == null) {
            return null;
        }
        try {
            // 参数判断
            if (sellRoom.getPkVillage().getTitle() != null) {
                Village village = villageService.getVillageByTitle(sellRoom.getPkVillage().getTitle());
                // village是否存在
                if (village == null) {
                    Village v = new Village();
                    v.setTitle(sellRoom.getPkVillage().getTitle());
                    if (villageService.doVillage(v) == 1) {
                        village = villageService.getVillageByTitle(sellRoom.getPkVillage().getTitle());
                    }
                }
                sellRoom.setPkVillage(village);
            }
            return sellRoomService.doSellRoom(sellRoom);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 增SellCompartmentInfo
     *
     * @param sellRoom
     * @return
     */
    @RequestMapping(value = "/do_compartment_info", method = RequestMethod.POST)
    public int doCompartmentInfo(SellRoom sellRoom) {
        int flag = 0;
        // 参数判断
        if (sellRoom == null) {
            return flag;
        }
        try {
            flag = sellRoomService.doCompartmentInfo(sellRoom);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 改sellRoom
     *
     * @param sellRoom
     * @return
     */
    @RequestMapping(value = "/update_sell_room", method = RequestMethod.POST)
    public int updateSellRoom(SellRoom sellRoom) {
        int flag = 0;
        // 参数判断
        if (sellRoom == null) {
            return flag;
        }
        try {
            // 删除图片
            if (sellRoom.getDeleteImg() != null) {
                for (String s : sellRoom.getDeleteImg()) {
                    flag = roomImgService.deleteRoomImg(s);
                }
            }
            // 参数判断
            if (sellRoom.getPkVillage().getPkVillage() == null) {
                // 参数判断
                if (sellRoom.getPkVillage().getTitle() != null) {
                    Village village = villageService.getVillageByTitle(sellRoom.getPkVillage().getTitle());
                    // village是否存在
                    if (village == null) {
                        Village v = new Village();
                        v.setTitle(sellRoom.getPkVillage().getTitle());
                        if (villageService.doVillage(v) == 1) {
                            village = villageService.getVillageByTitle(sellRoom.getPkVillage().getTitle());
                        }
                    }
                    sellRoom.setPkVillage(village);
                }
            }
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            sellRoom.setUpdateTs(format.format(new Date().getTime()));
            flag = sellRoomService.updateSellRoom(sellRoom);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 改SellCompartmentInfo
     *
     * @param sellRoom
     * @return
     */
    @RequestMapping(value = "/update_compartment_info", method = RequestMethod.POST)
    public int updateCompartmentInfo(SellRoom sellRoom) {
        int flag = 0;
        // 参数判断
        if (sellRoom == null) {
            return flag;
        }
        try {
            // 删compartmentInfo
            if (sellRoom.getPkSellRoomCompartment().getDeleteCompartment() != null) {
                for (String s : sellRoom.getPkSellRoomCompartment().getDeleteCompartment()) {
                    flag = sellRoomService.deleteSellRoomCompartmentInfo(s);
                }
            }
            flag = sellRoomService.updateCompartmentInfo(sellRoom);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删sellRoom
     *
     * @param pk
     * @return
     */
    @RequestMapping(value = "/delete_sell_room", method = RequestMethod.POST)
    public int deleteSellRoom(String pk) {
        int flag = 0;
        // 参数判断
        if (pk == null || "".equals(pk)) {
            return flag;
        }
        try {
            flag = sellRoomService.deleteSellRoom(pk);
            Owner owner = new Owner();
            SellRoom sellRoom = new SellRoom();
            sellRoom.setPkSellRoom(pk);
            owner.setPkSellRoom(sellRoom);
            flag = ownerService.deleteOwnerByRoom(owner);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 订单
     *
     * @param account
     * @param nowPage
     * @return
     */
    @RequestMapping(value = "/bill", method = RequestMethod.POST)
    public List<Bill> getBill(String account, String nowPage) {
        // 参数判断
        if (nowPage == null || "".equals(nowPage) || account == null || "".equals(account)) {
            return null;
        }
        Bill bill = new Bill();
        Customer customer = new Customer();
        customer.setAccount(account);
        bill.setPkCustomer(customer);
        return billService.getListBill(bill, Integer.parseInt(nowPage));
    }

    /**
     * 订单页数
     *
     * @param account
     * @return
     */
    @RequestMapping(value = "/bill_page", method = RequestMethod.POST)
    public int getBill(String account) {
        // 参数判断
        if (account == null || "".equals(account)) {
            return 0;
        }
        Bill bill = new Bill();
        Customer customer = new Customer();
        customer.setAccount(account);
        bill.setPkCustomer(customer);
        PageInfo pageInfo = billService.getListBillPage(bill);
        return pageInfo.getPages();
    }

    /**
     * 信息
     *
     * @param account
     * @param nowPage
     * @return
     */
    @RequestMapping(value = "/owner", method = RequestMethod.POST)
    public List<Owner> getOwner(String account, String nowPage) {
        // 参数判断
        if (nowPage == null || "".equals(nowPage) || account == null || "".equals(account)) {
            return null;
        }
        Owner owner = new Owner();
        Customer customer = new Customer();
        customer.setAccount(account);
        owner.setPkCustomer(customer);
        return ownerService.getListOwner(owner, Integer.parseInt(nowPage));
    }

    /**
     * 信息页数
     *
     * @param account
     * @return
     */
    @RequestMapping(value = "/owner_page", method = RequestMethod.POST)
    public int getOwner(String account) {
        // 参数判断
        if (account == null || "".equals(account)) {
            return 0;
        }
        Owner owner = new Owner();
        Customer customer = new Customer();
        customer.setAccount(account);
        owner.setPkCustomer(customer);
        PageInfo pageInfo = ownerService.getListOwnerPage(owner);
        return pageInfo.getPages();
    }

    /**
     * 改leaseRoom
     *
     * @param leaseRoom
     * @return
     */
    @RequestMapping(value = "/update_lease_room", method = RequestMethod.POST)
    public int updateLeaseRoom(LeaseRoom leaseRoom) {
        int flag = 0;
        // 参数判断
        if (leaseRoom == null) {
            return flag;
        }
        try {
            // 删除图片
            if (leaseRoom.getDeleteImg() != null) {
                for (String s : leaseRoom.getDeleteImg()) {
                    flag = roomImgService.deleteRoomImg(s);
                }
            }
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            leaseRoom.setUpdateTs(format.format(new Date().getTime()));
            flag = leaseRoomService.updateLeaseRoom(leaseRoom);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 增leaseRoom
     *
     * @param leaseRoom
     * @return
     */
    @RequestMapping(value = "/do_lease_room", method = RequestMethod.POST)
    public String doLeaseRoom(@Valid LeaseRoom leaseRoom) {
        int flag = 0;
        // 参数判断
        if (leaseRoom == null) {
            return null;
        }
        try {
            return leaseRoomService.doLeaseRoom(leaseRoom);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删leaseRoom
     *
     * @param pk
     * @return
     */
    @RequestMapping(value = "/delete_lease_room", method = RequestMethod.POST)
    public int deleteLeaseRoom(String pk) {
        int flag = 0;
        // 参数判断
        if (pk == null || "".equals(pk)) {
            return flag;
        }
        try {
            flag = leaseRoomService.deleteLeaseRoom(pk);
            Owner owner = new Owner();
            LeaseRoom leaseRoom = new LeaseRoom();
            leaseRoom.setPkLeaseRoom(pk);
            owner.setPkLeaseRoom(leaseRoom);
            flag = ownerService.deleteOwnerByRoom(owner);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 信息
     *
     * @param cus
     * @param room
     * @param type
     * @return
     */
    @RequestMapping(value = "/do_owner", method = RequestMethod.POST)
    public int doOwner(String cus, String room, String type) {
        int flag = 0;
        if (cus == null || "".equals(cus) || room == null || "".equals(room) || type == null || "".equals(type)) {
            return flag;
        }
        Owner owner = new Owner();
        Customer customer = customerService.getCustomerByAccount(cus);
        owner.setPkCustomer(customer);
        SellRoom sellRoom = new SellRoom();
        LeaseRoom leaseRoom = new LeaseRoom();
        if ("sell".equals(type)) {
            sellRoom.setPkSellRoom(room);
        }
        if ("lease".equals(type)) {
            leaseRoom.setPkLeaseRoom(room);
        }
        owner.setPkLeaseRoom(leaseRoom);
        owner.setPkSellRoom(sellRoom);
        try {
            flag = ownerService.doOwner(owner);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 标题是否存在
     *
     * @param title
     * @return
     */
    @RequestMapping(value = "/lease_title", method = RequestMethod.POST)
    public int getLeaseByTitle(String title) {
        // 参数判断
        if (title == null || "".equals(title)) return 0;
        // 是否存在
        if (leaseRoomService.getLeaseRoomByTitle(title) == null) return 0;
        else return 1;
    }

    /**
     * 标题是否存在
     *
     * @param title
     * @return
     */
    @RequestMapping(value = "/sell_title", method = RequestMethod.POST)
    public int getSellByTitle(String title) {
        // 参数判断
        if (title == null || "".equals(title)) return 0;
        // 是否存在
        if (sellRoomService.getSellRoomByTitle(title) == null) return 0;
        else return 1;
    }

    /**
     * 认证
     *
     * @param account
     * @return
     */
    @RequestMapping(value = "/identity", method = RequestMethod.POST)
    public Identity getIdentity(String account) {
        if (account == null) {
            return null;
        }
        return identityService.getIdentityByCustomer(account);
    }

}
