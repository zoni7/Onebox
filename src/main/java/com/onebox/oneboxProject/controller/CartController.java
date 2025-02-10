package com.onebox.oneboxProject.controller;

import com.onebox.oneboxProject.model.Cart;
import com.onebox.oneboxProject.model.Product;
import com.onebox.oneboxProject.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/carts")
@CrossOrigin("*")
public class CartController {


    @Autowired
    private CartService cartService;


    @PostMapping("/new")
    public ResponseEntity<Cart> createCart() {
        Cart cart = cartService.createCart();
        return ResponseEntity.status(HttpStatus.CREATED).body(cart);
    }

    @GetMapping("/get/{cartId}")
    public ResponseEntity<Cart> getCart(@PathVariable UUID cartId) {
        Cart cart = cartService.getCartById(cartId);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/add/{cartId}/products")
    public ResponseEntity<Cart> addProduct(@PathVariable UUID cartId, @RequestBody Product product) {
        Cart cart = cartService.addProductToCart(cartId, product);
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/delete/{cartId}")
    public ResponseEntity<Void> deleteCart(@PathVariable UUID cartId) {
        cartService.deleteCart(cartId);
        return ResponseEntity.noContent().build();
    }
}
