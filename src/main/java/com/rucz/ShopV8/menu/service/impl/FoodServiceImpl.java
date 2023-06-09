package com.rucz.ShopV8.menu.service.impl;

import com.rucz.ShopV8.menu.dto.FoodDto;
import com.rucz.ShopV8.menu.model.Category;
import com.rucz.ShopV8.menu.model.Food;
import com.rucz.ShopV8.menu.repository.CategoryRepository;
import com.rucz.ShopV8.menu.repository.FoodRepository;
import com.rucz.ShopV8.menu.service.FoodService;
import com.rucz.ShopV8.menu.utils.ImageUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ImageUpload imageUpload;

    @Override
    public List<FoodDto> findAll() {
        List<Food> foodList = foodRepository.findAll();
        List<FoodDto> foodDtoList = toDto(foodList);
        return foodDtoList;
    }

    @Override
    public Food save(MultipartFile foodImage, FoodDto foodDto) {
        try {
            Food food;
            if (foodDto.getId() != null) {
                food = foodRepository.findById(foodDto.getId()).orElseThrow(ChangeSetPersister.NotFoundException::new);
            } else {
                food = new Food();
            }

            if (foodImage == null) {
                food.setImage(null);
            } else {
                if (imageUpload.uploadImage(foodImage)) {
                    System.out.println("Image uploaded successfully");
                }
                food.setImage(Base64.getEncoder().encodeToString(foodImage.getBytes()));
            }

            food.setName(foodDto.getName());
            food.setPrice(foodDto.getPrice());
            food.setDescription(foodDto.getDescription());
            food.setInStock(foodDto.isInStock());
            food.setCategory(foodDto.getCategory());
            food.setActivated(foodDto.isActivated());

            return foodRepository.save(food);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteById(Long id) {
        foodRepository.deleteById(id);
    }

    @Override
    public FoodDto getById(Long id) {
        return null;
    }

    @Override
    public Page<FoodDto> foodPage(int pageNo) {
        return null;
    }

    @Override
    public Page<FoodDto> searchProducts(int pageNo, String keyword) {
        return null;
    }

    @Override
    public List<Food> getAllFood() {
        return null;
    }

    @Override
    public List<Food> listViewProducts() {
        return null;
    }

    @Override
    public Food getFoodById(Long id) {
        return foodRepository.findById(id).get();
    }

    @Override
    public List<Food> getFoodsInCategory(Long categoryId) {
        return foodRepository.getProductsInCategory(categoryId);
//        return null;
    }

    @Override
    public List<Food> getRelatedProducts(Long categoryId) {
        return null;
    }

    @Override
    public List<Food> filterHighPrice() {
        return null;
    }

    @Override
    public List<Food> filterLowPrice() {
        return null;
    }

    private List<FoodDto> toDto(List<Food> foods){
        List<FoodDto> foodDtoList = new ArrayList<>();
        for(Food food : foods) {
            FoodDto foodDto = new FoodDto();
            foodDto.setId(food.getId());
            foodDto.setName(food.getName());
            foodDto.setDescription(food.getDescription());
            foodDto.setPrice(food.getPrice());
            foodDto.setInStock(food.isInStock());
            foodDto.setActivated(food.isActivated());
            foodDto.setImage(food.getImage());
            foodDto.setCategory(food.getCategory());
            foodDtoList.add(foodDto);
        }
        return foodDtoList;
    }

    @Override
    public void changeEnabledById(Long id) {
        Food food = getFoodById(id);
        if(food.isActivated()) {
            food.setActivated(false);
        } else {
            food.setActivated(true);
        }
        foodRepository.save(food);
    }

    @Override
    public void changeInStockById(Long id) {
        Food food = getFoodById(id);
        if(food.isInStock()) {
            food.setInStock(false);
        } else {
            food.setInStock(true);
        }
        foodRepository.save(food);
    }
}
