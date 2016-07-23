<%@page import="rs.Korisnik"%>
<%@page import="rs.Forum"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WarezBB ~ Admin panel</title>
<link rel="stylesheet" type="text/css" href="design.css">
</head>
<%
	try {
		HttpSession sesija = request.getSession();
		Korisnik admin = (Korisnik) sesija.getAttribute("ulogovanAdmin");
		if (admin != null) {
%>
<body>
	<div id="header">
		<div onclick="location.href='index.jsp'" id="logo_holder"></div>
	</div>

	<div id="okvir1">

		<div id="levi_deo1">
			<%
				ArrayList<Forum> forum = (ArrayList<Forum>) request.getAttribute("forum");

						if (forum != null && forum.size() > 0) {

							for (int i = 0; i < forum.size(); i++) {
			%>
			<form action="ServletAdmin" method="get">
				<div id="holder_forum">
					<div id="holder_icon"></div>
					<div id="holder-link-opis">
						<input type="text" name="vrednost"
							value="<%=forum.get(i).getForum()%>"> <a
							href="ServletAdminTema?id=<%=forum.get(i).getId()%>&off=0">Dalje</a>
						<input type="hidden" name="idf" value="<%=forum.get(i).getId()%>">

					</div>
					<div id="holder_pregled">
						<input type="submit" name="akcija" value="Izmeni"><br>
						<input type="submit" name="akcija" value="Dodaj"><br>
						<input type="submit" name="akcija" value="Izbrisi">
					</div>
				</div>
			</form>
			<%
				}
						} else {
			%>

			<%
				}
			%>

		</div>

		<div id="desni_deo">
			<i>Admin panel</i><br> <br> <a href="ServletLogIn">AdminLogOut</a><br>
			<br> <br> <br> <input type="button"
				value="Lista svih foruma"
				onclick="location.href='ServletAdmin?akcija=forum'">

		</div>

	</div>
	<div id="footer">
		<i>Projekat iz internet programiranja</i>
	</div>

</body>
<%}else{
	response.sendRedirect("index.jsp");
}}
catch(Exception e){
response.sendRedirect("index.jsp");}
%>
</html>