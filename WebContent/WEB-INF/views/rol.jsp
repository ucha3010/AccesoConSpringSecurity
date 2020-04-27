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
	<title><fmt:message key="label.Rol" /></title>
	<c:import url="/WEB-INF/views/importHead.jsp" />
	
	<script type="text/javascript">
		function validar(){
			restablecer();
		    var nombreRol = document.getElementById('nombreRol');
		    var nombreRolError = document.getElementById('nombreRolError');
			if(nombreRol.value==''){
				nombreRolError.innerHTML = "<fmt:message key='error.field.not.empty' />";
				nombreRol.style.borderColor="red";
				return false;
			}
			if(nombreRol.value.length < 4 || nombreRol.value.substring(0,4)!='ROL_'){
				nombreRolError.innerHTML = "<fmt:message key='error.rolname.start' />";
				nombreRol.style.borderColor="red";
				return false;
			}
			return true;
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
		<c:if test="${rol.idRol != 0}">
			<div class="well well-sm text-center h2"><fmt:message key='Edit' /></div>
		</c:if>
		<c:if test="${rol.idRol == 0}">
			<div class="well well-sm text-center h2"><fmt:message key="Add.rol" /></div>
		</c:if>
		<sf:form method="post" action="${pageContext.request.contextPath}/rol/save" modelAttribute="rol" onsubmit="return validar()">
			<c:if test="${rol.idRol != 0}">
				<sf:hidden path="idRol"/>
			</c:if>
			<div class="row">		
				<div class="hidden-xs col-sm-3">
				</div>
				<div class="col-xs-12 col-sm-6">
					<label for="rol"><fmt:message key="Rol.name" /></label> 
					<sf:input path="rol" class="form-control" id="nombreRol" />
					<span id="nombreRolError" name="errorSpan"></span>
				</div>
				<div class="hidden-xs col-sm-3">
				</div>
			</div>
			<br/>
			<div class="row">	
				<div class="hidden-xs col-sm-4">
				</div>
				<div class="col-xs-12 col-sm-8">
					<button type="submit" class="btn btn-primary margin-left-5porciento"><fmt:message key="Send" /></button>
					<button type="button" class="btn btn-primary margin-left-5porciento" onclick='location.href="<c:url value='/rol' />"'><fmt:message key="Cancel" /></button>
				</div>
			</div>
		</sf:form>
		
		<footer>
			<c:import url="/WEB-INF/views/importFooter.jsp" />
		</footer>
	</div>
</body>
</html>