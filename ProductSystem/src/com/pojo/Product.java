package com.pojo;

import java.util.Date;

public class Product {
	private int id;
	private String pName;
	private double price;
	private int stock;
	private String unitOfMeasurement;
	private int number;
	private Date uploadtime;
	private int sellstatus;
	private int cId;

	public Product(int id, String p_name, double price, int stock, String unitOfMeasurement, int number,
			Date uploadtime, int sellstatus, int cId) {
		super();
		this.id = id;
		this.pName = pName;
		this.price = price;
		this.stock = stock;
		this.unitOfMeasurement = unitOfMeasurement;
		this.number = number;
		this.uploadtime = uploadtime;
		this.sellstatus = sellstatus;
		this.cId = cId;
	}

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getpName() {
		return pName;
	}

	public void setPName(String pName) {
		this.pName = pName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getUnitOfMeasurement() {
		return unitOfMeasurement;
	}

	public void setUnitOfMeasurement(String unitOfMeasurement) {
		this.unitOfMeasurement = unitOfMeasurement;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Date getUploadtime() {
		return uploadtime;
	}

	public void setUploadtime(Date uploadtime) {
		this.uploadtime = uploadtime;
	}

	public int getSellstatus() {
		return sellstatus;
	}

	public void setSellstatus(int sellstatus) {
		this.sellstatus = sellstatus;
	}

	public int getcId() {
		return cId;
	}

	public void setCId(int cId) {
		this.cId = cId;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", pName=" + pName + ", price=" + price + ", stock=" + stock
				+ ", unitOfMeasurement=" + unitOfMeasurement + ", number=" + number + ", uploadtime=" + uploadtime
				+ ", sellstatus=" + sellstatus + ", cId=" + cId + "]";
	}

	
	

}
