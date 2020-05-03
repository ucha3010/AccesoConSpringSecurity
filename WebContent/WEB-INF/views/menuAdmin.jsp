<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- language maneja el idioma actual --%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.damian.utils.multilanguage" />

							<li class="btn-primary border-color-dam">
								<a class="menu-style" href="<c:url value="/usuario/all/null/null/0/100"/>"><fmt:message key="label.Users" /></a>
							</li>
							<li class="btn-primary border-color-dam">
								<a class="menu-style" href="<c:url value="/formaPago"/>"><fmt:message key="label.Payment.methods" /></a>
							</li>
							<li class="btn-primary border-color-dam">
								<a class="menu-style" href="<c:url value="/estado"/>"><fmt:message key="label.states" /></a>
							</li>
							<li class="btn-primary border-color-dam">
								<a class="menu-style" href="<c:url value="/rol"/>"><fmt:message key="label.Roles" /></a>
							</li>