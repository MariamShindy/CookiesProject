package com.WishlistService.controller;
import com.WishlistService.dto.WishlistRequest;
import com.WishlistService.model.Wishlist;
import com.WishlistService.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {
    @Autowired
    private WishlistService wishlistService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<Wishlist>> getUserWishlist(@PathVariable int userId) {
        List<Wishlist> wishlist = wishlistService.getUserWishlist(userId);
        return ResponseEntity.ok(wishlist);
    }

    @PostMapping("/addItemToWishlist")
    public ResponseEntity<Wishlist> addItemToWishlist(@RequestBody Wishlist wishlistItem) {
        Wishlist addedItem = wishlistService.addItemToWishlist(wishlistItem);
        return ResponseEntity.ok(addedItem);
    }

    @DeleteMapping("/{wishlistId}")
    public ResponseEntity<Void> removeItemFromWishlist(@PathVariable int wishlistId) {
        wishlistService.removeItemFromWishlist(wishlistId);
        return ResponseEntity.noContent().build();
    }
}
