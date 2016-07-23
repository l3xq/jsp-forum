<%@page import="rs.Korisnik"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
	<%
		String idf = (String) request.getAttribute("idf");
			if (idf != null) {
	%>

	<div id="okvir">
		<div id="levi_deo_nova_tema">
			<form action="ServletPosTema" method="get">
				<div id="holder_input_tekst">
					<div id="holder_in_tekst1">Naslov:</div>
					<br>
					<div id="holder_in_tekst2">
						<i>Poruka:</i>
					</div>
				</div>

				<div id="holder_input_tema">
					<div id="holder_in_tema1">
						<input type="text" name="naslov" value=""
							style="width: 300px; background-color: #f9f9f9;">
					</div>
					<div id="holder_in_tema2">
						<textarea name="poruka" rows="23" cols="84"
							style="max-width: 610px; max-height: 350px; background-color: #f9f9f9;"></textarea>
					</div>
					<input type="hidden" name="idf" value="<%=idf%>"> <input
						type="hidden" name="user" value="<%=seskor.getId()%>">
					<div id="holder_in_tema3">
						<input type="submit" value="Postavi"> ${msg3}
					</div>
				</div>
			</form>

		</div>
	</div>

	<%
		} else
				response.sendRedirect("tema.jsp");
	%>

	<div id="footer">
		<i>Projekat iz internet programiranja</i>
	</div>

</body>
<%}else{
	request.setAttribute("msg1", "Niste se ulogovali!");
	request.getRequestDispatcher("index.jsp").forward(request, response);
	}%>
</html>