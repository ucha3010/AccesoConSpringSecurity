<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Insert title here</title>
	<script type="text/javascript" src='<c:url value="/resources/js/jquery.js" />'></script>
		<link href="<c:url value='/resources/bootstrap-4.3.1-dist/css/bootstrap.min.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/resources/css/menu.css'/>" rel="stylesheet" type="text/css" />
	<script type="text/javascript">
	jQuery(document).ready(function(){
		jQuery(".confirm").on("click", function(){
			return confirm("Si eliminas este elemento no se podrá recuperar. ¿Continuar?");
		})
	});
	</script>
</head>
<body>
	<c:import url="/WEB-INF/views/menu.jsp" />
<!-- 	<h1>usuario.jsp</h1> -->
<%-- 	<a href='<c:url value="/"/>'>Index</a><br/> --%>
<%-- 	<a href='<c:url value="/about"/>'>Acerca de</a><br/> --%>
<!-- 	<br/> -->
<!-- 	<br/> -->
<!-- 	<br/> -->
<%-- 	<sf:form method="post" action="${pageContext.request.contextPath}/usuario/save" modelAttribute="usuario"> --%>
<!-- 		<table> -->
<!-- 			<tr> -->
<!-- 				<td>Usuario</td> -->
<!-- 				<td> -->
<%-- 					<sf:input path="usuario" type="text"/> --%>
<%-- 					<sf:errors path="usuario" cssStyle="color:red"/> --%>
<!-- 				</td> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>Contraseña</td> -->
<!-- 				<td> -->
<%-- 					<sf:input path="clave" type="text"/> --%>
<%-- 					<sf:errors path="clave" cssStyle="color:red"/> --%>
<!-- 				</td> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>Permisos</td> -->
<!-- 				<td> -->
<%-- 					<sf:input path="fechaCreacion" type="text"/> --%>
<%-- 					<sf:errors path="fechaCreacion" cssStyle="color:red"/> --%>
<!-- 				</td> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td></td> -->
<!-- 				<td><input type="submit" value="Guardar cambios" /></td> -->
<!-- 			</tr>			 -->
<!-- 		</table> -->
<%-- 	</sf:form> --%>
<!-- 	<br/> -->
<%-- 	<c:out value="${resultado}"></c:out><br/><br/> --%>
	
<%-- 	<c:forEach items="${usuarios}" var="usuario"> --%>
<%-- 		<c:out value="${usuario}" /> --%>
<!-- 		<br/> -->
<%-- 	</c:forEach> --%>

<!-- <hr> -->
<br>
	<sf:form method="post" action="${pageContext.request.contextPath}/usuario/save" modelAttribute="usuario">
		<div class="form-group col-xs-12 col-sm-6 col-md-4">
			<label for="input">Usuario</label>
			<sf:input path="usuario" type="text" class="form-control"/> 
		</div>
		<div class="form-row">
			<div class="form-group col-xs-12 col-sm-6 col-md-4">
				<label for="inputPassword1">Contraseña</label> 
				<sf:password class="form-control" id="inputPassword1" path="clave"/>
			</div>
			<div class="form-group col-xs-12 col-sm-6 col-md-4">
				<label for="inputPassword2">Repetir contraseña</label> 
				<sf:password class="form-control" id="inputPassword2" path="clave"/>
			</div>
		</div>
		<div class="form-group col-xs-12 col-sm-6 col-md-4">
			<label for="inputNombre">Nombre</label> 
			<sf:input path="datosPersonales.nombre" class="form-control" id="inputNombre" />
		</div>
		<div class="form-group col-xs-12 col-sm-6 col-md-4">
			<label for="inputApellido1">Primer apellido</label>
			<sf:input path="datosPersonales.apellido1" type="text" class="form-control" id="inputApellido1" />
		</div>
		<div class="form-group col-xs-12 col-sm-6 col-md-4">
			<label for="inputApellido2">Segundo apellido</label>
			<sf:input path="datosPersonales.apellido2" type="text" class="form-control" id="inputApellido2" />
		</div>
		<div class="custom-control custom-radio custom-control-inline">
			<sf:radiobutton id="customRadioInline1" name="customRadioInline1" class="custom-control-input" path="datosPersonales.sexo" value="Hombre"/>
			<label class="custom-control-label" for="customRadioInline1">Hombre</label>
		</div>
		<div class="custom-control custom-radio custom-control-inline">
			<sf:radiobutton id="customRadioInline2" name="customRadioInline1" class="custom-control-input" path="datosPersonales.sexo" value="Mujer"/>
			<label class="custom-control-label" for="customRadioInline2">Mujer</label>
		</div>
		<div class="form-group col-xs-12 col-sm-6 col-md-4">		
			<label for="fechaNacimiento">Fecha de nacimiento</label>
	    	<sf:input type="date" class="form-control" id="fechaNacimiento" path="datosPersonales.fechaNacimiento" value="2000-01-01"/>
		</div>
		<div class="form-group col-xs-12 col-sm-6 col-md-4">
			<label for="dni">DNI</label>
			<sf:input path="datosPersonales.dni" type="text" class="form-control" id="dni" />
		</div>
		<div class="form-group col-xs-12 col-sm-6 col-md-4">
			<label for="email">EMAIL</label>
			<sf:input path="datosPersonales.email" type="text" class="form-control" id="email" />
		</div>
		<div class="form-group col-xs-12 col-sm-6 col-md-4">
			<label for="rol">Roles (presione Ctrl para múltiples opciones)</label>
			<sf:select class="form-control" id="rol" path="usuarioRoles" multiple="true">
				<sf:option value="ROL_CLIENTE" />
				<sf:option value="ROL_USUARIO" />
				<sf:option value="ROL_ADMIN" />
				<sf:option value="ROL_ROOT" />
			</sf:select>
		</div>	
		<div class="form-group col-xs-12 col-sm-6 col-md-4">
			<label for="telefono">Teléfono</label>
			<sf:input path="datosPersonales.email" type="text" class="form-control" id="telefono" />
		</div>
		
		
		
		
		
		<div class="form-group col-xs-12 col-sm-6 col-md-4">
			<label for="input">Nombre comercial</label> <input type="text"
				class="form-control" id="input">
		</div>
		
		<div class="custom-control custom-radio custom-control-inline">
			<input type="radio" id="radioSociedad1" name="radioSociedad1"
				class="custom-control-input"> <label
				class="custom-control-label" for="radioSociedad1">SL</label>
		</div>
		<div class="custom-control custom-radio custom-control-inline">
			<input type="radio" id="radioSociedad2" name="radioSociedad1"
				class="custom-control-input"> <label
				class="custom-control-label" for="radioSociedad2">SA</label>
		</div>
		<div class="custom-control custom-radio custom-control-inline">
			<input type="radio" id="radioSociedad3" name="radioSociedad1"
				class="custom-control-input"> <label
				class="custom-control-label" for="radioSociedad3">Sociedad colectiva</label>
		</div>
		<div class="custom-control custom-radio custom-control-inline">
			<input type="radio" id="radioSociedad4" name="radioSociedad1"
				class="custom-control-input"> <label
				class="custom-control-label" for="radioSociedad4">Sociedad comanditaria</label>
		</div>
		<div class="form-group col-xs-12 col-sm-6 col-md-4">
			<label for="input">Actividad</label> <input type="text"
				class="form-control" id="input">
		</div>
		<div class="form-group col-xs-12 col-sm-6 col-md-4">
			<label for="input">Página web</label> <input type="text"
				class="form-control" id="input">
		</div>
		<div class="form-group col-xs-12 col-sm-6 col-md-4">
			<label for="input">Fax</label> <input type="text"
				class="form-control" id="input">
		</div>
		<div class="form-group col-xs-12 col-sm-6">
			<label for="exampleFormControlTextarea1">Más información</label>
			<textarea class="form-control" id="exampleFormControlTextarea1"
				rows="3"></textarea>
		</div>
		<div class="form-group">
			<div class="form-check">
				<input class="form-check-input" type="checkbox" id="gridCheck">
				<label class="form-check-label" for="gridCheck"> Acepto los términos y condiciones</label>
			</div>
		</div>		
		
		<button type="submit" class="btn btn-primary">Aceptar</button>
	</sf:form>
</body>
</html>