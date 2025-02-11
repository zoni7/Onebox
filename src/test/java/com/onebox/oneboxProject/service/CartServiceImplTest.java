package com.onebox.oneboxProject.service;

import com.onebox.oneboxProject.model.Cart;
import com.onebox.oneboxProject.model.Product;
import com.onebox.oneboxProject.repository.CartRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CartServiceImplTest {

    @Mock
    private CartRepositoryImpl cartRepository;

    @InjectMocks
    private CartServiceImpl cartService;

    public CartServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void createCart_success() {
        Cart cart = new Cart();
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);

        Cart createdCart = cartService.createCart();

        assertNotNull(createdCart);
        verify(cartRepository, times(1)).save(any(Cart.class));
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

    @Test
    void removeInactiveCarts_ShouldRemoveExpiredCarts_success() {
        Cart inactiveCart = new Cart();
        inactiveCart.setLastUpdated(Instant.now().minusSeconds(700));
        Cart activeCart = new Cart();
        activeCart.setLastUpdated(Instant.now());
        when(cartRepository.findAll()).thenReturn(Map.of(
                inactiveCart.getId(), inactiveCart,
                activeCart.getId(), activeCart
        ));

        cartService.removeInactiveCarts();

        verify(cartRepository, times(1)).delete(inactiveCart.getId());
        verify(cartRepository, never()).delete(activeCart.getId());
    }
}