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
	<title><fmt:message key="Companies" /></title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<script type="text/javascript" src='<c:url value="/resources/js/jquery.js" />'></script>
	<script type="text/javascript" src='<c:url value="/resources/js/utiles.js" />'></script>
	<link href="<c:url value='/resources/bootstrap-4.3.1-dist/css/bootstrap.min.css'/>" rel="stylesheet" type="text/css" />
	<link href="<c:url value='/resources/css/menu.css'/>" rel="stylesheet" type="text/css" />
	<link href="<c:url value='/resources/css/tablas.css'/>" rel="stylesheet" type="text/css" />
	<script type="text/javascript">
		function confirmDelete(idEmp){
			if(confirm("<fmt:message key='Delete.message' />")){
				var url = "<c:url value='/empresa/"+idEmp+"/delete' />";
				location.href=url;
				return true;
			}
			return false;
		}
		function filtrar() {		
			const resultado = document.querySelector('#resultado');
			const texto = normalizado(formulario.value.toLowerCase());
			resultado.innerHTML = '';
			if(texto === ''){
				$(".collapse").collapse('hide');
			} else {
				<c:forEach items="${empresas}" var="emp" varStatus="status">
					var nombre = normalizado('${emp.nombreComercial}');
					var cif = normalizado('${emp.cif}');
					var email = normalizado('${emp.email}');
					if(nombre.toLowerCase().indexOf(texto) !== -1 || cif.indexOf(texto) !== -1 || email.toLowerCase().indexOf(texto) !== -1){
						resultado.innerHTML += "<a href=\"<c:url value='/empresa/filtered/${emp.idEmp}' />\">${emp.nombreComercial} ${emp.cif} ${emp.email}</a>";
					}
				</c:forEach>
				$(".collapse").collapse('show');
			}
		}
	</script>
</head>
<body>
	<c:import url="/WEB-INF/views/menu.jsp" />
	<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">
		<div class="d-flex">
			<div class="p-2">
				<input type="text" id="formulario" class="form-control">
				<script>
					const formulario = document.querySelector('#formulario');
					formulario.addEventListener('keyup', filtrar);
				</script>
			</div>
			<div class="p-2">
				<div class="dropdown collapse">
					<div class="dropdown-content" id="resultado">
					</div>
				</div>
			</div>
			<div class="p-2">
				<c:if test="${not empty empresa_agregada}">
					<span style="color: green;">
						<fmt:message key="Company.added" />
					</span>
				</c:if>
				<c:if test="${not empty empresa_eliminada}">
					<span style="color: green;">
						<fmt:message key="Company.deleted" />
					</span>
				</c:if>
			</div>
			<div class="ml-auto p-2">
			<button type="button" class="btn fondo-c0c0c0 float-right ml-1 border-color-dam" onclick='location.href="<c:url value='/empresa/0'/>"'>
				<fmt:message key="Add.company" />
			</button>
		</div>		
	</div>
	</sec:authorize>
	<sec:authorize access="!hasAnyRole('ROL_ADMIN','ROL_ROOT')">
		<div class="d-flex">
			<div class="p-2">
				<input type="text" id="formulario" class="form-control">
				<script>
					const formulario = document.querySelector('#formulario');
					formulario.addEventListener('keyup', filtrar);
				</script>
			</div>
			<div class="p-2">
				<div class="dropdown collapse">
					<div class="dropdown-content" id="resultado">
					</div>
				</div>
			</div>
			<div class="ml-auto p-2">
			</div>		
		</div>
	</sec:authorize>
	<div class="divTabla">
		<table id="tablaOrdenar" class="table table-striped">
			<thead>
				<tr>
					<c:set var="count" value="0" scope="page" />
					<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">
						<th colspan="2"></th>
						<c:set var="count" value="${count + 2}" scope="page"/>
					</sec:authorize>
					<th onclick="sortTable(${count})"><fmt:message key="label.Company.name" /></th>
					<c:set var="count" value="${count + 1}" scope="page"/>
					<th onclick="sortTable(${count})"><fmt:message key="label.vat" /></th>
					<c:set var="count" value="${count + 1}" scope="page"/>
					<th onclick="sortTable(${count})"><fmt:message key="label.Email" /></th>
					<c:set var="count" value="${count + 1}" scope="page"/>
					<th onclick="sortTable(${count})"><fmt:message key="label.Phone" /></th>
					<c:set var="count" value="${count + 1}" scope="page"/>
					<th onclick="sortTable(${count})"><fmt:message key="label.Fax" /></th>
					<th class="width-100"><fmt:message key="label.Extras" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${empresas}" var="empresa">
				    <tr title='<fmt:message key="label.Limited.company" />: <c:out value="${empresa.tipoSociedad}" />&#xA;<fmt:message key="label.Activity" />: <c:out value="${empresa.actividad}" />&#xA;<fmt:message key="label.Web.page" />: <c:out value="${empresa.paginaWeb}" />&#xA;<c:out value="${empresa.observaciones}" />'>
						<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">
							<td class="sin_padding">
								<button type="button" class="btn btn-default" title="<fmt:message key='Edit' />" onclick='location.href="<c:url value='/empresa/${empresa.idEmp}' />"'>
								  <img src='<c:url value="/resources/imgs/editar.png"/>' alt="Editar" class="tamanio_imagen">
								</button>
							</td>
							<td class="sin_padding">
								<button type="button" class="btn btn-default" title="<fmt:message key='Delete' />" onclick="return confirmDelete(${empresa.idEmp})">
								  <img src='<c:url value="/resources/imgs/borrar.png"/>' class="tamanio_imagen">
								</button>
							</td>
						</sec:authorize>
						<td><c:out value="${empresa.nombreComercial}" /></td>
						<td><c:out value="${empresa.cif}" /></td>
						<td><c:out value="${empresa.email}" /></td>	
						<td><c:out value="${empresa.telefono}" /></td>
						<td><c:out value="${empresa.fax}" /></td>
						<td class="sin_padding">
							<a title="<fmt:message key="label.Addresses" />" href='<c:url value='/direccionEmpresa/${empresa.idEmp}' />'>
								<img src='<c:url value="/resources/imgs/domicilio.png"/>' class="width-35">
							</a>
							<a title="<fmt:message key="label.Workers" />" href='<c:url value='/usuarioEmpresa/empresa/${empresa.idEmp}' />'>
								<img src='<c:url value="/resources/imgs/usuarios.png"/>' class="margin-left-5porciento width-35">
							</a>
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