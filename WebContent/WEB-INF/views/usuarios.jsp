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
	<title><fmt:message key="label.Users" /></title>
	<c:import url="/WEB-INF/views/importHead.jsp" />
	
	<script type="text/javascript" src='<c:url value="/resources/js/utiles.js" />'></script>
	<script type="text/javascript">
		function confirmDelete(idUsr){
			if(confirm("<fmt:message key='Delete.message' />")){
				var url = "<c:url value='/usuario/delete/"+idUsr+"' />";
				location.href=url;
				return true;
			}
			return false;
		}
		function confirmReset(idUsr){
			if(confirm("<fmt:message key='Reset.message' />")){
				var url = "<c:url value='/usuario/reset/"+idUsr+"' />";
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
				$(".collapse").collapse('hide');
			} else {
				<c:forEach items="${buscarusuarios}" var="usu" varStatus="status">
					var username = normalizado('${usu.usuario}');
					var nombre = normalizado('${usu.datosPersonales.nombre}');
					var apellidos = normalizado('${usu.datosPersonales.apellido1}'+" "+'${usu.datosPersonales.apellido2}');
					if(username.toLowerCase().indexOf(texto) !== -1 || nombre.toLowerCase().indexOf(texto) !== -1 || apellidos.toLowerCase().indexOf(texto) !== -1){
						if(admin){
							resultado.innerHTML += "<a href=\"<c:url value='/usuario/filtered/${usu.idUsr}' />\">${usu.usuario} - ${usu.datosPersonales.nombre} ${usu.datosPersonales.apellido1} ${usu.datosPersonales.apellido2}</a>";
						} else {
							resultado.innerHTML += "<a href=\"<c:url value='/cliente/filtered/${usu.idUsr}' />\">${usu.usuario} - ${usu.datosPersonales.nombre} ${usu.datosPersonales.apellido1} ${usu.datosPersonales.apellido2}</a>";
							
						}
					}
				</c:forEach>
				$(".collapse").collapse('show');
			}
		}	
		function ordenaTabla(numCol,actual,total){
			var columnas = ['usuario.usuario','usuario.habilitado','datosPersonales.nombre','datosPersonales.apellido1','datosPersonales.fechaNacimiento','datosPersonales.email','datosPersonales.telefono'];
			var url = "<c:url value='/usuario/all/"+columnas[numCol]+"/null/"+actual+"/"+total+"' />";
			location.href=url;			
		}	
	</script>
	
</head>
<body class="${prefUsr.tema}fondopantalla">
	<div class="container">
		<c:import url="/WEB-INF/views/menu.jsp" />
		<div class="well well-sm text-center h2 ${prefUsr.tema}titulo"><fmt:message key="label.Users" /></div>
		<fmt:message key="language.name" var="nameColSelect"/>
		<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">
			<div class="row">
				<div class="hidden-xs col-sm-3 col-md-2">
					<input type="text" id="formulario" class="form-control">
					<script>
						const formulario = document.querySelector('#formulario');
						formulario.addEventListener('keyup', filtrarAdmin);
					</script>
				</div>
				<div class="hidden-xs col-sm-1">
					<div class="dropdown collapse">
						<div class="dropdown-content" id="resultado">
						</div>
					</div>
				</div>
				<div class="col-sm-2">
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
				<div class="col-sm-6 col-md-7 navbar-right">				
					<div class="dropdown inline-block-dam">
						<button class="btn dropdown-toggle dropdown-dam-1 ${prefUsr.tema}botonresto" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
							<fmt:message key="label.Receive.publicity" />
							<span class="caret"></span>
						</button>
						<ul class="dropdown-menu ${prefUsr.tema}botonresto" aria-labelledby="dropdownMenu1">
				            <li><a href="<c:url value='/usuario/publicity/true'/>"><fmt:message key="label.Yes" /></a></li>
				            <li><a href="<c:url value='/usuario/publicity/false'/>"><fmt:message key="label.No" /></a></li>
						</ul>
					</div>
				

					<button type="button" class="btn fondo-c0c0c0 float-right ml-1 border-color-dam ${prefUsr.tema}botonagregar" onclick='location.href="<c:url value='/usuario/idUsr/0'/>"'>
						<fmt:message key="Add.user" />
					</button>
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
						<th class="text-center extraAdmin-th cursor-text"><fmt:message key="label.Extras" /></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${usuarios}" var="usuario">
					    <tr title='<fmt:message key="label.Creation.date" />: <fmt:formatDate value="${usuario.fechaCreacion}" pattern="dd/MM/yyyy"/>&#xA;<fmt:message key="label.idcard" />: <c:out value="${usuario.datosPersonales.dni}" />&#xA;<fmt:message key="label.Sex" />: <c:out value="${usuario.datosPersonales.sexo}" />&#xA;<fmt:message key="label.Nationality" />: <c:out value="${usuario.datosPersonales.nacionalidad[nameColSelect]}" />&#xA;<c:out value="${usuario.datosPersonales.observaciones}" />'>
							<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">
								<td class="extraAdmin-td">
									<a title="<fmt:message key='Edit' />" onclick='location.href="<c:url value='/usuario/idUsr/${usuario.idUsr}' />"'>
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
									<td><img src='<c:url value="/resources/imgs/true.png"/>' class="tamanio_imagen cursor-pointer" onclick='location.href="<c:url value='/usuario/available/${usuario.idUsr}' />"'></td>
								</c:if>
								<c:if test="${not usuario.habilitado}">
									<td><img src='<c:url value="/resources/imgs/false.png"/>' class="tamanio_imagen cursor-pointer" onclick='location.href="<c:url value='/usuario/available/${usuario.idUsr}' />"'></td>
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
								<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">
									<a title="<fmt:message key="label.Addresses" />" href='<c:url value='/direccion/${usuario.idUsr}' />'>
										<img src='<c:url value="/resources/imgs/domicilio.png"/>' class="tamanio_imagen">
									</a>
								</sec:authorize>
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
		
		<!-- PAGINACION -->
		<div class="row">
			<div class="justify-content-center">
				<c:set var="rutaAll" value="usuario/all/null/null" scope="page" /> <!-- esta es la ruta que cambia en cada página -->
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