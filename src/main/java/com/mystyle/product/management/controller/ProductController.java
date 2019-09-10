package com.mystyle.product.management.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mystyle.product.management.intercomm.LogClient;
import com.mystyle.product.management.intercomm.UserClient;
import com.mystyle.product.management.model.Transaction;
import com.mystyle.product.management.service.ProductService;

@RestController
@RequestMapping("/service")
public class ProductController {

	@Autowired
	private UserClient userClient;

	@Autowired
	private LogClient logClient;

	@Autowired
	private ProductService productService;

	@Autowired
	private DiscoveryClient discoveryClient;

	@Autowired
	private Environment env;

	@Value("${spring.application.name}")
	private String serviceId;

	@GetMapping("/service/port")
	public String getPort() {
		return "Service is working at port : " + env.getProperty("local.server.port");
	}

	@GetMapping("/service/instances")
	public ResponseEntity<?> getInstances() {
		return ResponseEntity.ok(discoveryClient.getInstances(serviceId));
	}

	@PostMapping("/user")
	public ResponseEntity<?> filterTransactions(@RequestBody Long userId) {
		return new ResponseEntity<>(productService.filterTransactionsOfUser(userId), HttpStatus.OK);
	}

	@GetMapping("/popular")
	public ResponseEntity<?> popularProducts() {
		List<Long> popularIdList = logClient.getPopularProduct();
		if (popularIdList == null || popularIdList.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return new ResponseEntity<>(productService.filterProductByIdList(popularIdList), HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<?> allProducts() {
		return new ResponseEntity<>(productService.allProduct(), HttpStatus.OK);
	}

	@PostMapping("/filter")
	public ResponseEntity<?> filterProducts(@RequestBody String text) {
		return new ResponseEntity<>(productService.filterProducts(text), HttpStatus.OK);
	}

	@PostMapping("/enroll")
	public ResponseEntity<?> saveTransaction(@RequestBody Transaction transaction) {
		transaction.setDateOfPurchase(LocalDateTime.now());
		transaction.setProductId(productService.findProductById(transaction.getProductId().getId()));
		productService.saveTransaction(transaction);
		return ResponseEntity.ok(transaction);
	}

	@PostMapping("/users")
	public ResponseEntity<?> findProductOfUsers(@RequestBody Long productId) {
		List<Transaction> list = productService.filterTransactionsOfProduct(productId);
		if (list != null && !list.isEmpty()) {
			List<Long> userIdList = list.parallelStream().map(t -> t.getUserId()).collect(Collectors.toList());
			List<String> users = userClient.getUsers(userIdList);
			return ResponseEntity.ok(users);
		}
		return ResponseEntity.notFound().build();
	}

}