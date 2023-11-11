package com.example.onlinebookstore.controller;

import com.example.onlinebookstore.dto.cart.items.CreateCartItemRequestDto;
import com.example.onlinebookstore.dto.shopping.cart.CartItemUpdateRequestDto;
import com.example.onlinebookstore.dto.shopping.cart.ShoppingCartDto;
import com.example.onlinebookstore.model.User;
import com.example.onlinebookstore.service.shopping.cart.ShoppingCartService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("api/cart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ShoppingCartDto getShoppingCart(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.findById(user.getId());
    }

    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ShoppingCartDto addBookToCart(Authentication authentication,
             @RequestBody @Valid CreateCartItemRequestDto requestDto) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.addToCart(user.getId(), requestDto);
    }

    @PutMapping(("/cart-items/{id}"))
    @PreAuthorize("hasRole('USER')")
    public ShoppingCartDto updateCartItem(Authentication authentication,
              @PathVariable @Positive Long id,
              @RequestBody @Valid CartItemUpdateRequestDto quantityRequest) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.update(user.getId(), id, quantityRequest);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping(("/cart-items/{id}"))
    public ShoppingCartDto deleteCartItem(Authentication authentication, @PathVariable Long id) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.removeCartItem(user.getId(), id);
    }
}
