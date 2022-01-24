package com.bigant.dxscy.model;

import lombok.Data;

/**
 *
 */
@Data
public class TMainorder {
    /**
     *
     */
    private Integer orderId;
    /**
     *
     */
    private Integer goodsId;
    /**
     *
     */
    private Integer userId;
    /**
     *
     */
    private Object orderPrice;
    /**
     *
     */
    private String orderTime;
    /**
     *
     */
    private String orderTitle;
}