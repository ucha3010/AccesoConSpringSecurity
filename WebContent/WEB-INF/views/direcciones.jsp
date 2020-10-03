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
	<title><fmt:message key='label.Addresses' /></title>
	<c:import url="/WEB-INF/views/importHead.jsp" />
	
	<script type="text/javascript">
		function confirmDelete(idDir,idUsr){
			if(confirm("<fmt:message key='Delete.message' />")){
				var url = "<c:url value='/direccion/delete/"+idDir+"/"+idUsr+"' />";
				location.href=url;
				return true;
			}
			return false;
		}
	</script>
</head>
<body class="${prefUsr.tema}fondopantalla">
	<div class="container">
		<c:import url="/WEB-INF/views/menu.jsp" />
		<fmt:message key="language.name" var="nameColSelect"/>
		<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">
			<div class="row">
				<div class="col-xs-12 col-sm-6">
					<h1><c:out value="${usuario.datosPersonales.nombre}" /> <c:out value="${usuario.datosPersonales.apellido1}" /> <c:out value="${usuario.datosPersonales.apellido2}" /></h1>
				</div>
				<div class="hidden-xs col-sm-3">
				</div>
				<div class="col-xs-12 col-sm-3">
					<button class="btn fondo-c0c0c0 border-color-dam margin-top-20 ${prefUsr.tema}botonagregar" onclick='location.href="<c:url value='/direccion/formulario/0/${usuario.datosPersonales.idDatosPers}'/>"'>
						<fmt:message key="Add.address" />
					</button>
				</div>		
			</div>
		</sec:authorize>
		<div class="row">
			<div class="hidden-xs hidden-sm col-md-12">
				<div class="divTablaSinScroll">
					<table class="table table-striped">
						<thead>
							<tr>
								<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">
									<th class="extraAdmin-th"></th>
								</sec:authorize>
								<th><fmt:message key="label.Type.road" /></th>
								<th><fmt:message key="label.Street" /></th>
								<th><fmt:message key="label.Number" /></th>
								<th><fmt:message key="label.Rest" /></th>
								<th><fmt:message key="label.Postal.code" /></th>
								<th><fmt:message key="label.Province" /></th>
								<th><fmt:message key="label.City" /></th>
								<th><fmt:message key="label.Country" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${direcciones}" var="direccion">
							    <tr>
									<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">
										<td class="extraAdmin-td">
											<a title="<fmt:message key='Edit' />" onclick='location.href="<c:url value='/direccion/formulario/${direccion.idDir}/0' />"'>
												<img src='<c:url value="/resources/imgs/editar.png"/>' class="tamanio_imagen">
											</a>
											<a title="<fmt:message key='Delete' />" onclick="return confirmDelete(${direccion.idDir},${usuario.idUsr})">
												<img src='<c:url value="/resources/imgs/borrar.png"/>' class="tamanio_imagen">
											</a>
										</td>
									</sec:authorize>
									<td><fmt:message key='${direccion.tipoVia}' /></td>
									<td><c:out value="${direccion.nombreVia}" /></td>	
									<td><c:out value="${direccion.numero}" /></td>
									<td><c:out value="${direccion.resto}" /></td>
									<td><c:out value="${direccion.cp}" /></td>	
									<td><c:out value="${direccion.provincia}" /></td>	
									<td><c:out value="${direccion.ciudad}" /></td>
									<td><c:out value="${direccion.pais[nameColSelect]}" /></td>
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
						<c:forEach items="${direcciones}" var="direccion">
						    <tr href="#ventana${direccion.idDir}" class="thumbnail" data-toggle="modal">
								<td><fmt:message key='${direccion.tipoVia}' /></td>
								<td><c:out value="${direccion.nombreVia}" /></td>	
								<td><c:out value="${direccion.numero}" /></td>
								<td><c:out value="${direccion.ciudad}" /></td>
						    </tr>
						    
							<div class="modal fade" id="ventana${direccion.idDir}">
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
														<a title="<fmt:message key='Edit' />" onclick='location.href="<c:url value='/direccion/formulario/${direccion.idDir}/0' />"' class="cursor-pointer">
															<img src='<c:url value="/resources/imgs/editar.png"/>' class="tamanio_imagen">
														</a>								
													</div>
										            <div class="col-xs-6">
														<a title="<fmt:message key='Delete' />" onclick="return confirmDelete(${direccion.idDir},${usuario.idUsr})" class="cursor-pointer">
															<img src='<c:url value="/resources/imgs/borrar.png"/>' class="tamanio_imagen">
														</a>	
													</div>									
												</sec:authorize>
											</div>	
								            <div class="col-xs-8">
											</div>
											<div class="height50"></div>
											<p><fmt:message key="label.Type.road" />: <fmt:message key='${direccion.tipoVia}' /></p>
											<p><fmt:message key="label.Street" />: <c:out value="${direccion.nombreVia}" /></p>
											<p><fmt:message key="label.Number" />: <c:out value="${direccion.numero}" /></p>
											<p><fmt:message key="label.Rest" />: <c:out value="${direccion.resto}" /></p>
											<p><fmt:message key="label.Postal.code" />: <c:out value="${direccion.cp}" /></p>
											<p><fmt:message key="label.Province" />: <c:out value="${direccion.provincia}" /></p>
											<p><fmt:message key="label.City" />: <c:out value="${direccion.ciudad}" /></p>
											<p><fmt:message key="label.Country" />: <c:out value="${direccion.pais[nameColSelect]}" /></p>
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