package rs;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletAdmin
 */
@WebServlet("/ServletAdmin")
public class ServletAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DAO dao=new DAO();
		ArrayList<Forum> forum=null;
		ArrayList<Tema> tema=null;
		ArrayList<Poruka> por=null;
		String akcija=request.getParameter("akcija");
		String idf=request.getParameter("idf");
		String naslov=request.getParameter("vrednost");
		String idtema=request.getParameter("idtema");
		String idpor=request.getParameter("idpor");
		
	

		if(akcija.equals("forum")){
			forum=dao.selectForum();
			request.setAttribute("forum", forum); 
			request.getRequestDispatcher("adminpanel.jsp").forward(request, response); 
		}
		else if(akcija.equals("Izmeni") && idf!=null && idf.length()>0){
			int idforum=Integer.parseInt(idf);
			dao.updateForum(idforum, naslov); 
			forum=dao.selectForum();
			request.setAttribute("forum", forum); 
			request.getRequestDispatcher("adminpanel.jsp").forward(request, response); 
		}else if(akcija.equals("Dodaj") && naslov.length()>0 && naslov!=null){
			dao.insertForum(naslov);
			forum=dao.selectForum();
			request.setAttribute("forum", forum); 
			request.getRequestDispatcher("adminpanel.jsp").forward(request, response); 
		}
		
		else if(akcija.equals("Izbrisi")){
			int idforum=Integer.parseInt(idf);
			dao.deleteFORUM(idforum);
			forum=dao.selectForum();
			request.setAttribute("forum", forum); 
			request.getRequestDispatcher("adminpanel.jsp").forward(request, response); 
			
		}

		else if(akcija.equals("izmenitemu")){
			
			long idtem=Long.parseLong(idtema);
			int idforum=Integer.parseInt(idf);
			dao.updateTema(idtem, naslov); 
			tema=dao.selectTema(idforum);
			
			int brstr=tema.size()/10;
			if(tema.size()%10==1)
				brstr++;
			if(brstr<1)
				brstr++;
			tema=dao.selectTemaoff(idforum, 0);
			request.setAttribute("tema", tema); 
			request.setAttribute("brstr", brstr); 
			request.getRequestDispatcher("tema-admin.jsp").forward(request, response);
			
			
		}
		else if(akcija.equals("obrisitemu")){
			
			long idtem=Long.parseLong(idtema);
			int idforum=Integer.parseInt(idf);
			dao.deleteTema(idtem);
			dao.deletePorukaTema(idtem); 
			tema=dao.selectTema(idforum);
			 
			
			int brstr=tema.size()/10;
			if(tema.size()%10==1)
				brstr++;
			if(brstr<1)
				brstr++;
			tema=dao.selectTemaoff(idforum, 0);
			request.setAttribute("tema", tema); 
			request.setAttribute("brstr", brstr); 
			request.getRequestDispatcher("tema-admin.jsp").forward(request, response);
			
		}
		else if(akcija.equals("dodajtemu")){
			request.setAttribute("idf", idf); 
			request.getRequestDispatcher("novatemaadmin.jsp").forward(request, response);
			
		}
		else if(akcija.equals("izmeniporuku")){
			int idporuka=Integer.parseInt(idpor);
			long idtem=Long.parseLong(idtema);
			dao.updatePoruka(idporuka, naslov);
			por=dao.selectPoruka(idtem);
			int brstr=por.size()/10;
			if(por.size()%10==1)
			brstr++;
			if(brstr<1)
				brstr++;
			por=dao.selectPorukaoffset(idtem, 0);
			request.setAttribute("por", por); 
			request.setAttribute("brstr", brstr); 
			request.getRequestDispatcher("prikaz-admin.jsp").forward(request, response);
		}
		else if(akcija.equals("obrisiporuku")){
			long idtem=Long.parseLong(idtema);
			int idporuka=Integer.parseInt(idpor);
			dao.deletePoruka(idporuka); 
			por=dao.selectPoruka(idtem);
			int brstr=por.size()/10;
			if(por.size()%10==1)
			brstr++;
			if(brstr<1)
				brstr++;
			por=dao.selectPorukaoffset(idtem, 0);
			request.setAttribute("por", por); 
			request.setAttribute("brstr", brstr); 
			request.getRequestDispatcher("prikaz-admin.jsp").forward(request, response);
		}
		else{
			response.sendRedirect("adminpanel.jsp"); 
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.sendRedirect("adminpanel.jsp"); 
	}

}
