<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin</title>
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
	<h1>admin.jsp</h1>
	<a href='<c:url value="/"/>'>Index</a><br/>
	<a href='<c:url value="/about"/>'>Acerca de</a><br/>
	<br/>
	Atributos del Model: <c:out value="${mensaje}" /><br/>
	Atributos en Session (estoy): <c:out value="${sessionScope.estoy}" /><br/>
	Atributos en Session (resultado): <c:out value="${sessionScope.resultado}" /><br/>
	Atributos en Session (nombre): <c:out value="${sessionScope.nombre}" /><br/>
	<br/>
	<br/>
	<br/>
	<sf:form method="post" action="${pageContext.request.contextPath}/admin/save" modelAttribute="admin">
		<input name="estado" type="text" />
		<c:if test="${admin.idAd ne 0}"> 
			<sf:input path="idAd" type="hidden"/>
			<sf:input path="fechaCreacion" type="hidden"/>
		</c:if>
		<table>
			<tr>
				<td>Nombre</td>
				<td><sf:input path="nombre" type="text"/></td>
			</tr>
			<tr>
				<td>Cargo</td>
				<td><sf:input path="cargo" type="text"/></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Guardar cambios" /></td>
			</tr>			
		</table>
	</sf:form>
	<br/>
	Atributos en Model (resultado): <c:out value="${resultado}"></c:out><br/><br/>
	
	<c:forEach items="${admins}" var="admin">
		<c:out value="${admin}" />
		<a href='<c:url value="/direccion/${admin.idAd}" />'>Direcciones</a>
		<a href='<c:url value="/admin/${admin.idAd}/update" />'>Actualizar</a>
		<a class="confirm" href='<c:url value="/admin/${admin.idAd}/delete" />'>Eliminar</a>
		<br/>
	</c:forEach>

</body>
</html>