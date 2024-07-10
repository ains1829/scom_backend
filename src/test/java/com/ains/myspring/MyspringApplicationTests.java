package com.ains.myspring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.ains.myspring.services.EtudiantService;
import com.ains.myspring.services.OrderService;

@SpringBootTest
class MyspringApplicationTests {
	@Autowired
	private EtudiantService _serviceEtudiant;

	@Autowired
	private OrderService _serviceOrder;

	@Test
	void contextLoads() {
	}

	@Test
	void ListEtudiantisCorrect() {
		_serviceEtudiant.getAllEtudiant();
	}

	@Test
	void ListOrdersIsCorrect() {
		_serviceOrder.getOrders();
	}
}
