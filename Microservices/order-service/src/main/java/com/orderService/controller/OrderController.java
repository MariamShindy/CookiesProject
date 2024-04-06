package com.orderService.controller;

import com.orderService.dto.OrderRequest;
import com.orderService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private  OrderService orderService;
    @Autowired
    private RestTemplate restTemplate;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest , Principal principal){
        int userId = Integer.parseInt(principal.getName());
        orderRequest.setUserId(userId);
        orderService.placeOrder(orderRequest);
        restTemplate.delete("http://localhost:9090/api/cart/removeItemsFromCartToOrder/{userId}", orderRequest.getUserId());
        return "Order Placed Successfully";
    }

}
