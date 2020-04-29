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
	<title><fmt:message key="label.Address" /></title>
	<c:import url="/WEB-INF/views/importHead.jsp" />
	
	<script type="text/javascript">
		function validar(){
			var campo = ['nombreVia','cp','ciudad'];
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
		<c:if test="${direccionEmpresa.idDirEmp != 0}">
			<div class="well well-sm text-center h2">
				<c:out value="${direccionEmpresa.empresa.nombreComercial}" />
			</div>
		</c:if>
		<c:if test="${direccionEmpresa.idDirEmp == 0}">
			<div class="well well-sm text-center h2"><fmt:message key="Add.address" /></div>
		</c:if>
		<sf:form method="post" action="${pageContext.request.contextPath}/direccionEmpresa/save/${direccionEmpresa.empresa.idEmp}" modelAttribute="direccionEmpresa" onsubmit="return validar()">
			<c:if test="${direccionEmpresa.idDirEmp != 0}">
				<sf:hidden path="idDirEmp"/>
			</c:if>
			<fmt:message key="Country.item.column" var="itemSelect"/>
			<fmt:message key="Select.country" var="selectCountry" />
			<div class="row">		
				<div class="hidden-xs col-sm-1">
				</div>
				<div class="col-xs-12 col-sm-2">
				<label for="tipoVia"><fmt:message key="label.Type.road" /></label>
					<sf:select class="form-control" id="tipoVia" path="tipoVia">
						<sf:option value="road.Street" ><fmt:message key="road.Street" /></sf:option>
						<sf:option value="road.Avenue" ><fmt:message key="road.Avenue" /></sf:option>
						<sf:option value="road.Paseo" ><fmt:message key="road.Paseo" /></sf:option>
						<sf:option value="road.Square" ><fmt:message key="road.Square" /></sf:option>
						<sf:option value="road.Route" ><fmt:message key="road.Route" /></sf:option>
						<sf:option value="road.Road" ><fmt:message key="road.Road" /></sf:option>
						<sf:option value="road.Boulevard" ><fmt:message key="road.Boulevard" /></sf:option>
						<sf:option value="road.Other" ><fmt:message key="road.Other" /></sf:option>
					</sf:select>
				</div>
				<div class="col-xs-12 col-sm-6">
					<label for="nombreVia"><fmt:message key="label.Street" /> *</label> 
					<sf:input path="nombreVia" class="form-control" id="nombreVia" />
					<span id="nombreViaError" name="errorSpan"></span>
				</div>	
				<div class="col-xs-12 col-sm-2">
					<label for="numero"><fmt:message key="label.Number" /></label>
					<sf:input path="numero" type="text" class="form-control" id="numero" />
				</div>	
				<div class="hidden-xs col-sm-1">
				</div>
			</div>
			<br/>	
			<div class="row">		
				<div class="hidden-xs col-sm-1">
				</div>
				<div class="col-xs-12 col-sm-10">
					<label for="resto"><fmt:message key="label.Rest" /></label>
					<sf:input path="resto" type="text" class="form-control" id="resto" />
				</div>
				<div class="hidden-xs col-sm-1">
				</div>	
			</div>
			<br/>
			<div class="row">		
				<div class="hidden-xs col-sm-1">
				</div>
				<div class="col-xs-12 col-sm-3">
					<label for="cp"><fmt:message key="label.Postal.code" /> *</label>
					<sf:input path="cp" type="text" class="form-control" id="cp" />
					<span id="cpError" name="errorSpan"></span>
				</div>
				<div class="col-xs-12 col-sm-7">
					<label for="ciudad"><fmt:message key="label.City" /> *</label>
					<sf:input path="ciudad" type="text" class="form-control" id="ciudad" />
					<span id="ciudadError" name="errorSpan"></span>
				</div>	
				<div class="hidden-xs col-sm-1">
				</div>	
			</div>
			<br/>
			<div class="row">		
				<div class="hidden-xs col-sm-1">
				</div>
				<div class="col-xs-12 col-sm-5">
					<label for="provincia"><fmt:message key="label.Province" /></label>
					<sf:input path="provincia" type="text" class="form-control" id="provincia" />
				</div>
				<div class="col-xs-12 col-sm-5">
					<label for="pais"><fmt:message key="label.Country" /></label>
		        	<sf:select path="pais.idPais" class="form-control" id="pais">
		            	<c:forEach items="${paises}" var="country">
		            		<sf:option value="${country.idPais}" label="${country[itemSelect]}" />
		            	</c:forEach>
		        	</sf:select>
				</div>		
				<div class="hidden-xs col-sm-1">
				</div>
			</div>
			<br/>
			<div class="row">	
				<div class="hidden-xs col-sm-4">
				</div>
				<div class="col-xs-12 col-sm-8">
					<button type="submit" class="btn btn-primary margin-left-5porciento"><fmt:message key="Send" /></button>
					<button type="button" class="btn btn-primary margin-left-5porciento" onclick='location.href="${pageContext.request.contextPath}/direccionEmpresa/${direccionEmpresa.empresa.idEmp}"'><fmt:message key="Cancel" /></button>
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