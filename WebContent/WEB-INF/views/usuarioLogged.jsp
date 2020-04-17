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
<body>
	<c:import url="/WEB-INF/views/menu.jsp" />
<br>
	<sf:form method="post" action="${pageContext.request.contextPath}/usuario/logged/save" modelAttribute="usuario" onsubmit="return validar()">
		<div class="form-row">		
			<div class="col-sm-3">
			</div>
			<div class="col-xs-12 col-sm-4">
				<label for="input"><fmt:message key="label.Username" /></label>
				<sf:input path="usuario" type="text" class="form-control" disabled="true"/>
			</div>
			<div class="col-sm-1">
			</div>
			<div class="col-xs-12 col-sm-4">
				<button type="button" class="btn btn-danger margin-left-5porciento" onclick='location.href="<c:url value="/usuario/logged/changePass/${usuario.idUsr}"/>"'>
					<fmt:message key="label.Change.password" />
				</button>
				<br>
				<c:if test="${not empty passChanged}">
					<span style="color: green;">
						<fmt:message key="${passChanged}" />
					</span>
				</c:if>
			</div>
		</div>
		<c:if test="${not empty usuario.clave}">
			<sf:hidden path="usuario"/>
			<sf:hidden path="clave"/>
			<sf:hidden path="idUsr"/>
			<sf:hidden path="habilitado"/>
			<sf:hidden path="fechaCreacion"/>
		</c:if>
		<br/>
		<div class="form-row">		
			<div class="col-sm-3">
			</div>
			<div class="col-xs-12 col-sm-4">
				<label for="inputNombre"><fmt:message key="label.Name" /> *</label> 
				<sf:input path="datosPersonales.nombre" class="form-control" id="inputNombre" />
				<span id="inputNombreError" name="errorSpan"></span>
			</div>
		</div>
		<br/>
		<div class="form-row">		
			<div class="col-sm-3">
			</div>
			<div class="col-xs-12 col-sm-4">
				<label for="inputApellido1"><fmt:message key="label.Fist.lastname" /> *</label>
				<sf:input path="datosPersonales.apellido1" type="text" class="form-control" id="inputApellido1" />
				<span id="inputApellido1Error" name="errorSpan"></span>
			</div>
		</div>
		<br/>
		<div class="form-row">		
			<div class="col-sm-3">
			</div>
			<div class="col-xs-12 col-sm-4">
				<label for="inputApellido2"><fmt:message key="label.Second.lastname" /></label>
				<sf:input path="datosPersonales.apellido2" type="text" class="form-control" id="inputApellido2" />
			</div>
		</div>
		<br/>
		<div class="form-row">		
			<div class="col-sm-3">
			</div>
			<div class="col-xs-12 col-sm-4">
				<div class="custom-control custom-radio custom-control-inline">
					<sf:radiobutton id="customRadioInline2" name="customRadioInline1" class="custom-control-input" path="datosPersonales.sexo" value="Mujer"/>
					<label class="custom-control-label" for="customRadioInline2"><fmt:message key="label.Female" /></label>
				</div>
				<div class="col-sm-4custom-control custom-radio custom-control-inline margin-left-5porciento">
					<sf:radiobutton id="customRadioInline1" name="customRadioInline1" class="custom-control-input" path="datosPersonales.sexo" value="Hombre"/>
					<label class="custom-control-label" for="customRadioInline1"><fmt:message key="label.Male" /></label>
				</div>
			</div>
		</div>
		<br/>
		<div class="form-row">		
			<div class="col-sm-3">
			</div>
			<div class="col-xs-12 col-sm-4">	
				<label for="fechaNacimiento"><fmt:message key="label.Birthdate" /></label>
		    	<sf:input type="date" class="form-control" id="fechaNacimiento" path="datosPersonales.fechaNacimiento"/>
	    	</div>
		</div>
		<fmt:message key="Country.item.column" var="itemSelect"/>
		<fmt:message key="Select.country" var="selectCountry" />
		<br/>
		<div class="form-row">		
			<div class="col-sm-3">
			</div>
			<div class="col-xs-12 col-sm-4">
				<label for="nacionalidad"><fmt:message key="label.Nationality" /></label>
	        	<sf:select path="datosPersonales.nacionalidad.idPais" class="form-control" id="nacionalidad">
	            	<sf:option value="0" label="${selectCountry}" />
	            	<c:forEach items="${paises}" var="pais">
	            		<sf:option value="${pais.idPais}" label="${pais[itemSelect]}" />
	            	</c:forEach>
	        	</sf:select>
        	</div>
		</div>
		<br/>
		<div class="form-row">		
			<div class="col-sm-3">
			</div>
			<div class="col-xs-12 col-sm-4">
				<label for="dni"><fmt:message key="label.idcard" /></label>
				<sf:input path="datosPersonales.dni" type="text" class="form-control" id="dni" />
			</div>
		</div>
		<br/>
		<div class="form-row">		
			<div class="col-sm-3">
			</div>
			<div class="col-xs-12 col-sm-4">
				<label for="email"><fmt:message key="label.Email" /> *</label>
				<sf:input path="datosPersonales.email" type="text" class="form-control" id="email" />
				<span id="emailError" name="errorSpan"></span>
			</div>
		</div>
		<sec:authorize access="!hasAnyRole('ROL_ROOT')">
			<div class="form-row invisible">
		</sec:authorize>
		<sec:authorize access="hasAnyRole('ROL_ROOT')">
			<br/>
			<div class="form-row">		
		</sec:authorize>
			<div class="col-sm-3">
			</div>
			<div class="col-xs-12 col-sm-4">
				<label for="rol"><fmt:message key="label.roles.press.ctrl" /></label>
				<div>
					<sf:select path="usuarioRol">
						<c:forEach var="item" items="${roles}">
							<c:set var="seleccionado" value="false" scope="page" />
							<c:forEach var="ur" items="${usuario.usuarioRol}">
						        <c:if test="${ur.rol.idRol == item.getIdRol()}">					            
									<c:set var="seleccionado" value="true" scope="page" />
						        </c:if>
						    </c:forEach>
						    <c:choose>
						    	<c:when test="${seleccionado}">
							    	<sf:option selected="true" value="${item.getIdRol()}">
								    	<c:out value="${item.getRol()}"></c:out>
						            </sf:option>
						    	</c:when>
						    	<c:otherwise>
						            <sf:option value="${item.getIdRol()}">
										<c:out value="${item.getRol()}"></c:out>
						            </sf:option>
						    	</c:otherwise>
					        </c:choose>
						</c:forEach>
					</sf:select>
				</div>
			</div>
		</div>	
		<br/>
		<div class="form-row">		
			<div class="col-sm-3">
			</div>
			<div class="col-xs-12 col-sm-4">
				<label for="telefono"><fmt:message key="label.Phone" /></label>
				<sf:input path="datosPersonales.telefono" type="text" class="form-control" id="telefono" />
			</div>
		</div>
		<br/>
		<div class="form-row">		
			<div class="col-sm-3">
			</div>
			<div class="col-xs-12 col-sm-6">
				<label for="observaciones"><fmt:message key="label.Observations" /></label>
				<sf:textarea path="datosPersonales.observaciones" class="form-control" id="observaciones" rows="3" />
			</div>
		</div>
		<br/>
<!-- 		<div class="form-row">		 -->
<!-- 			<div class="col-sm-3"> -->
<!-- 			</div> -->
<!-- 			<div class="col-xs-12 col-sm-4 form-check margin-left-5porciento"> -->
<!-- 				<input class="form-check-input" type="checkbox" id="gridCheck"> -->
<%-- 				<label class="form-check-label" for="gridCheck"> <fmt:message key="Accept.terms.and.conditions" /></label> --%>
<!-- 			</div> -->
<!-- 		</div>	 -->
<!-- 		<br/> -->
		<div class="form-row">		
			<div class="col-sm-3">
			</div>
			<div class="col-xs-12 col-sm-4">
				<button type="submit" class="btn btn-primary margin-left-5porciento"><fmt:message key="Send" /></button>
				<button type="button" class="btn btn-primary margin-left-5porciento" onclick='location.href="<c:url value="/"/>"'><fmt:message key="Cancel" /></button>
				<span id="hayError" name="errorSpan" style="color:red"></span>
			</div>
		</div>
	</sf:form>
	
	<footer>
		<c:import url="/WEB-INF/views/importFooter.jsp" />
	</footer>
</body>
</html>