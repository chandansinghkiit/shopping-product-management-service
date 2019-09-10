/**
 * 
 */
package com.mystyle.product.management.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mystyle.product.management.model.Product;

/**
 * @author chandan
 *
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

	
	
	@Query("select p from Product p where 1=1 AND (lower(p.productName) like lower(:pText) or lower(p.productModel) like lower(:pText) ) ")
	  List<Product> filterProducts(@Param("pText") String text);

	
	 @Query("Select p from Product p where p.id in (:pIdList)")
	  List<Product> filterProductByIdList(@Param("pIdList")List<Long> idList);
	  
	
	   

}