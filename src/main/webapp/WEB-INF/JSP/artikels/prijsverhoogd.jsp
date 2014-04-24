<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<c:set var='contextPath' value='${pageContext.servletContext.contextPath}' />
<!DOCTYPE html>
<html lang='nl'>
	<head>
		<title>Prijs verhoogd</title>
		<link rel='stylesheet' href='${contextPath}/styles/default.css'>
	</head>
	<body>
		<h1>Prijs verhoogd</h1>
		De prijs werd verhoogd
	</body>
</html>