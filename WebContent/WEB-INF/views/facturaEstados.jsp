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
	<title><fmt:message key="label.Bills" /></title>
	<c:import url="/WEB-INF/views/importHead.jsp" />
	
	<script type="text/javascript" src='<c:url value="/resources/js/utiles.js" />'></script>
	<script type="text/javascript">
		function enviarCambio(id,numero){
			document.getElementById("id").value = id;
			document.getElementById("observaciones").value = document.getElementById("observaciones" + numero).value;
			return true;
		}
		
// 		function actualizaEstado(idFac) {
// 			var valSelected = document.getElementById("estadoId" + idFac);
// 			var url = "<c:url value='/factura/status/"+idFac+"/" + valSelected.value + "' />";
// 			location.href=url;
			
// 		}
		
		
// 		function cambioTamanio(elemento){
// 			$(elemento).on('input', function() {
// 				var scroll_height = $(elemento).get(0).scrollHeight;

// 				$(elemento).css('height', scroll_height + 'px');
// 			});
// 		}
		             
		$(document)
	    .one('focus.autoExpand', 'textarea.autoExpand', function(){
	        var savedValue = this.value;
	        this.value = '';
	        this.baseScrollHeight = this.scrollHeight;
	        this.value = savedValue;
	    })
	    .on('input.autoExpand', 'textarea.autoExpand', function(){
	        var minRows = this.getAttribute('data-min-rows')|0, rows;
	        this.rows = minRows;
	        rows = Math.ceil((this.scrollHeight - this.baseScrollHeight) / 30);
	        this.rows = minRows + rows;
	    });
		
	</script>
</head>
<body>
	<div class="container-fluid">
		<c:import url="/WEB-INF/views/menu.jsp" />
		<fmt:message key="language.name" var="nameColSelect"/>
		<div class="row">
			<div class="hidden-xs col-sm-1">
			</div>
			<div class="col-xs-12 col-sm-7">
				<h2><fmt:message key="label.Bill.id" />: ${factura.idFac}</h2>
			</div>
			<div class="col-xs-12 col-sm-4">
				<c:if test="${not empty facturaEstado_id_observaciones}">
					<span style="color: green;">
						<fmt:message key="label.observations.updated" />
					</span>
				</c:if>
			</div>		
		</div>
		<div class="row">
			<div class="hidden-xs col-sm-1">
			</div>
			<div class="col-xs-12 col-sm-10">
				<div class="divTablaSinScroll">
					<table class="table table-striped">
						<sf:form method="post" action="${pageContext.request.contextPath}/facturaEstado/update/observaciones" modelAttribute="facturaEstado">
							<sf:hidden path="id"/>
							<sf:hidden path="observaciones"/>
							<thead>
								<tr>
									<th class="text-center"><fmt:message key="label.state" /></th>
									<th class="text-center"><fmt:message key="label.Creation.date" /></th>
									<th class="text-center"><fmt:message key="label.Creator" /></th>
									<th class="text-center"><fmt:message key="label.Observations" /></th>
									<th class="width-50"><fmt:message key="label.Extras" /></th>
								</tr>
							</thead>
							<tbody>
								<c:set var="sizeCount" value="0" scope="page" />
								<c:forEach items="${facturaEstados}" var="fe">
								    <tr title='<c:out value="${fe.observaciones}" />'>
										<td class="text-center"><c:out value="${fe.estado[nameColSelect]}" /></td>
										<td class="text-center"><fmt:formatDate value="${fe.fecha}" pattern="dd/MM/yyyy HH:mm:ss"/></td>
										<td class="text-center"><c:out value="${fe.creadoPor}" /></td>
										<td class="text-center"><textarea id="observaciones${sizeCount}" name="fe.observaciones" rows='1' data-min-rows='1' class='autoExpand'><c:out value="${fe.observaciones}" /></textarea></td>
										<td><button type="submit" class="btn btn-primary" onclick="enviarCambio(${fe.id},${sizeCount})"><fmt:message key="label.Update" /></button></td>
								    </tr>
								    <c:set var="sizeCount" value="${sizeCount + 1}" scope="page"/>
								</c:forEach>
							</tbody>
						</sf:form>
					</table>
				</div>
			</div>
		</div>
	</div>
	
	<footer>
		<c:import url="/WEB-INF/views/importFooter.jsp" />
	</footer>
</body>
</html>