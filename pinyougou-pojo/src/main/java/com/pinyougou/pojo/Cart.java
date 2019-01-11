package com.pinyougou.pojo;

import java.io.Serializable;
import java.util.List;

public class Cart implements Serializable {

    private String sellerId;//商家ID
    private String sllerName;//商家名称
    private List<TbOrderItem> orderItemList;//购物车明细

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getSllerName() {
        return sllerName;
    }

    public void setSllerName(String sllerName) {
        this.sllerName = sllerName;
    }

    public List<TbOrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<TbOrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }
}
