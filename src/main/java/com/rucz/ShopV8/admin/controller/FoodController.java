package com.rucz.ShopV8.admin.controller;

import com.rucz.ShopV8.menu.dto.CategoryDto;
import com.rucz.ShopV8.menu.dto.FoodDto;
import com.rucz.ShopV8.menu.model.Category;
import com.rucz.ShopV8.menu.model.Food;
import com.rucz.ShopV8.menu.repository.FoodRepository;
import com.rucz.ShopV8.menu.service.impl.CategoryServiceImpl;
import com.rucz.ShopV8.menu.service.impl.FoodServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class FoodController {

    @Autowired
    private FoodServiceImpl foodService;

    @Autowired
    private CategoryServiceImpl categoryService;

    @Autowired
    private FoodRepository foodRepository;

    @ModelAttribute("food")
    public FoodDto getFood() {
        return new FoodDto();
    }

    @GetMapping("manage-food")
    public String foods(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        List<FoodDto> foodDtoList = foodService.findAll();
        List<Category> categoryList = categoryService.findAllByActivated();
        model.addAttribute("foods", foodDtoList);
        model.addAttribute("categories", categoryList);
        model.addAttribute("size", foodDtoList.size());
        return "manage-food";
    }

    @PostMapping("manage-food/save-food")
    public String saveFood(@ModelAttribute("food")FoodDto foodDto,
                           @RequestParam("imageFood")MultipartFile imageFood,
                           RedirectAttributes attributes) {
        try {
            foodService.save(imageFood, foodDto);
            attributes.addFlashAttribute("success", "Food added successfully");
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("error", "Failed to add food.");
        }
        return "redirect:/manage-food";
    }

    @PostMapping("/delete-food/{id}")
    public String deleteFood(@PathVariable("id")Long id, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        if (foodRepository.existsById(id)) {
            foodService.deleteById(id);
        }
        return "redirect:/manage-food";
    }

    @GetMapping("/update-food/{id}")
    public String updateFood(@PathVariable("id") Long id, Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        List<FoodDto> foodDtoList = foodService.findAll();
        List<Category> categoryList = categoryService.findAllByActivated();
        Food food = foodRepository.findById(id).get();
        model.addAttribute("foods", foodDtoList);
        model.addAttribute("food", food);
        model.addAttribute("categories", categoryList);

        return "manage-food";
    }

    @RequestMapping(value = "/change-enabled-food/{id}", method = {RequestMethod.PUT, RequestMethod.GET})
    public String enableFood(@PathVariable("id")Long id, RedirectAttributes attributes) {
        try {
            foodService.changeEnabledById(id);
            attributes.addFlashAttribute("success", "Enabled successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("error", "Failed to enable.");
        }
        return "redirect:/manage-food";
    }

    @RequestMapping(value = "/change-inStock-food/{id}", method = {RequestMethod.PUT, RequestMethod.GET})
    public String changeInStock(@PathVariable("id")Long id, RedirectAttributes attributes) {
        try {
            foodService.changeInStockById(id);
            attributes.addFlashAttribute("success", "Enabled successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("error", "Failed to enable.");
        }
        return "redirect:/manage-food";
    }
}
