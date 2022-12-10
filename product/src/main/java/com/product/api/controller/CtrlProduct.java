package com.product.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.api.dto.ApiResponse;
import com.product.api.dto.DtoProductList;
import com.product.api.entity.Product;
import com.product.api.service.SvcProduct;
import com.product.exception.ApiException;

/**
 * Clase controlador para los productos.
 */
@RestController
@RequestMapping("/product")
public class CtrlProduct {

    @Autowired
    SvcProduct svc;

    // Implementacion endpoint update product category
    @PutMapping("{gtin}/category")
    public ResponseEntity<ApiResponse> updateProductCategory(@PathVariable String gtin, @Valid @RequestBody Integer category_id, BindingResult br){
        if(br.hasErrors())
            throw new ApiException(HttpStatus.BAD_REQUEST, br.getAllErrors().get(0).getDefaultMessage());
        return new ResponseEntity<>(svc.updateProductCategory(gtin, category_id), HttpStatus.OK);
    }

    // Implementacion del endpoint List Products
    @GetMapping("/category/{category_id}")
    public ResponseEntity<List<DtoProductList>> getProducts(@PathVariable Integer category_id) throws Exception{
        return new ResponseEntity<>(svc.findByCategory_id(category_id), HttpStatus.OK);
    }

    //1. Implementar metodo getProduct
    /**
     * Muestra un producto.
     * @param gtin codigo Gtin del producto que se va a mostrar.
     * @return ResponseEntity con el producto buscado y un HttpStatus.
     */
    @GetMapping("/{gtin}")
    public ResponseEntity<Product> getProduct(@PathVariable String gtin){
        return new ResponseEntity<>(svc.getProduct(gtin), HttpStatus.OK);
    }

    /**
     * Permite crear un nuevo producto.
     * @param in Json con la información del nuevo producto que será creado.
     * @param bindingResult que nos permite revisar validaciones.
     * @return ApiException si se encontraron errores, ResponseEntity correspondiente
     * a la acción de crear un nuevo producto en otro caso.
     */
    @PostMapping
    public ResponseEntity<ApiResponse> createProduct(@Valid @RequestBody Product in, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
        return new ResponseEntity<>(svc.createProduct(in),HttpStatus.OK);
    }
	
    /**
     * Actualiza un producto en especifico.
     * @param id identificador del producto a actualizar.
     * @return ApiException si se encontraron errores, ResponseEntity correspondiente
     * a la acción de actualizar un producto en otro caso.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("id") Integer id, @Valid @RequestBody Product in, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
        return new ResponseEntity<>(svc.updateProduct(in, id),HttpStatus.OK);
	}
	
    // 2. Implementar método updateProductStock
    /**
     * Actualiza el stock de un producto en especifico.
     * @param gtin codigo Gtin del producto a actualizar.
     * @param stock cantidad que le será restada al stock original.
     * @return ResponseEntity correspondiente a la acción de actualizar el stock 
     * de un producto.
     */
    @PutMapping("/{gtin}/stock/{stock}")
    public ResponseEntity<ApiResponse> updateProductStock(@PathVariable String gtin, @PathVariable Integer stock){
        return new ResponseEntity<>(svc.updateProductStock(gtin, stock), HttpStatus.OK);
    }
    
    /**
     * Cambia el status de un producto de 1 a 0.
     * @param id identificador del producto cuyo status será cambiado.
     * @return ResponseEntity correspondiente a "eliminar" un producto.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable("id") Integer id){
        return new ResponseEntity<>(svc.deleteProduct(id), HttpStatus.OK);
    }
}
