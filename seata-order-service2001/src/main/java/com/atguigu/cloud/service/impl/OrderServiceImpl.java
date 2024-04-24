package com.atguigu.cloud.service.impl;

import com.atguigu.cloud.apis.AccountFeignApi;
import com.atguigu.cloud.apis.StorageFeignApi;
import com.atguigu.cloud.entities.Order;
import com.atguigu.cloud.mapper.OrderMapper;
import com.atguigu.cloud.service.OrderService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private StorageFeignApi storageFeignApi;

    @Resource
    private AccountFeignApi accountFeignApi;

    @Override
    @GlobalTransactional(name = "zzyy-create-order",rollbackFor = Exception.class) //AT
    public void create(Order order) {

        //xid全局事务id的检查
        String xid = RootContext.getXID();

        log.info("xid:{}", xid);
        log.info("=====================开始新建订单");

        order.setStatus(0);
        int insert = orderMapper.insertSelective(order);

        Order orderTemp = null;

        if (insert > 0) {

            orderTemp = orderMapper.selectOne(order);
            log.info("=====================新建订单成功");

            //扣减库存
            log.info("=====================开始扣减库存");
            storageFeignApi.decrease(orderTemp.getProductId(), orderTemp.getCount());
            log.info("=====================成功扣减库存");

            //扣减余额
            log.info("=====================开始扣减余额");
            accountFeignApi.decrease(orderTemp.getUserId(), orderTemp.getMoney());
            log.info("=====================成功扣减余额");

            //修改订单状态
            orderTemp.setStatus(1);
            orderMapper.updateByPrimaryKeySelective(orderTemp);


        }

        log.info("xid:{}", xid);
        log.info("=====================结束新建订单");


    }
}
