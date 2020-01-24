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
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title><fmt:message key="label.Subcategory" /></title>
	<script type="text/javascript" src='<c:url value="/resources/js/jquery.js" />'></script>
	<link href="<c:url value='/resources/bootstrap-4.3.1-dist/css/bootstrap.min.css'/>" rel="stylesheet" type="text/css" />
	<link href="<c:url value='/resources/css/menu.css'/>" rel="stylesheet" type="text/css" />
	<script type="text/javascript">
		function validarSubcategoria(){
			restablecer();
		    var nombreSubcategoria = document.getElementById('nombreSubcategoria');
		    var nombreSubcategoriaError = document.getElementById('nombreSubcategoriaError');
			if(nombreSubcategoria.value==''){
				nombreSubcategoriaError.innerHTML = "<fmt:message key='error.field.not.empty' />";
				nombreSubcategoria.style.borderColor="red";
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
	<c:import url="/WEB-INF/views/menu.jsp" />
	<fmt:message key="language.name" var="nameColSelect"/>
	<sf:form method="post" action="${pageContext.request.contextPath}/subcategoria/save" modelAttribute="subcategoria" onsubmit="return validarSubcategoria()">
		<c:if test="${subcategoria.idSub != 0}">
			<sf:hidden path="idSub"/>
		</c:if>
		<div class="form-row">		
			<div class="col-sm-1">
			</div>
			<div class="col-xs-12 col-sm-2">
				<label for="categoria"><fmt:message key="label.Category" /></label>
	        	<sf:select path="categoria.idCat" class="form-control" id="categoria">
	            	<sf:options items="${categorias}" itemValue="idCat" itemLabel="${nameColSelect}" />
	        	</sf:select>
			</div>	
			<div class="col-sm-1">
			</div>
			<div class="col-xs-12 col-sm-4">
				<label for="subcategoria"><fmt:message key="Subcategory.name" /></label> 
				<sf:input path="nombre" class="form-control" id="nombreSubcategoria" />
				<span id="nombreSubcategoriaError" name="errorSpan"></span>
			</div>
		</div>
		<br/>
		<div class="form-row">		
			<div class="col-sm-3">
			</div>
			<div class="col-xs-12 col-sm-4">	
				<button type="submit" class="btn btn-primary margin-left-5porciento"><fmt:message key="Send" /></button>
				<button type="button" class="btn btn-primary margin-left-5porciento" onclick='location.href="<c:url value='/categoria' />"'><fmt:message key="Cancel" /></button>
			</div>
		</div>
	</sf:form>
</body>
</html>