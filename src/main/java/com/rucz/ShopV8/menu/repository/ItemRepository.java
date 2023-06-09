package com.rucz.ShopV8.menu.repository;

import com.rucz.ShopV8.menu.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("SELECT i FROM Item i WHERE i.active = true")
    List<Item> findAllByActivated();
}
