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
	<c:import url="/WEB-INF/views/importHead.jsp" />
	
	<script type="text/javascript" src='<c:url value="/resources/js/utiles.js" />'></script>
	<script type="text/javascript">
		function confirmDelete(idPropia){
			if(confirm("<fmt:message key='Delete.message' />")){
				var url = "<c:url value='/empresaPropia/delete/"+idPropia+"' />";
				location.href=url;
				return true;
			}
			return false;
		}
	</script>
</head>
<body>
	<div class="container-fluid">
		<c:import url="/WEB-INF/views/menu.jsp" />
		<fmt:message key="language.name" var="nameColSelect"/>
		<div class="well well-sm text-center h2"><fmt:message key="Own.company" /></div>
		<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">
			<div class="row">
				<div class="hidden-xs col-sm-5 col-md-8">
				</div>
				<div class="col-sm-4 col-md-2">
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
				<div class="col-sm-3 col-md-2 navbar-right">
					<button type="button" class="btn fondo-c0c0c0 ml-1 border-color-dam" onclick='location.href="<c:url value='/empresaPropia/0'/>"'>
						<fmt:message key="Add.company" />
					</button>
				</div>		
			</div>
		</sec:authorize>
		<div class="divTablaSinScroll">
			<table class="table table-striped">
				<thead>
					<tr class="cursor-pointer">
						<c:set var="count" value="0" scope="page" />
						<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">
							<th class="extraAdmin-th cursor-text"></th>
						</sec:authorize>
						<th><fmt:message key="label.Company.name" /></th>
						<th><fmt:message key="label.vat" /></th>
						<th><fmt:message key="label.Email" /></th>
						<th><fmt:message key="label.Phone" /></th>
						<th><fmt:message key="label.Fax" /></th>
						<th class="extraAdmin-th cursor-text"><fmt:message key="label.Extras" /></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${empresaPropias}" var="empresaPropia">
					    <tr title='<fmt:message key='${empresaPropia.direccionEmpresaPropia.tipoVia}' />&#x20;<c:out value="${empresaPropia.direccionEmpresaPropia.nombreVia}" />&#x20;<c:out value="${empresaPropia.direccionEmpresaPropia.numero}" />&#x20;<c:out value="${empresaPropia.direccionEmpresaPropia.resto}" />&#xA;(<c:out value="${empresaPropia.direccionEmpresaPropia.cp}" />) <c:out value="${empresaPropia.direccionEmpresaPropia.ciudad}" />&#xA;<c:out value="${empresaPropia.direccionEmpresaPropia.provincia}" />&#xA;<c:out value="${empresaPropia.direccionEmpresaPropia.pais[nameColSelect]}" />'>
							<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">
								<td class="extraAdmin-td">
									<a title="<fmt:message key='Edit' />" onclick='location.href="<c:url value='/empresaPropia/${empresaPropia.idPropia}' />"'>
										<img src='<c:url value="/resources/imgs/editar.png"/>' class="tamanio_imagen">
									</a>
									<a title="<fmt:message key='Delete' />" onclick="return confirmDelete(${empresaPropia.idPropia})">
										<img src='<c:url value="/resources/imgs/borrar.png"/>' class="tamanio_imagen">
									</a>
								</td>
							</sec:authorize>
							<td><c:out value="${empresaPropia.razonSocial}" /></td>
							<td><c:out value="${empresaPropia.cif}" /></td>
							<td><c:out value="${empresaPropia.email}" /></td>	
							<td><c:out value="${empresaPropia.telefono}" /></td>
							<td><c:out value="${empresaPropia.fax}" /></td>
							<td class="extraAdmin-td">
								<c:if test="${empresaPropia.facturacion}">
									<img src='<c:url value="/resources/imgs/true.png"/>' class="tamanio_imagen">
								</c:if>
								<c:if test="${not empresaPropia.facturacion}">
									<img src='<c:url value="/resources/imgs/false.png"/>' class="tamanio_imagen cursor-pointer" onclick='location.href="<c:url value='/empresaPropia/available/${empresaPropia.idPropia}' />"'>
								</c:if>
							</td>
					    </tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		
		<footer>
			<c:import url="/WEB-INF/views/importFooter.jsp" />
		</footer>
	</div>
</body>
</html>