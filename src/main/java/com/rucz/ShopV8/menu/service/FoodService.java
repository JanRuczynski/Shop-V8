package com.rucz.ShopV8.menu.service;

import com.rucz.ShopV8.menu.dto.FoodDto;
import com.rucz.ShopV8.menu.model.Food;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FoodService {

    List<FoodDto> findAll();
    Food save(MultipartFile foodImage, FoodDto foodDto);
    void deleteById(Long id);
    FoodDto getById(Long id);
    Page<FoodDto> foodPage(int pageNo);
    Page<FoodDto> searchProducts(int pageNo, String keyword);
    void changeEnabledById(Long id);
    void changeInStockById(Long id);

    List<Food> getAllFood();
    List<Food> listViewProducts();
    Food getFoodById(Long id);

    List<Food> getFoodsInCategory(Long categoryId);
    List<Food> getRelatedProducts(Long categoryId);
    List<Food> filterHighPrice();
    List<Food> filterLowPrice();
}
