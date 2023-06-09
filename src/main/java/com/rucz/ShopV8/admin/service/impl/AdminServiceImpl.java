package com.rucz.ShopV8.admin.service.impl;

import com.rucz.ShopV8.admin.dto.AdminDto;
import com.rucz.ShopV8.admin.model.Admin;
import com.rucz.ShopV8.admin.repository.AdminRepository;
import com.rucz.ShopV8.admin.repository.RoleRepository;
import com.rucz.ShopV8.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AdminServiceImpl implements AdminService {


    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Admin findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }

    @Override
    public Admin save(AdminDto adminDto) {
        Admin admin = new Admin();
        admin.setFirstName(adminDto.getFirstName());
        admin.setLastName(adminDto.getLastName());
        admin.setUsername(adminDto.getUsername());
        admin.setPassword(adminDto.getPassword());
        admin.setRoles(Arrays.asList(roleRepository.findByName("USER")));
        return adminRepository.save(admin);
    }

    @Override
    public void deleteAllById(Iterable<Long> ids) {
        adminRepository.deleteAllById(ids);
    }
}
