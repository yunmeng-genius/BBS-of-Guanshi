package finalproject.demo.entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;

public class SqlQuery{
	private Connection conn;
	public SqlQuery(){
	try {
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/guanshiwall?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC","Zhou","homework123");
	} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("连接数据库失败！");
	}
	}

	public String getPassword(String memberName){
		try {
			String sql = "SELECT passwords FROM members WHERE name=?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, memberName);
	        ResultSet rs = ps.executeQuery();
	        if(rs.next())
	        	return rs.getString("passwords");
		} catch (SQLException e) {
			e.printStackTrace();
		}
       return null;
	}//从数据库中查询获取密码
	
	public String[] getMemberNames() {
		ArrayList<String>temp=new ArrayList<>();
		try {
			Statement stmt = conn.createStatement();
			String sql = "SELECT name FROM members;";
	        ResultSet rs = stmt.executeQuery(sql);
	        while(rs.next())
	        	temp.add(rs.getString("name"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String[]result=new String[temp.size()];
		for(int i=0;i<temp.size();i++) {
			result[i]=temp.get(i);
		}
		return result;
	}//获取全部成员名
	public LinkedList<Comment> getComments(int targetlog){
		LinkedList<Comment> result = new LinkedList<>();
		String sql = "SELECT commentid,commenter,content,time FROM comments WHERE targetlog=? ORDER BY time;";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, targetlog);
	        ResultSet rs = ps.executeQuery();
	        while(rs.next()) {
	        	result.add(new Comment(rs.getInt("commentid"), rs.getString("commenter"), rs.getString("content"), rs.getString("time"),targetlog));
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}//升序获取指定logid对应的评论
	public String[] getLikes(int targetlog) {
		String[] result;
		ArrayList<String>temp=new ArrayList<>();
		String sql = "SELECT likegiver FROM likes WHERE targetlog=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, targetlog);
	        ResultSet rs = ps.executeQuery();
	        while(rs.next()) {
	        	temp.add(rs.getString("likegiver"));
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		result=new String[temp.size()];
		for(int i=0;i<temp.size();i++)result[i]=temp.get(i);
		return result;
	}//获得指定logid对应的点赞人名
	
	public ArrayList<Log> getByName(boolean flag,String name){
		ArrayList<Log>temp=new ArrayList<>();
		String sql;
		if(flag) {
			sql = "SELECT * FROM logs WHERE poster='"+name+"'ORDER BY time DESC;";
		}else {
			sql = "SELECT * FROM logs ORDER BY time DESC;";
		}
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();
	        while(rs.next()) {
	        	temp.add(new Log(rs.getInt("logid"), rs.getString("poster"), rs.getString("content"), rs.getString("time"), getLikes(rs.getInt("logid")), getComments(rs.getInt("logid"))));
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return temp;
	}//默认已经按时间降序排序，若需要按热度则直接再sort。是否为自己flag:true为显示自己，false为显示全部的。
	public int setpassword(String name,String newpassword) {
		String sql1 = "UPDATE members SET passwords=? WHERE name=?;"; 
		try {
			PreparedStatement ps=conn.prepareStatement(sql1);
			ps.setString(1, newpassword);
			ps.setString(2, name);
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}  
		return 0;
	}//设置密码

	public int setlog(Log aLog) {
		String sql1 = "INSERT INTO logs(poster,content,time) VALUES(?,?,?);"; 
		try {
			PreparedStatement ps=conn.prepareStatement(sql1);
			ps.setString(1, aLog.getWriter());
			ps.setString(2, aLog.getText());
			ps.setString(3, aLog.getTime());
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}  
		return 0;
	}


	public int setlike(String likegiver, int logid) {
		String sql1 = "INSERT INTO likes VALUES(?,?);"; 
		try {
			PreparedStatement ps=conn.prepareStatement(sql1);
			ps.setInt(1, logid);
			ps.setString(2, likegiver);
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}  
		return 0;
	}

	public int setcomment(Comment aComment) {
		String sql1 = "INSERT INTO comments(commenter,content,targetlog,time) VALUES(?,?,?,?);"; 
		try {
			PreparedStatement ps=conn.prepareStatement(sql1);
			ps.setString(1, aComment.getWriter());
			ps.setString(2, aComment.getText());
			ps.setInt(3, aComment.getTarget());
			ps.setString(4, aComment.getTime());
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}  
		return 0;
	}
}
