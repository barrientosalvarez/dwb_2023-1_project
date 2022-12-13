package com.invoice.api.dto;

/*
 * Requerimiento 3
 * Agregar atributos de clase para la validación del producto
 */
public class DtoProduct {
    private String gtin;

    public String getGtin(){
        return this.gtin;
    }

    public void setGtin(String gtin){
        this.gtin=gtin;
    }

}
