package com.example.onlinebookstore.mapper;

import com.example.onlinebookstore.dto.order.CreateOrderRequestDto;
import com.example.onlinebookstore.dto.order.OrderDto;
import com.example.onlinebookstore.model.Order;
import com.example.onlinebookstore.model.OrderItem;
import com.example.onlinebookstore.model.ShoppingCart;
import java.math.BigDecimal;
import org.mapstruct.AfterMapping;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = OrderItemMapper.class
)
public interface OrderMapper {
    @Mapping(target = "userId", source = "user.id")
    OrderDto toDto(Order order);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderItems", source = "shoppingCart.cartItems")
    Order toOrder(ShoppingCart shoppingCart, CreateOrderRequestDto requestDto);

    @AfterMapping
    default void setOrderToItems(@MappingTarget Order order) {
        order.getOrderItems()
                .forEach(item -> item.setOrder(order));
    }

    @AfterMapping
    default void setTotalPrice(@MappingTarget Order order) {
        order.setTotal(order.getOrderItems().stream()
                .map(this::getTotalItemPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
        );
    }

    private BigDecimal getTotalItemPrice(OrderItem orderItem) {
        BigDecimal quantity = BigDecimal.valueOf(orderItem.getQuantity());
        return orderItem.getPrice().multiply(quantity);
    }
}
