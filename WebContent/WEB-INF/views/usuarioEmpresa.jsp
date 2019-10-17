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
	<title><fmt:message key="Companies" /></title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<script type="text/javascript" src='<c:url value="/resources/js/jquery.js" />'></script>
	<link href="<c:url value='/resources/bootstrap-4.3.1-dist/css/bootstrap.min.css'/>" rel="stylesheet" type="text/css" />
	<link href="<c:url value='/resources/css/menu.css'/>" rel="stylesheet" type="text/css" />
	<link href="<c:url value='/resources/css/tablas.css'/>" rel="stylesheet" type="text/css" />
	<script type="text/javascript">
		function confirmDelete(idUsr,idEmp){
			if(confirm("<fmt:message key='Delete.message' />")){
				var url = "<c:url value='/usuarioEmpresa/usuario/delete/"+idUsr+"/"+idEmp+"' />";
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
		<h2><fmt:message key="label.companies.belongs.1" /> <c:out value="${usuario.datosPersonales.nombre}" /> <c:out value="${usuario.datosPersonales.apellido1}" /> <c:out value="${usuario.datosPersonales.apellido2}" /> <fmt:message key="label.companies.belongs.2" /></h2>
	</div>
	<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">
		<div class="form-row">		
			<div class="col-sm-3">
			</div>
			<div class="col-xs-12 col-sm-9">
				<sf:form method="post" action="${pageContext.request.contextPath}/usuarioEmpresa/usuario/save/${usuario.idUsr}" modelAttribute="auxString">
					<div class="p-4">
			        	<sf:select path="campo" class="form-control-dam">
			            	<sf:options items="${empresas}" itemValue="idEmp" itemLabel="nombreComercial" />
			        	</sf:select>
						<button type="submit" class="btn fondo-c0c0c0 border-color-dam">
							<fmt:message key="Add.company" />
						</button>
						<c:if test="${error}">
							<span><fmt:message key="label.Company.already.asigned" /></span>
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
					    <tr class="border-1px-ddd" title='<fmt:message key="label.vat" />: <c:out value="${usuarioEmpresa.empresa.cif}" />&#xA;<fmt:message key="label.Limited.company" />: <c:out value="${usuarioEmpresa.empresa.tipoSociedad}" />&#xA;<fmt:message key="label.Email" />: <c:out value="${usuarioEmpresa.empresa.email}" />&#xA;<fmt:message key="label.Phone" />: <c:out value="${usuarioEmpresa.empresa.telefono}" />&#xA;<fmt:message key="label.Fax" />: <c:out value="${usuarioEmpresa.empresa.fax}" />&#xA;<fmt:message key="label.Web.page" />: <c:out value="${usuarioEmpresa.empresa.paginaWeb}" />&#xA;<fmt:message key="label.Activity" />: <c:out value="${usuarioEmpresa.empresa.actividad}" />&#xA;<c:out value="${usuarioEmpresa.empresa.observaciones}" />'>
					    	<td>
						    	* <c:out value="${usuarioEmpresa.empresa.nombreComercial}" />
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
	<script type="text/javascript" src="<c:url value='/resources/js/tabla_ordenar.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/bootstrap-4.3.1-dist/js/bootstrap.min.js'/>"></script>
</body>
</html>