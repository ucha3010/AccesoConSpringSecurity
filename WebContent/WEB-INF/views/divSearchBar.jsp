<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- language maneja el idioma actual --%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.damian.utils.multilanguage" />
<fmt:message key="language.name" var="nameColSelect"/>


		
		<script type="text/javascript">		
			function irSubcategoria() {
				var valSelected = document.getElementById("subcategoriaSelect");
				var url = "<c:url value='/front/subcategoria/"+valSelected.value+"' />";
				location.href=url;
			}
		</script>
		<div class="row">
			<div class="hidden-xs col-sm-12">
				<div class="col-sm-4 col-md-3">
				    <select class="form-control select-view" id="subcategoriaSelect" onchange="irSubcategoria()">
				    
					<option><fmt:message key="label.All.categories" /></option>
				    
					<c:forEach items="${categorias}" var="categoria">
						<optgroup label="<c:out value="${categoria[nameColSelect]}" />">
						
						<c:forEach items="${categoria.subcategorias}" var="subcategoria">
							<option value="${subcategoria.idSub}" ><c:out value="${subcategoria[nameColSelect]}" /></option>
						</c:forEach>
						
					</c:forEach>
						
				    </select>
				</div>
				<div class="col-sm-6 col-md-7">
					<div class="input-group w-100">
						<input type="text" class="form-control" placeholder="<fmt:message key='label.Search' />" id="w-input-search" value=""/>
					</div>
				</div>
				<div class="col-sm-1">
					<img src='<c:url value="/resources/imgs/favoritos/default.png"/>' class="tamanio_imagen cursor-pointer">
				</div>
				<div class="col-sm-1">
					<img src='<c:url value="/resources/imgs/carrito.png"/>' class="height27 cursor-pointer">
				</div>
				  
				<script>
					$(document).ready(function() {
						$('#w-input-search').autocomplete({
							serviceUrl: '${pageContext.request.contextPath}/detalle/getProductos/<fmt:message key="selected.language" />',
							paramName: "tagName",
							delimiter: ",",
							transformResult: function(response) {
								return {      	
									//must convert json to javascript object before process
									suggestions: $.map($.parseJSON(response), function(item) {
										return { 
											value: item.name, 
											data: item.id
										};
									})
								};
							},
							onSelect: function (suggestion) {
								var url = "<c:url value='/detalle/producto/"+suggestion.data+"' />";
								location.href=url;
								return true;
							}
						 });
					});
				</script>
				
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
				    <select class="form-control select-view" id="subcategoriaSelect" onchange="irSubcategoria()">
				    
					<option><fmt:message key="label.All.categories" /></option>
					
					<c:forEach items="${categorias}" var="categoria">
						<optgroup label="<c:out value="${categoria[nameColSelect]}" />">
						
						<c:forEach items="${categoria.subcategorias}" var="subcategoria">
							<option value="${subcategoria.idSub}" ><c:out value="${subcategoria[nameColSelect]}" /></option>
						</c:forEach>
						
					</c:forEach>
						
				    </select>
				</div>
				<div class="col-xs-6">
					<div class="input-group">
						<input type="text" class="form-control" placeholder="<fmt:message key='label.Search' />" id="w-input-searchXS" value=""/>
					</div>
				</div>
				  
				<script>
					$(document).ready(function() {
						$('#w-input-searchXS').autocomplete({
							serviceUrl: '${pageContext.request.contextPath}/producto/getProductos/<fmt:message key="selected.language" />',
							paramName: "tagName",
							delimiter: ",",
							transformResult: function(response) {
								return {      	
									//must convert json to javascript object before process
									suggestions: $.map($.parseJSON(response), function(item) {
										return { 
											value: item.name, 
											data: item.id
										};
									})
								};
							},
							onSelect: function (suggestion) {
								var url = "<c:url value='/detalle/producto/"+suggestion.data+"' />";
								location.href=url;
								return true;
							}
						 });
					});
				</script>
				
			</div>
		</div>
		
		<div class="separacion20"></div>