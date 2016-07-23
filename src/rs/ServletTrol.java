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
 * Servlet implementation class ServletTrol
 */
@WebServlet("/ServletTrol")
public class ServletTrol extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTrol() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			
		HttpSession sesija=request.getSession();
		String id=request.getParameter("id");
		String off=request.getParameter("off");
		DAO dao=new DAO();
		ArrayList<Tema> tema1=null;
		ArrayList<Tema> temaoff=null;
		
		

		if(id!=null && id.length()>0 && off!=null && off.length()>0){
			
			int idf=Integer.parseInt(id);
			int offset=Integer.parseInt(off);
			tema1=dao.selectTema(idf); 
			temaoff=dao.selectTemaoff(idf, offset);
			int brstr=tema1.size()/10;
			if(tema1.size()%10==1)
				brstr++;
			if(brstr<1)
				brstr++;
			System.out.println(brstr); 
			
			sesija.setAttribute("brstrforum", brstr);
			sesija.setAttribute("tema", temaoff);
			sesija.setAttribute("idf", id); 
			request.getRequestDispatcher("tema.jsp").forward(request, response);
			
			
		}
		else {
			response.sendRedirect("index.jsp"); 
		}
		
		} catch (Exception e) {
			// TODO: handle exception
			response.sendRedirect("index.jsp"); 
		}

	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("index.jsp"); 
	}

}
