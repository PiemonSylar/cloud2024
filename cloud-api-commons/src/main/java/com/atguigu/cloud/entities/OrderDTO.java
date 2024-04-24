package com.atguigu.cloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @auther zzyy
 * @create 2023-12-22 22:25
 * 一般而言，调用者不应该获悉服务提供者的entity资源并知道表结构关系，所以服务提供方给出的
 *       接口文档都都应成为DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO implements Serializable
{
    /**
     *用户id
     */
    private Long userId;

    /**
     * 产品id
     */
    private Long productId;

    /**
     * 数量
     */
    private Integer count;

    /**
     * 金额
     */
    private Long money;

}

