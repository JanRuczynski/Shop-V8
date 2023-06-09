package com.rucz.ShopV8.menu.dto;

import com.rucz.ShopV8.admin.model.Admin;
import com.rucz.ShopV8.menu.model.Food;
import com.rucz.ShopV8.menu.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {

    private Long id;
    private String name;
    private Food food;
    private Admin admin;
    private Order order;
    private int quantity;
    private BigDecimal price;
    private BigDecimal totalPrice;
    private boolean isActive = true;
}
