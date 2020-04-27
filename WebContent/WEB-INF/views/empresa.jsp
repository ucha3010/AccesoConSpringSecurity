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
	<title><fmt:message key="Company" /></title>
	<c:import url="/WEB-INF/views/importHead.jsp" />
	
	<script type="text/javascript" src='<c:url value="/resources/js/validaciones.js" />'></script>
	<script type="text/javascript">
		function validar(){
			var campo = ['nombreComercial'];
			restablecer();
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
			
			var cif = document.getElementById('cif');
			if(cif.value.length > 0){
				if(!validarCif(cif.value)){
					cif.style.borderColor="red";
					document.getElementById('cifError').innerHTML = "<fmt:message key='error.not.valid.value' />";
					validado = false;
				}
			}
			
			var email = document.getElementById('email');
			if(email.value.length > 0){
				if(!validarEmail(email.value)){
					email.style.borderColor="red";
					document.getElementById('emailError').innerHTML = "<fmt:message key='error.not.valid.value' />";
					validado = false;
				}
			}
			
			if(validado){
				return true;
			} else {
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
		<c:if test="${empresa.idEmp != 0}">
			<div class="well well-sm text-center h2"><c:out value="${empresa.nombreComercial}"></c:out></div>
		</c:if>
		<c:if test="${empresa.idEmp == 0}">
			<div class="well well-sm text-center h2"><fmt:message key="Add.company" /></div>
		</c:if>
		<sf:form method="post" action="${pageContext.request.contextPath}/empresa/save" modelAttribute="empresa" onsubmit="return validar()">
			<c:if test="${empresa.idEmp != 0}">
				<sf:hidden path="idEmp"/>
			</c:if>
			<div class="row">		
				<div class="hidden-xs col-sm-1">
				</div>
				<div class="col-xs-12 col-sm-5">
					<label for="nombreComercial"><fmt:message key="label.Company.name" /></label> 
					<sf:input path="nombreComercial" class="form-control" id="nombreComercial" />
					<span id="nombreComercialError" name="errorSpan"></span>
				</div>
				<div class="col-xs-12 col-sm-5">
					<label for="tipoSociedad"><fmt:message key="label.Limited.company" /></label>
					<sf:select class="form-control" id="tipoSociedad" path="tipoSociedad">
						<sf:option value="SL" />
						<sf:option value="SA" />
						<sf:option value="Cooperativa" />
					</sf:select>
				</div>	
			</div>
			<br/>
			<div class="row">		
				<div class="hidden-xs col-sm-1">
				</div>
				<div class="col-xs-12 col-sm-5">
					<label for="actividad"><fmt:message key="label.Activity" /></label>
					<sf:input path="actividad" type="text" class="form-control" id="actividad" />
				</div>
				<div class="col-xs-12 col-sm-5">
					<label for="cif"><fmt:message key="label.vat" /></label>
					<sf:input path="cif" type="text" class="form-control" id="cif" />
					<span id="cifError" name="errorSpan"></span>
				</div>		
				<div class="hidden-xs col-sm-1">
				</div>
			</div>
			<br>
			<div class="row">		
				<div class="hidden-xs col-sm-1">
				</div>
				<div class="col-xs-12 col-sm-5">
					<label for="email"><fmt:message key="label.Email" /></label>
					<sf:input path="email" type="text" class="form-control" id="email" />
					<span id="emailError" name="errorSpan"></span>
				</div>
				<div class="col-xs-12 col-sm-5">
					<label for="paginaWeb"><fmt:message key="label.Web.page" /></label>
					<sf:input path="paginaWeb" type="text" class="form-control" id="paginaWeb" />
				</div>		
				<div class="hidden-xs col-sm-1">
				</div>
			</div>
			<br>
			<div class="row">		
				<div class="hidden-xs col-sm-1">
				</div>
				<div class="col-xs-12 col-sm-5">
					<label for="telefono"><fmt:message key="label.Phone" /></label>
					<sf:input path="telefono" type="text" class="form-control" id="telefono" />
				</div>
				<div class="col-xs-12 col-sm-5">
					<label for="fax"><fmt:message key="label.Fax" /></label>
					<sf:input path="fax" type="text" class="form-control" id="fax" />
				</div>		
				<div class="hidden-xs col-sm-1">
				</div>
			</div>
			<br>
			<div class="row">		
				<div class="hidden-xs col-sm-1">
				</div>
				<div class="col-xs-12 col-sm-5">
					
				</div>
				<div class="col-xs-12 col-sm-5">
					
				</div>		
				<div class="hidden-xs col-sm-1">
				</div>
			</div>
			<br>
			<div class="row">		
				<div class="hidden-xs col-sm-3">
				</div>
				<div class="col-xs-12 col-sm-6">
					<label for="observaciones"><fmt:message key="label.Observations" /></label>
					<sf:textarea path="observaciones" type="text" class="form-control" id="observaciones" />
				</div>		
				<div class="hidden-xs col-sm-3">
				</div>
			</div>
			<br>
			<div class="row">	
				<div class="hidden-xs col-sm-4">
				</div>
				<div class="col-xs-12 col-sm-8">
					<button type="submit" class="btn btn-primary margin-left-5porciento"><fmt:message key="Send" /></button>
					<button type="button" class="btn btn-primary margin-left-5porciento" onclick='location.href="<c:url value='/empresa/all/null' />"'><fmt:message key="Cancel" /></button>
				</div>
			</div>
		</sf:form>
		
		<footer>
			<c:import url="/WEB-INF/views/importFooter.jsp" />
		</footer>
	</div>
</body>
</html>