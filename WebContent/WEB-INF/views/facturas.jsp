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
		function ordenaTabla(numCol){
			var columnas = ['idFac','compra','fechaCompra','descuentoTotal','ivaTotal','importeFront'];
			var url = "<c:url value='/factura/all/"+columnas[numCol]+"' />";
			location.href=url;			
		}
		
		function imprimirId(id){
			var elemento = document.getElementById(id);
			var ventana = window.open('', 'PRINT', 'height=400,width=600');
			ventana.document.write('<html><head><title>' + document.title + '</title>');
			ventana.document.write('<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet" type="text/css" />');
			ventana.document.write('<link rel="stylesheet" href="/resources/css/tablas.css" rel="stylesheet" type="text/css" />');
			ventana.document.write('</head><body >');
			ventana.document.write(elemento.innerHTML);
			ventana.document.write('</body></html>');
			ventana.document.close();
			ventana.focus();
			ventana.onload = function() {
			    ventana.print();
				ventana.close();
			};
			return true;
		}
		
	</script>
</head>
<body>
	<c:import url="/WEB-INF/views/menu.jsp" />
	<fmt:message key="language.name" var="nameColSelect"/>
	<div class="row">
		<div class="hidden-xs col-sm-3">
		</div>
		<div class="col-xs-12 col-sm-3">
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
		<div class="col-xs-12 col-sm-3">
			<c:if test="${not empty vencen}">
				<a href="#ventanaVencen" class="btn btn-danger btn-lg" data-toggle="modal"><fmt:message key='label.Bill.next.days' /></a>
			</c:if>
		</div>
		<div class="hidden-xs col-sm-3">
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
					<th onclick="ordenaTabla(${count})" class="text-center"><fmt:message key="label.Bill.id" /></th>
					<c:set var="count" value="${count + 1}" scope="page"/>
					<th onclick="ordenaTabla(${count})" class="text-center"><fmt:message key="label.Purchase.Sale" /></th>
					<c:set var="count" value="${count + 1}" scope="page"/>
					<th onclick="ordenaTabla(${count})" class="text-center"><fmt:message key="label.Date" /></th>
					<c:set var="count" value="${count + 1}" scope="page"/>
					<th onclick="ordenaTabla(${count})" class="text-center"><fmt:message key="label.Total.dicount" /></th>
					<c:set var="count" value="${count + 1}" scope="page"/>
					<th onclick="ordenaTabla(${count})" class="text-center"><fmt:message key="label.Total.vat" /></th>
					<c:set var="count" value="${count + 1}" scope="page"/>
					<th onclick="ordenaTabla(${count})" colspan="2" class="text-center min-width-160"><fmt:message key="label.Total.amount" /></th>
					<th class="text-center"><fmt:message key="label.state" /></th>
					<th class="extraAdmin-th"><fmt:message key="label.Extras" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${facturas}" var="factura">
				    <tr title='<fmt:message key="label.Delivery.date" />: <fmt:formatDate value="${factura.fechaEntrega}" pattern="dd/MM/yyyy"/>&#xA;<fmt:message key="label.Delivery.address" />: <c:out value="${factura.direccionEntrega}" />&#xA;<fmt:message key="label.Observations" />: <c:out value="${factura.observaciones}" />&#xA;<fmt:message key="label.Payment.method" />: <c:out value="${factura.formaPago[nameColSelect]}" />&#xA;<fmt:message key="label.Creator" />: <c:out value="${factura.creadoPor}" /><c:if test="${factura.numeroCuota > 0}">&#xA;<fmt:message key="label.Installment.number" />: <c:out value="${factura.numeroCuota}" /></c:if>'>
						<sec:authorize access="hasAnyRole('ROL_ROOT')">
							<td class="extraAdmin-td">
								<a title="<fmt:message key='Delete' />" onclick="return confirmDelete(${factura.idFac})">
									<img src='<c:url value="/resources/imgs/borrar.png"/>' class="tamanio_imagen">
								</a>
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
						<td class="extraAdmin-td">
							<a title="<fmt:message key="Products" />" href='<c:url value='/productoFactura/factura/${factura.idFac}' />'>
								<img src='<c:url value="/resources/imgs/factura.png"/>' class="tamanio_imagen">
							</a>
							<a title="<fmt:message key="label.State.historical" />" href='<c:url value='/facturaEstado/factura/${factura.idFac}' />'>
								<img src='<c:url value="/resources/imgs/states.png"/>' class="tamanio_imagen">
							</a>
						</td>
				    </tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div class="modal fade" id="ventanaVencen">
		<div class="modal-dialog maxwidth-maxcontent">
			<div class="modal-content">
				
				<!-- Header de la ventana -->
				<div class="modal-header bg-info">
					<h4 class="modal-title"><fmt:message key='label.Bill.next.days' /></h4>
					<button style="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				</div>
				
				<!-- Contenido de la ventana -->
				<div class="modal-body">
					<div class="divTablaSinScroll bg-light">
						<table class="table table-striped">
							<thead>
								<tr>
									<th class="text-center"><fmt:message key="label.Bill.id" /></th>
									<th class="text-center"><fmt:message key="label.Purchase.Sale" /></th>
									<th class="text-center"><fmt:message key="label.Date" /></th>
									<th class="text-center"><fmt:message key="label.Total.dicount" /></th>
									<th class="text-center"><fmt:message key="label.Total.vat" /></th>
									<th colspan="2" class="text-center"><fmt:message key="label.Total.amount" /></th>
									<th class="text-center"><fmt:message key="label.state" /></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${vencen}" var="vence">
									<td class="text-center"><c:out value="${vence.idFac}" /></td>
									<c:if test="${vence.compra}">
										<td class="text-center"><fmt:message key="label.Purchase" /></td>
									</c:if>
									<c:if test="${not vence.compra}">
										<td class="text-center"><fmt:message key="label.Sale" /></td>
									</c:if>
									<td class="text-center"><fmt:formatDate value="${vence.fechaCompra}" pattern="dd/MM/yyyy"/></td>
									<td class="text-center"><fmt:formatNumber type="number" value="${vence.descuentoTotal}" minFractionDigits="2" />%</td>
									<td class="text-center"><fmt:formatNumber type="number" value="${vence.ivaTotal}" minFractionDigits="2" />%</td>
									<td class="text-right"><fmt:formatNumber type="currency" value="${vence.importeFront}" /></td>
									<td class="width-15"></td>
									<td>
										<c:forEach items="${estados}" var="est">
											<c:if test="${vence.estado.idEst == est.idEst}">
												<c:out value="${est[nameColSelect]}" />
											</c:if>
										</c:forEach>
									</td>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				
				<!-- Contenido de la ventana -->
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" onclick="imprimirId('ventanaVencen')"><fmt:message key="label.Print" /></button>
				</div>
				
			</div>
		</div>
	</div>
	
	<footer>
		<c:import url="/WEB-INF/views/importFooter.jsp" />
	</footer>
</body>
</html>