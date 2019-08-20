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
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Company</title>
	<link href="<c:url value='/resources/css/menu.css'/>" rel="stylesheet" type="text/css" />
</head>
<body>
	<c:import url="/WEB-INF/views/menu.jsp" />
	<h1>Index</h1>

	<a href='<c:url value="/about"/>'>Acerca de</a><br/>	
	<a href='<c:url value="/admin"/>'>Admin</a><br/>
	<a href='<c:url value="/index2"/>'>Index2</a><br/>
	<br/>
	Atributos del Model: <c:out value="${mensaje}" /><br/>
	Atributos en Session (estoy): <c:out value="${sessionScope.estoy}" /><br/>
	Atributos en Session (resultado): <c:out value="${sessionScope.resultado}" /><br/>
	Atributos en Session (nombre): <c:out value="${sessionScope.nombre}" /><br/>
	Atributos en Session (valor): <c:out value="${sessionScope.valor}" /><br/>
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

     <h3><fmt:message key="label.numbers" /></h3>
     
     <c:set var="numero" value="125678.4309" />
     
     <fmt:message key="label.number" />: <fmt:formatNumber value="${numero}" type="number"/><br />
     <fmt:message key="label.currency" />: <fmt:formatNumber value="${numero}" type="currency"/><br />
     <fmt:message key="label.percent" />: <fmt:formatNumber value="${numero}" type="percent"/><br />

</body>
</html>