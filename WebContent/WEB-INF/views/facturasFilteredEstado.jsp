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
	<c:import url="/WEB-INF/views/importHead.jsp" />
	
	<script type="text/javascript" src='<c:url value="/resources/js/utiles.js" />'></script>
	<script type="text/javascript">
		function confirmDelete(idFac){
			if(confirm("<fmt:message key='Delete.message' />")){
				var url = "<c:url value='/factura/delete/"+idFac+"' />";
				location.href=url;
				return true;
			}
			return false;
		}
		function actualizaEstado(idFac) {
			var valSelected = document.getElementById("estadoId" + idFac);
			var url = "<c:url value='/factura/status/"+idFac+"/" + valSelected.value + "/null' />";
			location.href=url;
		}
		function ordenaTabla(idEst,numCol){
			var columnas = ['idFac','compra','fechaCompra','descuentoTotal','ivaTotal','importeFront'];
			var url = "<c:url value='/factura/filteredEstado/"+idEst+"/"+columnas[numCol]+"' />";
			location.href=url;			
		}
	</script>
</head>
<body>
	<c:import url="/WEB-INF/views/menu.jsp" />
	<fmt:message key="language.name" var="nameColSelect"/>
	<div class="d-flex">
		<div class="p-2">
		</div>
		<div class="p-2">
		</div>
		<div class="p-2">
			<c:if test="${not empty factura_eliminado}">
				<span style="color: green;">
					<fmt:message key="Bill.deleted" />
				</span>
			</c:if>
			<c:if test="${not empty factura_stock_negativo}">
				<span style="color: green;">
					<fmt:message key="Bill.not.deleted.negative.stock" />
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
					</sec:authorize>
					<th onclick="ordenaTabla(${idEst},${count})" class="text-center"><fmt:message key="label.Bill.id" /></th>
					<c:set var="count" value="${count + 1}" scope="page"/>
					<th onclick="ordenaTabla(${idEst},${count})" class="text-center"><fmt:message key="label.Purchase.Sale" /></th>
					<c:set var="count" value="${count + 1}" scope="page"/>
					<th onclick="ordenaTabla(${idEst},${count})" class="text-center"><fmt:message key="label.date.purchase" /></th>
					<c:set var="count" value="${count + 1}" scope="page"/>
					<th onclick="ordenaTabla(${idEst},${count})" class="text-center"><fmt:message key="label.Total.dicount" /></th>
					<c:set var="count" value="${count + 1}" scope="page"/>
					<th onclick="ordenaTabla(${idEst},${count})" class="text-center"><fmt:message key="label.Total.vat" /></th>
					<c:set var="count" value="${count + 1}" scope="page"/>
					<th onclick="ordenaTabla(${idEst},${count})" colspan="2" class="text-center"><fmt:message key="label.Total.amount" /></th>
					<th class="text-center"><fmt:message key="label.state" /></th>
					<th class="width-100"><fmt:message key="label.Extras" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${facturas}" var="factura">
				    <tr title='<fmt:message key="label.Delivery.date" />: <fmt:formatDate value="${factura.fechaEntrega}" pattern="dd/MM/yyyy"/>&#xA;<fmt:message key="label.Delivery.address" />: <c:out value="${factura.direccionEntrega}" />&#xA;<fmt:message key="label.Observations" />: <c:out value="${factura.observaciones}" />&#xA;<fmt:message key="label.Payment.method" />: <c:out value="${factura.formaPago[nameColSelect]}" />&#xA;<fmt:message key="label.Creator" />: <c:out value="${factura.creadoPor}" /><c:if test="${factura.numeroCuota > 0}">&#xA;<fmt:message key="label.Installment.number" />: <c:out value="${factura.numeroCuota}" /></c:if>'>
						<sec:authorize access="hasAnyRole('ROL_ROOT')">
							<td class="sin_padding">
								<button type="button" class="btn btn-default" title="<fmt:message key='Delete' />" onclick="return confirmDelete(${factura.idFac})">
								  <img src='<c:url value="/resources/imgs/borrar.png"/>' class="tamanio_imagen">
								</button>
							</td>
						</sec:authorize>
						<td class="text-center"><c:out value="${factura.idFac}" /></td>
						<c:if test="${factura.compra}">
							<td class="text-center"><fmt:message key="label.Purchase" /></td>
						</c:if>
						<c:if test="${not factura.compra}">
							<td class="text-center"><fmt:message key="label.Sale" /></td>
						</c:if>
						<td class="text-center"><fmt:formatDate value="${factura.fechaCompra}" pattern="dd/MM/yyyy"/></td>
						<td class="text-center"><fmt:formatNumber type="number" value="${factura.descuentoTotal}" minFractionDigits="2" />%</td>
						<td class="text-center"><fmt:formatNumber type="number" value="${factura.ivaTotal}" minFractionDigits="2" />%</td>
						<td class="text-right"><fmt:formatNumber type="currency" value="${factura.importeFront}" /></td>
						<td class="width-15"></td>
						<td>
							<fmt:message key="label.state.column.name" var="itemSelect"/>
							<select name="factura.estado.idEst" class="border-radius-dam" id="estadoId${factura.idFac}" onchange="actualizaEstado(${factura.idFac})">
								<c:forEach items="${estados}" var="est">
									<option value="${est.idEst}" <c:if test="${factura.estado.idEst == est.idEst}">selected</c:if>>
										<c:out value="${est[nameColSelect]}" />
									</option>
								</c:forEach>
							</select>
						</td>
						<td class="sin_padding">
							<a title="<fmt:message key="Products" />" href='<c:url value='/productoFactura/factura/${factura.idFac}' />'>
								<img src='<c:url value="/resources/imgs/factura.png"/>' class="margin-left-5porciento width-35">
							</a>
							<a title="<fmt:message key="label.State.historical" />" href='<c:url value='/facturaEstado/factura/${factura.idFac}' />'>
								<img src='<c:url value="/resources/imgs/states.png"/>' class="margin-left-5porciento width-35">
							</a>
						</td>
				    </tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<footer>
		<c:import url="/WEB-INF/views/importFooter.jsp" />
	</footer>
</body>
</html>