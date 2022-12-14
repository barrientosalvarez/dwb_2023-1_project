package com.invoice.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import com.invoice.api.entity.Invoice;

@Repository
public interface RepoInvoice extends JpaRepository<Invoice, Integer>{

	List<Invoice> findByRfcAndStatus(String rfc, Integer status);

    /* Firma del método necesario para asignar un invoice_id a los items sin primero
     * haber guardado un invoice en base de datos. La idea es iterar sobre todas las
     * facturas (activas o inactivas) y, como el invoice_id es autoincrementado, el
     * invoice_id de una nueva factura siempre será el valor de la última factura 
     * mas 1.
     */
    @Query(value="SELECT * FROM invoice", nativeQuery=true)
    List<Invoice> getInvoice();
}
