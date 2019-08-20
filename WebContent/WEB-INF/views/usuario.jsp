<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src='<c:url value="/resources/js/jquery.js" />'></script>
	<link href="<c:url value='/resources/css/menu.css'/>" rel="stylesheet" type="text/css" />
<script type="text/javascript">
jQuery(document).ready(function(){
	jQuery(".confirm").on("click", function(){
		return confirm("Si eliminas este elemento no se podrá recuperar. ¿Continuar?");
	})
});
</script>
</head>
<body>
	<c:import url="/WEB-INF/views/menu.jsp" />
	<h1>usuario.jsp</h1>
	<a href='<c:url value="/"/>'>Index</a><br/>
	<a href='<c:url value="/about"/>'>Acerca de</a><br/>
	<br/>
	<br/>
	<br/>
	<sf:form method="post" action="${pageContext.request.contextPath}/usuario/save" modelAttribute="usuario">
		<table>
			<tr>
				<td>Usuario</td>
				<td>
					<sf:input path="usuario" type="text"/>
					<sf:errors path="usuario" cssStyle="color:red"/>
				</td>
			</tr>
			<tr>
				<td>Contraseña</td>
				<td>
					<sf:input path="clave" type="text"/>
					<sf:errors path="clave" cssStyle="color:red"/>
				</td>
			</tr>
			<tr>
				<td>Permisos</td>
				<td>
					<sf:input path="permiso" type="text"/>
					<sf:errors path="permiso" cssStyle="color:red"/>
				</td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Guardar cambios" /></td>
			</tr>			
		</table>
	</sf:form>
	<br/>
	<c:out value="${resultado}"></c:out><br/><br/>
	
	<c:forEach items="${usuarios}" var="usuario">
		<c:out value="${usuario}" />
		<br/>
	</c:forEach>

</body>
</html>