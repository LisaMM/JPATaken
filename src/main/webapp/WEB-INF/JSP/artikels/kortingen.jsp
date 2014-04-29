<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var='contextPath' value='${pageContext.servletContext.contextPath}' />
<!DOCTYPE html>
<html lang='nl'>
	<head>
		<title>Artikelkortingen</title>
		<link rel='stylesheet' href='${contextPath}/styles/default.css'>
	</head>
	<body>
		<h1>Artikelkortingen</h1>
			<ul class="zonderbolletjes">
				<c:forEach items='${artikels}' var='art'>
					<c:url value='/artikels/kortingen.htm' var='artikelURL'>
						<c:param name='artikelNr' value='${art.artikelNr}'/>
					</c:url>
					<li><a href="${artikelURL}">${art.naam}</a></li>
				</c:forEach>
			</ul>
			<c:if test="${not empty artikel}">
				<h2>${artikel.naam}</h2>
				<table>
					<thead>
						<tr>
							<th>Vanaf aantal</th>
							<th>% korting</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items='${artikel.kortingen}' var='korting'>
						<tr>
							<td class="rechts">${korting.vanafAantal}</td>
							<td class="rechts"><fmt:formatNumber
								value="${korting.kortingsPercentage}" minFractionDigits="2"
								maxFractionDigits="2" /></td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</c:if>
	</body>
</html>