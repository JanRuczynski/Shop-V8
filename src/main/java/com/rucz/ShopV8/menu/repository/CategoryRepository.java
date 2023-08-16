package com.rucz.ShopV8.menu.repository;

import com.rucz.ShopV8.menu.dto.CategoryDto;
import com.rucz.ShopV8.menu.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c WHERE c.activated = true AND c.deleted = false")
    List<Category> findAllByActivated();

//    @Query("SELECT NEW com.rucz.ShopV8.menu.dto.CategoryDto(c.id, c.name, count(f.category.id)) " +
//            "FROM Category c INNER JOIN Food f on f.category.id = c.id " +
//            "WHERE c.activated = true GROUP BY c.id")
//    List<CategoryDto> getCategoryAndFood();

    @Query("SELECT c FROM Category c WHERE c.deleted = false")
    List<Category> findAll();
}
