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
	<fmt:message key="language.name" var="nameColSelect"/>

</head>
<body class="${prefUsr.tema}fondopantalla">
	<div class="container">
		<c:import url="/WEB-INF/views/menu.jsp" />
		<div class="well well-sm text-center h2 ${prefUsr.tema}titulo"><c:out value="${producto[nameColSelect]}" /></div>
		
		<div class="row">
			<div class="col-md-12">
								
				<div id="principalCarrousel" class="carousel slide" data-ride="carousel">
					<!-- Indicators -->
					<ol class="carousel-indicators changeitem">
					
						<c:set var="activeSlide" value="active" scope="page" />
						<c:set var="contadorSlide" value="0" scope="page" />
						<c:forEach items="${producto.fotos}" var="sl">
							<li data-target="#principalCarrousel" data-slide-to="${contadorSlide}" class="${activeSlide}"></li>
							<c:set var="activeSlide" value="" scope="page" />
							<c:set var="contadorSlide" value="${contadorSlide + 1}" scope="page" />
						</c:forEach>
					</ol>
					
					<!-- Wrapper for slides -->
					<div class="carousel-inner">
						
						<c:set var="activeSlide" value="active" scope="page" />
						<c:set var="contadorSlide" value="1" scope="page" />
						<c:forEach items="${producto.fotos}" var="foto">
							<div class="item item-center ${activeSlide}">
								<img src='<c:url value="/resources/imgs/productos/${producto.idPro}/${foto.nombre}"/>' alt="${foto.descripcion}">
							</div>
							<c:set var="activeSlide" value="" scope="page" />
							<c:set var="contadorSlide" value="${contadorSlide + 1}" scope="page" />
						</c:forEach>

					</div>
					
					<!-- Left and right controls -->
					<a class="left carousel-control" href="#principalCarrousel" data-slide="prev">
						<span class="glyphicon glyphicon-chevron-left"></span>
						<span class="sr-only">Previous</span>
					</a>
					<a class="right carousel-control" href="#principalCarrousel" data-slide="next">
						<span class="glyphicon glyphicon-chevron-right"></span>
						<span class="sr-only">Next</span>
					</a>
				</div>
				
			</div>
		</div>
		
		<div class="well well-sm text-center h2 ${prefUsr.tema}titulo"><c:out value="${descripcion[nameColSelect]}" /></div>
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