<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Index 3</title>
	<c:import url="/WEB-INF/views/importHead.jsp" />
</head>
<body class="${prefUsr.tema}fondopantalla">
	<c:import url="/WEB-INF/views/menu.jsp" />
	<h1>Index 3</h1>

	<a href='<c:url value="/about"/>'>Acerca de</a><br/>	
	<a href='<c:url value="/index2"/>'>Index2</a><br/>
	<br/>
	Atributos del Model: <c:out value="${mensaje}" /><br/>
	Atributos en Session (estoy): <c:out value="${sessionScope.estoy}" /><br/>
	Atributos en Session (resultado): <c:out value="${sessionScope.resultado}" /><br/>
	Atributos en Session (nombre): <c:out value="${sessionScope.nombre}" /><br/>
	Atributos en Session (valor): <c:out value="${sessionScope.valor}" /><br/>
	<br/>
	<br/>
	<h2>Datos del formulario de Admin.jsp</h2>
	<c:out value="${adminForm}"></c:out><br/>
	<br/>
	<br/>
	 <div class="divTabla">
             <table id="tablaOrdenar" class="table table-striped table-margin-dam">
	  <thead>
	    <tr>
	      <th onclick="sortTable(0)">titulo 1</th>
	      <th onclick="sortTable(1)">label.Enabled</th>
	      <th onclick="sortTable(2)">label.Creation.date</th>
	      <th onclick="sortTable(3)">label.idcard</th>
	      <th onclick="sortTable(4)">label.Name</th>
	      <th onclick="sortTable(5)">label.Lastname</th>
	      <th onclick="sortTable(6)">label.Sex</th>
	      <th onclick="sortTable(7)">label.Birthdate</th>
	      <th onclick="sortTable(8)">label.Nationality</th>
	      <th onclick="sortTable(9)">label.Email</th>
	      <th onclick="sortTable(10)">label.Roles</th>
	      <th colspan="3">label.Extras</th>
	    </tr>
	  </thead>
                <tr><td>1</td><td>2</td><td>3</td><td>4</td><td>5</td><td>6</td><td>7</td></tr>
                <tr><td>1</td><td>2</td><td>3</td><td>4</td><td>5</td><td>6</td><td>7</td></tr>
                <tr><td>1</td><td>2</td><td>3</td><td>4</td><td>5</td><td>6</td><td>7</td></tr>
                <tr><td>1</td><td>2</td><td>3</td><td>4</td><td>5</td><td>6</td><td>7</td></tr>
                <tr><td>1</td><td>2</td><td>3</td><td>4</td><td>5</td><td>6</td><td>7</td></tr>
                <tr><td>1</td><td>2</td><td>3</td><td>4</td><td>5</td><td>6</td><td>7</td></tr>
                <tr><td>1</td><td>2</td><td>3</td><td>4</td><td>5</td><td>6</td><td>7</td></tr>
                <tr><td>1</td><td>2</td><td>3</td><td>4</td><td>5</td><td>6</td><td>7</td></tr>
                <tr><td>1</td><td>2</td><td>3</td><td>4</td><td>5</td><td>6</td><td>7</td></tr>
                <tr><td>1</td><td>2</td><td>3</td><td>4</td><td>5</td><td>6</td><td>7</td></tr>
                <tr><td>1</td><td>2</td><td>3</td><td>4</td><td>5</td><td>6</td><td>7</td></tr>
                <tr><td>1</td><td>2</td><td>3</td><td>4</td><td>5</td><td>6</td><td>7</td></tr>
                <tr><td>1</td><td>2</td><td>3</td><td>4</td><td>5</td><td>6</td><td>7</td></tr>
                <tr><td>1</td><td>2</td><td>3</td><td>4</td><td>5</td><td>6</td><td>7</td></tr>
                <tr><td>1</td><td>2</td><td>3</td><td>4</td><td>5</td><td>6</td><td>7</td></tr>
                <tr><td>1</td><td>2</td><td>3</td><td>4</td><td>5</td><td>6</td><td>7</td></tr>
                <tr><td>1</td><td>2</td><td>3</td><td>4</td><td>5</td><td>6</td><td>7</td></tr>
                <tr><td>1</td><td>2</td><td>3</td><td>4</td><td>5</td><td>6</td><td>7</td></tr>
            </table>
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