package com.product.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.product.api.entity.Category;
import java.util.List;

import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;

/**
 * Repositorio para interactuar con la base de datos 'category'
 */
@Repository
public interface RepoCategory extends JpaRepository<Category, Integer>{

    /**
     * Regresa una lista con las categorías almacenadas en la base de datos 
     * 'category'.
     * @return una lista con las categorías almacenadas en la base de datos category.
     */
    @Query(value="SELECT * FROM category WHERE status= :status", nativeQuery=true)
    List<Category> findByStatus(@Param("status") Integer status);

    /**
     * Regresa una categoría en específico almacenada en la base de datos 'category'
     * buscada por el nombre de la categoría.
     * @return una categoría en específico almacenadas en la base de datos category.
     */
    @Query(value="SELECT * FROM category WHERE category= :category", nativeQuery=true)
    Category findByCategory(@Param("category") String category);

    /**
     * Regresa una categoría en específico almacenada en la base de datos 'category'
     * buscada por el id de la categoría.
     * @return una categoría en específico almacenadas en la base de datos category.
     */
    @Query(value="SELECT * FROM category WHERE category_id= :category_id AND status=1", nativeQuery=true)
    Category findByCategoryId(@Param("category_id") Integer category_id);
    
    /**
     * Crea una nueva categoría en la base de datos.
     */
    @Modifying
    @Transactional
    @Query(value="INSERT INTO category (category,status) VALUES(:category,1)", nativeQuery=true)
    void createCategory(@Param("category") String category);

    /**
     * Actualiza una categoria en especifico.
     */
    @Modifying
    @Transactional
    @Query(value="UPDATE category SET category= :category WHERE category_id= :category_id", nativeQuery=true)
    Integer updateCategory(@Param("category_id") Integer category_id, @Param("category") String category);

    /**
     * Cambia el status de una categoria de 0 a 1.
     */
    @Modifying
    @Transactional
    @Query(value="UPDATE category SET status= 1 WHERE category_id= :category_id", nativeQuery=true)
    Integer activateCategory(@Param("category_id") Integer category_id);

    /**
     * Cambia el status de una categoria de 1 a 0.
     */
    @Modifying
    @Transactional
    @Query(value="UPDATE category SET status= 0 WHERE category_id= :category_id", nativeQuery=true)
    void deleteById(@Param("category_id") Integer category_id);
}
