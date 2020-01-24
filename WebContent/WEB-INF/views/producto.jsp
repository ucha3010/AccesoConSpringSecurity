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
	<title><fmt:message key="Product" /></title>
	<script type="text/javascript" src='<c:url value="/resources/js/jquery.js" />'></script>
	<script type="text/javascript" src='<c:url value="/resources/js/validaciones.js" />'></script>
	<link href="<c:url value='/resources/bootstrap-4.3.1-dist/css/bootstrap.min.css'/>" rel="stylesheet" type="text/css" />
	<link href="<c:url value='/resources/css/menu.css'/>" rel="stylesheet" type="text/css" />
	<script type="text/javascript">
		function validar(){
			var campo = ['nombreES', 'nombreEN','precioVenta','precioCompra'];
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
				nombreDecimal.value = cambiarComaPorPunto(nombreDecimal.value);
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
	<fmt:message key="language.name" var="nameColSelect"/>
	<sf:form method="post" action="${pageContext.request.contextPath}/producto/save" modelAttribute="producto" onsubmit="return validar()">
		<c:if test="${producto.idPro != 0}">
			<sf:hidden path="idPro"/>
			<sf:hidden path="unidades"/>
		</c:if>
		<div class="form-row">		
			<div class="col-sm-3">
			</div>
			<div class="col-xs-12 col-sm-4">
				<label for="nombreES"><fmt:message key="label.Product.name.spanish" /></label> 
				<sf:input path="nombreES" class="form-control" id="nombreES" />
				<span id="nombreESError" name="errorSpan"></span>
			</div>
		</div>
		<br/>
		<div class="form-row">		
			<div class="col-sm-3">
			</div>
			<div class="col-xs-12 col-sm-4">
				<label for="nombreEN"><fmt:message key="label.Product.name.english" /></label> 
				<sf:input path="nombreEN" class="form-control" id="nombreEN" />
				<span id="nombreENError" name="errorSpan"></span>
			</div>
		</div>
		<br/>
		<div class="form-row">		
			<div class="col-sm-3">
			</div>
			<div class="col-xs-12 col-sm-4">
				<label for="estado"><fmt:message key="label.state" /></label>
				<sf:select class="form-control" id="estado" path="estado">
					<sf:option value="ACTIVE"><fmt:message key="ACTIVE" /></sf:option>
					<sf:option value="INACTIVE"><fmt:message key="INACTIVE" /></sf:option>
					<sf:option value="DISCONTINUED"><fmt:message key="DISCONTINUED" /></sf:option>
				</sf:select>
			</div>
		</div>
		<br/>
		<div class="form-row">		
			<div class="col-sm-3">
			</div>
			<div class="col-xs-12 col-sm-4">
				<label for="precioVenta"><fmt:message key="label.salePrice" /></label> 
				<sf:input path="precioVenta" class="form-control" id="precioVenta" />
				<span id="precioVentaError" name="errorSpan"></span>
			</div>
		</div>
		<br/>
		<div class="form-row">		
			<div class="col-sm-3">
			</div>
			<div class="col-xs-12 col-sm-4">
				<label for="precioCompra"><fmt:message key="label.purchase.price" /></label> 
				<sf:input path="precioCompra" class="form-control" id="precioCompra" />
				<span id="precioCompraError" name="errorSpan"></span>
			</div>
		</div>
		<br/>
		<div class="form-row">		
			<div class="col-sm-3">
			</div>
			<div class="col-xs-12 col-sm-4">
				<label for="marca"><fmt:message key="label.brand" /></label> 
				<sf:input path="marca" class="form-control" id="marca" />
				<span id="marcaError" name="errorSpan"></span>
			</div>
		</div>
		<br/>
		<div class="form-row">		
			<div class="col-sm-3">
			</div>
			<div class="col-xs-12 col-sm-4">
				<label for="modelo"><fmt:message key="label.model" /></label> 
				<sf:input path="modelo" class="form-control" id="modelo" />
				<span id="modeloError" name="errorSpan"></span>
			</div>
		</div>
		<br/>
		<div class="form-row">		
			<div class="col-sm-3">
			</div>
			<div class="col-xs-12 col-sm-4">
				<label for="serie"><fmt:message key="label.Serial.number" /></label> 
				<sf:input path="serie" class="form-control" id="serie" />
				<span id="serieError" name="errorSpan"></span>
			</div>
		</div>
		<br/>
		<div class="form-row">		
			<div class="col-sm-3">
			</div>
			<div class="col-xs-12 col-sm-4">
				<label for="ubicacion"><fmt:message key="label.location" /></label> 
				<sf:input path="ubicacion" class="form-control" id="ubicacion" />
				<span id="ubicacionError" name="errorSpan"></span>
			</div>
		</div>
		<br/>
<!-- 		<div class="form-row">		 -->
<!-- 			<div class="col-sm-3"> -->
<!-- 			</div> -->
<!-- 			<div class="col-xs-12 col-sm-4">	 -->
<%-- 				<label for="fechaCompra"><fmt:message key="label.date.purchase" /></label> --%>
<%-- 		    	<sf:input type="date" class="form-control" id="fechaCompra" path="fechaCompra"/> --%>
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<br/> -->
		<div class="form-row">		
			<div class="col-sm-3">
			</div>
			<div class="col-xs-12 col-sm-4 form-check">
				<label class="form-check-label" for="enviar"> <fmt:message key="label.send" /></label>
				<sf:checkbox path="enviar" cssClass="form-check-input margin-left-5porciento" />
				<br>
				<span id="enviarError" name="errorSpan" style="color:red"></span>
			</div>
		</div>		
		<br/>
		<div class="form-row">		
			<div class="col-sm-3">
			</div>
			<div class="col-xs-12 col-sm-4 form-check">
				<label class="form-check-label" for="vendible"> <fmt:message key="label.salable" /></label>
				<sf:checkbox path="vendible" cssClass="form-check-input margin-left-5porciento" />
				<br>
				<span id="vendibleError" name="errorSpan" style="color:red"></span>
			</div>
		</div>		
		<br/>
		<div class="form-row">		
			<div class="col-sm-3">
			</div>
			<div class="col-xs-12 col-sm-4">
				<label for="mesesGarantia"><fmt:message key="label.warranty.months" /></label> 
				<sf:input path="mesesGarantia" class="form-control" id="mesesGarantia" />
				<span id="mesesGarantiaError" name="errorSpan"></span>
			</div>
		</div>
		<br/>
		<div class="form-row">		
			<div class="col-sm-3">
			</div>
			<div class="col-xs-12 col-sm-4">
				<label for="peso"><fmt:message key="label.weigth" /></label> 
				<sf:input path="peso" class="form-control" id="peso" />
				<span id="pesoError" name="errorSpan"></span>
			</div>
		</div>
		<br/>
		<div class="form-row">		
			<div class="col-sm-3">
			</div>
			<div class="col-xs-12 col-sm-4">
				<label for="volumen"><fmt:message key="label.volume" /></label> 
				<sf:input path="volumen" class="form-control" id="volumen" />
				<span id="volumenError" name="errorSpan"></span>
			</div>
		</div>
		<br/>
		<div class="form-row">		
			<div class="col-sm-3">
			</div>
			<div class="col-xs-12 col-sm-4">
				<label for="subcategoria"><fmt:message key="label.Subcategory" /></label>
	        	<sf:select path="subcategoria.idSub" class="form-control" id="subcategoria">
	        		<c:forEach var="categoria" items="${categorias}">
	        			<c:forEach var="sub" items="${categoria.subcategorias}">
	        				<sf:option value="${sub.idSub}" label="${categoria.nombre} - ${sub.nombre}" />
	        			</c:forEach>
	        		</c:forEach>
	        	</sf:select>
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