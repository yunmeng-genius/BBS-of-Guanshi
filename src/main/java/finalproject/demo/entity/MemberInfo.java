package finalproject.demo.entity;

public class MemberInfo {
	public String currentMember;// 当前登录了的成员
	private SqlQuery memberInfoQuery = new SqlQuery();// 建立查询

	public MemberInfo(String memberName) {
		currentMember = memberName;
	}

	public boolean login(String memberName,String password) {
		boolean flag = false;
		if (memberName != null && password != null && (password.equals(memberInfoQuery.getPassword(currentMember)))) {
			for (String member : memberInfoQuery.getMemberNames()) {
				if (member.equals(currentMember)) {
					flag = true;
					break;
				}
			}
		}
		return flag;
	}// 登录判断密码不空、成员存在、密码正确

	public void alterPassWord(String newPassword) {
		memberInfoQuery.setpassword(currentMember,newPassword);
	}

}
