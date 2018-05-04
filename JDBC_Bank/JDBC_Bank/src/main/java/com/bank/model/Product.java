package com.bank.model;

import java.io.Serializable;

public class Product implements Serializable{

	private static final long serialVersionUID = -8513692159013423934L;
	private int product_id;
	private String product_name;
	private double product_price;
	private int product_quantity;
	
	public Product(){}
	
	public Product(String product_name) {
		this.product_name = product_name;
	}

	public Product(String product_name, double product_price, int product_quantity) {
		this.product_name = product_name;
		this.product_price = product_price;
		this.product_quantity = product_quantity;
	}
	
	public Product(int product_id, String product_name, double product_price, int product_quantity) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.product_price = product_price;
		this.product_quantity = product_quantity;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public double getProduct_price() {
		return product_price;
	}

	public void setProduct_price(double product_price) {
		this.product_price = product_price;
	}

	public int getProduct_quantity() {
		return product_quantity;
	}

	public void setProduct_quantity(int product_quantity) {
		this.product_quantity = product_quantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + product_id;
		result = prime * result + ((product_name == null) ? 0 : product_name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(product_price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + product_quantity;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (product_id != other.product_id)
			return false;
		if (product_name == null) {
			if (other.product_name != null)
				return false;
		} else if (!product_name.equals(other.product_name))
			return false;
		if (Double.doubleToLongBits(product_price) != Double.doubleToLongBits(other.product_price))
			return false;
		if (product_quantity != other.product_quantity)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [product_id=" + product_id + ", product_name=" + product_name + ", product_price="
				+ product_price + ", product_quantity=" + product_quantity + "\n]";
	}
	
	
	
	

}