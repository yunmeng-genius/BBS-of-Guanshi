package finalproject.demo.entity;


import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

public class WebPrint {
	public static MemberInfo currentMember;
	public static SqlQuery logQuery=new SqlQuery();
	
	public static boolean logJudge(String memberName, String password) {
		currentMember =new MemberInfo(memberName);
		return currentMember.login(memberName, password);
	}//登录判断
	
	public static ArrayList<Log> getNewestLogs() {
		return logQuery.getByName(false,currentMember.currentMember );
	}//获取最新十条log
	
//	public static LinkedList<Log> getHotestLog(){
//		return logQuery.getLinkedList(false,false);
//	}//获取热门的前五条log
//	
	public static ArrayList<Log> getMyNewLogs(String username){
		return logQuery.getByName(true, currentMember.currentMember);
	}//获取最近五条我的log
	
//	public static LinkedList<Log> getMyHotLogs(){
//		return logQuery.getLinkedList(false, true);
//	}//获取最热五条我的log
//	
	public static void alterPassword(String newPassword) {
		logQuery.setpassword(newPassword,currentMember.currentMember);
	}//修改密码
	
	public static void releaseLog(String releaseContent){
		logQuery.setlog(new Log(currentMember.currentMember,releaseContent,new Date().toString()));
	}//发表log
	
	public static void comment(String commentContent,String commentor,int logid) {
		Comment comment=new Comment(currentMember.currentMember,commentor ,new Date().toString(), logid);
		logQuery.setcomment(comment);
	}//发表评论
	
	public static void like(String likegiver,int  logid) {
		logQuery.setlike(likegiver,logid);
	}//点赞
}
