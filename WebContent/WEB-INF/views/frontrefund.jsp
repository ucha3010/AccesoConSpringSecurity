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
	<title><fmt:message key="label.Refund" /></title>
	<c:import url="/WEB-INF/views/importHead.jsp" />

</head>
<body class="${prefUsr.tema}fondopantalla">
	<div class="container">
		<c:import url="/WEB-INF/views/menu.jsp" />
		<div class="well well-sm text-center h2 ${prefUsr.tema}titulo"><fmt:message key="label.Refund" /></div>
		
		<c:import url="/WEB-INF/views/divSearchBar.jsp" />
		
		<div class="row">
			<div class="col-md-12">
				<p class="text-justify">
					Lorem ipsum dolor sit amet, consectetur
					adipiscing elit. Nullam maximus, est vel commodo efficitur, velit
					arcu feugiat turpis, sit amet venenatis tellus dui eget sapien.
					Phasellus non blandit mi, eu porta ante. Quisque a leo vitae ligula
					molestie mattis ac sed arcu. Nam consequat diam nibh, sed gravida
					elit laoreet non. In et dignissim justo, id aliquam purus. Etiam
					imperdiet non massa in iaculis. Maecenas eu vestibulum leo.
				</p>
				<p class="text-justify">
					Suspendisse tempus ac sem nec viverra. Aenean convallis urna at
					mattis vulputate. Integer rutrum eros ac orci mattis, finibus
					scelerisque nunc elementum. Nulla id nunc in ligula molestie
					rhoncus. Nunc non iaculis diam. Quisque sagittis orci lectus, eu
					aliquam nisl maximus id. Phasellus vel tristique mi. Sed at neque
					pharetra mi ornare facilisis a sed nunc. Morbi cursus pulvinar dui
					sed mollis. Ut porta, dolor in rhoncus luctus, metus nisi ultrices
					magna, vel laoreet metus purus in turpis. Mauris luctus ac neque
					nec accumsan. Vivamus at maximus lacus, id pharetra nunc. Phasellus
					consequat malesuada dui nec sodales. Donec consequat erat vel purus
					pretium pharetra. Maecenas a erat feugiat, rhoncus tellus in,
					pretium erat. Praesent eu convallis mi, id euismod augue.
				</p>
				<p class="text-justify">
					Vivamus convallis congue pulvinar. Maecenas
					enim sapien, varius sit amet ex et, consequat porttitor nisi.
					Aenean sed nunc in tellus condimentum suscipit nec vitae est.
					Nullam arcu enim, finibus vitae nisl sit amet, viverra sodales
					nulla. Mauris tincidunt placerat sapien, non mollis nisi suscipit
					sed. Nulla odio nunc, luctus ut turpis vitae, ultrices cursus
					justo. Vestibulum vel venenatis nisl. Quisque magna mi, dictum at
					dui non, eleifend convallis nibh. Nulla eget suscipit erat. Aliquam
					erat volutpat. Lorem ipsum dolor sit amet, consectetur adipiscing
					elit. Curabitur turpis libero, ultrices non leo vitae, aliquam
					fringilla nibh. Nunc et magna commodo, feugiat erat sed, maximus
					tellus. Mauris accumsan ultrices dolor eget sollicitudin.
				</p>
				<p class="text-justify">
					Nunc suscipit quis dui sit amet pretium.
					Quisque consequat sem a elit malesuada fringilla. Nulla ultrices id
					tellus nec ultrices. Aenean at diam cursus, mollis arcu vel,
					consequat magna. Morbi ac pretium nulla. Vestibulum sagittis
					rhoncus dolor, eu condimentum massa condimentum sed. Quisque
					vehicula laoreet nisl sit amet mollis. Praesent consequat, libero
					pellentesque vulputate porttitor, nunc leo posuere lectus, quis
					pretium dui augue sit amet ex. Fusce semper tortor vitae lacus
					faucibus, vel dignissim quam ultrices. Ut sodales sollicitudin sem,
					vitae facilisis orci molestie sit amet. Pellentesque sed orci ut
					libero rhoncus tincidunt et vel risus.
				</p>
				<p class="text-justify">
					Ut sagittis pellentesque turpis et egestas.
					Vestibulum vestibulum pellentesque tempor. Vestibulum tincidunt
					dictum metus quis vehicula. Phasellus molestie sit amet enim
					lobortis rhoncus. Aenean efficitur massa congue quam consequat
					dignissim. Vestibulum at elit sed ante dictum congue. Duis in erat
					in diam efficitur scelerisque. Aliquam id varius tellus. Mauris
					molestie a erat at eleifend. Aliquam ac lectus non mauris
					condimentum vulputate eget eget enim. Duis egestas sapien vehicula
					elementum maximus. Vestibulum ante ipsum primis in faucibus orci
					luctus et ultrices posuere cubilia curae
				</p>
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