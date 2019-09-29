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
	<title><fmt:message key="label.Login.page" /></title>
	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<link href="<c:url value='/resources/bootstrap-4.3.1-dist/css/bootstrap.min.css'/>" rel="stylesheet" type="text/css" />
	<link href="<c:url value='/resources/css/menu.css'/>" rel="stylesheet" type="text/css" />
</head>
<body class="fondo-candado">
	<div class="container">
		<div class="row">
			<h1 class="pl-4">
				<fmt:message key="label.Login.page" />
			</h1>
		</div>
		<c:if test="${not empty error}">
			<span style="color: red;">
				<fmt:message key="${error}" />
			</span>
		</c:if>
		<form name='f' action="j_spring_security_check" method='post'>
			<div class="row">
				<div class="col-xs-12 col-sm-6">
					<div class="form-group">
						<label for="exampleInputEmail1"><fmt:message key="label.User" /></label> 
						<input type="text" class="form-control border-color-dam" id="exampleInputEmail1" name='usuario' value='' >
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1"><fmt:message key="label.Password" /></label>
						<input type="password" class="form-control border-color-dam" id="exampleInputPassword1" name='clave' value=''>
					</div>
					<div class="form-check">
						<input type="checkbox" class="form-check-input" id="exampleCheck1" name='_spring_security_remember_me' checked='checked'>
						<label class="form-check-label" for="exampleCheck1"><fmt:message key="label.Remember.me" /></label>
					</div>
					<button type="submit" class="btn btn-primary" name="submit"><fmt:message key="label.Login" /></button>
				<button type="button" class="btn btn-primary margin-left-5porciento" onclick='location.href="<c:url value="/"/>"'><fmt:message key="Cancel" /></button>
				</div>
			</div>
		</form>
	</div>

	<script type="text/javascript" src="<c:url value='/resources/js/jquery.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/bootstrap-4.3.1-dist/js/bootstrap.min.js'/>"></script>
</body>
</html>