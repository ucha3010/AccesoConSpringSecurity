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
		function validar(){
			var campo = ['inputNombre','inputApellido1','email'];
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
			
			var email = document.getElementById('email');
			if(email.value.length > 0){
				if(!validarEmail(email.value)){
					email.style.borderColor="red";
					document.getElementById('emailError').innerHTML = "<fmt:message key='error.not.valid.value' />";
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
		<div class="well well-sm text-center h2 ${prefUsr.tema}titulo">
			<fmt:message key="label.User" /> <c:out value="${usuario.usuario}" /> <a onclick='location.href="<c:url value='/foto/usuarioLogged/${usuario.idUsr}' />"' class="btn btn-success ${prefUsr.tema}botonresto"><fmt:message key="label.Pictures" /></a>
		</div>

		<sf:form method="post" action="${pageContext.request.contextPath}/usuario/logged/save" modelAttribute="usuario" onsubmit="return validar()">
			<c:if test="${(not empty usuario.clave) && (empty username_existente)}">
				<sf:hidden path="clave"/>
				<sf:hidden path="idUsr"/>
				<sf:hidden path="habilitado"/>
				<sf:hidden path="fechaCreacion"/>
				<sf:hidden path="usuario"/>
			</c:if>
			<fmt:message key="Country.item.column" var="itemSelect"/>
			<fmt:message key="Select.country" var="selectCountry" />
			
			<div class="row">		
				<div class="hidden-xs col-sm-6 col-md-6">
					<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">
						<a title="<fmt:message key="label.System.configuration" />" href='<c:url value='/administrar/configuracion/find/${usuario.idUsr}' />'>
							<img src='<c:url value="/resources/imgs/engranaje.png"/>' class="tamanio_imagen_100x100">
						</a>
					</sec:authorize>
					<a title="<fmt:message key="label.User.preferences" />" href='<c:url value='/preferenciaUsuario/${usuario.idUsr}' />'>
						<img src='<c:url value="/resources/imgs/engranaje_doble_usuario.png"/>' class="tamanio_imagen_100x100">
					</a>
				</div>	
				<div class="col-xs-4 hidden-sm hidden-md hidden-lg hidden-xl">
					<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">
						<a title="<fmt:message key="label.System.configuration" />" href='<c:url value='/administrar/configuracion/find/${usuario.idUsr}' />'>
							<img src='<c:url value="/resources/imgs/engranaje.png"/>' class="tamanio_imagen_50x50">
						</a>
					</sec:authorize>
					<a title="<fmt:message key="label.User.preferences" />" href='<c:url value='/preferenciaUsuario/${usuario.idUsr}' />'>
						<img src='<c:url value="/resources/imgs/engranaje_doble_usuario.png"/>' class="tamanio_imagen_50x50">
					</a>
				</div>
				<div class="col-xs-5 col-sm-4 col-md-3">
					<button type="button" class="btn btn-danger margin-left-5porciento" onclick='location.href="<c:url value="/usuario/logged/changePass/${usuario.idUsr}"/>"'>
						<fmt:message key="label.Change.password" />
					</button>
				</div>
				<div class="col-xs-3 col-sm-2 col-md-1">
					<c:if test="${(not empty usuario.fotos)}">
						<c:forEach items="${usuario.fotos}" var="fotoPerfil">
							<c:if test="${fotoPerfil.principal}">
								<a href="#foto1" class="thumbnail" data-toggle="modal">
									<img src='<c:url value="/resources/imgs/usuarios/${usuario.idUsr}/${fotoPerfil.nombre}"/>' class="width-100">
								</a>
								<c:set var="fotoPrincipal" value="${fotoPerfil}" scope="page" />
							</c:if>
						</c:forEach>
					</c:if>
				</div>
			</div>
			<br/>
			
			<div class="modal fade" id="foto1">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h4 class="modal-title"><img src='<c:url value="/resources/imgs/usuarios/${usuario.idUsr}/${fotoPrincipal.nombre}"/>' class="justify-content-center"></h4>
						</div>
					</div>
				</div>
			</div>
			
			<c:if test="${not empty passChanged}">
				<div class="row">		
					<div class="hidden-xs col-sm-2 col-md-3">
					</div>
					<div class="col-xs-12 col-sm-10 col-md-9">
						<span style="color: green;">
							<fmt:message key="${passChanged}" />
						</span>
					</div>
				</div>
				<br/>
			</c:if>
			<div class="row">		
				<div class="hidden-xs col-sm-1">
				</div>
				<div class="col-xs-12 col-sm-5">
					<label for="inputNombre"><fmt:message key="label.Name" /> *</label> 
					<sf:input path="datosPersonales.nombre" class="form-control" id="inputNombre" />
					<span id="inputNombreError" name="errorSpan"></span>
				</div>
				<div class="col-xs-12 col-sm-5">
					<label for="inputApellido1"><fmt:message key="label.Fist.lastname" /> *</label>
					<sf:input path="datosPersonales.apellido1" type="text" class="form-control" id="inputApellido1" />
					<span id="inputApellido1Error" name="errorSpan"></span>
				</div>		
				<div class="hidden-xs col-sm-1">
				</div>
			</div>
			<br/>
			<div class="row">		
				<div class="hidden-xs col-sm-1">
				</div>
				<div class="col-xs-12 col-sm-5">
					<label for="inputApellido2"><fmt:message key="label.Second.lastname" /></label>
					<sf:input path="datosPersonales.apellido2" type="text" class="form-control" id="inputApellido2" />
				</div>
				<div class="col-xs-12 col-sm-2">
					<div class="radio">
						<label for="female">
							<sf:radiobutton id="female" name="customRadioInline1" class="custom-control-input" path="datosPersonales.sexo" value="Mujer"/>
							<fmt:message key="label.Female" />
						</label>
					</div>
					<div class="radio">
						<label for="male">
							<sf:radiobutton id="male" name="customRadioInline1" class="custom-control-input" path="datosPersonales.sexo" value="Hombre"/>
							<fmt:message key="label.Male" />
						</label>
					</div>
				</div>	
				<div class="col-xs-12 col-sm-3">	
					<label for="fechaNacimiento"><fmt:message key="label.Birthdate" /></label>
			    	<sf:input type="date" class="form-control" id="fechaNacimiento" path="datosPersonales.fechaNacimiento"/>
				</div>
			</div>
			<br/>
			<div class="row">		
				<div class="hidden-xs col-sm-1">
				</div>
				<div class="col-xs-12 col-sm-5">
					<label for="nacionalidad"><fmt:message key="label.Nationality" /></label>
		        	<sf:select path="datosPersonales.nacionalidad.idPais" class="form-control" id="nacionalidad">
		            	<sf:option value="0" label="${selectCountry}" />
		            	<c:forEach items="${paises}" var="pais">
		            		<sf:option value="${pais.idPais}" label="${pais[itemSelect]}" />
		            	</c:forEach>
		        	</sf:select>
				</div>
				<div class="col-xs-12 col-sm-5">
					<label for="dni"><fmt:message key="label.idcard" /></label>
					<sf:input path="datosPersonales.dni" type="text" class="form-control" id="dni" />
				</div>		
				<div class="hidden-xs col-sm-1">
				</div>
			</div>
			<br/>
			<div class="row">		
				<div class="hidden-xs col-sm-1">
				</div>
				<div class="col-xs-12 col-sm-5">
					<label for="email"><fmt:message key="label.Email" /> *</label>
					<sf:input path="datosPersonales.email" type="text" class="form-control" id="email" />
					<span id="emailError" name="errorSpan"></span>
				</div>		
				<div class="hidden-xs col-sm-1">
				</div>
			</div>
			<br/>
			<span class="d-none">
				<sf:select path="anda">
					<c:forEach var="item" items="${roles}">
						<c:set var="seleccionado" value="false" scope="page" />
						<c:forEach var="ur" items="${usuario.usuarioRol}">
					        <c:if test="${ur.rol.idRol == item.getIdRol()}">					            
								<c:set var="seleccionado" value="true" scope="page" />
					        </c:if>
					    </c:forEach>
					    <c:choose>
					    	<c:when test="${seleccionado}">
						    	<sf:option selected="true" value="${item.getIdRol()}" />
					    	</c:when>
				        </c:choose>
					</c:forEach>
				</sf:select>
			</span>
			<div class="row">		
				<div class="hidden-xs col-sm-1">
				</div>
				<div class="col-xs-12 col-sm-5">
					<label for="telefono"><fmt:message key="label.Phone" /></label>
					<sf:input path="datosPersonales.telefono" type="text" class="form-control" id="telefono" />
				</div>
				<div class="col-xs-12 col-sm-5">
					<label for="observaciones"><fmt:message key="label.Observations" /></label>
					<sf:textarea path="datosPersonales.observaciones" class="form-control" id="observaciones" rows="3" />
				</div>		
				<div class="hidden-xs col-sm-1">
				</div>
			</div>
			<br/>
			<div class="row">		
				<div class="hidden-xs col-sm-1">
				</div>
				<div class="col-xs-12 col-sm-5">
				
				</div>
				<div class="col-xs-12 col-sm-5">
				
				</div>		
				<div class="hidden-xs col-sm-1">
				</div>
			</div>
			<br/>
			<div class="row">	
				<div class="hidden-xs col-sm-4">
				</div>
				<div class="col-xs-12 col-sm-8">
					<button type="submit" class="btn btn-primary margin-left-5porciento ${prefUsr.tema}botonguardar"><fmt:message key="Save" /></button>
					<button type="button" class="btn btn-primary margin-left-5porciento ${prefUsr.tema}botoncancelar" onclick='location.href="<c:url value="/"/>"'><fmt:message key="Cancel" /></button>
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