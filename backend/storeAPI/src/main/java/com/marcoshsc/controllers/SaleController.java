package com.marcoshsc.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcoshsc.dao.ErrorDAO;
import com.marcoshsc.dao.FinalResponse;
import com.marcoshsc.dao.SaleDTO;
import com.marcoshsc.dao.SaleItemDTO;
import com.marcoshsc.domain.Client;
import com.marcoshsc.domain.Product;
import com.marcoshsc.domain.Sale;
import com.marcoshsc.domain.SaleItem;
import com.marcoshsc.exceptions.InvalidSale;
import com.marcoshsc.exceptions.NullField;
import com.marcoshsc.services.ClientService;
import com.marcoshsc.services.ProductService;
import com.marcoshsc.services.SaleService;

@RestController
@RequestMapping("/sales")
public class SaleController {
	
	@Autowired
	private SaleService saleService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired ProductService productService;
	
	@GetMapping
	public FinalResponse<? extends Object> getAllSales() {
		List<Sale> dbSales = saleService.getAll();
		return new FinalResponse<Sale>("200", dbSales);
	}
	
	@GetMapping("/{id}")
	public FinalResponse<? extends Object> getSaleById(@PathVariable("id") Long id) {
		try {
			Sale dbSale = saleService.getById(id);
			return new FinalResponse<Sale>("200", dbSale);
		} catch(NoSuchElementException exc) {
			ErrorDAO error = new ErrorDAO("Invalid ID. Sale not found.");
			return new FinalResponse<ErrorDAO>("400", error);
		} catch(Exception exc) {
			ErrorDAO error = new ErrorDAO("Unexpected Error.");
			return new FinalResponse<ErrorDAO>("400", error);
		}
	}
	
	@PostMapping
	public FinalResponse<? extends Object> addSale(@RequestBody SaleDTO data) {
		try {
			Client requestClient = clientService.find(data.getClientId());
			List<SaleItem> saleItems = new ArrayList<>();
			for(SaleItemDTO sidto: data.getSaleItems()) {
				Product p = productService.find(sidto.getProductId());
				saleItems.add(new SaleItem(p, sidto.getQuantity()));
			}
			Sale preSale = Sale.makeSale(requestClient, saleItems);
			Sale dbSale = saleService.add(preSale);
			return new FinalResponse<Sale>("201", dbSale);
		} catch (NullField | InvalidSale exc) {
			ErrorDAO error = new ErrorDAO(exc.getMessage());
			return new FinalResponse<ErrorDAO>("400", error);
		} catch(NoSuchElementException exc) {
			ErrorDAO error = new ErrorDAO("Invalid ID. Client not found. Can't make the sale.");
			return new FinalResponse<ErrorDAO>("400", error);
		} catch(Exception exc) {
			ErrorDAO error = new ErrorDAO("Unexpected Error.");
			return new FinalResponse<ErrorDAO>("400", error);
		}
	}
	
	@PutMapping("/{id}")
	public FinalResponse<? extends Object> updateSale(@PathVariable("id") Long id, @RequestBody SaleDTO data) {
		try {
			Client requestClient = clientService.find(data.getClientId());
			//Sale preSale = Sale.makeSale(requestClient, data.getSaleItems());
			//Sale dbSale = saleService.update(id, preSale);
			return new FinalResponse<Sale>("200", new Sale()/*dbSale*/);
		} //catch (InvalidSale | NullField exc) {
//			ErrorDAO error = new ErrorDAO(exc.getMessage());
//			return new FinalResponse<ErrorDAO>("400", error);
		/*}*/ catch(NoSuchElementException exc) {
			ErrorDAO error = new ErrorDAO("Invalid ID. Client/Sale not found. Can't update the sale.");
			return new FinalResponse<ErrorDAO>("400", error);
		} catch(Exception exc) {
			ErrorDAO error = new ErrorDAO("Unexpected Error.");
			return new FinalResponse<ErrorDAO>("400", error);
		}
	}
	
	@DeleteMapping("/{id}")
	public FinalResponse<? extends Object> deleteSale(@PathVariable("id") Long id) {
		try {
			Sale dbSale = saleService.remove(id);
			return new FinalResponse<Sale>("200", dbSale);
		} catch(NoSuchElementException exc) {
			ErrorDAO error = new ErrorDAO("Invalid ID. Sale not found. Can't delete the sale.");
			return new FinalResponse<ErrorDAO>("400", error);
		} catch(Exception exc) {
			ErrorDAO error = new ErrorDAO("Unexpected Error.");
			return new FinalResponse<ErrorDAO>("400", error);
		}
	}
	
}
