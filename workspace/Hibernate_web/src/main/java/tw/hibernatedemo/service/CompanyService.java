package tw.hibernatedemo.service;

import java.util.List;

import org.hibernate.Session;

import tw.hibernatedemo.model.CompanyBean;
import tw.hibernatedemo.model.CompanyDAO;

public class CompanyService implements CompanyServiceInterface {
	
	// CompanyService 屬於商業邏輯層，搭配 DAO 使用
	// 主要的運算、使用都透過 CompanyService，盡量不動到原 DAO，增加資料使用彈性
	
	private CompanyDAO comDao;
	
	// 依賴注入，透過實作 CompanyDAO 將 session 注入 CompanyService
	public CompanyService(Session session) {
		this.comDao = new CompanyDAO(session);
	}

	@Override
	public CompanyBean select(int comId) {
		return comDao.select(comId);
	}

	@Override
	public List<CompanyBean> selectAll() {
		return comDao.selectAll();
	}

	@Override
	public CompanyBean insert(CompanyBean comBean) {
		return comDao.insert(comBean);
	}

	@Override
	public CompanyBean updateOne(int comId, String comName) {
		CompanyBean reComBean = comDao.updateOne(comId, comName);
		return reComBean;		
	}

	@Override
	public boolean delete(int comId) {
		return comDao.deleteOne(comId);
	}

}
