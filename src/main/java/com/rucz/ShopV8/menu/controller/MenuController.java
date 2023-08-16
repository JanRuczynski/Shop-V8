package com.rucz.ShopV8.menu.controller;

import com.rucz.ShopV8.admin.model.Admin;
import com.rucz.ShopV8.admin.repository.AdminRepository;
import com.rucz.ShopV8.menu.dto.ItemDto;
import com.rucz.ShopV8.menu.dto.OrderDto;
import com.rucz.ShopV8.menu.model.Category;
import com.rucz.ShopV8.menu.model.Food;
import com.rucz.ShopV8.menu.model.Item;
import com.rucz.ShopV8.menu.repository.CategoryRepository;
import com.rucz.ShopV8.menu.repository.FoodRepository;
import com.rucz.ShopV8.menu.repository.ItemRepository;
import com.rucz.ShopV8.menu.repository.OrderRepository;
import com.rucz.ShopV8.menu.service.impl.CategoryServiceImpl;
import com.rucz.ShopV8.menu.service.impl.FoodServiceImpl;
import com.rucz.ShopV8.menu.service.impl.ItemServiceImpl;
import com.rucz.ShopV8.menu.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@Controller
public class MenuController {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemServiceImpl itemService;

    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private FoodServiceImpl foodService;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private AdminRepository adminRepository;

    @GetMapping("/menu")
    public String menu(Model model) {
        List<Food> foodList = foodRepository.findAll();
        List<Category> categoryList = categoryRepository.findAllByActivated();
        List<Food> recommended = foodRepository.findAllByRecommended();
        model.addAttribute("recommended", recommended);
        model.addAttribute("foods", foodList);
        model.addAttribute("food", new Food());
        model.addAttribute("categories", categoryList);
        model.addAttribute("category", new Category());
        return "menu";
    }

    @GetMapping("/show-category/{id}")
    public String showCategory(@PathVariable("id")Long id, Model model) {
        Category category = categoryRepository.findById(id).get();
        List<Food> foodsInCategory = foodService.getFoodsInCategory(id);
        model.addAttribute("category", category);
        model.addAttribute("newCategory", new Category());
        model.addAttribute("item", new ItemDto());
        model.addAttribute("foods", foodsInCategory);
        model.addAttribute("food", new Food());
        return "show-category";
    }

    @GetMapping("/cart")
    public String cart(Model model) {
        List<Item> items = itemRepository.findAllByActivated();
        model.addAttribute("item", new Item());
        model.addAttribute("items", items);
        model.addAttribute("totalCartPrice", itemService.getTotalActivatedCartPrice());
        model.addAttribute("order", new OrderDto());
        return "cart";
    }

    @PostMapping("/show-category/save-item")
    public String saveItem(@ModelAttribute("item") ItemDto itemDto,
                           RedirectAttributes attributes,
                           @RequestParam("categoryId") Long categoryId,
                           @RequestParam("foodId") Long foodId,
                           Principal principal) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Admin admin = adminRepository.findByUsername(authentication.getName());
        itemDto.setAdmin(admin);



        if (itemDto.getAdmin() == null) {
            attributes.addAttribute("notLoggedIn", true);
        } else if (principal != null) {
            Food food = foodRepository.findById(foodId).get();
            itemDto.setFood(food);

            itemDto.setName(food.getName());

            itemDto.setPrice(food.getPrice());
            itemDto.setTotalPrice(food.getPrice().multiply(BigDecimal.valueOf(itemDto.getQuantity())));

            System.out.println(admin.getFirstName());
            System.out.println(itemDto.getFood().getName());
            try {
                itemService.save(itemDto);
                attributes.addFlashAttribute("success", "Item added successfully");
            } catch (Exception e) {
                e.printStackTrace();
                attributes.addFlashAttribute("error", "Failed to add item.");
            }
        }
        return "redirect:/show-category/" + categoryId;
    }

    @PostMapping("/delete-item/{id}")
    public String deleteItem(@PathVariable("id")Long id, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        if (itemRepository.existsById(id)) {
            System.out.println("lele lelelele");
            Item item = itemRepository.findById(id).get();
            System.out.println(item.getName());
            item.setActive(false);
            itemRepository.save(item);
        }
        return "redirect:/cart";
    }

    @PostMapping("/cart/send-order")
    public String sendOrder(@ModelAttribute("order") OrderDto orderDto,
                            Principal principal,
                            RedirectAttributes attributes) {
        if (principal == null) {
            return "/login";
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Admin admin = adminRepository.findByUsername(authentication.getName());
        List<Long> itemsIds = itemRepository.findAll().stream()
                .map(Item::getId).toList();

        orderDto.setAdmin(admin);
        orderDto.setItemsInOrderIds(itemsIds);
        orderDto.setTotalCartPrice(itemService.getTotalActivatedCartPrice());

        if (principal != null) {
            try {
                orderService.save(orderDto);
                attributes.addFlashAttribute("success", "Item added successfully");
            } catch (Exception e) {
                e.printStackTrace();
                attributes.addFlashAttribute("error", "Failed to add item.");
            }
        }
        return "redirect:/cart";
    }
}
