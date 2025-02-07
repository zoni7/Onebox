package com.onebox.oneboxProject.controller;

import com.onebox.oneboxProject.model.Cart;
import com.onebox.oneboxProject.model.Product;
import com.onebox.oneboxProject.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts") // Prefijo para todas las rutas relacionadas con carts
public class CartRestController {

    private final CartService cartService;

    public CartRestController(CartService cartService) {
        this.cartService = cartService;
    }

    // Crear una nueva cart
    @PostMapping
    public ResponseEntity<Cart> createCart(@RequestBody Cart cart) {
        cartService.createCart(cart);
        return ResponseEntity.ok(cart);
    }

    // Obtener una cart por ID
    @GetMapping("/{cartId}")
    public ResponseEntity<Cart> getCart(@PathVariable String cartId) {
        Cart cart = cartService.getCart(cartId);
        if (cart != null) {
            return ResponseEntity.ok(cart);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Añadir un producto a una cart
    @PostMapping("/{cartId}/products")
    public ResponseEntity<String> addProductToCart(@PathVariable String cartId, @RequestBody Product product) {
        cartService.addProductToCart(cartId, product);
        return ResponseEntity.ok("Producto añadido correctamente.");
    }

    // Listar productos de una cart
    @GetMapping("/{cartId}/products")
    public ResponseEntity<List<Product>> getProductsFromCart(@PathVariable String cartId) {
        Cart cart = cartService.getCart(cartId);
        if (cart != null) {
            return ResponseEntity.ok(cart.getProducts());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un producto de una cart
    @DeleteMapping("/{cartId}/products")
    public ResponseEntity<String> removeProductFromCart(
            @PathVariable String cartId, @RequestBody Product product) {
        cartService.removeProductFromCart(cartId, product);
        return ResponseEntity.ok("Producto eliminado correctamente.");
    }
}