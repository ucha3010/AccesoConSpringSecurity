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
	<title><fmt:message key="label.Category" /></title>
	<c:import url="/WEB-INF/views/importHead.jsp" />
	
	<script type="text/javascript">
		function validar(){
			restablecer();
			var campo = ['nombreCategoriaES','nombreCategoriaEN'];
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
<body>
	<div class="container">
		<c:import url="/WEB-INF/views/menu.jsp" />
		<c:if test="${categoria.idCat != 0}">
			<div class="well well-sm text-center h2"><fmt:message key='Edit' /></div>
		</c:if>
		<c:if test="${categoria.idCat == 0}">
			<div class="well well-sm text-center h2"><fmt:message key="Add.category" /></div>
		</c:if>
		<sf:form method="post" action="${pageContext.request.contextPath}/categoria/save" modelAttribute="categoria" onsubmit="return validar()">
			<c:if test="${categoria.idCat != 0}">
				<sf:hidden path="idCat"/>
			</c:if>
			<div class="row">		
				<div class="hidden-xs col-sm-1">
				</div>
				<div class="col-xs-12 col-sm-5">
					<label for="nombreES"><fmt:message key="Category.name.spanish" /></label> 
					<sf:input path="nombreES" class="form-control" id="nombreCategoriaES" />
					<span id="nombreCategoriaESError" name="errorSpan"></span>
				</div>
				<div class="col-xs-12 col-sm-5">
					<label for="nombreEN"><fmt:message key="Category.name.english" /></label> 
					<sf:input path="nombreEN" class="form-control" id="nombreCategoriaEN" />
					<span id="nombreCategoriaENError" name="errorSpan"></span>
				</div>	
			</div>
			<br/>
			<div class="row">	
				<div class="hidden-xs col-sm-4">
				</div>
				<div class="col-xs-12 col-sm-8">
					<button type="submit" class="btn btn-primary margin-left-5porciento"><fmt:message key="Send" /></button>
					<button type="button" class="btn btn-primary margin-left-5porciento" onclick='location.href="<c:url value='/categoria' />"'><fmt:message key="Cancel" /></button>
					<span id="hayError" name="errorSpan" style="color:red"></span>
				</div>
			</div>
		</sf:form>
		
		<footer>
			<c:import url="/WEB-INF/views/importFooter.jsp" />
		</footer>
	</div>
</body>
</html>