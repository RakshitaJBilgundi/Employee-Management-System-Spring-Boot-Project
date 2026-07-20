package com.example.oragnization.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.oragnization.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

	Admin findByUserName(String userName);
}
