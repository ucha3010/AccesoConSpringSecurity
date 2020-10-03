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
<body class="${prefUsr.tema}fondopantalla">
	<div class="container">
		<c:import url="/WEB-INF/views/menu.jsp" />
		<fmt:message key="language.name" var="nameColSelect"/>
		<div class="well well-sm text-center h2 ${prefUsr.tema}titulo"><c:out value="${producto[nameColSelect]}" /></div>
		<div class="row">
			<div class="hidden-xs col-sm-12">
				<div class="divTablaSinScroll">
					<table class="table table-striped">
						<thead>
							<tr>					
								<th class="text-center"><fmt:message key="label.Bill.id" /></th>
								<th class="text-center"><fmt:message key="label.Purchase.Sale" /></th>
								<th class="text-center"><fmt:message key="label.date.purchase" /></th>
								<th colspan="2" class="text-center min-width-160"><fmt:message key="label.Total.amount" /></th>
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
					</table>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-xs-12 hidden-sm hidden-md hidden-lg hidden-xl">
				<table class="table table-striped">
					<tbody>
						<c:forEach items="${productoFacturas}" var="productoFactura">
						    <tr href="#ventana${productoFactura.factura.idFac}" class="thumbnail" data-toggle="modal">
								<td class="text-center"><c:out value="${productoFactura.factura.idFac}" /></td>
								<c:if test="${productoFactura.factura.compra}">
									<td class="text-center"><fmt:message key="label.Purchase" /></td>
								</c:if>
								<c:if test="${not productoFactura.factura.compra}">
									<td class="text-center"><fmt:message key="label.Sale" /></td>
								</c:if>
								<td class="text-center"><fmt:formatDate value="${productoFactura.factura.fechaCompra}" pattern="dd/MM/yyyy"/></td>
								<td class="text-right"><fmt:formatNumber type="currency" value="${productoFactura.factura.importeTotal}" /></td>
						    </tr>
						    
							<div class="modal fade" id="ventana${productoFactura.factura.idFac}">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
											<h4 class="modal-title justify-content-center"><fmt:message key="label.Bill.id" />: <c:out value="${productoFactura.factura.idFac}" /></h4>
										</div>
										<div class="modal-body">
							            	<div class="col-xs-2">	
												<a title="<fmt:message key="Products" />" href='<c:url value='/productoFactura/factura/${productoFactura.factura.idFac}' />'>
													<img src='<c:url value="/resources/imgs/factura.png"/>' class="margin-left-5porciento width-35">
												</a>
											</div>	
								            <div class="col-xs-10">
											</div>
											<div class="height50"></div>
											<p><fmt:message key="label.Purchase.Sale" />: 
												<c:if test="${productoFactura.factura.compra}">
													<fmt:message key="label.Purchase" />
												</c:if>
												<c:if test="${not productoFactura.factura.compra}">
													<fmt:message key="label.Sale" />
												</c:if>
											</p>
											<p><fmt:message key="label.date.purchase" />: <fmt:formatDate value="${productoFactura.factura.fechaCompra}" pattern="dd/MM/yyyy"/></p>
											<p><fmt:message key="label.Total.amount" />: <fmt:formatNumber type="currency" value="${productoFactura.factura.importeTotal}" /></p>
											<p><fmt:message key="label.units" />: <c:out value="${productoFactura.cantidad}" /></p>
											<p><fmt:message key="label.Observations" />: <c:out value="${productoFactura.observaciones}" /></p>
										</div>
									</div>
								</div>
							</div>
					    </c:forEach>
				    </tbody>
				</table>
			</div>
		</div>
	
		<footer>
			<c:import url="/WEB-INF/views/importFooter.jsp" />
		</footer>
	</div>
</body>
</html>