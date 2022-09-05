package tw.hibernatedemo.action;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import tw.hibernatedemo.model.Department;
import tw.hibernatedemo.util.HibernateUtil;

public class DemoDepartmentActionEx2 {

	public static void main(String[] args) {

		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		Department dept1 = new Department();
		session.beginTransaction();
		dept1.setDeptname("Sales");
		session.save(dept1);
		
		session.getTransaction().commit();
		session.close();
		factory.close();
	}

}
