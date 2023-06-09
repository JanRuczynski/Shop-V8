package com.rucz.ShopV8.menu.service;

import com.rucz.ShopV8.menu.dto.OrderDto;
import com.rucz.ShopV8.menu.model.Order;

import java.math.BigDecimal;

public interface OrderService {

    Order save(OrderDto orderDto);

    BigDecimal getTotalCartPrice(OrderDto order);
}
