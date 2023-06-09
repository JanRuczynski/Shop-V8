package com.rucz.ShopV8.menu.repository;

import com.rucz.ShopV8.admin.model.Admin;
import com.rucz.ShopV8.menu.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByUsername(String username);
}
