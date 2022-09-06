package tw.hibernatedemo.action;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import tw.hibernatedemo.model.CompanyBean;
import tw.hibernatedemo.model.CompanyDAO;
import tw.hibernatedemo.util.HibernateUtil;

public class CompanyDAOAction {

	public static void main(String[] args) {

		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();

			CompanyDAO dao = new CompanyDAO(session);
			
			// insert
//			CompanyBean comBean = new CompanyBean(1005, "再睡五分鐘");
//			dao.insert(comBean);
			// update
//			dao.updateOne(1005, "再睡10分鐘");
			// select
//			CompanyBean bean1 = dao.select(999);	
//			if(bean1!=null) {
//				System.out.println("Name: "+bean1.getCompanyName());
//			}else {
//				System.out.println("沒有這筆資料");
//			}					
			// selectAll
//			List<CompanyBean> list = dao.selectAll();
//			for(CompanyBean oneBean:list) {
//				System.out.println(oneBean.getCompanyName());
//			}
			// delete
//			boolean result = dao.deleteOne(999);
//			if(result) {
//				System.out.println("刪除成功");
//			}else {
//				System.out.println("沒有這筆資料");
//			}

			session.getTransaction().commit();
		} catch (Exception e) {
		} finally {
			HibernateUtil.closeSessionFactory();
		}
	}

}
