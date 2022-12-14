package com.invoice.api.service;

import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.invoice.api.dto.ApiResponse;
import com.invoice.api.dto.DtoProduct;
import com.invoice.exception.ApiException;
import com.invoice.api.entity.Invoice;
import com.invoice.api.entity.Item;
import com.invoice.api.entity.Cart;
import com.invoice.api.repository.RepoInvoice;
import com.invoice.api.repository.RepoItem;
import com.invoice.api.repository.RepoCart;
import com.invoice.configuration.client.ProductClient;
import org.springframework.http.HttpStatus;

@Service
public class SvcInvoiceImp implements SvcInvoice {

	@Autowired
	RepoInvoice repo;
	
	@Autowired
	RepoItem repoItem;

    @Autowired
    RepoCart repoCart;

    @Autowired
    ProductClient productCl;

	@Override
	public List<Invoice> getInvoices(String rfc) {
		return repo.findByRfcAndStatus(rfc, 1);
	}

	@Override
	public List<Item> getInvoiceItems(Integer invoice_id) {
		return repoItem.getInvoiceItems(invoice_id);
	}

	@Override
	public ApiResponse generateInvoice(String rfc) {
		/*
		 * Requerimiento 5
		 * Implementar el método para generar una factura 
		 */
		
        
        List<Cart> carts=repoCart.findByRfcAndStatus(rfc, 1);
        if(carts==null)
            throw new ApiException(HttpStatus.NOT_FOUND, "cart not found");

        Integer id=0;
        List<Invoice> invoice = repo.getInvoice();

        for(Invoice inv : invoice){
            if(inv.getInvoice_id()>id)
                id=inv.getInvoice_id();
        }

        for(Cart cart : carts){            
            Integer price=productCl.getProduct(cart.getGtin()).getPrice();
            
            Item item = new Item();
            item.setId_invoice(id);
            item.setGtin(cart.getGtin());
            item.setQuantity(cart.getQuantity());
            item.setUnit_price(productCl.getProduct(cart.getGtin()).getPrice());
            item.setSubtotal(item.getUnit_price()*item.getQuantity());
            item.setTaxes(item.getSubtotal()*0.16);
            item.setTotal(item.getSubtotal()+(item.getSubtotal()*0.16));
            item.setStatus(1);
            repoItem.save(item);
        }

        Invoice invoice=new Invoice();
        invoice.setRfc(invoice);
        Integer subtotal=0;
        Integer total=0;
        Integer taxes=0;

        List<Item> items=repoItems.getInvoiceItems(id);
        for(Item item : items){
            subtotal+=item.getSubtotal();
            total+=item.getTotal();
            taxes+=item.getTaxes();
        }

        invoice.setSubtotal(subtotal);
        invoice.setTaxes(taxes);
        invoice.setTotal(total);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        invoice.setDate(now);
        repoCart.clearCart(rfc);
        return new ApiResponse("invoice generated");
	}

}
