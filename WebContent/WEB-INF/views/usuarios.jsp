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
	<link href="<c:url value='/resources/bootstrap-4.3.1-dist/css/bootstrap.min.css'/>" rel="stylesheet" type="text/css" />
	<link href="<c:url value='/resources/css/menu.css'/>" rel="stylesheet" type="text/css" />
</head>
<body>
	<c:import url="/WEB-INF/views/menu.jsp" />
	
	<h2><c:out value="${resultado}"/></h2>
	
	<table class="table table-striped table-margin-dam">
	  <thead>
	    <tr>
	      <th scope="col"><fmt:message key="label.Username" /></th>
	      <th scope="col"><fmt:message key="label.Enabled" /></th>
	      <th scope="col"><fmt:message key="label.Creation.date" /></th>
	      <th scope="col"><fmt:message key="label.idcard" /></th>
	      <th scope="col"><fmt:message key="label.Name" /></th>
	      <th scope="col"><fmt:message key="label.Lastname" /></th>
	      <th scope="col"><fmt:message key="label.Sex" /></th>
	      <th scope="col"><fmt:message key="label.Birthdate" /></th>
	      <th scope="col"><fmt:message key="label.Nationality" /></th>
	      <th scope="col"><fmt:message key="label.Email" /></th>
	      <th scope="col"><fmt:message key="label.Roles" /></th>
	      <th scope="col" colspan="3"><fmt:message key="label.Extras" /></th>
	    </tr>
	  </thead>
	  <tbody>
			<c:forEach items="${usuarios}" var="usuario">
			    <tr>
					<td><c:out value="${usuario.usuario}" /></td>
					<td><c:out value="${usuario.habilitado}" /></td>
					<td><fmt:formatDate value="${usuario.fechaCreacion}" pattern="dd/MM/yyyy"/></td>	
					<td><c:out value="${usuario.datosPersonales.dni}" /></td>
					<td><c:out value="${usuario.datosPersonales.nombre}" /></td>
					<td><c:out value="${usuario.datosPersonales.apellido}" /></td>	
					<td><c:out value="${usuario.datosPersonales.sexo}" /></td>	
					<td><fmt:formatDate value="${usuario.datosPersonales.fechaNacimiento}" pattern="dd/MM/yyyy"/></td>		
					<td><c:out value="${usuario.datosPersonales.nacionalidad}" /></td>
					<td><c:out value="${usuario.datosPersonales.email}" /></td>
					<c:set var="userRoles" value="${usuario.usuarioRoles}" scope="page" />
					<td>
					<c:forEach items="${userRoles}" var="roles">
						<c:out value="${roles.pk.roles.rol}" /><br/>
					</c:forEach>
					</td>
					<td>
						<button type="button" class="btn btn-info ml-1 btn-sm" onclick='location.href="<c:url value='/direccion/${usuario.idUsr}' />"'>
							<fmt:message key="label.Addresses" />
						</button>
					</td>
					<td>
						<button type="button" class="btn btn-default" onclick='location.href="<c:url value='/usuario/${usuario.idUsr}' />"'>
						  <img src='<c:url value="/resources/imgs/editar.png"/>' alt="Editar" class="EditarImg">
						</button>
					</td>
					<td>
						<button type="button" class="btn btn-default" onclick='location.href="<c:url value='/usuario/${usuario.idUsr}/delete' />"'>
						  <img src='<c:url value="/resources/imgs/borrar.png"/>' alt="Borrar" class="EditarImg">
						</button>
					</td>
							
			    </tr>
			</c:forEach>
	  </tbody>
	</table>
	
	<script type="text/javascript" src="<c:url value='/resources/js/jquery.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/bootstrap-4.3.1-dist/js/bootstrap.min.js'/>"></script>
</body>
</html>