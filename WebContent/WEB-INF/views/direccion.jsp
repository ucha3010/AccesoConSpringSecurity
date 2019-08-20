<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src='<c:url value="/res/js/jquery.js" />'></script>
<script type="text/javascript">
</script>
</head>
<body>
	<c:import url="/WEB-INF/views/menu.jsp" />

	<h1>Direccion.jsp</h1>
	<a href='<c:url value="/"/>'>Index</a><br/>
	<a href='<c:url value="/about"/>'>Acerca de</a><br/>
	<a href='<c:url value="/admin"/>'>Admin</a><br/>
	<br/>
	<br/>
	<br/>
	<sf:form method="post" action="${pageContext.request.contextPath}/direccion/save" modelAttribute="direccion">
<%-- 		<c:if test="${admin.idAd ne 0}">  --%>
<%-- 			<sf:input path="idAd" type="hidden"/> --%>
<%-- 			<sf:input path="fechaCreacion" type="hidden"/> --%>
<%-- 		</c:if> --%>
		<table>
			<tr>
				<td>Calle</td>
				<td><sf:input path="calle" type="text"/></td>
			</tr>
			<tr>
				<td>C.P.</td>
				<td><sf:input path="cp" type="text"/></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Guardar cambios" /></td>
			</tr>			
		</table>
	</sf:form>
	<br/>
	Atributos en Model (resultado): <c:out value="${resultado}"></c:out><br/><br/>
	
	<c:forEach items="${direcciones}" var="direccion">
		<c:out value="${direccion}" />
		<br/>
	</c:forEach>

</body>
</html>