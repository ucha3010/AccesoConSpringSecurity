<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- language maneja el idioma actual --%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.damian.utils.multilanguage" />

<html>
<head>
	<title><fmt:message key="label.Offers" /></title>
	<c:import url="/WEB-INF/views/importHead.jsp" />
	<script type="text/javascript">
		function confirmDelete(idPro){
			if(confirm("<fmt:message key='Delete.message' />")){
				var url = "<c:url value='/administrar/ofertas/delete/"+idPro+"' />";
				location.href=url;
				return true;
			}
			return false;
		}
		function actualizaPrecioSinOferta(a) {
			for(i=0;i<${jproductos}.length;i++) {
				if(${jproductos}[i].idPro == a.value) {
					document.getElementById("precioSinOfertaDiv").innerHTML = '<label for="precioSinOferta">Precio sin oferta***</label> <input name="precioSinOferta" class="form-control" id="precioSinOferta" readonly value="' + ${jproductos}[i].precioVenta + '" />';
				}
			}
		}
		function ordenarOfertas(idPro,orden) {
			var url = "<c:url value='/administrar/ofertas/order/"+idPro+"/"+orden.value+"' />";
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
						<a class="nav-link active" href="#"><fmt:message key="label.Offers" /></a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="<c:url value='/'/>"><fmt:message key="label.Most.popular.products" /></a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="<c:url value='/'/>"><fmt:message key="label.New.stock" /></a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="<c:url value='/'/>"><fmt:message key='label.Campaigns' /></a>
					</li>
				</ul>
			</div>
			<div class="hidden-xs col-sm-1">
			</div>
			<div class="hidden-xs col-sm-10">
				<ul class="nav nav-tabs">
					<li class="nav-item">
						<a class="nav-link active" href="#"><fmt:message key="label.Offers" /></a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="<c:url value='/'/>"><fmt:message key="label.Most.popular.products" /></a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="<c:url value='/'/>"><fmt:message key="label.New.stock" /></a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="<c:url value='/'/>"><fmt:message key='label.Campaigns' /></a>
					</li>
				</ul>
			</div>
			<div class="hidden-xs col-sm-1">
			</div>
		</div>
	</div>
		
	<div class="separacion20"></div>
	
	
	<sf:form method="post" action="${pageContext.request.contextPath}/administrar/ofertas/save" modelAttribute="administracionOfertas" onsubmit="return validar()">	
		<div class="row">
			<div class="hidden-xs col-sm-2 col-md-3">	
			</div>
			<div class="col-xs-12 col-sm-8 col-md-6">
	        	<sf:select path="idPro" class="form-control" id="idPro" onchange="actualizaPrecioSinOferta(this)">
	            	<sf:options items="${productos}" itemValue="idPro" itemLabel="${nameColSelect}" />
	        	</sf:select>
			</div>
			<div class="hidden-xs col-sm-2 col-md-3">	
			</div>
		</div>
		<br/>
		<div class="row">		
			<div class="hidden-xs col-sm-3 col-md-4">
			</div>
			<div class="col-xs-12 col-sm-3 col-md-2" id="precioSinOfertaDiv">
				<label for="precioSinOferta">Precio sin oferta***</label> 
				<sf:input path="precioSinOferta" class="form-control" id="precioSinOferta" disabled="true" />
			</div>
			<div class="col-xs-12 col-sm-3 col-md-2">
				<label for="precioConOferta">Precio con oferta***</label> 
				<sf:input path="precioConOferta" class="form-control" id="precioConOferta" />
				<span id="precioConOfertaError" name="errorSpan"></span>
			</div>			
			<div class="hidden-xs col-sm-3 col-md-4">
			</div>
		</div>
		<br/>
		<div class="row">	
			<div class="col-xs-5">
			</div>
			<div class="col-xs-2">
				<button type="submit" class="btn btn-primary margin-left-5porciento ${prefUsr.tema}botonguardar"><fmt:message key="label.Add" /></button>
				<span id="hayError" name="errorSpan" style="color:red"></span>
			</div>
		</div>
	</sf:form>
		
	<div class="separacion20"></div>
	
	<div class="row">
		<div class="hidden-xs col-sm-2 col-md-3">	
		</div>
		<div class="col-xs-12 col-sm-8 col-md-6">
        	<c:forEach items="${ofertas}" var="oferta">
				<a title="<fmt:message key='Delete' />" onclick="return confirmDelete(${oferta.idPro})">
					<img src='<c:url value="/resources/imgs/borrar.png"/>' class="tamanio_imagen">
				</a>
				
				<select onchange="ordenarOfertas(${oferta.idPro},this)">
					<c:forEach items="${listadoSelect}" var="sel">
						<option value="${sel}" ${sel == oferta.ordenOferta ? 'selected' : ''}>${sel}</option>
					</c:forEach>
				</select>
        		<c:out value="${oferta.producto[nameColSelect]}"></c:out>
        		<c:out value="${oferta.precioSinOferta}"></c:out>
        		<c:out value="${oferta.precioConOferta}"></c:out>
        		<fmt:formatDate value="${oferta.fecha}" pattern="dd/MM/yyyy HH:mm"/>
        		<br>
        	</c:forEach>
		</div>
		<div class="hidden-xs col-sm-2 col-md-3">	
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