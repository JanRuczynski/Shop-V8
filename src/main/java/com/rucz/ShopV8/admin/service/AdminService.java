package com.rucz.ShopV8.admin.service;

import com.rucz.ShopV8.admin.dto.AdminDto;
import com.rucz.ShopV8.admin.model.Admin;

public interface AdminService {
    Admin findByUsername(String username);

    Admin save(AdminDto adminDto);

    void deleteAllById(Iterable<Long> ids);
}
