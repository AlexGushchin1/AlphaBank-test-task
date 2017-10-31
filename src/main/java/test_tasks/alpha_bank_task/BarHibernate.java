package test_tasks.alpha_bank_task;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BarHibernate {
	@Autowired
	   SessionFactory sessionFactory;
	 
	  // ...
	 
	   protected Session getCurrentSession(){
	      return sessionFactory.getCurrentSession();
	   }
}
