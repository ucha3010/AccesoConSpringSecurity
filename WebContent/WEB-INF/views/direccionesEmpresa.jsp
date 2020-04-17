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
	<title><fmt:message key='label.Addresses' /></title>
	<c:import url="/WEB-INF/views/importHead.jsp" />
	
	<script type="text/javascript">
		function confirmDelete(idDirEmp,idEmp){
			if(confirm("<fmt:message key='Delete.message' />")){
				var url = "<c:url value='/direccionEmpresa/delete/"+idDirEmp+"/"+idEmp+"' />";
				location.href=url;
				return true;
			}
			return false;
		}
	</script>
</head>
<body>
	<c:import url="/WEB-INF/views/menu.jsp" />
	<fmt:message key="language.name" var="nameColSelect"/>
	<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">
		<div class="d-flex">
			<div class="mr-auto p-2">
				<h1><c:out value="${empresa.nombreComercial}" /></h1>
			</div>
			<div class="p-2">
				<button type="button" class="btn fondo-c0c0c0 float-right ml-1 border-color-dam" onclick='location.href="<c:url value='/direccionEmpresa/formulario/0/${empresa.idEmp}'/>"'>
					<fmt:message key="Add.address" />
				</button>
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
					<th onclick="sortTable(${count})"><fmt:message key="label.Type.road" /></th>
					<c:set var="count" value="${count + 1}" scope="page"/>
					<th onclick="sortTable(${count})"><fmt:message key="label.Street" /></th>
					<c:set var="count" value="${count + 1}" scope="page"/>
					<th onclick="sortTable(${count})"><fmt:message key="label.Number" /></th>
					<c:set var="count" value="${count + 1}" scope="page"/>
					<th onclick="sortTable(${count})"><fmt:message key="label.Rest" /></th>
					<c:set var="count" value="${count + 1}" scope="page"/>
					<th onclick="sortTable(${count})"><fmt:message key="label.Postal.code" /></th>
					<c:set var="count" value="${count + 1}" scope="page"/>
					<th onclick="sortTable(${count})"><fmt:message key="label.Province" /></th>
					<c:set var="count" value="${count + 1}" scope="page"/>
					<th onclick="sortTable(${count})"><fmt:message key="label.City" /></th>
					<c:set var="count" value="${count + 1}" scope="page"/>
					<th onclick="sortTable(${count})"><fmt:message key="label.Country" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${direccionesEmpresa}" var="direccion">
				    <tr>
						<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">
							<td class="sin_padding">
								<button type="button" class="btn btn-default" title="<fmt:message key='Edit' />" onclick='location.href="<c:url value='/direccionEmpresa/formulario/${direccion.idDirEmp}/0' />"'>
								  <img src='<c:url value="/resources/imgs/editar.png"/>' class="tamanio_imagen">
								</button>
							</td>
							<td class="sin_padding">
								<button type="button" class="btn btn-default" title="<fmt:message key='Delete' />" onclick="return confirmDelete(${direccion.idDirEmp},${empresa.idEmp})" >
								  <img src='<c:url value="/resources/imgs/borrar.png"/>' class="tamanio_imagen">
								</button>
							</td>
						</sec:authorize>
						<td><c:out value="${direccion.tipoVia}" /></td>
						<td><c:out value="${direccion.nombreVia}" /></td>	
						<td><c:out value="${direccion.numero}" /></td>
						<td><c:out value="${direccion.resto}" /></td>
						<td><c:out value="${direccion.cp}" /></td>	
						<td><c:out value="${direccion.provincia}" /></td>	
						<td><c:out value="${direccion.ciudad}" /></td>
						<td><c:out value="${direccion.pais[nameColSelect]}" /></td>
				    </tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<footer>
		<c:import url="/WEB-INF/views/importFooter.jsp" />
	</footer>
</body>
</html>