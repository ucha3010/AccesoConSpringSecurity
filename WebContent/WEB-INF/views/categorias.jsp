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
	<c:import url="/WEB-INF/views/importHead.jsp" />
		
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
	<div class="container-fluid">
		<c:import url="/WEB-INF/views/menu.jsp" />
		<div class="well well-sm text-center h2"><fmt:message key="label.Categories.and.subcategories" /></div>
		<fmt:message key="language.name" var="nameColSelect"/>
		<sec:authorize access="hasAnyRole('ROL_ROOT')">
			<div class="row">
				<div class="hidden-xs col-sm-3">
				</div>
				<div class="col-xs-12 col-sm-3">
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
				<div class="col-xs-12 col-sm-6">
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
			<div class="hidden-xs hidden-sm hidden-md col-lg-2">
			</div>
			<div class="col-xs-12 col-lg-8">
				<div class="divTablaSinScroll">
					<table class="table table-striped">
						<thead>
							<tr>
								<sec:authorize access="hasAnyRole('ROL_ROOT','ROL_ADMIN')">
									<th class="extraAdmin-th"></th>
								</sec:authorize>
								<th><fmt:message key="label.Categories" /></th>
								<sec:authorize access="hasAnyRole('ROL_ROOT','ROL_ADMIN')">
									<th class="extraAdmin-th"></th>
								</sec:authorize>
								<th><fmt:message key="label.Subcategories" /></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${categorias}" var="categoria">
								<c:set var="subcategorias" value="${categoria.subcategorias}" scope="page" />
								
								<c:if test="${empty subcategorias}">
									<tr>
										<sec:authorize access="hasAnyRole('ROL_ROOT','ROL_ADMIN')">
											<td class="extraAdmin-td">
												<a title="<fmt:message key='Edit' />" onclick='location.href="<c:url value='/categoria/${categoria.idCat}' />"'>
													<img src='<c:url value="/resources/imgs/editar.png"/>' class="tamanio_imagen">
												</a>
												<a title="<fmt:message key='Delete' />" onclick="return confirmDelete(${categoria.idCat})">
													<img src='<c:url value="/resources/imgs/borrar.png"/>' class="tamanio_imagen">
												</a>
											</td>
										</sec:authorize>
										<td><c:out value="${categoria[nameColSelect]}" /></td>
										<sec:authorize access="hasAnyRole('ROL_ROOT','ROL_ADMIN')">
											<td></td>
										</sec:authorize>
										<td></td>
										<td></td>
									</tr>
								</c:if>
								
								<c:set var="repetido" value="false" scope="page" />
								<c:forEach items="${subcategorias}" var="subcategoria">
								    <tr>
									    <c:if test="${repetido}">
											<sec:authorize access="hasAnyRole('ROL_ROOT','ROL_ADMIN')">
												<td></td>
											</sec:authorize>
											<td></td>
										</c:if>
									    <c:if test="${not repetido}">
											<sec:authorize access="hasAnyRole('ROL_ROOT','ROL_ADMIN')">
												<td class="extraAdmin-td">
													<a title="<fmt:message key='Edit' />" onclick='location.href="<c:url value='/categoria/${categoria.idCat}' />"'>
														<img src='<c:url value="/resources/imgs/editar.png"/>' class="tamanio_imagen">
													</a>
													<a title="<fmt:message key='Delete' />" onclick="return confirmDelete(${categoria.idCat})">
														<img src='<c:url value="/resources/imgs/borrar.png"/>' class="tamanio_imagen">
													</a>
												</td>
											</sec:authorize>
											<td><c:out value="${categoria[nameColSelect]}" /></td>
											<c:set var="repetido" value="true" scope="page" />
										</c:if>
										<sec:authorize access="hasAnyRole('ROL_ROOT','ROL_ADMIN')">
											<td class="extraAdmin-td">
												<a title="<fmt:message key='Edit' />" onclick='location.href="<c:url value='/subcategoria/${subcategoria.idSub}' />"'>
													<img src='<c:url value="/resources/imgs/editar.png"/>' class="tamanio_imagen">
												</a>
												<a title="<fmt:message key='Delete' />" onclick="return confirmDeleteSub(${subcategoria.idSub})">
													<img src='<c:url value="/resources/imgs/borrar.png"/>' class="tamanio_imagen">
												</a>
											</td>
										</sec:authorize>
										<td><c:out value="${subcategoria[nameColSelect]}" /></td>
										<c:set var="productos" value="${subcategoria.productos}" scope="page" />
										<td>
											<c:if test="${not empty subcategoria.productos}">
												<select name="selectProducto" class="border-radius-dam">
													<c:forEach items="${productos}" var="producto">
														<option value="${producto.idPro}"><c:out value="${producto[nameColSelect]}" /></option>
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
			<div class="hidden-xs hidden-sm hidden-md col-lg-2">
			</div>
		</div>
		
		<footer>
			<c:import url="/WEB-INF/views/importFooter.jsp" />
		</footer>
	</div>
</body>
</html>