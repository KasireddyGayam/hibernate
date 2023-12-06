package org.jsp.userfood.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jsp.userfood.dto.Food;
import org.jsp.userfood.dto.User;

public class FoodDao
{
	EntityManager manager=Persistence.createEntityManagerFactory("jpa").createEntityManager();
	
	public Food orderFood(Food f,int u_id)
	{
		User u=manager.find(User.class, u_id);
		f.setU(u);
		EntityTransaction t=manager.getTransaction();
		manager.persist(f);
		t.begin();
		t.commit();
		return f;
	}
	public Food findFoodByid(int id)
	{
		Food f=manager.find(Food.class, id);
		if(f!=null)
			return f;
		return null;
	}
	public Food upadateOrder(Food f)
	{
		EntityTransaction t=manager.getTransaction();
		t.begin();
		t.commit();
		return f;
	}
	public List<Food> findOrderByUserId(int id)
	{
		Query q=manager.createQuery("select f from Food f where f.u.id=?1");
		q.setParameter(1, id);
		return q.getResultList();
	}
	public boolean cancelOrder(int f_id)
	{
		Food f=manager.find(Food.class, f_id);
		if(f!=null) {
		manager.remove(f);
		EntityTransaction t=manager.getTransaction();
		t.begin();
		t.commit();
		return true;
		}
		return false;
		
	}

}
