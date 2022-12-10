package com.product.api.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;

/**
 * Clase que nos permite modelar categorías.
 * @author Jorge Miguel Aaron Barrientos Alvarez
 * @since septiembre/2022
 */

@Entity
@Table(name = "categorias")
public class Category{
    
    /** Valor entero que servira como identificador para las categorías.  */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="category_id")
    private Integer category_id;

    /** Cadena que guardará el nombre de las categorías. */
    @NotNull
    @Column(name="category")
    private String category;

    /** Valor entero que servirá como estatus para las categorías.  */
    @Column(name="status")
    @Min(value=0, message="status most be 0 or 1")
    @Max(value=1, message="status most be 0 or 1")
    private Integer status;

    /**
     * Constructor de Categorías.
     * @param category_id identificador de la categoría.
     * @param category nombre de la categoría.
     * @param status estatus de la categoría.
     */
    /*public Category(Integer category_id, String category, Integer status){
        this.category_id=category_id;
        this.category=category;
        this.status=status;
    }*/
    
    public Category(){}

    /**
     * Getter del identificador de las categorías.
     * @return el identificador de la categoría que invocó el método.
     */
    public Integer getCategory_id(){
        return this.category_id;
    }

    /**
     * Getter del nombre de las categorías.
     * @return el nombre de la categoria que invocó el método.
     */
    public String getCategory(){
        return this.category;
    }

    /**
     * Getter del estatus de las categorías.
     * @return el estatus de la categoría que invocó el método.
     */
    public Integer getStatus(){
        return this.status;
    }

    @Override
    public String toString(){
        return "{category_id="+category_id+", category="+category+", status="+status+"}";
    }

    @Override
    //algo anda mal
    public boolean equals(Object o){
        if(this==o)
            return true;

        if(o==null || this.getClass()!=o.getClass())
            return false;

        Category c = (Category) o;
        
        if(this.getCategory_id()!=c.getCategory_id())
            return false;

        if(this.getCategory()!=c.getCategory())
            return false;

        if(this.getStatus()!=c.getStatus())
            return false;

        return true;
    }
        
}
