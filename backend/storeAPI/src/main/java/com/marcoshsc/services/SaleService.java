package com.marcoshsc.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcoshsc.domain.Product;
import com.marcoshsc.domain.Sale;
import com.marcoshsc.domain.SaleItem;
import com.marcoshsc.exceptions.InvalidStock;
import com.marcoshsc.exceptions.NullField;
import com.marcoshsc.repos.ProductRepository;
import com.marcoshsc.repos.SaleRepository;

@Service
public class SaleService {
	
	@Autowired
	private SaleRepository salesRepo;
	
	@Autowired
	private ProductRepository productRepo;
	
	public Sale add(Sale sale) throws NullField {
		sale.checkIrregularities();
		Sale s =  salesRepo.save(sale);
		for(SaleItem si: sale.getSaleItems()) {
			Product p = si.getProduct();
			try {
				p.removeStock(si.getQuantity());
			} catch(InvalidStock ivdstck) {
				System.out.println("Not enough stock");
			}
			productRepo.save(p);
		}
		return s;
	}
	
	public Sale remove(Long id) throws NoSuchElementException {
		Sale dbSale = salesRepo.findById(id).get();
		for(SaleItem si: dbSale.getSaleItems()) {
			Product p = si.getProduct();
			try {
				p.addStock(si.getQuantity());
			} catch(InvalidStock ivdstck) {
				System.out.println("negative stock");
			}
			productRepo.save(p);
		}
		salesRepo.delete(dbSale);
		return dbSale;
	}
	
	public Sale update(Long id, Sale sale) throws NoSuchElementException, NullField {
		sale.checkIrregularities();
		Sale dbSale = salesRepo.findById(id).get();
		dbSale.setClient(sale.getClient());
		dbSale.setDate(sale.getDate());
		for(SaleItem si: sale.getSaleItems()) {
			si.setSale(dbSale);
		}
		Set<SaleItem> paramSaleItemsSet = new HashSet<SaleItem>(sale.getSaleItems());
		Set<SaleItem> dbSaleItemsSet = new HashSet<SaleItem>(dbSale.getSaleItems());
		dbSaleItemsSet.addAll(paramSaleItemsSet);
		dbSale.getSaleItems().clear();
		dbSale.getSaleItems().addAll(dbSaleItemsSet);
		return salesRepo.save(dbSale);
	}
	
	public List<Sale> getAll() {
		return salesRepo.findAll();
	}
	
	public Sale getById(Long id) throws NoSuchElementException {
		return salesRepo.findById(id).get();
	}
	
}
