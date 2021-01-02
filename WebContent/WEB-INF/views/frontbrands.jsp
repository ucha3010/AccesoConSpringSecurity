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
	<title><fmt:message key="label.Brands" /></title>
	<c:import url="/WEB-INF/views/importHead.jsp" />

</head>
<body class="${prefUsr.tema}fondopantalla">
	<div class="container">
		<c:import url="/WEB-INF/views/menu.jsp" />
		<div class="well well-sm text-center h2 ${prefUsr.tema}titulo"><fmt:message key="label.Brands" /></div>
		
		<c:import url="/WEB-INF/views/divSearchBar.jsp" />
		
		<div class="row">
			<div class="col-md-12">
				<c:forEach items="${marcas}" var="marca">
					<c:if test="${(not empty marca.foto)}">
						<a title="${marca.nombre}" onclick='location.href="<c:url value='/......' />"'><!-- TODO DAMIAN poner bien esta ruta cuando lo tenga hecho -->
							<img src='<c:url value="/resources/imgs/marcas/${marca.idMar}/${marca.foto.nombre}"/>' class="w-20 rounded" alt="${marca.nombre}">
						</a>
					</c:if>
				</c:forEach>
			</div>
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