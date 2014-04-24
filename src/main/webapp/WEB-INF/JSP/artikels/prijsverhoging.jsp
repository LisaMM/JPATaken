<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<c:set var='contextPath' value='${pageContext.servletContext.contextPath}' />
<!DOCTYPE html>
<html lang='nl'>
	<head>
		<title>Prijsverhoging</title>
		<link rel='stylesheet' href='${contextPath}/styles/default.css'>
	</head>
	<body>
		<h1>Prijsverhoging</h1>
		<form action="<c:url value='/artikels/prijsverhoging.htm'/>" method='post' id='verhogingform'>
			<label>Percentage: 
				<input name='percentage' value='${param.percentage}' autofocus type='number'>
			</label> 
			<input type='submit' value='Verhoog prijzen' id='verhogingknop'>
		</form>
		<c:import url='/WEB-INF/JSP/fouten.jsp' />
		<script>
			document.getElementById('verhogingform').onsubmit= function() {
				document.getElementById('verhogingknop').disabled=true;
			};
		</script>
	</body>
</html>