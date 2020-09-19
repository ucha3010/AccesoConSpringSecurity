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
	<title><fmt:message key="label.User.preferences" /></title>
	<c:import url="/WEB-INF/views/importHead.jsp" />
	
	<script type="text/javascript" src='<c:url value="/resources/js/validaciones.js" />'></script>
	<script type="text/javascript">
		function validar(){
			restablecer();
			var campo = ['tema','botonFavorito'];
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
		<div class="well well-sm text-center h2"><fmt:message key="label.User.preferences" /></div>

		<sf:form method="post" action="${pageContext.request.contextPath}/preferenciaUsuario/update" modelAttribute="preferenciaUsuario" onsubmit="return validar()">
			<sf:hidden path="idPrefUsr"/>
			<div class="row">
				<c:if test="${not empty preferenciaUsuario_actualizado}">					
					<div class="col-xs-2 col-sm-3">
					</div>
					<div class="col-xs-8 col-sm-6">
						<div class="alert alert-success">
							<button class="close" data-dismiss="alert"><span>&times;</span></button>
							<fmt:message key="label.Change.done.success" />
						</div>					
					</div>
					<div class="col-xs-2 col-sm-3">
					</div>
				</c:if>
				<c:if test="${not empty preferenciaUsuario_problemas}">					
					<div class="col-xs-2 col-sm-3">
					</div>
					<div class="col-xs-8 col-sm-6">
						<div class="alert alert-danger">
							<button class="close" data-dismiss="alert"><span>&times;</span></button>
							<fmt:message key="label.Change.not.done.success" />
						</div>					
					</div>
					<div class="col-xs-2 col-sm-3">
					</div>
				</c:if>
			</div>
			<div class="row">		
				<div class="hidden-xs col-sm-1">
				</div>
				<div class="col-xs-12 col-sm-4">
					<label for="tema"><fmt:message key="label.Theme" /></label> 
					<sf:input path="tema" class="form-control" id="tema" />
					<span id="temaError" name="errorSpan"></span>
				</div>
				<div class="col-xs-12 col-sm-4">
					<label for="botonFavorito"><fmt:message key="label.Favourite.button" /></label> 
					<sf:input path="botonFavorito" class="form-control" id="botonFavorito" />
					<span id="botonFavoritoError" name="errorSpan"></span>
				</div>	
				<div class="col-xs-12 col-sm-4">
					<div class="checkbox">
						<label>
							<sf:checkbox path="recibirPublicidad" /><fmt:message key="label.Receive.publicity" />
						</label>
					</div>
				</div>
			</div>
			<br/>
			<div class="row">	
				<div class="hidden-xs col-sm-4">
				</div>
				<div class="col-xs-12 col-sm-8">
					<button type="submit" class="btn btn-primary margin-left-5porciento"><fmt:message key="Send" /></button>
					<button type="button" class="btn btn-primary margin-left-5porciento" onclick='location.href="<c:url value='/usuario/logged/${preferenciaUsuario.idPrefUsr}' />"'><fmt:message key="Return" /></button>
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