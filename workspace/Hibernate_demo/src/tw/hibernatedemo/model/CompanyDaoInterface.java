package tw.hibernatedemo.model;

import java.util.List;

public interface CompanyDaoInterface {
	CompanyBean insert(CompanyBean comBean);
	CompanyBean select(int comId);
	List<CompanyBean>selectAll();
	CompanyBean updateOne(int comId, String comName);
	boolean deleteOne(int comId);
}
