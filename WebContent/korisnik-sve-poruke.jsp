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
<title>WarezBB ~ poruke</title>
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
			<a href="ServletLogIn">&nbsp;&nbsp;Izloguj se</a>

		</div>

	</div>


	<div id="okvir1">
		<div id="levi_deo1">
			<a href="index.jsp">&nbsp;&nbsp;Pocetna</a>
			<%
				try {
						int j = 0;
						DAO dao = new DAO();
						ArrayList<Poruka> por = (ArrayList<Poruka>) request.getAttribute("lp");

						Korisnik kor = new Korisnik();
						Tema tema = new Tema();
						if (por != null && por.size() > 0) {
							for (Poruka pom : por) {
								tema = dao.selectTemaID(pom.getIdtema());
								kor = dao.selectOsobaID(pom.getUser());
			%>
			<div id="holder_poruka">
				<div id="holder_icon1"></div>
				<div id="holder-poruka-opis">
					<a id="link_forum"
						href="ServletPregled?idtem=<%=tema.getIdtema()%>&offset=0">TEMA:<%=tema.getTema()%></a><br><%=pom.getPoruka()%></div>
				<div id="holder_pregled1">
					Napisao:<%=kor.getUser()%><br>Vreme postavljanja:<br><%=pom.getDatpost()%></div>
			</div>
			<%
				}
						} else {
							response.sendRedirect("index.jsp");
						}
			%>


		</div>

		<div id="desni_deo1">

			<div id="log_in">
				<div id="login_naslov">Registracija korisnika</div>
				<form action="ServletRegistracija" method="post">
					<div id="holder">
						<div id="holder_tekst">Ime:</div>
						<div id="holder_tekst">Lozinka:</div>
						<div id="holder_tekst">Email:</div>
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
	<%
		} catch (Exception e) {
				response.sendRedirect("index.jsp");
			}
	%>
</body>

<%}else{
	request.setAttribute("msg1", "Niste se ulogovali!");
	request.getRequestDispatcher("index.jsp").forward(request, response);
	}%>

</html>