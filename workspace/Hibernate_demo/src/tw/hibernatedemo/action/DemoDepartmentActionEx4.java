package tw.hibernatedemo.action;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import tw.hibernatedemo.model.Department;
import tw.hibernatedemo.util.HibernateUtil;

public class DemoDepartmentActionEx4 {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Department dept2 = new Department(
					"數位發展部0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
			// 超過字數限制，無法順利新增
			session.save(dept2);
			tx.commit();

		} catch (Exception e) {
			System.out.println("回滾");
			tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSessionFactory();
		}

//		session.beginTransaction();
//		
//		Department dept2 = new Department();
//		dept2.setDeptname("餐具");
//		Serializable id = session.save(dept2);
//		System.out.println("id: "+id);
//		
//		session.getTransaction().commit();
//		factory.close();
	}

}
