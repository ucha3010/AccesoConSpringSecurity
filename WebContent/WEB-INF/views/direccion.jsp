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
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Usuario</title>
	<script type="text/javascript" src='<c:url value="/resources/js/jquery.js" />'></script>
	<link href="<c:url value='/resources/bootstrap-4.3.1-dist/css/bootstrap.min.css'/>" rel="stylesheet" type="text/css" />
	<link href="<c:url value='/resources/css/menu.css'/>" rel="stylesheet" type="text/css" />
</head>
<body>
	<c:import url="/WEB-INF/views/menu.jsp" />
	<c:if test="${direccion.idDir != 0}">
		<div class="form-group col-xs-12 text-center">
			<h1>
				<c:out value="${direccion.datosPersonales.nombre}" /> <c:out value="${direccion.datosPersonales.apellido1}" /> <c:out value="${direccion.datosPersonales.apellido2}" />
				<button type="button" class="btn btn-primary margin-left-5porciento" onclick='location.href="<c:url value='/direccion/${direccion.datosPersonales.usuario.idUsr}' />"'><fmt:message key="Addresses.of" /> <c:out value="${direccion.datosPersonales.nombre}" /></button>
			</h1>
		</div>
	</c:if>
	<sf:form method="post" action="${pageContext.request.contextPath}/direccion/save/${direccion.datosPersonales.usuario.idUsr}" modelAttribute="direccion">
		<c:if test="${direccion.idDir != 0}">
			<sf:hidden path="idDir"/>
		</c:if>
		
		<div class="form-group col-xs-12 col-sm-6">
			<label for="tipoVia"><fmt:message key="label.Type.road" /></label>
			<sf:select class="form-control" id="tipoVia" path="tipoVia">
				<sf:option value="Calle" />
				<sf:option value="Avenida" />
				<sf:option value="Paseo" />
				<sf:option value="Plaza" />
			</sf:select>
		</div>
		<div class="form-group col-xs-12 col-sm-6">
			<label for="nombreVia"><fmt:message key="label.Street" /></label> 
			<sf:input path="nombreVia" class="form-control" id="nombreVia" />
		</div>
		<div class="form-group col-xs-12 col-sm-6">
			<label for="numero"><fmt:message key="label.Number" /></label>
			<sf:input path="numero" type="text" class="form-control" id="numero" />
		</div>
		<div class="form-group col-xs-12 col-sm-6">
			<label for="resto"><fmt:message key="label.Rest" /></label>
			<sf:input path="resto" type="text" class="form-control" id="resto" />
		</div>
		<div class="form-group col-xs-12 col-sm-6">
			<label for="cp"><fmt:message key="label.Postal.code" /></label>
			<sf:input path="cp" type="text" class="form-control" id="cp" />
		</div>
		<div class="form-group col-xs-12 col-sm-6">
			<label for="provincia"><fmt:message key="label.Province" /></label>
			<sf:input path="provincia" type="text" class="form-control" id="provincia" />
		</div>
		<div class="form-group col-xs-12 col-sm-6">
			<label for="ciudad"><fmt:message key="label.City" /></label>
			<sf:input path="ciudad" type="text" class="form-control" id="ciudad" />
		</div>
		<fmt:message key="Country.item.column" var="itemSelect"/>
		<fmt:message key="Select.country" var="selectCountry" />
		<div class="form-group col-xs-12 col-sm-6">
			<label for="pais"><fmt:message key="label.Country" /></label>
        	<sf:select path="pais" class="form-control" id="pais">
            	<sf:option value="1" label="${selectCountry}" />
            	<sf:options items="${paises}" itemValue="${itemSelect}" itemLabel="${itemSelect}" />
        	</sf:select>
		</div>		
		
		<button type="submit" class="btn btn-primary margin-left-5porciento"><fmt:message key="Send" /></button>
		<button type="button" class="btn btn-primary margin-left-5porciento" onclick='location.href="<c:url value='/direccion/${direccion.datosPersonales.usuario.idUsr}' />"'><fmt:message key="Cancel" /></button>
	</sf:form>
</body>
</html>