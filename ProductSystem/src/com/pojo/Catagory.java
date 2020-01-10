package com.pojo;

public class Catagory {
	private int id;
	private String cName;
	private int ordernumber;
	private String cInstraction;

	public Catagory(int id, String cName, int ordernumber, String cInstraction) {
		super();
		this.id = id;
		this.cName = cName;
		this.ordernumber = ordernumber;
		this.cInstraction = cInstraction;
	}

	public Catagory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getcName() {
		return cName;
	}

	public void setCName(String cName) {
		this.cName = cName;
	}

	public int getOrdernumber() {
		return ordernumber;
	}

	public void setOrdernumber(int ordernumber) {
		this.ordernumber = ordernumber;
	}

	public String getcInstraction() {
		return cInstraction;
	}

	public void setCInstraction(String cInstraction) {
		this.cInstraction = cInstraction;
	}

	@Override
	public String toString() {
		return "Catagory [id=" + id + ", cName=" + cName + ", ordernumber=" + ordernumber + ", cInstraction="
				+ cInstraction + "]";
	}

}
