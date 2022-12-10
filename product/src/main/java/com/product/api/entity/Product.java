package com.product.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Clase entidad de productos.
 */
@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("product_id")
	@Column(name = "product_id")
	private Integer product_id;
	
	@JsonProperty("gtin")
	@Column(name = "gtin")
	@NotNull(message="gtin is required")
	private String gtin;
	
	@JsonProperty("product")
	@Column(name = "product")
	@NotNull(message="product is required")
	private String product;
	
	@JsonProperty("description")
	@Column(name = "description")
	private String description;
	
	@JsonProperty("price")
	@Column(name = "price")
	@NotNull(message="price is required")
	@Min(value=0, message="price must be positive")
	private Double price;
	
	@JsonProperty("stock")
	@Column(name = "stock")
	@NotNull(message="stock is required")
	@Min(value=1, message="stock must be greater than 0")
	private Integer stock;
	
	@JsonProperty("category_id")
	@Column(name = "category_id")
	@NotNull(message="category_id is required")
	private Integer category_id;
	
	@JsonIgnore
	@Column(name = "status")
	@Min(value=0, message="status must be 0 or 1")
	@Max(value=1, message="status must be 0 or 1")
	private Integer status;
	
	@Transient 
	@JsonProperty("category")
	private Category category;

    /**
     * Getter del produtc_id
     * @return product_id
     */
	public Integer getProduct_id() {
		return product_id;
	}

    /**
     * Setter del id de un producto.
     * @param product_id nuevo id de un producto. 
     */
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}

    /**
     * Getter del código Gtin
     * @return gtin
     */
	public String getGtin() {
		return gtin;
	}

    /**
     * Setter del código Gton de un producto.
     * @param gtin nuevo código Gtin de un producto. 
     */
	public void setGtin(String gtin) {
		this.gtin = gtin;
	}

    /**
     * Getter del nombre del producto
     * @return nombre del producto
     */
	public String getProduct() {
		return product;
	}

    /**
     * Setter del nombre de un producto.
     * @param product nuevo nombre de un producto. 
     */
	public void setProduct(String product) {
		this.product = product;
	}

    /**
     * Getter de la descripción del producto
     * @return descripción del producto.
     */
	public String getDescription() {
		return description;
	}

    /**
     * Setter de la descripción de un producto.
     * @param descripcion nueva descripción de un producto. 
     */
	public void setDescription(String description) {
		this.description = description;
	}

    /**
     * Getter del precio del producto.
     * @return precio del producto.
     */
	public Double getPrice() {
		return price;
	}

    /**
     * Setter del precio de un producto.
     * @param precio nuevo precio de un producto. 
     */
	public void setPrice(Double price) {
		this.price = price;
	}

    /**
     * Getter del stock del producto.
     * @return stock del producto.
     */
	public Integer getStock() {
		return stock;
	}

    /**
     * Setter del stock de un producto.
     * @param stock nueva cantidad del stock para un producto. 
     */
	public void setStock(Integer stock) {
		this.stock = stock;
	}

    /**
     * Getter del identificador de la categoría a la que pertenece el producto.
     * @return identificador de la categoría a la que pertenece el producto.
     */
	public Integer getCategory_id() {
		return category_id;
	}

    /**
     * Setter del id de la categoría a la que pertenece el producto.
     * @param category_id nuevo id de la categoría a la que pertenecera el producto. 
     */
	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}

    /**
     * Getter del status del producto.
     * @return status del producto.
     */
	public Integer getStatus() {
		return status;
	}

    /**
     * Setter del status del producto.
     * @param status nuevo status para el producto. 
     */
	public void setStatus(Integer status) {
		this.status = status;
	}

    /**
     * Getter de la categoría a la que pertenece el producto.
     * @return categoría a la que pertenece el producto.
     */
	public Category getCategory() {
		return category;
	}

    /**
     * Setter de la categoría a la que pertenece el producto.
     * @param category categoría a actualizar.
     */
	public void setCategory(Category category) {
		this.category = category;
	}
}
