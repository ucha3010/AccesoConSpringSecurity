<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="menu">

	<div id="logo">
		<a title="paginaprincipal" href='<c:url value="/"/>'>
			Imagen
<!-- 			<img src='' alt="pagina principal"> -->
		</a>
	</div>
	<div id="datosUsuario">
<!-- 	<h2>Menú</h2> -->

		<sec:authorize access="!isAuthenticated()">
		<div id="botonVerde">
		Acceder
		</div>
		Por favor inicia sesión
		</sec:authorize>
	
		<sec:authorize access="isRememberMe()">
		Usuario ha iniciado sesión como:
		<sec:authentication property="principal" var="principal" />
			<c:set var="username" value="${principal.username}" />
			<c:out value="${username}"></c:out>
			<br />
			<sec:authorize access="hasRole('ROL_ADMIN')">
				<div id="botonVerde">
				Usuarios
				</div>
			</sec:authorize>
			<sec:authorize access="hasRole('ROL_USUARIO')">
				<div id="botonAzul">
				Clientes
				</div>
			</sec:authorize>
			<a href="<c:url value='/logout' />">Cerrar sesión</a>
		</sec:authorize>
	
		<sec:authorize access="isFullyAuthenticated()">
		Usuario ha iniciado sesión como:
		<sec:authentication property="principal" var="principal" />
			<c:set var="username" value="${principal}" />
			<c:out value="${username}"></c:out>
			<br />
			<sec:authorize access="hasRole('ROL_ADMIN')">
				<div id="botonVerde">
				Usuarios
				</div>
			</sec:authorize>
			<sec:authorize access="hasRole('ROL_USUARIO')">
				<div id="botonAzul">
				Clientes
				</div>
			</sec:authorize>
			<a href="<c:url value='/logout' />">Cerrar sesión</a>
		</sec:authorize>
		<br />
		<br /> <a href='<c:url value="/usuario"/>'>Registrar usuario</a><br />
	</div>
</div>
