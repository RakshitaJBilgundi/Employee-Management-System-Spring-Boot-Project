package com.example.oragnization.logger;

public class TestLogger {

    public static void main(String[] args) {

        CustomLogger.log("INFO", "Application Started");
        CustomLogger.log("INFO", "Login Successful");
        CustomLogger.log("ERROR", "Invalid Password");
    }
}