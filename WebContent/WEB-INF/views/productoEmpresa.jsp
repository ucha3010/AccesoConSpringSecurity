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
	<c:import url="/WEB-INF/views/importHead.jsp" />
	<script type="text/javascript">
		function confirmDelete(idPro,idEmp){
			if(confirm("<fmt:message key='Delete.message' />")){
				var url = "<c:url value='/productoEmpresa/producto/delete/"+idPro+"/"+idEmp+"' />";
				location.href=url;
				return true;
			}
			return false;
		}
	</script>
</head>
<body>
	<div class="container">
		<c:import url="/WEB-INF/views/menu.jsp" />
		<fmt:message key="language.name" var="nameColSelect"/>
		<div class="well well-sm text-center h2"><c:out value="${producto[nameColSelect]}"></c:out></div>
		<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">
			<div class="row">		
				<div class="hidden-xs col-sm-3">
				</div>
				<div class="col-xs-12 col-sm-9">
					<sf:form method="post" action="${pageContext.request.contextPath}/productoEmpresa/producto/save/${producto.idPro}" modelAttribute="auxString">
						<div class="p-4">
				        	<sf:select path="campo" class="form-control-dam">
				        		<c:forEach items="${empresas}" var="empresa">
				            		<sf:option value="${empresa.idEmp}" label="${empresa.nombreComercial}"/>
				            	</c:forEach>
				        	</sf:select>
							<button type="submit" class="btn fondo-c0c0c0 border-color-dam">
								<fmt:message key="Add.company" />
							</button>
							<c:if test="${error}">
								<span><fmt:message key="label.Company.already.asigned" /></span>
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
						<c:forEach items="${productoEmpresas}" var="productoEmpresa">
						    <tr class="border-1px-ddd" title='<fmt:message key="label.vat" />: <c:out value="${productoEmpresa.empresa.cif}" />&#xA;<fmt:message key="label.Email" />: <c:out value="${productoEmpresa.empresa.email}" />&#xA;<fmt:message key="label.Phone" />: <c:out value="${productoEmpresa.empresa.telefono}" />'>
								<td class="extraAdmin-td">
									<a title="<fmt:message key="Companies" />" onclick='location.href="<c:url value='/empresa/filtered/${productoEmpresa.empresa.idEmp}' />"'>
										<img src='<c:url value="/resources/imgs/empresa.png"/>' class="tamanio_imagen">
									</a>
									<a title="<fmt:message key='Delete' />" onclick="return confirmDelete(${productoEmpresa.producto.idPro},${productoEmpresa.empresa.idEmp})">
										<img src='<c:url value="/resources/imgs/borrar.png"/>' class="tamanio_imagen">
									</a>
								</td>
						    	<td>
							    	<c:out value="${productoEmpresa.empresa.nombreComercial}" />
						    	</td>
						    </tr>
						</c:forEach>
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