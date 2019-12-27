<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<%-- language maneja el idioma actual --%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.damian.utils.multilanguage" />
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title><fmt:message key="label.Payment.Method" /></title>
	<script type="text/javascript" src='<c:url value="/resources/js/jquery.js" />'></script>
	<link href="<c:url value='/resources/bootstrap-4.3.1-dist/css/bootstrap.min.css'/>" rel="stylesheet" type="text/css" />
	<link href="<c:url value='/resources/css/menu.css'/>" rel="stylesheet" type="text/css" />
	<script type="text/javascript">
		function validarFormaPago(){
			restablecer();
		    var nombreFormaPago = document.getElementById('nombreFormaPago');
		    var nombreFormaPagoError = document.getElementById('nombreFormaPagoError');
			if(nombreFormaPago.value==''){
				nombreFormaPagoError.innerHTML = "<fmt:message key='error.field.not.empty' />";
				nombreFormaPago.style.borderColor="red";
				return false;
			}
			return true;
		}
		function restablecer(){
			var errorSpan = document.getElementsByName('errorSpan');
			for (var i = 0; i < errorSpan.length; i++) {
				errorSpan[i].innerHTML='';
			}
			var campos = document.getElementsByClassName("form-control");
			for (var i = 0; i < campos.length; i++) {
				campos[i].style.borderColor="#ced4da";
			}
		}
	</script>
</head>
<body>
	<c:import url="/WEB-INF/views/menu.jsp" />
	<sf:form method="post" action="${pageContext.request.contextPath}/formaPago/save" modelAttribute="formaPago" onsubmit="return validarFormaPago()">
		<c:if test="${formaPago.idFor != 0}">
			<sf:hidden path="idFor"/>
		</c:if>
		<div class="form-row">		
			<div class="col-sm-3">
			</div>
			<div class="col-xs-12 col-sm-4">
				<label for="formaPago"><fmt:message key="Payment.method.name" /></label> 
				<sf:input path="nombre" class="form-control" id="nombreFormaPago" />
				<span id="nombreFormaPagoError" name="errorSpan"></span>
			</div>
		</div>
		<br/>
		<div class="form-row">		
			<div class="col-sm-3">
			</div>
			<div class="col-xs-12 col-sm-4">	
				<button type="submit" class="btn btn-primary margin-left-5porciento"><fmt:message key="Send" /></button>
				<button type="button" class="btn btn-primary margin-left-5porciento" onclick='location.href="<c:url value='/formaPago' />"'><fmt:message key="Cancel" /></button>
			</div>
		</div>
	</sf:form>
</body>
</html>