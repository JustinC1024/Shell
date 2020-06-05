package com.chm.customer.controller.visitor;

import com.chm.customer.pojo.building.*;
import com.chm.customer.pojo.human.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/visitor_data")
public class VisitorDataController {

    @Autowired
    private RestTemplate restTemplate;

    String url = "http://PROVIDER/visitor_data/";

    /**
     * 二手房列表
     * @param county
     * @param title
     * @param price
     * @param ts
     * @param nowPage
     * @return
     */
    @RequestMapping(value = "/sell", method = RequestMethod.POST)
    public List<SellRoom> getListSell(String county, String title, String price, String ts, String nowPage) {
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
        param.add("county", county);
        param.add("title", title);
        param.add("price", price);
        param.add("ts", ts);
        param.add("nowPage", nowPage);
        return (List<SellRoom>) restTemplate.postForObject(url + "sell", param, Object.class);
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
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
        param.add("county", county);
        param.add("title", title);
        param.add("price", price);
        param.add("ts", ts);
        return restTemplate.postForObject(url + "sell_page", param, int.class);
    }

    /**
     * 二手房相片
     * @param pk
     * @return
     */
    @RequestMapping(value = "/room_img/{pk}", produces = MediaType.IMAGE_JPEG_VALUE, method = RequestMethod.GET)
    public byte[] getRoomImage(@PathVariable("pk") String pk) {
        RoomImg roomImg = restTemplate.getForObject(url + "roomImg/" + pk, RoomImg.class);
        byte[] img = roomImg.getImg();
        InputStream inputStream = new ByteArrayInputStream(img);
        try {
            inputStream.read(img, 0, inputStream.available());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    /**
     * 租房列表
     * @param county
     * @param title
     * @param price
     * @param ts
     * @param nowPage
     * @return
     */
    @RequestMapping(value = "/lease", method = RequestMethod.POST)
    public List<LeaseRoom> getListLease(String county, String title, String price, String ts, String nowPage) {
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
        param.add("county", county);
        param.add("title", title);
        param.add("price", price);
        param.add("ts", ts);
        param.add("nowPage", nowPage);
        return (List<LeaseRoom>) restTemplate.postForObject(url + "lease", param, Object.class);
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
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
        param.add("county", county);
        param.add("title", title);
        param.add("price", price);
        param.add("ts", ts);
        return restTemplate.postForObject(url + "lease_page", param, int.class);
    }

    /**
     * 小区列表
     * @param county
     * @param title
     * @param price
     * @param ts
     * @param nowPage
     * @return
     */
    @RequestMapping(value = "/village", method = RequestMethod.POST)
    public List<Village> getListVillage(String county, String title, String price, String ts, String nowPage) {
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
        param.add("county", county);
        param.add("title", title);
        param.add("price", price);
        param.add("ts", ts);
        param.add("nowPage", nowPage);
        return (List<Village>) restTemplate.postForObject(url + "village", param, Object.class);
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
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
        param.add("county", county);
        param.add("title", title);
        param.add("price", price);
        param.add("ts", ts);
        return restTemplate.postForObject(url + "village_page", param, int.class);
    }


    /**
     * 小区相片
     * @param pk
     * @return
     */
    @RequestMapping(value = "/village_img/{pk}", produces = MediaType.IMAGE_JPEG_VALUE, method = RequestMethod.GET)
    public byte[] getVillageImage(@PathVariable("pk") String pk) {
        VillageImg villageImg = restTemplate.getForObject(url + "villageImg/" + pk, VillageImg.class);
        byte[] img = villageImg.getImg();
        InputStream inputStream = new ByteArrayInputStream(img);
        try {
            inputStream.read(img, 0, inputStream.available());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    /**
     * 单个二手房
     * @param pk
     * @return
     */
    @RequestMapping(value = "/sell_detail", method = RequestMethod.POST)
    public SellRoom getDetailSell(String pk) {
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
        param.add("pk", pk);
        return restTemplate.postForObject(url + "sell_detail", param, SellRoom.class);
    }

    /**
     * 户型相片
     * @param pk
     * @return
     */
    @RequestMapping(value = "/compartment_img/{pk}", produces = MediaType.IMAGE_JPEG_VALUE, method = RequestMethod.GET)
    public byte[] getCompartmentImage(@PathVariable("pk") String pk) {
        SellRoomCompartment sellRoomCompartment = restTemplate.getForObject(url + "compartmentImg/" + pk, SellRoomCompartment.class);
        byte[] img = sellRoomCompartment.getImg();
        InputStream inputStream = new ByteArrayInputStream(img);
        try {
            inputStream.read(img, 0, inputStream.available());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    /**
     * 单个租房
     * @param pk
     * @return
     */
    @RequestMapping(value = "/lease_detail", method = RequestMethod.POST)
    public LeaseRoom getDetailLease(String pk) {
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
        param.add("pk", pk);
        return restTemplate.postForObject(url + "lease_detail", param, LeaseRoom.class);
    }

    /**
     * 单个小区
     * @param pk
     * @return
     */
    @RequestMapping(value = "/village_detail", method = RequestMethod.POST)
    public Village getDetailVillage(String pk) {
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
        param.add("pk", pk);
        return restTemplate.postForObject(url + "village_detail", param, Village.class);
    }

    /**
     * 注册
     * @param customer
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public int doCustomer(Customer customer) {
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
        param.add("account", customer.getAccount());
        param.add("password", customer.getPassword());
        param.add("mail", customer.getMail());
        param.add("name", customer.getName());
        param.add("identity", customer.getIdentity());
        return restTemplate.postForObject(url + "register", param, int.class);
    }

    /**
     * 查密码
     *
     * @param account
     * @return
     */
    @RequestMapping(value = "/login_customer", method = RequestMethod.POST)
    public Customer getCustomerByAccount(String account) {
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
        param.add("account", account);
        return  restTemplate.postForObject(url + "login_customer", param, Customer.class);
    }

    /**
     * 账号是否存在
     *
     * @param account
     * @return
     */
    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public int getAccount(String account) {
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
        param.add("account", account);
        return  restTemplate.postForObject(url + "account", param, int.class);
    }

}
