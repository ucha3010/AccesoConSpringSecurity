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
	<title><fmt:message key='Company.name' /></title>
	<c:import url="/WEB-INF/views/importHead.jsp" />

</head>
<body class="${prefUsr.tema}fondopantalla">
	<div class="container">
		<c:import url="/WEB-INF/views/menu.jsp" />
		<fmt:message key="language.name" var="nameColSelect"/>
		
		<div class="row">
			<div class="col-xs-12 hidden-sm hidden-md hidden-lg hidden-xl">
				<ul class="nav nav-tabs">
					<li class="nav-item">
						<a class="nav-link" href="<c:url value='/administrar/ofertas'/>"><fmt:message key="label.Offers" /></a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="<c:url value='/administrar/populares'/>"><fmt:message key="label.Most.popular.products" /></a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="<c:url value='/administrar/novedades'/>"><fmt:message key="label.New.stock" /></a>
					</li>
					<li class="nav-item">
						<a class="nav-link active" href="<c:url value='#'/>"><fmt:message key='label.Campaigns' /></a>
					</li>
				</ul>
			</div>
			<div class="hidden-xs col-sm-1">
			</div>
			<div class="hidden-xs col-sm-10">
				<ul class="nav nav-tabs">
					<li class="nav-item">
						<a class="nav-link" href="<c:url value='/administrar/ofertas'/>"><fmt:message key="label.Offers" /></a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="<c:url value='/administrar/populares'/>"><fmt:message key="label.Most.popular.products" /></a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="<c:url value='/administrar/novedades'/>"><fmt:message key="label.New.stock" /></a>
					</li>
					<li class="nav-item">
						<a class="nav-link active" href="<c:url value='#'/>"><fmt:message key='label.Campaigns' /></a>
					</li>
				</ul>
			</div>
			<div class="hidden-xs col-sm-1">
			</div>
		</div>
	</div>
		
	<div class="separacion20"></div>
	
		
		
	<div class="container">
			<div id="du-sidebar">
				<div class="du-toggle-btn">
					<span><fmt:message key='label.Campaigns' /></span>
				</div>
				<ul>
					<li>Home</li>
					<li>About</li>
					<li>Contact</li>
				</ul>
			</div>
	</div>
	
	
	<div class="row">
		<div class="col-xs-1">
		</div>
		<div class="col-xs-10">
			<footer>
				<c:import url="/WEB-INF/views/importFooter.jsp" />
			</footer>
		</div>
		<div class="col-xs-1">
		</div>
	</div>
</body>
</html>