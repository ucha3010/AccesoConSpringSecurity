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
	<title><fmt:message key="label.Receive.publicity" /></title>
	<c:import url="/WEB-INF/views/importHead.jsp" />	
</head>
<body class="${prefUsr.tema}fondopantalla">
	<div class="container-fluid">
		<c:import url="/WEB-INF/views/menu.jsp" />
		<c:if test="${(quieren_publicidad)}">
			<div class="well well-sm text-center h3 ${prefUsr.tema}titulo"><fmt:message key="label.Users.want.receive.publicity" /></div>
		</c:if>
		<c:if test="${(not quieren_publicidad)}">
			<div class="well well-sm text-center h3 ${prefUsr.tema}titulo"><fmt:message key="label.Users.dont.want.receive.publicity" /></div>
		</c:if>
		<fmt:message key="language.name" var="nameColSelect"/>
		<div class="divTablaSinScroll">
			<table class="table table-striped">
				<thead>
					<tr>
						<th><fmt:message key="label.Username" /></th>
						<th><fmt:message key="label.Name" /></th>
						<th><fmt:message key="label.Lastname" /></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${usuarios}" var="usuario">
					    <tr>
							<td><c:out value="${usuario.usuario}" /></td>							
							<td><c:out value="${usuario.datosPersonales.nombre}" /></td>
							<td><c:out value="${usuario.datosPersonales.apellido1}" /> <c:out value="${usuario.datosPersonales.apellido2}" /></td>
					    </tr>
					</c:forEach>
				</tbody>
			</table>
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