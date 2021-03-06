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
	<title><fmt:message key="label.Customers" /></title>
	<c:import url="/WEB-INF/views/importHead.jsp" />
	
	<script type="text/javascript" src='<c:url value="/resources/js/utiles.js" />'></script>
	<script type="text/javascript">
		function confirmDelete(idUsr){
			if(confirm("<fmt:message key='Delete.message' />")){
				var url = "<c:url value='/cliente/"+idUsr+"/delete' />";
				location.href=url;
				return true;
			}
			return false;
		}
		function confirmReset(idUsr){
			if(confirm("<fmt:message key='Reset.message' />")){
				var url = "<c:url value='/cliente/reset/"+idUsr+"' />";
				location.href=url;
				return true;
			}
			return false;
		}
		function filtrarAdmin(){
			filtrar(true);
		}
		function filtrarCliente(){
			filtrar(false);
		}
		function filtrar(admin) {	
			const resultado = document.querySelector('#resultado');
			const texto = normalizado(formulario.value.toLowerCase());
			resultado.innerHTML = '';
			if(texto === ''){
				$(".resultadosBusqueda").collapse('hide');
			} else {
				<c:forEach items="${buscarusuarios}" var="usu" varStatus="status">
					var username = normalizado('${usu.usuario}');
					var nombre = normalizado('${usu.datosPersonales.nombre}');
					var apellidos = normalizado('${usu.datosPersonales.apellido1}'+" "+'${usu.datosPersonales.apellido2}');
					if(username.toLowerCase().indexOf(texto) !== -1 || nombre.toLowerCase().indexOf(texto) !== -1 || apellidos.toLowerCase().indexOf(texto) !== -1){
						if(admin){
							resultado.innerHTML += "<a href=\"<c:url value='/cliente/filtered/${usu.idUsr}' />\">${usu.usuario} - ${usu.datosPersonales.nombre} ${usu.datosPersonales.apellido1} ${usu.datosPersonales.apellido2}</a>";
						} else {
							resultado.innerHTML += "<a href=\"<c:url value='/cliente/cliente/filtered/${usu.idUsr}' />\">${usu.usuario} - ${usu.datosPersonales.nombre} ${usu.datosPersonales.apellido1} ${usu.datosPersonales.apellido2}</a>";
							
						}
					}
				</c:forEach>
				$(".resultadosBusqueda").collapse('show');
			}
		}	
		function ordenaTabla(numCol,actual,total){
			var columnas = ['usuario.usuario','usuario.habilitado','datosPersonales.nombre','datosPersonales.apellido1','datosPersonales.fechaNacimiento','datosPersonales.email','datosPersonales.telefono'];
			var url = "<c:url value='/cliente/all/"+columnas[numCol]+"/null/"+actual+"/"+total+"' />";
			location.href=url;			
		}	
	</script>
	
</head>
<body class="${prefUsr.tema}fondopantalla">
	<div class="container">
		<c:import url="/WEB-INF/views/menu.jsp" />
		<div class="well well-sm text-center h2 ${prefUsr.tema}titulo"><fmt:message key="label.Customers" /></div>
		<fmt:message key="language.name" var="nameColSelect"/>
		<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">
			<div class="row">
				<div class="col-sm-3 col-md-2">
					<input type="text" id="formulario" class="form-control">
					<script>
						const formulario = document.querySelector('#formulario');
						formulario.addEventListener('keyup', filtrarAdmin);
					</script>
				</div>
				<div class="col-sm-3 col-md-6">
					<div class="dropdown collapse resultadosBusqueda">
						<div class="dropdown-content" id="resultado">
						</div>
					</div>
				</div>
				<div class="col-sm-3 col-md-2">
					<c:if test="${not empty usuario_agregado}">
						<span style="color: green;">
							<fmt:message key="User.added" />
						</span>
					</c:if>
					<c:if test="${not empty usuario_eliminado}">
						<span style="color: green;">
							<fmt:message key="User.deleted" />
						</span>
					</c:if>
				</div>
				<div class="col-sm-3 col-md-2  navbar-right">
					<button type="button" class="btn fondo-c0c0c0 float-right ml-1 border-color-dam ${prefUsr.tema}botonagregar" onclick='location.href="<c:url value='/cliente/0'/>"'>
						<fmt:message key="Add.user" />
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
								<th <c:if test="${usuarios.size() > 1}">onclick="ordenaTabla(${count},${paginacion.actual},${paginacion.registrosPorPagina})"</c:if>><fmt:message key="label.Username" /></th>
								<c:set var="count" value="${count + 1}" scope="page"/>
								<th <c:if test="${usuarios.size() > 1}">onclick="ordenaTabla(${count},${paginacion.actual},${paginacion.registrosPorPagina})"</c:if>></th>
								<c:set var="count" value="${count + 1}" scope="page"/>
								<th <c:if test="${usuarios.size() > 1}">onclick="ordenaTabla(${count},${paginacion.actual},${paginacion.registrosPorPagina})"</c:if>><fmt:message key="label.Name" /></th>
								<c:set var="count" value="${count + 1}" scope="page"/>
								<th <c:if test="${usuarios.size() > 1}">onclick="ordenaTabla(${count},${paginacion.actual},${paginacion.registrosPorPagina})"</c:if>><fmt:message key="label.Lastname" /></th>
								<c:set var="count" value="${count + 1}" scope="page"/>
								<th <c:if test="${usuarios.size() > 1}">onclick="ordenaTabla(${count},${paginacion.actual},${paginacion.registrosPorPagina})"</c:if>><fmt:message key="label.Birthdate" /></th>
								<c:set var="count" value="${count + 1}" scope="page"/>
								<th <c:if test="${usuarios.size() > 1}">onclick="ordenaTabla(${count},${paginacion.actual},${paginacion.registrosPorPagina})"</c:if>><fmt:message key="label.Email" /></th>
								<c:set var="count" value="${count + 1}" scope="page"/>
								<th <c:if test="${usuarios.size() > 1}">onclick="ordenaTabla(${count},${paginacion.actual},${paginacion.registrosPorPagina})"</c:if>><fmt:message key="label.Phone" /></th>
								<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">
									<th class="cursor-text"><fmt:message key="label.Roles" /></th>
								</sec:authorize>
								<th class="extraAdmin-th cursor-text"><fmt:message key="label.Extras" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${usuarios}" var="usuario">
							    <tr title='<fmt:message key="label.Creation.date" />: <fmt:formatDate value="${usuario.fechaCreacion}" pattern="dd/MM/yyyy"/>&#xA;<fmt:message key="label.idcard" />: <c:out value="${usuario.datosPersonales.dni}" />&#xA;<fmt:message key="label.Sex" />: <c:out value="${usuario.datosPersonales.sexo}" />&#xA;<fmt:message key="label.Nationality" />: <c:out value="${usuario.datosPersonales.nacionalidad[nameColSelect]}" />&#xA;<c:out value="${usuario.datosPersonales.observaciones}" />'>
							    
									<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">
										<td class="extraAdmin-td">
											<a title="<fmt:message key='Edit' />" onclick='location.href="<c:url value='/cliente/${usuario.idUsr}' />"'>
												<img src='<c:url value="/resources/imgs/editar.png"/>' class="tamanio_imagen">
											</a>
											<a title="<fmt:message key='Delete' />" onclick="return confirmDelete(${usuario.idUsr})">
												<img src='<c:url value="/resources/imgs/borrar.png"/>' class="tamanio_imagen">
											</a>
										</td>
									</sec:authorize>
									<td><c:out value="${usuario.usuario}" /></td>
									
									<sec:authorize access="!hasAnyRole('ROL_ADMIN','ROL_ROOT')">
										<c:if test="${usuario.habilitado}">
											<td><img src='<c:url value="/resources/imgs/true.png"/>' class="tamanio_imagen"></td>
										</c:if>
										<c:if test="${not usuario.habilitado}">
											<td><img src='<c:url value="/resources/imgs/false.png"/>' class="tamanio_imagen"></td>
										</c:if>
									</sec:authorize>
									
									<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">
										<c:if test="${usuario.habilitado}">
											<td><img src='<c:url value="/resources/imgs/true.png"/>' class="tamanio_imagen cursor-pointer" onclick='location.href="<c:url value='/cliente/available/${usuario.idUsr}' />"'></td>
										</c:if>
										<c:if test="${not usuario.habilitado}">
											<td><img src='<c:url value="/resources/imgs/false.png"/>' class="tamanio_imagen cursor-pointer" onclick='location.href="<c:url value='/cliente/available/${usuario.idUsr}' />"'></td>
										</c:if>
									</sec:authorize>
									
									<td><c:out value="${usuario.datosPersonales.nombre}" /></td>
									<td><c:out value="${usuario.datosPersonales.apellido1}" /> <c:out value="${usuario.datosPersonales.apellido2}" /></td>	
									<td><fmt:formatDate value="${usuario.datosPersonales.fechaNacimiento}" pattern="dd/MM/yyyy"/></td>		
									<td><c:out value="${usuario.datosPersonales.email}" /></td>
									<td><c:out value="${usuario.datosPersonales.telefono}" /></td>
									<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">
										<c:set var="userRoles" value="${usuario.usuarioRol}" scope="page" />
										<td>
											<c:forEach items="${userRoles}" var="roles">
												<span class="colorFondo${roles.rol.idRol}"><c:out value="${roles.rol.rol}" /></span><br/>
											</c:forEach>
										</td>
									</sec:authorize>
									<td class="extraAdmin-td">
										<a title="<fmt:message key="label.Addresses" />" href='<c:url value='/direccion/${usuario.idUsr}' />'>
											<img src='<c:url value="/resources/imgs/domicilio.png"/>' class="tamanio_imagen">
										</a>
										<a title="<fmt:message key="Companies" />" href='<c:url value='/usuarioEmpresa/usuario/${usuario.idUsr}' />'>
											<img src='<c:url value="/resources/imgs/empresa.png"/>' class="tamanio_imagen">
										</a>
										<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">
											<a title="<fmt:message key="Pass.reset" />" onclick="return confirmReset(${usuario.idUsr})">
												<img src='<c:url value="/resources/imgs/pass_reset.png"/>' class="tamanio_imagen">
											</a>
										</sec:authorize>
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
						<c:forEach items="${usuarios}" var="usuario">
						    <tr href="#ventana${usuario.idUsr}" class="thumbnail" data-toggle="modal">
						    	<td><c:out value="${usuario.usuario}" /></td>
							
								<sec:authorize access="!hasAnyRole('ROL_ADMIN','ROL_ROOT')">
									<c:if test="${usuario.habilitado}">
										<td><img src='<c:url value="/resources/imgs/true.png"/>' class="tamanio_imagen"></td>
									</c:if>
									<c:if test="${not usuario.habilitado}">
										<td><img src='<c:url value="/resources/imgs/false.png"/>' class="tamanio_imagen"></td>
									</c:if>
								</sec:authorize>
								
								<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">
									<c:if test="${usuario.habilitado}">
										<td><img src='<c:url value="/resources/imgs/true.png"/>' class="tamanio_imagen cursor-pointer" onclick='location.href="<c:url value='/cliente/available/${usuario.idUsr}' />"'></td>
									</c:if>
									<c:if test="${not usuario.habilitado}">
										<td><img src='<c:url value="/resources/imgs/false.png"/>' class="tamanio_imagen cursor-pointer" onclick='location.href="<c:url value='/cliente/available/${usuario.idUsr}' />"'></td>
									</c:if>
								</sec:authorize>
								
								<td><c:out value="${usuario.datosPersonales.nombre}" /></td>
								<td><c:out value="${usuario.datosPersonales.apellido1}" /> <c:out value="${usuario.datosPersonales.apellido2}" /></td>
						    </tr>
						    
							<div class="modal fade" id="ventana${usuario.idUsr}">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
											<h4 class="modal-title justify-content-center"><c:out value="${usuario.datosPersonales.nombre}" /> <c:out value="${usuario.datosPersonales.apellido1}" /> <c:out value="${usuario.datosPersonales.apellido2}" /></h4>
										</div>
										<div class="modal-body">
							            	<div class="col-xs-4">								
												<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">	
									            	<div class="col-xs-6">									
														<a title="<fmt:message key='Edit' />" onclick='location.href="<c:url value='/cliente/${usuario.idUsr}' />"' class="cursor-pointer">
															<img src='<c:url value="/resources/imgs/editar.png"/>' class="tamanio_imagen">
														</a>
													</div>
										            <div class="col-xs-6">
														<a title="<fmt:message key='Delete' />" onclick="return confirmDelete(${usuario.idUsr})" class="cursor-pointer">
															<img src='<c:url value="/resources/imgs/borrar.png"/>' class="tamanio_imagen">
														</a>	
													</div>									
												</sec:authorize>
											</div>	
								            <div class="col-xs-2">
												<a title="<fmt:message key="label.Addresses" />" href='<c:url value='/direccion/${usuario.idUsr}' />'>
													<img src='<c:url value="/resources/imgs/domicilio.png"/>' class="tamanio_imagen">
												</a>
											</div>
								            <div class="col-xs-2">
												<a title="<fmt:message key="Companies" />" href='<c:url value='/usuarioEmpresa/usuario/${usuario.idUsr}' />'>
													<img src='<c:url value="/resources/imgs/empresa.png"/>' class="tamanio_imagen">
												</a>
											</div>
								            <div class="col-xs-4">
												<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">
													<a title="<fmt:message key="Pass.reset" />" onclick="return confirmReset(${usuario.idUsr})" class="cursor-pointer">
														<img src='<c:url value="/resources/imgs/pass_reset.png"/>' class="tamanio_imagen">
													</a>
												</sec:authorize>
											</div>
											<div class="height50"></div>
								            <p><fmt:message key="label.Username" />: <c:out value="${usuario.usuario}" /></p>
								            <p><fmt:message key="label.Birthdate" />: <fmt:formatDate value="${usuario.datosPersonales.fechaNacimiento}" pattern="dd/MM/yyyy"/></p>
								            <p><fmt:message key="label.Email" />: <c:out value="${usuario.datosPersonales.email}" /></p>
								            <p><fmt:message key="label.Phone" />: <c:out value="${usuario.datosPersonales.telefono}" /></p>
								            <p><fmt:message key="label.Creation.date" />: <fmt:formatDate value="${usuario.fechaCreacion}" pattern="dd/MM/yyyy"/></p>
								            <p><fmt:message key="label.idcard" />: <c:out value="${usuario.datosPersonales.dni}" /></p>
								            <p><fmt:message key="label.Sex" />: <c:out value="${usuario.datosPersonales.sexo}" /></p>
								            <p><fmt:message key="label.Nationality" />: <c:out value="${usuario.datosPersonales.nacionalidad[nameColSelect]}" /></p>
								            <p><c:out value="${usuario.datosPersonales.observaciones}" /></p>
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
				<c:set var="rutaAll" value="cliente/all/null/null" scope="page" /> <!-- esta es la ruta que cambia en cada página -->
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
	</div>
	<div class="row">
		<div class="col-xs-1">
		</div>
		<div class="col-xs-10">
			<footer>
				<c:import url="/WEB-INF/views/importFooter.jsp" />
			</footer>
		</div>
		<div class="col-xs-1">
		</div>
	</div>
</body>
</html>