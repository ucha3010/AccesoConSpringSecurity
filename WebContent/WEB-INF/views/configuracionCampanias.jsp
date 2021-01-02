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
	<title><fmt:message key='Company.name' /></title>
	<c:import url="/WEB-INF/views/importHead.jsp" />
	
	<script type="text/javascript">
		function validar(){

			var campo = ['nombre','descuentoPor','fechaInicio','fechaFin'];

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
		function confirmDelete(idCam){
			if(confirm("<fmt:message key='Delete.message' />")){
				var url = "<c:url value='/administrar/campanias/delete/"+idCam+"' />";
				location.href=url;
				return true;
			}
			return false;
		}
		function agregarProducto(idCam){
			var idPro = document.getElementById('idPro');
			var url = "<c:url value='/administrar/campanias/addProducto/"+idCam+"/"+idPro.value+"' />";
			location.href=url;
			
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
						<a class="nav-link" href="<c:url value='/administrar/novedades'/>"><fmt:message key="label.New.stock" /></a>
					</li>
					<li class="nav-item">
						<a class="nav-link active" href="<c:url value='#'/>"><fmt:message key='label.Campaigns' /></a>
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
						<a class="nav-link" href="<c:url value='/administrar/novedades'/>"><fmt:message key="label.New.stock" /></a>
					</li>
					<li class="nav-item">
						<a class="nav-link active" href="<c:url value='#'/>"><fmt:message key='label.Campaigns' /></a>
					</li>
				</ul>
			</div>
			<div class="hidden-xs col-sm-1">
			</div>
		</div>
	</div>
		
	<div class="separacion20"></div>
		
	<sf:form method="post" action="${pageContext.request.contextPath}/administrar/campanias/save" modelAttribute="campania" onsubmit="return validar()">	
		<div class="container">
			
			<div class="row">
				<div class="col-xs-1 col-sm-3">
				</div>
				<div class="col-xs-11 col-sm-4">
					<label for="nombre"><fmt:message key="label.Campaign.name" /></label> 
					<sf:input path="nombre" class="form-control" id="nombre" />
					<span id="nombreError" name="errorSpan"></span>
				</div>		
				<div class="col-xs-1 hidden-sm hidden-md hidden-lg hidden-xl">
				</div>
				<div class="col-xs-11 col-sm-4">
					<label for="descuentoPor"><fmt:message key="label.Percentage.discount" /></label> 
					<input type="number" step="1" class="form-control" min="1" name="descuentoPor" id="descuentoPor"/>
					<span id="descuentoPorError" name="errorSpan"></span>
				</div>	
				<div class="col-xs-1">
				</div>
			</div>
			
			<div class="row">
				<div class="col-xs-1 col-sm-3">
				</div>
				<div class="col-xs-11 col-sm-4">
					<label for="fechaInicio"><fmt:message key="label.Start.date" /></label>
			    	<sf:input type="date" class="form-control" id="fechaInicio" path="fechaInicio"/>
					<span id="fechaInicioError" name="errorSpan"></span>
				</div>		
				<div class="col-xs-1 hidden-sm hidden-md hidden-lg hidden-xl">
				</div>
				<div class="col-xs-11 col-sm-4">
					<label for="fechaFin"><fmt:message key="label.End.date" /></label>
			    	<sf:input type="date" class="form-control" id="fechaFin" path="fechaFin"/>
					<span id="fechaFinError" name="errorSpan"></span>
				</div>	
				<div class="col-xs-1">
				</div>
			</div>
			
			<div class="row">
				<div class="col-xs-1 col-sm-3">
				</div>
				<div class="col-xs-6 col-sm-4">
					<label for="descripcion"><fmt:message key="label.Description" /></label>
					<sf:textarea path="descripcion" class="form-control" id="descripcion" rows="3" />
				</div>	
				<div class="col-xs-4 col-sm-3">
					<button type="submit" class="btn btn-primary margin-top-20 margin-left-5porciento ${prefUsr.tema}botonguardar"><fmt:message key="Save" /></button>
					<span id="hayError" name="errorSpan" style="color:red"></span>
				</div>	
				<div class="col-xs-1">
				</div>
			</div>
	
			<div class="hidden-sm hidden-md hidden-lg hidden-xl">
				<div id="du-sidebar">
					<div class="du-toggle-btn">
						<span><fmt:message key='label.Campaigns' /></span>
					</div>
					<ul>
		        		<c:forEach items="${campanias}" var="campania">
							<li><a href="<c:url value='/administrar/campanias/${campania.idCam}'/>"><c:out value="${campania.nombre}" /></a></li>
						</c:forEach>
					</ul>
				</div>				
			</div>
			<div class="hidden-xs col-sm-2">
				<div id="campanias-arbol">
					<ul>
		        		<c:forEach items="${campanias}" var="campania">
							<li><a href="<c:url value='/administrar/campanias/${campania.idCam}'/>"><c:out value="${campania.nombre}" /></a></li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</sf:form>
	<hr style="background-color: black;">
	
	<div class="row">
		<div class="col-xs-2 col-sm-4">
		</div>
		<div class="col-xs-9 col-sm-7">
			<h5><fmt:message key='message.products.offer.campaign' /></h5>
		</div>
		<div class="col-xs-1">
		</div>
	</div>
		
	<div class="separacion20"></div>
	
	<div class="row">
		<div class="col-xs-2 col-sm-4">
		</div>
		<div class="col-xs-7 col-sm-6">
			<h2><c:out value="${campaniaSelect.nombre}"> </c:out>  (<fmt:formatNumber type="number" value="${campaniaSelect.descuentoPor}"/> %)</h2>
		</div>
		<div class="col-xs-2 col-sm-1">
			<button class="btn fondo-c0c0c0 border-color-dam ${prefUsr.tema}botonresto" onclick='location.href="<c:url value='/administrar/campanias/edit/${campaniaSelect.idCam}'/>"'>
				<fmt:message key='Edit' />
			</button>
		</div>
		<div class="col-xs-1">
		</div>
	</div>

	<div class="row">
		<div class="col-xs-2 col-sm-4">
		</div>
		<div class="col-xs-7 col-sm-6">
			<p class="fuente-cursive"><fmt:formatDate value="${campaniaSelect.fechaInicio}" pattern="dd/MM/yyyy"/> <fmt:message key="label.to" /> <fmt:formatDate value="${campaniaSelect.fechaFin}" pattern="dd/MM/yyyy"/>
			</p>
		</div>
		<div class="col-xs-2 col-sm-1">
			<button class="btn btn-danger margin-top-20" onclick="return confirmDelete(${campaniaSelect.idCam})">
				<fmt:message key='Delete' />
			</button>
		</div>
		<div class="col-xs-1">
		</div>
	</div>
	
	<div class="row">
		<div class="col-xs-2 col-sm-4">
		</div>
		<div class="col-xs-10 col-sm-8">
			<c:out value="${campaniaSelect.descripcion}"> </c:out>
		</div>
	</div>
		
	<div class="separacion20"></div>
		
	<div class="row">
		<div class="col-xs-2 col-sm-4">
		</div>
		<div class="col-xs-6 col-sm-5">
        	<select name="idPro" class="form-control" id="idPro">
	        	<c:forEach items="${productos}" var="producto">
	        	
		        	<c:if test="${not empty producto.campania}">
		        		<c:set var="fondo" value="nav-link" scope="page"/>
		        	</c:if> 
		        	<c:if test="${empty producto.campania}">
		        		<c:set var="fondo" value="bg-white" scope="page"/>
		        	</c:if>
	        	
	        	
	            	<option value="${producto.idPro}" class="${fondo}">${producto[nameColSelect]}
	            		<c:if test="${not empty producto.campania}">
							(<c:out value="${producto.campania}"/>)
						</c:if> 
	            	</option>
            	</c:forEach>
        	</select>
		</div>
		<div class="col-xs-4 col-sm-3">
			<button type="button" class="btn fondo-c0c0c0 float-right ml-1 border-color-dam ${prefUsr.tema}botonagregar" onclick='agregarProducto(${campaniaSelect.idCam})'>
				<fmt:message key="Add.product" />
			</button>
		</div>
	</div>
		
	<div class="separacion20"></div>
		
	<div class="row">
		<div class="col-xs-2 col-sm-4">
		</div>
		<div class="col-xs-10 col-sm-8">
        	<c:forEach items="${productosCampaniaList}" var="proCam">
				<a title="<fmt:message key='Delete' />" onclick='location.href="<c:url value='/administrar/campanias/deleteProducto/${campaniaSelect.idCam}/${proCam.idPro}'/>"'>
					<img src='<c:url value="/resources/imgs/borrar.png"/>' class="tamanio_imagen cursor-pointer">
				</a>
       			<c:out value="${proCam.producto[nameColSelect]}"> </c:out>
       			<br>
           	</c:forEach>
		</div>
	</div>
		
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
	
	
	<script src="<c:url value='/resources/js/menu.js'/>" type="text/javascript"></script>
</body>
</html>