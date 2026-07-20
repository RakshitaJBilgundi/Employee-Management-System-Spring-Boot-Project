package com.example.oragnization;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {

    public static void main(String[] args) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String hash = "$2a$10$B7tAATlZ1iJ4.3/29YF6R.HJQ8ozYkdnTHpwX8dQz6mWl2wgGpP1m";

        System.out.println(encoder.matches("Adi", hash));
    }
}
