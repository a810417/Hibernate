package tw.hibernatedemo.action;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import tw.hibernatedemo.model.Instructor;
import tw.hibernatedemo.model.InstructorDetail;
import tw.hibernatedemo.util.HibernateUtil;

public class DemoOneToOneInstructorAction5 {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();

			// Jerry (Instructor 1) 又想加 Detail 回去

			// 透過已知的 Instructor 1 取得該筆資料(目前 fk_instructorDetail_id 還是 null)
			Instructor ins1 = session.get(Instructor.class, 1);

			// 新增要的資訊 -> InstructorDetail 的資訊
			InstructorDetail insd1 = new InstructorDetail();
			insd1.setEmail("jerry@hotmail.com");
			insd1.setPhone("3345678");
			session.save(ins1);
			
			// 將 Instructor 與 InstructorDetail 產生鏈接 -> .setInstructorDetail()
			// 會執行 update Instructor(fk_instructorDetail_id 從 null 更新)
			ins1.setInstructorDetail(insd1);
			
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
