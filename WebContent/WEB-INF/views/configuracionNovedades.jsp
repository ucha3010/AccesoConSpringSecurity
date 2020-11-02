<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- language maneja el idioma actual --%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.damian.utils.multilanguage" />
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
	<title><fmt:message key="label.Offers" /></title>
	<c:import url="/WEB-INF/views/importHead.jsp" />
	<script type="text/javascript" src='<c:url value="/resources/js/validaciones.js" />'></script>
	<script type="text/javascript">
		function confirmDelete(idPro){
			if(confirm("<fmt:message key='Delete.message' />")){
				var url = "<c:url value='/administrar/novedades/delete/"+idPro+"' />";
				location.href=url;
				return true;
			}
			return false;
		}
		function ordenarNovedades(idPro,orden) {
			var url = "<c:url value='/administrar/novedades/order/"+idPro+"/"+orden.value+"' />";
			location.href=url;
			return true;
		}
	</script>

</head>
<body class="${prefUsr.tema}fondopantalla">
	<div class="container">
		<c:import url="/WEB-INF/views/menu.jsp" />
		<fmt:message key="language.name" var="nameColSelect"/>
		
		<div class="row">
			<div class="col-xs-12 hidden-sm hidden-md hidden-lg hidden-xl">
				<ul class="nav nav-tabs">
					<li class="nav-item">
						<a class="nav-link" href="<c:url value='/administrar/ofertas'/>"><fmt:message key="label.Offers" /></a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="<c:url value='/administrar/populares'/>"><fmt:message key="label.Most.popular.products" /></a>
					</li>
					<li class="nav-item">
						<a class="nav-link active" href="<c:url value='#'/>"><fmt:message key="label.New.stock" /></a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="<c:url value='/administrar/campanias/0'/>"><fmt:message key='label.Campaigns' /></a>
					</li>
				</ul>
			</div>
			<div class="hidden-xs col-sm-1">
			</div>
			<div class="hidden-xs col-sm-10">
				<ul class="nav nav-tabs">
					<li class="nav-item">
						<a class="nav-link" href="<c:url value='/administrar/ofertas'/>"><fmt:message key="label.Offers" /></a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="<c:url value='/administrar/populares'/>"><fmt:message key="label.Most.popular.products" /></a>
					</li>
					<li class="nav-item">
						<a class="nav-link active" href="<c:url value='#'/>"><fmt:message key="label.New.stock" /></a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="<c:url value='/administrar/campanias/0'/>"><fmt:message key='label.Campaigns' /></a>
					</li>
				</ul>
			</div>
			<div class="hidden-xs col-sm-1">
			</div>
		</div>
	</div>
		
	<div class="separacion20"></div>
	
	
	<sf:form method="post" action="${pageContext.request.contextPath}/administrar/novedades/save" modelAttribute="administracionOfertas">	
		<div class="row">
			<div class="hidden-xs col-sm-2 col-md-3">	
			</div>
			<div class="col-xs-12 col-sm-8 col-md-6">
	        	<sf:select path="idPro" class="form-control" id="idPro">
	            	<sf:options items="${productos}" itemValue="idPro" itemLabel="${nameColSelect}" />
	        	</sf:select>
			</div>
			<div class="hidden-xs col-sm-2 col-md-3">	
			</div>
		</div>
		<br/>
		<div class="row">	
			<div class="col-xs-5">
			</div>
			<div class="col-xs-2">
				<button type="submit" class="btn btn-primary margin-left-5porciento ${prefUsr.tema}botonguardar"><fmt:message key="label.Add" /></button>
			</div>
		</div>
	</sf:form>
		
	<div class="separacion20"></div>
	
	<div class="row">
		<div class="hidden-xs col-sm-2">	
		</div>
		<div class="col-xs-12 col-sm-8 col-md-8 col-lg-8 col-xl-6">
        	<c:forEach items="${novedades}" var="novedad">
	        	<div class="productoOferta fondoNovedades" title="${novedad.producto[nameColSelect]}">
	        		<div class="col-xs-3 col-sm-3 col-md-2">
						<a title="<fmt:message key='Delete' />" onclick="return confirmDelete(${novedad.idPro})">
							<img src='<c:url value="/resources/imgs/borrar.png"/>' class="tamanio_imagen cursor-pointer">
						</a>						
						<select onchange="ordenarNovedades(${novedad.idPro},this)">
							<c:forEach items="${listadoSelect}" var="sel">
								<option value="${sel}" ${sel == novedad.ordenNovedades ? 'selected' : ''}>${sel}</option>
							</c:forEach>
						</select>
	        		</div>
	        		<div class="col-xs-9 col-sm-5 col-md-6">
	        			<c:out value="${novedad.producto[nameColSelect]}"></c:out>
	        		</div>
	        		<div class="hidden-xs col-sm-2">
							<c:out value="${novedad.producto.marca}" />
	        		</div>
	        		<div class="hidden-xs col-sm-2">
							<c:out value="${novedad.producto.modelo}" />
	        		</div>
        		</div>
        		<div class="separacion10"></div>
        	</c:forEach>
		</div>
		<div class="hidden-xs col-sm-2">	
		</div>
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