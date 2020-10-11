<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- language maneja el idioma actual --%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.damian.utils.multilanguage" />

<div class="espaciocomienzofooter"></div>
<div class="row footerredes">
	<div class="hidden-xs col-sm-2">
	</div>
	<div class="hidden-xs col-sm-1">
		<a href="https://www.facebook.com/" target="_blank">
			<i class="fa fa-facebook-official" style="font-size:50px;color:#3b5998"></i>
		</a>
	</div>
	<div class="hidden-xs col-sm-1">
		<a href="https://www.youtube.com/" target="_blank">
			<i class="fa fa-youtube" style="font-size:50px;color:#f00"></i>
		</a>
	</div>
	<div class="hidden-xs col-sm-1">
		<a href="https://www.instagram.com/" target="_blank">
			<i class="fa fa-instagram" style="font-size:50px;color:#8134af"></i>
		</a>
	</div>
	<div class="hidden-xs col-sm-1">
		<a href="mailto:info@company.com" target="_blank">
			<span class="glyphicon glyphicon-envelope" style="font-size:50px;color:#593dde"></span>
		</a>
	</div>
	<div class="hidden-xs col-sm-1">
		<a href="https://twitter.com/" target="_blank">
			<i class="fa fa-twitter" style="font-size:50px;color:#08a0e9"></i>
		</a>
	</div>
	<div class="hidden-xs col-sm-1">
		<a href="https://www.pinterest.com/" target="_blank">
			<i class="fa fa-pinterest-p" style="font-size:50px;color:#bd081c"></i>
		</a>
	</div>
	<div class="hidden-xs col-sm-1">
		<a href="https://www.linkedin.com/" target="_blank">
			<i class="fa fa-linkedin-square" style="font-size:50px;color:#0077b5"></i>
		</a>
	</div>
	<div class="hidden-xs col-sm-3">
	</div>
	<div class="col-xs-12 hidden-sm hidden-md hidden-lg hidden-xl">
		<div class="imagenesredesxs">
			<a href="https://www.facebook.com/" target="_blank">
				<i class="fa fa-facebook-official" style="font-size:30px;color:#3b5998"></i>
			</a>
		</div>
		<div class="imagenesredesxs">
			<a href="https://www.youtube.com/" target="_blank">
				<i class="fa fa-youtube" style="font-size:30px;color:#f00"></i>
			</a>
		</div>
		<div class="imagenesredesxs">
			<a href="https://www.instagram.com/" target="_blank">
				<i class="fa fa-instagram" style="font-size:30px;color:#8134af"></i>
			</a>
		</div>
		<div class="imagenesredesxs">
			<a href="mailto:info@company.com" target="_blank">
				<span class="glyphicon glyphicon-envelope" style="font-size:30px;color:#593dde"></span>
			</a>
		</div>
		<div class="imagenesredesxs">
			<a href="https://twitter.com/" target="_blank">
				<i class="fa fa-twitter" style="font-size:30px;color:#08a0e9"></i>
			</a>
		</div>
		<div class="imagenesredesxs">
			<a href="https://www.pinterest.com/" target="_blank">
				<i class="fa fa-pinterest-p" style="font-size:30px;color:#bd081c"></i>
			</a>
		</div>
		<div class="imagenesredesxs">
			<a href="https://www.linkedin.com/" target="_blank">
				<i class="fa fa-linkedin-square" style="font-size:30px;color:#0077b5"></i>
			</a>
		</div>
	</div>
</div>

<div class="row hidden-xs footerenlaces">
	<div class="hidden-xs col-sm-3">
		<span class="footerenlacestitulos"><fmt:message key="label.We" /></span>
		<p><a class="menu-style footeraccesos" href="<c:url value='/footer/aboutus'/>"><fmt:message key="label.About.us" /></a></p>
		<p><a class="menu-style footeraccesos" href="<c:url value='/footer/findyourstore'/>"><fmt:message key="label.Find.your.strore" /></a></p>
		<p><a class="menu-style footeraccesos" href="<c:url value='/footer/workwithus'/>"><fmt:message key="label.Work.with.us" /></a></p>
		<p><a class="menu-style footeraccesos" href="<c:url value='/footer/legal'/>"><fmt:message key="label.Legal" /></a></p>
		<p><a class="menu-style footeraccesos" href="<c:url value='/footer/customerreviews'/>"><fmt:message key="label.Customer.reviews" /></a></p>
		<p><a class="menu-style footeraccesos" href="<c:url value='/footer/howtobuy'/>"><fmt:message key="label.How.to.buy" /></a></p>
		<p><a class="menu-style footeraccesos" href="<c:url value='/footer/cookiespolicy'/>"><fmt:message key="label.Cookies.policy" /></a></p>
	</div>
	<div class="hidden-xs col-sm-3">
		<span class="footerenlacestitulos"><fmt:message key="label.Services" /></span>
		<p><a class="menu-style footeraccesos" href="<c:url value='/footer/shipments'/>"><fmt:message key="label.Shipments" /></a></p>
		<p><a class="menu-style footeraccesos" href="<c:url value='/footer/giftcard'/>"><fmt:message key="label.Gift.card" /></a></p>
		<p><a class="menu-style footeraccesos" href="<c:url value='/footer/brands'/>"><fmt:message key="label.Brands" /></a></p>
		<p><a class="menu-style footeraccesos" href="<c:url value='/footer/membersclub'/>"><fmt:message key="label.Members.club" /></a></p>
	</div>
	<div class="hidden-xs col-sm-3">
		<span class="footerenlacestitulos"><fmt:message key="label.Information" /></span>
		<p><a class="menu-style footeraccesos" href="<c:url value='/footer/contact'/>"><fmt:message key="label.Contact" /></a></p>
		<p><a class="menu-style footeraccesos" href="<c:url value='/footer/Frequentquestions'/>"><fmt:message key="label.Frequent.questions" /></a></p>
		<p><a class="menu-style footeraccesos" href="<c:url value='/footer/shipments'/>"><fmt:message key="label.Shipments" /></a></p>
		<p><a class="menu-style footeraccesos" href="<c:url value='/footer/refund'/>"><fmt:message key="label.Refund" /></a></p>
		<p><a class="menu-style footeraccesos" href="<c:url value='/footer/guarantee'/>"><fmt:message key="label.Guarantee" /></a></p>
	</div>
	<div class="hidden-xs col-sm-3">
		<span class="footerenlacestitulos"><fmt:message key="label.Promotions" /></span>
		<p><a class="menu-style footeraccesos" href="<c:url value='/footer/promotion1'/>"><fmt:message key="label.Promotion1" /></a></p>
		<p><a class="menu-style footeraccesos" href="<c:url value='/footer/promotion2'/>"><fmt:message key="label.Promotion2" /></a></p>
		<p><a class="menu-style footeraccesos" href="<c:url value='/footer/promotion3'/>"><fmt:message key="label.Promotion3" /></a></p>
		<p><a class="menu-style footeraccesos" href="<c:url value='/footer/promotion4'/>"><fmt:message key="label.Promotion4" /></a></p>
	</div>
</div>
			
<div class="row footerenlacesxs">
	<div class="col-xs-12 hidden-sm hidden-md hidden-lg hidden-xl">
		<div class="col-xs-3">
			<span class="footerenlacestitulos"><fmt:message key="label.We" /></span>
			<p><a class="menu-style footeraccesos" href="<c:url value='/footer/aboutus'/>"><fmt:message key="label.About.us" /></a></p>
			<p><a class="menu-style footeraccesos" href="<c:url value='/footer/findyourstore'/>"><fmt:message key="label.Find.your.strore" /></a></p>
			<p><a class="menu-style footeraccesos" href="<c:url value='/footer/workwithus'/>"><fmt:message key="label.Work.with.us" /></a></p>
			<p><a class="menu-style footeraccesos" href="<c:url value='/footer/legal'/>"><fmt:message key="label.Legal" /></a></p>
			<p><a class="menu-style footeraccesos" href="<c:url value='/footer/customerreviews'/>"><fmt:message key="label.Customer.reviews" /></a></p>
			<p><a class="menu-style footeraccesos" href="<c:url value='/footer/howtobuy'/>"><fmt:message key="label.How.to.buy" /></a></p>
			<p><a class="menu-style footeraccesos" href="<c:url value='/footer/cookiespolicy'/>"><fmt:message key="label.Cookies.policy" /></a></p>
		</div>
		<div class="col-xs-3">
			<span class="footerenlacestitulos"><fmt:message key="label.Services" /></span>
			<p><a class="menu-style footeraccesos" href="<c:url value='/footer/shipments'/>"><fmt:message key="label.Shipments" /></a></p>
			<p><a class="menu-style footeraccesos" href="<c:url value='/footer/giftcard'/>"><fmt:message key="label.Gift.card" /></a></p>
			<p><a class="menu-style footeraccesos" href="<c:url value='/footer/brands'/>"><fmt:message key="label.Brands" /></a></p>
			<p><a class="menu-style footeraccesos" href="<c:url value='/footer/membersclub'/>"><fmt:message key="label.Members.club" /></a></p>
		</div>
		<div class="col-xs-3">
			<span class="footerenlacestitulos"><fmt:message key="label.Information" /></span>
			<p><a class="menu-style footeraccesos" href="<c:url value='/footer/contact'/>"><fmt:message key="label.Contact" /></a></p>
			<p><a class="menu-style footeraccesos" href="<c:url value='/footer/Frequentquestions'/>"><fmt:message key="label.Frequent.questions" /></a></p>
			<p><a class="menu-style footeraccesos" href="<c:url value='/footer/shipments'/>"><fmt:message key="label.Shipments" /></a></p>
			<p><a class="menu-style footeraccesos" href="<c:url value='/footer/refund'/>"><fmt:message key="label.Refund" /></a></p>
			<p><a class="menu-style footeraccesos" href="<c:url value='/footer/guarantee'/>"><fmt:message key="label.Guarantee" /></a></p>
		</div>
		<div class="col-xs-3">
			<span class="footerenlacestitulos"><fmt:message key="label.Promotions" /></span>
			<p><a class="menu-style footeraccesos" href="<c:url value='/footer/promotion1'/>"><fmt:message key="label.Promotion1" /></a></p>
			<p><a class="menu-style footeraccesos" href="<c:url value='/footer/promotion2'/>"><fmt:message key="label.Promotion2" /></a></p>
			<p><a class="menu-style footeraccesos" href="<c:url value='/footer/promotion3'/>"><fmt:message key="label.Promotion3" /></a></p>
			<p><a class="menu-style footeraccesos" href="<c:url value='/footer/promotion4'/>"><fmt:message key="label.Promotion4" /></a></p>
		</div>
	</div>
</div>

<div class="row">
	<div class="hidden-xs col-sm-1">
	</div>
	<div class="hidden-xs col-sm-2">
		<img src='<c:url value="/resources/imgs/footer/bizum.jpg"/>' class="width-100" alt="Bizum" id="logoBizum">
	</div>
	<div class="hidden-xs col-sm-2">
		<img src='<c:url value="/resources/imgs/footer/visa.jpg"/>' class="width-100" alt="Visa" id="logoVisa">
	</div>
	<div class="hidden-xs col-sm-2">
		<img src='<c:url value="/resources/imgs/footer/master_card.jpg"/>' class="width-100" alt="Master card" id="logoMaster">
	</div>
	<div class="hidden-xs col-sm-2">
		<img src='<c:url value="/resources/imgs/footer/american_express.png"/>' class="width-100" alt="Américan Express" id="logoAmerican">
	</div>
	<div class="hidden-xs col-sm-2">
		<img src='<c:url value="/resources/imgs/footer/paypal.jpg"/>' class="width-100" alt="Paypal" id="logoPaypal">
	</div>
	<div class="hidden-xs col-sm-1">
	</div>
	<div class="col-xs-2 hidden-sm hidden-md hidden-lg hidden-xl">
		<img src='<c:url value="/resources/imgs/footer/bizum.jpg"/>' class="width-70" alt="Bizum" id="logoBizum">
	</div>
	<div class="col-xs-2 hidden-sm hidden-md hidden-lg hidden-xl">
		<img src='<c:url value="/resources/imgs/footer/visa.jpg"/>' class="width-70" alt="Visa" id="logoVisa">
	</div>
	<div class="col-xs-2 hidden-sm hidden-md hidden-lg hidden-xl">
		<img src='<c:url value="/resources/imgs/footer/master_card.jpg"/>' class="width-70" alt="Master card" id="logoMaster">
	</div>
	<div class="col-xs-2 hidden-sm hidden-md hidden-lg hidden-xl">
		<img src='<c:url value="/resources/imgs/footer/american_express.png"/>' class="width-70" alt="Américan Express" id="logoAmerican">
	</div>
	<div class="col-xs-2 hidden-sm hidden-md hidden-lg hidden-xl">
		<img src='<c:url value="/resources/imgs/footer/paypal.jpg"/>' class="width-70" alt="Paypal" id="logoPaypal">
	</div>
</div>