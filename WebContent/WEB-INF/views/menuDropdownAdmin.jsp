<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- language maneja el idioma actual --%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.damian.utils.multilanguage" />

					<div class="hidden-xs hidden-lg hidden-xl">
						<ul class="nav navbar-nav">
							<li class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">
									<fmt:message key="label.Menu" /> <span class="caret"></span>
								</a>
								<ul class="dropdown-menu" role="menu">
									<c:import url="/WEB-INF/views/menuUsuario.jsp" />
									<li class="divider"></li>
									<c:import url="/WEB-INF/views/menuAdmin.jsp" />
								</ul>
							</li>
						</ul>
					</div>