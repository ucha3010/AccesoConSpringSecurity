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
	<title><fmt:message key="label.Bills" /></title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<script type="text/javascript" src='<c:url value="/resources/js/jquery.js" />'></script>
	<script type="text/javascript" src='<c:url value="/resources/js/utiles.js" />'></script>
	<link href="<c:url value='/resources/bootstrap-4.3.1-dist/css/bootstrap.min.css'/>" rel="stylesheet" type="text/css" />
	<link href="<c:url value='/resources/css/menu.css'/>" rel="stylesheet" type="text/css" />
	<link href="<c:url value='/resources/css/tablas.css'/>" rel="stylesheet" type="text/css" />
	<script type="text/javascript">
		function confirmDelete(idFac){
			if(confirm("<fmt:message key='Delete.message' />")){
				var url = "<c:url value='/factura/delete/"+idFac+"' />";
				location.href=url;
				return true;
			}
			return false;
		}
		function filtrar() {		
			const resultado = document.querySelector('#resultado');
			const texto = normalizado(formulario.value.toLowerCase());
			resultado.innerHTML = '';
			if(texto === ''){
				$(".collapse").collapse('hide');
			} else {
				<c:forEach items="${facturas}" var="fac" varStatus="status">
					var creado = normalizado('${fac.creadoPor}');
					var estado = normalizado('${fac.estado.nombre}');
					var importeNum = ${fac.importeTotal};
					var importe = importeNum.toString();
					if(creado.toLowerCase().indexOf(texto) !== -1 || estado.indexOf(texto) !== -1 || importe.toLowerCase().indexOf(texto) !== -1){
						resultado.innerHTML += "<a href=\"<c:url value='/factura/filtered/${fac.idFac}' />\">${fac.idFac} ** <fmt:formatDate value='${fac.fechaCompra}' pattern='dd/MM/yyyy'/> ** <fmt:formatNumber type='currency' value='${fac.importeTotal}'/> ** ${fac.creadoPor}</a>";
					}
				</c:forEach>
				$(".collapse").collapse('show');
			}
		}
		function actualizaEstado(idFac) {
			var valSelected = document.getElementById("estadoId" + idFac);
			var url = "<c:url value='/factura/status/"+idFac+"/" + valSelected.value + "' />";
			location.href=url;
			
		}
	</script>
</head>
<body>
	<c:import url="/WEB-INF/views/menu.jsp" />
	<div class="d-flex">
		<div class="p-2">
		</div>
		<div class="p-6">
			<h2><fmt:message key="label.Bill.id" />: ${factura.idFac}</h2>
		</div>
		<div class="p-2">
		</div>
		<div class="ml-auto p-2">
		</div>		
	</div>
	<div class="divTabla">
		<table id="tablaOrdenar" class="table table-striped">
			<thead>
				<tr>
					<c:set var="count" value="0" scope="page" />
					<th onclick="sortTable(${count})" class="text-center"><fmt:message key="label.state" /></th>
					<c:set var="count" value="${count + 1}" scope="page"/>
					<th onclick="sortTable(${count})" class="text-center"><fmt:message key="label.Creation.date" /></th>
					<c:set var="count" value="${count + 1}" scope="page"/>
					<th onclick="sortTable(${count})" class="text-center"><fmt:message key="label.Creator" /></th>
					<th class="text-center"><fmt:message key="label.Observations" /></th>
					<th class="width-50"><fmt:message key="label.Extras" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${facturaEstados}" var="facturaEstado">
				    <tr title='<fmt:message key="label.Delivery.date" />: <fmt:formatDate value="${factura.fechaEntrega}" pattern="dd/MM/yyyy"/>&#xA;<fmt:message key="label.Delivery.address" />: <c:out value="${factura.direccionEntrega}" />&#xA;<fmt:message key="label.Observations" />: <c:out value="${factura.observaciones}" />&#xA;<fmt:message key="label.Payment.method" />: <c:out value="${factura.formaPago.nombre}" />&#xA;<fmt:message key="label.Creator" />: <c:out value="${factura.creadoPor}" />'>
						<td class="text-center"><fmt:message key="${facturaEstado.estado.nombre}" /></td>
						<td class="text-center"><fmt:formatDate value="${facturaEstado.fecha}" pattern="dd/MM/yyyy"/></td>
						<td class="text-center"><c:out value="${facturaEstado.creadoPor}" /></td>
						<td class="text-center"><c:out value="${facturaEstado.observaciones}" /></td>
						<td><button type="button" class="btn btn-primary margin-left-5porciento" onclick='location.href="<c:url value="/usuario"/>"'><fmt:message key="label.Update" /></button></td>
<!-- 						TODO DAMIAN mostrar caja de texto en facturaEstado.observaciones (que al seleccionarlo se agrande) y hacer destino botón update -->
				    </tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<script type="text/javascript" src="<c:url value='/resources/js/tabla_ordenar.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/bootstrap-4.3.1-dist/js/bootstrap.min.js'/>"></script>
</body>
</html>