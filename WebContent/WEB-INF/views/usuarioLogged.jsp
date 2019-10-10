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
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title><fmt:message key="label.User" /></title>
	<script type="text/javascript" src='<c:url value="/resources/js/jquery.js" />'></script>
	<link href="<c:url value='/resources/bootstrap-4.3.1-dist/css/bootstrap.min.css'/>" rel="stylesheet" type="text/css" />
	<link href="<c:url value='/resources/css/menu.css'/>" rel="stylesheet" type="text/css" />
</head>
<body>
	<c:import url="/WEB-INF/views/menu.jsp" />
<br>
	<sf:form method="post" action="${pageContext.request.contextPath}/usuario/logged/save" modelAttribute="usuario">
		<div class="form-group col-xs-12 col-sm-6">
			<label for="input"><fmt:message key="label.Username" /></label>
			<sf:input path="usuario" type="text" class="form-control" disabled="true"/>
		</div>
		<c:if test="${not empty usuario.clave}">
			<sf:hidden path="usuario"/>
			<sf:hidden path="clave"/>
			<sf:hidden path="idUsr"/>
			<sf:hidden path="habilitado"/>
			<sf:hidden path="fechaCreacion"/>
		</c:if>
		<div class="form-group col-xs-12 col-sm-6">
			<label for="inputNombre"><fmt:message key="label.Name" /></label> 
			<sf:input path="datosPersonales.nombre" class="form-control" id="inputNombre" />
		</div>
		<div class="form-group col-xs-12 col-sm-6">
			<label for="inputApellido1"><fmt:message key="label.Fist.lastname" /></label>
			<sf:input path="datosPersonales.apellido1" type="text" class="form-control" id="inputApellido1" />
		</div>
		<div class="form-group col-xs-12 col-sm-6">
			<label for="inputApellido2"><fmt:message key="label.Second.lastname" /></label>
			<sf:input path="datosPersonales.apellido2" type="text" class="form-control" id="inputApellido2" />
		</div>
		<br/>
		<div class="custom-control custom-radio custom-control-inline margin-left-5porciento">
			<sf:radiobutton id="customRadioInline1" name="customRadioInline1" class="custom-control-input" path="datosPersonales.sexo" value="Hombre"/>
			<label class="custom-control-label" for="customRadioInline1"><fmt:message key="label.Male" /></label>
		</div>
		<div class="custom-control custom-radio custom-control-inline">
			<sf:radiobutton id="customRadioInline2" name="customRadioInline1" class="custom-control-input" path="datosPersonales.sexo" value="Mujer"/>
			<label class="custom-control-label" for="customRadioInline2"><fmt:message key="label.Female" /></label>
		</div>
		<br/><br/>
		<div class="form-group col-xs-12 col-sm-6">		
			<label for="fechaNacimiento"><fmt:message key="label.Birthdate" /></label>
	    	<sf:input type="date" class="form-control" id="fechaNacimiento" path="datosPersonales.fechaNacimiento"/>
		</div>
		<fmt:message key="Country.item.column" var="itemSelect"/>
		<fmt:message key="Select.country" var="selectCountry" />
		<div class="form-group col-xs-12 col-sm-6">
			<label for="nacionalidad"><fmt:message key="label.Nationality" /></label>
        	<sf:select path="datosPersonales.nacionalidad" class="form-control" id="nacionalidad">
            	<sf:option value="empty" label="${selectCountry}" />
            	<sf:options items="${paises}" itemValue="${itemSelect}" itemLabel="${itemSelect}" />
        	</sf:select>
		</div>
		<div class="form-group col-xs-12 col-sm-6">
			<label for="dni"><fmt:message key="label.idcard" /></label>
			<sf:input path="datosPersonales.dni" type="text" class="form-control" id="dni" />
		</div>
		<div class="form-group col-xs-12 col-sm-6">
			<label for="email"><fmt:message key="label.Email" /></label>
			<sf:input path="datosPersonales.email" type="text" class="form-control" id="email" />
		</div>
		<sec:authorize access="!hasAnyRole('ROL_ROOT')">
			<div class="form-group col-xs-12 col-sm-6 col-md-4 invisible">
		</sec:authorize>
		<sec:authorize access="hasAnyRole('ROL_ROOT')">
			<div class="form-group col-xs-12 col-sm-6 col-md-4">
		</sec:authorize>
				<label for="rol"><fmt:message key="label.roles.press.ctrl" /></label>
				<div>
					<sf:select path="usuarioRol">
						<c:forEach var="item" items="${roles}">
							<c:set var="seleccionado" value="false" scope="page" />
							<c:forEach var="ur" items="${usuario.usuarioRol}">
						        <c:if test="${ur.pk.rol.idRol == item.getIdRol()}">					            
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
		<div class="form-group col-xs-12 col-sm-6">
			<label for="telefono"><fmt:message key="label.Phone" /></label>
			<sf:input path="datosPersonales.telefono" type="text" class="form-control" id="telefono" />
		</div>
		<div class="form-group col-xs-12 col-sm-6">
			<label for="observaciones"><fmt:message key="label.Observations" /></label>
			<sf:textarea path="datosPersonales.observaciones" class="form-control" id="observaciones" rows="3" />
		</div>
		<div class="form-group margin-left-5porciento">
			<div class="form-check">
				<input class="form-check-input" type="checkbox" id="gridCheck">
				<label class="form-check-label" for="gridCheck"> <fmt:message key="Accept.terms.and.conditions" /></label>
			</div>
		</div>		
		
		<button type="submit" class="btn btn-primary margin-left-5porciento"><fmt:message key="Send" /></button>
		<button type="button" class="btn btn-primary margin-left-5porciento" onclick='location.href="<c:url value="/"/>"'><fmt:message key="Cancel" /></button>
	</sf:form>
</body>
</html>