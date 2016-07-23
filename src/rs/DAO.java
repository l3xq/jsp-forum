
package  rs;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.ArrayList;

public class DAO {
	private DataSource ds;


	private static String INSERTOSOBA = "INSERT INTO `korisnik`(`user`, `lozinka`, `email`) VALUES (?,?,?)";
	private static String INSERTTEMA="INSERT INTO `tema`(`idtema`,`tema`, `user`, `idf`, `datpost`) VALUES (?,?,?,?,CURRENT_TIMESTAMP)";
	private static String INSERTPORUKA="INSERT INTO `poruka`(`poruka`, `user`, `idtema`, `datpost`) VALUES (?,?,?,CURRENT_TIMESTAMP)";
	private static String INSERTFORUM= "INSERT INTO `forum`(`forum`) VALUES (?)";

	private static String SELECTOSOBA=" SELECT * FROM `korisnik` WHERE user=?";
	private static String BROJTEMA=" SELECT COUNT(*) AS rowcount FROM tema";
	private static String SELECTFORUM=" SELECT * FROM forum";
	private static String SELECTTEMA=" SELECT * FROM tema WHERE idf=? ORDER BY datpost";
	private static String SELECTOSOBABYID=" SELECT * FROM `korisnik` WHERE id=?";
	private static String SELECTPORUKA=" SELECT * FROM poruka WHERE idtema=?";
	private static String SELECTPORUKABYOFFSET=" SELECT * FROM poruka WHERE idtema=? LIMIT 10 OFFSET ?";
	private static String SELECTTEMAOFFSET=" SELECT * FROM tema WHERE idf=? ORDER BY datpost LIMIT 10 OFFSET ?";
	private static String SELECTFORUMBYID=" SELECT * FROM forum where idf=?";
	private static String SELECTTEMABYID=" SELECT * FROM tema WHERE idtema=?";
	private static String SELECTSVIHPORUKA=" SELECT * FROM poruka WHERE user=?";

	private static String UPDATEPORUKA="UPDATE `poruka` SET `poruka`=? WHERE idpor=?";
	private static String UPDATEFORUM=" UPDATE `forum` SET `forum`=? WHERE `idf`=?";
	private static String UPDATETEMA="UPDATE `tema` SET `tema`=? WHERE `idtema`=?";

	private static String DELETETEMA="DELETE FROM `tema` WHERE idtema=?";
	private static String DELETEPORUKA="DELETE FROM `poruka` WHERE idpor=?";
	private static String DELETEPORUKATEMA="DELETE FROM `poruka` WHERE idtema=?";
	private static String DELETEFORUM="DELETE FROM `forum` WHERE idf=?";

	@SuppressWarnings("unused")
	public DAO(){
		try {
			InitialContext cxt = new InitialContext();
			if ( cxt == null ) { 
			} 
			ds = (DataSource) cxt.lookup( "java:/comp/env/jdbc/mysql" ); 
			if ( ds == null ) { 
			} 		
		} catch (NamingException e) {
		}
	}


	public void insertkorisnik(String user,String pass,String email){
		Connection con = null;
		PreparedStatement pstm = null;

		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(INSERTOSOBA);


			pstm.setString(1, user);
			pstm.setString(2, pass); 
			pstm.setString(3, email); 
			pstm.execute();


		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Korisnik selectOsoba(String user){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		Korisnik kor = null;

		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(SELECTOSOBA);

			pstm.setString(1, user);
			pstm.execute();

			rs = pstm.getResultSet();

			if(rs.next()){ 
				kor = new Korisnik();
				kor.setId(rs.getInt("id"));
				kor.setUser(rs.getString("user"));
				kor.setPass(rs.getString("lozinka"));
				kor.setEmail(rs.getString("email"));


			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kor; 
	}

	public int countForum(){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		int i=0;

		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(BROJTEMA);

			pstm.execute();

			rs = pstm.getResultSet();

			if(rs.next()){ 
				i=rs.getInt("rowcount");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i; 
	}


	public ArrayList<Forum> selectForum(){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		ArrayList<Forum> flista = new ArrayList<Forum>();
		Forum forum=null;

		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(SELECTFORUM);

			pstm.execute();

			rs = pstm.getResultSet();

			while(rs.next()){ 
				forum=new Forum();
				forum.setId(rs.getInt("idf"));
				forum.setForum(rs.getString("forum"));


				flista.add(forum);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flista; 
	}


	public ArrayList<Tema> selectTema(int idf){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		ArrayList<Tema> Tlista = new ArrayList<Tema>();
		Tema Tema=null;

		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(SELECTTEMA);
			pstm.setInt(1, idf);

			pstm.execute();

			rs = pstm.getResultSet();

			while(rs.next()){
				Tema=new Tema();
				Tema.setIdtema(rs.getLong("idtema"));
				Tema.setTema(rs.getString("tema")); 
				Tema.setUser(rs.getInt("user"));
				Tema.setIdf(rs.getInt("idf"));
				Tema.setDatpost(rs.getTimestamp("datpost"));

				Tlista.add(Tema);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Tlista; 
	}

	public Korisnik selectOsobaID(int user){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		Korisnik kor = null;

		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(SELECTOSOBABYID);

			pstm.setInt(1, user);
			pstm.execute();

			rs = pstm.getResultSet();

			if(rs.next()){
				kor = new Korisnik();
				kor.setId(rs.getInt("id"));
				kor.setUser(rs.getString("user"));
				kor.setPass(rs.getString("lozinka"));
				kor.setEmail(rs.getString("email"));


			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kor; 
	}
	//ubacivanje teme
	public void insertTema(long idtema,String tema,int userid,int idf){
		Connection con = null;
		PreparedStatement pstm = null;


		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(INSERTTEMA);

			pstm.setLong(1, idtema); 
			pstm.setString(2, tema);
			pstm.setInt(3, userid); 
			pstm.setInt(4, idf); 
			pstm.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void insertPoruka(String poruka,int userid,long idtema){
		Connection con = null;
		PreparedStatement pstm = null;

		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(INSERTPORUKA);


			pstm.setString(1, poruka);
			pstm.setInt(2, userid); 
			pstm.setLong(3, idtema); 
			pstm.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}


	public ArrayList<Poruka> selectPoruka(long idtema){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		ArrayList<Poruka> Plista = new ArrayList<Poruka>();
		Poruka poruka=null;

		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(SELECTPORUKA);
			pstm.setLong(1, idtema);

			pstm.execute();

			rs = pstm.getResultSet();

			while(rs.next()){ 
				poruka=new Poruka();
				poruka.setIdtema(rs.getLong("idtema"));
				poruka.setPoruka(rs.getString("poruka")); 
				poruka.setUser(rs.getInt("user"));
				poruka.setIdpor(rs.getInt("idpor")); 
				poruka.setDatpost(rs.getTimestamp("datpost"));  

				Plista.add(poruka);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Plista; 
	}

	public ArrayList<Poruka> selectPorukaoffset(long idtema, int off){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<Poruka> Plista = new ArrayList<Poruka>();
		Poruka poruka=null;

		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(SELECTPORUKABYOFFSET);
			pstm.setLong(1, idtema);
			pstm.setInt(2, off); 

			pstm.execute();

			rs = pstm.getResultSet();

			while(rs.next()){
				poruka=new Poruka();
				poruka.setIdtema(rs.getLong("idtema"));
				poruka.setPoruka(rs.getString("poruka")); 
				poruka.setUser(rs.getInt("user"));
				poruka.setIdpor(rs.getInt("idpor")); 
				poruka.setDatpost(rs.getTimestamp("datpost"));  

				Plista.add(poruka);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Plista; 
	}

	public ArrayList<Tema> selectTemaoff(int idf,int off){

		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<Tema> Tlista = new ArrayList<Tema>();
		Tema Tema=null;

		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(SELECTTEMAOFFSET);
			pstm.setInt(1, idf);
			pstm.setInt(2, off); 

			pstm.execute();
			rs = pstm.getResultSet();

			while(rs.next()){
				Tema=new Tema();
				Tema.setIdtema(rs.getLong("idtema"));
				Tema.setTema(rs.getString("tema")); 
				Tema.setUser(rs.getInt("user"));
				Tema.setIdf(rs.getInt("idf"));
				Tema.setDatpost(rs.getTimestamp("datpost"));

				Tlista.add(Tema);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Tlista; 
	}


	public Forum selectForumID(int idf){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		Forum forum=null;

		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(SELECTFORUMBYID);
			pstm.setInt(1, idf); 
			pstm.execute();

			rs = pstm.getResultSet();

			if(rs.next()){ 
				forum=new Forum();
				forum.setId(rs.getInt("idf"));
				forum.setForum(rs.getString("forum"));


			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return forum; 
	}


	public Tema selectTemaID(long idtema){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		Tema Tema=null;

		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(SELECTTEMABYID);
			pstm.setLong(1, idtema);

			pstm.execute();

			rs = pstm.getResultSet();

			if(rs.next()){ 
				Tema=new Tema();
				Tema.setIdtema(rs.getLong("idtema"));
				Tema.setTema(rs.getString("tema")); 
				Tema.setUser(rs.getInt("user"));
				Tema.setIdf(rs.getInt("idf"));
				Tema.setDatpost(rs.getTimestamp("datpost"));


			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Tema; 
	}

	public void updateForum(int idf,String naslov){
		Connection con = null;
		PreparedStatement pstm = null;

		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(UPDATEFORUM);
			pstm.setString(1, naslov);
			pstm.setInt(2, idf);

			pstm.execute();


		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateTema(long idtema,String naslov){
		Connection con = null;
		PreparedStatement pstm = null;

		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(UPDATETEMA);
			pstm.setString(1, naslov);
			pstm.setLong(2, idtema);

			pstm.execute();


		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteTema(long idtema){
		Connection con = null;
		PreparedStatement pstm = null;


		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(DELETETEMA);
			pstm.setLong(1, idtema);

			pstm.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}


	public void updatePoruka(int idpor,String naslov){
		Connection con = null;
		PreparedStatement pstm = null;

		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(UPDATEPORUKA);
			pstm.setString(1, naslov);
			pstm.setLong(2, idpor);

			pstm.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deletePoruka(int idpor){
		Connection con = null;
		PreparedStatement pstm = null;

		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(DELETEPORUKA);
			pstm.setInt(1, idpor);

			pstm.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public ArrayList<Poruka> selectSvihPoruka(int user){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<Poruka> Plista = new ArrayList<Poruka>();
		Poruka poruka=null;

		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(SELECTSVIHPORUKA);
			pstm.setInt(1, user);

			pstm.execute();

			rs = pstm.getResultSet();

			while(rs.next()){ 
				poruka=new Poruka();
				poruka.setIdtema(rs.getLong("idtema"));
				poruka.setPoruka(rs.getString("poruka")); 
				poruka.setUser(rs.getInt("user"));
				poruka.setIdpor(rs.getInt("idpor")); 
				poruka.setDatpost(rs.getTimestamp("datpost"));  

				Plista.add(poruka);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Plista; 
	}


	public void deletePorukaTema(long idtema){
		Connection con = null;
		PreparedStatement pstm = null;

		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(DELETEPORUKATEMA);
			pstm.setLong(1, idtema);

			pstm.execute();


		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertForum(String poruka){
		Connection con = null;
		PreparedStatement pstm = null;

		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(INSERTFORUM);

			pstm.setString(1, poruka);

			pstm.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void deleteFORUM(int idf){
		Connection con = null;
		PreparedStatement pstm = null;

		try {
			con = ds.getConnection();
			pstm = con.prepareStatement(DELETEFORUM);
			pstm.setInt(1, idf);

			pstm.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}							

}
