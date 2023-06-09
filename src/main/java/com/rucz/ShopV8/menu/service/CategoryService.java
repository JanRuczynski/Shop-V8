package com.rucz.ShopV8.menu.service;

import com.rucz.ShopV8.menu.dto.CategoryDto;
import com.rucz.ShopV8.menu.model.Category;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

public interface CategoryService {

    List<CategoryDto> findAll();
    Category save(MultipartFile imageCategory, CategoryDto categoryDto);
    Category findById(Long id);
    Category update(Category category);
    void deleteById(Long id);
    void changeEnabledById(Long id);
    List<Category> findAllByActivated();

    List<CategoryDto> getCategoryAndProduct();
}
