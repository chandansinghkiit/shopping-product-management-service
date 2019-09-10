package com.mystyle.product.management.service;

import java.util.List;

import com.mystyle.product.management.model.Product;
import com.mystyle.product.management.model.Transaction;

public interface ProductService {
    List<Product> allProduct();

    List<Product> filterProductByIdList(List<Long> idList);

    List<Product> filterProducts(String content);

    List<Transaction> filterTransactionsOfUser(Long userId);

    List<Transaction> filterTransactionsOfProduct(Long productId);

    void saveTransaction(Transaction transaction);

    Product findProductById(Long productId);
}