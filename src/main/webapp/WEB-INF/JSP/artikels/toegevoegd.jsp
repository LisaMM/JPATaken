<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<c:set var='contextPath' value='${pageContext.servletContext.contextPath}' />
<!doctype html>
<html lang='nl'>
	<head>
		<title>Artikel toegevoegd</title>
		<link rel='stylesheet' href='${contextPath}/styles/default.css'>
	</head>
	<body>
		<h1>Artikel toegevoegd</h1>
		Het nummer van het toegevoegde artikel is ${param.artikelNr}
		<div><a href="<c:url value='/'/>">Menu</a></div>
	</body>
</html>