package org.jsp.userfood.controller;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.hibernate.service.spi.Manageable;
import org.jsp.userfood.dao.FoodDao;
import org.jsp.userfood.dao.UserDao;
import org.jsp.userfood.dto.Food;
import org.jsp.userfood.dto.User;

public class User_Food_Controller 
{
	static Scanner sc=new Scanner(System.in);
	static UserDao uDao=new UserDao();
	static FoodDao fDao=new FoodDao();
	
	public static void main(String[] args) {
		while(true) {
		System.out.println("=== Welcome to Food Tab =====");
		System.out.println("1.save User\n2.update user\n3.Find User by id\n4.Verify user by phone and password\n5.verify user by email and password");
		System.out.println("6.delete user\n7.order food\n8.update order\n9.find rder by user id\n10.cancel order\n11.exit");
		System.out.println("enter ur option");
		switch(sc.nextInt())
		{
		case 1:
			saveUser();
			break;
		case 2:
			upadteUser();
			break;
		case 3:
			findUserById();
			break;
		case 4:
			verifyUserByPhonePassword();
			break;
		case 5:
			verifyUserByEmailPassword();
			break;
		case 6:
			deleteUser();
			break;
		case 7:
			orderFood();
			break;
		case 8:
			updateOrder();
			break;
		case 9:
			findOrderByUserId();
			break;
		case 10:
			cancelOrder();
			break;
		case 11:
			System.out.println("Thank, Have a nice Day!!!");
			System.exit(0);
			break;
			
		}
		}
	}
	public static void cancelOrder()
	{
		System.out.println("Enter order id to cancel the order");
		int f_id=sc.nextInt();
		if(fDao.cancelOrder(f_id))
			System.out.println("Order cancelled successfully");
		else
			System.err.println("No orders found with entered order id");
	}
	public static void findOrderByUserId()
	{
		System.out.println("enter user to find order details");
		int id=sc.nextInt();
		List<Food> orders=fDao.findOrderByUserId(id);
		if(orders.size()>0)
		{
			orders.forEach(f->{System.out.println(f.getId()+"\n"+f.getItem_name()+"\n"+f.getAdderss()+"\n"+f.getCost());});
		}
		else
			System.err.println("No orders found with entered user id");
	}
	public static void updateOrder()
	{
		System.out.println("enter food id");
		int id=sc.nextInt();
		Food f=fDao.findFoodByid(id);
		System.out.println("enter name,address and cost");
		f.setItem_name(sc.next());
		f.setAdderss(sc.next());
		f.setCost(sc.nextDouble());
		f=fDao.upadateOrder(f);
		if(f!=null)
		System.out.println("order updated");
		else
			System.err.println("invalid inputs");
	}
	public static void saveUser()
	{
		User u=new User();
		System.out.println("enter user name,email,phone and password");
		u.setName(sc.next());
		u.setEmail(sc.next());
		u.setPhone(sc.nextLong());
		u.setPassword(sc.next());
		u=uDao.saveUser(u);
		if(u!=null)
		{
			System.out.println("user saved with id: "+u.getId());
		}
		else
			System.err.println("invalid inputs");
		
	}
	public static void upadteUser()
	{
		User u=new User();
		System.out.println("enter user id");
		u.setId(sc.nextInt());
		System.out.println("enter user name,email,phone nd password");
		u.setName(sc.next());
		u.setEmail(sc.next());
		u.setPhone(sc.nextLong());
		u.setPassword(sc.next());
		u=uDao.updateUser(u);
		if(u!=null)
			System.out.println("user update with id: "+u.getId());
		else
			System.err.println("Invlid inputs");    
		
	}
	public static void findUserById()
	{
		System.out.println("enter user id to fetch User");
		int id=sc.nextInt();
		User u=uDao.findUserById(id);
		if(u!=null)
			System.out.println(u.getId()+"\n"+u.getName()+"\n"+u.getEmail());
		else
			System.err.println("User not found with entered Id");
	}
	public static void verifyUserByPhonePassword()
	{
		System.out.println("enter phone and password to verify user");
		long phone=sc.nextLong();
		String password=sc.next();
		User u=uDao.verifyUser(phone, password);
		if(u!=null)
			System.out.println(u.getId()+"\n"+u.getName()+"\n"+u.getEmail());
		else
			System.err.println("User not found with entered Id");
	}

	public static void verifyUserByEmailPassword()
	{
		System.out.println("enter phone and password to verify user");
		String email=sc.next();
		String password=sc.next();
		User u=uDao.verifyUser(email, password);
		if(u!=null)
			System.out.println(u.getId()+"\n"+u.getName()+"\n"+u.getEmail());
		else
			System.err.println("User not found with entered Id");
	}
	public static void deleteUser()
	{
		System.out.println("enter id to delete user");
		int id=sc.nextInt();
		if(uDao.deleteUser(id))
			System.out.println("user deleted successfully");
		else
			System.err.println("Invalid user id");
		
	}
	public static void orderFood()
	{
		Food f=new Food();
		System.out.println("enter user id to order food");
		int id=sc.nextInt();
		System.out.println("enter food name,address,cost");
		f.setItem_name(sc.next());
		f.setAdderss(sc.next());
		f.setCost(sc.nextDouble());
		f=fDao.orderFood(f, id);
		if(f!=null)
			System.out.println("Order placed with id: "+f.getId());
		else
			System.out.println("Invalid inputs");
	}

}
