<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="menu">

	<div class="logo">
		<a title="Company" href='<c:url value="/"/>'> <img
			src='<c:url value="/resources/imgs/logo.jpg"/>'
			alt="Logo de la empresa" id="logoImg">
		</a>
	</div>
	<div class="datosUsuario">
		<sec:authorize access="!isAuthenticated()">
			<a id="boton0095ff" href='<c:url value="/usuario"/>'> Nuevo
				usuario </a>
			<a id="boton039091" title="paginaprincipal"
				href='<c:url value="/private/${sessionScope.estoy}"/>'> Acceder
			</a>
		</sec:authorize>

		<sec:authorize access="isRememberMe()">
			<sec:authentication property="principal" var="principal" />
			<c:set var="username" value="${principal.username}" />
<%-- 			<c:out value="${username}"/>			 --%>
			<a id="botonNegro" href="<c:url value='/logout' />">Cerrar sesión</a>
			<a title="${username}" href='<c:url value="/usuario/${username}"/>'>
				<img src='<c:url value="/resources/imgs/usuario.png"/>' alt="${username}" id="usuarioImg">
			</a>			
		</sec:authorize>

		<sec:authorize access="isFullyAuthenticated()">
			<sec:authentication property="principal" var="principal" />
			<c:set var="username" value="${principal}" />
<%-- 			<c:out value="${username}"/>			 --%>
			<a id="botonNegro" href="<c:url value='/logout' />">Cerrar sesión</a>
			<a title="${username}" href='<c:url value="/usuario/${username}"/>'>
				<img src='<c:url value="/resources/imgs/usuario.png"/>' alt="${username}" id="usuarioImg">
			</a>		
		</sec:authorize>
	</div>
	<div class="botones">
		<sec:authorize access="isRememberMe()">
			<sec:authorize access="hasRole('ROL_ADMIN')">
				<a id="botonVerde" href='<c:url value="/usuario"/>'> Usuarios </a>
			</sec:authorize>
			<sec:authorize access="hasRole('ROL_USUARIO')">
				<a id="botonAzul" href='<c:url value="/cliente"/>'> Clientes </a>
			</sec:authorize>
		</sec:authorize>

		<sec:authorize access="isFullyAuthenticated()">
			<sec:authorize access="hasRole('ROL_ADMIN')">
				<a id="botonVerde" href='<c:url value="/usuario"/>'> Usuarios </a>
			</sec:authorize>
			<sec:authorize access="hasRole('ROL_USUARIO')">
				<a id="botonAzul" href='<c:url value="/cliente"/>'> Clientes </a>
			</sec:authorize>
		</sec:authorize>
	</div>
</div>
