package tw.hibernatedemo.action;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import tw.hibernatedemo.model.Department;

public class DemoDepartmentActionEx1 {

	public static void main(String[] args) {
		StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		SessionFactory factory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
		
		Session session = factory.openSession();
		session.beginTransaction();
		
		Department depart1 = new Department();
		depart1.setDeptname("HR");
		session.save(depart1);
		
		session.getTransaction().commit();
		session.close();
		factory.close();
	}

}
