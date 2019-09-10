/**
 * 
 */
package com.mystyle.product.management.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author chandan
 *
 */
@Data
@Entity
@Table(name = "product")
public class Product implements IModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "product_name")
	private String productName;
	@Column(name = "product_model")
	private String productModel;
	@Column(name = "product_quantity")
	private Integer productQuantity;
	@Column(name = "product_price")
	private BigDecimal productPrice;
	@Column(name = "created_date")
	private LocalDate createdDate;
	

}
