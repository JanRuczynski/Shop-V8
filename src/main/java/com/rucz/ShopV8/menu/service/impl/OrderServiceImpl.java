package com.rucz.ShopV8.menu.service.impl;

import com.rucz.ShopV8.menu.dto.OrderDto;
import com.rucz.ShopV8.menu.model.Item;
import com.rucz.ShopV8.menu.model.Order;
import com.rucz.ShopV8.menu.repository.ItemRepository;
import com.rucz.ShopV8.menu.repository.OrderRepository;
import com.rucz.ShopV8.menu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Order save(OrderDto orderDto) {
        try {
            Order order;
            if (orderDto.getId() != null) {
                order = orderRepository.findById(orderDto.getId()).orElseThrow(ChangeSetPersister.NotFoundException::new);
            } else {
                order = new Order();
            }

            order.setAdmin(orderDto.getAdmin());
            order.setItemsInOrderIds(
                    itemRepository.findAllByActivated().stream().map(Item::getId).toList()
            );
            order.setAddress(orderDto.getAddress());
            order.setMemo(orderDto.getMemo());
            order.setTotalCartPrice(orderDto.getTotalCartPrice());

            order.getItemsInOrderIds().stream()
                    .map(id -> itemRepository.findById(id))
                    .forEach(itemOptional -> itemOptional.ifPresent(item -> {
                        item.setActive(false);
                    }));

            return orderRepository.save(order);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public BigDecimal getTotalCartPrice(OrderDto order) {
        List<Item> itemsList = itemRepository.findAllById(order.getItemsInOrderIds());

        BigDecimal totalPrice = BigDecimal.ZERO;
        for (Item item : itemsList) {
            totalPrice = totalPrice.add(item.getTotalPrice());
        }
        return totalPrice;
    }

}
