package com.rucz.ShopV8.menu.service;

import com.rucz.ShopV8.admin.dto.AdminDto;
import com.rucz.ShopV8.admin.model.Admin;
import com.rucz.ShopV8.menu.dto.CustomerDto;
import com.rucz.ShopV8.menu.model.Customer;

public interface CustomerService {
    Customer findByUsername(String username);

    Customer save(CustomerDto customerDto);
}
