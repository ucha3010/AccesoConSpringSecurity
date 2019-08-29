<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src='<c:url value="/resources/js/jquery.js" />'></script>
	<link href="<c:url value='/resources/bootstrap-4.3.1-dist/css/bootstrap.min.css'/>" rel="stylesheet" type="text/css" />
	<link href="<c:url value='/resources/css/menu.css'/>" rel="stylesheet" type="text/css" />
<script type="text/javascript">
jQuery(document).ready(function(){
	jQuery(".confirm").on("click", function(){
		return confirm("Si eliminas este elemento no se podrá recuperar. ¿Continuar?");
	})
});
</script>
</head>
<body>
	<c:import url="/WEB-INF/views/menu.jsp" />
	<h1>usuario.jsp</h1>
	<a href='<c:url value="/"/>'>Index</a><br/>
	<a href='<c:url value="/about"/>'>Acerca de</a><br/>
	<br/>
	<br/>
	<br/>
	<sf:form method="post" action="${pageContext.request.contextPath}/usuario/save" modelAttribute="usuario">
		<table>
			<tr>
				<td>Usuario</td>
				<td>
					<sf:input path="usuario" type="text"/>
					<sf:errors path="usuario" cssStyle="color:red"/>
				</td>
			</tr>
			<tr>
				<td>Contraseña</td>
				<td>
					<sf:input path="clave" type="text"/>
					<sf:errors path="clave" cssStyle="color:red"/>
				</td>
			</tr>
			<tr>
				<td>Permisos</td>
				<td>
					<sf:input path="fechaCreacion" type="text"/>
					<sf:errors path="fechaCreacion" cssStyle="color:red"/>
				</td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Guardar cambios" /></td>
			</tr>			
		</table>
	</sf:form>
	<br/>
	<c:out value="${resultado}"></c:out><br/><br/>
	
	<c:forEach items="${usuarios}" var="usuario">
		<c:out value="${usuario}" />
		<br/>
	</c:forEach>

<hr>
<br>
	<form>
		<div class="form-group">
			<label for="exampleFormControlInput1">Email address</label> <input
				type="text" class="form-control" id="exampleFormControlInput1">
		</div>
		<div class="form-group">
			<label for="exampleFormControlSelect1">Example select</label> <select
				class="form-control" id="exampleFormControlSelect1">
				<option>1</option>
				<option>2</option>
				<option>3</option>
				<option>4</option>
				<option>5</option>
			</select>
		</div>
		<div class="form-group">
			<label for="exampleFormControlSelect2">Example multiple
				select</label> <select multiple class="form-control"
				id="exampleFormControlSelect2">
				<option>1</option>
				<option>2</option>
				<option>3</option>
				<option>4</option>
				<option>5</option>
			</select>
		</div>
		<div class="form-group">
			<label for="exampleFormControlTextarea1">Example textarea</label>
			<textarea class="form-control" id="exampleFormControlTextarea1"
				rows="3"></textarea>
		</div>
		<div class="form-row">
			<div class="form-group col-md-6">
				<label for="inputEmail4">Email</label> <input type="text"
					class="form-control" id="inputEmail4">
			</div>
			<div class="form-group col-md-6">
				<label for="inputPassword4">Password</label> <input type="password"
					class="form-control" id="inputPassword4">
			</div>
		</div>
		<div class="form-group">
			<label for="inputAddress">Address</label> <input type="text"
				class="form-control" id="inputAddress">
		</div>
		<div class="form-group">
			<label for="inputAddress2">Address 2</label> <input type="text"
				class="form-control" id="inputAddress2">
		</div>
		<div class="form-row">
			<div class="form-group col-md-6">
				<label for="inputCity">City</label> <input type="text"
					class="form-control" id="inputCity">
			</div>
			<div class="form-group col-md-4">
				<label for="inputState">State</label> <select id="inputState"
					class="form-control">
					<option selected>Choose...</option>
					<option>...</option>
				</select>
			</div>
			<div class="form-group col-md-2">
				<label for="inputZip">Zip</label> <input type="text"
					class="form-control" id="inputZip">
			</div>
		</div>
		<div class="form-group">
			<div class="form-check">
				<input class="form-check-input" type="checkbox" id="gridCheck">
				<label class="form-check-label" for="gridCheck"> Check me
					out </label>
			</div>
		</div>
		<button type="submit" class="btn btn-primary">Sign in</button>
	</form>
</body>
</html>