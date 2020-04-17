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
	<title><fmt:message key="label.Bills" /></title>
	<c:import url="/WEB-INF/views/importHead.jsp" />
</head>
<body>
	<c:import url="/WEB-INF/views/menu.jsp" />
	<fmt:message key="language.name" var="nameColSelect"/>
	<div>
		<h2><fmt:message key="label.Product.description" />: <c:out value="${producto[nameColSelect]}" /></h2>
	</div>
	<div class="divTabla">
		<table id="tablaOrdenar" class="table table-striped">
			<thead>
				<tr>
					<c:set var="count" value="0" scope="page" />					
					<th onclick="sortTable(${count})" class="text-center"><fmt:message key="label.Bill.id" /></th>
					<c:set var="count" value="${count + 1}" scope="page"/>
					<th onclick="sortTable(${count})" class="text-center"><fmt:message key="label.Purchase.Sale" /></th>
					<c:set var="count" value="${count + 1}" scope="page"/>
					<th onclick="sortTable(${count})" class="text-center"><fmt:message key="label.date.purchase" /></th>
					<c:set var="count" value="${count + 1}" scope="page"/>
					<th colspan="2" class="text-center"><fmt:message key="label.Total.amount" /></th>
					<th class="width-50"><fmt:message key="label.Extras" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${productoFacturas}" var="productoFactura">
					<tr title='<fmt:message key="label.units" />: <c:out value="${productoFactura.cantidad}" />&#xA;<fmt:message key="label.Observations" />: <c:out value="${productoFactura.observaciones}" />'>
						<td class="text-center"><c:out value="${productoFactura.factura.idFac}" /></td>
						<c:if test="${productoFactura.factura.compra}">
							<td class="text-center"><fmt:message key="label.Purchase" /></td>
						</c:if>
						<c:if test="${not productoFactura.factura.compra}">
							<td class="text-center"><fmt:message key="label.Sale" /></td>
						</c:if>
						<td class="text-center"><fmt:formatDate value="${productoFactura.factura.fechaCompra}" pattern="dd/MM/yyyy"/></td>
						<td class="text-right"><fmt:formatNumber type="currency" value="${productoFactura.factura.importeTotal}" /></td>
						<td class="width-35"></td>
						<td class="sin_padding">
							<a title="<fmt:message key="Products" />" href='<c:url value='/productoFactura/factura/${productoFactura.factura.idFac}' />'>
								<img src='<c:url value="/resources/imgs/factura.png"/>' class="margin-left-5porciento width-35">
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