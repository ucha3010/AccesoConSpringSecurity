<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- language maneja el idioma actual --%>
<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.damian.utils.multilanguage" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
	<h1><fmt:message key="label.Login.page" /></h1>
	<c:if test="${param.error != null}">
		<span style="color:red;"><fmt:message key="label.Invalid.credentials" /></span>
	</c:if>
	<form name='f' action="j_spring_security_check" method='post'>
		<table>
			<tr>
				<td><fmt:message key="label.User" /></td>
				<td><input type='text' name='usuario' value='' /></td>
			</tr>
			<tr>
				<td><fmt:message key="label.Password" /></td>
				<td><input type='password' name='clave' value='' /></td>
			</tr>
			<tr>
				<td><fmt:message key="label.Remember.me" /></td>
				<td><input type='checkbox' name='_spring_security_remember_me' checked='checked' /></td>
			</tr>
			<tr>
				<td><input name="submit" type="submit" value="<fmt:message key="label.Login" />" /></td>
			</tr>
		</table>
	</form>
</body>
</html>