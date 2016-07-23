package rs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletPosTema
 */
@WebServlet("/ServletPosTema")
public class ServletPosTema extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPosTema() {
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
		
		
		DAO dao=new DAO();
		ArrayList<Tema> tema1=null;
		
			String tema=request.getParameter("naslov");
			String poruka=request.getParameter("poruka");
			String idf=request.getParameter("idf");
			String userid=request.getParameter("user");
			System.out.println(userid); 
			try {
			Random rand = new Random();
			long accumulator = 1 + rand.nextInt(9); // ensures that the 16th digit isn't 0
			for(int i = 0; i < 9; i++) {
			    accumulator *= 10L;
			    accumulator += rand.nextInt(10);
			}
			int idf1=Integer.parseInt(idf);
			int iduser=Integer.parseInt(userid);
			
			if(tema!=null && tema.length()>0 && tema.length()<41 && poruka!=null && poruka.length()>0 && poruka.length()<500){
			dao.insertTema(accumulator, tema, iduser, idf1);
			dao.insertPoruka(poruka, iduser, accumulator);
			tema1=dao.selectTema(idf1);
			

			}
			else {
				request.setAttribute("idf", idf);
				request.setAttribute("msg3", "Niste uneli oba polja!(Poruka najvise 500 i naslov teme 40 karaktera! )");
				if(admin!=null){
					request.getRequestDispatcher("novatemaadmin.jsp").forward(request, response);
					}
					else{
						request.getRequestDispatcher("postavi-temu.jsp").forward(request, response);
					}
			}
			int brstr=tema1.size()/10;
			if(tema1.size()%10==1)
				brstr++;
			if(brstr<1)
				brstr++;
			
			sesija.setAttribute("brstr", brstr);
			sesija.setAttribute("tema", tema1);
			if(admin!=null){
			request.getRequestDispatcher("tema-admin.jsp").forward(request, response);
			}
			else{
				request.getRequestDispatcher("tema.jsp").forward(request, response);
			}

		
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("idf", idf);
			request.setAttribute("msg3", "Neispravan unos!");
			if(admin!=null){
				request.getRequestDispatcher("novatemaadmin.jsp").forward(request, response);
				}
				else{
					request.getRequestDispatcher("postavi-temu.jsp").forward(request, response);
				}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("postavi-temu.jsp"); 
	}

}
