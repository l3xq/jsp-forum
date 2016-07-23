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
<title>WarezBB ~ tema</title>
<link rel="stylesheet" type="text/css" href="design.css">


</head>



<%
	HttpSession sesija = request.getSession();
	Korisnik seskor = (Korisnik) sesija.getAttribute("ulogovanSesija");
	if (seskor != null) {
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
					value="<%=seskor.getUser()%>">
			</div>
			<a href="ServletLogIn">&nbsp;&nbsp;Izloguj se</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
				onclick="location.href='ServletKorisnikPoruke?idkor=<%=seskor.getId()%>'"
				type="button" value="Moje poruke">

		</div>

	</div>
	<div id="okvir1">
		<%
			try {
					DAO dao = new DAO();
					int j = 0;
					ArrayList<Tema> tema = (ArrayList<Tema>) sesija.getAttribute("tema");
					int brstrforum = (Integer) sesija.getAttribute("brstrforum");
					if (tema != null && tema.size() > 0 && brstrforum > 0) {

						Forum forum = dao.selectForumID(tema.get(0).getIdf());
						sesija.setAttribute("ime-foruma", forum.getForum());
		%>



		<div id="levi_deo1">

			<div id="dugme_tema">
				<a style="text-decoration: none;"
					href="ServletProsledi?id=<%=tema.get(0).getIdf()%>">Nova tema</a>
			</div>
			<div id="holder_forum_naslov"><%=forum.getForum()%></div>
			<a href="index.jsp">Pocetna</a>>>>Forum:<%=forum.getForum()%>
			<%
				Korisnik kor = new Korisnik();

							for (Tema pom : tema) {
								kor = dao.selectOsobaID(pom.getUser());
			%>
			<div id="holder_forum">
				<div id="holder_icon"></div>
				<div id="holder-link-opis-tema">
					<a id="link_forum"
						href="ServletPregled?idtem=<%=pom.getIdtema()%>&offset=0"><%=pom.getTema()%></a>
				</div>
				<div id="holder_pregled">
					Zapoceo:<%=kor.getUser()%><br>Vreme postavljanja:<br><%=pom.getDatpost()%></div>
			</div>
			<%
				}
			%>

			<%
				for (int i = 1; i <= brstrforum; i++) {
			%>
			&nbsp;&nbsp;&nbsp;<a
				href="ServletTrol?id=<%=tema.get(0).getIdf()%>&off=<%=j%>"><%=i%></a>
			<%
				j = j + 10;
							}
			%>
		</div>

		<%
			} else {
		%>
		<div id="levi_deo1">
			<div id="dugme_tema">
				<a style="text-decoration: none;"
					href="ServletProsledi?id=<%=sesija.getAttribute("idf")%>">Nova
					tema</a>
			</div>
		</div>
		<%
			}
		%>
		<div id="desni_deo1">

			<div id="log_in">
				<div id="login_naslov">Registracija korisnika</div>
				<form action="ServletRegistracija" method="post">
					<div id="holder">
						<div id="holder_tekst">&nbsp;&nbsp;Korisnik</div>
						<div id="holder_tekst">&nbsp;&nbsp;Lozinka</div>
						<div id="holder_tekst">
							&nbsp;&nbsp;<i>E-mail</i>
						</div>
					</div>
					<div id="holder_input">
						<input id="mali_input" type="text" name="user"> <input
							id="mali_input" type="password" name="pass"> <input
							id="mali_input" type="text" name="email">
					</div>
					<input id="mali_submit" type="submit" name="ulaz" value="Registruj">
					${msg}
				</form>
			</div>
		</div>
	</div>
	<div id="footer">
		<i>Projekat iz internet programiranja</i>
	</div>
	<%}catch(Exception e) {response.sendRedirect("index.jsp");} %>
</body>

<%}else{
	request.setAttribute("msg1", "Niste se ulogovali!");
	request.getRequestDispatcher("index.jsp").forward(request, response);
	}%>

</html>