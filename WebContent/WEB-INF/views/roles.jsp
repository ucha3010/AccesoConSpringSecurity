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
	<title><fmt:message key='label.Roles' /></title>
	<c:import url="/WEB-INF/views/importHead.jsp" />
	
	<script type="text/javascript">
		function confirmDelete(idRol){
			if(confirm("<fmt:message key='Delete.message' />")){
				var url = "<c:url value='/rol/delete/"+idRol+"' />";
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
		<div class="well well-sm text-center h2"><fmt:message key="label.Roles" /></div>
		<sec:authorize access="hasAnyRole('ROL_ROOT')">
			<div class="row">
				<div class="hidden-xs col-sm-1">
				</div>
				<div class="col-xs-12 col-sm-5 text-center">
					<c:if test="${not empty rol_agregado}">
						<span style="color: green;">
							<fmt:message key="Rol.added" />
						</span>
					</c:if>
					<c:if test="${not empty rol_asociado}">
						<span style="color: red;">
							<fmt:message key="error.Rol.asociated" />
						</span>
					</c:if>
					<c:if test="${not empty rol_eliminado}">
						<span style="color: green;">
							<fmt:message key="Rol.deleted" />
						</span>
					</c:if>
				</div>
				<div class="col-xs-12 col-sm-6">
					<button type="button" class="btn fondo-c0c0c0 float-right ml-1 border-color-dam" onclick='location.href="<c:url value='/rol/0'/>"'>
						<fmt:message key="Add.rol" />
					</button>
				</div>	
			</div>
		</sec:authorize>
		<div class="form-row">
			<div class="hidden-xs col-sm-2 col-md-3">
			</div>
			<div class="col-xs-12 col-sm-8 col-md-6">
				<div class="divTablaSinScroll">
					<table class="table table-striped">
						<thead>
							<tr>
								<sec:authorize access="hasAnyRole('ROL_ROOT')">
									<th class="extraAdmin-th"></th>
								</sec:authorize>
								<th><fmt:message key="label.Roles" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${roles}" var="rol">
							    <tr>
									<sec:authorize access="hasAnyRole('ROL_ROOT')">
										<td class="extraAdmin-td">
											<a title="<fmt:message key='Edit' />" onclick='location.href="<c:url value='/rol/${rol.idRol}' />"'>
												<img src='<c:url value="/resources/imgs/editar.png"/>' class="tamanio_imagen">
											</a>
											<a title="<fmt:message key='Delete' />" onclick="return confirmDelete(${rol.idRol})">
												<img src='<c:url value="/resources/imgs/borrar.png"/>' class="tamanio_imagen">
											</a>
										</td>
									</sec:authorize>
									<td><c:out value="${rol.rol}" /></td>
							    </tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<div class="hidden-xs col-sm-2 col-md-3">
			</div>
		</div>
		
		<footer>
			<c:import url="/WEB-INF/views/importFooter.jsp" />
		</footer>
	</div>
</body>
</html>