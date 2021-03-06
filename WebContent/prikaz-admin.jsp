<%@page import="rs.Poruka"%>
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
<title>WarezBB ~ Admin view</title>
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
		<div id="levi_deo1">
			<%
				try {
						int j = 0;
						DAO dao = new DAO();
						ArrayList<Poruka> por = (ArrayList<Poruka>) request.getAttribute("por");
						Tema tema = dao.selectTemaID(por.get(0).getIdtema());
						int brstr = (Integer) request.getAttribute("brstr");
			%>

			<div id="holder_forum_naslov"><%=tema.getTema()%></div>
			<%
				Korisnik kor = new Korisnik();

						if (por != null && por.size() > 0) {
							for (Poruka pom : por) {
								kor = dao.selectOsobaID(pom.getUser());
			%>
			<form action="ServletAdmin" method="get">
				<div id="holder_poruka">
					<div id="holder_icon1"></div>
					<div id="holder-poruka-opis">


						<input type="text" name="vrednost" value="<%=pom.getPoruka()%>">

						<input type="hidden" name="akcija" value="izmeniporuku"> <input
							type="hidden" name="idpor" value="<%=pom.getIdpor()%>"> <input
							type="hidden" name="idtema" value="<%=pom.getIdtema()%>">



					</div>
					<div id="holder_pregled1">
						Napisao:<%=kor.getUser()%><br>Vreme postavljanja:<br><%=pom.getDatpost()%></div>
				</div>

				<input type="button" value="Obrisi poruku"
					onclick="location.href='ServletAdmin?akcija=obrisiporuku&idpor=<%=pom.getIdpor()%>&idtema=<%=pom.getIdtema()%>'">
				<input type="submit" value="Izmeni poruku"> <input
					type="button" value="Dodaj poruku"
					onclick="location.href='ServletProsledi?idpor=<%=por.get(0).getIdtema()%>'">
				<br>
			</form>
			<%
				}
						} else {
							response.sendRedirect("index.jsp");
						}
			%>

			<%
				for (int i = 1; i <= brstr; i++) {
			%>
			&nbsp;&nbsp;&nbsp;<a
				href="ServletPregled?idtem=<%=por.get(0).getIdtema()%>&offset=<%=j%>"><%=i%></a>
			<%
				j = j + 10;
						}
			%>
		</div>

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