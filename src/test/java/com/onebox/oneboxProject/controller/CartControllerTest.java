package com.onebox.oneboxProject.controller;

import com.onebox.oneboxProject.model.Cart;
import com.onebox.oneboxProject.model.Product;
import com.onebox.oneboxProject.service.CartServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CartControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CartServiceImpl cartService;

    @InjectMocks
    private CartController cartController;

    private Cart cart;
    private Product product;
    private UUID cartId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cartController).build();

        cartId = UUID.randomUUID();
        cart = new Cart();
        product = new Product( "Test Product", 100.0);
    }

    @Test
    void createCart_success() throws Exception {
        when(cartService.createCart()).thenReturn(cart);

        mockMvc.perform(post("/api/carts/new"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(cart.getId().toString()));
    }

    @Test
    void getCart_success() throws Exception {
        when(cartService.getCartById(cartId)).thenReturn(cart);

        mockMvc.perform(get("/api/carts/get/{cartId}", cartId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(cart.getId().toString()));
    }

    @Test
    void addProduct_success() throws Exception {
        when(cartService.addProductToCart(any(UUID.class), any(Product.class))).thenReturn(cart);

        mockMvc.perform(post("/api/carts/add/{cartId}/products", cartId)
                        .contentType("application/json")
                        .content("{\"id\":\"" + product.getId() + "\", \"name\":\"Test Product\", \"price\":100.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(cart.getId().toString()));
    }

    @Test
    void deleteCart_success() throws Exception {
        mockMvc.perform(delete("/api/carts/delete/{cartId}", cartId))
                .andExpect(status().isNoContent());
    }
}
