package com.chm.customer.controller.customer;

import com.chm.customer.pojo.building.*;
import com.chm.customer.pojo.human.Clerk;
import com.chm.customer.pojo.human.Customer;
import com.chm.customer.pojo.record.Bill;
import com.chm.customer.pojo.record.Identity;
import com.chm.customer.pojo.record.Owner;
import com.chm.customer.pojo.record.TempImg;
import com.chm.customer.service.InsertImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/customer_data")
public class CustomerDataController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private InsertImgService insertImgService;

    String url = "http://PROVIDER/customer_data/";

    /**
     * 个人信息页
     *
     * @param account
     * @return
     */
    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public Customer getCustomer(String account) {
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
        param.add("account", account);
        return restTemplate.postForObject(url + "customer", param, Customer.class);
    }

    /**
     * 修改个人信息
     *
     * @param customer
     * @return
     */
    @RequestMapping(value = "/update_customer", method = RequestMethod.POST)
    public String updateCustomer(Customer customer) {
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
        param.add("pkCustomer", customer.getPkCustomer());
        param.add("mail", customer.getMail());
        param.add("name", customer.getName());
        param.add("identity", customer.getIdentity());
        return restTemplate.postForObject(url + "update_customer", param, String.class);
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
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
        param.add("account", account);
        param.add("password", password);
        return restTemplate.postForObject(url + "password", param, int.class);
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
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
        param.add("pk", pk);
        param.add("password", password);
        return restTemplate.postForObject(url + "update_password", param, String.class);
    }

    /**
     * 所有业务员
     *
     * @return
     */
    @RequestMapping(value = "/clerk", method = RequestMethod.POST)
    public List<Clerk> getClerk() {
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
        return (List<Clerk>) restTemplate.postForObject(url + "clerk", param, Object.class);
    }

    /**
     * 获取头像
     *
     * @param pk
     * @return
     */
    @RequestMapping(value = "/img/{pk}", produces = MediaType.IMAGE_JPEG_VALUE, method = RequestMethod.GET)
    public byte[] getImg(@PathVariable("pk") String pk) {
        TempImg tempImg = restTemplate.getForObject(url + "customerImg/" + pk, TempImg.class);
        byte[] img = tempImg.getImg();
        InputStream inputStream = new ByteArrayInputStream(img);
        try {
            inputStream.read(img, 0, inputStream.available());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
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
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
        param.add("pkClerk", pkClerk);
        param.add("pkCustomer", pkCustomer);
        param.add("pkRoom", pkRoom);
        param.add("type", type);
        return restTemplate.postForObject(url + "clerk", param, int.class);
    }

    /**
     * 上传图片
     *
     * @param file
     * @return
     */
    @RequestMapping(value = "/do_img", method = RequestMethod.POST)
    public String doImg(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            byte[] pic = new byte[(int) file.getSize()];
            inputStream.read(pic);
            RoomImg roomImg = new RoomImg();
            roomImg.setImg(pic);
            return insertImgService.doRoomImg(roomImg);
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
            return insertImgService.doSellRoomCompartment(sellRoomCompartment);
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
    public String doSellRoom(SellRoom sellRoom) {
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
        param.add("title", sellRoom.getTitle());
        param.add("county", sellRoom.getCounty());
        param.add("location", sellRoom.getLocation());
        param.add("pkVillage.title", sellRoom.getPkVillage().getTitle());
        param.add("pkSellPrice.totalPrice", sellRoom.getPkSellPrice().getTotalPrice());
        param.add("pkSellPrice.unitPrice", sellRoom.getPkSellPrice().getUnitPrice());
        param.add("pkSellRoomBasic.apartment", sellRoom.getPkSellRoomBasic().getApartment());
        param.add("pkSellRoomBasic.floor", sellRoom.getPkSellRoomBasic().getFloor());
        param.add("pkSellRoomBasic.floorNum", sellRoom.getPkSellRoomBasic().getFloorNum());
        param.add("pkSellRoomBasic.builtUpArea", sellRoom.getPkSellRoomBasic().getBuiltUpArea());
        param.add("pkSellRoomBasic.structure", sellRoom.getPkSellRoomBasic().getStructure());
        param.add("pkSellRoomBasic.area", sellRoom.getPkSellRoomBasic().getArea());
        param.add("pkSellRoomBasic.type", sellRoom.getPkSellRoomBasic().getType());
        param.add("pkSellRoomBasic.orientation", sellRoom.getPkSellRoomBasic().getOrientation());
        param.add("pkSellRoomBasic.building", sellRoom.getPkSellRoomBasic().getBuilding());
        param.add("pkSellRoomBasic.decoration", sellRoom.getPkSellRoomBasic().getDecoration());
        param.add("pkSellRoomBasic.ladder", sellRoom.getPkSellRoomBasic().getLadder());
        param.add("pkSellRoomBasic.propertyYears", sellRoom.getPkSellRoomBasic().getPropertyYears());
        param.add("pkSellRoomTransaction.listingTs", sellRoom.getPkSellRoomTransaction().getListingTs());
        param.add("pkSellRoomTransaction.ownership", sellRoom.getPkSellRoomTransaction().getOwnership());
        param.add("pkSellRoomTransaction.lastTs", sellRoom.getPkSellRoomTransaction().getLastTs());
        param.add("pkSellRoomTransaction.purpose", sellRoom.getPkSellRoomTransaction().getPurpose());
        param.add("pkSellRoomTransaction.years", sellRoom.getPkSellRoomTransaction().getYears());
        param.add("pkSellRoomTransaction.property", sellRoom.getPkSellRoomTransaction().getProperty());
        param.add("pkSellRoomTransaction.mortgage", sellRoom.getPkSellRoomTransaction().getMortgage());
        param.add("pkSellRoomTransaction.permit", sellRoom.getPkSellRoomTransaction().getPermit());
        param.add("pkSellRoomCharacteristic.core", sellRoom.getPkSellRoomCharacteristic().getCore());
        param.add("pkSellRoomCharacteristic.apartment", sellRoom.getPkSellRoomCharacteristic().getApartment());
        param.add("pkSellRoomCharacteristic.village", sellRoom.getPkSellRoomCharacteristic().getVillage());
        param.add("pkSellRoomCharacteristic.crowd", sellRoom.getPkSellRoomCharacteristic().getCrowd());
        param.add("pkSellRoomCharacteristic.traffic", sellRoom.getPkSellRoomCharacteristic().getTraffic());
        param.add("pkSellRoomCharacteristic.surround", sellRoom.getPkSellRoomCharacteristic().getSurround());
        param.add("pkSellRoomCharacteristic.taxation", sellRoom.getPkSellRoomCharacteristic().getTaxation());
        if (sellRoom.getImgs() != null) {
            int i = 0;
            for (RoomImg ri : sellRoom.getImgs()) {
                param.add("imgs[" + i + "].pkRoomImg", ri.getPkRoomImg());
                i++;
            }
        }
        return restTemplate.postForObject(url + "do_sell_room", param, String.class);
    }

    /**
     * 增SellCompartmentInfo
     *
     * @param sellRoom
     * @return
     */
    @RequestMapping(value = "/do_compartment_info", method = RequestMethod.POST)
    public int doCompartmentInfo(SellRoom sellRoom) {
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
        param.add("pkSellRoom", sellRoom.getPkSellRoom());
        param.add("pkSellRoomCompartment.pkSellRoomCompartment", sellRoom.getPkSellRoomCompartment().getPkSellRoomCompartment());
        if (sellRoom.getPkSellRoomCompartment().getCompartments() != null) {
            int i = 0;
            for (SellCompartmentInfo sci : sellRoom.getPkSellRoomCompartment().getCompartments()) {
                param.add("pkSellRoomCompartment.compartments[" + i + "].area", sci.getArea());
                param.add("pkSellRoomCompartment.compartments[" + i + "].spare1", sci.getSpare1());
                param.add("pkSellRoomCompartment.compartments[" + i + "].direction", sci.getDirection());
                param.add("pkSellRoomCompartment.compartments[" + i + "].windows", sci.getWindows());
                i++;
            }
        }
        return restTemplate.postForObject(url + "do_compartment_info", param, int.class);
    }

    /**
     * 改sellRoom
     *
     * @param sellRoom
     * @return
     */
    @RequestMapping(value = "/update_sell_room", method = RequestMethod.POST)
    public int updateSellRoom(SellRoom sellRoom) {
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
        param.add("pkSellRoom", sellRoom.getPkSellRoom());
        param.add("title", sellRoom.getTitle());
        param.add("county", sellRoom.getCounty());
        param.add("location", sellRoom.getLocation());
        param.add("pkVillage.pkVillage", sellRoom.getPkVillage().getPkVillage());
        param.add("pkVillage.title", sellRoom.getPkVillage().getTitle());
        param.add("pkSellPrice.pkSellPrice", sellRoom.getPkSellPrice().getPkSellPrice());
        param.add("pkSellPrice.totalPrice", sellRoom.getPkSellPrice().getTotalPrice());
        param.add("pkSellPrice.unitPrice", sellRoom.getPkSellPrice().getUnitPrice());
        param.add("pkSellRoomBasic.pkSellRoomBasic", sellRoom.getPkSellRoomBasic().getPkSellRoomBasic());
        param.add("pkSellRoomBasic.apartment", sellRoom.getPkSellRoomBasic().getApartment());
        param.add("pkSellRoomBasic.floor", sellRoom.getPkSellRoomBasic().getFloor());
        param.add("pkSellRoomBasic.floorNum", sellRoom.getPkSellRoomBasic().getFloorNum());
        param.add("pkSellRoomBasic.builtUpArea", sellRoom.getPkSellRoomBasic().getBuiltUpArea());
        param.add("pkSellRoomBasic.structure", sellRoom.getPkSellRoomBasic().getStructure());
        param.add("pkSellRoomBasic.area", sellRoom.getPkSellRoomBasic().getArea());
        param.add("pkSellRoomBasic.type", sellRoom.getPkSellRoomBasic().getType());
        param.add("pkSellRoomBasic.orientation", sellRoom.getPkSellRoomBasic().getOrientation());
        param.add("pkSellRoomBasic.building", sellRoom.getPkSellRoomBasic().getBuilding());
        param.add("pkSellRoomBasic.decoration", sellRoom.getPkSellRoomBasic().getDecoration());
        param.add("pkSellRoomBasic.ladder", sellRoom.getPkSellRoomBasic().getLadder());
        param.add("pkSellRoomBasic.propertyYears", sellRoom.getPkSellRoomBasic().getPropertyYears());
        param.add("pkSellRoomTransaction.pkSellRoomTransaction", sellRoom.getPkSellRoomTransaction().getPkSellRoomTransaction());
        param.add("pkSellRoomTransaction.listingTs", sellRoom.getPkSellRoomTransaction().getListingTs());
        param.add("pkSellRoomTransaction.ownership", sellRoom.getPkSellRoomTransaction().getOwnership());
        param.add("pkSellRoomTransaction.lastTs", sellRoom.getPkSellRoomTransaction().getLastTs());
        param.add("pkSellRoomTransaction.purpose", sellRoom.getPkSellRoomTransaction().getPurpose());
        param.add("pkSellRoomTransaction.years", sellRoom.getPkSellRoomTransaction().getYears());
        param.add("pkSellRoomTransaction.property", sellRoom.getPkSellRoomTransaction().getProperty());
        param.add("pkSellRoomTransaction.mortgage", sellRoom.getPkSellRoomTransaction().getMortgage());
        param.add("pkSellRoomTransaction.permit", sellRoom.getPkSellRoomTransaction().getPermit());
        param.add("pkSellRoomCharacteristic.pkSellRoomCharacteristic", sellRoom.getPkSellRoomCharacteristic().getPkSellRoomCharacteristic());
        param.add("pkSellRoomCharacteristic.core", sellRoom.getPkSellRoomCharacteristic().getCore());
        param.add("pkSellRoomCharacteristic.apartment", sellRoom.getPkSellRoomCharacteristic().getApartment());
        param.add("pkSellRoomCharacteristic.village", sellRoom.getPkSellRoomCharacteristic().getVillage());
        param.add("pkSellRoomCharacteristic.crowd", sellRoom.getPkSellRoomCharacteristic().getCrowd());
        param.add("pkSellRoomCharacteristic.traffic", sellRoom.getPkSellRoomCharacteristic().getTraffic());
        param.add("pkSellRoomCharacteristic.surround", sellRoom.getPkSellRoomCharacteristic().getSurround());
        param.add("pkSellRoomCharacteristic.taxation", sellRoom.getPkSellRoomCharacteristic().getTaxation());
        if (sellRoom.getImgs() != null) {
            int i = 0;
            for (RoomImg ri : sellRoom.getImgs()) {
                param.add("imgs[" + i + "].pkRoomImg", ri.getPkRoomImg());
                i++;
            }
        }
        if (sellRoom.getDeleteImg() != null) {
            int i = 0;
            for (String s : sellRoom.getDeleteImg()) {
                param.add("deleteImg[" + i + "].pkRoomImg", s);
                i++;
            }
        }
        return restTemplate.postForObject(url + "update_sell_room", param, int.class);
    }

    /**
     * 改SellCompartmentInfo
     *
     * @param sellRoom
     * @return
     */
    @RequestMapping(value = "/update_compartment_info", method = RequestMethod.POST)
    public int updateCompartmentInfo(SellRoom sellRoom) {
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
        param.add("pkSellRoom", sellRoom.getPkSellRoom());
        param.add("pkSellRoomCompartment.pkSellRoomCompartment", sellRoom.getPkSellRoomCompartment().getPkSellRoomCompartment());
        if (sellRoom.getPkSellRoomCompartment().getCompartments() != null) {
            int i = 0;
            for (SellCompartmentInfo sci : sellRoom.getPkSellRoomCompartment().getCompartments()) {
                param.add("pkSellRoomCompartment.compartments[" + i + "].pkSellCompartmentInfo", sci.getPkSellCompartmentInfo());
                param.add("pkSellRoomCompartment.compartments[" + i + "].area", sci.getArea());
                param.add("pkSellRoomCompartment.compartments[" + i + "].spare1", sci.getSpare1());
                param.add("pkSellRoomCompartment.compartments[" + i + "].direction", sci.getDirection());
                param.add("pkSellRoomCompartment.compartments[" + i + "].windows", sci.getWindows());
                i++;
            }
        }
        if (sellRoom.getPkSellRoomCompartment().getDeleteCompartment() != null) {
            int i = 0;
            for (String s : sellRoom.getPkSellRoomCompartment().getDeleteCompartment()) {
                param.add("pkSellRoomCompartment.deleteCompartment[" + i + "]", s);
                i++;
            }
        }
        return restTemplate.postForObject(url + "update_compartment_info", param, int.class);
    }

    /**
     * 删sellRoom
     *
     * @param pk
     * @return
     */
    @RequestMapping(value = "/delete_sell_room", method = RequestMethod.POST)
    public int deleteSellRoom(String pk) {
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
        param.add("pk", pk);
        return restTemplate.postForObject(url + "delete_sell_room", param, int.class);
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
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
        param.add("account", account);
        param.add("nowPage", nowPage);
        return (List<Bill>) restTemplate.postForObject(url + "bill", param, Object.class);
    }

    /**
     * 订单页数
     *
     * @param account
     * @return
     */
    @RequestMapping(value = "/bill_page", method = RequestMethod.POST)
    public int getBill(String account) {
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
        param.add("account", account);
        return restTemplate.postForObject(url + "bill_page", param, int.class);
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
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
        param.add("account", account);
        param.add("nowPage", nowPage);
        return (List<Owner>) restTemplate.postForObject(url + "owner", param, Object.class);
    }

    /**
     * 信息
     *
     * @param account
     * @return
     */
    @RequestMapping(value = "/owner_page", method = RequestMethod.POST)
    public int getOwner(String account) {
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
        param.add("account", account);
        return restTemplate.postForObject(url + "owner_page", param, int.class);
    }

    /**
     * 改leaseRoom
     *
     * @param leaseRoom
     * @return
     */
    @RequestMapping(value = "/update_lease_room", method = RequestMethod.POST)
    public int updateLeaseRoom(LeaseRoom leaseRoom) {
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
        param.add("pkLeaseRoom", leaseRoom.getPkLeaseRoom());
        param.add("title", leaseRoom.getTitle());
        param.add("county", leaseRoom.getCounty());
        param.add("location", leaseRoom.getLocation());
        param.add("apartment", leaseRoom.getApartment());
        param.add("mode", leaseRoom.getMode());
        param.add("pkLeasePrice.pkLeasePrice", leaseRoom.getPkLeasePrice().getPkLeasePrice());
        param.add("pkLeasePrice.payment", leaseRoom.getPkLeasePrice().getPayment());
        param.add("pkLeasePrice.rent", leaseRoom.getPkLeasePrice().getRent());
        param.add("pkLeasePrice.deposit", leaseRoom.getPkLeasePrice().getDeposit());
        param.add("pkLeaseRoomBasic.pkLeaseRoomBasic", leaseRoom.getPkLeaseRoomBasic().getPkLeaseRoomBasic());
        param.add("pkLeaseRoomBasic.area", leaseRoom.getPkLeaseRoomBasic().getArea());
        param.add("pkLeaseRoomBasic.orientation", leaseRoom.getPkLeaseRoomBasic().getOrientation());
        param.add("pkLeaseRoomBasic.checkIn", leaseRoom.getPkLeaseRoomBasic().getCheckIn());
        param.add("pkLeaseRoomBasic.floor", leaseRoom.getPkLeaseRoomBasic().getFloor());
        param.add("pkLeaseRoomBasic.floorNum", leaseRoom.getPkLeaseRoomBasic().getFloorNum());
        param.add("pkLeaseRoomBasic.ladder", leaseRoom.getPkLeaseRoomBasic().getLadder());
        param.add("pkLeaseRoomBasic.parking", leaseRoom.getPkLeaseRoomBasic().getParking());
        param.add("pkLeaseRoomBasic.water", leaseRoom.getPkLeaseRoomBasic().getWater());
        param.add("pkLeaseRoomBasic.electricity", leaseRoom.getPkLeaseRoomBasic().getElectricity());
        param.add("pkLeaseRoomBasic.gas", leaseRoom.getPkLeaseRoomBasic().getGas());
        param.add("pkLeaseRoomBasic.heating", leaseRoom.getPkLeaseRoomBasic().getHeating());
        param.add("pkLeaseRoomBasic.term", leaseRoom.getPkLeaseRoomBasic().getTerm());
        param.add("pkLeaseRoomBasic.inspection", leaseRoom.getPkLeaseRoomBasic().getInspection());
        param.add("pkLeaseRoomMating.pkLeaseRoomMating", leaseRoom.getPkLeaseRoomMating().getPkLeaseRoomMating());
        param.add("pkLeaseRoomMating.washing", leaseRoom.getPkLeaseRoomMating().getWashing());
        param.add("pkLeaseRoomMating.air", leaseRoom.getPkLeaseRoomMating().getAir());
        param.add("pkLeaseRoomMating.wardrobe", leaseRoom.getPkLeaseRoomMating().getWardrobe());
        param.add("pkLeaseRoomMating.television", leaseRoom.getPkLeaseRoomMating().getTelevision());
        param.add("pkLeaseRoomMating.fridge", leaseRoom.getPkLeaseRoomMating().getFridge());
        param.add("pkLeaseRoomMating.heater", leaseRoom.getPkLeaseRoomMating().getHeater());
        param.add("pkLeaseRoomMating.bed", leaseRoom.getPkLeaseRoomMating().getBed());
        param.add("pkLeaseRoomMating.heating", leaseRoom.getPkLeaseRoomMating().getHeating());
        param.add("pkLeaseRoomMating.broadband", leaseRoom.getPkLeaseRoomMating().getBroadband());
        param.add("pkLeaseRoomMating.gas", leaseRoom.getPkLeaseRoomMating().getGas());
        if (leaseRoom.getImgs() != null) {
            int i = 0;
            for (RoomImg ri : leaseRoom.getImgs()) {
                param.add("imgs[" + i + "].pkRoomImg", ri.getPkRoomImg());
                i++;
            }
        }
        if (leaseRoom.getDeleteImg() != null) {
            int i = 0;
            for (String s : leaseRoom.getDeleteImg()) {
                param.add("deleteImg[" + i + "].pkRoomImg", s);
                i++;
            }
        }
        return restTemplate.postForObject(url + "update_lease_room", param, int.class);
    }

    /**
     * 增leaseRoom
     *
     * @param leaseRoom
     * @return
     */
    @RequestMapping(value = "/do_lease_room", method = RequestMethod.POST)
    public String doLeaseRoom(LeaseRoom leaseRoom) {
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
        param.add("title", leaseRoom.getTitle());
        param.add("county", leaseRoom.getCounty());
        param.add("location", leaseRoom.getLocation());
        param.add("apartment", leaseRoom.getApartment());
        param.add("mode", leaseRoom.getMode());
        param.add("pkLeasePrice.payment", leaseRoom.getPkLeasePrice().getPayment());
        param.add("pkLeasePrice.rent", leaseRoom.getPkLeasePrice().getRent());
        param.add("pkLeasePrice.deposit", leaseRoom.getPkLeasePrice().getDeposit());
        param.add("pkLeaseRoomBasic.area", leaseRoom.getPkLeaseRoomBasic().getArea());
        param.add("pkLeaseRoomBasic.orientation", leaseRoom.getPkLeaseRoomBasic().getOrientation());
        param.add("pkLeaseRoomBasic.checkIn", leaseRoom.getPkLeaseRoomBasic().getCheckIn());
        param.add("pkLeaseRoomBasic.floor", leaseRoom.getPkLeaseRoomBasic().getFloor());
        param.add("pkLeaseRoomBasic.floorNum", leaseRoom.getPkLeaseRoomBasic().getFloorNum());
        param.add("pkLeaseRoomBasic.ladder", leaseRoom.getPkLeaseRoomBasic().getLadder());
        param.add("pkLeaseRoomBasic.parking", leaseRoom.getPkLeaseRoomBasic().getParking());
        param.add("pkLeaseRoomBasic.water", leaseRoom.getPkLeaseRoomBasic().getWater());
        param.add("pkLeaseRoomBasic.electricity", leaseRoom.getPkLeaseRoomBasic().getElectricity());
        param.add("pkLeaseRoomBasic.gas", leaseRoom.getPkLeaseRoomBasic().getGas());
        param.add("pkLeaseRoomBasic.heating", leaseRoom.getPkLeaseRoomBasic().getHeating());
        param.add("pkLeaseRoomBasic.term", leaseRoom.getPkLeaseRoomBasic().getTerm());
        param.add("pkLeaseRoomBasic.inspection", leaseRoom.getPkLeaseRoomBasic().getInspection());
        param.add("pkLeaseRoomMating.washing", leaseRoom.getPkLeaseRoomMating().getWashing());
        param.add("pkLeaseRoomMating.air", leaseRoom.getPkLeaseRoomMating().getAir());
        param.add("pkLeaseRoomMating.wardrobe", leaseRoom.getPkLeaseRoomMating().getWardrobe());
        param.add("pkLeaseRoomMating.television", leaseRoom.getPkLeaseRoomMating().getTelevision());
        param.add("pkLeaseRoomMating.fridge", leaseRoom.getPkLeaseRoomMating().getFridge());
        param.add("pkLeaseRoomMating.heater", leaseRoom.getPkLeaseRoomMating().getHeater());
        param.add("pkLeaseRoomMating.bed", leaseRoom.getPkLeaseRoomMating().getBed());
        param.add("pkLeaseRoomMating.heating", leaseRoom.getPkLeaseRoomMating().getHeating());
        param.add("pkLeaseRoomMating.broadband", leaseRoom.getPkLeaseRoomMating().getBroadband());
        param.add("pkLeaseRoomMating.gas", leaseRoom.getPkLeaseRoomMating().getGas());
        if (leaseRoom.getImgs() != null) {
            int i = 0;
            for (RoomImg ri : leaseRoom.getImgs()) {
                param.add("imgs[" + i + "].pkRoomImg", ri.getPkRoomImg());
                i++;
            }
        }
        return restTemplate.postForObject(url + "do_lease_room", param, String.class);
    }

    /**
     * 删leaseRoom
     *
     * @param pk
     * @return
     */
    @RequestMapping(value = "/delete_lease_room", method = RequestMethod.POST)
    public int deleteLeaseRoom(String pk) {
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
        param.add("pk", pk);
        return restTemplate.postForObject(url + "delete_lease_room", param, int.class);
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
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
        param.add("cus", cus);
        param.add("room", room);
        param.add("type", type);
        return restTemplate.postForObject(url + "do_owner", param, int.class);
    }

    /**
     * 标题是否存在
     *
     * @param title
     * @return
     */
    @RequestMapping(value = "/lease_title", method = RequestMethod.POST)
    public int getLeaseByTitle(String title) {
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
        param.add("title", title);
        return restTemplate.postForObject(url + "lease_title", param, int.class);
    }

    /**
     * 标题是否存在
     *
     * @param title
     * @return
     */
    @RequestMapping(value = "/sell_title", method = RequestMethod.POST)
    public int getSellByTitle(String title) {
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
        param.add("title", title);
        return restTemplate.postForObject(url + "sell_title", param, int.class);
    }

    /**
     * 认证
     *
     * @param account
     * @return
     */
    @RequestMapping(value = "/identity", method = RequestMethod.POST)
    public Identity getIdentity(String account) {
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
        param.add("account", account);
        return restTemplate.postForObject(url + "identity", param, Identity.class);
    }

}
