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
	<h1>Artikel toevoegen</h1>
	<form action="<c:url value='/artikels/toevoegen.htm'/>" method='post'
		id='toevoegform' class='ondermekaar'>
		<label>Naam: <input name='naam' value='${param.naam}'
			autofocus>
		</label> <label>Aankoopprijs: <input name='aankoopprijs'
			value='${param.aankoopprijs}' type='number'>
		</label> <label>Verkoopprijs: <input name='verkoopprijs'
			value='${param.verkoopprijs}' type='number'>
		</label>
		<div>
			<label><input name="soort" value="F" type="radio"
				${param.soort == "F" ? "checked" : "" }>Food</label>
		</div>
		<label>Houdbaarheid: <input name="houdbaarheid" value="${param.houdbaarheid}"
			type="number"></label>
		<div>
			<label><input name="soort" value="NF" type="radio"
				${param.soort == "NF" ? "checked" : ""}>Non-Food</label>
		</div>
		<label>Garantie: <input name="garantie"
			value="${param.garantie}" type="number"></label> <input type='submit'
			value='Toevoegen' id='toevoegknop'>
	</form>
	<c:import url='/WEB-INF/JSP/fouten.jsp' />
	<script>
		document.getElementById('toevoegform').onsubmit = function() {
			document.getElementById('toevoegknop').disabled = true;
		};
	</script>
</body>
</html>