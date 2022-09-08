package tw.hibernatedemo.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class CompanyDAO implements CompanyDaoInterface {
	private Session session;

	public CompanyDAO(Session session) {
		this.session = session;
	}

	public Session getSession() {
		return session;
	}
	
	// 概念上大多雷同：
	// 先以 session.get(CompanyBean.class, comBean.getCompanyId()) 查詢確認指定 ID 是否有資料
	// 再執行後續的"新增、修改、刪除"動作

	// 新增
	@Override
	public CompanyBean insert(CompanyBean comBean) {
		CompanyBean companyBean = session.get(CompanyBean.class, comBean.getCompanyId());

		if (companyBean == null) {
			session.save(comBean);
			return comBean;
		}
		return null;
	}

	// 查詢單筆
	@Override
	public CompanyBean select(int comId) {
		return session.get(CompanyBean.class, comId);
	}

	// 查詢全部
	@Override
	public List<CompanyBean> selectAll() {
		Query<CompanyBean> query = session.createQuery("from CompanyBean", CompanyBean.class);
		List<CompanyBean> list = query.list();
		return list;
	}

	// 修改
	@Override
	public CompanyBean updateOne(int comId, String comName) {
		CompanyBean comBean = session.get(CompanyBean.class, comId);
		if (comBean != null) {
			comBean.setCompanyName(comName);
		}
		return comBean;
	}

	// 刪除
	@Override
	public boolean deleteOne(int comId) {
		CompanyBean comBean = session.get(CompanyBean.class, comId);
		if (comBean != null) {
			session.delete(comBean);
			return true;
		}
		return false;
	}

}
