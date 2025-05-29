package com.example.supplychain.entity;

import com.example.supplychain.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
//@Table(name = "Haarish")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

	public void setEmail(String email2, String email) {
		this.email = email;
		
	}

	public void setName(String name2, String name) {
		this.name = name;
	   
		
	}

	public void setRole(Object object, Role role) {
		this.role = role;
		
	}

	public String getPassword() {
		return password;
	
	}

	public void setPassword(String encode, String password) {
		this.password = password;
		
	}
}
