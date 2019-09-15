<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- language maneja el idioma actual --%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.damian.utils.multilanguage" />

<html>
<head>
	<title>Usuarios</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<script type="text/javascript" src='<c:url value="/resources/js/jquery.js" />'></script>
	<link href="<c:url value='/resources/bootstrap-4.3.1-dist/css/bootstrap.min.css'/>" rel="stylesheet" type="text/css" />
	<link href="<c:url value='/resources/css/menu.css'/>" rel="stylesheet" type="text/css" />
	<link href="<c:url value='/resources/css/tablas.css'/>" rel="stylesheet" type="text/css" />
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
	<div class="divTabla">
		<table id="tablaOrdenar" class="table table-striped">
			<thead>
				<tr>
					<th colspan="2"></th>
					<th onclick="sortTable(2)"><fmt:message key="label.Username" /></th>
					<th onclick="sortTable(3)"><fmt:message key="label.Enabled" /></th>
					<th onclick="sortTable(4)"><fmt:message key="label.Creation.date" /></th>
					<th onclick="sortTable(5)"><fmt:message key="label.idcard" /></th>
					<th onclick="sortTable(6)"><fmt:message key="label.Name" /></th>
					<th onclick="sortTable(7)"><fmt:message key="label.Lastname" /></th>
					<th onclick="sortTable(8)"><fmt:message key="label.Sex" /></th>
					<th onclick="sortTable(9)"><fmt:message key="label.Birthdate" /></th>
					<th onclick="sortTable(10)"><fmt:message key="label.Nationality" /></th>
					<th onclick="sortTable(11)"><fmt:message key="label.Email" /></th>
					<th onclick="sortTable(12)"><fmt:message key="label.Phone" /></th>
					<th onclick="sortTable(13)"><fmt:message key="label.Roles" /></th>
					<th><fmt:message key="label.Extras" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${usuarios}" var="usuario">
				    <tr title='<c:out value="${usuario.datosPersonales.observaciones}" />'>
						<td class="sin_padding">
							<button type="button" class="btn btn-default" onclick='location.href="<c:url value='/usuario/${usuario.idUsr}' />"'>
							  <img src='<c:url value="/resources/imgs/editar.png"/>' alt="Editar" class="tamanio_imagen">
							</button>
						</td>
						<td class="sin_padding">
							<button type="button" class="btn btn-default confirm" onclick='location.href="<c:url value='/usuario/${usuario.idUsr}/delete' />"'>
							  <img src='<c:url value="/resources/imgs/borrar.png"/>' alt="Borrar" class="tamanio_imagen">
							</button>
						</td>
						<td><c:out value="${usuario.usuario}" /></td>
						<td><c:out value="${usuario.habilitado}" /></td>
						<td><fmt:formatDate value="${usuario.fechaCreacion}" pattern="dd/MM/yyyy"/></td>	
						<td><c:out value="${usuario.datosPersonales.dni}" /></td>
						<td><c:out value="${usuario.datosPersonales.nombre}" /></td>
						<td><c:out value="${usuario.datosPersonales.apellido1}" /> <c:out value="${usuario.datosPersonales.apellido2}" /></td>	
						<td><c:out value="${usuario.datosPersonales.sexo}" /></td>	
						<td><fmt:formatDate value="${usuario.datosPersonales.fechaNacimiento}" pattern="dd/MM/yyyy"/></td>		
						<td><c:out value="${usuario.datosPersonales.nacionalidad}" /></td>
						<td><c:out value="${usuario.datosPersonales.email}" /></td>
						<td><c:out value="${usuario.datosPersonales.telefono}" /></td>
						<c:set var="userRoles" value="${usuario.usuarioRoles}" scope="page" />
						<td>
						<c:forEach items="${userRoles}" var="roles">
							<c:out value="${roles.pk.rol.rol}" /><br/>
						</c:forEach>
						</td>
						<td class="sin_padding">
							<button type="button" class="btn btn-info ml-1 btn-sm" onclick='location.href="<c:url value='/direccion/${usuario.idUsr}' />"'>
								<fmt:message key="label.Addresses" />
							</button>
						</td>
				    </tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<script type="text/javascript" src="<c:url value='/resources/js/tabla_ordenar.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/bootstrap-4.3.1-dist/js/bootstrap.min.js'/>"></script>
</body>
</html>