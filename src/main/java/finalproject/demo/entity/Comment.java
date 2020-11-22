package finalproject.demo.entity;

public class Comment extends Content{
	private int target;//评论的目标
	public Comment(int id,String writer,String text,String time,int target) {
		super(id,writer,text,time);
		this.target=target;
	}
	public Comment(String writer,String text,String time,int target) {
		super(writer,text,time);
		this.target=target;
	}

	public Comment(){

	}
	public int getTarget() {
		return target;
	}
	@Override
	public String toString() {
		return "Comment [target=" + target + ", " + super.toString() + "]";
	}
	
}
