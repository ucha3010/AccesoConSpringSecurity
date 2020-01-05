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
						resultado.innerHTML += "<a href=\"<c:url value='/factura/filtered/${fac.idFac}' />\">${fac.idFac} ${fac.fechaCompra} ${fac.importeTotal} ${fac.creadoPor}</a>";
					}
				</c:forEach>
				$(".collapse").collapse('show');
			}
		}
	</script>
</head>
<body>
	<c:import url="/WEB-INF/views/menu.jsp" />
	<div class="d-flex">
		<div class="p-2">
			<input type="text" id="formulario" class="form-control">
			<script>
				const formulario = document.querySelector('#formulario');
				formulario.addEventListener('keyup', filtrar);
			</script>
		</div>
		<div class="p-2">
			<div class="dropdown collapse">
				<div class="dropdown-content" id="resultado">
				</div>
			</div>
		</div>
		<div class="p-2">
			<c:if test="${not empty factura_eliminado}">
				<span style="color: green;">
					<fmt:message key="Bill.deleted" />
				</span>
			</c:if>
		</div>
		<div class="ml-auto p-2">
		</div>		
	</div>
	<div class="divTabla">
		<table id="tablaOrdenar" class="table table-striped">
			<thead>
				<tr>
					<c:set var="count" value="0" scope="page" />
					<sec:authorize access="hasAnyRole('ROL_ROOT')">
						<th></th>
						<c:set var="count" value="${count + 1}" scope="page"/>
					</sec:authorize>
					<th onclick="sortTable(${count})"><fmt:message key="label.Bill.id" /></th>
					<c:set var="count" value="${count + 1}" scope="page"/>
					<th onclick="sortTable(${count})"><fmt:message key="label.Purchase.Sale" /></th>
					<c:set var="count" value="${count + 1}" scope="page"/>
					<th onclick="sortTable(${count})"><fmt:message key="label.date.purchase" /></th>
					<c:set var="count" value="${count + 1}" scope="page"/>
					<th onclick="sortTable(${count})"><fmt:message key="label.Total.dicount" /></th>
					<c:set var="count" value="${count + 1}" scope="page"/>
					<th onclick="sortTable(${count})"><fmt:message key="label.Total.tax" /></th>
					<c:set var="count" value="${count + 1}" scope="page"/>
					<th onclick="sortTable(${count})"><fmt:message key="label.Total.amount" /></th>
					<c:set var="count" value="${count + 1}" scope="page"/>
					<th onclick="sortTable(${count})"><fmt:message key="label.state" /></th>
					<th class="width-50"><fmt:message key="label.Extras" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${facturas}" var="factura">
				    <tr title='<fmt:message key="label.Delivery.date" />: <fmt:formatDate value="${factura.fechaEntrega}" pattern="dd/MM/yyyy"/>&#xA;<fmt:message key="label.Delivery.address" />: <c:out value="${factura.direccionEntrega}" />&#xA;<fmt:message key="label.Observations" />: <c:out value="${factura.observaciones}" />&#xA;<fmt:message key="label.Payment.method" />: <c:out value="${factura.formaPago.nombre}" />&#xA;<fmt:message key="label.Creator" />: <c:out value="${factura.creadoPor}" />'>
						<sec:authorize access="hasAnyRole('ROL_ROOT')">
							<td class="sin_padding">
								<button type="button" class="btn btn-default" title="<fmt:message key='Delete' />" onclick="return confirmDelete(${factura.idFac})">
								  <img src='<c:url value="/resources/imgs/borrar.png"/>' class="tamanio_imagen">
								</button>
							</td>
						</sec:authorize>
						<td><c:out value="${factura.idFac}" /></td>
						<c:if test="${factura.compra}">
							<td><fmt:message key="label.Purchase" /></td>
						</c:if>
						<c:if test="${not factura.compra}">
							<td><fmt:message key="label.Sale" /></td>
						</c:if>
						<td><fmt:formatDate value="${factura.fechaCompra}" pattern="dd/MM/yyyy"/></td>
						<td><c:out value="${factura.descuentoTotal}" /></td>	
						<td><c:out value="${factura.ivaTotal}" /></td>	
						<td><c:out value="${factura.importeTotal}" /></td>
						<td><c:out value="${factura.estado.nombre}" /></td>
						<td class="sin_padding">
							<a title="<fmt:message key="Products" />" href='<c:url value='/productoFactura/factura/${factura.idFac}' />'>
								<img src='<c:url value="/resources/imgs/factura.png"/>' class="margin-left-5porciento width-35">
							</a>
						</td>
				    </tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<script type="text/javascript" src="<c:url value='/resources/js/tabla_ordenar.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/bootstrap-4.3.1-dist/js/bootstrap.min.js'/>"></script>
</body>
</html>