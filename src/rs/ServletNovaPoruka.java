package rs;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletNovaPoruka
 */
@WebServlet("/ServletNovaPoruka")
public class ServletNovaPoruka extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletNovaPoruka() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.sendRedirect("prikaz.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession sesija=request.getSession();
		Korisnik admin=(Korisnik)sesija.getAttribute("ulogovanAdmin");
		
		DAO dao= new DAO();
		ArrayList<Poruka> por=new ArrayList<Poruka>();
		String poruka=request.getParameter("poruka");
		String idpor=request.getParameter("idpor");
		String user=request.getParameter("user");
		
		if(poruka!=null && poruka.length()>0 && poruka.length()<500 && idpor!=null && idpor.length()>0 && user!=null && user.length()>0){
			
			long idtema=Long.parseLong(idpor);
			int userid=Integer.parseInt(user);
			dao.insertPoruka(poruka, userid, idtema); 
			por=dao.selectPoruka(idtema);
			
			
			int brstr=por.size()/10;
			if(por.size()%10==1)
				brstr++;
			if(brstr<1)
				brstr++;
			request.setAttribute("brstr", brstr); 
			request.setAttribute("por", por);
			if(admin!=null){
				request.getRequestDispatcher("prikaz-admin.jsp").forward(request, response); 
			}
			else{
			
			
			request.getRequestDispatcher("prikaz.jsp").forward(request, response); 
			}
		}
		
		else{
			
			request.setAttribute("msg4", "Neispravan unos!"); 
			request.setAttribute("idpor", idpor);
			request.getRequestDispatcher("novaporuka.jsp").forward(request, response); 
			
		}
		
		
	}

}
