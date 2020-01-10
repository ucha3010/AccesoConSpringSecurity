<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
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
	<link href="<c:url value='/resources/bootstrap-4.3.1-dist/css/bootstrap.min.css'/>" rel="stylesheet" type="text/css" />
	<link href="<c:url value='/resources/css/menu.css'/>" rel="stylesheet" type="text/css" />
	<link href="<c:url value='/resources/css/tablas.css'/>" rel="stylesheet" type="text/css" />
</head>
<body>
	<c:import url="/WEB-INF/views/menu.jsp" />
	<div>
		<h2><fmt:message key="label.Product.description" />: <c:out value="${producto.descripcion}" /></h2>
	</div>
	<div class="form-row">		
		<div class="col-sm-3">
		</div>
		<div class="col-xs-12 col-sm-9">
			<div class="margin-left-5porciento">
				<table id="tablaOrdenar" class="table-striped">
					<c:forEach items="${productoEmpresas}" var="productoEmpresa">
					    <tr class="border-1px-ddd" title='<fmt:message key="label.vat" />: <c:out value="${productoEmpresa.empresa.cif}" />&#xA;<fmt:message key="label.Email" />: <c:out value="${productoEmpresa.empresa.email}" />&#xA;<fmt:message key="label.Phone" />: <c:out value="${productoEmpresa.empresa.telefono}" />'>
					    	<td class="padding-5-25-dam">
						    	<c:out value="${productoEmpresa.empresa.nombreComercial}" />
					    	</td>
							<td class="padding-5-25-dam">
								<a title="<fmt:message key="Companies" />" href='<c:url value='/empresa/filtered/${productoEmpresa.empresa.idEmp}' />'>
									<img src='<c:url value="/resources/imgs/empresa.png"/>' class="margin-left-5porciento width-35">
								</a>
							</td>
					    </tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="<c:url value='/resources/js/tabla_ordenar.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/bootstrap-4.3.1-dist/js/bootstrap.min.js'/>"></script>
</body>
</html>