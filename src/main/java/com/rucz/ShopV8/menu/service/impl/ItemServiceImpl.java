package com.rucz.ShopV8.menu.service.impl;

import com.rucz.ShopV8.admin.model.Admin;
import com.rucz.ShopV8.menu.dto.ItemDto;
import com.rucz.ShopV8.menu.model.Food;
import com.rucz.ShopV8.menu.model.Item;
import com.rucz.ShopV8.menu.repository.ItemRepository;
import com.rucz.ShopV8.menu.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Base64;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Item save(ItemDto itemDto) {
        try {
            Item item;
            if (itemDto.getId() != null) {
                item = itemRepository.findById(itemDto.getId()).orElseThrow(ChangeSetPersister.NotFoundException::new);
            } else {
                item = new Item();
            }

            item.setFood(itemDto.getFood());
            item.setAdmin(itemDto.getAdmin());
            item.setName(itemDto.getName());
            item.setQuantity(itemDto.getQuantity());
            item.setPrice(itemDto.getPrice());
            item.setTotalPrice(itemDto.getTotalPrice());
            item.setActive(true);

            return itemRepository.save(item);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public BigDecimal getTotalActivatedCartPrice() {
        List<Item> items = itemRepository.findAllByActivated();
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (Item item : items) {
            totalPrice = totalPrice.add(item.getTotalPrice());
        }
        return totalPrice;
    }
}
