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
			var campo = ['ivaEnvio'];
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
		<div class="well well-sm text-center h2"><fmt:message key="label.System.configuration" /></div>

		<sf:form method="post" action="${pageContext.request.contextPath}/administrar/configuracion/save" modelAttribute="frontAdministrarConfiguracion" onsubmit="return validar()">
			<c:if test="${frontAdministrarConfiguracion.idUsr != 0}">
				<sf:hidden path="idUsr"/>
			</c:if>
			<div class="row">
				<div class="col-xs-12 text-center">
					<c:if test="${not empty adminConfig_save}">
						<span style="color: green;">
							<fmt:message key="label.Change.done.success" />
						</span>
					</c:if>
					<c:if test="${not empty adminConfig_no_save}">
						<span style="color: red;">
							<fmt:message key="label.Change.not.done.success" />
						</span>
					</c:if>
				</div>
			</div>
			<div class="row">		
				<div class="hidden-xs col-sm-1">
				</div>
				<div class="col-xs-12 col-sm-5">
					<label for="nombreES"><fmt:message key="label.Vat.delivery" /></label> 
					<sf:input path="ivaEnvio" class="form-control" id="ivaEnvio" />
					<span id="ivaEnvioError" name="errorSpan"></span>
				</div>
<!-- 				<div class="col-xs-12 col-sm-5"> -->
<%-- 					<label for="nombreEN"><fmt:message key="Category.name.english" /></label>  --%>
<%-- 					<sf:input path="nombreEN" class="form-control" id="nombreCategoriaEN" /> --%>
<!-- 					<span id="nombreCategoriaENError" name="errorSpan"></span> -->
<!-- 				</div>	 -->
			</div>
			<br/>
			<div class="row">	
				<div class="hidden-xs col-sm-4">
				</div>
				<div class="col-xs-12 col-sm-8">
					<button type="submit" class="btn btn-primary margin-left-5porciento"><fmt:message key="Send" /></button>
					<button type="button" class="btn btn-primary margin-left-5porciento" onclick='location.href="<c:url value='/usuario/logged/${frontAdministrarConfiguracion.idUsr}' />"'><fmt:message key="Return" /></button>
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