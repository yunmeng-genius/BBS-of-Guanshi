package finalproject.demo.entity;

public class MemberInfo {
	private String currentMember;//当前登录用户
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


	public void alterPassWord(String newpassword) {
		memberInfoQuery.setpassword(currentMember,newpassword);
	}
	//用于修改密码

}
