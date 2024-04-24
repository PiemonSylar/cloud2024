package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.Order;
import com.atguigu.cloud.entities.OrderDTO;
import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping("/order/create")
    public ResultData createOrder(@RequestBody OrderDTO orderDTO) {
        Order order = new Order();
        BeanUtils.copyProperties(orderDTO,order);
        orderService.create(order);
        return ResultData.success(order);
    }


}
