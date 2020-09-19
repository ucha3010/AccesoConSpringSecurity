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
				<c:forEach items="${buscarproductos}" var="pro" varStatus="status">
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
		function ordenaTabla(numCol,actual,total){
			var columnas = ['${nameColSelect}','estado','marca','modelo','precioVenta','unidades'];
			var url = "<c:url value='/producto/all/"+columnas[numCol]+"/"+actual+"/"+total+"' />";
			location.href=url;			
		}
	</script>
</head>
<body>
	<div class="container-fluid">
		<c:import url="/WEB-INF/views/menu.jsp" />
		<div class="well well-sm text-center h2"><fmt:message key="Products" /></div>
		<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">
			<div class="row">
				<div class="hidden-xs col-sm-3 col-md-2">
					<input type="text" id="formulario" class="form-control">
					<script>
						const formulario = document.querySelector('#formulario');
						formulario.addEventListener('keyup', filtrar);
					</script>
				</div>
				<div class="hidden-xs col-sm-2 col-md-4">
					<div class="dropdown collapse">
						<div class="dropdown-content" id="resultado">
						</div>
					</div>
				</div>
				<div class="col-sm-4">
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
				<div class="col-sm-3 col-md-2  navbar-right">
					<button type="button" class="btn fondo-c0c0c0 float-right ml-1 border-color-dam" onclick='location.href="<c:url value='/producto/0'/>"'>
						<fmt:message key="Add.product" />
					</button>
				</div>		
			</div>
		</sec:authorize>
		<sec:authorize access="!hasAnyRole('ROL_ADMIN','ROL_ROOT')">
			<div class="row">
				<div class="hidden-xs col-sm-3 col-md-2">
					<input type="text" id="formulario" class="form-control">
					<script>
						const formulario = document.querySelector('#formulario');
						formulario.addEventListener('keyup', filtrar);
					</script>
				</div>
				<div class="hidden-xs col-sm-3 col-md-6">
					<div class="dropdown collapse">
						<div class="dropdown-content" id="resultado">
						</div>
					</div>
				</div>
				<div class="col-sm-3 col-md-2">
				</div>
				<div class="col-sm-3 col-md-2  navbar-right">
				</div>
			</div>
		</sec:authorize>
		<div class="divTablaSinScroll">
			<table class="table table-striped">
				<thead>
					<tr class="cursor-pointer">
						<c:set var="count" value="0" scope="page" />
						<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">
							<th class="extraAdmin-th cursor-text"></th>
						</sec:authorize>
						<th <c:if test="${productos.size() > 1}">onclick="ordenaTabla(${count},${paginacion.actual},${paginacion.registrosPorPagina})"</c:if>><fmt:message key="label.Product.description" /></th>
						<th class="min-width-60"></th>
						<c:set var="count" value="${count + 1}" scope="page"/>
						<th <c:if test="${productos.size() > 1}">onclick="ordenaTabla(${count},${paginacion.actual},${paginacion.registrosPorPagina})"</c:if> class="text-center"><fmt:message key="label.state" /></th>
						<c:set var="count" value="${count + 1}" scope="page"/>
						<th <c:if test="${productos.size() > 1}">onclick="ordenaTabla(${count},${paginacion.actual},${paginacion.registrosPorPagina})"</c:if>><fmt:message key="label.brand" /></th>
						<c:set var="count" value="${count + 1}" scope="page"/>
						<th <c:if test="${productos.size() > 1}">onclick="ordenaTabla(${count},${paginacion.actual},${paginacion.registrosPorPagina})"</c:if>><fmt:message key="label.model" /></th>
						<c:set var="count" value="${count + 1}" scope="page"/>
						<th colspan="2" class="text-center min-width-160" <c:if test="${productos.size() > 1}">onclick="ordenaTabla(${count},${paginacion.actual},${paginacion.registrosPorPagina})"</c:if>><fmt:message key="label.salePrice" /></th>
						<c:set var="count" value="${count + 1}" scope="page"/>
						<th class="text-center" <c:if test="${productos.size() > 1}">onclick="ordenaTabla(${count},${paginacion.actual},${paginacion.registrosPorPagina})"</c:if>><fmt:message key="label.units" /></th>
						<th class="min-width-160 cursor-text zindex-100"><fmt:message key="label.Extras" /></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${productos}" var="producto">
					    <tr title='<fmt:message key="label.purchase.price" />: <fmt:formatNumber type="currency" value="${producto.precioCompra}" />&#xA;<fmt:message key="label.Serial.number" />: <c:out value="${producto.serie}" />&#xA;<fmt:message key="label.location" />: <c:out value="${producto.ubicacion}" />&#xA;<fmt:message key="label.send" />: <c:out value="${producto.enviar}" />&#xA;<fmt:message key="label.salable" />: <c:out value="${producto.vendible}" />&#xA;<fmt:message key="label.warranty.months" />: <c:out value="${producto.mesesGarantia}" />&#xA;<fmt:message key="label.weigth" />: <c:out value="${producto.peso}" />&#xA;<fmt:message key="label.volume" />: <c:out value="${producto.volumen}" />&#xA;<fmt:message key="label.Category" />: <c:out value="${producto.subcategoria.categoria[nameColSelect]}" />&#xA;<fmt:message key="label.Subcategory" />: <c:out value="${producto.subcategoria[nameColSelect]}" />'>
							<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">
								<td class="extraAdmin-td">
									<a title="<fmt:message key='Edit' />" onclick='location.href="<c:url value='/producto/${producto.idPro}' />"'>
										<img src='<c:url value="/resources/imgs/editar.png"/>' class="tamanio_imagen">
									</a>
									<sec:authorize access="hasAnyRole('ROL_ROOT')">
										<a title="<fmt:message key='Delete' />" onclick="return confirmDelete(${producto.idPro})">
											<img src='<c:url value="/resources/imgs/borrar.png"/>' class="tamanio_imagen">
										</a>
									</sec:authorize>
								</td>
							</sec:authorize>
							<td><c:out value="${producto[nameColSelect]}" /></td>
							<td>
								<c:if test="${(not empty producto.fotos)}">
									<c:forEach items="${producto.fotos}" var="fotoPerfil">
										<c:if test="${fotoPerfil.principal}">
											<a href="#foto${producto.idPro}" class="thumbnail" data-toggle="modal">
												<img src='<c:url value="/resources/imgs/productos/${producto.idPro}/${fotoPerfil.nombre}"/>' class="width-50">
											</a>
											<div class="modal fade" id="foto${producto.idPro}">
												<div class="modal-dialog">
													<div class="modal-content">
														<div class="modal-header">
															<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
															<h4 class="modal-title"><img src='<c:url value="/resources/imgs/productos/${producto.idPro}/${fotoPerfil.nombre}"/>' class="justify-content-center"></h4>
														</div>
													</div>
												</div>
											</div>
										</c:if>
									</c:forEach>
								</c:if>
							</td>
							<td class="text-center"><fmt:message key="${producto.estado}" /></td>
							<td><c:out value="${producto.marca}" /></td>
							<td><c:out value="${producto.modelo}" /></td>	
							<td class="text-right"><fmt:formatNumber type="currency" value="${producto.precioVenta}" /></td>
							<td class="width-35"></td>
							<td class="text-center"><c:out value="${producto.unidades}" /></td>
							<td class="extraAdmin-td">
								<a title="<fmt:message key='label.Pictures' />" href='<c:url value='/foto/producto/${producto.idPro}' />'>
									<span class="glyphicon glyphicon-picture tamanio_imagen zindex-1"></span>
								</a>
								<a title="<fmt:message key="Companies" />" href='<c:url value='/productoEmpresa/producto/${producto.idPro}' />'>
									<img src='<c:url value="/resources/imgs/empresa.png"/>' class="tamanio_imagen">
								</a>
								<a title="<fmt:message key="label.Bills" />" href='<c:url value='/productoFactura/producto/${producto.idPro}' />'>
									<img src='<c:url value="/resources/imgs/factura.png"/>' class="tamanio_imagen">
								</a>
								<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">
									<a title="<fmt:message key="label.Add.remove.stock" />" href='<c:url value='/producto/stock/${producto.idPro}' />'>
										<img src='<c:url value="/resources/imgs/stock.png"/>' class="tamanio_imagen">
									</a>
								</sec:authorize>
							</td>
					    </tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<!-- PAGINACION -->
		<div class="row">
			<div class="justify-content-center">
				<c:set var="rutaAll" value="producto/all/null" scope="page" /> <!-- esta es la ruta que cambia en cada página -->
				<ul class="pagination">
					<li<c:if test="${paginacion.primeraPagina}"> class="disabled"</c:if>>
						<c:if test="${!paginacion.primeraPagina}">
							<a href="<c:url value="/${rutaAll}/${paginacion.anterior}/${paginacion.registrosPorPagina}"/>">&laquo;</a>
						</c:if>
						<c:if test="${paginacion.primeraPagina}">
							<span>&laquo;</span>
						</c:if>
					</li>

					<c:set var="pagina" value="0" scope="page" />
					<c:forEach items="${paginacion.comienzaPagina}" var="comienza">
						<c:set var="pagina" value="${pagina + 1}" scope="page"/>
						<li<c:if test="${comienza == paginacion.actual}"> class="disabled"</c:if>>
							<c:if test="${comienza != paginacion.actual}">
								<a href="<c:url value="/${rutaAll}/${comienza}/${paginacion.registrosPorPagina}"/>">${pagina}</a>
							</c:if>
							<c:if test="${comienza == paginacion.actual}">
								<span>${pagina}</span>
							</c:if>
						</li>
					</c:forEach>

					<li<c:if test="${paginacion.ultimaPagina}"> class="disabled"</c:if>>
						<c:if test="${!paginacion.ultimaPagina}">
							<a href="<c:url value="/${rutaAll}/${paginacion.siguiente}/${paginacion.registrosPorPagina}"/>">&raquo;</a>
						</c:if>
						<c:if test="${paginacion.ultimaPagina}">
							<span>&raquo;</span>
						</c:if>
					</li>
				</ul>
			</div>
		</div>
		
		<footer>
			<c:import url="/WEB-INF/views/importFooter.jsp" />
		</footer>
	</div>
</body>
</html>