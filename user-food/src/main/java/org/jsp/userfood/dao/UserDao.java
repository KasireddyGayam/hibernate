package org.jsp.userfood.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jsp.userfood.dto.User;


public class UserDao 
{
	EntityManager manager=Persistence.createEntityManagerFactory("jpa").createEntityManager();
	public User saveUser(User u)
	{
	  EntityTransaction t=manager.getTransaction();
	  manager.persist(u);
	  t.begin();
	  t.commit();
	  return u;
	}
	public User updateUser(User u)
	{
		manager.merge(u);
		EntityTransaction t=manager.getTransaction();
		t.begin();
		t.commit();
		return u;

	}
	public User findUserById(int u_id)
	{
		User u=manager.find(User.class, u_id);
		if(u!=null) {
		manager.merge(u);
		EntityTransaction t=manager.getTransaction();
		t.begin();
		t.commit();
		return u;
		}
		return null;
	}
	public User verifyUser(String email,String password)
	{
		Query q=manager.createQuery("select u from User u where u.email=?1 and u.password=?2");
		q.setParameter(1, email);
		q.setParameter(2, password);
		try {
			return (User)q.getSingleResult();
		}
		catch(NoResultException e)
		{ 
			return null;
		}
	}
	
	public User verifyUser(long phone,String password)
	{
		Query q=manager.createQuery("select u from User u where u.phone=?1 and u.password=?2");
		q.setParameter(1, phone);
		q.setParameter(2, password);
		try {
			return (User)q.getSingleResult();
		}
		catch(NoResultException e)
		{ 
			return null;
		}
		
	}
	public boolean deleteUser(int u_id)
	{
		User u=manager.find(User.class, u_id);
		if(u!=null)
		{
		manager.remove(u);
		EntityTransaction t=manager.getTransaction();
		t.begin();
		t.commit();
		return true;
		}
		return false;
		
	}

}
