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
	<fmt:message key="language.name" var="nameColSelect"/>
	<title><c:out value="${subcategoria[nameColSelect]}"/></title>
	<c:import url="/WEB-INF/views/importHead.jsp" />
	<script type="text/javascript">
		function marcarFavorito(idPro, agregar){
			var url = window.location.href;
			var n = url.indexOf("?");
			if(n > 0) {
				url = url.substring(0,n);
			}
			var cadenaSplit = url.split('/');
			var salida = '';
			for (var i = 4; i < cadenaSplit.length; i++) {
				salida = salida + cadenaSplit[i];
				if(i < cadenaSplit.length-1){
			    	salida = salida + '-';
			    }
			}
			var voy = '';
			if(agregar) {
				voy = "<c:url value='/favorito/save/"+idPro+"/"+salida+"' />";
			} else {
				voy = "<c:url value='/favorito/delete/"+idPro+"/"+salida+"' />";
			}
			location.href=voy;
			return true;
		}
	
	</script>
</head>
<body class="${prefUsr.tema}fondopantalla">
		
	<div class=""> <!-- div container inicio -->
		<c:import url="/WEB-INF/views/menu.jsp" />
		<div class="well well-sm text-center h2 ${prefUsr.tema}titulo"><c:out value="${subcategoria[nameColSelect]}"/></div>
		
			
		<div class="row"> <!-- div barra búsqueda -->
			<div class="col-xs-1 col-sm-3">
			</div>
			<div class="col-xs-11 col-sm-9">
				<c:import url="/WEB-INF/views/divSearchBar.jsp" />
			</div>
		</div>
			
		<div class="row"> <!-- div row inicio -->
			<div class="col-xs-1 col-sm-3 col-lg-2">
			</div>
			<div class="col-xs-11 col-sm-9 col-lg-10">
		
				<c:set var="count" value="0" scope="page" />
				
				<c:forEach items="${productos}" var="producto">
				
				<c:if test="${(count == 4)}">
					<c:set var="count" value="0" scope="page" />
				</c:if>
			
				<c:if test="${(count == 0)}">
				<div class="row">
				</c:if>
				
				
				
					<div class="col-xs-3">
						<div class="bloqueProducto">
							<c:if test="${(not empty producto.nombreFotoPrincipal)}">
								<div class="nombreProducto">
									<c:out value="${producto[nameColSelect]}"/>
								</div>
								<c:if test="${producto.favorito}">
								<img src='<c:url value="/resources/imgs/favoritos/${prefUsr.botonFavorito}-yes.png"/>' class="tamanio_imagen cursor-pointer" onclick='marcarFavorito(${producto.idPro},false)'>
								</c:if>
								<c:if test="${(not producto.favorito && not empty prefUsr.botonFavorito)}">
								<img src='<c:url value="/resources/imgs/favoritos/${prefUsr.botonFavorito}-no.png"/>' class="tamanio_imagen cursor-pointer" onclick='marcarFavorito(${producto.idPro},true)'>
								</c:if>
								<div class="imagenProducto">
									<a title="${producto[nameColSelect]}" onclick='location.href="<c:url value='/detalle/producto/${producto.idPro}' />"' class="cursor-pointer">
										<c:if test="${(producto.precioConOferta > 0)}">
										<h4 class="precioSinOferta"><fmt:formatNumber type="currency" value="${producto.precioSinOferta}" /></h4>
										<h2 class="precioConOferta"><fmt:formatNumber type="currency" value="${producto.precioConOferta}" /></h2>
										</c:if>
										<c:if test="${(producto.precioConOferta == 0)}">
										<h2 class="precioConOferta"><fmt:formatNumber type="currency" value="${producto.precioVenta}" /></h2>
										</c:if>
										<img src='<c:url value="/resources/imgs/productos/${producto.idPro}/${producto.nombreFotoPrincipal}"/>' class="w-100 border-radius-10-porciento">
									</a>
									
								</div>
							</c:if>
						</div>
						<c:set var="count" value="${count + 1}" scope="page"/>
				
		 			<c:if test="${(count < 4)}"><%--cierro el div col-xs-3 --%>
						</div>
					</c:if>
				
				<c:if test="${(count == 4)}"><%--cierro el div col-xs-3 y el div row--%>
					</div>
				</div>
				<div class="height100"></div>
				</c:if>
							
				</c:forEach>
				
				<c:if test="${(count < 4)}">
					</div>
				</div>
				<br>
				</c:if>
				
			</div>
			
		</div> <!-- div row fin -->
		
		<div class="row">
			<div class="col-xs-1 col-sm-3">
			</div>
			<div class="col-xs-11 col-sm-8">
				<footer>
					<c:import url="/WEB-INF/views/importFooter.jsp" />
				</footer>
			</div>
			<div class="col-xs-1">
			</div>
		</div>


		<!-- la parte de los filtros -->
		<div class="hidden-sm hidden-md hidden-lg hidden-xl">
			<div id="du-sidebar">
				<div class="du-toggle-btn">
					<span><fmt:message key='label.Filters' /></span>
				</div>
				<c:forEach items="${filtroTitulos}" var="titulo">
					<div>
						<label for="etiquetaTitulo"><c:out value="${titulo[nameColSelect]}" /></label>
					</div>
					<c:forEach items="${titulo.filtroNombres}" var="nombre">	
						<div class="checkbox">
							<label>									
								<input type="checkbox" name="idNombres" value="${nombre.idNombre}" id="idNombres${nombre.idNombre}" <c:if test="${nombre.seleccionado}">checked="checked"</c:if> />
								<c:out value="${nombre[nameColSelect]}" />
							</label>
						</div>
					</c:forEach>
				</c:forEach>
			</div>				
		</div>
		<div class="hidden-xs col-sm-2">
			<div id="campanias-arbol">
				<c:forEach items="${filtroTitulos}" var="titulo">
					<div>
						<label for="etiquetaTitulo"><c:out value="${titulo[nameColSelect]}" /></label>
					</div>
					<c:forEach items="${titulo.filtroNombres}" var="nombre">	
						<div class="checkbox">
							<label>									
								<input type="checkbox" name="idNombres" value="${nombre.idNombre}" id="idNombres${nombre.idNombre}" <c:if test="${nombre.seleccionado}">checked="checked"</c:if> />
								<c:out value="${nombre[nameColSelect]}" />
							</label>
						</div>
					</c:forEach>
				</c:forEach>
			</div>
		</div>
	</div> <!-- div container fin -->
	
	<script src="<c:url value='/resources/js/jquery.js'/>"></script>
	<script src="<c:url value='/resources/js/menu.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/resources/js/jquery.autocomplete.min.js'/>"></script>
	
	
</body>
</html>