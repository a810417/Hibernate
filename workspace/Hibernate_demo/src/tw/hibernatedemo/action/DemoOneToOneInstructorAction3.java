package tw.hibernatedemo.action;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import tw.hibernatedemo.model.Instructor;
import tw.hibernatedemo.model.InstructorDetail;
import tw.hibernatedemo.util.HibernateUtil;

public class DemoOneToOneInstructorAction3 {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
					
			// 只想刪除 Detail ，(個資問題)
			Instructor ins1 = session.get(Instructor.class, 1);
			InstructorDetail detail = ins1.getInstructorDetail();
			
			// setInstructorDetail 設定對應的是 fk_instructorDetail_id
			// 是兩個表參照的關鍵，設為 null 表示另一個表沒有參照
			// 被參照的表就不被束縛可以自由刪除
			ins1.setInstructorDetail(null);
			session.delete(detail);

			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("ROLLBACK!!!");
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSessionFactory();
		}
	}

}
