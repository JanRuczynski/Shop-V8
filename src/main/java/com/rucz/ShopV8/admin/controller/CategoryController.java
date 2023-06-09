package com.rucz.ShopV8.admin.controller;

import com.rucz.ShopV8.menu.dto.CategoryDto;
import com.rucz.ShopV8.menu.model.Category;
import com.rucz.ShopV8.menu.repository.CategoryRepository;
import com.rucz.ShopV8.menu.service.impl.CategoryServiceImpl;
import com.rucz.ShopV8.menu.utils.ImageUpload;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@Controller
public class CategoryController {
    @Autowired
    private CategoryServiceImpl categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    //----------------------------------------------------------

    @ModelAttribute("category")
    public CategoryDto getCategory() {
        return new CategoryDto();
    }

    @GetMapping("manage-categories")
    public String categories(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        List<CategoryDto> categoryDtoList = categoryService.findAll();
        model.addAttribute("categories", categoryDtoList);
        model.addAttribute("size", categoryDtoList.size());
        return "manage-categories";
    }

//    public String categoriesPage(@PathVariable("pageNo") int pageNo, Model model, Principal principal) {
//        if(principal == null) {
//            return "redirect:/login";
//        }
//    }

    @PostMapping("/manage-categories/save-category")
    public String saveCategory(@ModelAttribute("category") CategoryDto categoryDto,
                               @RequestParam("imageCategory") MultipartFile imageCategory,
                               RedirectAttributes attributes) {
        try {
            categoryService.save(imageCategory, categoryDto);
            attributes.addFlashAttribute("success", "Category added successfully");
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("error", "Failed to add category.");
        }
        return "redirect:/manage-categories";
    }

    @GetMapping("/update-category/{id}")
    public String updateCategory(@PathVariable("id") Long id, Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        List<CategoryDto> categoryDtoList = categoryService.findAll();
        model.addAttribute("categories", categoryDtoList);
        Category category = categoryRepository.findById(id).get();
        model.addAttribute("category", category);

        return "manage-categories";
    }

    @RequestMapping(value = "/change-enabled-category/{id}", method = {RequestMethod.PUT, RequestMethod.GET})
    public String enableCategory(@PathVariable("id")Long id, RedirectAttributes attributes) {
        try {
            categoryService.changeEnabledById(id);
            attributes.addFlashAttribute("success", "Enabled successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("error", "Failed to enable.");
        }
        return "redirect:/manage-categories";
    }

    @PostMapping("/delete-category/{id}")
    public String deleteCategory(@PathVariable("id")Long id, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        if (categoryRepository.existsById(id)) {
            categoryService.deleteById(id);
        }
        return "redirect:/manage-categories";
    }

}
