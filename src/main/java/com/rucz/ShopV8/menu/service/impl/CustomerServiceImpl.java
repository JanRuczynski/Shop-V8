package com.rucz.ShopV8.menu.service.impl;

import com.rucz.ShopV8.admin.dto.AdminDto;
import com.rucz.ShopV8.admin.model.Admin;
import com.rucz.ShopV8.admin.repository.RoleRepository;
import com.rucz.ShopV8.menu.dto.CustomerDto;
import com.rucz.ShopV8.menu.model.Customer;
import com.rucz.ShopV8.menu.repository.CategoryRepository;
import com.rucz.ShopV8.menu.repository.CustomerRepository;
import com.rucz.ShopV8.menu.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Customer findByUsername(String username) {
        return customerRepository.findByUsername(username);
    }

    @Override
    public Customer save(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setUsername(customerDto.getUsername());
        customer.setPassword(customerDto.getPassword());
        customer.setRoles(Arrays.asList(roleRepository.findByName("ADMIN")));
        return customerRepository.save(customer);
    }
}
