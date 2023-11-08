package com.example.onlinebookstore.dto.shopping.cart;

import com.example.onlinebookstore.dto.cart.items.CartItemDto;
import java.util.Set;

public record ShoppingCartDto(
        Long id,
        Long userId,
        Set<CartItemDto> cartItems
) {
}
