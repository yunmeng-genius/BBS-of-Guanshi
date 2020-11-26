package finalproject.demo.entity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.SimpleFormatter;

public class WebPrint {
	public static MemberInfo currentMember;
	public static SqlQuery logQuery = new SqlQuery();

	public static boolean logJudge(String memberName, String password) {
		currentMember = new MemberInfo(memberName);
		return currentMember.login(memberName, password);
	}// 登录判断

	public static ArrayList<Log> getNewestLogs(String memberName) {
		return logQuery.getByName(false, memberName);
	}// 获取最新十条log

//	public static LinkedList<Log> getHotestLog(){
//		return logQuery.getLinkedList(false,false);
//	}//获取热门的前五条log
//

//	public static LinkedList<Log> getMyHotLogs(){
//		return logQuery.getLinkedList(false, true);
//	}//获取最热五条我的log

	public static ArrayList<Log> getMyNewLogs(String memberName) {
		return logQuery.getByName(true, memberName);
	}// 获取最近五条我的log

	public static void alterPassword(String newPassword, String memberName) {
		logQuery.setpassword(memberName, newPassword);
	}// 修改密码

	public static void releaseLog(String releaseContent, String memberName) {
		String dateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		Log newLog = new Log(memberName, releaseContent, dateString);
		logQuery.setlog(newLog);
	}// 发表log

	public static void comment(String commentContent, String commentor, int logid) {
		String dateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		Comment comment = new Comment(commentor, commentContent, dateString, logid);
		logQuery.setcomment(comment);
	}// 发表评论

	public static void like(String likegiver, int logid) {
		logQuery.setlike(likegiver, logid);
	}// 点赞

	public static LinkedList<Comment> getComment(int logid) {
		return logQuery.getComments(logid);
	}// 获取评论

	public static int getLikeNum(int logid) {
		return logQuery.getLikes(logid).length;
	}//获取点赞人数

	public static String[] getLikeGivers(int logid) {
		return logQuery.getLikes(logid);
	}//获取点赞人们的名字

}
