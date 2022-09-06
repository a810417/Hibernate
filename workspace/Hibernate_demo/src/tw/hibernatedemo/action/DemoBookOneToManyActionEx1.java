package tw.hibernatedemo.action;

import java.util.LinkedHashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import tw.hibernatedemo.model.BookUsers;
import tw.hibernatedemo.model.Books;
import tw.hibernatedemo.util.HibernateUtil;

public class DemoBookOneToManyActionEx1 {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		
		// Tom 要借兩本書
		try {
			session.beginTransaction();
			
			// 新增 2 本書、使用者 Tom
			Books book1 = new Books();
			book1.setBooktitle("Java");
			book1.setPublicYear("2018");
			
			Books book2 = new Books();
			book2.setBooktitle("Spy Family");
			book2.setPublicYear("2022-1");
			
			BookUsers user1 = new BookUsers();
			user1.setUserName("Tom");
			
			// book 關聯使用者、使用者關聯 books 都要寫 setBookusers/setBooks 兩邊才會正確關聯
			// book 關聯使用者
			book1.setBookusers(user1);
			book2.setBookusers(user1);
			
			// 使用者關聯 books
			Set<Books> tempSet = new LinkedHashSet<Books>();
			tempSet.add(book1);
			tempSet.add(book2);
			
			user1.setBooks(tempSet);
			
			session.save(user1);
			
			session.getTransaction().commit();
		}
		catch(Exception e) {
			session.getTransaction().rollback();
			System.out.println("RollBack!!!");
			e.printStackTrace();
		}
		finally {
			HibernateUtil.closeSessionFactory();
		}
	}

}
