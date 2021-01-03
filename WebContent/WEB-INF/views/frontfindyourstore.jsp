<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- language maneja el idioma actual --%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.damian.utils.multilanguage" />

<html>
<head>
	<title><fmt:message key="label.Find.your.strore" /></title>
	<c:import url="/WEB-INF/views/importHead.jsp" />
	<script type="text/javascript">
		function muestroMapa(carpeta,foto) {
			var url = "<c:url value='/resources/imgs/empresaPropias/"+carpeta+"/"+foto+"'/>";
			document.getElementById('fotoMapa').src = url;
		}		
	</script>

</head>
<body class="${prefUsr.tema}fondopantalla">
	<div class="container">
		<c:import url="/WEB-INF/views/menu.jsp" />
		<div class="well well-sm text-center h2 ${prefUsr.tema}titulo"><fmt:message key="label.Find.your.strore" /></div>
		
		<c:import url="/WEB-INF/views/divSearchBar.jsp" />
		
		<div class="row">
			<div class="col-xs-12">
				<div class="row">
					<div class="col-xs-6">
						<c:set var="count" value="0" scope="page" />
						<c:forEach items="${empresaPropias}" var="empresa">
							<div class="col-xs-12">
								<div class="border-radius-10 foncoEncuentraTienda ${prefUsr.tema}titulo paddingEncuentraTienda cursor-pointer" onclick="muestroMapa(${empresaPropias[count].idPropia},'${empresaPropias[count].foto.nombre}')">
									<h2><c:out value="${empresa.razonSocial}" /></h2>
									<h4><fmt:message key='${empresa.direccionEmpresaPropia.tipoVia}' /> <c:out value="${empresa.direccionEmpresaPropia.nombreVia}" /> <c:out value="${empresa.direccionEmpresaPropia.numero}" /> <c:out value="${empresa.direccionEmpresaPropia.resto}" /></h4>
									<h4>(<c:out value="${empresa.direccionEmpresaPropia.cp}" />) <c:out value="${empresa.direccionEmpresaPropia.ciudad}" /></h4>
									<h4><c:out value="${empresa.direccionEmpresaPropia.provincia}" /></h4>
									<h4><c:out value="${empresa.telefono}" /></h4>
								</div>
							</div>
							<c:set var="count" value="${count + 1}" scope="page"/>
						</c:forEach>
					</div>
					<div class="col-xs-6">
						<img class="w-100" src='<c:url value="/resources/imgs/empresaPropias/${empresaPropias[0].idPropia}/${empresaPropias[0].foto.nombre}"/>' alt="mapa" id="fotoMapa">
					</div>
				</div>
			</div>
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