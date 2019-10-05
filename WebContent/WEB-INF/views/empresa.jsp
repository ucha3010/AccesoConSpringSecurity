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
	<title><fmt:message key="label.Company" /></title>
	<script type="text/javascript" src='<c:url value="/resources/js/jquery.js" />'></script>
	<link href="<c:url value='/resources/bootstrap-4.3.1-dist/css/bootstrap.min.css'/>" rel="stylesheet" type="text/css" />
	<link href="<c:url value='/resources/css/menu.css'/>" rel="stylesheet" type="text/css" />
</head>
<body>
	<c:import url="/WEB-INF/views/menu.jsp" />
	<sf:form method="post" action="${pageContext.request.contextPath}/empresa/save" modelAttribute="empresa">
		<c:if test="${empresa.idEmp != 0}">
			<sf:hidden path="idEmp"/>
		</c:if>
		
		<div class="form-group col-xs-12 col-sm-6">
			<label for="nombreComercial"><fmt:message key="label.Company.name" /></label> 
			<sf:input path="nombreComercial" class="form-control" id="nombreComercial" />
		</div>
		<div class="form-group col-xs-12 col-sm-6">
			<label for="tipoSociedad"><fmt:message key="label.Limited.company" /></label>
			<sf:select class="form-control" id="tipoSociedad" path="tipoSociedad">
				<sf:option value="SL" />
				<sf:option value="SA" />
				<sf:option value="Cooperativa" />
			</sf:select>
		</div>
		<div class="form-group col-xs-12 col-sm-6">
			<label for="actividad"><fmt:message key="label.Activity" /></label>
			<sf:input path="actividad" type="text" class="form-control" id="actividad" />
		</div>
		<div class="form-group col-xs-12 col-sm-6">
			<label for="cif"><fmt:message key="label.vat" /></label>
			<sf:input path="cif" type="text" class="form-control" id="cif" />
		</div>
		<div class="form-group col-xs-12 col-sm-6">
			<label for="email"><fmt:message key="label.Email" /></label>
			<sf:input path="email" type="text" class="form-control" id="email" />
		</div>
		<div class="form-group col-xs-12 col-sm-6">
			<label for="paginaWeb"><fmt:message key="label.Web.page" /></label>
			<sf:input path="paginaWeb" type="text" class="form-control" id="paginaWeb" />
		</div>
		<div class="form-group col-xs-12 col-sm-6">
			<label for="telefono"><fmt:message key="label.Phone" /></label>
			<sf:input path="telefono" type="text" class="form-control" id="telefono" />
		</div>	
		<div class="form-group col-xs-12 col-sm-6">
			<label for="fax"><fmt:message key="label.Fax" /></label>
			<sf:input path="fax" type="text" class="form-control" id="fax" />
		</div>
		<div class="form-group col-xs-12 col-sm-6">
			<label for="observaciones"><fmt:message key="label.Observations" /></label>
			<sf:textarea path="observaciones" type="text" class="form-control" id="observaciones" />
		</div>
		
		<button type="submit" class="btn btn-primary margin-left-5porciento"><fmt:message key="Send" /></button>
		<button type="button" class="btn btn-primary margin-left-5porciento" onclick='location.href="<c:url value='/empresa' />"'><fmt:message key="Cancel" /></button>
	</sf:form>
</body>
</html>