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
	<title><fmt:message key="label.Bill.detail" /></title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<script type="text/javascript" src='<c:url value="/resources/js/jquery.js" />'></script>
	<link href="<c:url value='/resources/bootstrap-4.3.1-dist/css/bootstrap.min.css'/>" rel="stylesheet" type="text/css" />
	<link href="<c:url value='/resources/css/menu.css'/>" rel="stylesheet" type="text/css" />
	<link href="<c:url value='/resources/css/tablas.css'/>" rel="stylesheet" type="text/css" />
</head>
<body>
	<c:import url="/WEB-INF/views/menu.jsp" />
	<fmt:message key="language.name" var="nameColSelect"/>
	<div>
		<h2><fmt:message key="label.Bill.id" />: <c:out value="${factura.idFac}" /></h2>
	</div>
	<div class="divTabla">
		<table id="tablaOrdenar" class="table table-striped">
			<thead>
				<tr>
					<th class="text-center"><fmt:message key="label.Product.description" /></th>
					<th class="text-center"><fmt:message key="label.Unit.price" /></th>
					<th class="text-center"><fmt:message key="label.Quantity" /></th>
					<th class="text-center"><fmt:message key="label.Discount" /></th>
					<th class="text-center"><fmt:message key="label.Vat" /></th>
					<th class="text-center"><fmt:message key="label.Unit.price.with.vat" /></th>
					<th colspan="2" class="text-center"><fmt:message key="label.Final.amount" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${productoFacturas}" var="productoFactura">
					<tr title='<c:out value="${productoFactura.observaciones}" />'>
						<td><c:out value="${productoFactura.producto[nameColSelect]}" /></td>
						<td class="text-center"><fmt:formatNumber type="currency" value="${productoFactura.precioUnitSinIva}"/></td>
						<td class="text-center"><c:out value="${productoFactura.cantidad}" /></td>
						<td class="text-center"><fmt:formatNumber type="number" value="${productoFactura.porcentajeDescuento}" minFractionDigits="2" />%</td>
						<td class="text-center"><fmt:formatNumber type="number" value="${productoFactura.ivaProducto}" minFractionDigits="2" />%</td>
						<td class="text-center"><fmt:formatNumber type="currency" value="${productoFactura.precioUnitConIva}"/></td>
						<td class="text-right"><fmt:formatNumber type="currency" value="${productoFactura.precioFinal}" /></td>
						<td class="width-35"></td>
					</tr>
				</c:forEach>
				<tr><td colspan="7" class="text-center">***************************************************</td></tr>
				<c:if test="${factura.numeroCuota == 0}">
	<!-- 				DESCUENTO -->
					<tr>
						<td colspan="3"><strong><fmt:message key="label.Total.dicount" /></strong></td>
						<c:if test="${factura.descuentoTotal > 0}">
							<td class="text-center"><strong><fmt:formatNumber type="number" value="${factura.descuentoTotal}" minFractionDigits="2" />%</strong></td>
						</c:if>
						<c:if test="${factura.descuentoTotal == 0}">
							<td></td>
						</c:if>
						<td colspan="2"></td>
						<td class="text-right"><strong>- <fmt:formatNumber type="currency" value="${factura.descuentoImporteTotal}" /></strong></td>
						<td class="width-35"></td>
					</tr>
	<!-- 				IVA -->
					<c:if test="${factura.ivaTotal > 0}">
						<tr>
							<td colspan="4"><strong><fmt:message key="label.Total.vat" /></strong></td>
							<td class="text-center"><strong><fmt:formatNumber type="number" value="${factura.ivaTotal}" minFractionDigits="2" />%</strong></td>
							<td></td>
							<td class="text-right"><strong><fmt:formatNumber type="currency" value="${factura.ivaImporteTotal}" /></strong></td>
							<td class="width-35"></td>
						</tr>
					</c:if>
					<c:if test="${factura.ivaTotal == 0}">
						<tr>
							<td colspan="4"><strong><fmt:message key="label.Total.vat" /></strong></td>
							<td class="text-center"><strong><fmt:formatNumber type="currency" value="${factura.ivaImporteTotal}" /></strong></td>
							<td colspan="2"></td>
							<td class="width-35"></td>
						</tr>
					</c:if>
	<!-- 				IMPORTE FINAL -->
					<tr>
						<td colspan="6"><strong><fmt:message key="label.Total.amount" /></strong></td>
						<td class="text-right"><strong><fmt:formatNumber type="currency" value="${factura.importeTotal}" /></strong></td>
						<td class="width-35"></td>
					</tr>
				</c:if>
				<c:if test="${factura.numeroCuota > 0}">
	<!-- 				CUOTA SIN IVA -->
					<tr>
						<td colspan="2"></td>
						<td colspan="6"><strong><fmt:message key="label.Installment.number" /> <c:out value="${factura.numeroCuota}" /></strong></td>
					</tr>
	<!-- 				CUOTA SIN IVA -->
					<tr>
						<td colspan="6"><strong><fmt:message key="label.Installment.no.vat" /></strong></td>
						<td class="text-right"><strong><fmt:formatNumber type="currency" value="${factura.cuotaSinIva}" /></strong></td>
						<td class="width-35"></td>
					</tr>
	<!-- 				IVA -->
					<tr>
						<td colspan="4"><strong><fmt:message key="label.Installment.vat" /></strong></td>
						<td class="text-center"><strong><fmt:formatNumber type="number" value="${factura.ivaTotal}" minFractionDigits="2" />%</strong></td>
						<td></td>
						<td class="text-right"><strong><fmt:formatNumber type="currency" value="${factura.cuotaConIva}" /></strong></td>
						<td class="width-35"></td>
					</tr>
	<!-- 				INTERES -->
					<tr>
						<td colspan="6"><strong><fmt:message key="label.Loan" /></strong></td>
						<td class="text-right"><strong><fmt:formatNumber type="currency" value="${factura.interesCuotaImporte}" /></strong></td>
						<td class="width-35"></td>
					</tr>
	<!-- 				IMPORTE FINAL -->
					<tr>
						<td colspan="6"><strong><fmt:message key="label.Total.amount" /></strong></td>
						<td class="text-right"><strong><fmt:formatNumber type="currency" value="${factura.importeCuotaTotal}" /></strong></td>
						<td class="width-35"></td>
					</tr>					
				</c:if>
			</tbody>
		</table>
	</div>
	<script type="text/javascript" src="<c:url value='/resources/js/tabla_ordenar.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/bootstrap-4.3.1-dist/js/bootstrap.min.js'/>"></script>
</body>
</html>