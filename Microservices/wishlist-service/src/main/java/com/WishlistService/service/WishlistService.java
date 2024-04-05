package com.WishlistService.service;

import com.WishlistService.dto.WishlistRequest;
import com.WishlistService.model.Wishlist;
import com.WishlistService.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {
    @Autowired
    private WishlistRepository wishlistRepository;

    public List<Wishlist> getUserWishlist(int userId) {
        return wishlistRepository.findByUserId(userId);
    }

    public Wishlist addItemToWishlist(WishlistRequest wishlistItemRequest) {
        Wishlist wishlistItem = mapWishlistItemRequestToWishlistItem(wishlistItemRequest);
        return wishlistRepository.save(wishlistItem);
    }

    public void removeItemFromWishlist(int wishlistId) {
        wishlistRepository.deleteById(wishlistId);
    }

    private Wishlist mapWishlistItemRequestToWishlistItem(WishlistRequest wishlistItemRequest){
        Wishlist wishlist = new Wishlist();
        wishlist.setProductId(wishlistItemRequest.getProductId());
        wishlist.setUserId(wishlistItemRequest.getUserId());
        return wishlist;
    }
}
