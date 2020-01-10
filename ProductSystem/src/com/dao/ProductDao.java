package com.dao;

import java.util.List;

import com.pojo.Catagory;
import com.pojo.Product;
import com.utils.DatabaseUtils;

public class ProductDao {
	//添加商品
	public Product addP(Product product) {
		String sql = "Insert into product(p_name,price,stock,unit_of_measurement,number,uploadtime,sellstatus,c_id)  VALUES (?,?,?,?,?,?,?,?)";
		int id = DatabaseUtils.executeInsert(sql, product.getpName(), product.getPrice(), product.getStock(),product.getUnitOfMeasurement(), product.getNumber(), product.getUploadtime(), product.getSellstatus(),product.getcId());
		product.setId(id);
		return product;

	}
//按类别查找商品
	public void selectToCate(String a) {
		String sql1 = "select * from catagory where c_name = ?";
		Catagory cate = DatabaseUtils.executeQueryOne(Catagory.class, sql1, a);
//		System.out.println(cate.toString());
		String sql2 = "select*from product where c_id = ?";
		List<Product> p = DatabaseUtils.executeQuery(Product.class, sql2, cate.getId());
		for (Product product2 : p) {
			System.out.println(product2);
		}
	}
	//删除商品
	public int delectP(int a){
		String sql = "Delete from product where id = ?";
		int result = DatabaseUtils.executeUpdate(sql, a);
		return result;
	}
	//按库存查找
	public void selectByStock(int num){
		String sql = "select*from product where stock < ?";
		List<Product> p = DatabaseUtils.executeQuery(Product.class, sql, num);
		for (Product product2 : p) {
			System.out.println(product2);
		}
	}
	//销量降序
	public void selectOrderBynum(){
		String sql = "select*from product order by num desc";
		List<Product> p = DatabaseUtils.executeQuery(Product.class, sql);
		for (Product product2 : p) {
			System.out.println(product2);
		}
	}
	//单价降序
	public void selectOrderByprice(){
		String sql = "select*from product order by price desc";
		List<Product> p = DatabaseUtils.executeQuery(Product.class, sql);
		for (Product product2 : p) {
			System.out.println(product2);
		}
	}
	//分页
	public void selectByLimit(int page){
		String sql = "select*from product limit ?,3";
		List<Product> p = DatabaseUtils.executeQuery(Product.class, sql,(page-1)*3);
		for (Product product : p) {
			System.out.println(product);
		}
		
	}
	//修改分类
	public int updateProductToCate(int a,int b){
		String sql1 = "select*from catagory ";
		List<Catagory> c = DatabaseUtils.executeQuery(Catagory.class, sql1);
		for (Catagory catagory : c) {
			System.out.println(catagory);
		}
		String sql2 = "update product set c_id = ? where id =?";
		int result = DatabaseUtils.executeUpdate(sql2,b,a);
		return result;
	}
	//修改状态
	public int updateP(int a,int b){
		String sql = "update product set sellstatus = ? where id = ?";
		int result = DatabaseUtils.executeUpdate(sql,b,a);
		return result;
	}
//	#####################################################################3
	//添加分类
	public Catagory addC(Catagory cata){
		String sql = "insert into catagory(c_name,ordernumber,c_instraction)values(?,?,?)";
		int id = DatabaseUtils.executeInsert(sql, cata.getcName(),cata.getOrdernumber(),cata.getcInstraction());
		cata.setId(id);
		return cata;
	}
	//查看所有分类
	public void selectC(){
		String sql = "select*from catagory";
		List<Catagory> cata = DatabaseUtils.executeQuery(Catagory.class, sql);
		for (Catagory catagory : cata) {
			System.out.println(catagory);
		}
	}
	//修改排序
	public int selectByON(int a,int b){
		String sql1 = "update catagory set ordernumber=? where id = ?";
		int result = DatabaseUtils.executeUpdate(sql1,b,a);
		String sql2 = "select*from catagory order by ordernumber asc";
		List<Catagory> cata = DatabaseUtils.executeQuery(Catagory.class, sql2);
		for (Catagory catagory : cata) {
			System.out.println(catagory);
		} 
		return result;
	}
	//删除指定分类
	public int deleteC(int a){
		String sql = "delete from catagory where id = ?";
		int result = DatabaseUtils.executeUpdate(sql, a);
		return result;
	}
}
