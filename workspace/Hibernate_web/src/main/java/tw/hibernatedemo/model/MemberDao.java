package tw.hibernatedemo.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import tw.hibernatedemo.util.HibernateUtil;

public class MemberDao {

	private SessionFactory factory;

	// 依賴注入
	public MemberDao() {
		this.factory = HibernateUtil.getSessionFactory();
	}

	public Member findMemberByNameAndPwd(String username, String pwd) {

		String hql = "from Member m where m.memberName = :username and m.memberPwd = :pwdd";
		Session session = factory.getCurrentSession();

		try {
			Member result = session.createQuery(hql, Member.class).setParameter("username", username)
					.setParameter("pwdd", pwd).getSingleResult();

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
