package rs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletRegistracija
 */
@SuppressWarnings("unused")
@WebServlet("/ServletRegistracija")
public class ServletRegistracija extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRegistracija() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("index.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// TODO Auto-generated method stub
				DAO dk=new DAO();
				Korisnik k=new Korisnik();
				try {
					String user = new String(request.getParameter("user").getBytes("ISO-8859-1"), "UTF-8");
					String pass = new String(request.getParameter("pass").getBytes("ISO-8859-1"), "UTF-8");
					String email = new String(request.getParameter("email").getBytes("ISO-8859-1"), "UTF-8");
					
					
					
					
					
				
						
						if(user!=null && pass!=null && email!=null && user.length()>0 && user.length()<21  && pass.length()>0 && pass.length()<21  && email.length()>0 && email.length()<31){
							
							
						k = dk.selectOsoba(user);
						System.out.println(k);
						if(k==null){
						dk.insertkorisnik(user, pass, email);
						request.setAttribute("msg", "Uspesna registracija!");
						request.getRequestDispatcher("index.jsp").forward(request, response);
						}
						else{
							request.setAttribute("msg", "Postojeci user!");
							request.getRequestDispatcher("index.jsp").forward(request, response);
						}
						}
						else{
							request.setAttribute("msg", "Niste uneli sva polja!");
							request.getRequestDispatcher("index.jsp").forward(request, response);
						}
				
					
					
				} catch (Exception e) {
					// TODO: handle exception
					request.setAttribute("msg", "Neispravan unos!");
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}
				
			}
	}


