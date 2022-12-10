package com.product.api.service;

import java.util.List;
import com.product.api.entity.Category;
import com.product.api.dto.ApiResponse;

public interface SvcCategory{

    List<Category> getCategories();

    Category getCategory(Integer category_id);

    ApiResponse createCategory(Category c);

    ApiResponse updateCategory(Integer category_id, Category c);

    ApiResponse deleteCategory(Integer category_id);
}
