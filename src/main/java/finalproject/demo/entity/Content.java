package finalproject.demo.entity;

public class Content {
	private int id;
	private String writer;
	private String text;//文本
	private String time;
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
	public String toString() {
		return "Content [id=" + id + ", writer=" + writer + ", text=" + text + ", time=" + time + "]";
	}
	
}