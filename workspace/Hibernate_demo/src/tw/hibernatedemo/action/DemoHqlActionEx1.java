package tw.hibernatedemo.action;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import tw.hibernatedemo.model.Employee;
import tw.hibernatedemo.util.HibernateUtil;

public class DemoHqlActionEx1 {

	// 找所有資料(設條件 salary, vacation)
	public void queryAllEmployee() {

		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();

//			String hql = "from Employee"; // Employee.java 的 class，大小寫要正確
			String hql2 = "from Employee WHERE salary >= 30000 and vacation >= 10"; // 條件查詢，salary, vacation 是
																					// Employee.java 的屬性，不是資料庫中的
																					// ColumnName
			Query<Employee> query = session.createQuery(hql2, Employee.class);
			List<Employee> list = query.getResultList();

			for (Employee oneEmp : list) {
				System.out.println(oneEmp);
			}

			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("RollBack!!!");
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSessionFactory();
		}
	}

	// 根據 vacation, salary 找資料
	public void queryEmployeeByVacationAndSalary(Integer vacation, Integer salary) {

		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();

			String hql3 = "from Employee WHERE salary >= :salary and vacation >= :vacation"; // 冒號和參數值要貼著不能空格
			Query<Employee> query = session.createQuery(hql3, Employee.class).setParameter("salary", salary)
					.setParameter("vacation", vacation);
			List<Employee> list = query.getResultList();

			for (Employee oneEmp : list) {
				System.out.println(oneEmp);
			}

			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("RollBack!!!");
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSessionFactory();
		}
	}

	// 根據名字找資料
	public void queryEmployeeByName(String empName) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();

			String hql4 = "from Employee WHERE empName = :empName"; // 冒號和參數值要貼著不能空格
			Query<Employee> query = session.createQuery(hql4, Employee.class).setParameter("empName", empName);
			List<Employee> list = query.getResultList();

			for (Employee oneEmp : list) {
				System.out.println(oneEmp);
			}

			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("RollBack!!!");
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSessionFactory();
		}
	}

	// 根據名字 empName 調整薪水 Salary
	public void updateSalaryByName(String name, Integer salary) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();

			String hql5 = "update Employee set salary = :salary where empName = :name";
			session.createQuery(hql5).setParameter("name", name).setParameter("salary", salary)
					.executeUpdate();
			// executeUpdate() 會回傳 int (影響幾筆資料，和原本 SQL Server 的情況類似)
			// update 的 createQuery() 語法不用後方的 Employee.class，也不用回傳 Query => Query 是查詢時使用

			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("RollBack!!!");
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSessionFactory();
		}
	}

	public static void main(String[] args) {
		DemoHqlActionEx1 hqlTest = new DemoHqlActionEx1();
//		hqlTest.queryAllEmployee();
//		hqlTest.queryEmployeeByVacationAndSalary(10, 30000);
//		hqlTest.queryEmployeeByName("Tom");
		hqlTest.updateSalaryByName("John", 30000);
	}

}
