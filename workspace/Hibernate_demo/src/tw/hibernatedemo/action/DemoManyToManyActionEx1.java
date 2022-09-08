package tw.hibernatedemo.action;

import java.util.Iterator;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import tw.hibernatedemo.model.Friend;
import tw.hibernatedemo.model.MyGroup;
import tw.hibernatedemo.util.HibernateUtil;

public class DemoManyToManyActionEx1 {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();

			// 找到 work 群組的物件
			MyGroup workGroup = session.get(MyGroup.class, 3);
			Set<Friend> friends = workGroup.getFriends();
			// 找出 Tina，用 Iterator 把 Tina 從 Set 內移除
			Iterator<Friend> it = friends.iterator();

			while (it.hasNext()) {
				Friend friend = it.next();
				System.out.println("friendName: " + friend.getFriendName());
				String name = friend.getFriendName();
				if(name.equals("Tina")) {
					it.remove();
				}
			}
			// 把 Bill 加進 work 群組
			Friend bill = new Friend();
			bill.setFriendName("Bill");
			session.save(bill);
			
			friends.add(bill);
			
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("RollBack!!!");
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSessionFactory();
		}

	}
}
