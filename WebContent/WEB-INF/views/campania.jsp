<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<title><fmt:message key='Company.name' /></title>
	<c:import url="/WEB-INF/views/importHead.jsp" />
	
	<script type="text/javascript">
		function validar(){

			restablecer();
			var validado = true;

			var campo = ['nombre','fechaInicio','fechaFin'];			
			for(var i=0; i < campo.length; i++){
			    var nombreRol = document.getElementById(campo[i]);
			    var nombreRolError = document.getElementById(campo[i]+'Error');
				if(nombreRol.value==''){
					nombreRolError.innerHTML = "<fmt:message key='error.field.not.empty' />";
					nombreRol.style.borderColor="red";
					validado = false;
				}
			}

			var enteros = ['descuentoPor'];
			for(var i=0; i < enteros.length; i++){
				var nombreEntero = document.getElementById(enteros[i]);
				var nombreEnteroError = document.getElementById(enteros[i]+'Error');
				if(!validarDecimal(nombreEntero.value)){
					nombreEnteroError.innerHTML = "<fmt:message key='error.not.valid.value' />";
					nombreEntero.style.borderColor="red";
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
		<div class="well well-sm text-center h2 ${prefUsr.tema}titulo"><fmt:message key="label.Edit.campaign" /></div>
		<sf:form method="post" action="${pageContext.request.contextPath}/administrar/campanias/edit/save" modelAttribute="campania" onsubmit="return validar()">	
			<sf:hidden path="idCam"/>
			
			<div class="row">
				<div class="hidden-xs col-sm-1">
				</div>
				<div class="col-xs-9 col-sm-8">
					<label for="nombre"><fmt:message key="label.Campaign.name" /></label> 
					<sf:input path="nombre" class="form-control" id="nombre" />
					<span id="nombreError" name="errorSpan"></span>
				</div>	
				
					
				<div class="col-xs-3 col-sm-2">
					<label for="descuentoPor"><fmt:message key="label.Percentage.discount" /></label> 
					<sf:input path="descuentoPor" class="form-control" id="descuentoPor"/>
					<span id="descuentoPorError" name="errorSpan"></span>
				</div>
				<div class="hidden-xs col-sm-1">
				</div>
			</div>
			
			<div class="row">
				<div class="hidden-xs col-sm-1">
				</div>
				<div class="col-xs-6 col-sm-5">
					<label for="fechaInicio"><fmt:message key="label.Start.date" /></label>
			    	<sf:input type="date" class="form-control" id="fechaInicio" path="fechaInicio"/>
					<span id="fechaInicioError" name="errorSpan"></span>
				</div>
				
				
				<div class="col-xs-6 col-sm-5">
					<label for="fechaFin"><fmt:message key="label.End.date" /></label>
			    	<sf:input type="date" class="form-control" id="fechaFin" path="fechaFin"/>
					<span id="fechaFinError" name="errorSpan"></span>
				</div>	
				<div class="hidden-xs col-sm-1">
				</div>
			</div>
			
			<div class="row">
				<div class="hidden-xs col-sm-1">
				</div>
				<div class="col-xs-8">
					<label for="descripcion"><fmt:message key="label.Description" /></label>
					<sf:textarea path="descripcion" class="form-control" id="descripcion" rows="3" />
				</div>	
				<div class="col-xs-4 col-sm-3">
					<button type="submit" class="btn btn-primary margin-top-20 margin-left-5porciento ${prefUsr.tema}botonguardar"><fmt:message key="Save" /></button>
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