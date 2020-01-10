package com.service;

import java.util.List;

import com.dao.ProductDao;
import com.pojo.Catagory;
import com.pojo.Product;

public class ProductService {
	private ProductDao productdao;

	public ProductService() {
		this.productdao = new ProductDao();
	}

	public boolean addP(Product product) {
		Product p = productdao.addP(product);
		return p.getId() != 0 && p.getId() != 0;
	}
	public boolean addC(Catagory cata){
		Catagory c = productdao.addC(cata);
		return c.getId() != 0 && c.getId() != 0;
	}
}
