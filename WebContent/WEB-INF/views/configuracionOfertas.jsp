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
				var url = "<c:url value='/administrar/ofertas/delete/"+idPro+"' />";
				location.href=url;
				return true;
			}
			return false;
		}
		function actualizaPrecioSinOferta(a) {
			for(i=0;i<${jproductos}.length;i++) {
				if(${jproductos}[i].idPro == a.value) {
					document.getElementById("precioSinOfertaDiv").innerHTML = '<label for="precioSinOferta"><fmt:message key="label.No.offer.price" /></label> <input name="precioSinOferta" class="form-control" id="precioSinOferta" readonly value="' + ${jproductos}[i].precioVenta + '" />';
				}
			}
		}
		function ordenarOfertas(idPro,orden) {
			var url = "<c:url value='/administrar/ofertas/order/"+idPro+"/"+orden.value+"' />";
			location.href=url;
			return true;
		}
		function validar(){
			restablecer();
			var campo = ['precioConOferta'];
			var validado = true;			
			for(var i=0; i < campo.length; i++){
			    var nombreRol = document.getElementById(campo[i]);
			    var nombreRolError = document.getElementById(campo[i]+'Error');
				if(nombreRol.value==''){
					nombreRolError.innerHTML = "<fmt:message key='error.field.not.empty' />";
					nombreRol.style.borderColor="red";
					validado = false;
				}
			}
			
			if(validado){
				var precioConOferta = document.getElementById('precioConOferta');
				precioConOferta.value = cambiarComaPorPunto(precioConOferta.value);
				if (isNaN(precioConOferta.value)) {
					document.getElementById('hayError').innerHTML = "<fmt:message key='error.any.field' />: <fmt:message key='label.Offer.price' />";
					return false;					
				}
			}
			
			if(validado){
				return true;
			} else {
				document.getElementById('hayError').innerHTML = "<fmt:message key='error.any.field' />";
				return false;
			}
		}
		function restablecer(){
			var errorSpan = document.getElementsByName('errorSpan');
			for (var i = 0; i < errorSpan.length; i++) {
				errorSpan[i].innerHTML='';
			}
			var campos = document.getElementsByClassName("form-control");
			for (var i = 0; i < campos.length; i++) {
				campos[i].style.borderColor="#ced4da";
			}
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
						<a class="nav-link" href="<c:url value='/administrar/populares'/>"><fmt:message key="label.Most.popular.products" /></a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="<c:url value='/administrar/novedades'/>"><fmt:message key="label.New.stock" /></a>
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
						<a class="nav-link active" href="#"><fmt:message key="label.Offers" /></a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="<c:url value='/administrar/populares'/>"><fmt:message key="label.Most.popular.products" /></a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="<c:url value='/administrar/novedades'/>"><fmt:message key="label.New.stock" /></a>
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
				<label for="precioSinOferta"><fmt:message key="label.No.offer.price" /></label> 
				<sf:input path="precioSinOferta" class="form-control" id="precioSinOferta" disabled="true" />
			</div>
			<div class="col-xs-12 col-sm-3 col-md-2">
				<label for="precioConOferta"><fmt:message key="label.Offer.price" /></label> 
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
		<div class="hidden-xs col-sm-2">	
		</div>
		<div class="col-xs-12 col-lg-8 col-xl-6">
        	<c:forEach items="${ofertas}" var="oferta">
	        	<div class="productoOferta fondoOferta" title="${oferta.producto[nameColSelect]}">
	        	
	        	
	        		<div class="col-xs-3 col-sm-2">
						<a title="<fmt:message key='Delete' />" onclick="return confirmDelete(${oferta.idPro})">
							<img src='<c:url value="/resources/imgs/borrar.png"/>' class="tamanio_imagen cursor-pointer">
						</a>						
						<select onchange="ordenarOfertas(${oferta.idPro},this)">
							<c:forEach items="${listadoSelect}" var="sel">
								<option value="${sel}" ${sel == oferta.ordenOferta ? 'selected' : ''}>${sel}</option>
							</c:forEach>
						</select>
	        		</div>
	        		<div class="col-xs-5 col-md-3">
                		<c:set var="shortProdNombre" value="${fn:substring(oferta.producto[nameColSelect], 0, 65)}" />
	        			<c:out value="${shortProdNombre}"></c:out>
	        		</div>
	        		<div class="col-xs-2 productoOfertaSin">
							<fmt:formatNumber type="currency" value="${oferta.precioSinOferta}" />
	        		</div>
	        		<div class="col-xs-2 productoOfertaCon">
							<fmt:formatNumber type="currency" value="${oferta.precioConOferta}" />
	        		</div>
	        		<div class="hidden-xs hidden-sm col-md-3 productoOfertaFecha">
		        			<fmt:formatDate value="${oferta.fecha}" pattern="dd/MM/yyyy HH:mm"/>
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