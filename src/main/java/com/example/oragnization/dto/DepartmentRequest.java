package com.example.oragnization.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentRequest {
	
	@Positive(message = "dept should be positive")
	@Min(value=1, message="it should start with 1 not 0")
	@Max(value=8, message="only 6 is the last")
	private int deptId;
	@NotBlank
	private String deptName;
}
