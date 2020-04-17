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
	<title><fmt:message key="Products" /></title>
	<c:import url="/WEB-INF/views/importHead.jsp" />
	
	<script type="text/javascript" src='<c:url value="/resources/js/utiles.js" />'></script>
	<fmt:message key="language.name" var="nameColSelect"/>
	<script type="text/javascript">
		function confirmDelete(idPro){
			if(confirm("<fmt:message key='Delete.message' />")){
				var url = "<c:url value='/producto/delete/"+idPro+"' />";
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
				<c:forEach items="${productos}" var="pro" varStatus="status">
					var marca = normalizado('${pro.marca}');
					var modelo = normalizado('${pro.modelo}');
					var precioNum = ${pro.precioVenta};
					var precio = precioNum.toString();
					var nombre = normalizado('${pro[nameColSelect]}');
					if(marca.toLowerCase().indexOf(texto) !== -1 || modelo.indexOf(texto) !== -1 || precio.toLowerCase().indexOf(texto) !== -1 || nombre.toLowerCase().indexOf(texto) !== -1){
						resultado.innerHTML += "<a href=\"<c:url value='/producto/filtered/${pro.idPro}' />\">${pro.marca} ${pro.modelo} ${pro.precioVenta} ${pro[nameColSelect]}</a>";
					}
				</c:forEach>
				$(".collapse").collapse('show');
			}
		}
		function ordenaTabla(numCol){
			var columnas = ['${nameColSelect}','estado','marca','modelo','precioVenta','unidades'];
			var url = "<c:url value='/producto/all/"+columnas[numCol]+"' />";
			location.href=url;			
		}
	</script>
</head>
<body>
	<c:import url="/WEB-INF/views/menu.jsp" />
	<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">
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
				<c:if test="${not empty producto_agregado}">
					<span style="color: green;">
						<fmt:message key="Product.added" />
					</span>
				</c:if>
				<c:if test="${not empty producto_eliminado}">
					<span style="color: green;">
						<fmt:message key="Product.deleted" />
					</span>
				</c:if>
			</div>
			<div class="ml-auto p-2">
			<button type="button" class="btn fondo-c0c0c0 float-right ml-1 border-color-dam" onclick='location.href="<c:url value='/producto/0'/>"'>
				<fmt:message key="Add.product" />
			</button>
		</div>		
	</div>
	</sec:authorize>
	<sec:authorize access="!hasAnyRole('ROL_ADMIN','ROL_ROOT')">
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
			<div class="ml-auto p-2">
			</div>		
		</div>
	</sec:authorize>
	<div class="divTabla">
		<table id="tablaOrdenar" class="table table-striped">
			<thead>
				<tr>
					<c:set var="count" value="0" scope="page" />
					<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">
						<th></th>
					</sec:authorize>
					<sec:authorize access="hasAnyRole('ROL_ROOT')">
						<th></th>
					</sec:authorize>
					<th onclick="ordenaTabla(${count})"><fmt:message key="label.Product.description" /></th>
					<c:set var="count" value="${count + 1}" scope="page"/>
					<th onclick="ordenaTabla(${count})" class="text-center"><fmt:message key="label.state" /></th>
					<c:set var="count" value="${count + 1}" scope="page"/>
					<th onclick="ordenaTabla(${count})"><fmt:message key="label.brand" /></th>
					<c:set var="count" value="${count + 1}" scope="page"/>
					<th onclick="ordenaTabla(${count})"><fmt:message key="label.model" /></th>
					<c:set var="count" value="${count + 1}" scope="page"/>
					<th colspan="2" class="text-center" onclick="ordenaTabla(${count})"><fmt:message key="label.salePrice" /></th>
					<c:set var="count" value="${count + 1}" scope="page"/>
					<th class="text-center" onclick="ordenaTabla(${count})"><fmt:message key="label.units" /></th>
					<th class="width-150"><fmt:message key="label.Extras" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${productos}" var="producto">
				    <tr title='<fmt:message key="label.purchase.price" />: <fmt:formatNumber type="currency" value="${producto.precioCompra}" />&#xA;<fmt:message key="label.Serial.number" />: <c:out value="${producto.serie}" />&#xA;<fmt:message key="label.location" />: <c:out value="${producto.ubicacion}" />&#xA;<fmt:message key="label.send" />: <c:out value="${producto.enviar}" />&#xA;<fmt:message key="label.salable" />: <c:out value="${producto.vendible}" />&#xA;<fmt:message key="label.warranty.months" />: <c:out value="${producto.mesesGarantia}" />&#xA;<fmt:message key="label.weigth" />: <c:out value="${producto.peso}" />&#xA;<fmt:message key="label.volume" />: <c:out value="${producto.volumen}" />&#xA;<fmt:message key="label.Category" />: <c:out value="${producto.subcategoria.categoria[nameColSelect]}" />&#xA;<fmt:message key="label.Subcategory" />: <c:out value="${producto.subcategoria[nameColSelect]}" />'>
						<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">
							<td class="sin_padding">
								<button type="button" class="btn btn-default" title="<fmt:message key='Edit' />" onclick='location.href="<c:url value='/producto/${producto.idPro}' />"'>
								  <img src='<c:url value="/resources/imgs/editar.png"/>' alt="Editar" class="tamanio_imagen">
								</button>
							</td>
						</sec:authorize>
						<sec:authorize access="hasAnyRole('ROL_ROOT')">
							<td class="sin_padding">
								<button type="button" class="btn btn-default" title="<fmt:message key='Delete' />" onclick="return confirmDelete(${producto.idPro})">
								  <img src='<c:url value="/resources/imgs/borrar.png"/>' class="tamanio_imagen">
								</button>
							</td>
						</sec:authorize>
						<td><c:out value="${producto[nameColSelect]}" /></td>
						<td class="text-center"><fmt:message key="${producto.estado}" /></td>
						<td><c:out value="${producto.marca}" /></td>
						<td><c:out value="${producto.modelo}" /></td>	
						<td class="text-right"><fmt:formatNumber type="currency" value="${producto.precioVenta}" /></td>
						<td class="width-35"></td>
						<td class="text-center"><c:out value="${producto.unidades}" /></td>
						<td class="sin_padding">
							<a title="<fmt:message key="Companies" />" href='<c:url value='/productoEmpresa/producto/${producto.idPro}' />'>
								<img src='<c:url value="/resources/imgs/empresa.png"/>' class="width-35">
							</a>
							<a title="<fmt:message key="label.Bills" />" href='<c:url value='/productoFactura/producto/${producto.idPro}' />'>
								<img src='<c:url value="/resources/imgs/factura.png"/>' class="margin-left-5porciento width-35">
							</a>
							<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">
								<a title="<fmt:message key="label.Add.remove.stock" />" href='<c:url value='/producto/stock/${producto.idPro}' />'>
									<img src='<c:url value="/resources/imgs/stock.png"/>' class="margin-left-5porciento width-35">
								</a>
							</sec:authorize>
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