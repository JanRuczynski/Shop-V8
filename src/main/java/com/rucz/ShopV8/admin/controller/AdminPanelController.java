package com.rucz.ShopV8.admin.controller;

import com.rucz.ShopV8.admin.model.Admin;
import com.rucz.ShopV8.admin.repository.AdminRepository;
import com.rucz.ShopV8.menu.dto.CategoryDto;
import com.rucz.ShopV8.menu.repository.CategoryRepository;
import com.rucz.ShopV8.menu.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@Controller
public class AdminPanelController {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AdminRepository adminRepository;

    @ModelAttribute("category")
    public CategoryDto getCategory() {
        return new CategoryDto();
    }

    @GetMapping("/admin-panel")
    public String adminPanel(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        Admin admin = adminRepository.findByUsername(principal.getName());
        model.addAttribute("adminName", admin.getFirstName() + " " + admin.getLastName());
        return "admin-panel";
    }
}
