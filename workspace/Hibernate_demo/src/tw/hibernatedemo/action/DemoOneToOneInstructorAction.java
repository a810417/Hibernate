package tw.hibernatedemo.action;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import tw.hibernatedemo.model.Instructor;
import tw.hibernatedemo.model.InstructorDetail;
import tw.hibernatedemo.util.HibernateUtil;

public class DemoOneToOneInstructorAction {

	public static void main(String[] args) {

		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			Instructor ins1 = new Instructor();
			ins1.setName("Jerry");
			
			InstructorDetail insd1 = new InstructorDetail();
			insd1.setEmail("jerry@gmail.com");
			insd1.setPhone("3345678");
			
			ins1.setInstructorDetail(insd1);
			session.save(ins1);
			
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
