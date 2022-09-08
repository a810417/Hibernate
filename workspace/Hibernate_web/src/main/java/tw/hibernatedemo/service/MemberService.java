package tw.hibernatedemo.service;

import tw.hibernatedemo.model.Member;
import tw.hibernatedemo.model.MemberDao;

public class MemberService {

	private MemberDao mDao;
	public MemberService() {
		this.mDao = new MemberDao();
	}
	
	public Member checkLogin(String loginName, String loginPwd) {
		Member member = mDao.findMemberByNameAndPwd(loginName, loginPwd);
		
		if(member != null) {
			return member;
		}
		return null;
	}
}
