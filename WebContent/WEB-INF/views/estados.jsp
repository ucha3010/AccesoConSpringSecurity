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
				var url = "<c:url value='/estado/delete/"+idEst+"' />";
				location.href=url;
				return true;
			}
			return false;
		}
	</script>
</head>
<body>
	<c:import url="/WEB-INF/views/menu.jsp" />
	<fmt:message key="language.name" var="nameColSelect"/>
	<sec:authorize access="hasAnyRole('ROL_ROOT')">
		<div class="d-flex justify-content-between">
			<div class="p-2">
			</div>
			<div class="p-2">
				<c:if test="${not empty estado_agregado}">
					<span style="color: green;">
						<fmt:message key="Estado.added" />
					</span>
				</c:if>
				<c:if test="${not empty estado_asociado}">
					<span style="color: red;">
						<fmt:message key="error.Estado.asociated" />
					</span>
				</c:if>
				<c:if test="${not empty estado_eliminado}">
					<span style="color: green;">
						<fmt:message key="Estado.deleted" />
					</span>
				</c:if>
			</div>
			<div class="p-2">
				<button type="button" class="btn fondo-c0c0c0 float-right ml-1 border-color-dam" onclick='location.href="<c:url value='/estado/0'/>"'>
					<fmt:message key="Add.estado" />
				</button>
			</div>	
			<div class="p-2">
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
							<c:set var="count" value="0" scope="page" />
							<sec:authorize access="hasAnyRole('ROL_ROOT')">
								<th colspan="2"></th>
								<c:set var="count" value="${count + 2}" scope="page"/>
							</sec:authorize>
							<th onclick="sortTable(${count})"><fmt:message key="label.states" /></th>
							<th class="width-100"><fmt:message key="label.Extras" /></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${estados}" var="estado">
						    <tr>
								<sec:authorize access="hasAnyRole('ROL_ROOT')">
									<td class="sin_padding">
										<button type="button" class="btn btn-default" title="<fmt:message key='Edit' />" onclick='location.href="<c:url value='/estado/${estado.idEst}' />"'>
										  <img src='<c:url value="/resources/imgs/editar.png"/>' class="tamanio_imagen">
										</button>
									</td>
									<td class="sin_padding">
										<button type="button" class="btn btn-default" title="<fmt:message key='Delete' />" onclick="return confirmDelete(${estado.idEst})" >
										  <img src='<c:url value="/resources/imgs/borrar.png"/>' class="tamanio_imagen">
										</button>
									</td>
								</sec:authorize>
								<td><c:out value="${estado[nameColSelect]}" /></td>
								<td class="sin_padding">
									<a title="<fmt:message key="label.Bills" />" href='<c:url value='/factura/filteredEstado/${estado.idEst}/null' />'>
										<img src='<c:url value="/resources/imgs/factura.png"/>' class="margin-left-5porciento width-35">
									</a>
								</td>
						    </tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>				
		<div class="col-sm-4">
		</div>
	</div>
	
	<footer>
		<c:import url="/WEB-INF/views/importFooter.jsp" />
	</footer>
</body>
</html>