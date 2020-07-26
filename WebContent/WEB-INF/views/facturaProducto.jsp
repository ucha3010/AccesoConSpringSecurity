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
	<c:import url="/WEB-INF/views/importHead.jsp" />
	
	<script>
		function mostrarAviso(nombrediv) {
			var ventanamostrar = document.getElementById(nombrediv);
			if(ventanamostrar.style.display == "block"){
				ventanamostrar.style.display = "none";				
			} else {
				ventanamostrar.style.display = "block";
			}
		}
	</script>
	
</head>
<body>		
	<div class="container-fluid">
		<c:import url="/WEB-INF/views/menu.jsp" />
		<fmt:message key="language.name" var="nameColSelect"/>
		<div class="well well-sm text-center h2">
			<fmt:message key="label.Bill.id" />: <c:out value="${factura.idFac}" />
			<br>
			<fmt:message key="label.Date" />: <fmt:formatDate value="${factura.fechaCompra}" pattern="dd/MM/yyyy"/>
		</div>
		<div class="divTablaSinScroll">
			<table class="table table-striped">
				<thead>
					<tr>
						<th class="text-center min-width-160"><fmt:message key="label.Product.description" /></th>
						<th class="text-center min-width-160"><fmt:message key="label.Unit.price" /></th>
						<th class="text-center"><fmt:message key="label.Discount" /></th>
						<th class="text-center"><fmt:message key="label.Unit.tax.base" /></th>
						<th class="text-center"><fmt:message key="label.Units" /></th>
						<th class="text-center min-width-160"><fmt:message key="label.Vat" /></th>
						<th colspan="2" class="text-center min-width-160"><fmt:message key="label.Final.amount" /></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${productoFacturas}" var="productoFactura">
						<tr title='<c:out value="${productoFactura.observaciones}" />'>
							<td><a href="<c:url value='/productoFactura/producto/${productoFactura.idPro}' />"><c:out value="${productoFactura[nameColSelect]}" /></a></td>
							<td class="text-center"><fmt:formatNumber type="currency" value="${productoFactura.precioUnit}"/></td>
							<td class="text-center"><fmt:formatNumber type="number" value="${productoFactura.descuentoPor}" minFractionDigits="2" />% (<fmt:formatNumber type="currency" value="${productoFactura.descuentoImp}"/>)</td>
							<td class="text-center"><fmt:formatNumber type="currency" value="${productoFactura.precioUnitConDescuento}"/></td>
							<td class="text-center"><c:out value="${productoFactura.cantidad}" /></td>
							<td class="text-center">(<fmt:formatNumber type="number" value="${productoFactura.ivaProductoPor}" minFractionDigits="1" />%) <fmt:formatNumber type="currency" value="${productoFactura.ivaProductosCantidadImp}"/></td>
							<td class="text-right"><fmt:formatNumber type="currency" value="${productoFactura.precioFinal}" /></td>
							<td class="width-35"></td>
						</tr>
					</c:forEach>
					<tr><td colspan="8" class="text-center">***************************************************</td></tr>
		<!-- 				DESCUENTO -->
						<tr>
							<td colspan="5"></td>
							<td><strong><fmt:message key="label.Total.dicount" /></strong></td>
							<td class="text-right"><strong><fmt:formatNumber type="currency" value="${factura.descuentoImporteTotal}" /></strong></td>
							<td class="width-35"></td>
						</tr>
		<!-- 				IVA -->
						<tr>
							<td colspan="5"></td>
							<td><strong><fmt:message key="label.Total.vat" /></strong></td>
							<td class="text-right"><strong><fmt:formatNumber type="currency" value="${factura.ivaImporteTotal}" /></strong></td>
							<td class="width-35"></td>
						</tr>
		<!-- 				IMPORTE FINAL -->
						<tr>
							<td colspan="5"></td>
							<td><strong><fmt:message key="label.Total.amount" /></strong></td>
							<td class="text-right"><strong><fmt:formatNumber type="currency" value="${factura.importeTotal}" /></strong></td>
							<td class="width-35"></td>
						</tr>
				</tbody>
			</table>
			<c:if test="${factura.cuota ne null && factura.cuota.cantidadCuotas > 0}">
				<hr>
				<h1><fmt:message key="label.Installments" /></h1>
				<h4><fmt:message key="label.Opening.commission" />: <fmt:formatNumber type="number" value="${factura.cuota.comisionAperturaPor}" minFractionDigits="0" />% (<fmt:formatNumber type="currency" value="${factura.cuota.comisionAperturaImp}" />), <fmt:message key="label.Loan" />: <fmt:formatNumber type="number" value="${factura.cuota.interesPor}" minFractionDigits="0" />%</h4>
				<table class="table-striped">
					<thead>
						<tr>
							<th class="text-center"><fmt:message key="label.Installment.number" /></th>
							<th class="text-center"><fmt:message key="label.Date" /></th>
							<th class="min-width-20"></th>
							<th class="text-center min-width-100"><fmt:message key="label.Amount.not.loan" /></th>
							<th class="text-center min-width-100"><fmt:message key="label.Loan" /></th>
							<th class="text-center min-width-100"><fmt:message key="label.Installment" /></th>
							<th class="text-center min-width-100"><fmt:message key="label.Outstanding.capital.before" /></th>
							<th class="min-width-20"></th>
							<th class="text-center min-width-100"><fmt:message key="label.Outstanding.capital.after" /></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${factura.cuota.cuotaDetalles}" var="cuotaDetalle">
							<tr>
								<td class="text-center"><c:out value="${cuotaDetalle.numeroCuota}" /></td>
								<td class="text-center"><fmt:formatDate value="${cuotaDetalle.fecha}" pattern="dd/MM/yyyy"/></td>
								<td></td>
								<td class="text-center"><fmt:formatNumber type="currency" value="${cuotaDetalle.importeSinInteres}" /></td>
								<td class="text-center"><fmt:formatNumber type="currency" value="${cuotaDetalle.importeInteres}" /></td>
								<td class="text-center">
									<fmt:formatNumber type="currency" value="${cuotaDetalle.importeCuota}" />
									<c:if test="${cuotaDetalle.numeroCuota == 1 && factura.cuota.comisionAperturaImp > 0}">
										<span title='<fmt:message key="label.Opening.commission" />: <fmt:formatNumber type="currency" value="${factura.cuota.comisionAperturaImp}" />'class="glyphicon glyphicon-info-sign"></span>
									</c:if>
								</td>
								<td class="text-center"><fmt:formatNumber type="currency" value="${cuotaDetalle.capitalPendienteAntes}" /></td>
								<td></td>
								<td class="text-center"><fmt:formatNumber type="currency" value="${cuotaDetalle.capitalPendienteDespues}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
		</div>
	</div>
	
	<footer>
		<c:import url="/WEB-INF/views/importFooter.jsp" />
	</footer>
</body>
</html>