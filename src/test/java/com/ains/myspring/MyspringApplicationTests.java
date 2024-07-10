package com.ains.myspring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ains.myspring.services.AdministrationService;

@SpringBootTest
class MyspringApplicationTests {
	@Autowired
	private AdministrationService _service;

	@Test
	void contextLoads() {
	}

	@Test
	void TestAdminEmail() {
		_service.getAdminByEmail("andyrakotonavalona@gmail.com");
	}
}
