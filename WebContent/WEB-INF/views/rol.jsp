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
	<title><fmt:message key="label.Rol" /></title>
	<script type="text/javascript" src='<c:url value="/resources/js/jquery.js" />'></script>
	<link href="<c:url value='/resources/bootstrap-4.3.1-dist/css/bootstrap.min.css'/>" rel="stylesheet" type="text/css" />
	<link href="<c:url value='/resources/css/menu.css'/>" rel="stylesheet" type="text/css" />
</head>
<body>
	<c:import url="/WEB-INF/views/menu.jsp" />
	<sf:form method="post" action="${pageContext.request.contextPath}/rol/save" modelAttribute="rol">
		<c:if test="${rol.idRol != 0}">
			<sf:hidden path="idRol"/>
		</c:if>
		<div class="form-row">		
			<div class="col-sm-3">
			</div>
			<div class="col-xs-12 col-sm-4">
				<label for="rol"><fmt:message key="Rol.name" /></label> 
				<sf:input path="rol" class="form-control" id="rol" />
			</div>
		</div>
		<br/>
		<div class="form-row">		
			<div class="col-sm-3">
			</div>
			<div class="col-xs-12 col-sm-4">	
				<button type="submit" class="btn btn-primary margin-left-5porciento"><fmt:message key="Send" /></button>
				<button type="button" class="btn btn-primary margin-left-5porciento" onclick='location.href="<c:url value='/rol' />"'><fmt:message key="Cancel" /></button>
			</div>
		</div>
	</sf:form>
</body>
</html>