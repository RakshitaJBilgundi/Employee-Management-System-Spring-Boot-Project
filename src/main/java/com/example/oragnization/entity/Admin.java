package com.example.oragnization.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Admin {
      @Id
      @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
      @Column(nullable=false)
	private String userName;
      @Column(nullable=false,unique=true)
	private String password;
      @Column(nullable=false)
      @Enumerated(EnumType.STRING)
      private Role role;
}
