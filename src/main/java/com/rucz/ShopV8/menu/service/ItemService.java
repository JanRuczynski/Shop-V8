package com.rucz.ShopV8.menu.service;

import com.rucz.ShopV8.menu.dto.ItemDto;
import com.rucz.ShopV8.menu.model.Item;

import java.math.BigDecimal;


public interface ItemService {

    Item save(ItemDto itemDto);
    BigDecimal getTotalActivatedCartPrice();
}
