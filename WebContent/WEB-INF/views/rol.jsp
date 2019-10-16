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
	<script type="text/javascript" src='<c:url value="/resources/js/validaciones.js" />'></script>
	<link href="<c:url value='/resources/bootstrap-4.3.1-dist/css/bootstrap.min.css'/>" rel="stylesheet" type="text/css" />
	<link href="<c:url value='/resources/css/menu.css'/>" rel="stylesheet" type="text/css" />
	<script type="text/javascript">
		function validarRol(){
			restablecer();
		    var nombreRol = document.getElementById('nombreRol');
		    var nombreRolError = document.getElementById('nombreRolError');
			if(nombreRol.value==''){
				nombreRolError.innerHTML = "<fmt:message key='error.field.not.empty' />";
				nombreRol.style.borderColor="red";
				return false;
			}
			if(nombreRol.value.length < 4 || nombreRol.value.substring(0,4)!='ROL_'){
				nombreRolError.innerHTML = "<fmt:message key='error.rolname.start' />";
				nombreRol.style.borderColor="red";
				return false;
			}
			return true;
		}
	</script>
</head>
<body>
	<c:import url="/WEB-INF/views/menu.jsp" />
	<sf:form method="post" action="${pageContext.request.contextPath}/rol/save" modelAttribute="rol" onsubmit="return validarRol()">
		<c:if test="${rol.idRol != 0}">
			<sf:hidden path="idRol"/>
		</c:if>
		<div class="form-row">		
			<div class="col-sm-3">
			</div>
			<div class="col-xs-12 col-sm-4">
				<label for="rol"><fmt:message key="Rol.name" /></label> 
				<sf:input path="rol" class="form-control" id="nombreRol" />
				<span id="nombreRolError" name="errorSpan"></span>
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