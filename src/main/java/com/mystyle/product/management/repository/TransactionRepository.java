/**
 * 
 */
package com.mystyle.product.management.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mystyle.product.management.model.Transaction;

/**
 * @author chandan
 *
 */
@Transactional
@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
	

	
	     @Query("select t from Transaction t where t.userId = :pUserId ")
	     List<Transaction> findAllTransactionsOfUser(@Param("pUserId")final Long userId);
	     @Query("select t from Transaction t where t.productId = :pProductId ")
	     List<Transaction> findAllTransactionsOfProduct(@Param("pProductId")final Long productId);
	
}
