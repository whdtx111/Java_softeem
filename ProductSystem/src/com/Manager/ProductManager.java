package com.Manager;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.dao.ProductDao;
import com.pojo.Catagory;
import com.pojo.Product;
import com.service.ProductService;

public class ProductManager {
	private Scanner sc;
	private ProductService ps;
	private ProductDao pd;

	public ProductManager() {
		this.sc = new Scanner(System.in);
		this.ps = new ProductService();
		this.pd = new ProductDao();
		Mainmeau();
	}

	@SuppressWarnings("deprecation")
	private void Mainmeau() {
		System.out.println("###########主商品菜单##############");
		System.out.println("1.添加商品");
		System.out.println("2.查询指定分类的商品信息");
		System.out.println("3.查询库存量低于5的商品信息");
		System.out.println("4.查询所有商品按照销量从高到低排序");
		System.out.println("5.查询所有商品按照单价从高到低排序");
		System.out.println("6.分页查询所有商品，每页显示5条记录");
		System.out.println("7.修改商品分类");
		System.out.println("8.修改商品状态");
		System.out.println("9.删除商品");
		System.out.println("0.退出");
		String a = sc.next();
		switch (a) {
		case "1":
			Product p = new Product();
			System.out.println("请输入要添加的商品信息。");
			System.out.print("请输入商品名：");
			p.setPName(sc.next());
			System.out.print("请输入商品价格：");
			p.setPrice(Double.parseDouble(sc.next()));
			System.out.print("请输入商品库存：");
			p.setNumber(sc.nextInt());
			System.out.print("请输入商品单位：");
			p.setUnitOfMeasurement(sc.next());
			System.out.print("请输入商品分类：");
			p.setCId(sc.nextInt());
			p.setUploadtime(new Date());
			p.setSellstatus(0);
			p.setNumber(0);
			if (ps.addP(p)) {
				System.out.println("商品添加成功！");
			} else {
				System.out.println("商品添加失败！");
			}
			Mainmeau();
			break;
		case "2":
			System.out.println("输入要查询的分类名：");
			pd.selectToCate(sc.next());
			Mainmeau();
			break;
		case "3":
			System.out.println("输入要查询的库存量:");
			pd.selectByStock(sc.nextInt());
			Mainmeau();
			break;
		case "4":
			System.out.println("查询结果：");
			pd.selectOrderBynum();
			Mainmeau();
			break;
		case "5":
			System.out.println("查询结果：");
			pd.selectOrderByprice();
			Mainmeau();
			break;
		case "6":
			int page =1;
			pd.selectByLimit(page);
			while(true){
				System.out.println("上一页：0，下一页：1");
				int a1 = sc.nextInt();
				switch(a1){
				case 0:
					page--;
					pd.selectByLimit(page);
					break;
				case 1:
					page++;
					pd.selectByLimit(page);
					break;
				}
			}
			
		case "7":
			System.out.println("输入修改的编号：");
			int a1 = sc.nextInt();
			System.out.println("输入要给定的分类号:");
			int b = sc.nextInt();
			pd.updateProductToCate(a1, b);
			Mainmeau();
			break;
		case "8":
			System.out.println("输入修改的编号：");
			int a2 = sc.nextInt();
			System.out.println("输入修改的状态:");
			int b1 = sc.nextInt();
			pd.updateP(a2, b1);
			Mainmeau();
			break;
		case "9":
			System.out.println("输入要删除的商品编号:");
			pd.delectP(sc.nextInt());
			System.out.println("删除成功！");
			Mainmeau();
			break;
		}
	}
	private void CataMeau(){
		System.out.println("##########分页菜单###########");
		System.out.println("1.添加分类");
		System.out.println("2.查看所有分类");
		System.out.println("3.修改分类排序");
		System.out.println("4.删除分类");
		String m = sc.next();
		switch(m){
		case "1":
			Catagory c= new Catagory();
			System.out.println("请输入分类名");
			c.setCName(sc.next());
			System.out.println("请输入排序号");
			c.setOrdernumber(sc.nextInt());
			System.out.println("请输入介绍");
			c.setCInstraction(sc.next());
			if(ps.addC(c)){
				System.out.println("添加成功！");
			}else{
				System.out.println("添加失败！");
			}
			CataMeau();
			break;
		case "2":
			System.out.println("查询结果>>>");
			pd.selectC();
			CataMeau();
			break;
		case "3":
			System.out.println("输入要修改类的编号：");
			int a = sc.nextInt();
			System.out.println("输入新类的排序号：");
			int b = sc.nextInt();
			pd.selectByON(a, b);
			CataMeau();
			break;
		case "4":
			System.out.println("输入要删除类的编号：");
			pd.deleteC(sc.nextInt());
			CataMeau();
			break;
		}
	}
}
