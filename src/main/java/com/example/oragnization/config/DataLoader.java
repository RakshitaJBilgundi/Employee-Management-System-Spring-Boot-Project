/*
 * package com.example.oragnization.config;
 * 
 * import org.springframework.boot.CommandLineRunner; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.security.crypto.password.PasswordEncoder;
 * 
 * import com.example.oragnization.entity.Admin; import
 * com.example.oragnization.repository.AdminRepository;
 * 
 * @Configuration public class DataLoader {
 * 
 * @Bean CommandLineRunner runner(AdminRepository adminRepository,
 * PasswordEncoder passwordEncoder) {
 * 
 * return args -> {
 * 
 * Admin admin = adminRepository.findByUserName("Aditya");
 * 
 * admin.setPassword(passwordEncoder.encode("Adi"));
 * 
 * adminRepository.save(admin);
 * 
 * System.out.println("Password Updated Successfully"); }; } }
 */