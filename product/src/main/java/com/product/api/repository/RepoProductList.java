package com.product.api.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.product.api.dto.DtoProductList;

public interface RepoProductList extends JpaRepository<DtoProductList, Integer>{
	@Query(value="SELECT * FROM product WHERE category_id= :category_id", nativeQuery=true)
	List<DtoProductList> findByCategory_id(@Param("category_id") Integer category_id);
}
