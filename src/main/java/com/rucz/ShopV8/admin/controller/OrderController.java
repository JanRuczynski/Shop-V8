package com.rucz.ShopV8.admin.controller;

import com.rucz.ShopV8.menu.dto.OrderDto;
import com.rucz.ShopV8.menu.model.Item;
import com.rucz.ShopV8.menu.model.Order;
import com.rucz.ShopV8.menu.repository.ItemRepository;
import com.rucz.ShopV8.menu.repository.OrderRepository;
import com.rucz.ShopV8.menu.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemService itemService;

    @GetMapping("/manage-orders")
    public String manageOrders(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        List<Order> orders = orderRepository.findAll();
        List<Item> items = itemRepository.findAll();

        model.addAttribute("orders", orders);
        model.addAttribute("order", new Order());
        model.addAttribute("item", new Item());
        model.addAttribute("items", items);
        model.addAttribute("totalCartPrice", itemService.getTotalActivatedCartPrice());
        return "manage-orders";
    }
}
