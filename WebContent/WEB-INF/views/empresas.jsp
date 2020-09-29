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
	<title><fmt:message key="Companies" /></title>
	<c:import url="/WEB-INF/views/importHead.jsp" />
	
	<script type="text/javascript" src='<c:url value="/resources/js/utiles.js" />'></script>
	<script type="text/javascript">
		function confirmDelete(idEmp){
			if(confirm("<fmt:message key='Delete.message' />")){
				var url = "<c:url value='/empresa/"+idEmp+"/delete' />";
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
				$(".resultadosBusqueda").collapse('hide');
			} else {
				<c:forEach items="${buscarempresas}" var="emp" varStatus="status">
					var nombre = normalizado('${emp.nombreComercial}');
					var cif = normalizado('${emp.cif}');
					var email = normalizado('${emp.email}');
					if(nombre.toLowerCase().indexOf(texto) !== -1 || cif.indexOf(texto) !== -1 || email.toLowerCase().indexOf(texto) !== -1){
						resultado.innerHTML += "<a href=\"<c:url value='/empresa/filtered/${emp.idEmp}' />\">${emp.nombreComercial} ${emp.cif} ${emp.email}</a>";
					}
				</c:forEach>
				$(".resultadosBusqueda").collapse('show');
			}
		}
		function ordenaTabla(numCol,actual,total){
			var columnas = ['nombreComercial','cif','email','telefono','fax'];
			var url = "<c:url value='/empresa/all/"+columnas[numCol]+"/"+actual+"/"+total+"' />";
			location.href=url;			
		}
	</script>
</head>
<body class="${prefUsr.tema}fondopantalla">
	<div class="container">
		<c:import url="/WEB-INF/views/menu.jsp" />
		<div class="well well-sm text-center h2 ${prefUsr.tema}titulo"><fmt:message key="Companies" /></div>
		<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">
			<div class="row">
				<div class="col-sm-3 col-md-2">
					<input type="text" id="formulario" class="form-control">
					<script>
						const formulario = document.querySelector('#formulario');
						formulario.addEventListener('keyup', filtrar);
					</script>
				</div>
				<div class="col-sm-2 col-md-4">
					<div class="dropdown collapse resultadosBusqueda">
						<div class="dropdown-content" id="resultado">
						</div>
					</div>
				</div>
				<div class="col-sm-2 col-md-2">
					<c:if test="${hayEmpresaPropia}">
						<button type="button" class="btn btn-warning ml-1 border-color-dam" onclick='location.href="<c:url value='/empresaPropia'/>"'>
							<fmt:message key="Own.company" />
						</button>
					</c:if>
					<c:if test="${not hayEmpresaPropia}">
						<button type="button" class="btn btn-danger ml-1 border-color-dam" onclick='location.href="<c:url value='/empresaPropia'/>"'>
							<fmt:message key="Own.company" />
						</button>
					</c:if>
				</div>
				<div class="col-sm-2 col-md-2">
					<c:if test="${not empty empresa_agregada}">
						<span style="color: green;">
							<fmt:message key="Company.added" />
						</span>
					</c:if>
					<c:if test="${not empty empresa_eliminada}">
						<span style="color: green;">
							<fmt:message key="Company.deleted" />
						</span>
					</c:if>
				</div>
				<div class="col-sm-3 col-md-2  navbar-right">
					<button type="button" class="btn fondo-c0c0c0 ml-1 border-color-dam ${prefUsr.tema}botonagregar" onclick='location.href="<c:url value='/empresa/0'/>"'>
						<fmt:message key="Add.company" />
					</button>
				</div>		
			</div>
		</sec:authorize>
		<sec:authorize access="!hasAnyRole('ROL_ADMIN','ROL_ROOT')">
			<div class="row">
				<div class="col-sm-3 col-md-2">
					<input type="text" id="formulario" class="form-control">
					<script>
						const formulario = document.querySelector('#formulario');
						formulario.addEventListener('keyup', filtrar);
					</script>
				</div>
				<div class="col-sm-3 col-md-6">
					<div class="dropdown collapse resultadosBusqueda">
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
		
		<div class="row">
			<div class="hidden-xs hidden-sm col-md-12">
				<div class="divTablaSinScroll">
					<table class="table table-striped">
						<thead>
							<tr class="cursor-pointer">
								<c:set var="count" value="0" scope="page" />
								<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">
									<th class="extraAdmin-th cursor-text"></th>
								</sec:authorize>
								<th <c:if test="${empresas.size() > 1}">onclick="ordenaTabla(${count},${paginacion.actual},${paginacion.registrosPorPagina})"</c:if>><fmt:message key="label.Company.name" /></th>
								<c:set var="count" value="${count + 1}" scope="page"/>
								<th <c:if test="${empresas.size() > 1}">onclick="ordenaTabla(${count},${paginacion.actual},${paginacion.registrosPorPagina})"</c:if>><fmt:message key="label.vat" /></th>
								<c:set var="count" value="${count + 1}" scope="page"/>
								<th <c:if test="${empresas.size() > 1}">onclick="ordenaTabla(${count},${paginacion.actual},${paginacion.registrosPorPagina})"</c:if>><fmt:message key="label.Email" /></th>
								<c:set var="count" value="${count + 1}" scope="page"/>
								<th <c:if test="${empresas.size() > 1}">onclick="ordenaTabla(${count},${paginacion.actual},${paginacion.registrosPorPagina})"</c:if>><fmt:message key="label.Phone" /></th>
								<c:set var="count" value="${count + 1}" scope="page"/>
								<th <c:if test="${empresas.size() > 1}">onclick="ordenaTabla(${count},${paginacion.actual},${paginacion.registrosPorPagina})"</c:if>><fmt:message key="label.Fax" /></th>
								<th class="extraAdmin-th cursor-text"><fmt:message key="label.Extras" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${empresas}" var="empresa">
							    <tr title='<fmt:message key="label.Limited.company" />: <c:out value="${empresa.tipoSociedad}" />&#xA;<fmt:message key="label.Activity" />: <c:out value="${empresa.actividad}" />&#xA;<fmt:message key="label.Web.page" />: <c:out value="${empresa.paginaWeb}" />&#xA;<c:out value="${empresa.observaciones}" />'>
									<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">
										<td class="extraAdmin-td">
											<a title="<fmt:message key='Edit' />" onclick='location.href="<c:url value='/empresa/${empresa.idEmp}' />"'>
												<img src='<c:url value="/resources/imgs/editar.png"/>' class="tamanio_imagen">
											</a>
											<a title="<fmt:message key='Delete' />" onclick="return confirmDelete(${empresa.idEmp})">
												<img src='<c:url value="/resources/imgs/borrar.png"/>' class="tamanio_imagen">
											</a>
										</td>
									</sec:authorize>
									<td><c:out value="${empresa.nombreComercial}" /></td>
									<td><c:out value="${empresa.cif}" /></td>
									<td><c:out value="${empresa.email}" /></td>	
									<td><c:out value="${empresa.telefono}" /></td>
									<td><c:out value="${empresa.fax}" /></td>
									<td class="extraAdmin-td">
										<a title="<fmt:message key="label.Addresses" />" href='<c:url value='/direccionEmpresa/${empresa.idEmp}' />'>
											<img src='<c:url value="/resources/imgs/domicilio.png"/>' class="tamanio_imagen">
										</a>
										<a title="<fmt:message key="label.Workers" />" href='<c:url value='/usuarioEmpresa/empresa/${empresa.idEmp}' />'>
											<img src='<c:url value="/resources/imgs/usuarios.png"/>' class="tamanio_imagen">
										</a>
										<a title="<fmt:message key="Products" />" href='<c:url value='/productoEmpresa/empresa/${empresa.idEmp}' />'>
											<img src='<c:url value="/resources/imgs/productos.png"/>' class="tamanio_imagen">
										</a>
									</td>
							    </tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-xs-12 col-sm-12 hidden-md hidden-lg hidden-xl">
				<table class="table table-striped">
					<tbody>
						<c:forEach items="${empresas}" var="empresa">
						    <tr href="#ventana${empresa.idEmp}" class="thumbnail" data-toggle="modal">
								<td><c:out value="${empresa.nombreComercial}" /></td>
								<td><c:out value="${empresa.cif}" /></td>
						    </tr>
						    
							<div class="modal fade" id="ventana${empresa.idEmp}">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
											<h4 class="modal-title justify-content-center"><c:out value="${empresa.nombreComercial}" /></h4>
										</div>
										<div class="modal-body">
							            	<div class="col-xs-4">								
												<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">	
									            	<div class="col-xs-6">
														<a title="<fmt:message key='Edit' />" onclick='location.href="<c:url value='/empresa/${empresa.idEmp}' />"' class="cursor-pointer">
															<img src='<c:url value="/resources/imgs/editar.png"/>' class="tamanio_imagen">
														</a>								
													</div>
										            <div class="col-xs-6">
														<a title="<fmt:message key='Delete' />" onclick="return confirmDelete(${empresa.idEmp})" class="cursor-pointer">
															<img src='<c:url value="/resources/imgs/borrar.png"/>' class="tamanio_imagen">
														</a>	
													</div>									
												</sec:authorize>
											</div>	
								            <div class="col-xs-2">
												<a title="<fmt:message key="label.Addresses" />" href='<c:url value='/direccionEmpresa/${empresa.idEmp}' />'>
													<img src='<c:url value="/resources/imgs/domicilio.png"/>' class="tamanio_imagen">
												</a>
											</div>
								            <div class="col-xs-2">
												<a title="<fmt:message key="label.Workers" />" href='<c:url value='/usuarioEmpresa/empresa/${empresa.idEmp}' />'>
													<img src='<c:url value="/resources/imgs/usuarios.png"/>' class="tamanio_imagen">
												</a>
											</div>
								            <div class="col-xs-4">
												<a title="<fmt:message key="Products" />" href='<c:url value='/productoEmpresa/empresa/${empresa.idEmp}' />'>
													<img src='<c:url value="/resources/imgs/productos.png"/>' class="tamanio_imagen">
												</a>
											</div>
											<div class="height50"></div>
											<p><fmt:message key="label.vat" />: <c:out value="${empresa.cif}" /></p>
											<p><fmt:message key="label.Email" />: <c:out value="${empresa.email}" /></p>
											<p><fmt:message key="label.Phone" />: <c:out value="${empresa.telefono}" /></p>
											<p><fmt:message key="label.Fax" />: <c:out value="${empresa.fax}" /></p>
											<p><fmt:message key="label.Limited.company" />: <c:out value="${empresa.tipoSociedad}" /></p>
											<p><fmt:message key="label.Activity" />: <c:out value="${empresa.actividad}" /></p>
											<p><fmt:message key="label.Web.page" />: <c:out value="${empresa.paginaWeb}" /></p>
											<p><c:out value="${empresa.observaciones}" /></p>
										</div>
									</div>
								</div>
							</div>
					    </c:forEach>
				    </tbody>
				</table>
			</div>
		</div>
		
		<!-- PAGINACION -->
		<div class="row">
			<div class="justify-content-center">
				<c:set var="rutaAll" value="empresa/all/null" scope="page" /> <!-- esta es la ruta que cambia en cada página -->
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