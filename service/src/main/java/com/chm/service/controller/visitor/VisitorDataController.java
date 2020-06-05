package com.chm.service.controller.visitor;

import com.chm.service.pojo.building.*;
import com.chm.service.pojo.human.Customer;
import com.chm.service.pojo.record.Identity;
import com.chm.service.service.*;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/visitor_data")
public class VisitorDataController {

    @Autowired
    private SellRoomService sellRoomService;
    @Autowired
    private RoomImgService roomImgService;
    @Autowired
    private LeaseRoomService leaseRoomService;
    @Autowired
    private VillageService villageService;
    @Autowired
    private VillageImgService villageImgService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private IdentityService identityService;

    /**
     * 二手房列表
     *
     * @param county
     * @param title
     * @param price
     * @param ts
     * @param nowPage
     * @return
     */
    @RequestMapping(value = "/sell", method = RequestMethod.POST)
    public List<SellRoom> getListSell(String county, String title, String price, String ts, String nowPage) {
        SellRoom sellRoom = new SellRoom();
        sellRoom.setPkSellPrice(new SellPrice());
        // 区为空判断
        if (county != null && !"".equals(county)) {
            sellRoom.setCounty(county);
        }
        // 标题为空判断
        if (title != null && !"".equals(title)) {
            sellRoom.setTitle("%" + title + "%");
        }
        // 价格为空判断
        if (price != null && !"".equals(price)) {
            switch (price) {
                case "30":
                    sellRoom.getPkSellPrice().setTotalPrice(1);
                    break;
                case "28":
                    sellRoom.getPkSellPrice().setTotalPrice(0);
                    break;
                default:
                    break;
            }
        }
        // 时间为空判断
        if (ts != null && !"".equals(ts)) {
            switch (ts) {
                case "30":
                    sellRoom.setUpdateTs("asc");
                    break;
                case "28":
                    sellRoom.setUpdateTs("desc");
                    break;
                default:
                    break;
            }
        }
        return sellRoomService.getListSellRoom(sellRoom, Integer.parseInt(nowPage));
    }

    /**
     * 二手房列表页数
     *
     * @param county
     * @param title
     * @param price
     * @param ts
     * @return
     */
    @RequestMapping(value = "/sell_page", method = RequestMethod.POST)
    public int getListSell(String county, String title, String price, String ts) {
        SellRoom sellRoom = new SellRoom();
        sellRoom.setPkSellPrice(new SellPrice());
        // 区为空判断
        if (county != null && !"".equals(county)) {
            sellRoom.setCounty(county);
        }
        // 标题为空判断
        if (title != null && !"".equals(title)) {
            sellRoom.setTitle("%" + title + "%");
        }
        // 价格为空判断
        if (price != null && !"".equals(price)) {
            switch (price) {
                case "30":
                    sellRoom.getPkSellPrice().setTotalPrice(1);
                    break;
                case "28":
                    sellRoom.getPkSellPrice().setTotalPrice(0);
                    break;
                default:
                    break;
            }
        }
        // 时间为空判断
        if (ts != null && !"".equals(ts)) {
            switch (ts) {
                case "30":
                    sellRoom.setUpdateTs("asc");
                    break;
                case "28":
                    sellRoom.setUpdateTs("desc");
                    break;
                default:
                    break;
            }
        }
        PageInfo pageInfo = sellRoomService.getListSellRoomPage(sellRoom);
        return pageInfo.getPages();
    }

    /**
     * 二手房相片
     *
     * @param pk
     * @return
     */
    @RequestMapping(value = "/room_img/{pk}", produces = MediaType.IMAGE_JPEG_VALUE, method = RequestMethod.GET)
    public byte[] getRoomImage(@PathVariable("pk") String pk) {
        byte[] img = roomImgService.getImgInRoomImgByPk(pk).getImg();
        InputStream inputStream = new ByteArrayInputStream(img);
        try {
            inputStream.read(img, 0, inputStream.available());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    @RequestMapping(value = "/roomImg/{pk}", method = RequestMethod.GET)
    public RoomImg getRoomImg(@PathVariable("pk") String pk) {
        return roomImgService.getImgInRoomImgByPk(pk);
    }

    /**
     * 租房列表
     *
     * @param county
     * @param title
     * @param price
     * @param ts
     * @param nowPage
     * @return
     */
    @RequestMapping(value = "/lease", method = RequestMethod.POST)
    public List<LeaseRoom> getListLease(String county, String title, String price, String ts, String nowPage) {
        LeaseRoom leaseRoom = new LeaseRoom();
        leaseRoom.setPkLeasePrice(new LeasePrice());
        // 区为空判断
        if (county != null && !"".equals(county)) {
            leaseRoom.setCounty(county);
        }
        // 标题为空判断
        if (title != null && !"".equals(title)) {
            leaseRoom.setTitle("%" + title + "%");
        }
        // 价格为空判断
        if (price != null && !"".equals(price)) {
            switch (price) {
                case "30":
                    leaseRoom.getPkLeasePrice().setRent(1);
                    break;
                case "28":
                    leaseRoom.getPkLeasePrice().setRent(0);
                    break;
                default:
                    break;
            }
        }
        // 时间为空判断
        if (ts != null && !"".equals(ts)) {
            switch (ts) {
                case "30":
                    leaseRoom.setUpdateTs("asc");
                    break;
                case "28":
                    leaseRoom.setUpdateTs("desc");
                    break;
                default:
                    break;
            }
        }
        return leaseRoomService.getListLeaseRoom(leaseRoom, Integer.parseInt(nowPage));
    }

    /**
     * 租房列表页数
     *
     * @param county
     * @param title
     * @param price
     * @param ts
     * @return
     */
    @RequestMapping(value = "/lease_page", method = RequestMethod.POST)
    public int getListLease(String county, String title, String price, String ts) {
        LeaseRoom leaseRoom = new LeaseRoom();
        leaseRoom.setPkLeasePrice(new LeasePrice());
        // 区为空判断
        if (county != null && !"".equals(county)) {
            leaseRoom.setCounty(county);
        }
        // 标题为空判断
        if (title != null && !"".equals(title)) {
            leaseRoom.setTitle("%" + title + "%");
        }
        // 价格为空判断
        if (price != null && !"".equals(price)) {
            switch (price) {
                case "30":
                    leaseRoom.getPkLeasePrice().setRent(1);
                    break;
                case "28":
                    leaseRoom.getPkLeasePrice().setRent(0);
                    break;
                default:
                    break;
            }
        }
        // 时间为空判断
        if (ts != null && !"".equals(ts)) {
            switch (ts) {
                case "30":
                    leaseRoom.setUpdateTs("asc");
                    break;
                case "28":
                    leaseRoom.setUpdateTs("desc");
                    break;
                default:
                    break;
            }
        }
        PageInfo pageInfo = leaseRoomService.getListLeaseRoomPage(leaseRoom);
        return pageInfo.getPages();
    }

    /**
     * 小区列表
     *
     * @param county
     * @param title
     * @param price
     * @param ts
     * @param nowPage
     * @return
     */
    @RequestMapping(value = "/village", method = RequestMethod.POST)
    public List<Village> getListVillage(String county, String title, String price, String ts, String nowPage) {
        Village village = new Village();
        // 区为空判断
        if (county != null && !"".equals(county)) {
            village.setCounty(county);
        }
        // 标题为空判断
        if (title != null && !"".equals(title)) {
            village.setTitle("%" + title + "%");
        }
        // 价格为空判断
        if (price != null && !"".equals(price)) {
            switch (price) {
                case "30":
                    village.setConsult(1);
                    break;
                case "28":
                    village.setConsult(0);
                    break;
                default:
                    break;
            }
        }
        return villageService.getListVillage(village, Integer.parseInt(nowPage));
    }

    /**
     * 小区列表页数
     *
     * @param county
     * @param title
     * @param price
     * @param ts
     * @return
     */
    @RequestMapping(value = "/village_page", method = RequestMethod.POST)
    public int getListVillage(String county, String title, String price, String ts) {
        Village village = new Village();
        // 区为空判断
        if (county != null && !"".equals(county)) {
            village.setCounty(county);
        }
        // 标题为空判断
        if (title != null && !"".equals(title)) {
            village.setTitle("%" + title + "%");
        }
        // 价格为空判断
        if (price != null && !"".equals(price)) {
            switch (price) {
                case "30":
                    village.setConsult(1);
                    break;
                case "28":
                    village.setConsult(0);
                    break;
                default:
                    break;
            }
        }
        PageInfo pageInfo = villageService.getListVillagePage(village);
        return pageInfo.getPages();
    }

    /**
     * 小区相片
     *
     * @param pk
     * @return
     */
    @RequestMapping(value = "/village_img/{pk}", produces = MediaType.IMAGE_JPEG_VALUE, method = RequestMethod.GET)
    public byte[] getVillageImage(@PathVariable("pk") String pk) {
        byte[] img = villageImgService.getImgVillageImgByPk(pk).getImg();
        InputStream inputStream = new ByteArrayInputStream(img);
        try {
            inputStream.read(img, 0, inputStream.available());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    @RequestMapping(value = "/villageImg/{pk}", method = RequestMethod.GET)
    public VillageImg getVillageImg(@PathVariable("pk") String pk) {
        return villageImgService.getImgVillageImgByPk(pk);
    }

    /**
     * 单个二手房
     *
     * @param pk
     * @return
     */
    @RequestMapping(value = "/sell_detail", method = RequestMethod.POST)
    public SellRoom getDetailSell(String pk) {
        return sellRoomService.getSellRoomByPk(pk);
    }

    /**
     * 户型相片
     *
     * @param pk
     * @return
     */
    @RequestMapping(value = "/compartment_img/{pk}", produces = MediaType.IMAGE_JPEG_VALUE, method = RequestMethod.GET)
    public byte[] getCompartmentImage(@PathVariable("pk") String pk) {
        byte[] img = sellRoomService.getImgSellRoomCompartmentByPk(pk).getImg();
        InputStream inputStream = new ByteArrayInputStream(img);
        try {
            inputStream.read(img, 0, inputStream.available());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    @RequestMapping(value = "/compartmentImg/{pk}", method = RequestMethod.GET)
    public SellRoomCompartment getSellRoomCompartment(@PathVariable("pk") String pk) {
        return sellRoomService.getImgSellRoomCompartmentByPk(pk);
    }

    /**
     * 单个租房
     *
     * @param pk
     * @return
     */
    @RequestMapping(value = "/lease_detail", method = RequestMethod.POST)
    public LeaseRoom getDetailLease(String pk) {
        return leaseRoomService.getLeaseRoomByPk(pk);
    }

    /**
     * 单个小区
     *
     * @param pk
     * @return
     */
    @RequestMapping(value = "/village_detail", method = RequestMethod.POST)
    public Village getDetailVillage(String pk) {
        return villageService.getVillageByPk(pk);
    }

    /**
     * 注册
     *
     * @param customer
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public int doCustomer(@Valid Customer customer) {
        int flag = 0;
        if (customer.getAccount() == null) {
            return flag;
        }
        Identity identity = new Identity();
        identity.setPkCustomer(customer);
        try {
            flag = customerService.doCustomer(customer);
            flag = identityService.doIdentity(identity);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return flag;
    }

    /**
     * 查密码
     *
     * @param account
     * @return
     */
    @RequestMapping(value = "/login_customer", method = RequestMethod.POST)
    public Customer getCustomerByAccount(String account) {
        if (account == null || "".equals(account)) {
            return null;
        }
        return customerService.getCustomerByAccount(account);
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
        if (customerService.getCustomerByAccount(account) == null) return 0;
        else return 1;
    }

}
