package com.zhengcs.seata.busi.controller;

import com.zhengcs.seata.busi.service.BusiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhengcs
 * @version 1.0
 * @description
 * @date 2020/6/15 7:51 下午
 **/
@RestController
public class BusiController {

    @Autowired
    private BusiService busiService;

    @RequestMapping("/purchase")
    public String purchase(@RequestParam("userId") String userId, @RequestParam("code") String commodityCode, @RequestParam("count") int orderCount){
        try {
            busiService.purchase(userId,commodityCode,orderCount);
        } catch (Exception e) {
            e.printStackTrace();
            return "下单失败";
        }
        return "下单成功";
    }
}
