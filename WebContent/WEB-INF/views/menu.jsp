<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- language maneja el idioma actual --%>
<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.damian.utils.multilanguage" />

<div class="row" id="margin-header">

	<div class="col-xs-12 col-md-2">
		<a title="Company" href='<c:url value="/"/>'> <img
			src='<c:url value="/resources/imgs/logo.jpg"/>'
			alt="Logo de la empresa" id="logoImg">
		</a>
	</div>
	<div class="col-xs-12 col-md-4">
		<sec:authorize access="isRememberMe()">
			<sec:authorize access="hasRole('ROL_ADMIN')">
				<a id="botonVerde" href='<c:url value="/usuario"/>'><fmt:message key="label.Users" /></a>
			</sec:authorize>
			<sec:authorize access="hasRole('ROL_USUARIO')">
				<a id="botonAzul" href='<c:url value="/cliente"/>'><fmt:message key="label.Customers" /></a>
			</sec:authorize>
		</sec:authorize>

		<sec:authorize access="isFullyAuthenticated()">
			<sec:authorize access="hasRole('ROL_ADMIN')">
				<a id="botonVerde" href='<c:url value="/usuario"/>'><fmt:message key="label.Users" /></a>
			</sec:authorize>
			<sec:authorize access="hasRole('ROL_USUARIO')">
				<a id="botonAzul" href='<c:url value="/cliente"/>'><fmt:message key="label.Customers" /></a>
			</sec:authorize>
		</sec:authorize>
	</div>
	<div class="col-xs-2 col-md-2">
		<form>
			<select class="browser-default custom-select" name="language"
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
	<div class="col-xs-10 col-md-4">
		<sec:authorize access="!isAuthenticated()">

			<button type="button" class="btn btn-info float-right ml-1" onclick='location.href="<c:url value='/usuario'/>"'><fmt:message key="label.New.user" /></button>
			<button type="button" class="btn btn-success float-right ml-1" onclick='location.href="<c:url value='/private/${sessionScope.estoy}'/>"'><fmt:message key="label.Login" /></button>

		</sec:authorize>

		<sec:authorize access="isRememberMe()">
			<sec:authentication property="principal" var="principal" />
			<c:set var="username" value="${principal.username}" />
			<%-- 			<c:out value="${username}"/>			 --%>
			<a id="botonNegro" href="<c:url value='/logout' />">
				<fmt:message key="label.Logout" />
			</a>
			<a title="${username}" href='<c:url value="/usuario/${username}"/>'>
				<img src='<c:url value="/resources/imgs/usuario.png"/>'
				alt="${username}" id="usuarioImg">
			</a>

		</sec:authorize>

		<sec:authorize access="isFullyAuthenticated()">
			<sec:authentication property="principal" var="principal" />
			<c:set var="username" value="${principal}" />
			<%-- 			<c:out value="${username}"/>			 --%>
			<a id="botonNegro" href="<c:url value='/logout' />">
				<fmt:message key="label.Logout" />
			</a>
			<a title="${username}" href='<c:url value="/usuario/${username}"/>'>
				<img src='<c:url value="/resources/imgs/usuario.png"/>'
				alt="${username}" id="usuarioImg">
			</a>

		</sec:authorize>
	</div>
</div>
