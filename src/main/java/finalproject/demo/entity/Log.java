package finalproject.demo.entity;

import java.util.Arrays;
import java.util.LinkedList;

public class Log extends Content implements Comparable<Log> {
	public String[] likeGiver;           // log的点赞人
	public LinkedList<Comment> comments; // log的评论

	public Log(int id,String writer,String text,String time,String[] likeGiver,LinkedList<Comment> comments) {
		super(id,writer,text,time);
		this.likeGiver=likeGiver;
		this.comments=comments;
	}
	
	public Log(String writer,String text,String time) {
		super(writer,text,time);
	}

	public int getHotIndex() {
		return this.comments.size() * 5 + this.likeGiver.length;
	}

	public int compareTo(Log o) {
		return this.getHotIndex() - o.getHotIndex();
	}
	@Override
	public String toString() {
		return "Log [likeGiver=" + Arrays.toString(likeGiver) + ", comments=" + comments + ", "
				+ super.toString() + "]";
	}

	public LinkedList<Comment> getComments() {
		return comments;
	}

	public String[] getLikeGiver() {
		return likeGiver;
	}
}