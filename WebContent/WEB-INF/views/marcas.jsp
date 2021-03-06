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
	<title><fmt:message key='label.states' /></title>
	<c:import url="/WEB-INF/views/importHead.jsp" />
	
	<script type="text/javascript">
		function confirmDelete(idEst){
			if(confirm("<fmt:message key='Delete.message' />")){
				var url = "<c:url value='/marca/delete/"+idEst+"' />";
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
		<div class="well well-sm text-center h2 ${prefUsr.tema}titulo"><fmt:message key="label.Brands" /></div>
		<fmt:message key="language.name" var="nameColSelect"/>
		<sec:authorize access="hasAnyRole('ROL_ROOT')">
			<div class="row">
				<div class="hidden-xs col-sm-1">
				</div>
				<div class="col-xs-12 col-sm-5 text-center">
					<c:if test="${not empty marca_agregado}">
						<span style="color: green;">
							<fmt:message key="Brand.added" />
						</span>
					</c:if>
					<c:if test="${not empty marca_asociado}">
						<span style="color: red;">
							<fmt:message key="error.brand.asociated" />
						</span>
					</c:if>
					<c:if test="${not empty marca_eliminado}">
						<span style="color: green;">
							<fmt:message key="Brand.deleted" />
						</span>
					</c:if>
				</div>
				<div class="col-xs-12 col-sm-6">
					<button type="button" class="btn fondo-c0c0c0 float-right ml-1 border-color-dam ${prefUsr.tema}botonagregar" onclick='location.href="<c:url value='/marca/0'/>"'>
						<fmt:message key="Add.brand" />
					</button>
				</div>	
			</div>
		</sec:authorize>
		<div class="form-row">
			<div class="hidden-xs col-sm-2 col-md-3">
			</div>
			<div class="col-xs-12 col-sm-8 col-md-6">
				<div class="divTablaSinScroll">
					<table id="tablaOrdenar" class="table table-striped">
						<thead>
							<tr>
								<sec:authorize access="hasAnyRole('ROL_ROOT')">
									<th class="extraAdmin-th"></th>
								</sec:authorize>
								<th><fmt:message key="label.brand" /></th>
								<th class="min-width-160 cursor-text zindex-100" colspan="2"><fmt:message key="label.Pictures" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${marcas}" var="marca">
							    <tr>
									<sec:authorize access="hasAnyRole('ROL_ROOT')">
										<td class="extraAdmin-td">
											<a title="<fmt:message key='Edit' />" onclick='location.href="<c:url value='/marca/${marca.idMar}' />"'>
												<img src='<c:url value="/resources/imgs/editar.png"/>' class="tamanio_imagen">
											</a>
											<a title="<fmt:message key='Delete' />" onclick="return confirmDelete(${marca.idMar})">
												<img src='<c:url value="/resources/imgs/borrar.png"/>' class="tamanio_imagen">
											</a>
										</td>
									</sec:authorize>
									<td><c:out value="${marca.nombre}" /></td>
									<td class="extraAdmin-td">
										<a title="<fmt:message key='label.Pictures' />" href='<c:url value='/foto/marca/${marca.idMar}' />'>
											<span class="glyphicon glyphicon-picture tamanio_imagen zindex-1"></span>
										</a>
									</td>
									<td class="extraAdmin-td">
										<c:if test="${(not empty marca.foto)}">
											<a href="#foto${marca.idMar}" data-toggle="modal">
												<img src='<c:url value="/resources/imgs/marcas/${marca.idMar}/${marca.foto.nombre}"/>' class="width-50">
											</a>
											<div class="modal fade" id="foto${marca.idMar}">
												<div class="modal-dialog">
													<div class="modal-content">
														<div class="modal-header">
															<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
															<h4 class="modal-title"><img src='<c:url value="/resources/imgs/marcas/${marca.idMar}/${marca.foto.nombre}"/>' class="justify-content-center"></h4>
														</div>
													</div>
												</div>
											</div>
										</c:if>
									</td>
							    </tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<div class="hidden-xs col-sm-2 col-md-3">
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