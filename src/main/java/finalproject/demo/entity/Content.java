package finalproject.demo.entity;

public class Content {
	private int id;           //log及comment的id
	private String writer;    //log及comment的作者
	private String text;      //log及comment的文本
	private String time;      //log及comment的时间
	Content(int id,String writer,String text,String time) {
		this.id=id;
		this.writer=writer;
		this.text = text;
		this.time=time;
	}
	Content(String writer,String text,String time) {
		this.writer=writer;
		this.text = text;
		this.time=time;
	}
	
	public int getId() {
		return id;
	}
	public String getWriter() {
		return writer;
	}
	public String getText() {
		return text;
	}
	public String getTime() {
		return time;
	}
	@Override
	public String toString() {            //重写toString方便测试
		return "Content [id=" + id + ", writer=" + writer + ", text=" + text + ", time=" + time + "]";
	}
	
}