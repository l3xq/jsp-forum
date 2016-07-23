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
<title>WarezBB Forum</title>
<link rel="stylesheet" type="text/css" href="design.css">
</head>

<body>
	<div id="header">
		<div onclick="location.href='index.jsp'" id="logo_holder"></div>

		<%
			HttpSession sesija = request.getSession();
			Korisnik seskor = (Korisnik) sesija.getAttribute("ulogovanSesija");
			if (seskor == null) {
		%>

		<div id="log_in">
			<div id="login_naslov">Prijava na forum</div>
			<form action="ServletLogIn" method="post">
				<div id="holder">
					<div id="holder_tekst">&nbsp;&nbsp;Korisnik</div>
					<div id="holder_tekst">&nbsp;&nbsp;Lozinka</div>
				</div>
				<div id="holder_input">
					<input id="mali_input" type="text" name="user"> <input
						id="mali_input" type="password" name="pass">
				</div>
				<input id="mali_submit" type="submit" name="ulaz" value="Prijavi">
				${msg1}
			</form>
		</div>
		<%
			} else {
		%>
		<div id="log_in">
			<div id="login_naslov">&nbsp;&nbsp;Ulogovan korisnik</div>
			<div id="holder">
				<div id="holder_tekst">&nbsp;&nbsp;Korisnik:&nbsp;</div>
			</div>
			<div id="holder_input">
				<input id="mali_input" type="text" name="user"
					value="<%=seskor.getUser()%>">
			</div>
			<a href="ServletLogIn">Izloguj se</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
				onclick="location.href='ServletKorisnikPoruke?idkor=<%=seskor.getId()%>'"
				type="button" value="Moje poruke">

		</div>


		<%
			}
		%>
	</div>
	<div id="okvir1">
		<div id="levi_deo1">

			<div id="naslov_forum">
				<i>&nbsp;&nbsp;WarezBB</i>
			</div>

			<%
				try {
					DAO dao = new DAO();
					ArrayList<Forum> flista = null;
					flista = dao.selectForum();

					for (int i = 0; i < flista.size(); i++) {
			%>
			<div id="holder_forum">
				<div id="holder_icon"></div>
				<div id="holder-link-opis">
					<a id="link_forum"
						href="ServletTrol?id=<%=flista.get(i).getId()%>&off=0"><%=flista.get(i).getForum()%></a>
				</div>
				<div id="holder_pregled"></div>
			</div>

			<%
				}
			%>


			<%
				} catch (Exception e) {
					response.sendRedirect("index.jsp");
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
</body>
</html>