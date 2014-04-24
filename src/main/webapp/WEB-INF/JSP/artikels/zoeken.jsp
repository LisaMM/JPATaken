<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<c:set var='contextPath'
	value='${pageContext.servletContext.contextPath}' />
<!DOCTYPE html>
<html lang='nl'>
	<head>
		<title>Artikels zoeken op woord</title>
		<link rel='stylesheet' href='${contextPath}/styles/default.css'>
	</head>
	<body>
		<h1>Artikels zoeken op woord</h1>
		<form action="<c:url value='/artikels/zoeken.htm'/>" method='get'>
			<label>Woord: 
				<input name='woord' value='${param.woord}' autofocus type='text'>
			</label> 
			<input type='submit' value='Zoeken'>
		</form>
		<c:import url='/WEB-INF/JSP/fouten.jsp' />
		<c:if test="${not empty artikels}">
			<table>
				<thead>
					<tr>
						<th>Nummer</th>
						<th>Naam</th>
						<th>Aankoopprijs</th>
						<th>Verkoopprijs</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items='${artikels}' var='artikel'>
						<tr>
							<td>${artikel.artikelNr}</td>
							<td>${artikel.naam}</td>
							<td>${artikel.aankoopprijs}</td>
							<td>${artikel.verkoopprijs}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</body>
</html>