<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<%-- language maneja el idioma actual --%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.damian.utils.multilanguage" />
<html>
<head>
	<title><fmt:message key="label.Filters" /></title>
	<c:import url="/WEB-INF/views/importHead.jsp" />
	<script type="text/javascript">
		function confirmDeleteNombre(id){
			if(confirm("<fmt:message key='Delete.message' />")){
				var url = "<c:url value='/filtro/delete/nombre/"+id+"/${frontProductoFiltro.idPro}/${frontProductoFiltro.productoPaginaInicio}/${frontProductoFiltro.productoTotalPaginas}' />";
				location.href=url;
				return true;
			}
			return false;
		}
		function confirmDeleteTitulo(id){
			if(confirm("<fmt:message key='Delete.message' />")){
				var url = "<c:url value='/filtro/delete/titulo/"+id+"/${frontProductoFiltro.idPro}/${frontProductoFiltro.productoPaginaInicio}/${frontProductoFiltro.productoTotalPaginas}' />";
				location.href=url;
				return true;
			}
			return false;
		}
	</script>
</head>
<body class="${prefUsr.tema}fondopantalla">
	<div class="container">
		<c:import url="/WEB-INF/views/menu.jsp" />
		<fmt:message key="language.name" var="nameColSelect"/>
		<div class="well well-sm text-center h2 ${prefUsr.tema}titulo">
			<fmt:message key="label.Filters" /> 
			<fmt:message key="label.of" /> 
			<span class="color-green"><c:out value="${subcategoria[nameColSelect]}" /></span> 
			<fmt:message key="label.for" />  
			<span class="color-blue"><c:out value="${producto[nameColSelect]}" /></span> 
		</div>
		<div class="row">
			<div class="col-xs-12 text-center">
				<c:if test="${not empty filtro_guardado}">
					<span style="color: green;">
						<fmt:message key="label.Filter.added" />
					</span>
				</c:if>
				<c:if test="${not empty filtro_eliminado}">
					<span style="color: green;">
						<fmt:message key="label.Filter.deleted" />
					</span>
				</c:if>
				<c:if test="${not empty filtro_asociado}">
					<span style="color: orange;">
						<fmt:message key="error.Filter.asociated" />
					</span>
				</c:if>
			</div>
		</div>
		<sf:form method="post" action="${pageContext.request.contextPath}/filtro/save" modelAttribute="frontProductoFiltro">
		
			<sf:hidden path="idPro"/>
			<sf:hidden path="productoPaginaInicio"/>
			<sf:hidden path="productoTotalPaginas"/>
			<sf:hidden path="idSub"/>
			<div class="row">		
				<div class="hidden-xs col-sm-3">
				</div>
				<div class="col-xs-12 col-sm-7">
					<label for="categoria"><fmt:message key="label.Add.filters.title" /></label>
					<sf:input path="tituloNuevo" class="form-control" />
				</div>			
				<div class="hidden-xs col-sm-2">
				</div>
			</div>
			<div class="row">		
				<div class="hidden-xs col-sm-3">
				</div>
				<div class="col-xs-12 col-sm-7">
					<label for="categoria"><fmt:message key="label.Add.filter.name" /></label>
		        	<sf:select path="idTituloNuevo" class="form-control">
		            	<sf:options items="${filtroTitulos}" itemValue="idTitulo" itemLabel="${nameColSelect}" />
		        	</sf:select>
					<sf:input path="nombreNuevo" class="form-control" />
				</div>			
				<div class="hidden-xs col-sm-2">
				</div>
			</div>
		
			<br/>
			<hr/>
			<br/>
			<div class="row">		
				<div class="col-xs-1 col-sm-3">
				</div>
				<div class="col-xs-11 col-sm-9">
					<c:forEach items="${filtroTitulos}" var="titulo">
						<div>
							<label for="etiquetaTitulo"><c:out value="${titulo[nameColSelect]}" /></label>
							<c:if test="${empty titulo.filtroNombres}">
								<a title="<fmt:message key='Delete' />" onclick="return confirmDeleteTitulo(${titulo.idTitulo})" class="cursor-pointer">
									<img src='<c:url value="/resources/imgs/borrar.png"/>' class="tamanio_imagen">
								</a>
							</c:if>
						</div>
						<c:forEach items="${titulo.filtroNombres}" var="nombre">	
							<div class="checkbox">
								<label>
<%-- 									<sf:checkbox path="idNombres" value="${nombre.idNombre}" /> --%>
									
									<input type="checkbox" name="idNombres" value="${nombre.idNombre}" id="idNombres${nombre.idNombre}" <c:if test="${nombre.seleccionado}">checked="checked"</c:if> />
									
									<c:out value="${nombre[nameColSelect]}" />
								</label>
								<a title="<fmt:message key='Delete' />" onclick="return confirmDeleteNombre(${nombre.idNombre})" class="cursor-pointer">
									<img src='<c:url value="/resources/imgs/borrar.png"/>' class="tamanio_imagen">
								</a>
							</div>
						</c:forEach>
					</c:forEach>
				</div>	
			</div>
			<br/>
			<div class="row">	
				<div class="hidden-xs col-sm-4">
				</div>
				<div class="col-xs-12 col-sm-8">
					<button type="submit" class="btn btn-primary margin-left-5porciento ${prefUsr.tema}botonguardar"><fmt:message key="Save" /></button>
					<button type="button" class="btn btn-primary margin-left-5porciento ${prefUsr.tema}botoncancelar" onclick='location.href="<c:url value='/producto/all/null/${frontProductoFiltro.productoPaginaInicio}/${frontProductoFiltro.productoTotalPaginas}' />"'><fmt:message key="Return" /></button>
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