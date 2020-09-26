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
	<title><fmt:message key="label.User.preferences" /></title>
	<c:import url="/WEB-INF/views/importHead.jsp" />
	
	<script type="text/javascript" src='<c:url value="/resources/js/validaciones.js" />'></script>
	<script type="text/javascript">
		function validar(){
			restablecer();
			var campo = ['tema','botonFavorito'];
			var validado = true;			
			for(var i=0; i < campo.length; i++){
			    var nombreRol = document.getElementById(campo[i]);
			    var nombreRolError = document.getElementById(campo[i]+'Error');
				if(nombreRol.value==''){
					nombreRolError.innerHTML = "<fmt:message key='error.field.not.empty' />";
					nombreRol.style.borderColor="red";
					validado = false;
				}
			}
			
			if(validado){
				return true;
			} else {
				document.getElementById('hayError').innerHTML = "<fmt:message key='error.any.field' />";
				return false;
			}
		}
		function restablecer(){
			var errorSpan = document.getElementsByName('errorSpan');
			for (var i = 0; i < errorSpan.length; i++) {
				errorSpan[i].innerHTML='';
			}
			var campos = document.getElementsByClassName("form-control");
			for (var i = 0; i < campos.length; i++) {
				campos[i].style.borderColor="#ced4da";
			}
		}
		
		function selectFavorito(dato,clave) {
			document.getElementById("imagenPrincipalFavoritos").src = '<c:url value="/resources/imgs/favoritos/' + dato + '.png"/>';
			document.getElementById("hiddenBotonFavorito").innerHTML = '<input id="botonFavorito" name="botonFavorito" type="hidden" value="' + dato + '"/>';
		}
	</script>
</head>
<body class="${prefUsr.tema}fondopantalla">
	<div class="container">
		<c:import url="/WEB-INF/views/menu.jsp" />
		<div class="well well-sm text-center h2 ${prefUsr.tema}titulo"><fmt:message key="label.User.preferences" /></div>

		<sf:form method="post" action="${pageContext.request.contextPath}/preferenciaUsuario/update" modelAttribute="preferenciaUsuario" onsubmit="return validar()">
			<sf:hidden path="idPrefUsr"/>
			<div class="row">
				<c:if test="${not empty preferenciaUsuario_actualizado}">					
					<div class="col-xs-2 col-sm-3">
					</div>
					<div class="col-xs-8 col-sm-6">
						<div class="alert alert-success">
							<button class="close" data-dismiss="alert"><span>&times;</span></button>
							<fmt:message key="label.Change.done.success" />
						</div>					
					</div>
					<div class="col-xs-2 col-sm-3">
					</div>
				</c:if>
				<c:if test="${not empty preferenciaUsuario_problemas}">					
					<div class="col-xs-2 col-sm-3">
					</div>
					<div class="col-xs-8 col-sm-6">
						<div class="alert alert-danger">
							<button class="close" data-dismiss="alert"><span>&times;</span></button>
							<fmt:message key="label.Change.not.done.success" />
						</div>					
					</div>
					<div class="col-xs-2 col-sm-3">
					</div>
				</c:if>
			</div>
			
			<div class="row">
				<div class="col-xs-4">
				</div>
				<div class="col-xs-4">
				    <div class="dropdown dropdown-menu-dam">
				        <div class="dropdown-toggle" data-toggle="dropdown">
				        	<img id="imagenPrincipalFavoritos" src='<c:url value="/resources/imgs/favoritos/${preferenciaUsuario.botonFavorito}.png"/>' class="tamanio_imagen_100x100">
				        	<span class="caret"></span>
			        	</div>
				        <ul class="dropdown-menu">
					        <c:forEach items="${favoritos}" var="favorito">
					            <li onclick="selectFavorito('${favorito.nombre}')"><img src='<c:url value="/resources/imgs/favoritos/${favorito.nombre}.png"/>' class="tamanio_imagen_100x100"></li>
				            </c:forEach>
				        </ul>
				    </div>
				    <div id="hiddenBotonFavorito"><sf:hidden path="botonFavorito" value="${botonFavorito}" /></div>
				</div>	
				<div class="col-xs-4">
					<div class="checkbox">
						<label>
							<sf:checkbox path="recibirPublicidad" /><fmt:message key="label.Receive.publicity" />
						</label>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-xs-3 hidden-sm hidden-md hidden-lg hidden-xl">
				</div>
				<div class="col-xs-8 col-sm-4">
					<div class="temamuestra defaultfondopantalla">
						<div class="row margin-tb-5px">
							<div class="col-xs-1">
							</div>
							<div class="col-xs-1">
								<div class="radio">
									<label for="tema01">
										<sf:radiobutton id="tema01" name="customRadioTema01" class="custom-control-input" path="tema" value="default" />
									</label>
								</div>
							</div>							
							<div class="col-xs-8">
								<div class="well well-sm text-center h2 defaulttitulo"><fmt:message key="label.Title" /></div>
							</div>		
							<div class="col-xs-1">
							</div>		
						</div>
						<div class="row margin-tb-5px">
							<div class="col-xs-2 col-sm-3">
							</div>
							<div class="col-xs-2 col-sm-1">
								<button type="button" class="btn defaultbotonresto"><fmt:message key="label.Others" /></button>
							</div>
							<div class="col-xs-3 col-sm-2 col-md-3">
							</div>
							<div class="col-xs-2">
								<button type="button" class="btn defaultbotonagregar"><fmt:message key="label.Add" /></button>
							</div>
						</div>
						<div class="row margin-tb-5px tematamanioletra">
							<fmt:message key="label.Rest" />
						</div>
						<div class="row margin-tb-5px">
							<div class="col-xs-3 col-sm-2 col-md-3">
							</div>
							<div class="col-xs-2">
								<button type="button" class="btn defaultbotonguardar"><fmt:message key="Save" /></button>
							</div>
							<div class="col-xs-1 col-md-1">
							</div>
							<div class="col-xs-2">
								<button type="button" class="btn defaultbotoncancelar"><fmt:message key="Return" /></button>
							</div>
							<div class="col-xs-3 col-sm-5 col-md-4">
							</div>
						</div>
					</div>
				</div>
				
				<div class="col-xs-3 hidden-sm hidden-md hidden-lg hidden-xl">
				</div>
				<div class="col-xs-8 col-sm-4">
					<div class="temamuestra tema02fondopantalla">
						<div class="row margin-tb-5px">
							<div class="col-xs-1">
							</div>
							<div class="col-xs-1">
								<div class="radio">
									<label for="tema02">
										<sf:radiobutton id="tema02" name="customRadioTema02" class="custom-control-input" path="tema" value="tema02" />
									</label>
								</div>
							</div>							
							<div class="col-xs-8">
								<div class="well well-sm text-center h2 tema02titulo"><fmt:message key="label.Title" /></div>
							</div>		
							<div class="col-xs-1">
							</div>		
						</div>
						<div class="row margin-tb-5px">
							<div class="col-xs-2 col-sm-3">
							</div>
							<div class="col-xs-2 col-sm-1">
								<button type="button" class="btn tema02botonresto"><fmt:message key="label.Others" /></button>
							</div>
							<div class="col-xs-3 col-sm-2 col-md-3">
							</div>
							<div class="col-xs-2">
								<button type="button" class="btn tema02botonagregar"><fmt:message key="label.Add" /></button>
							</div>
						</div>
						<div class="row margin-tb-5px tematamanioletra">
							<fmt:message key="label.Rest" />
						</div>
						<div class="row margin-tb-5px">
							<div class="col-xs-3 col-sm-2 col-md-3">
							</div>
							<div class="col-xs-2">
								<button type="button" class="btn tema02botonguardar"><fmt:message key="Save" /></button>
							</div>
							<div class="col-xs-1 col-md-1">
							</div>
							<div class="col-xs-2">
								<button type="button" class="btn tema02botoncancelar"><fmt:message key="Return" /></button>
							</div>
							<div class="col-xs-3 col-sm-5 col-md-4">
							</div>
						</div>
					</div>
				</div>
								
				<div class="col-xs-3 hidden-sm hidden-md hidden-lg hidden-xl">
				</div>
				<div class="col-xs-8 col-sm-4">
					<div class="temamuestra tema03fondopantalla">
						<div class="row margin-tb-5px">
							<div class="col-xs-1">
							</div>
							<div class="col-xs-1">
								<div class="radio">
									<label for="tema03">
										<sf:radiobutton id="tema03" name="customRadioTema03" class="custom-control-input" path="tema" value="tema03" />
									</label>
								</div>
							</div>							
							<div class="col-xs-8">
								<div class="well well-sm text-center h2 tema03titulo"><fmt:message key="label.Title" /></div>
							</div>		
							<div class="col-xs-1">
							</div>		
						</div>
						<div class="row margin-tb-5px">
							<div class="col-xs-2 col-sm-3">
							</div>
							<div class="col-xs-2 col-sm-1">
								<button type="button" class="btn tema03botonresto"><fmt:message key="label.Others" /></button>
							</div>
							<div class="col-xs-3 col-sm-2 col-md-3">
							</div>
							<div class="col-xs-2">
								<button type="button" class="btn tema03botonagregar"><fmt:message key="label.Add" /></button>
							</div>
						</div>
						<div class="row margin-tb-5px tematamanioletra">
							<fmt:message key="label.Rest" />
						</div>
						<div class="row margin-tb-5px">
							<div class="col-xs-3 col-sm-2 col-md-3">
							</div>
							<div class="col-xs-2">
								<button type="button" class="btn tema03botonguardar"><fmt:message key="Save" /></button>
							</div>
							<div class="col-xs-1 col-md-1">
							</div>
							<div class="col-xs-2">
								<button type="button" class="btn tema03botoncancelar"><fmt:message key="Return" /></button>
							</div>
							<div class="col-xs-3 col-sm-5 col-md-4">
							</div>
						</div>
					</div>
				</div>
			</div>			
			<br/>
			<div class="row">	
				<div class="col-xs-4">
				</div>
				<div class="col-xs-8">
					<button type="submit" class="btn btn-primary margin-left-5porciento ${prefUsr.tema}botonguardar"><fmt:message key="Save" /></button>
					<button type="button" class="btn btn-primary margin-left-5porciento ${prefUsr.tema}botoncancelar" onclick='location.href="<c:url value='/usuario/logged/${preferenciaUsuario.idPrefUsr}' />"'><fmt:message key="Return" /></button>
					<span id="hayError" name="errorSpan" style="color:red"></span>
				</div>
			</div>
		</sf:form>
		
		<footer>
			<c:import url="/WEB-INF/views/importFooter.jsp" />
		</footer>
	</div>
</body>
</html>