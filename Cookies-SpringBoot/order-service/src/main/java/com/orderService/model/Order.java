package com.orderService.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class Order {
    @Id
    long id ;
    BigDecimal totalPrice;
    Date orderDate ;
    String status;
    String shipping_Address;
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> orderItemsList;
    public Order(){

    }
    public Order(long id, BigDecimal totalPrice, Date orderDate, String status, String shipping_Address) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.status = status;
        this.shipping_Address = shipping_Address;
    }
    public List<OrderItem> getOrderItemsList() {
        return orderItemsList;
    }

    public void setOrderItemsList(List<OrderItem> orderItemsList) {
        this.orderItemsList = orderItemsList;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShipping_Address() {
        return shipping_Address;
    }

    public void setShipping_Address(String shipping_Address) {
        this.shipping_Address = shipping_Address;
    }

}
