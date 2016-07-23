package rs;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletProsledi
 */
@WebServlet("/ServletProsledi")
public class ServletProsledi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletProsledi() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String id=request.getParameter("id");
			String idpor=request.getParameter("idpor");
			System.out.println(id); 
			if(id!=null && id.length()>0){
			
			request.setAttribute("idf", id);
			request.getRequestDispatcher("postavi-temu.jsp").forward(request, response); 
			}
			else if(idpor!=null && idpor.length()>0){
				request.setAttribute("idpor", idpor);
				request.getRequestDispatcher("novaporuka.jsp").forward(request, response); 
			}
			else{
				response.sendRedirect("index.jsp"); 
			}
		} catch (Exception e) {
			// TODO: handle exception
			response.sendRedirect("tema.jsp");
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
