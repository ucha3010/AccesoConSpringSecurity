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
	<title><fmt:message key="Products" /></title>
	<c:import url="/WEB-INF/views/importHead.jsp" />
	<script type="text/javascript">
		function confirmDelete(idPro,idEmp){
			if(confirm("<fmt:message key='Delete.message' />")){
				var url = "<c:url value='/productoEmpresa/empresa/delete/"+idPro+"/"+idEmp+"' />";
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
		<div class="well well-sm text-center h2 ${prefUsr.tema}titulo"><c:out value="${empresa.nombreComercial}"></c:out></div>
		<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">
			<div class="row">		
				<div class="hidden-xs col-sm-3">
				</div>
				<div class="col-xs-12 col-sm-9">
					<sf:form method="post" action="${pageContext.request.contextPath}/productoEmpresa/empresa/save/${empresa.idEmp}" modelAttribute="auxString">
						<div class="p-4">
				        	<sf:select path="campo" class="form-control-dam">
				        		<c:forEach items="${productos}" var="producto">
				        			<c:set var="nombreProducto" value="${producto[nameColSelect]}" scope="page"/>
				            		<sf:option value="${producto.idPro}" label="${nombreProducto}"/>
				            	</c:forEach>
				        	</sf:select>
							<button type="submit" class="btn fondo-c0c0c0 border-color-dam ${prefUsr.tema}botonagregar">
								<fmt:message key="Add.product" />
							</button>
							<c:if test="${error}">
								<span><fmt:message key="label.Product.already.asigned" /></span>
							</c:if>
							<c:if test="${productoEmpresa_deleted}">
								<span><fmt:message key="label.Deleted" /></span>
							</c:if>
						</div>
					</sf:form>	
				</div>
			</div>
			<br>
		</sec:authorize>
		<div class="row">
			<div class="hidden-xs col-sm-1 col-md-2">
			</div>
			<div class="col-xs-12 col-sm-10 col-md-8">
				<div class="divTablaSinScroll">
					<table class="table table-striped">
						<tbody>
							<c:forEach items="${productoEmpresas}" var="productoEmpresa">
						    	<tr title='<fmt:message key="label.state" />: <fmt:message key="${productoEmpresa.producto.estado}" />&#xA;<fmt:message key="label.brand" />: <c:out value="${productoEmpresa.producto.marca}" />&#xA;<fmt:message key="label.model" />: <c:out value="${productoEmpresa.producto.modelo}" />&#xA;<fmt:message key="label.salePrice" />: <fmt:formatNumber type="currency" value="${productoEmpresa.producto.precioVenta}" />&#xA;<fmt:message key="label.units" />: <c:out value="${productoEmpresa.producto.unidades}" />&#xA;<fmt:message key="label.Category" />: <c:out value="${productoEmpresa.producto.subcategoria.categoria[nameColSelect]}" />&#xA;<fmt:message key="label.Subcategory" />: <c:out value="${productoEmpresa.producto.subcategoria[nameColSelect]}" />'>
									<td class="extraAdmin-td">
										<a title="<fmt:message key="Products" />" onclick='location.href="<c:url value='/producto/filtered/${productoEmpresa.producto.idPro}' />"'>
											<img src='<c:url value="/resources/imgs/productos.png"/>' class="tamanio_imagen">
										</a>
										<a title="<fmt:message key='Delete' />" onclick="return confirmDelete(${productoEmpresa.producto.idPro},${productoEmpresa.empresa.idEmp})">
											<img src='<c:url value="/resources/imgs/borrar.png"/>' class="tamanio_imagen">
										</a>
									</td>
							    	<td>
								    	<c:out value="${productoEmpresa.producto[nameColSelect]}" />
							    	</td>
							    </tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<div class="hidden-xs col-sm-1 col-md-2">
			</div>
		</div>
	</div>
	
	<footer>
		<c:import url="/WEB-INF/views/importFooter.jsp" />
	</footer>
</body>
</html>