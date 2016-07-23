package rs;

public class Korisnik {
	private String user,pass,email;
	int id;
	public Korisnik(String user, String pass, String email) {
		super();
		this.user = user;
		this.pass = pass;
		this.email = email;
	}
	public Korisnik() {
		super();
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
