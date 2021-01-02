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
	<script src="<c:url value='/resources/js/jquery.js'/>"></script>
	<script type="text/javascript">
		function search(idPro){
			var url = "<c:url value='/test/getOne/"+idPro+"' />";
			location.href=url;
			return true;
		}
	</script>

</head>
<body class="${prefUsr.tema}fondopantalla">
	<div class="container">
		<c:import url="/WEB-INF/views/menu.jsp" />
		
		<div class="row">
			<div class="col-md-12">
				<label>Barra de búsqueda</label>
				  <input type="text"  id="w-input-search" value="">
				  
				<script>
					$(document).ready(function() {
						$('#w-input-search').autocomplete({
							serviceUrl: '${pageContext.request.contextPath}/test/getProductos',
							paramName: "tagName",
							delimiter: ",",
							transformResult: function(response) {
								return {      	
									//must convert json to javascript object before process
									suggestions: $.map($.parseJSON(response), function(item) {
										return { 
											value: item.nombreES, 
											data: item.idPro
										};
									})
								};
							},
							onSelect: function (suggestion) {
							    search(suggestion.data);
							}
						 });
					});
				</script>
			</div>
		</div>
	</div>
	<div><c:out value="${productoEncontrado}"></c:out></div>
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