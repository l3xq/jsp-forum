package rs;

public class Forum {
	private String forum;
	private int id;
	public Forum(String forum) {
		super();
		this.forum = forum;
	}
	public Forum() {
		super();
	}
	public String getForum() {
		return forum;
	}
	public void setForum(String forum) {
		this.forum = forum;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
