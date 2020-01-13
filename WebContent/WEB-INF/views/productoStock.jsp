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
	<title><fmt:message key="label.Add.remove.stock" /></title>
	<script type="text/javascript" src='<c:url value="/resources/js/jquery.js" />'></script>
	<script type="text/javascript" src='<c:url value="/resources/js/validaciones.js" />'></script>
	<link href="<c:url value='/resources/bootstrap-4.3.1-dist/css/bootstrap.min.css'/>" rel="stylesheet" type="text/css" />
	<link href="<c:url value='/resources/css/menu.css'/>" rel="stylesheet" type="text/css" />
	<script type="text/javascript">
		function validar(){
			var campo = ['descripcion','precioVenta','precioCompra'];
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

			var decimales = ['precioVenta','precioCompra','mesesGarantia','peso','volumen'];
			for(var i=0; i < decimales.length; i++){
				var nombreDecimal = document.getElementById(decimales[i]);
				var nombreDecimalError = document.getElementById(decimales[i]+'Error');
				if(!validarDecimal(nombreDecimal.value)){
					nombreDecimalError.innerHTML = "<fmt:message key='error.not.valid.value' />";
					nombreDecimal.style.borderColor="red";
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
	<c:import url="/WEB-INF/views/menu.jsp" />
	<sf:form method="post" action="${pageContext.request.contextPath}/producto/save" modelAttribute="frontProductoStock" onsubmit="return validar()">
		<sf:hidden path="idPro"/>
		<sf:hidden path="unidades"/>
		<div class="form-row">		
			<div class="col-sm-3">
				<h1><fmt:message key="label.Add.remove.stock" /></h1>
			</div>
			<div class="col-xs-12 col-sm-4">
			</div>
		</div>
		<br/>
		<div class="form-row">		
			<div class="col-sm-2">
			</div>
			<div class="col-xs-12 col-sm-10">
				<h2><fmt:message key="label.Product.description" />: <c:out value="${frontProductoStock.descripcion}" /></h2>
			</div>
		</div>
		<br/>
		<br/>
		<div class="form-row">		
			<div class="col-sm-3">
			</div>
			<div class="col-xs-12 col-sm-4">
				<div class="custom-control custom-radio custom-control-inline">
					<sf:radiobutton id="customRadioInline2" name="customRadioInline1" class="custom-control-input" path="compra" value="true"/>
					<label class="custom-control-label" for="customRadioInline2"><fmt:message key="label.Add" /></label>
				</div>
				<div class="col-sm-4custom-control custom-radio custom-control-inline margin-left-5porciento">
					<sf:radiobutton id="customRadioInline1" name="customRadioInline1" class="custom-control-input" path="compra" value="false"/>
					<label class="custom-control-label" for="customRadioInline1"><fmt:message key="label.Remove" /></label>
				</div>
			</div>
		</div>
		<br/>
		<div class="form-row">		
			<div class="col-sm-3">
			</div>
			<div class="col-xs-12 col-sm-4">
				<fmt:message key="label.It.will.be" />
				<span id="addStock"><fmt:message key="label.added" /></span>
				<span id="removeStock"><fmt:message key="label.removed" /></span>
				<sf:input path="precioFinal" class="form-control" id="precioFinal" />
				<fmt:message key="label.units" />
			</div>
		</div>
		<br/>
		
		
		
		
		
		
		
		
		
		<div class="form-row">		
			<div class="col-sm-3">
			</div>
			<div class="col-xs-12 col-sm-4">
				<button type="submit" class="btn btn-primary margin-left-5porciento"><fmt:message key="Send" /></button>
				<button type="button" class="btn btn-primary margin-left-5porciento" onclick='location.href="<c:url value='/producto' />"'><fmt:message key="Cancel" /></button>
			</div>
		</div>
	</sf:form>
</body>
</html>