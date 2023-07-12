package com.rucz.ShopV8.menu.repository;

import com.rucz.ShopV8.menu.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {

    @Query(value = "SELECT f FROM Food f INNER JOIN f.category c WHERE c.id = ?1 AND f.isActivated = true AND f.deleted = false")
    List<Food> getProductsInCategory(Long categoryId);

    @Query(value = "SELECT id FROM Food WHERE IS_ACTIVATED = true AND DELETED = false AND RECOMMENDED = true")
    List<Food> findAllByRecommended();
}
