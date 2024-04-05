package com.CartService.service;

import com.CartService.model.Cart;
import com.CartService.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public List<Cart> getUserCart(int userId) {
        return cartRepository.findByUserId(userId);
    }

    public Cart addItemToCart(Cart cartItem) {
        return cartRepository.save(cartItem);
    }

    public void removeItemFromCart(int cartId) {
        cartRepository.deleteById(cartId);
    }
}

