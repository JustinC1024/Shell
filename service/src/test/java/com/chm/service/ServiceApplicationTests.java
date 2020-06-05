package com.chm.service;

import com.chm.service.mapper.building.LeaseRoomMapper;
import com.chm.service.mapper.building.RoomImgMapper;
import com.chm.service.mapper.building.SellRoomCompartmentMapper;
import com.chm.service.mapper.human.CustomerMapper;
import com.chm.service.pojo.building.LeasePrice;
import com.chm.service.pojo.building.LeaseRoom;
import com.chm.service.pojo.building.LeaseRoomBasic;
import com.chm.service.pojo.building.RoomImg;
import com.chm.service.pojo.human.Customer;
import com.chm.service.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.ArrayList;
import java.util.List;

@MapperScan(value= "com.chm.service.mapper")
@SpringBootTest
class ServiceApplicationTests {

    @Autowired
    private LeaseRoomMapper leaseRoomMapper;
    List<LeaseRoom> leaseRoomList = new ArrayList<LeaseRoom>();
    LeaseRoom leaseRoom = new LeaseRoom();
    LeaseRoomBasic leaseRoomBasic = new LeaseRoomBasic();
    LeasePrice leasePrice = new LeasePrice();
    @Autowired
    private RoomImgMapper roomImgMapper;
    @Autowired
    private SellRoomCompartmentMapper sellRoomCompartmentMapper;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private CustomerService customerService;

    @Test
    void test1(){
//        stringRedisTemplate.opsForValue().append("cao", "nm");
        redisTemplate.opsForValue().set("cc",leaseRoomMapper.getLeaseRoomByPk("1578967656IOE035"));
    }

    @Test
    void test2(){
        roomImgMapper.getImgInRoomImgByPk("1578967660CST770");
    }

    @Test
    void test3() {
        sellRoomCompartmentMapper.getImgSellRoomCompartmentByPk("1578470908BAX067");
    }

    @Test
    void test4() {
        Customer customer = new Customer();
        customer.setAccount("13802898593");
        System.out.println(customerMapper.doCustomer(customer));
    }

    @Test
    void test5() throws Exception {
        Customer customer = new Customer();
        customer.setAccount("13802898593");
        System.out.println(customerService.doCustomer(customer));
    }
    @Test
    void lr1() {
        LeaseRoom leaseRoom = new LeaseRoom();
        leaseRoom.setPkLeasePrice(new LeasePrice());
        leaseRoomList = leaseRoomMapper.getListLeaseRoom(leaseRoom);
        if (leaseRoomList.size() > 0) {
            for (LeaseRoom lr : leaseRoomList) {
                System.out.println(lr.getTitle());
            }
        }else {
            System.out.println("null");
        }
    }

    @Test
    void lr2() {
        leaseRoom.setCounty("增城");
        leaseRoom.setPkLeasePrice(new LeasePrice());
        leaseRoomList = leaseRoomMapper.getListLeaseRoom(leaseRoom);
        if (leaseRoomList.size() > 0) {
            for (LeaseRoom lr : leaseRoomList) {
                System.out.println(lr.getTitle());
                System.out.println(lr.getCounty());
            }
        }else {
            System.out.println("null");
        }
    }

    @Test
    void lr3() {
        leaseRoom.setMode(1);
        leaseRoomList = leaseRoomMapper.getListLeaseRoom(leaseRoom);
        if (leaseRoomList.size() > 0) {
            for (LeaseRoom lr : leaseRoomList) {
                System.out.println(lr.getTitle());
                System.out.println(lr.getMode());
            }
        }else {
            System.out.println("null");
        }
    }

    @Test
    void lr4() {
        leaseRoom.setApartment("%两房%");
        leaseRoomList = leaseRoomMapper.getListLeaseRoom(leaseRoom);
        if (leaseRoomList.size() > 0) {
            for (LeaseRoom lr : leaseRoomList) {
                System.out.println(lr.getTitle());
                System.out.println(lr.getApartment());
            }
        }else {
            System.out.println("null");
        }
    }

    @Test
    void lr5() {
        leaseRoomBasic.setOrientation("东");
        leaseRoom.setPkLeaseRoomBasic(leaseRoomBasic);
        leaseRoomList = leaseRoomMapper.getListLeaseRoom(leaseRoom);
        if (leaseRoomList.size() > 0) {
            for (LeaseRoom lr : leaseRoomList) {
                System.out.println(lr.getTitle());
                System.out.println(lr.getPkLeaseRoomBasic().getOrientation());
            }
        }else {
            System.out.println("null");
        }
    }

    @Test
    void lr6() {
        leasePrice.setRent(5);
        leaseRoom.setPkLeasePrice(leasePrice);
        leaseRoomList = leaseRoomMapper.getListLeaseRoom(leaseRoom);
        if (leaseRoomList.size() > 0) {
            for (LeaseRoom lr : leaseRoomList) {
                System.out.println(lr.getTitle());
                System.out.println(lr.getPkLeasePrice().getRent());
            }
        }else {
            System.out.println("null");
        }
    }

    @Test
    void lr7() {
        leaseRoom.setTitle("%雅居乐%");
        leaseRoomList = leaseRoomMapper.getListLeaseRoom(leaseRoom);
        if (leaseRoomList.size() > 0) {
            for (LeaseRoom lr : leaseRoomList) {
                System.out.println(lr.getTitle());
            }
        }else {
            System.out.println("null");
        }
    }

    @Test
    void lr8() {
        leaseRoom.setUpdateTs("desc");
        leaseRoomList = leaseRoomMapper.getListLeaseRoom(leaseRoom);
        if (leaseRoomList.size() > 0) {
            for (LeaseRoom lr : leaseRoomList) {
                System.out.println(lr.getTitle());
            }
        }else {
            System.out.println("null");
        }
    }

    @Test
    void lr9() {
        leaseRoom.setCounty("番禺");
        leaseRoom.setMode(1);
        leaseRoom.setApartment("%两房%");
        leaseRoomBasic.setOrientation("东");
        leaseRoom.setPkLeaseRoomBasic(leaseRoomBasic);
        leasePrice.setRent(5);
        leaseRoom.setPkLeasePrice(leasePrice);
        leaseRoom.setTitle("%雅居乐%");
        leaseRoom.setUpdateTs("desc");
        leaseRoomList = leaseRoomMapper.getListLeaseRoom(leaseRoom);
        if (leaseRoomList.size() > 0) {
            for (LeaseRoom lr : leaseRoomList) {
                System.out.println(lr.getTitle());
                System.out.println(lr.getCounty());
                System.out.println(lr.getApartment());
                System.out.println(lr.getPkLeaseRoomBasic().getOrientation());
                System.out.println(lr.getPkLeasePrice().getRent());
            }
        }else {
            System.out.println("null");
        }

    }

    @Test
    void lr10() {
        leaseRoom.setLocation("%雅居乐%");
        leaseRoomList = leaseRoomMapper.getListLeaseRoom(leaseRoom);
        for (LeaseRoom lr : leaseRoomList) {
            System.out.println(lr.getTitle());
            System.out.println(lr.getLocation());
        }
    }

}
