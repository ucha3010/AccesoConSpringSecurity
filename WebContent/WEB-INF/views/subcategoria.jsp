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
	<title><fmt:message key="label.Subcategory" /></title>
	<c:import url="/WEB-INF/views/importHead.jsp" />
	
	<script type="text/javascript">
		function validar(){
			restablecer();
			var campo = ['nombreSubcategoriaES','nombreSubcategoriaEN'];
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
		<fmt:message key="language.name" var="nameColSelect"/>
		<c:if test="${subcategoria.idSub != 0}">
			<div class="well well-sm text-center h2"><fmt:message key='Edit' /></div>
		</c:if>
		<c:if test="${subcategoria.idSub == 0}">
			<div class="well well-sm text-center h2"><fmt:message key="Add.subcategory" /></div>
		</c:if>
		<sf:form method="post" action="${pageContext.request.contextPath}/subcategoria/save" modelAttribute="subcategoria" onsubmit="return validar()">
			<c:if test="${subcategoria.idSub != 0}">
				<sf:hidden path="idSub"/>
			</c:if>
			<div class="row">		
				<div class="hidden-xs col-sm-3">
				</div>
				<div class="col-xs-12 col-sm-6">
					<label for="categoria"><fmt:message key="label.Category" /></label>
		        	<sf:select path="categoria.idCat" class="form-control" id="categoria">
		            	<sf:options items="${categorias}" itemValue="idCat" itemLabel="${nameColSelect}" />
		        	</sf:select>
				</div>		
				<div class="hidden-xs col-sm-3">
				</div>	
			</div>
			<br/>
			<div class="row">		
				<div class="hidden-xs col-sm-1">
				</div>
				<div class="col-xs-12 col-sm-5">
					<label for="subcategoria"><fmt:message key="Subcategory.name.spanish" /></label> 
					<sf:input path="nombreES" class="form-control" id="nombreSubcategoriaES" />
					<span id="nombreSubcategoriaESError" name="errorSpan"></span>
				</div>
				<div class="col-xs-12 col-sm-5">
					<label for="subcategoria"><fmt:message key="Subcategory.name.english" /></label> 
					<sf:input path="nombreEN" class="form-control" id="nombreSubcategoriaEN" />
					<span id="nombreSubcategoriaENError" name="errorSpan"></span>
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