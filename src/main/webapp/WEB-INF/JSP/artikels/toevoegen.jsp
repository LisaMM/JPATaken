<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<c:set var='contextPath'
	value='${pageContext.servletContext.contextPath}' />
<!DOCTYPE html>
<html lang='nl'>
<head>
<title>Artikel toevoegen</title>
<link rel='stylesheet' href='${contextPath}/styles/default.css'>
</head>
<body>
	<h1>Artikel toeovegen</h1>
	<form action="<c:url value='/artikels/toevoegen.htm'/>" method='post'
		id='toevoegform'>
		<label>Naam: <input name='naam' value='${param.naam}'
			autofocus>
		</label> <label>Aankoopprijs: <input name='aankoopprijs'
			value='${param.aankoopprijs}' type='number'>
		</label> <label>Verkoopprijs: <input name='verkoopprijs'
			value='${param.verkoopprijs}' type='number'>
		</label> <input type='submit' value='Toevoegen' id='toevoegknop'>
	</form>
	<c:import url='/WEB-INF/JSP/fouten.jsp' />
	<script>
		document.getElementById('toevoegform').onsubmit = function() {
			document.getElementById('toevoegknop').disabled = true;
		};
	</script>
</body>
</html>