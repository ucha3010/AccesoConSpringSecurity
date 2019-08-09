<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Acceso a usuarios</h1>
	<c:if test="${param.error != null}">
		<span style="color:red;">�Error en credenciales!</span>
	</c:if>
	<form name='f' action="j_spring_security_check" method='post'>
		<table>
			<tr>
				<td>Usuario:</td>
				<td><input type='text' name='usuario' value='' /></td>
			</tr>
			<tr>
				<td>Contrase�a:</td>
				<td><input type='password' name='clave' value='' /></td>
			</tr>
			<tr>
				<td>Recordarme</td>
				<td><input type='checkbox' name='_spring_security_remember_me' checked='checked' /></td>
			</tr>
			<tr>
				<td><input name="submit" type="submit" value="Acceder" /></td>
			</tr>
		</table>
	</form>
</body>
</html>