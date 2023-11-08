package com.example.onlinebookstore.service.shopping.cart;

import com.example.onlinebookstore.dto.cart.items.CreateCartItemRequestDto;
import com.example.onlinebookstore.dto.shopping.cart.CartItemUpdateRequestDto;
import com.example.onlinebookstore.dto.shopping.cart.ShoppingCartDto;

public interface ShoppingCartService {
    ShoppingCartDto findById(Long id);

    ShoppingCartDto addToCart(Long id, CreateCartItemRequestDto requestDto);

    ShoppingCartDto update(Long userId, Long cartItemId, CartItemUpdateRequestDto quantityDto);

    ShoppingCartDto removeCartItem(Long userId, Long cartItemId);
}
