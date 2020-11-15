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
	<title><fmt:message key='Company.name' /></title>
	<c:import url="/WEB-INF/views/importHead.jsp" />
	<fmt:message key="language.name" var="nameColSelect"/>

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
			<div class="hidden-xs col-sm-12">
				<div class="col-sm-4 col-md-3">
				    <select class="form-control">
				      <option>Todas las categorías</option>
				      <option>2</option>
				      <option>3</option>
				      <option>4</option>
				      <option>5</option>
				    </select>
				</div>
				<div class="col-sm-6 col-md-7">
					<div class="input-group">
						<input type="text" class="form-control" placeholder="<fmt:message key='label.Search' />" id="buscar"/>
						<div class="input-group-btn">
							<button class="btn btn-primary padding9" type="submit">
								<span class="glyphicon glyphicon-search"></span>
							</button>
						</div>
					</div>
				</div>
				<div class="col-sm-1">
					<img src='<c:url value="/resources/imgs/favoritos/default.png"/>' class="tamanio_imagen cursor-pointer">
				</div>
				<div class="col-sm-1">
					<img src='<c:url value="/resources/imgs/carrito.png"/>' class="height27 cursor-pointer">
				</div>
			</div>
			<div class="col-xs-12 hidden-sm hidden-md hidden-lg hidden-xl">
				<div class="col-xs-2">
				</div>
				<div class="col-xs-4">
					<img src='<c:url value="/resources/imgs/favoritos/default.png"/>' class="tamanio_imagen cursor-pointer">
				</div>
				<div class="col-xs-4">
					<img src='<c:url value="/resources/imgs/carrito.png"/>' class="height27 cursor-pointer">
				</div>
				<div class="col-xs-2">
				</div>
			</div>
			<div class="col-xs-12 hidden-sm hidden-md hidden-lg hidden-xl">
				<div class="col-xs-6">
				    <select class="form-control">
				      <option>Todas las categorías</option>
				      <option>2</option>
				      <option>3</option>
				      <option>4</option>
				      <option>5</option>
				    </select>
				</div>
				<div class="col-xs-6">
					<div class="input-group">
						<input type="text" class="form-control" placeholder="<fmt:message key='label.Search' />" id="buscarXS"/>
						<div class="input-group-btn">
							<button class="btn btn-primary padding9" type="submit">
								<span class="glyphicon glyphicon-search"></span>
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="separacion10"></div>
	
		<div class="row">
			<div class="hidden-xs col-sm-12">
				<div class="hidden-xs col-sm-2">
					<div class="botonesindex defaultbotonresto ${prefUsr.tema}botonresto" onclick='location.href="<c:url value='/footer/findyourstore'/>"'>
						<fmt:message key="label.Find.your.strore" />
					</div>
				</div>
				<div class="hidden-xs col-sm-2">
					<div class="botonesindex defaultbotonresto ${prefUsr.tema}botonresto" onclick='location.href="<c:url value='/footer/membersclub'/>"'>
						<fmt:message key="label.Members.club" />
					</div>
				</div>
				<div class="hidden-xs col-sm-2">
					<div class="botonesindex defaultbotonresto ${prefUsr.tema}botonresto" onclick='location.href="<c:url value='/footer/shipments'/>"'>
						<fmt:message key="label.Shipments" />
					</div>
				</div>
				<div class="hidden-xs col-sm-2">
					<div class="botonesindex defaultbotonresto ${prefUsr.tema}botonresto" onclick='location.href="<c:url value='/footer/brands'/>"'>
						<fmt:message key="label.Brands" />
					</div>
				</div>
				<div class="hidden-xs col-sm-2">
					<div class="botonesindex defaultbotonresto ${prefUsr.tema}botonresto" onclick='location.href="<c:url value='/footer/Frequentquestions'/>"'>
						<fmt:message key="label.Frequent.questions" />
					</div>
				</div>
				<div class="hidden-xs col-sm-2">
					<div class="botonesindex defaultbotonresto ${prefUsr.tema}botonresto" onclick='location.href="<c:url value='/footer/contact'/>"'>
						<fmt:message key="label.Contact" />
					</div>
				</div>
			</div>
			<div class="col-xs-12 hidden-sm hidden-md hidden-lg hidden-xl">
				<div class="col-xs-4">
					<div class="botonesindex defaultbotonresto ${prefUsr.tema}botonresto" onclick='location.href="<c:url value='/footer/findyourstore'/>"'>
						<fmt:message key="label.Find.your.strore" />
					</div>
				</div>
				<div class="col-xs-4">
					<div class="botonesindex defaultbotonresto ${prefUsr.tema}botonresto" onclick='location.href="<c:url value='/footer/membersclub'/>"'>
						<fmt:message key="label.Members.club" />
					</div>
				</div>
				<div class="col-xs-4">
					<div class="botonesindex defaultbotonresto ${prefUsr.tema}botonresto" onclick='location.href="<c:url value='/footer/shipments'/>"'>
						<fmt:message key="label.Shipments" />
					</div>
				</div>
			</div>
			<div class="col-xs-12 hidden-sm hidden-md hidden-lg hidden-xl">
				<div class="separacion10"></div>
			</div>
			<div class="col-xs-12 hidden-sm hidden-md hidden-lg hidden-xl">
				<div class="col-xs-4">
					<div class="botonesindex defaultbotonresto ${prefUsr.tema}botonresto" onclick='location.href="<c:url value='/footer/brands'/>"'>
						<fmt:message key="label.Brands" />
					</div>
				</div>
				<div class="col-xs-4">
					<div class="botonesindex defaultbotonresto ${prefUsr.tema}botonresto" onclick='location.href="<c:url value='/footer/Frequentquestions'/>"'>
						<fmt:message key="label.Frequent.questions" />
					</div>
				</div>
				<div class="col-xs-4">
					<div class="botonesindex defaultbotonresto ${prefUsr.tema}botonresto" onclick='location.href="<c:url value='/footer/contact'/>"'>
						<fmt:message key="label.Contact" />
					</div>
				</div>
			</div>
		</div>
		
		<div class="separacion20"></div>
	
		<div class="row">
			<div class="col-xs-12">
				<div class="speechPublicitario">
					<c:out value="${speech.valorText}"></c:out>
				</div>
			</div>
		</div>
		
		<div class="separacion10"></div>
	
		<div class="row">
			<div class="col-xs-12">
				<div id="principalCarrousel" class="carousel slide" data-ride="carousel">
					<!-- Indicators -->
					<ol class="carousel-indicators changeitem">
					
						<c:set var="activeSlide" value="active" scope="page" />
						<c:set var="contadorSlide" value="0" scope="page" />
						<c:forEach items="${slide}" var="sl">
							<li data-target="#principalCarrousel" data-slide-to="${contadorSlide}" class="${activeSlide}"></li>
							<c:set var="activeSlide" value="" scope="page" />
							<c:set var="contadorSlide" value="${contadorSlide + 1}" scope="page" />
						</c:forEach>
					</ol>
					
					<!-- Wrapper for slides -->
					<div class="carousel-inner">
						
						<c:set var="activeSlide" value="active" scope="page" />
						<c:set var="contadorSlide" value="1" scope="page" />
						<c:forEach items="${slide}" var="foto">
							<div class="item item-center ${activeSlide}">
								<a onclick='location.href="<c:url value='/footer/promotion${contadorSlide}' />"'><!-- ESTO HAY QUE PONERLO COMO EN EL FOOTER!!! -->
									<img src='<c:url value="/resources/imgs/slide/0/${foto.nombre}"/>' alt="${foto.descripcion}">
								</a>
							</div>
							<c:set var="activeSlide" value="" scope="page" />
							<c:set var="contadorSlide" value="${contadorSlide + 1}" scope="page" />
						</c:forEach>

					</div>
					
					<!-- Left and right controls -->
					<a class="left carousel-control" href="#principalCarrousel" data-slide="prev">
						<span class="glyphicon glyphicon-chevron-left"></span>
						<span class="sr-only">Previous</span>
					</a>
					<a class="right carousel-control" href="#principalCarrousel" data-slide="next">
						<span class="glyphicon glyphicon-chevron-right"></span>
						<span class="sr-only">Next</span>
					</a>
				</div>
			</div>
		</div>
	
		<div class="titulossecundarios"><fmt:message key="label.Offers" /></div>
	
		<div class="row">
			<div class="col-xs-12">
				<c:set var="count" value="9" scope="page" />
				<c:forEach items="${ofertas}" var="oferta">
					<div class="col-xs-3">
						<c:if test="${(not empty oferta.producto.nombreFotoPrincipal)}">
							<a title="${oferta.producto[nameColSelect]}" onclick='location.href="<c:url value='/detalle/producto/${oferta.producto.idPro}' />"' class="cursor-pointer">
								<img src='<c:url value="/resources/imgs/productos/${oferta.producto.idPro}/${oferta.producto.nombreFotoPrincipal}"/>' class="w-100 border-radius-10-porciento">
							</a>
						</c:if>
					</div>
					<c:set var="count" value="${count - 3}" scope="page"/>
				</c:forEach>
				<div class="col-xs-1">
					<div class="fondoOferta border-radius-10-porciento ver-mas">
						<fmt:message key="label.See.more" />
					</div>
				</div>
				<div class="col-xs-2">
				</div>
				<div class="col-xs-${count} col-sm-${count}">
				</div>
			</div>
			<div class="col-xs-12">
				<c:set var="count" value="9" scope="page" />
				<c:forEach items="${ofertas}" var="oferta">
					<div class="col-xs-3 text-center">
						<c:if test="${(not empty oferta.producto.nombreFotoPrincipal)}">
							<c:out value="${oferta.producto[nameColSelect]}"></c:out>
						</c:if>
					</div>
					<c:set var="count" value="${count - 3}" scope="page"/>
				</c:forEach>
				<div class="col-xs-3">
				</div>
				<div class="col-xs-${count} col-sm-${count}">
				</div>
			</div>
		</div>
	
		<div class="titulossecundarios"><fmt:message key="label.Most.popular.products" /></div>
	
		<div class="row">
			<div class="col-xs-12">
				<c:set var="count" value="9" scope="page" />
				<c:forEach items="${populares}" var="popular">
					<div class="col-xs-3">
						<c:if test="${(not empty popular.producto.nombreFotoPrincipal)}">
							<a title="${popular.producto[nameColSelect]}" onclick='location.href="<c:url value='/detalle/producto/${popular.producto.idPro}' />"' class="cursor-pointer">
								<img src='<c:url value="/resources/imgs/productos/${popular.producto.idPro}/${popular.producto.nombreFotoPrincipal}"/>' class="w-100 border-radius-10-porciento">
							</a>
						</c:if>
					</div>
					<c:set var="count" value="${count - 3}" scope="page"/>
				</c:forEach>
				<div class="col-xs-1">
					<div class="fondoPopular border-radius-10-porciento ver-mas">
						<fmt:message key="label.See.more" />
					</div>
				</div>
				<div class="col-xs-2">
				</div>
				<div class="col-xs-${count} col-sm-${count}">
				</div>
			</div>
			<div class="col-xs-12">
				<c:set var="count" value="9" scope="page" />
				<c:forEach items="${populares}" var="popular">
					<div class="col-xs-3 text-center">
						<c:if test="${(not empty popular.producto.nombreFotoPrincipal)}">
							<c:out value="${popular.producto[nameColSelect]}"></c:out>
						</c:if>
					</div>
					<c:set var="count" value="${count - 3}" scope="page"/>
				</c:forEach>
				<div class="col-xs-3">
				</div>
				<div class="col-xs-${count} col-sm-${count}">
				</div>
			</div>
		</div>
		
		<div class="titulossecundarios"><fmt:message key="label.New.stock" /></div>
	
		<div class="row">
			<div class="col-xs-12">
				<c:set var="count" value="9" scope="page" />
				<c:forEach items="${novedades}" var="novedad">
					<div class="col-xs-3">
						<c:if test="${(not empty novedad.producto.nombreFotoPrincipal)}">
							<a title="${novedad.producto[nameColSelect]}" onclick='location.href="<c:url value='/detalle/producto/${novedad.producto.idPro}' />"' class="cursor-pointer">
								<img src='<c:url value="/resources/imgs/productos/${novedad.producto.idPro}/${novedad.producto.nombreFotoPrincipal}"/>' class="w-100 border-radius-10-porciento">
							</a>
						</c:if>
					</div>
					<c:set var="count" value="${count - 3}" scope="page"/>
				</c:forEach>
				<div class="col-xs-1">
					<div class="fondoNovedades border-radius-10-porciento ver-mas">
						<fmt:message key="label.See.more" />
					</div>
				</div>
				<div class="col-xs-2">
				</div>
				<div class="col-xs-${count} col-sm-${count}">
				</div>
			</div>
			<div class="col-xs-12">
				<c:set var="count" value="9" scope="page" />
				<c:forEach items="${novedades}" var="novedad">
					<div class="col-xs-3 text-center">
						<c:if test="${(not empty novedad.producto.nombreFotoPrincipal)}">
							<c:out value="${novedad.producto[nameColSelect]}"></c:out>
						</c:if>
					</div>
					<c:set var="count" value="${count - 3}" scope="page"/>
				</c:forEach>
				<div class="col-xs-3">
				</div>
				<div class="col-xs-${count} col-sm-${count}">
				</div>
			</div>
		</div>
	
	</div>
		
	<div class="separacion20"></div>
	
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