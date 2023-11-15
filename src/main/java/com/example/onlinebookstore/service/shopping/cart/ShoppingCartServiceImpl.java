package com.example.onlinebookstore.service.shopping.cart;

import com.example.onlinebookstore.dto.cart.items.CreateCartItemRequestDto;
import com.example.onlinebookstore.dto.shopping.cart.CartItemUpdateRequestDto;
import com.example.onlinebookstore.dto.shopping.cart.ShoppingCartDto;
import com.example.onlinebookstore.exception.EntityNotFoundException;
import com.example.onlinebookstore.mapper.ShoppingCartMapper;
import com.example.onlinebookstore.model.Book;
import com.example.onlinebookstore.model.CartItem;
import com.example.onlinebookstore.model.ShoppingCart;
import com.example.onlinebookstore.model.User;
import com.example.onlinebookstore.repository.BookRepository;
import com.example.onlinebookstore.repository.CartItemRepository;
import com.example.onlinebookstore.repository.ShoppingCartRepository;
import com.example.onlinebookstore.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartMapper shoppingCartMapper;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public ShoppingCartDto findById(Long id) {
        return shoppingCartMapper.toDto(shoppingCartRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Cannot find shoppingCart by id " + id)
                )
        );
    }

    @Override
    @Transactional
    public ShoppingCartDto addToCart(Long id, CreateCartItemRequestDto requestDto) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cannot find user")
        );
        ShoppingCart shoppingCart = shoppingCartRepository.findById(id)
                .orElseGet(() -> {
                    ShoppingCart shoppingCartNew = new ShoppingCart();
                    shoppingCartNew.setUser(user);
                    return shoppingCartRepository.save(shoppingCartNew);
                }
        );
        Book book = bookRepository.findById(requestDto.bookId()).orElseThrow(
                () -> new EntityNotFoundException("Cannot find book")
        );
        CartItem cartItem = cartItemRepository.findByShoppingCartIdAndBookId(
                shoppingCart.getId(), book.getId()
        ).orElseGet(() -> createCartItem(shoppingCart, book, requestDto));
        if (cartItem.getId() != null) {
            cartItem.setQuantity(cartItem.getQuantity() + requestDto.quantity());
        }
        cartItemRepository.save(cartItem);
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Override
    @Transactional
    public ShoppingCartDto update(Long userId,
                                  Long cartItemId,
                                  CartItemUpdateRequestDto quantityDto) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find shopping cart by id " + userId)
                );
        CartItem cartItem = cartItemRepository
                .findCartItemByIdAndShoppingCartId(cartItemId, shoppingCart.getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find cart item by id " + cartItemId + "for user by id " + userId)
                );
        cartItem.setQuantity(quantityDto.quantity());
        cartItemRepository.save(cartItem);
        return shoppingCartMapper.toDto(cartItem.getShoppingCart());
    }

    @Override
    @Transactional
    public ShoppingCartDto removeCartItem(Long userId, Long cartItemId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find shopping cart by id " + userId)
                );
        CartItem cartItem = cartItemRepository
                .findCartItemByIdAndShoppingCartId(cartItemId, shoppingCart.getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find cart item by id " + cartItemId + "for user by id " + userId)
                );
        cartItemRepository.delete(cartItem);
        shoppingCart.removeCartItem(cartItem);
        return shoppingCartMapper.toDto(shoppingCart);
    }

    private CartItem createCartItem(ShoppingCart cart, Book book,
                                    CreateCartItemRequestDto requestDto) {
        CartItem cartItem = new CartItem();
        cartItem.setBook(book);
        cartItem.setShoppingCart(cart);
        cartItem.setQuantity(requestDto.quantity());
        cart.getCartItems().add(cartItem);
        return cartItem;
    }
}
