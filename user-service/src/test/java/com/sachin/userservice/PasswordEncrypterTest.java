package com.sachin.userservice;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncrypterTest {
	
	public static void main(String [] args) throws Exception {
		
		String encodedPassword = "$2a$10$o2uYQ4X.LcTM1iV2PXm7NO5XamtwTpEexLFFIBCWc8y321awEpkJi";
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
		String password = encoder.encode("password");
		System.out.println("password --> "+password);
		
		System.out.println(encoder.matches("password", encodedPassword));
	}

}
