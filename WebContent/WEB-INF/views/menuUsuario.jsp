<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- language maneja el idioma actual --%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.damian.utils.multilanguage" />

							<li class="btn-success border-color-dam">
								<a class="menu-style" href="<c:url value='/cliente/all/null/null/0/10'/>"><fmt:message key="label.Customers" /></a>
							</li>
							<li class="btn-info border-color-dam">
								<a class="menu-style" href="<c:url value="/empresa/all/null"/>"><fmt:message key="Companies" /></a>
							</li>
							<li class="btn-info border-color-dam">
								<a class="menu-style" href="<c:url value="/producto/all/null"/>"><fmt:message key="Products" /></a>
							</li>
							<li class="btn-info border-color-dam">
								<a class="menu-style" href="<c:url value="/factura/all/null"/>"><fmt:message key="label.Bills" /></a>
							</li>
							<li class="btn-info border-color-dam">
								<a class="menu-style" href="<c:url value="/categoria"/>"><fmt:message key="label.Categories" /></a>
							</li>