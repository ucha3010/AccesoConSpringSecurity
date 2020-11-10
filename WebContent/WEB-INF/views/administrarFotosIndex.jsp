<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<%-- language maneja el idioma actual --%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.damian.utils.multilanguage" />
<html>
<head>
	<title><fmt:message key="label.Pictures.of" /> <fmt:message key="label.slide" /></title>
	<c:import url="/WEB-INF/views/importHead.jsp" />
	
	<script type="text/javascript">
		function confirmDelete(idFot){
			if(confirm("<fmt:message key='Delete.message' />")){
				var url = "<c:url value='/foto/slide/delete/"+idFot+"' />";
				location.href=url;
				return true;
			}
			return false;
		}
	</script>
</head>
<body class="${prefUsr.tema}fondopantalla">
	<div class="container">
		<c:import url="/WEB-INF/views/menu.jsp" />
		<div class="well well-sm text-center h2 ${prefUsr.tema}titulo">
			<fmt:message key="label.Pictures.of" /> <fmt:message key="label.slide" />
		</div>
		<c:if test="${not empty foto_agregada}">
			<div class="row">	
				<div class="hidden-xs col-sm-2 col-md-4">
				</div>
				<div class="col-xs-12 col-sm-8 col-md-4">
					<c:if test="${foto_agregada}"><div class="alert alert-success text-center"><fmt:message key="Picture.added" /></div></c:if>
					<c:if test="${not foto_agregada}"><div class="alert alert-danger text-center"><fmt:message key="Picture.not.added" /></div></c:if>
				</div>
				<div class="hidden-xs col-sm-2 col-md-4">
				</div>
			</div>
			<br>
		</c:if>
		<div class="row">
			<c:forEach items="${fotos}" var="f">
				<div class="col-xs-12 col-sm-6 col-md-3">
					<div class="thumbnail">
						<img src='<c:url value="/resources/imgs/slide/0/${f.nombre}"/>' alt="${f.nombre}">
						<div class="caption">
							<p>${f.descripcion}</p>
						</div>
						<a title="<fmt:message key='Delete' />" onclick="return confirmDelete(${f.idFot})">
							<img src='<c:url value="/resources/imgs/borrar.png"/>' class="tamanio_imagen cursor-pointer">
						</a>
					</div>
				</div>				
			</c:forEach>
		</div>
		<br>
		<div class="row">	
			<div class="hidden-xs col-sm-3">
			</div>
			<div class="col-xs-12 col-sm-6">
				<h4><fmt:message key="label.Add.picture" /> <fmt:message key="label.for" /> <fmt:message key="label.slide" /></h4>
			</div>
			<div class="hidden-xs col-sm-3">
			</div>
		</div>
		
		<sf:form method="POST" action="${pageContext.request.contextPath}/foto/slide/save" enctype="multipart/form-data" modelAttribute="foto">
		
			<div class="row">
				<div class="hidden-xs col-sm-3">
				</div>
				<div class="col-xs-12 col-sm-6">
					<label for="fotoSlide"><fmt:message key="label.Select.file" /></label>
					<input type="file" class="form-control-file form-control ${prefUsr.tema}botonresto" id="fotoSlide" name="file" />
				</div>
				<div class="hidden-xs col-sm-3">
				</div>
			</div>
			
			<div class="row">
				<div class="hidden-xs col-sm-3">
				</div>
				<div class="col-xs-12 col-sm-6">
				</div>
				<div class="hidden-xs col-sm-3">
				</div>
			</div>
			
			<div class="row">
				<div class="hidden-xs col-sm-3">
				</div>
				<div class="col-xs-12 col-sm-6">
					<sf:hidden path="descripcion" value=""/>
<%-- 					<label for="descripcion"><fmt:message key="label.Description" /></label> --%>
<%-- 					<sf:textarea path="descripcion" class="form-control" rows="3" /> --%>
				</div>
				<div class="hidden-xs col-sm-3">
				</div>
			</div>
			<br>
			
			<div class="row">
				<div class="hidden-xs col-sm-3">
				</div>
				<div class="col-xs-12 col-sm-6">
					<button class="btn btn-success ${prefUsr.tema}botonguardar" type="submit" <c:if test="${fotos.size() == 4 }">disabled</c:if>><fmt:message key="Save" /></button>
					<button class="btn btn-default ${prefUsr.tema}botoncancelar" type="button" onclick='location.href="<c:url value="/administrar/configuracion/find/${usuario.idUsr}"/>"'><fmt:message key="label.Back" /></button>
				</div>
				<div class="hidden-xs col-sm-3">
				</div>
			</div>
		
		</sf:form>
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