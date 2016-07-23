package rs;

import java.sql.Timestamp;

public class Poruka {

	private int idpor,user;
	private long idtema;
	private String poruka;

	private Timestamp datpost;

	public Poruka(int user, long idtema, String poruka) {
		super();
		this.user = user;
		this.idtema = idtema;
		this.poruka = poruka;
	}

	public Poruka() {
		super();
	}

	public int getIdpor() {
		return idpor;
	}
	public void setIdpor(int idpor) {
		this.idpor = idpor;
	}
	public int getUser() {
		return user;
	}
	public void setUser(int user) {
		this.user = user;
	}
	public long getIdtema() {
		return idtema;
	}
	public void setIdtema(long idtema) {
		this.idtema = idtema;
	}
	public String getPoruka() {
		return poruka;
	}
	public void setPoruka(String poruka) {
		this.poruka = poruka;
	}
	public Timestamp getDatpost() {
		return datpost;
	}
	public void setDatpost(Timestamp datpost) {
		this.datpost = datpost;
	}


}
