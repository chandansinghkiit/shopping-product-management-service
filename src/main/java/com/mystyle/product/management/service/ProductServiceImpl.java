/**
 * 
 */
package com.mystyle.product.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mystyle.product.management.model.Product;
import com.mystyle.product.management.model.Transaction;
import com.mystyle.product.management.repository.ProductRepository;
import com.mystyle.product.management.repository.TransactionRepository;

/**
 * @author chandan
 *
 */
@Service
public class ProductServiceImpl implements ProductService{
	
	
	  @Autowired
	    private ProductRepository productRepository;

	    @Autowired
	    private TransactionRepository transactionRepository;

	@Override
	public List<Product> allProduct() {
		
		return productRepository.findAll();
	}

	@Override
	public List<Product> filterProductByIdList(List<Long> idList) {
		
		return productRepository.filterProductByIdList(idList);
	}

	@Override
	public List<Product> filterProducts(String content) {
		return productRepository.filterProducts(content);
	}

	@Override
	public List<Transaction> filterTransactionsOfUser(Long userId) {
		
		return transactionRepository.findAllTransactionsOfUser(userId);
	}

	@Override
	public List<Transaction> filterTransactionsOfProduct(Long productId) {
		
		return transactionRepository.findAllTransactionsOfProduct(productId);
	}

	@Override
	public void saveTransaction(Transaction transaction) {
		
		transactionRepository.save(transaction);
	}

	@Override
	public Product findProductById(Long productId) {
	  return productRepository.findById(productId).orElse(null);
	}

}
