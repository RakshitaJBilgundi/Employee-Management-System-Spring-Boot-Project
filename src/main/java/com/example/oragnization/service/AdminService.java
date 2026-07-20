package com.example.oragnization.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.oragnization.entity.Admin;
import com.example.oragnization.repository.AdminRepository;

@Service
public class AdminService {
	private final PasswordEncoder passwordEncoder;

    private final AdminRepository adminRepository;

    public AdminService(
            PasswordEncoder passwordEncoder,
            AdminRepository adminRepository) {

        this.passwordEncoder = passwordEncoder;
        this.adminRepository = adminRepository;
    }

    public Admin save(Admin admin) {

        admin.setPassword(
                passwordEncoder.encode(admin.getPassword())
        );

        return adminRepository.save(admin);
    }
}
