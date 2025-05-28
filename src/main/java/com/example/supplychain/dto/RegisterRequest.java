package com.example.supplychain.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequest {
	@NotBlank(message = "Name is required")
	private String Name;

	@Email(message = "Enter a valid email")
	@NotBlank(message = "Email is required")
	private String Email;

	@NotBlank(message = "Password is required")
	private String Password;

	@NotBlank(message = "Role is required")
	private String Role;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		this.Name = name;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getRole() {
		return Role;
	}

	public void setRole(String role) {
		this.Role = role;
	}


}
