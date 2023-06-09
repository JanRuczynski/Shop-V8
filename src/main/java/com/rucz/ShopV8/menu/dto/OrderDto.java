package com.rucz.ShopV8.menu.dto;

import com.rucz.ShopV8.admin.model.Admin;
import com.rucz.ShopV8.menu.model.Item;
import com.rucz.ShopV8.menu.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Long id;
    private Admin admin;
    private List<Long> itemsInOrderIds;
    private String address;
    private String memo;
    private BigDecimal totalCartPrice;
}
