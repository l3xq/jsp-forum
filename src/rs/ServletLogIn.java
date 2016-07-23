package rs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletLogIn
 */
@WebServlet("/ServletLogIn")
public class ServletLogIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLogIn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession sesija=request.getSession();
		sesija.invalidate();
		
		request.getRequestDispatcher("index.jsp").forward(request, response); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DAO dk=new DAO();
		Korisnik k=new Korisnik();
		try {
			String user = new String(request.getParameter("user").getBytes("ISO-8859-1"), "UTF-8");
			String pass = new String(request.getParameter("pass").getBytes("ISO-8859-1"), "UTF-8");
			
	
				System.out.println(user);
				k = dk.selectOsoba(user);
				
				if(k.getUser().equals("admin") && k.getPass().equals(pass)){
					HttpSession sesija=request.getSession();
					sesija.setAttribute("ulogovanAdmin", k);
					sesija.setAttribute("ulogovanSesija", k);
					request.getRequestDispatcher("adminpanel.jsp").forward(request, response);
				}
				
				
				else	if(k.getUser().equals(user) && k.getPass().equals(pass)){
					HttpSession sesija=request.getSession();
					sesija.setAttribute("ulogovanSesija", k);
					request.getRequestDispatcher("index.jsp").forward(request, response); 
					
				}
				else{
					request.setAttribute("msg1", "Neispravan user ili pass!");
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}
				
				
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("msg1", "Neispravan unos!");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
	}

}
