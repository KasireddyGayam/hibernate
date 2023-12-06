package org.jsp.userfood.controller;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Test 
{
	EntityManager m=Persistence.createEntityManagerFactory("jpa").createEntityManager();
	

}
