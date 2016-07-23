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
 * Servlet implementation class ServletPregled
 */
@WebServlet("/ServletPregled")
public class ServletPregled extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPregled() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession sesija=request.getSession();
		Korisnik admin = (Korisnik) sesija.getAttribute("ulogovanAdmin");
		
		String idtem=request.getParameter("idtem");
		String offset=request.getParameter("offset");
		DAO dao=new DAO();
		ArrayList<Poruka> por=new ArrayList<Poruka>();
		ArrayList<Poruka> poroff=new ArrayList<Poruka>();
		
		if(idtem!= null && idtem.length()>0 && offset!=null && offset.length()>0){
		
		long idtema=Long.parseLong(idtem);
		int off=Integer.parseInt(offset);
		por=dao.selectPoruka(idtema);
		poroff=dao.selectPorukaoffset(idtema, off);
		
		int brstr=por.size()/10;
		if(por.size()%10==1)
		brstr++;
		if(brstr<1)
			brstr++;
		
		if(admin!=null){
		request.setAttribute("brstr", brstr); 
		request.setAttribute("por", poroff);
		request.getRequestDispatcher("prikaz-admin.jsp").forward(request, response);  
		}
		else{
			request.setAttribute("brstr", brstr); 
			request.setAttribute("por", poroff);
			request.getRequestDispatcher("prikaz.jsp").forward(request, response);  
		}
		}
		else{
			response.sendRedirect("index.jsp"); 
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("tema.jsp"); 
	}

}
