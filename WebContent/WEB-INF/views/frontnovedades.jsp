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
	<title><fmt:message key="label.Promotion3" /></title>
	<c:import url="/WEB-INF/views/importHead.jsp" />
	<fmt:message key="language.name" var="nameColSelect"/>
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
	<div class="container">
		<c:import url="/WEB-INF/views/menu.jsp" />
		<div class="well well-sm text-center h2 ${prefUsr.tema}titulo"><fmt:message key="label.Promotion3" /></div>
		
		<c:import url="/WEB-INF/views/divSearchBar.jsp" />
		
		<c:set var="count" value="0" scope="page" />
		
		<c:forEach items="${novedades}" var="novedad">
		
		<c:if test="${(count == 4)}">
			<c:set var="count" value="0" scope="page" />
		</c:if>
	
		<c:if test="${(count == 0)}">
		<div class="row">
		</c:if>
		
		
		
			<div class="col-xs-3">
				<div class="bloqueProducto">
					<c:if test="${(not empty novedad.producto.nombreFotoPrincipal)}">
						<div class="nombreProducto">
							<c:out value="${novedad.producto[nameColSelect]}"/>
						</div>
						<c:if test="${novedad.producto.favorito}">
						<img src='<c:url value="/resources/imgs/favoritos/${prefUsr.botonFavorito}-yes.png"/>' class="tamanio_imagen cursor-pointer" onclick='marcarFavorito(${novedad.producto.idPro},false)'>
						</c:if>
						<c:if test="${(not novedad.producto.favorito && not empty prefUsr.botonFavorito)}">
						<img src='<c:url value="/resources/imgs/favoritos/${prefUsr.botonFavorito}-no.png"/>' class="tamanio_imagen cursor-pointer" onclick='marcarFavorito(${novedad.producto.idPro},true)'>
						</c:if>
						<div class="imagenProducto">
							<a title="${novedad.producto[nameColSelect]}" onclick='location.href="<c:url value='/detalle/producto/${novedad.producto.idPro}' />"' class="cursor-pointer">
								<c:if test="${novedad.booleanOferta}">
									<h4 class="precioSinOferta"><fmt:formatNumber type="currency" value="${novedad.precioSinOferta}" /></h4>
									<h2 class="precioConOferta"><fmt:formatNumber type="currency" value="${novedad.precioConOferta}" /></h2>
								</c:if>
								<c:if test="${not novedad.booleanOferta}">
									<h2 class="precioConOferta"><fmt:formatNumber type="currency" value="${novedad.producto.precioVenta}" /></h2>
								</c:if>
								<img src='<c:url value="/resources/imgs/productos/${novedad.producto.idPro}/${novedad.producto.nombreFotoPrincipal}"/>' class="w-100 border-radius-10-porciento">
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