package rs;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletKorisnikPoruke
 */
@WebServlet("/ServletKorisnikPoruke")
public class ServletKorisnikPoruke extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletKorisnikPoruke() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	ArrayList<Poruka> lp = new ArrayList<Poruka>();
	DAO dao = new DAO();
		String idkor = request.getParameter("idkor");
		
		if(idkor!=null && idkor.length()>0){
			
			int user = Integer.parseInt(idkor);
			lp=dao.selectSvihPoruka(user);
			
			request.setAttribute("lp", lp);
			request.getRequestDispatcher("korisnik-sve-poruke.jsp").forward(request, response);
			
			
		}else {
			response.sendRedirect("index.jsp");
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		response.sendRedirect("index.jsp");
		
	}

}
