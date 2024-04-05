package com.CartService.service;
import com.CartService.dto.CartRequest;
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

    public Cart addItemToCart(CartRequest cartItemRequest) {
        Cart cartItem = mapCartItemRequestToCartItem(cartItemRequest);
        return cartRepository.save(cartItem);
    }

    public void removeItemFromCart(int cartId) {
        cartRepository.deleteById(cartId);
    }

    private Cart mapCartItemRequestToCartItem(CartRequest cartRequest){
        Cart cart = new Cart();
        cart.setCartId(cartRequest.getUserId());
        cart.setQuantity(cartRequest.getQuantity());
        cart.setUserId(cartRequest.getUserId());
        cart.setProductId(cartRequest.getProductId());
        return cart;
    }
}

