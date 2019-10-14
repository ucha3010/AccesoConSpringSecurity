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
	<title><fmt:message key="label.User" /></title>
	<script type="text/javascript" src='<c:url value="/resources/js/jquery.js" />'></script>
	<link href="<c:url value='/resources/bootstrap-4.3.1-dist/css/bootstrap.min.css'/>" rel="stylesheet" type="text/css" />
	<link href="<c:url value='/resources/css/menu.css'/>" rel="stylesheet" type="text/css" />
</head>
<body>
	<c:import url="/WEB-INF/views/menu.jsp" />
<br>
	<sf:form method="post" action="${pageContext.request.contextPath}/usuario/logged/changePass/save" modelAttribute="usuario">

		<c:if test="${not empty usuario.clave}">
			<sf:hidden path="usuario"/>
			<sf:hidden path="idUsr"/>
			<sf:hidden path="habilitado"/>
			<sf:hidden path="fechaCreacion"/>
		</c:if>		
		<div class="text-center">
			<h1><c:out value="${usuario.datosPersonales.nombre}"/> <c:out value="${usuario.datosPersonales.apellido1}"/> <c:out value="${usuario.datosPersonales.apellido2}"/></h1>
		</div>
		<br>
		<div class="form-row">	
			<div class="col-sm-2">
			</div>
			<div class="col-xs-12 col-sm-4">
				<label for="inputPassword1"><fmt:message key="label.Password" /></label> 
				<sf:password class="form-control" id="inputPassword1" path="clave"/>
				<sf:errors path="clave" cssStyle="color:red"/>
			</div>
			<div class="col-xs-12 col-sm-4">
				<label for="inputPassword2"><fmt:message key="label.Password.repeat" /></label> 
				<input type="password" class="form-control" id="inputPassword2"/>
			</div>
		</div>
		<br>
		<div class="form-row">	
			<div class="col-sm-5">
			</div>
			<div class="col-xs-12 col-sm-4">
				<button type="submit" class="btn btn-success margin-left-5porciento"><fmt:message key="Send" /></button>
				<button type="button" class="btn btn-primary margin-left-5porciento" onclick='location.href="<c:url value="/usuario/logged/${usuario.idUsr}"/>"'><fmt:message key="Cancel" /></button>
			</div>
		</div>
	</sf:form>
</body>
</html>