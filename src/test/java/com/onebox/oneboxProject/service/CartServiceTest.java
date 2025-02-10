package com.onebox.oneboxProject.service;

import com.onebox.oneboxProject.model.Cart;
import com.onebox.oneboxProject.model.Product;
import com.onebox.oneboxProject.repository.CartRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartService cartService;

    public CartServiceTest() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void addProductToCart_ShouldAddProductWhenCartExists_success() {
        UUID cartId = UUID.randomUUID();
        Cart existingCart = new Cart();
        Product product = new Product("Test Product", 10.0);

        when(cartRepository.findById(cartId)).thenReturn(Optional.of(existingCart));
        when(cartRepository.save(existingCart)).thenReturn(existingCart);

        Cart updatedCart = cartService.addProductToCart(cartId, product);

        assertNotNull(updatedCart);
        assertTrue(updatedCart.getProducts().contains(product));
        verify(cartRepository, times(1)).findById(cartId);
        verify(cartRepository, times(1)).save(existingCart);
    }

    @Test
    void addProductToCart_ShouldThrowExceptionWhenCartDoesNotExist_failure() {
        UUID cartId = UUID.randomUUID();
        Product product = new Product("Test Product", 10.0);

        when(cartRepository.findById(cartId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> cartService.addProductToCart(cartId, product));

        assertEquals("Carrito no encontrado con el ID: " + cartId, exception.getMessage());
        verify(cartRepository, times(1)).findById(cartId);
        verify(cartRepository, never()).save(any(Cart.class));
    }
}