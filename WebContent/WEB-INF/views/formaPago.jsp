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
	<title><fmt:message key="label.Payment.Method" /></title>
	<c:import url="/WEB-INF/views/importHead.jsp" />
	
	<script type="text/javascript">
		function validar(){
			restablecer();
			var campo = ['nombreFormaPagoES','nombreFormaPagoEN'];
			var validado = true;			
			for(var i=0; i < campo.length; i++){
			    var nombreRol = document.getElementById(campo[i]);
			    var nombreRolError = document.getElementById(campo[i]+'Error');
				if(nombreRol.value==''){
					nombreRolError.innerHTML = "<fmt:message key='error.field.not.empty' />";
					nombreRol.style.borderColor="red";
					validado = false;
				}
			}
			
			if(validado){
				return true;
			} else {
				document.getElementById('hayError').innerHTML = "<fmt:message key='error.any.field' />";
				return false;
			}
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
<body class="${prefUsr.tema}fondopantalla">
	<div class="container">
		<c:import url="/WEB-INF/views/menu.jsp" />
		<c:if test="${formaPago.idFor != 0}">
			<div class="well well-sm text-center h2 ${prefUsr.tema}titulo"><fmt:message key='Edit' /></div>
		</c:if>
		<c:if test="${formaPago.idFor == 0}">
			<div class="well well-sm text-center h2 ${prefUsr.tema}titulo"><fmt:message key="Add.formaPago" /></div>
		</c:if>
		<sf:form method="post" action="${pageContext.request.contextPath}/formaPago/save" modelAttribute="formaPago" onsubmit="return validar()">
			<c:if test="${formaPago.idFor != 0}">
				<sf:hidden path="idFor"/>
			</c:if>
			<div class="row">		
				<div class="hidden-xs col-sm-1">
				</div>
				<div class="col-xs-12 col-sm-5">
					<label for="formaPago"><fmt:message key="Payment.method.name.spanish" /></label> 
					<sf:input path="nombreES" class="form-control" id="nombreFormaPagoES" />
					<span id="nombreFormaPagoESError" name="errorSpan"></span>
				</div>
				<div class="col-xs-12 col-sm-5">
					<label for="formaPago"><fmt:message key="Payment.method.name.english" /></label> 
					<sf:input path="nombreEN" class="form-control" id="nombreFormaPagoEN" />
					<span id="nombreFormaPagoENError" name="errorSpan"></span>
				</div>	
			</div>
			<br/>
			<div class="row">	
				<div class="hidden-xs col-sm-4">
				</div>
				<div class="col-xs-12 col-sm-8">
					<button type="submit" class="btn btn-primary margin-left-5porciento ${prefUsr.tema}botonguardar"><fmt:message key="Save" /></button>
					<button type="button" class="btn btn-primary margin-left-5porciento ${prefUsr.tema}botoncancelar" onclick='location.href="<c:url value='/formaPago' />"'><fmt:message key="Cancel" /></button>
					<span id="hayError" name="errorSpan" style="color:red"></span>
				</div>
			</div>
		</sf:form>
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