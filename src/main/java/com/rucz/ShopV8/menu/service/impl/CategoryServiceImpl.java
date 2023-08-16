package com.rucz.ShopV8.menu.service.impl;

import com.rucz.ShopV8.menu.dto.CategoryDto;
import com.rucz.ShopV8.menu.model.Category;
import com.rucz.ShopV8.menu.model.Food;
import com.rucz.ShopV8.menu.repository.CategoryRepository;
import com.rucz.ShopV8.menu.service.CategoryService;
import com.rucz.ShopV8.menu.utils.ImageUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ImageUpload imageUpload;

    @Override
    public List<CategoryDto> findAll() {
        List<Category> categories = categoryRepository.findAll();
        return transfer(categories);
    }

    @Override
    public Category save(MultipartFile imageCategory, CategoryDto categoryDto) {
        try {
            Category category;
            if (categoryDto.getId() != null) {
                category = categoryRepository.findById(categoryDto.getId())
                        .orElseThrow(ChangeSetPersister.NotFoundException::new);
            } else {
                category = new Category();
            }

            if (imageCategory == null) {
                category.setImage(null);
            } else {
                if (imageUpload.uploadImage(imageCategory)) {
                    System.out.println("Image uploaded successfully");
                }
                category.setImage(Base64.getEncoder().encodeToString(imageCategory.getBytes()));
            }

            category.setName(categoryDto.getName());
            category.setActivated(categoryDto.isActivated());

            return categoryRepository.save(category);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public Category update(Category category) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        Category category = categoryRepository.findById(id).get();
        category.setDeleted(true);
        categoryRepository.save(category);
    }

    @Override
    public void changeEnabledById(Long id) {
        Category category = findById(id);
        if(category.isActivated()) {
            category.setActivated(false);
        } else {
            category.setActivated(true);
        }
        categoryRepository.save(category);
//        TODO: make it change all food in category enabled/disabled
    }

    @Override
    public List<Category> findAllByActivated() {
        return categoryRepository.findAllByActivated();
    }

    @Override
    public List<CategoryDto> getCategoryAndProduct() {
        return null;
    }

    private List<CategoryDto> transfer(List<Category> categories){
        List<CategoryDto> categoriesDtoList = new ArrayList<>();
        for(Category category : categories){
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(category.getId());
            categoryDto.setName(category.getName());
            categoryDto.setActivated(category.isActivated());
            categoryDto.setImage(category.getImage());
            categoriesDtoList.add(categoryDto);
        }
        return categoriesDtoList;
    }
}
