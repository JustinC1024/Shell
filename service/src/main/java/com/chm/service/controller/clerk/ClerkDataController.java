package com.chm.service.controller.clerk;

import com.chm.service.pojo.human.Clerk;
import com.chm.service.pojo.human.Customer;
import com.chm.service.pojo.record.Bill;
import com.chm.service.pojo.record.TempImg;
import com.chm.service.service.BillService;
import com.chm.service.service.ClerkService;
import com.chm.service.service.TempImgService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/clerk_data")
public class ClerkDataController {

    @Autowired
    private ClerkService clerkService;
    @Autowired
    private BillService billService;
    @Autowired
    private TempImgService tempImgService;

    /**
     * 修改个人信息
     *
     * @param clerk
     * @return
     */
    @RequestMapping(value = "/update_clerk", method = RequestMethod.POST)
    public String updateCustomer(Clerk clerk) {
        int flag;
        try {
            flag = clerkService.updateClerk(clerk);
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
     * 订单信息
     *
     * @param status
     * @param ts
     * @param nowPage
     * @param clerk
     * @return
     */
    @RequestMapping(value = "/bill", method = RequestMethod.POST)
    public List<Bill> getBill(String status, String ts, String nowPage, String clerk) {
        // 参数为空判断
        if (status == null || "".equals(status) || clerk == null || "".equals(clerk)) {
            return null;
        }
        Bill bill = new Bill();
        Clerk c = new Clerk();
        c.setAccount(clerk);
        bill.setPkClerk(c);
        bill.setStatus(Integer.parseInt(status));
        // 条件为空判断
        if (ts != null && !"".equals(ts)) {
            bill.setTs(ts);
        }
        return billService.getListBill(bill, Integer.parseInt(nowPage));
    }

    /**
     * 订单信息
     *
     * @param status
     * @param ts
     * @param clerk
     * @return
     */
    @RequestMapping(value = "/bill_page", method = RequestMethod.POST)
    public int getBill(String status, String ts, String clerk) {
        // 参数为空判断
        if (status == null || "".equals(status) || clerk == null || "".equals(clerk)) {
            return 0;
        }
        Bill bill = new Bill();
        Clerk c = new Clerk();
        c.setAccount(clerk);
        bill.setPkClerk(c);
        bill.setStatus(Integer.parseInt(status));
        // 条件为空判断
        if (ts != null && !"".equals(ts)) {
            bill.setTs(ts);
        }
        PageInfo pageInfo = billService.getListBillPage(bill);
        return pageInfo.getPages();
    }

    /**
     * 更改订单
     *
     * @param pk
     * @param status
     * @return
     */
    @RequestMapping(value = "/update_bill", method = RequestMethod.POST)
    public int updateBill(String pk, String status) {
        // 参数为空判断
        if (status == null || "".equals(status) || pk == null || "".equals(pk)) {
            return 0;
        }
        Bill bill = new Bill();
        bill.setPkBill(pk);
        bill.setStatus(Integer.parseInt(status));
        int flag;
        try {
            flag = billService.updateBill(bill);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        // 修改结果判断
        if (flag == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * 上传头像
     *
     * @param file
     * @return
     */
    @RequestMapping(value = "/upload_img", method = RequestMethod.POST)
    public String upImg(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            byte[] pic = new byte[(int) file.getSize()];
            inputStream.read(pic);
            TempImg tempImg = new TempImg();
            tempImg.setImg(pic);
            return tempImgService.doTempImg(tempImg);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 删除头像
     *
     * @param pk
     * @return
     */
    @RequestMapping(value = "/delete_img", method = RequestMethod.POST)
    public int deleteImg(String pk) {
        int flag = 0;
        // 参数判断
        if (pk == null || "".equals(pk)) {
            return flag;
        }
        try {
            flag = tempImgService.deleteTempImg(pk);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
