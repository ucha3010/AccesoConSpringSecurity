<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- language maneja el idioma actual --%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.damian.utils.multilanguage" />

<div class="menu-dam margin-header d-inline-block bg-color-white-dam">
	<div class="row">
		
		<div class="col-xs-12 col-md-2">
			<a title="Company" href='<c:url value="/"/>'>
				<img src='<c:url value="/resources/imgs/logo.jpg"/>' alt="Logo de la empresa" id="logoImg">
			</a>
		</div>
		
		<div class="col-xs-12 col-md-6 pt-2 border-int-dam">
			<sec:authorize access="isRememberMe()">
				<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT','ROL_USUARIO')">
					<button type="button" class="btn colorFondo4 float-right ml-1 border-color-dam" onclick='location.href="<c:url value="/categoria"/>"'>
						<fmt:message key="label.Categories" />
					</button>
				</sec:authorize>
				<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">
					<button type="button" class="btn colorFondo4 float-right ml-1 border-color-dam" onclick='location.href="<c:url value="/formaPago"/>"'>
						<fmt:message key="label.Payment.methods" />
					</button>
				</sec:authorize>
				<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">
					<button type="button" class="btn colorFondo4 float-right ml-1 border-color-dam" onclick='location.href="<c:url value="/estado"/>"'>
						<fmt:message key="label.states" />
					</button>
				</sec:authorize>
				<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT','ROL_USUARIO')">
					<button type="button" class="btn colorFondo4 float-right ml-1 border-color-dam" onclick='location.href="<c:url value="/factura"/>"'>
						<fmt:message key="label.Bills" />
					</button>
				</sec:authorize>
				<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT','ROL_USUARIO')">
					<button type="button" class="btn colorFondo4 float-right ml-1 border-color-dam" onclick='location.href="<c:url value="/producto"/>"'>
						<fmt:message key="Products" />
					</button>
				</sec:authorize>
				<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">
					<button type="button" class="btn colorFondo4 float-right ml-1 border-color-dam" onclick='location.href="<c:url value="/rol"/>"'>
						<fmt:message key="label.Roles" />
					</button>
				</sec:authorize>				
				<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_USUARIO','ROL_ROOT')">
					<button type="button" class="btn fondo-1f45e0 float-right ml-1 border-color-dam" onclick='location.href="<c:url value="/empresa"/>"'>
						<fmt:message key="Companies" />
					</button>
				</sec:authorize>
				<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">
					<button type="button" class="btn fondo-1f45e0 float-right ml-1 border-color-dam" onclick='location.href="<c:url value="/usuario"/>"'>
						<fmt:message key="label.Users" />
					</button>
				</sec:authorize>
				<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_USUARIO','ROL_ROOT')">
					<button type="button" class="btn fondo-008000 float-right ml-1 border-color-dam" onclick='location.href="<c:url value='/usuario/cliente'/>"'>
						<fmt:message key="label.Customers" />
					</button>
				</sec:authorize>
			</sec:authorize>
	
			<sec:authorize access="isFullyAuthenticated()">
				<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT','ROL_USUARIO')">
					<button type="button" class="btn colorFondo4 float-right ml-1 border-color-dam" onclick='location.href="<c:url value="/categoria"/>"'>
						<fmt:message key="label.Categories" />
					</button>
				</sec:authorize>
				<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">
					<button type="button" class="btn colorFondo4 float-right ml-1 border-color-dam" onclick='location.href="<c:url value="/formaPago"/>"'>
						<fmt:message key="label.Payment.methods" />
					</button>
				</sec:authorize>
				<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">
					<button type="button" class="btn colorFondo4 float-right ml-1 border-color-dam" onclick='location.href="<c:url value="/estado"/>"'>
						<fmt:message key="label.states" />
					</button>
				</sec:authorize>
				<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT','ROL_USUARIO')">
					<button type="button" class="btn colorFondo4 float-right ml-1 border-color-dam" onclick='location.href="<c:url value="/factura"/>"'>
						<fmt:message key="label.Bills" />
					</button>
				</sec:authorize>
				<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT','ROL_USUARIO')">
					<button type="button" class="btn colorFondo4 float-right ml-1 border-color-dam" onclick='location.href="<c:url value="/producto"/>"'>
						<fmt:message key="Products" />
					</button>
				</sec:authorize>
				<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">
					<button type="button" class="btn colorFondo4 float-right ml-1 border-color-dam" onclick='location.href="<c:url value="/rol"/>"'>
						<fmt:message key="label.Roles" />
					</button>
				</sec:authorize>				
				<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_USUARIO','ROL_ROOT')">
					<button type="button" class="btn fondo-1f45e0 float-right ml-1 border-color-dam" onclick='location.href="<c:url value="/empresa"/>"'>
						<fmt:message key="Companies" />
					</button>
				</sec:authorize>
				<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">
					<button type="button" class="btn fondo-1f45e0 float-right ml-1 border-color-dam" onclick='location.href="<c:url value="/usuario"/>"'>
						<fmt:message key="label.Users" />
					</button>
				</sec:authorize>
				<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_USUARIO','ROL_ROOT')">
					<button type="button" class="btn fondo-008000 float-right ml-1 border-color-dam" onclick='location.href="<c:url value='/usuario/cliente'/>"'>
						<fmt:message key="label.Customers" />
					</button>
				</sec:authorize>
			</sec:authorize>
		</div>
		
		<div class="col-xs-12 col-md-4 pt-2 border-int-dam box-user-dam">
			<sec:authorize access="!isAuthenticated()">
				<button type="button" class="btn btn-info float-right ml-1 border-color-dam" onclick='location.href="<c:url value='/usuario/nuevo'/>"'>
					<fmt:message key="label.New.user" />
				</button>
				<button type="button" class="btn btn-success float-right ml-1 border-color-dam" onclick='location.href="<c:url value='/private/${sessionScope.estoy}'/>"'>
					<fmt:message key="label.Login" />
				</button>
			</sec:authorize>
	
			<sec:authorize access="isRememberMe()">
				<sec:authentication property="principal" var="principal" />
				<c:set var="username" value="${principal}" />
				<button type="button" class="btn btn-dark float-right ml-1 btn-sm border-color-dam" onclick='location.href="<c:url value='/logout' />"'>
					<fmt:message key="label.Logout" />
				</button>
				<a title="${username}" href='<c:url value="/usuario/logged/${idUsrLogged}"/>'>
					<img src='<c:url value="/resources/imgs/usuario.png"/>'
					alt="${username}" id="usuarioImg">
				</a>
			</sec:authorize>
	
			<sec:authorize access="isFullyAuthenticated()">
				<sec:authentication property="principal" var="principal" />
				<c:set var="username" value="${principal}" />
				<button type="button" class="btn btn-dark float-right ml-1 btn-sm border-color-dam" onclick='location.href="<c:url value='/logout' />"'>
					<fmt:message key="label.Logout" />
				</button>
				<a title="${username}" href='<c:url value="/usuario/logged/${idUsrLogged}"/>'>
					<img src='<c:url value="/resources/imgs/usuario.png"/>'
					alt="${username}" id="usuarioImg">
				</a>
	
			</sec:authorize>
			<form class="float-right">
				<select class="browser-default custom-select w-auto" name="language"
					onchange="submit();">
					<option value="es_ES"
						<c:if test="${language=='es_ES'}">selected</c:if>>
						<fmt:message key="label.Spanish" />
					</option>
					<option value="en_US"
						<c:if test="${language=='en_US'}">selected</c:if>>
						<fmt:message key="label.English" />
					</option>
				</select>
			</form>
		</div>
	</div>
</div>
<!-- 	dejo un espacio entre el menú y la página en sí -->
<div class="py-24px-dam bg-color-white-dam border border-light border-bottom"></div>
