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
	<title><fmt:message key="label.User" /></title>
	<c:import url="/WEB-INF/views/importHead.jsp" />
	
	<script type="text/javascript" src='<c:url value="/resources/js/validaciones.js" />'></script>
	<script type="text/javascript">
	function confirmDelete(idUsr,idFot){
		if(confirm("<fmt:message key='Delete.message' />")){
			var url = "<c:url value='/foto/usuarioLogged/delete/"+idUsr+"/"+idFot+"' />";
			location.href=url;
			return true;
		}
		return false;
	}
		function validar(){
			var campo = ['fotoUsuario'];
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
		<div class="well well-sm text-center h2">
			<fmt:message key="label.Pictures.of" /> <c:out value="${usuario.usuario}" />
		</div>
		<c:if test="${not empty foto_agregada}">
			<div class="row">	
				<div class="hidden-xs col-sm-2 col-md-4">
				</div>
				<div class="col-xs-12 col-sm-8 col-md-4">
					<c:if test="${foto_agregada}"><div class="alert alert-success text-center"><fmt:message key="Picture.added" /></div></c:if>
					<c:if test="${not foto_agregada}"><div class="alert alert-danger text-center"><fmt:message key="Picture.not.added" /></div></c:if>
				</div>
				<div class="hidden-xs col-sm-2 col-md-4">
				</div>
			</div>
			<br>
		</c:if>
		<div class="row">
			<c:forEach items="${fotos}" var="f">
				<div class="col-xs-12 col-sm-6 col-md-3">
					<div class="thumbnail">
						<img src='<c:url value="/resources/imgs/usuarios/${usuario.idUsr}/${f.nombre}"/>' alt="${f.nombre}">
						<div class="caption">
							<h3>${f.nombre}</h3>
							<p>${f.descripcion}</p>
						</div>
						<a title="<fmt:message key='Delete' />" onclick="return confirmDelete(${usuario.idUsr},${f.idFot})">
							<img src='<c:url value="/resources/imgs/borrar.png"/>' class="tamanio_imagen cursor-pointer">
						</a>
						<c:if test="${f.principal }">
							<div class="alert alert-success text-center"><fmt:message key='label.Principal' /></div>
						</c:if>
						<c:if test="${not f.principal }">
							<button class="btn btn-warning btn-xs" type="button" onclick='location.href="<c:url value="/foto/usuarioLogged/principal/${f.idFot}"/>"'><fmt:message key="label.Do.principal" /></button>
						</c:if>
					</div>
				</div>				
			</c:forEach>
		</div>
		<br>
		<div class="row">	
			<div class="hidden-xs col-sm-3">
			</div>
			<div class="col-xs-12 col-sm-6">
				<h4><fmt:message key="label.Add.picture" /> <fmt:message key="label.to" /> <c:out value="${usuario.usuario}" /></h4>
			</div>
			<div class="hidden-xs col-sm-3">
			</div>
		</div>
		
		<sf:form method="POST" action="${pageContext.request.contextPath}/foto/usuarioLogged/save" enctype="multipart/form-data" modelAttribute="foto" onsubmit="return validar()">
		
			<sf:hidden path="usuario.idUsr" value="${usuario.idUsr}"/>
			<sf:hidden path="ruta" value="${pageContext.request.contextPath}"/>
			<div class="row">
				<div class="hidden-xs col-sm-3">
				</div>
				<div class="col-xs-12 col-sm-6">
					<label for="fotoUsuario"><fmt:message key="label.Select.file" /></label>
					<input type="file" class="form-control-file form-control" id="fotoUsuario" name="file" />
				</div>
				<div class="hidden-xs col-sm-3">
				</div>
			</div>
			
			<div class="row">
				<div class="hidden-xs col-sm-3">
				</div>
				<div class="col-xs-12 col-sm-6">
					<div class="radio">
						<label for="siPrincipal">
							<sf:radiobutton id="siPrincipal" name="usuarioPrincipal" class="custom-control-input" path="principal" value="true"/>
							<fmt:message key="label.Principal.picture" />
						</label>
					</div>
					<div class="radio">
						<label for="noPrincipal">
							<sf:radiobutton id="noPrincipal" name="usuarioPrincipal" class="custom-control-input"  path="principal" value="false"/>
							<fmt:message key="label.Not.principal.picture" />
						</label>
					</div>
				</div>
				<div class="hidden-xs col-sm-3">
				</div>
			</div>
			
			<div class="row">
				<div class="hidden-xs col-sm-3">
				</div>
				<div class="col-xs-12 col-sm-6">
					<label for="descripcion"><fmt:message key="label.Description" /></label>
					<sf:textarea path="descripcion" class="form-control" rows="3" />
				</div>
				<div class="hidden-xs col-sm-3">
				</div>
			</div>
			<br>
			
			<div class="row">
				<div class="hidden-xs col-sm-3">
				</div>
				<div class="col-xs-12 col-sm-6">
					<button class="btn btn-default" type="button" onclick='location.href="<c:url value="/usuario/logged/${usuario.idUsr}"/>"'><fmt:message key="label.Back" /></button>
					<button class="btn btn-success" type="submit" <c:if test="${fotos.size() == 4 }">disabled</c:if>><fmt:message key="Send" /></button>
				</div>
				<div class="hidden-xs col-sm-3">
				</div>
			</div>
		
		</sf:form>
		
		<footer>
			<c:import url="/WEB-INF/views/importFooter.jsp" />
		</footer>
	</div>
</body>
</html>