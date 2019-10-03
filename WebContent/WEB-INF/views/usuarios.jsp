<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- language maneja el idioma actual --%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.damian.utils.multilanguage" />

<html>
<head>
	<title><fmt:message key="label.Users" /></title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<script type="text/javascript" src='<c:url value="/resources/js/jquery.js" />'></script>
	<link href="<c:url value='/resources/bootstrap-4.3.1-dist/css/bootstrap.min.css'/>" rel="stylesheet" type="text/css" />
	<link href="<c:url value='/resources/css/menu.css'/>" rel="stylesheet" type="text/css" />
	<link href="<c:url value='/resources/css/tablas.css'/>" rel="stylesheet" type="text/css" />
	<script type="text/javascript">
		function confirmDelete(idUsr){
			if(confirm("<fmt:message key='Delete.message' />")){
				var url = "<c:url value='/usuario/"+idUsr+"/delete' />";
				location.href=url;
				return true;
			}
			return false;
		}
		function confirmReset(idUsr){
			if(confirm("<fmt:message key='Reset.message' />")){
				var url = "<c:url value='/usuario/reset/"+idUsr+"' />";
				location.href=url;
				return true;
			}
			return false;
		}
	</script>
</head>
<body>
	<c:import url="/WEB-INF/views/menu.jsp" />
	<div class="divTabla">
		<table id="tablaOrdenar" class="table table-striped">
			<thead>
				<tr>
					<c:set var="count" value="0" scope="page" />
					<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">
						<th colspan="2"></th>
						<c:set var="count" value="${count + 2}" scope="page"/>
					</sec:authorize>
					<th onclick="sortTable(${count})"><fmt:message key="label.Username" /></th>
					<c:set var="count" value="${count + 1}" scope="page"/>
					<th onclick="sortTable(${count})"></th>
					<c:set var="count" value="${count + 1}" scope="page"/>
<%-- 					<th onclick="sortTable(${count})"><fmt:message key="label.Creation.date" /></th> --%>
<%-- 					<c:set var="count" value="${count + 1}" scope="page"/> --%>
<%-- 					<th onclick="sortTable(${count})"><fmt:message key="label.idcard" /></th> --%>
<%-- 					<c:set var="count" value="${count + 1}" scope="page"/> --%>
					<th onclick="sortTable(${count})"><fmt:message key="label.Name" /></th>
					<c:set var="count" value="${count + 1}" scope="page"/>
					<th onclick="sortTable(${count})"><fmt:message key="label.Lastname" /></th>
					<c:set var="count" value="${count + 1}" scope="page"/>
<%-- 					<th onclick="sortTable(${count})"><fmt:message key="label.Sex" /></th> --%>
<%-- 					<c:set var="count" value="${count + 1}" scope="page"/> --%>
					<th onclick="sortTable(${count})"><fmt:message key="label.Birthdate" /></th>
					<c:set var="count" value="${count + 1}" scope="page"/>
<%-- 					<th onclick="sortTable(${count})"><fmt:message key="label.Nationality" /></th> --%>
<%-- 					<c:set var="count" value="${count + 1}" scope="page"/> --%>
					<th onclick="sortTable(${count})"><fmt:message key="label.Email" /></th>
					<c:set var="count" value="${count + 1}" scope="page"/>
					<th onclick="sortTable(${count})"><fmt:message key="label.Phone" /></th>
					<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">
						<c:set var="count" value="${count + 1}" scope="page"/>
						<th onclick="sortTable(${count})"><fmt:message key="label.Roles" /></th>
					</sec:authorize>
					<th class="width-150"><fmt:message key="label.Extras" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${usuarios}" var="usuario">
				    <tr title='<fmt:message key="label.Creation.date" />: <fmt:formatDate value="${usuario.fechaCreacion}" pattern="dd/MM/yyyy"/>&#xA;<fmt:message key="label.idcard" />: <c:out value="${usuario.datosPersonales.dni}" />&#xA;<fmt:message key="label.Sex" />: <c:out value="${usuario.datosPersonales.sexo}" />&#xA;<fmt:message key="label.Nationality" />: <c:out value="${usuario.datosPersonales.nacionalidad}" />&#xA;<c:out value="${usuario.datosPersonales.observaciones}" />'>
						<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">
							<td class="sin_padding">
								<button type="button" class="btn btn-default" title="<fmt:message key='Edit' />" onclick='location.href="<c:url value='/usuario/${usuario.idUsr}' />"'>
								  <img src='<c:url value="/resources/imgs/editar.png"/>' alt="Editar" class="tamanio_imagen">
								</button>
							</td>
							<td class="sin_padding">
								<button type="button" class="btn btn-default" title="<fmt:message key='Delete' />" onclick="return confirmDelete(${usuario.idUsr})">
								  <img src='<c:url value="/resources/imgs/borrar.png"/>' class="tamanio_imagen">
								</button>
							</td>
						</sec:authorize>
						<td><c:out value="${usuario.usuario}" /></td>
						
						<sec:authorize access="!hasAnyRole('ROL_ADMIN','ROL_ROOT')">
							<c:if test="${usuario.habilitado}">
								<td><img src='<c:url value="/resources/imgs/true.png"/>' class="tamanio_imagen"></td>
							</c:if>
							<c:if test="${not usuario.habilitado}">
								<td><img src='<c:url value="/resources/imgs/false.png"/>' class="tamanio_imagen"></td>
							</c:if>
						</sec:authorize>
						
						<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">
							<c:if test="${usuario.habilitado}">
								<td><img src='<c:url value="/resources/imgs/true.png"/>' class="tamanio_imagen cursor-pointer" onclick='location.href="<c:url value='/usuario/available/${usuario.idUsr}' />"'></td>
							</c:if>
							<c:if test="${not usuario.habilitado}">
								<td><img src='<c:url value="/resources/imgs/false.png"/>' class="tamanio_imagen cursor-pointer" onclick='location.href="<c:url value='/usuario/available/${usuario.idUsr}' />"'></td>
							</c:if>
						</sec:authorize>
						
<%-- 						<td><fmt:formatDate value="${usuario.fechaCreacion}" pattern="dd/MM/yyyy"/></td>	 --%>
<%-- 						<td><c:out value="${usuario.datosPersonales.dni}" /></td> --%>
						<td><c:out value="${usuario.datosPersonales.nombre}" /></td>
						<td><c:out value="${usuario.datosPersonales.apellido1}" /> <c:out value="${usuario.datosPersonales.apellido2}" /></td>	
<%-- 						<td><c:out value="${usuario.datosPersonales.sexo}" /></td>	 --%>
						<td><fmt:formatDate value="${usuario.datosPersonales.fechaNacimiento}" pattern="dd/MM/yyyy"/></td>		
<%-- 						<td><c:out value="${usuario.datosPersonales.nacionalidad}" /></td> --%>
						<td><c:out value="${usuario.datosPersonales.email}" /></td>
						<td><c:out value="${usuario.datosPersonales.telefono}" /></td>
						<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">
							<c:set var="userRoles" value="${usuario.usuarioRol}" scope="page" />
							<td>
								<c:forEach items="${userRoles}" var="roles">
									<span class="colorFondo${roles.rol.idRol}"><c:out value="${roles.rol.rol}" /></span><br/>
								</c:forEach>
							</td>
						</sec:authorize>
						<td class="sin_padding">
							<a title="<fmt:message key="label.Addresses" />" href='<c:url value='/direccion/${usuario.idUsr}' />'>
								<img src='<c:url value="/resources/imgs/domicilio.png"/>' class="width-35">
							</a>
							<a title="<fmt:message key="Companies" />" href='<c:url value='/empresa/${usuario.idUsr}' />'>
								<img src='<c:url value="/resources/imgs/empresa.png"/>' class="margin-left-5porciento width-35">
							</a>
							<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">
								<button type="button" class="btn btn-default" title="<fmt:message key="Pass.reset" />" onclick="return confirmReset(${usuario.idUsr})">
									<img src='<c:url value="/resources/imgs/pass_reset.png"/>' class="margin-left-5porciento width-35">
								</button>
							</sec:authorize>
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