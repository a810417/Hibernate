package tw.hibernatedemo.action;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import tw.hibernatedemo.model.Friend;
import tw.hibernatedemo.model.MyGroup;
import tw.hibernatedemo.util.HibernateUtil;

public class DemoManyToManyActionEx2 {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			MyGroup workGroup = session.get(MyGroup.class, 3);
			
			Set<Friend> workFriend = workGroup.getFriends();
			
			Friend mary = session.get(Friend.class, 2);
			
			workFriend.add(mary);
			
			session.getTransaction().commit();
		}
		catch(Exception e) {
			session.getTransaction().rollback();
			System.out.println("RollBack~~");
			e.printStackTrace();
		}
		finally {
			HibernateUtil.closeSessionFactory();
		}
	}

}
