<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- language maneja el idioma actual --%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.damian.utils.multilanguage" />

<html>
<head>
	<title>Company</title>
	<c:import url="/WEB-INF/views/importHead.jsp" />

</head>
<body class="${prefUsr.tema}fondopantalla">
	<div class="container">
		<c:import url="/WEB-INF/views/menu.jsp" />
		<c:if test="${not empty usuario_creado}">
		
			<div class="row">
				<div class="col-xs-2 col-sm-3">
				</div>
				<div class="col-xs-8 col-sm-6">
					<div class="alert alert-success">
						<button class="close" data-dismiss="alert"><span>&times;</span></button>
						<fmt:message key="label.user.successfully.created" />
					</div>					
				</div>
				<div class="col-xs-2 col-sm-3">
				</div>
			</div>
		</c:if>	
		
		
		<div class="row">
			<div class="col-md-12">
				<h1>Index</h1>
			</div>
		</div>
		
<!-- 		pruebas fondo botones desde acá -->
		<div class="well well-sm text-center h2 ${prefUsr.tema}titulo"><fmt:message key="label.Categories.and.subcategories" /></div>
		
		<div class="row">	
			<div class="hidden-xs col-sm-4">
			</div>
			<div class="col-xs-12 col-sm-8">
				<a href="#" class="btn btn-danger btn-lg ${prefUsr.tema}botonresto" data-toggle="modal"><fmt:message key='label.Bill.next.days' /></a>
				<button type="button" class="btn fondo-c0c0c0 float-right ml-1 border-color-dam ${prefUsr.tema}botonagregar"><fmt:message key="Add.category" /></button>
			</div>
		</div>
	
		<div class="row">	
			<div class="hidden-xs col-sm-4">
			</div>
			<div class="col-xs-12 col-sm-8">
				<button type="button" class="btn btn-primary margin-left-5porciento ${prefUsr.tema}botonguardar"><fmt:message key="Save" /></button>
				<button type="button" class="btn btn-primary margin-left-5porciento ${prefUsr.tema}botoncancelar"><fmt:message key="Cancel" /></button>
			</div>
		</div>
<!-- 		pruebas fondo botones hasta acá -->
	
		<div class="row">
<!-- 			xs, sm, md y lg hacen referencia al tamaño del dispositivo donde se muestra -->
			<div class="col-xs-12 col-sm-6 col-md-4 col-lg-2">
				<a href='<c:url value="/about"/>'>Acerca de</a><br/>	
				<a href='<c:url value="/index2"/>'>Index2</a>
			</div>
			<div class="col-xs-12 col-sm-6 col-md-8 col-lg-10">	
				Atributos del Model: <c:out value="${mensaje}" /><br/>
				Atributos en Session (estoy): <c:out value="${sessionScope.estoy}" /><br/>
				Atributos en Session (resultado): <c:out value="${sessionScope.resultado}" /><br/>
				Atributos en Session (nombre): <c:out value="${sessionScope.nombre}" /><br/>
				Atributos en Session (valor): <c:out value="${sessionScope.valor}" />
			</div>
		</div>
		
		<div class="row">
			<div class="color1 col-md-2">
				<h1>Fondo</h1>
			</div>
			<div class="col-md-2">
				<h1>Fondo</h1>
			</div>
			<div class="color1 col-md-2">
				<h1>Fondo</h1>
			</div>
			<div class="col-md-2">
				<h1>Fondo</h1>
			</div>
			<div class="color1 col-md-2">
				<h1>Fondo</h1>
			</div>
			<div class="col-md-2">
				<h1>Fondo</h1>
			</div>
		</div>
		
		<br/>
		<br/>
		<h2>Datos del formulario de Admin.jsp</h2>
		<c:out value="${adminForm}"></c:out><br/>
		<br/>
		<br/>
		<br/>Datos
		<br/>
		<br/>
	     <h3>Campo language: <c:out value="${language}" /></h3>
	
	     <fmt:message key="msg.greeting" /><br />
	
	     <h3><fmt:message key="label.Numbers" /></h3>
	     
	     <c:set var="numero" value="125678.4309" />
	     
	     <fmt:message key="label.Number" />: <fmt:formatNumber value="${numero}" type="number"/><br />
	     <fmt:message key="label.Currency" />: <fmt:formatNumber value="${numero}" type="currency"/><br />
	     <fmt:message key="label.Percent" />: <fmt:formatNumber value="${numero}" type="percent"/><br />
		<button class="btn btn-primary">Botón</button>
		<br/>
		<br/>
		<br/>Datos
		<br/>
		<br/>
		<br/>Datos
		<br/>
		<br/>
		<br/>Datos
		<br/>
		<br/>
		<br/>Datos
		<br/>
		<br/>
		<br/>Datos
		<br/>
		<br/>
		<br/>Datos
		<br/>
		<br/>
		<br/>Datos
		<br/>
		<br/>
		<br/>Datos
		<br/>
		<br/>
		<br/>Datos
	</div>

	<footer>
		<c:import url="/WEB-INF/views/importFooter.jsp" />
	</footer>
</body>
</html>