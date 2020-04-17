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
	<title><fmt:message key="label.Users" /></title>
	<c:import url="/WEB-INF/views/importHead.jsp" />
	
	<script type="text/javascript">
		function confirmDelete(idUsr,idEmp){
			if(confirm("<fmt:message key='Delete.message' />")){
				var url = "<c:url value='/usuarioEmpresa/empresa/delete/"+idUsr+"/"+idEmp+"' />";
				location.href=url;
				return true;
			}
			return false;
		}
	</script>
</head>
<body>
	<c:import url="/WEB-INF/views/menu.jsp" />
	<div>
		<h2><fmt:message key="label.users.belong" /> <c:out value="${empresa.nombreComercial}" /></h2>
	</div>
	<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">
		<div class="form-row">		
			<div class="col-sm-3">
			</div>
			<div class="col-xs-12 col-sm-9">
				<sf:form method="post" action="${pageContext.request.contextPath}/usuarioEmpresa/empresa/save/${empresa.idEmp}" modelAttribute="auxString">
					<div class="p-4">
			        	<sf:select path="campo" class="form-control-dam">
			        		<c:forEach items="${usuarios}" var="usuarioFor">
			        			<c:set var="nombreAp" value="${usuarioFor.datosPersonales.nombre} ${usuarioFor.datosPersonales.apellido1} ${usuarioFor.datosPersonales.apellido2}" scope="page"/>
			            		<sf:option value="${usuarioFor.idUsr}" label="${nombreAp}"/>
			            	</c:forEach>
			        	</sf:select>
						<button type="submit" class="btn fondo-c0c0c0 border-color-dam">
							<fmt:message key="label.New.user" />
						</button>
						<c:if test="${error}">
							<span><fmt:message key="label.User.already.asigned" /> (<c:out value="${existia}"></c:out>)</span>
						</c:if>
					</div>
				</sf:form>	
			</div>
		</div>
	</sec:authorize>
	<div class="form-row">		
		<div class="col-sm-3">
		</div>
		<div class="col-xs-12 col-sm-9">
			<div class="divTabla margin-left-5porciento">
				<table id="tablaOrdenar" class="table-striped">
					<c:forEach items="${usuarioEmpresas}" var="usuarioEmpresa">
					    <tr class="border-1px-ddd" title='<fmt:message key="label.Birthdate" />: <fmt:formatDate value="${usuarioEmpresa.usuario.datosPersonales.fechaNacimiento}" pattern="dd/MM/yyyy"/>&#xA;<fmt:message key="label.idcard" />: <c:out value="${usuarioEmpresa.usuario.datosPersonales.dni}" />&#xA;<fmt:message key="label.Email" />: <c:out value="${usuarioEmpresa.usuario.datosPersonales.email}" />&#xA;<fmt:message key="label.Phone" />: <c:out value="${usuarioEmpresa.usuario.datosPersonales.telefono}" />&#xA;<c:out value="${usuarioEmpresa.usuario.datosPersonales.observaciones}" />'>
					    	<td>
						    	* <c:out value="${usuarioEmpresa.usuario.datosPersonales.nombre}" /> <c:out value="${usuarioEmpresa.usuario.datosPersonales.apellido1}" /> <c:out value="${usuarioEmpresa.usuario.datosPersonales.apellido2}" />
								<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">
									<td class="sin_padding">
										<button type="button" class="btn btn-default" title="<fmt:message key='Delete' />" onclick="return confirmDelete(${usuarioEmpresa.usuario.idUsr},${usuarioEmpresa.empresa.idEmp})">
										  <img src='<c:url value="/resources/imgs/borrar.png"/>' class="tamanio_imagen">
										</button>
									</td>
								</sec:authorize>
							</td>
					    </tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
	
	<footer>
		<c:import url="/WEB-INF/views/importFooter.jsp" />
	</footer>
</body>
</html>