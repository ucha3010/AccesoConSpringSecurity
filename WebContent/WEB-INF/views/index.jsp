<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- language maneja el idioma actual --%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.damian.utils.multilanguage" />
<html>
<head>
	<title>Company</title>
	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<link href="<c:url value='/resources/bootstrap-4.3.1-dist/css/bootstrap.min.css'/>" rel="stylesheet" type="text/css" />
	<link href="<c:url value='/resources/css/menu.css'/>" rel="stylesheet" type="text/css" />
</head>
<body>
	<c:import url="/WEB-INF/views/menu.jsp" />
	<div class="container-fluid">
	
		<div class="row">
			<div class="col-md-12">
				<h1>Index</h1>
			</div>
		</div>
	
		<div class="row">
<!-- 			xs, sm, md y lg hacen referencia al tamaño del dispositivo donde se muestra -->
			<div class="col-xs-12 col-sm-6 col-md-4 col-lg-2">
				<a href='<c:url value="/about"/>'>Acerca de</a><br/>	
				<a href='<c:url value="/admin"/>'>Admin</a><br/>
				<a href='<c:url value="/index2"/>'>Index2</a>
			</div>
			<div class="col-xs-12 col-sm-6 col-md-8 col-lg-10">	
				Atributos del Model: <c:out value="${mensaje}" /><br/>
				Atributos en Session (estoy): <c:out value="${sessionScope.estoy}" /><br/>
				Atributos en Session (resultado): <c:out value="${sessionScope.resultado}" /><br/>
				Atributos en Session (nombre): <c:out value="${sessionScope.nombre}" /><br/>
				Atributos en Session (valor): <c:out value="${sessionScope.valor}" />
			</div>
		</div>
		
		<div class="row">
			<div class="color1 col-md-2">
				<h1>Fondo</h1>
			</div>
			<div class="col-md-2">
				<h1>Fondo</h1>
			</div>
			<div class="color1 col-md-2">
				<h1>Fondo</h1>
			</div>
			<div class="col-md-2">
				<h1>Fondo</h1>
			</div>
			<div class="color1 col-md-2">
				<h1>Fondo</h1>
			</div>
			<div class="col-md-2">
				<h1>Fondo</h1>
			</div>
		</div>
		
		<br/>
		<br/>
		<h2>Datos del formulario de Admin.jsp</h2>
		<c:out value="${adminForm}"></c:out><br/>
		<br/>
		<br/>
		<br/>Datos
		<br/>
		<br/>
	     <h3>Campo language: <c:out value="${language}" /></h3>
	
	     <fmt:message key="msg.greeting" /><br />
	
	     <h3><fmt:message key="label.Numbers" /></h3>
	     
	     <c:set var="numero" value="125678.4309" />
	     
	     <fmt:message key="label.Number" />: <fmt:formatNumber value="${numero}" type="number"/><br />
	     <fmt:message key="label.Currency" />: <fmt:formatNumber value="${numero}" type="currency"/><br />
	     <fmt:message key="label.Percent" />: <fmt:formatNumber value="${numero}" type="percent"/><br />
		<button class="btn btn-primary">Botón</button>
		<br/>
		<br/>
		<br/>Datos
		<br/>
		<br/>
		<br/>Datos
		<br/>
		<br/>
		<br/>Datos
		<br/>
		<br/>
		<br/>Datos
		<br/>
		<br/>
		<br/>Datos
		<br/>
		<br/>
		<br/>Datos
		<br/>
		<br/>
		<br/>Datos
		<br/>
		<br/>
		<br/>Datos
		<br/>
		<br/>
		<br/>Datos
	</div>
	<script type="text/javascript" src="<c:url value='/resources/js/jquery.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/bootstrap-4.3.1-dist/js/bootstrap.min.js'/>"></script>
</body>
</html>