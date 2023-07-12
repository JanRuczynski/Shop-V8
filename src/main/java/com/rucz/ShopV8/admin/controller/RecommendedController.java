package com.rucz.ShopV8.admin.controller;

import com.rucz.ShopV8.menu.dto.FoodDto;
import com.rucz.ShopV8.menu.model.Category;
import com.rucz.ShopV8.menu.model.Food;
import com.rucz.ShopV8.menu.repository.FoodRepository;
import com.rucz.ShopV8.menu.service.impl.FoodServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class RecommendedController {

    @Autowired
    private FoodServiceImpl foodService;

    @Autowired
    private FoodRepository foodRepository;

    @ModelAttribute("food")
    public FoodDto getFood() {
        return new FoodDto();
    }

    @GetMapping("manage-recommended")
    public String foods(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        List<FoodDto> foodDtoList = foodService.findAll();
        List<Food> recommendedList = foodRepository.findAllByRecommended();
        model.addAttribute("foods", foodDtoList);
        model.addAttribute("size", foodDtoList.size());
        model.addAttribute("recommended", recommendedList);
        return "manage-recommended";
    }

    @RequestMapping(value = "change-recommended/{id}", method = {RequestMethod.PUT, RequestMethod.GET})
    public String changeRecommended(@PathVariable("id")Long id, RedirectAttributes attributes) {
        try {
            foodService.changeRecommendedById(id);
            attributes.addFlashAttribute("success", "Changed recommended status successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("error", "Failed to change recommended status.");
        }
        return "redirect:/manage-recommended";
    }
}
