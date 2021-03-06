<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- language maneja el idioma actual --%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.damian.utils.multilanguage" />

<header>
	<nav class="navbar navbar-fixed-top mb-0" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header bg-white">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navegacion-fm">
					<span class="sr-only"><fmt:message key="label.Expand.Hide" /> <fmt:message key="label.Menu" /></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a title="Company" href='<c:url value="/"/>'>
					<img src='<c:url value="/resources/imgs/logo.jpg"/>' alt="Logo de la empresa" id="logoImg">
				</a>
			</div>
			
			<div class="collapse navbar-collapse bg-white" id="navegacion-fm">
				<sec:authorize access="hasAnyRole('ROL_ADMIN','ROL_ROOT')">
					<div class="hidden-sm hidden-md">
						<ul class="nav navbar-nav">
							<sec:authorize access="isRememberMe()">
								<c:import url="/WEB-INF/views/menuUsuario.jsp" />	
								<c:import url="/WEB-INF/views/menuAdmin.jsp" />	
							</sec:authorize>
							<sec:authorize access="isFullyAuthenticated()">
								<c:import url="/WEB-INF/views/menuUsuario.jsp" />
								<c:import url="/WEB-INF/views/menuAdmin.jsp" />	
							</sec:authorize>
						</ul>
					</div>
					<sec:authorize access="isFullyAuthenticated()">
						<c:import url="/WEB-INF/views/menuDropdownAdmin.jsp" />
					</sec:authorize>				
					<sec:authorize access="isRememberMe()">
						<c:import url="/WEB-INF/views/menuDropdownAdmin.jsp" />
					</sec:authorize>
				</sec:authorize>
				<sec:authorize access="hasAnyRole('ROL_USUARIO') and !hasAnyRole('ROL_ADMIN','ROL_ROOT')">
					<div class="hidden-sm">
						<ul class="nav navbar-nav">
							<sec:authorize access="isRememberMe()">
								<c:import url="/WEB-INF/views/menuUsuario.jsp" />
							</sec:authorize>
							<sec:authorize access="isFullyAuthenticated()">
								<c:import url="/WEB-INF/views/menuUsuario.jsp" />	
							</sec:authorize>
						</ul>
					</div>
					<sec:authorize access="isFullyAuthenticated()">
						<c:import url="/WEB-INF/views/menuDropdownUsuario.jsp" />
					</sec:authorize>				
					<sec:authorize access="isRememberMe()">
						<c:import url="/WEB-INF/views/menuDropdownUsuario.jsp" />
					</sec:authorize>
				</sec:authorize>
				
			
				<ul class="nav navbar-nav navbar-right">
					<sec:authorize access="!isAuthenticated()">
						<li class="btn-info border-color-dam">
							<a class="menu-style" href="<c:url value='/usuario/nuevo'/>"><fmt:message key="New.user" /></a>
						</li>
						<li class="btn-success border-color-dam">
							<c:if test="${sessionScope.estoy != null}">
								<a class="menu-style" href="<c:url value='/private/${sessionScope.estoy}'/>"><fmt:message key="label.Login" /></a>
							</c:if>
							<c:if test="${sessionScope.estoy == null}">
								<a class="menu-style" href="<c:url value='/private/index'/>"><fmt:message key="label.Login" /></a>
							</c:if>
						</li>
					</sec:authorize>
			
					<sec:authorize access="isRememberMe()">
						<sec:authentication property="principal" var="principal" />
						<li>
							<span>
								<a title="${nameUsrLogged}" href='<c:url value="/usuario/logged/${idUsrLogged}"/>'>
									<c:if test="${prinPicUsr == null}">
										<img src='<c:url value="/resources/imgs/usuario.png"/>' alt="${nameUsrLogged}" class="usuarioImg">
									</c:if>
									<c:if test="${prinPicUsr != null}">
										<img src='<c:url value="/resources/imgs/usuarios/${idUsrLogged}/${prinPicUsr}"/>' alt="${nameUsrLogged}" class="usuarioImgSize img-circle">
									</c:if>
								</a>
							</span>
						</li>
						<li class="btn-danger border-radius-10">
							<a class="menu-style" href="<c:url value='/logout' />"><span class="glyphicon glyphicon-off"></span></a>
						</li>
					</sec:authorize>
			
					<sec:authorize access="isFullyAuthenticated()">
						<sec:authentication property="principal" var="principal" />
						<li class="margin-right-10px bg-color-white-dam">
							<span>
								<a title="${nameUsrLogged}" href='<c:url value="/usuario/logged/${idUsrLogged}"/>'>
									<c:if test="${prinPicUsr == null}">
										<img src='<c:url value="/resources/imgs/usuario.png"/>' alt="${nameUsrLogged}" class="usuarioImg">
									</c:if>
									<c:if test="${prinPicUsr != null}">
										<img src='<c:url value="/resources/imgs/usuarios/${idUsrLogged}/${prinPicUsr}"/>' alt="${nameUsrLogged}" class="usuarioImgSize img-circle">
									</c:if>
								</a>
							</span>
						</li>
						<li class="btn-danger border-radius-10">
							<a class="menu-style" href="<c:url value='/logout' />"><span class="glyphicon glyphicon-off"></span></a>
						</li>
					</sec:authorize>
				</ul>
				
				<form class="navbar-form navbar-right desaparece-1200-1282 bg-color-white-dam">
					<select class="dropdown dropdown-toggle" name="language" onchange="submit();">
						<option value="es_ES" <c:if test="${language=='es_ES'}">selected</c:if>><fmt:message key="label.Spanish" /></option>
						<option value="en_IE" <c:if test="${language=='en_IE'}">selected</c:if>><fmt:message key="label.English" /></option>						
	<!-- 					con en_US el símbolo del dinero pasa a ser $ y no € -->
	<!-- 					<option value="en_US" -->
	<%-- 						<c:if test="${language=='en_US'}">selected</c:if>> --%>
	<%-- 						<fmt:message key="label.English" /> --%>
	<!-- 					</option> -->
					</select>
				</form>
			</div>
			
		</div>
	</nav>
	<div class="separacion10"></div>
</header>