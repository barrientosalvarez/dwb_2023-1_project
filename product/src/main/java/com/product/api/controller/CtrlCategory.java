package com.product.api.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.product.api.entity.Category;
import com.product.api.service.SvcCategory;
import com.product.api.dto.ApiResponse;
import com.product.exception.ApiException;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Clase controlador para las categorías.
 */
@RestController
@RequestMapping("/category")
public class CtrlCategory{

    @Autowired
    SvcCategory svc;

    /**
     * Muestra todas las categorias almacenadas en la base de datos.
     * @return un objeto ResponseEntity con la lista de categorias y un estatus 
     * positivo de http: 'OK'.
     */
    @GetMapping
    public ResponseEntity<List<Category>> getCategories() throws Exception{
        return new ResponseEntity<>(svc.getCategories(), HttpStatus.OK);
    }

    /**
     * Nos permite ver el identificador de una categoría, el identificador es 
     * recibido através del url path.
     * @param category_id identificador de la categoría a la cual se querra acceder
     * en un futuro.
     * @return un objeto ResponseEntity con el identificador recibido y un estatus
     * positivo de http: OK.
     */
    @GetMapping("/{category_id}")
    public ResponseEntity<Category> getCategory(@PathVariable Integer category_id){
        return new ResponseEntity<>(svc.getCategory(category_id), HttpStatus.OK);
    }

    /**
     * Crea una nueca categoria.
     * @param c la categoría que se quiere crear.
     * @return un objeto ResponseEntity con un mensaje y un estatus de html:
     * si la categoría que recibe el método no existe en la base de datos de categorías 
     * 'category', se regresa un estatus positivo de html (OK) y un mensaje que 
     * indica que la categoría fue creada. Si la categoría que recibe el método
     * ya existia, se regresa un estatus negativo de html (BAD_REQUEST) y un
     * mensaje que indica que la categoría que se quizo crear ya existía.
     */
    @PostMapping
    public ResponseEntity<ApiResponse> createCategory(@Valid @RequestBody Category c,
            BindingResult br){
        
        if(br.hasErrors())
            throw new ApiException(HttpStatus.BAD_REQUEST, br.getAllErrors().get(0).getDefaultMessage());

        return new ResponseEntity<>(svc.createCategory(c), HttpStatus.OK);
    }

    /**
     * Actualiza una categoría.
     * @param c la categoría que se quiere actualizar.
     * @return un objeto ResponseEntity con un mensaje y un estatus de html:
     * si la categoría que recibe el método no existe en la base de datos 
     * 'category', se regresa un status negativo de html (bad_request) y un 
     * mensaje que indica que la categoría no existe. Si la categoría que recibe 
     * el método existe, se regresa un estatus positivo de html (ok) y un
     * mensaje que indica que la categoría fue actualizada.
     */
    @PutMapping("/{category_id}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable Integer category_id,
        @Valid @RequestBody Category c, BindingResult br){

        if(br.hasErrors())
            throw new ApiException(HttpStatus.BAD_REQUEST, br.getAllErrors().get(0).getDefaultMessage());

        return new ResponseEntity<>(svc.updateCategory(category_id, c), HttpStatus.OK);
    }

    /**
     * Elimina una categoría (cambia el status de 1 a 0)
     * @param category_id el id de la categoría a eliminar, se recibe através
     * del url path.
     * @return un objeto ResponsEentity con un mensaje y un estatus de html:
     * si el identificador que recibe el método coincide con el de una categoría
     * existente, se regresa un estatus positivo de html (OK) con un mensaje que 
     * indica que la categoría fue removida. Si no se encuentra ninguna categoría
     * con el mismo identificador que recibio el método, se regresa un estatus
     * negativo de html (NOT_FOUND) con un mensaje que indica que la categoría 
     * buscada no existe.
     */
    @DeleteMapping("/{category_id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer category_id){
        return new ResponseEntity<>(svc.deleteCategory(category_id), HttpStatus.OK);
    }

}
