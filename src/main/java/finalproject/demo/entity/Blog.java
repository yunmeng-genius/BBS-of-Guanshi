package finalproject.demo.entity;

public class Blog {
    private String user;
    private String content;
    private String likePeople;
    private String comment;

    public Blog(String user,String content,String likePeople,String comment){
        this.comment=comment;
        this.content=content;
        this.likePeople=likePeople;
        this.user=user;
    }

    public String getUser() {
        return user;
    }
    public void setUser(String user){
        this.user=user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLikePeople() {
        return likePeople;
    }

    public void setLikePeople(String likePeople) {
        this.likePeople = likePeople;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
