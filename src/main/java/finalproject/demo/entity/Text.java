package finalproject.demo.entity;

public class Text {

	public static void main(String[] args) throws ClassNotFoundException {
		SqlQuery a=new SqlQuery();
		System.out.println(a.getByName(false, null));
		System.out.println(a.getPassword("Liu"));
		System.out.println(a.getPassword("Zhou"));
		System.out.println(a.getPassword("Li"));
		System.out.println(a.getByName(false,"Liu").toString());
		WebPrint.logJudge("Liu","000000");
		System.out.println(WebPrint.currentMember.currentMember);
		WebPrint.getMyNewLogs("Liu");
	}

}
