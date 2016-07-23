package rs;

import java.sql.Timestamp;

public class Tema {

	private String tema;
	private int idf,user;
	private long idtema;
	private Timestamp datpost;
	public Tema(String tema, int idf, int user, long idtema, Timestamp datpost) {
		super();
		this.tema = tema;
		this.idf = idf;
		this.user = user;
		this.idtema = idtema;
		this.datpost = datpost;
	}
	public Tema() {
		super();
	}
	public String getTema() {
		return tema;
	}
	public void setTema(String tema) {
		this.tema = tema;
	}
	public int getIdf() {
		return idf;
	}
	public void setIdf(int idf) {
		this.idf = idf;
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
	public Timestamp getDatpost() {
		return datpost;
	}
	public void setDatpost(Timestamp datpost) {
		this.datpost = datpost;
	}



}
