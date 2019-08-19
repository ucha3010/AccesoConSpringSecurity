<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="menu">

	<div id="logo">
		<a title="paginaprincipal" href='<c:url value="/"/>'> <img
			src='<c:url value="/resources/imgs/logo.jpg"/>'
			alt="Logo de la empresa" id="logoImg">
		</a>
	</div>
	<div id="datosUsuario">
		<sec:authorize access="!isAuthenticated()">
			<a id="boton0095ff" href='<c:url value="/usuario"/>'> Nuevo
				usuario </a>
			<a id="boton039091" title="paginaprincipal"
				href='<c:url value="/private/${sessionScope.estoy}"/>'> Acceder
			</a>
		</sec:authorize>

		<sec:authorize access="isRememberMe()">
			Bienvenido
			<sec:authentication property="principal" var="principal" />
			<c:set var="username" value="${principal.username}" />
			<c:out value="${username}"></c:out>
			<a id="botonNegro" href="<c:url value='/logout' />">Cerrar sesión</a>
			<sec:authorize access="hasRole('ROL_ADMIN')">
				<a id="botonVerde" href='<c:url value="/usuario"/>'> Usuarios </a>
			</sec:authorize>
			<sec:authorize access="hasRole('ROL_USUARIO')">
				<a id="botonAzul" href='<c:url value="clientes"/>'> Clientes </a>
			</sec:authorize>
		</sec:authorize>

		<sec:authorize access="isFullyAuthenticated()">
			Bienvenido
			<sec:authentication property="principal" var="principal" />
			<c:set var="username" value="${principal}" />
			<c:out value="${username}"></c:out>
			<a id="botonNegro" href="<c:url value='/logout' />">Cerrar sesión</a>
			<sec:authorize access="hasRole('ROL_ADMIN')">
				<a id="botonVerde" href='<c:url value="/usuario"/>'> Usuarios </a>
			</sec:authorize>
			<sec:authorize access="hasRole('ROL_USUARIO')">
				<a id="botonAzul" href='<c:url value="clientes"/>'> Clientes </a>
			</sec:authorize>
		</sec:authorize>
	</div>
</div>
