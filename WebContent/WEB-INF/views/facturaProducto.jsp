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
						<th class="text-center"><fmt:message key="label.Unit.price.without.vat" /></th>
						<th class="text-center"><fmt:message key="label.Units" /></th>
						<th class="text-center min-width-160"><fmt:message key="label.quantity.price.without.vat" /></th>
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
							<td class="text-center"><fmt:formatNumber type="currency" value="${productoFactura.precioUnitFinal}"/></td>
							
							<c:if test="${factura.cuota eq null || factura.cuota.cantidadCuotas == 0}">
								<td class="text-right"><fmt:formatNumber type="currency" value="${productoFactura.precioFinal}" /></td>
							</c:if>
							<c:if test="${factura.cuota ne null && factura.cuota.cantidadCuotas > 0}">
								<td class="text-right">
									<fmt:formatNumber type="currency" value="${productoFactura.precioFinal}" /> 
									<span onclick="mostrarAviso('aviso${productoFactura.idPro}');" class="glyphicon glyphicon-info-sign"></span>
									<c:if test="${factura.cuota.cantidadCuotas == 1}">
										<div id="aviso${productoFactura.idPro}" style="display: none;">
											<fmt:message key="label.message.final.amount.1" /><br>
											<fmt:message key="label.message.final.amount.3" />
										</div>
									</c:if>
									<c:if test="${factura.cuota.cantidadCuotas > 1}">
										<div id="aviso${productoFactura.idPro}" style="display: none;">
											<fmt:message key="label.message.final.amount.1" /><br>
											<fmt:message key="label.message.final.amount.2" /> <a href="<c:url value='/productoFactura/factura/${factura.idFac}' />"><c:out value="${factura.idFac}"></c:out></a>
										</div>
									</c:if>
								</td>
							</c:if>
							
							
							<td class="width-35"></td>
						</tr>
					</c:forEach>
					<tr><td colspan="7" class="text-center">***************************************************</td></tr>
					<c:if test="${factura.cuota eq null || factura.cuota.cantidadCuotas == 0}">
		<!-- 				DESCUENTO -->
						<tr>
							<td colspan="5"></td>
							<td><strong><fmt:message key="label.Total.dicount" /></strong></td>
							<td class="text-right"><strong>- <fmt:formatNumber type="currency" value="${factura.descuentoImporteTotal}" /></strong></td>
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
					</c:if>
					<c:if test="${factura.cuota ne null && factura.cuota.cantidadCuotas > 0}">
		<!-- 				CUOTA SIN IVA -->
						<tr>
							<td colspan="2"></td>
							<td colspan="6"><strong><fmt:message key="label.Installment.number" /> <c:out value="${factura.cuota.cantidadCuotas}" /> <fmt:message key="label.of" /> <c:out value="${factura.cuota.cantidadCuotas}" /></strong></td>
						</tr>
		<!-- 				CUOTA SIN IVA -->
						<tr>
							<td colspan="6"><strong><fmt:message key="label.Installment.no.vat" /></strong></td>
							<td class="text-right"><strong></strong></td>
							<td class="width-35"></td>
						</tr>
		<!-- 				IVA -->
						<tr>
							<td colspan="4"><strong><fmt:message key="label.Installment.vat" /></strong></td>
							<td class="text-center"><strong><fmt:formatNumber type="number" value="${factura.ivaTotal}" minFractionDigits="2" />%</strong></td>
							<td></td>
							<td class="text-right"><strong>  </strong></td>
							<td class="width-35"></td>
						</tr>
		<!-- 				INTERES -->
						<tr>
							<td colspan="6"><strong><fmt:message key="label.Loan" /></strong></td>
							<td class="text-right"><strong><fmt:formatNumber type="currency" value="${factura.cuota.interesImp}" /></strong></td>
							<td class="width-35"></td>
						</tr>
		<!-- 				IMPORTE FINAL -->
						<tr>
							<td colspan="6"><strong><fmt:message key="label.Total.amount" /></strong></td>
							<td class="text-right"><strong><fmt:formatNumber type="currency" value="${factura.cuota.totalCompletoAPagar}" /></strong></td>
							<td class="width-35"></td>
						</tr>					
					</c:if>
				</tbody>
			</table>
		</div>
	</div>
	
	<footer>
		<c:import url="/WEB-INF/views/importFooter.jsp" />
	</footer>
</body>
</html>