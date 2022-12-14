package com.invoice.api.dto;

/*
 * Requerimiento 3
 * Agregar atributos de clase para la validación del producto
 */
public class DtoProduct {
    private String gtin;

    private Integer stock;

    private Integer price;

    public String getGtin(){
        return this.gtin;
    }

    public Integer getStock(){
        return this.stock;
    }

    public Integer getPrice(){
        return this.price;
    }

    public void setGtin(String gtin){
        this.gtin=gtin;
    }

    public void setStock(Integer stock){
        this.stock=stock;
    }

    public void setPrice(Integer price){
        this.price=price;
    }
}
