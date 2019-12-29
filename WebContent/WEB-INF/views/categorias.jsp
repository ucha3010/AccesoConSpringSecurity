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
	<title><fmt:message key='label.Categories.and.subcategories' /></title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<script type="text/javascript" src='<c:url value="/resources/js/jquery.js" />'></script>
	<link href="<c:url value='/resources/bootstrap-4.3.1-dist/css/bootstrap.min.css'/>" rel="stylesheet" type="text/css" />
	<link href="<c:url value='/resources/css/menu.css'/>" rel="stylesheet" type="text/css" />
	<link href="<c:url value='/resources/css/tablas.css'/>" rel="stylesheet" type="text/css" />
	<script type="text/javascript">
		function confirmDelete(idCat){
			if(confirm("<fmt:message key='Delete.message' />")){
				var url = "<c:url value='/categoria/delete/"+idCat+"' />";
				location.href=url;
				return true;
			}
			return false;
		}
		function confirmDeleteSub(idSub){
			if(confirm("<fmt:message key='Delete.message' />")){
				var url = "<c:url value='/subcategoria/delete/"+idSub+"' />";
				location.href=url;
				return true;
			}
			return false;
		}
	</script>
</head>
<body>
	<c:import url="/WEB-INF/views/menu.jsp" />
	<sec:authorize access="hasAnyRole('ROL_ROOT')">
		<div class="d-flex justify-content-between">
			<div class="p-2">
			</div>
			<div class="p-2">
				<c:if test="${not empty categoria_agregado}">
					<span style="color: green;">
						<fmt:message key="Category.added" />
					</span>
				</c:if>
				<c:if test="${not empty categoria_asociado}">
					<span style="color: red;">
						<fmt:message key="error.Category.asociated" />
					</span>
				</c:if>
				<c:if test="${not empty categoria_eliminado}">
					<span style="color: green;">
						<fmt:message key="Category.deleted" />
					</span>
				</c:if>
				<c:if test="${not empty subcategoria_agregado}">
					<span style="color: green;">
						<fmt:message key="Subcategory.added" />
					</span>
				</c:if>
				<c:if test="${not empty subcategoria_asociado}">
					<span style="color: red;">
						<fmt:message key="error.Subcategory.asociated" />
					</span>
				</c:if>
				<c:if test="${not empty subcategoria_eliminado}">
					<span style="color: green;">
						<fmt:message key="Subcategory.deleted" />
					</span>
				</c:if>
			</div>
			<div class="p-2">
				<button type="button" class="btn fondo-c0c0c0 float-right ml-1 border-color-dam" onclick='location.href="<c:url value='/subcategoria/0'/>"'>
					<fmt:message key="Add.subcategory" />
				</button>
				<button type="button" class="btn fondo-c0c0c0 float-right ml-1 border-color-dam" onclick='location.href="<c:url value='/categoria/0'/>"'>
					<fmt:message key="Add.category" />
				</button>
			</div>		
		</div>
	</sec:authorize>
	<div class="form-row">		
		<div class="col-sm-4">
		</div>
		<div class="col-xs-12 col-sm-4">
			<div class="divTablaSinScroll">
				<table id="tablaOrdenar" class="table table-striped">
					<thead>
						<tr>
							<th><fmt:message key="label.Categories" /></th>
							<sec:authorize access="hasAnyRole('ROL_ROOT','ROL_ADMIN')">
								<th colspan="2"></th>
							</sec:authorize>
							<th><fmt:message key="label.Subcategories" /></th>
							<sec:authorize access="hasAnyRole('ROL_ROOT','ROL_ADMIN')">
								<th colspan="2"></th>
							</sec:authorize>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${categorias}" var="categoria">
							<c:set var="subcategorias" value="${categoria.subcategorias}" scope="page" />
							
							<c:if test="${empty subcategorias}">
								<tr>
									<td><c:out value="${categoria.nombre}" /></td>
									<sec:authorize access="hasAnyRole('ROL_ROOT','ROL_ADMIN')">
										<td class="sin_padding">
											<button type="button" class="btn btn-default" title="<fmt:message key='Edit' />" onclick='location.href="<c:url value='/categoria/${categoria.idCat}' />"'>
											  <img src='<c:url value="/resources/imgs/editar.png"/>' class="tamanio_imagen">
											</button>
										</td>
										<td class="sin_padding">
											<button type="button" class="btn btn-default" title="<fmt:message key='Delete' />" onclick="return confirmDelete(${categoria.idCat})" >
											  <img src='<c:url value="/resources/imgs/borrar.png"/>' class="tamanio_imagen">
											</button>
										</td>
									</sec:authorize>
									<td></td>
									<sec:authorize access="hasAnyRole('ROL_ROOT','ROL_ADMIN')">
										<td colspan="2"></td>
									</sec:authorize>
									<td></td>
								</tr>
							</c:if>
							
							<c:set var="repetido" value="false" scope="page" />
							<c:forEach items="${subcategorias}" var="subcategoria">
							    <tr>
								    <c:if test="${repetido}">
										<td></td>
										<sec:authorize access="hasAnyRole('ROL_ROOT','ROL_ADMIN')">
											<td></td><td></td>
										</sec:authorize>
									</c:if>
								    <c:if test="${not repetido}">
										<td><c:out value="${categoria.nombre}" /></td>
										<sec:authorize access="hasAnyRole('ROL_ROOT','ROL_ADMIN')">
											<td class="sin_padding">
												<button type="button" class="btn btn-default" title="<fmt:message key='Edit' />" onclick='location.href="<c:url value='/categoria/${categoria.idCat}' />"'>
												  <img src='<c:url value="/resources/imgs/editar.png"/>' class="tamanio_imagen">
												</button>
											</td>
											<td class="sin_padding">
												<button type="button" class="btn btn-default" title="<fmt:message key='Delete' />" onclick="return confirmDelete(${categoria.idCat})" >
												  <img src='<c:url value="/resources/imgs/borrar.png"/>' class="tamanio_imagen">
												</button>
											</td>
										</sec:authorize>
										<c:set var="repetido" value="true" scope="page" />
									</c:if>
									<td><c:out value="${subcategoria.nombre}" /></td>
									<sec:authorize access="hasAnyRole('ROL_ROOT','ROL_ADMIN')">
										<td class="sin_padding">
											<button type="button" class="btn btn-default" title="<fmt:message key='Edit' />" onclick='location.href="<c:url value='/subcategoria/${subcategoria.idSub}' />"'>
											  <img src='<c:url value="/resources/imgs/editar.png"/>' class="tamanio_imagen">
											</button>
										</td>
										<td class="sin_padding">
											<button type="button" class="btn btn-default" title="<fmt:message key='Delete' />" onclick="return confirmDeleteSub(${subcategoria.idSub})" >
											  <img src='<c:url value="/resources/imgs/borrar.png"/>' class="tamanio_imagen">
											</button>
										</td>
									</sec:authorize>
									<c:set var="productos" value="${subcategoria.productos}" scope="page" />
									<td>
										<c:if test="${not empty subcategoria.productos}">
											<select name="selectProducto" class="border-radius-dam">
												<c:forEach items="${productos}" var="producto">
													<option value="${producto.idPro}"><c:out value="${producto.descripcion}" /></option>
												</c:forEach>
											</select>
										</c:if>
									</td>
							    </tr>
							</c:forEach>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>				
		<div class="col-sm-4">
		</div>
	</div>
	<script type="text/javascript" src="<c:url value='/resources/js/tabla_ordenar.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/bootstrap-4.3.1-dist/js/bootstrap.min.js'/>"></script>
</body>
</html>