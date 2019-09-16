<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- language maneja el idioma actual --%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.damian.utils.multilanguage" />

<html>
<head>
	<title>Direcciones</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<script type="text/javascript" src='<c:url value="/resources/js/jquery.js" />'></script>
	<link href="<c:url value='/resources/bootstrap-4.3.1-dist/css/bootstrap.min.css'/>" rel="stylesheet" type="text/css" />
	<link href="<c:url value='/resources/css/menu.css'/>" rel="stylesheet" type="text/css" />
	<link href="<c:url value='/resources/css/tablas.css'/>" rel="stylesheet" type="text/css" />
	<script type="text/javascript">
		jQuery(document).ready(function(){
			jQuery(".confirm").on("click", function(){
				return confirm("Si eliminas este elemento no se podrá recuperar. ¿Continuar?");
			})
		});
	</script>
</head>
<body>
	<c:import url="/WEB-INF/views/menu.jsp" />
	<div class="form-group col-xs-12 col-sm-6">
		<h1><c:out value="${usuario.datosPersonales.nombre}" /> <c:out value="${usuario.datosPersonales.apellido1}" /> <c:out value="${usuario.datosPersonales.apellido2}" /></h1>
	</div>
	<div class="divTabla">
		<table id="tablaOrdenar" class="table table-striped">
			<thead>
				<tr>
					<c:set var="count" value="0" scope="page" />
					<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">
						<th colspan="2"></th>
						<c:set var="count" value="${count + 2}" scope="page"/>
					</sec:authorize>
					<th onclick="sortTable(${count})"><fmt:message key="label.Type.road" /></th>
					<c:set var="count" value="${count + 1}" scope="page"/>
					<th onclick="sortTable(${count})"><fmt:message key="label.Street" /></th>
					<c:set var="count" value="${count + 1}" scope="page"/>
					<th onclick="sortTable(${count})"><fmt:message key="label.Number" /></th>
					<c:set var="count" value="${count + 1}" scope="page"/>
					<th onclick="sortTable(${count})"><fmt:message key="label.Rest" /></th>
					<c:set var="count" value="${count + 1}" scope="page"/>
					<th onclick="sortTable(${count})"><fmt:message key="label.Postal.code" /></th>
					<c:set var="count" value="${count + 1}" scope="page"/>
					<th onclick="sortTable(${count})"><fmt:message key="label.Province" /></th>
					<c:set var="count" value="${count + 1}" scope="page"/>
					<th onclick="sortTable(${count})"><fmt:message key="label.City" /></th>
					<c:set var="count" value="${count + 1}" scope="page"/>
					<th onclick="sortTable(${count})"><fmt:message key="label.Country" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${direcciones}" var="direccion">
				    <tr>
						<td class="sin_padding">
							<button type="button" class="btn btn-default" onclick='location.href="<c:url value='/direccion/formulario/${direccion.idDir}' />"'>
							  <img src='<c:url value="/resources/imgs/editar.png"/>' alt="Editar" class="tamanio_imagen">
							</button>
						</td>
						<td class="sin_padding">
							<button type="button" class="btn btn-default confirm" onclick='location.href="<c:url value='/direccion/delete/${direccion.idDir}' />"'>
							  <img src='<c:url value="/resources/imgs/borrar.png"/>' alt="Borrar" class="tamanio_imagen">
							</button>
						</td>
						<td><c:out value="${direccion.tipoVia}" /></td>
						<td><c:out value="${direccion.nombreVia}" /></td>	
						<td><c:out value="${direccion.numero}" /></td>
						<td><c:out value="${direccion.resto}" /></td>
						<td><c:out value="${direccion.cp}" /></td>	
						<td><c:out value="${direccion.provincia}" /></td>	
						<td><c:out value="${direccion.ciudad}" /></td>
						<td><c:out value="${direccion.pais}" /></td>
				    </tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<script type="text/javascript" src="<c:url value='/resources/js/tabla_ordenar.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/bootstrap-4.3.1-dist/js/bootstrap.min.js'/>"></script>

	

	
	
<%-- 		<c:forEach var="direccion" items="${usaurio.datosPersonales.direcciones}"> --%>
<!-- 			<div class="form-row">		 -->
<!-- 				<div class="form-group col-xs-12 col-sm-2"> -->
<!-- 					<label for="exampleFormControlSelect1">Tipo vía</label> <select -->
<!-- 						class="form-control" id="exampleFormControlSelect1"> -->
<!-- 						<option>Calle</option> -->
<!-- 						<option>Avenida</option> -->
<!-- 						<option>Pasaje</option> -->
<!-- 						<option>Carretera</option> -->
<!-- 						<option>Plaza</option> -->
<!-- 					</select> -->
<!-- 				</div> -->
<!-- 				<div class="form-group col-xs-12 col-sm-8"> -->
<!-- 					<label for="nombreVia">Nombre vía</label> -->
<%-- 					<sf:input path="direccion.nombreVia" type="text" class="form-control" id="nombreVia" /> --%>
<!-- 				</div> -->
<!-- 				<div class="form-group col-xs-12 col-sm-2"> -->
<!-- 					<label for="input">Número</label> <input type="text" -->
<!-- 						class="form-control" id="input"> -->
<!-- 				</div>		 -->
<!-- 			</div> -->
<!-- 			<div class="form-group col-xs-12 col-sm-6"> -->
<!-- 				<label for="input">Resto dirección</label> <input type="text" -->
<!-- 					class="form-control" id="input"> -->
<!-- 			</div>		 -->
<!-- 			<div class="form-group col-xs-12 col-sm-6 col-md-4"> -->
<!-- 				<label for="input">Ciudad</label> <input type="text" -->
<!-- 					class="form-control" id="input"> -->
<!-- 			</div> -->
			
<!-- 			<div class="form-row"> -->
<!-- 				<div class="form-group col-xs-12 col-sm-4"> -->
<!-- 					<label for="input">Código Postal</label> <input type="text" -->
<!-- 						class="form-control" id="input"> -->
<!-- 				</div>	 -->
<!-- 				<div class="form-group col-xs-12 col-sm-4"> -->
<!-- 					<label for="exampleFormControlSelect1">Provincia</label> <select -->
<!-- 						class="form-control" id="exampleFormControlSelect1"> -->
<!-- 						<option>Madrid</option> -->
<!-- 						<option>Barcelona</option> -->
<!-- 						<option>Toledo</option> -->
<!-- 						<option>Valencia</option> -->
<!-- 						<option>Córdoba</option> -->
<!-- 					</select> -->
<!-- 				</div> -->
<!-- 				<div class="form-group col-xs-12 col-sm-4"> -->
<!-- 					<label for="exampleFormControlSelect1">País</label> <select -->
<!-- 						class="form-control" id="exampleFormControlSelect1"> -->
<!-- 						<option>España</option> -->
<!-- 						<option>Francia</option> -->
<!-- 						<option>Portugal</option> -->
<!-- 						<option>Italia</option> -->
<!-- 						<option>Reino unido</option> -->
<!-- 					</select> -->
<!-- 				</div>	 -->
<!-- 			</div> -->
<%-- 		</c:forEach> --%>

</body>
</html>