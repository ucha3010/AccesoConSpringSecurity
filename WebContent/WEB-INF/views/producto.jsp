<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
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
	<title><fmt:message key="Product" /></title>
	<c:import url="/WEB-INF/views/importHead.jsp" />
	
	<script type="text/javascript" src='<c:url value="/resources/js/validaciones.js" />'></script>
	<script type="text/javascript">
		function validar(){
			var campo = ['nombreES', 'nombreEN','precioVenta','precioCompra'];
			restablecer();
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

			var decimales = ['precioVenta','precioCompra','mesesGarantia','peso','volumen'];
			for(var i=0; i < decimales.length; i++){
				var nombreDecimal = document.getElementById(decimales[i]);
				var nombreDecimalError = document.getElementById(decimales[i]+'Error');
				nombreDecimal.value = cambiarComaPorPunto(nombreDecimal.value);
				if(!validarDecimal(nombreDecimal.value)){
					nombreDecimalError.innerHTML = "<fmt:message key='error.not.valid.value' />";
					nombreDecimal.style.borderColor="red";
					validado = false;
				}
			}
			
			if(validado){
				return true;
			} else {
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
		function agregarMarcaJS(){
			var formulario = document.getElementById("formulario");
			var url = "<c:url value='/producto/saveMarca' />";
			formulario.action=url;
        	formulario.submit();
		}
	</script>
</head>
<body class="${prefUsr.tema}fondopantalla">
	<fmt:message key="language.name" var="nameColSelect"/>
	<div class="container">
		<c:import url="/WEB-INF/views/menu.jsp" />
		<c:if test="${producto.idPro != 0}">
			<div class="well well-sm text-center h2 ${prefUsr.tema}titulo"><fmt:message key='Edit' /></div>
		</c:if>
		<c:if test="${producto.idPro == 0}">
			<div class="well well-sm text-center h2 ${prefUsr.tema}titulo"><fmt:message key="Add.product" /></div>
		</c:if>
		<sf:form method="post" action="${pageContext.request.contextPath}/producto/save" modelAttribute="producto" onsubmit="return validar()" id="formulario">
			<c:if test="${producto.idPro != 0}">
				<sf:hidden path="idPro"/>
				<sf:hidden path="unidades"/>
			</c:if>
			<div class="row">		
				<div class="hidden-xs col-sm-1">
				</div>
				<div class="col-xs-12 col-sm-5">
					<label for="nombreES"><fmt:message key="label.Product.name.spanish" /></label> 
					<sf:input path="nombreES" class="form-control" id="nombreES" />
					<span id="nombreESError" name="errorSpan"></span>
				</div>
				<div class="col-xs-12 col-sm-5">
					<label for="nombreEN"><fmt:message key="label.Product.name.english" /></label> 
					<sf:input path="nombreEN" class="form-control" id="nombreEN" />
					<span id="nombreENError" name="errorSpan"></span>
				</div>	
			</div>
			<br/>
			<div class="row">		
				<div class="hidden-xs col-sm-1">
				</div>
				<div class="col-xs-12 col-sm-5">
					<label for="estado"><fmt:message key="label.state" /></label>
					<sf:select class="form-control" id="estado" path="estado">
						<sf:option value="ACTIVE"><fmt:message key="ACTIVE" /></sf:option>
						<sf:option value="INACTIVE"><fmt:message key="INACTIVE" /></sf:option>
						<sf:option value="DISCONTINUED"><fmt:message key="DISCONTINUED" /></sf:option>
					</sf:select>
				</div>
				<div class="col-xs-12 col-sm-5">
					<label for="precioVenta"><fmt:message key="label.salePrice" /></label> 
					<sf:input path="precioVenta" class="form-control" id="precioVenta" />
					<span id="precioVentaError" name="errorSpan"></span>
				</div>	
			</div>
			<br/>
			<div class="row">		
				<div class="hidden-xs col-sm-1">
				</div>
				<div class="col-xs-12 col-sm-5">
					<label for="precioCompra"><fmt:message key="label.purchase.price" /></label> 
					<sf:input path="precioCompra" class="form-control" id="precioCompra" />
					<span id="precioCompraError" name="errorSpan"></span>
				</div>
				<div class="col-xs-8 col-sm-3">
					<label for="marca"><fmt:message key="label.brand" /></label> 
		        	<sf:select path="marca" class="form-control" id="marca">
		            	<sf:option value="" label="" />
		            	<c:forEach items="${marcas}" var="marca">
		            		<sf:option value="${marca.nombre}" label="${marca.nombre}" />
		            	</c:forEach>
		        	</sf:select>
				</div>	
				<div class="col-xs-4 col-sm-2">
					<br>
		        	<a href="#agregarMarca" class="btn btn-default" data-toggle="modal"><fmt:message key="Add.brand" /></a>
					<div class="modal fade" id="agregarMarca">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
									<h4 class="modal-title justify-content-center"><fmt:message key="Add.brand" /></h4>
								</div>
								<div class="modal-body">
									<label for="nombreMarca"><fmt:message key="label.Name" /></label>
									<sf:input path="nombreMarca" class="form-control" id="nombreMarca" />
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-primary margin-left-5porciento ${prefUsr.tema}botonguardar" onclick="agregarMarcaJS()"><fmt:message key="Save" /></button>
									<button type="button" class="btn btn-primary margin-left-5porciento ${prefUsr.tema}botoncancelar" data-dismiss="modal"><fmt:message key="Cancel" /></button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<br/>
			<div class="row">		
				<div class="hidden-xs col-sm-1">
				</div>
				<div class="col-xs-12 col-sm-5">
					<label for="modelo"><fmt:message key="label.model" /></label> 
					<sf:input path="modelo" class="form-control" id="modelo" />
					<span id="modeloError" name="errorSpan"></span>
				</div>
				<div class="col-xs-12 col-sm-5">
					<label for="serie"><fmt:message key="label.Serial.number" /></label> 
					<sf:input path="serie" class="form-control" id="serie" />
					<span id="serieError" name="errorSpan"></span>
				</div>	
			</div>
			<br/>
			<div class="row">		
				<div class="hidden-xs col-sm-1">
				</div>
				<div class="col-xs-12 col-sm-5">
					<label for="ubicacion"><fmt:message key="label.location" /></label> 
					<sf:input path="ubicacion" class="form-control" id="ubicacion" />
					<span id="ubicacionError" name="errorSpan"></span>
				</div>
				<div class="col-xs-12 col-sm-5">
					<div class="checkbox">
						<label>
							<sf:checkbox path="enviar" /><fmt:message key="label.send" />
						</label>
					</div>
					<div class="checkbox">
						<label>
							<sf:checkbox path="vendible" /><fmt:message key="label.salable" />
						</label>
					</div>
				</div>	
			</div>
			<br/>
			<div class="row">		
				<div class="hidden-xs col-sm-1">
				</div>
				<div class="col-xs-12 col-sm-5">
					<label for="peso"><fmt:message key="label.weigth" /></label> 
					<sf:input path="peso" class="form-control" id="peso" />
					<span id="pesoError" name="errorSpan"></span>
				</div>
				<div class="col-xs-12 col-sm-5">
					<label for="volumen"><fmt:message key="label.volume" /></label> 
					<sf:input path="volumen" class="form-control" id="volumen" />
					<span id="volumenError" name="errorSpan"></span>
				
				</div>	
			</div>
			<br/>
			<div class="row">		
				<div class="hidden-xs col-sm-1">
				</div>
				<div class="col-xs-12 col-sm-5">
					<label for="subcategoria"><fmt:message key="label.Subcategory" /></label>
		        	<sf:select path="subcategoria.idSub" class="form-control" id="subcategoria">
		        		<c:forEach var="categoria" items="${categorias}">
		        			<c:forEach var="sub" items="${categoria.subcategorias}">
		        				<sf:option value="${sub.idSub}" label="${categoria[nameColSelect]} - ${sub[nameColSelect]}" />
		        			</c:forEach>
		        		</c:forEach>
		        	</sf:select>
				</div>
				<div class="col-xs-12 col-sm-5">
					<label for="mesesGarantia"><fmt:message key="label.warranty.months" /></label> 
					<sf:input path="mesesGarantia" class="form-control" id="mesesGarantia" />
					<span id="mesesGarantiaError" name="errorSpan"></span>
				</div>	
			</div>
			<br/>
			<div class="row">	
				<div class="hidden-xs col-sm-4">
				</div>
				<div class="col-xs-12 col-sm-8">
					<button type="submit" class="btn btn-primary margin-left-5porciento ${prefUsr.tema}botonguardar"><fmt:message key="Save" /></button>
					<button type="button" class="btn btn-primary margin-left-5porciento ${prefUsr.tema}botoncancelar" onclick='location.href="<c:url value='/producto/all/null/0/100' />"'><fmt:message key="Cancel" /></button>
				</div>
			</div>
		</sf:form>
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