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
	<title><fmt:message key="label.User" /></title>
	<c:import url="/WEB-INF/views/importHead.jsp" />
	
	<script type="text/javascript">
		function validar(){

			var campo = ['inputPassword1','inputPassword2'];
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
			
    		var pass1 = document.getElementById('inputPassword1').value;
	    	var pass2 = document.getElementById('inputPassword2').value;
	    	if(pass1.length > 0 && pass1 != pass2){
	    		document.getElementById('inputPassword1Error').innerHTML = "<fmt:message key='error.password.not.equal' />";
	    		document.getElementById('inputPassword1').style.borderColor="red";
	    		document.getElementById('inputPassword1').style.borderColor="red";
	    		validado = false;
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
<br>
	<sf:form method="post" action="${pageContext.request.contextPath}/usuario/logged/changePass/save" modelAttribute="usuario" onsubmit="return validar()">

		<c:if test="${not empty usuario.clave}">
			<sf:hidden path="usuario"/>
			<sf:hidden path="idUsr"/>
			<sf:hidden path="habilitado"/>
			<sf:hidden path="fechaCreacion"/>
		</c:if>		
		<div class="text-center">
			<h1><c:out value="${usuario.datosPersonales.nombre}"/> <c:out value="${usuario.datosPersonales.apellido1}"/> <c:out value="${usuario.datosPersonales.apellido2}"/></h1>
		</div>
		<br>
		<div class="form-row">	
			<div class="col-sm-2">
			</div>
			<div class="col-xs-12 col-sm-4">
				<label for="inputPassword1"><fmt:message key="label.Password" /> *</label> 
				<sf:password class="form-control" id="inputPassword1" path="clave"/>
				<span id="inputPassword1Error" name="errorSpan"></span>
			</div>
			<div class="col-xs-12 col-sm-4">
				<label for="inputPassword2"><fmt:message key="label.Password.repeat" /> *</label> 
				<input type="password" class="form-control" id="inputPassword2"/>
				<span id="inputPassword2Error" name="errorSpan"></span>
			</div>
		</div>
		<br>
		<div class="form-row">	
			<div class="col-sm-5">
			</div>
			<div class="col-xs-12 col-sm-4">
				<button type="submit" class="btn btn-success margin-left-5porciento"><fmt:message key="Send" /></button>
				<button type="button" class="btn btn-primary margin-left-5porciento" onclick='location.href="<c:url value="/usuario/logged/${usuario.idUsr}"/>"'><fmt:message key="Cancel" /></button>
			</div>
		</div>
	</sf:form>
	
	<footer>
		<c:import url="/WEB-INF/views/importFooter.jsp" />
	</footer>
</body>
</html>