package com.orderService.service;

import com.orderService.dto.OrderItemsDto;
import com.orderService.dto.OrderRequest;
import com.orderService.model.Order;
import com.orderService.model.OrderItem;
import com.orderService.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Transactional
    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        List<OrderItem> orderItemsList = new ArrayList<>();
        // Iterate over each OrderItemsDto from the OrderRequest and create corresponding OrderItem objects
        for (OrderItemsDto orderItemsDto : orderRequest.getOrderItemsDtoList()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setId(orderItemsDto.getId());
            orderItem.setSelectedQuantity(orderItemsDto.getSelectedQuantity());
            orderItem.setSubTotal(orderItemsDto.getSubTotal());
            orderItemsList.add(orderItem);
        }
        // Set the list of OrderItems to the Order
        order.setOrderItemsList(orderItemsList);
        // Calculate total price
        BigDecimal totalPrice = calculateTotalPrice(orderItemsList);
        order.setTotalPrice(totalPrice);
        // Set order date
        order.setOrderDate(orderRequest.getOrderDate());
        //  properties like status and shipping address can be set here if needed
        order.setShipping_Address(orderRequest.getShippingAddress());
        order.setStatus("Shipping");
        order.setUserId(orderRequest.getUserId());
        // Save the order to the database
        orderRepository.save(order);
    }
    private BigDecimal calculateTotalPrice(List<OrderItem> orderItemsList) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (OrderItem orderItem : orderItemsList) {
            totalPrice = totalPrice.add(orderItem.getSubTotal());
        }
        return totalPrice;
    }
}
