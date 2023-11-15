package com.example.onlinebookstore.service.order;

import com.example.onlinebookstore.dto.order.CreateOrderRequestDto;
import com.example.onlinebookstore.dto.order.OrderDto;
import com.example.onlinebookstore.dto.order.OrderItemDto;
import com.example.onlinebookstore.dto.order.UpdateOrderRequestDto;
import com.example.onlinebookstore.exception.CreateOrderException;
import com.example.onlinebookstore.exception.EntityNotFoundException;
import com.example.onlinebookstore.mapper.OrderItemMapper;
import com.example.onlinebookstore.mapper.OrderMapper;
import com.example.onlinebookstore.model.Order;
import com.example.onlinebookstore.model.OrderItem;
import com.example.onlinebookstore.model.ShoppingCart;
import com.example.onlinebookstore.repository.OrderItemRepository;
import com.example.onlinebookstore.repository.OrderRepository;
import com.example.onlinebookstore.repository.ShoppingCartRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;

    @Override
    @Transactional
    public OrderDto createOrder(Authentication authentication, CreateOrderRequestDto requestDto) {
        ShoppingCart shoppingCart = shoppingCartRepository
                .findByUserEmail(authentication.getName())
                .orElseThrow(() ->
                        new EntityNotFoundException("Can not find existing cart")
                );
        if (shoppingCart.size() == 0) {
            throw new CreateOrderException("Cannot create order from empty cart");
        }
        Order order = orderMapper.toOrder(shoppingCart, requestDto);
        shoppingCart.clear();
        return orderMapper.toDto(orderRepository.save(order));
    }

    @Override
    public List<OrderDto> findAll(Authentication authentication, Pageable pageable) {
        return orderRepository.findAllByUserEmail(authentication.getName(), pageable)
                .stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    public OrderDto getById(Authentication authentication, Long id) {
        Order order = orderRepository.findByIdAndUserEmail(id, authentication.getName())
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Order with id " + id + " does not exist")
                );
        return orderMapper.toDto(order);
    }

    @Override
    public OrderItemDto getOrderItemById(Authentication authentication, Long orderId, Long id) {
        OrderItem orderItem = orderItemRepository
                .findByIdAndOrderAndUser(id, orderId, authentication.getName())
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Item with id " + id + " does not exist in order with "
                                        + orderId + " id")
                );
        return orderItemMapper.toDto(orderItem);
    }

    @Override
    public OrderDto update(Long id, UpdateOrderRequestDto requestDto) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find order with id=%d".formatted(id))
                );
        order.setStatus(requestDto.status());
        return orderMapper.toDto(orderRepository.save(order));
    }
}
