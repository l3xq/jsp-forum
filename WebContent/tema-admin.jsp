<%@page import="rs.Tema"%>
<%@page import="rs.Forum"%>
<%@page import="java.util.ArrayList"%>
<%@page import="rs.DAO"%>
<%@page import="rs.Korisnik"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WarezBB ~ Admin tema</title>
<link rel="stylesheet" type="text/css" href="design.css">


</head>



<%
	HttpSession sesija = request.getSession();
	Korisnik admin = (Korisnik) sesija.getAttribute("ulogovanAdmin");
	if (admin != null) {
%>
<body>


	<div id="header">
		<div onclick="location.href='index.jsp'" id="logo_holder"></div>


		<div id="log_in">
			<div id="login_naslov">Ulogovan korisnik</div>
			<div id="holder">
				<div id="holder_tekst">&nbsp;&nbsp;Korisnik:&nbsp;</div>
			</div>
			<div id="holder_input">
				<input id="mali_input" type="text" name="user"
					value="<%=admin.getUser()%>">
			</div>
			<a href="ServletLogIn">&nbsp;&nbsp;Izloguj se</a>

		</div>

	</div>
	<div id="okvir1">
		<%
			try {
					DAO dao = new DAO();
					int j = 0;
					ArrayList<Tema> tema = (ArrayList<Tema>) request.getAttribute("tema");
					int brstrforum = (Integer) request.getAttribute("brstr");
					if (tema != null && tema.size() > 0 && brstrforum > 0) {

						Forum forum = dao.selectForumID(tema.get(0).getIdf());
		%>



		<div id="levi_deo1">


			<div id="holder_forum_naslov"><%=forum.getForum()%></div>
			<input type="button" value="Dodaj temu"
				onclick="location.href='ServletAdmin?akcija=dodajtemu&idf=<%=tema.get(0).getIdf()%>'">
			<br>
			<%
				Korisnik kor = new Korisnik();

							for (Tema pom : tema) {
								kor = dao.selectOsobaID(pom.getUser());
			%>
			<form action="ServletAdmin" method="get">
				<div id="holder_forum">
					<div id="holder_icon"></div>
					<div id="holder-link-opis">
						<input type="text" name="vrednost" value="<%=pom.getTema()%>">
						<a href="ServletPregled?idtem=<%=pom.getIdtema()%>&offset=0">Dalje</a>
						<input type="hidden" name="akcija" value="izmenitemu"> <input
							type="hidden" name="idtema" value="<%=pom.getIdtema()%>">
						<input type="hidden" name="idf" value="<%=pom.getIdf()%>">
					</div>
					<div id="holder_pregled">
						Zapoceo:<%=kor.getUser()%><br>Vreme postavljanja:<br><%=pom.getDatpost()%></div>
				</div>
				<input type="button" value="Obrisi temu"
					onclick="location.href='ServletAdmin?akcija=obrisitemu&idtema=<%=pom.getIdtema()%>&idf=<%=pom.getIdf()%>'">
				<input type="submit" value="Izmeni temu">

			</form>
			<%
				}
			%>

			<%
				for (int i = 1; i <= brstrforum; i++) {
			%>
			&nbsp;&nbsp;&nbsp;<a
				href="ServletAdminTema?id=<%=tema.get(0).getIdf()%>&off=<%=j%>"><%=i%></a>
			<%
				j = j + 10;
							}
			%>
		</div>
		<%
			} else {
		%>
		<div id="levi_deo1">
			<input type="button" value="Dodaj temu"
				onclick="location.href='ServletAdmin?akcija=dodajtemu&idf=<%=sesija.getAttribute("idf")%>'">
			<br>
		</div>
		<%
			}
		%>
	</div>
	<div id="footer">
		<i>Projekat iz internet programiranja</i>
	</div>
	<%
		} catch (Exception e) {
				response.sendRedirect("adminpanel.jsp");
			}
	%>
</body>

<%}else{
	request.setAttribute("msg1", "Niste se ulogovali!");
	request.getRequestDispatcher("adminpanel.jsp").forward(request, response);
	}%>

</html>