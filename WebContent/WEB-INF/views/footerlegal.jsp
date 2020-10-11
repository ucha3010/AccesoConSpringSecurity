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
	<title><fmt:message key="label.Legal" /></title>
	<c:import url="/WEB-INF/views/importHead.jsp" />

</head>
<body class="${prefUsr.tema}fondopantalla">
	<div class="container">
		<c:import url="/WEB-INF/views/menu.jsp" />
		
		<div class="row">
			<div class="col-md-12">
				<h1><fmt:message key="label.Legal" /></h1>
				
				<h2>Política de privacidad de la plataforma</h2>
				<p>Gracias formarte con xxxx. En xxxx, respetamos la privacidad de nuestros alumnos, y queremos que comprendas cómo recopilamos, utilizamos y compartimos tus datos.</p>
				
				<p>Debido al RGPD (Reglamento General de Protección de Datos) de la Unión Europea, es necesario que aceptes que has leído y comprendes esta información antes de poder entrar en la plataforma. En caso de no aceptar, no podemos darte acceso a la formación. Verás que es muy sencillo.</p>
				
				<h2>Qué datos obtenemos</h2>
				<p>Recopilamos determinados datos (como tu nombre o dirección de email), bien de ti directamente, bien de la persona que ha gestionado la compra del curso que vas a realizar, así como información que introduces por tu cuenta y datos sobre tu participación en cursos. También recopilamos algunos datos de forma automática, como información sobre el dispositivo que utilizas y las partes de la plataforma con las que interactúas, que son necesarios para hacer un seguimiento del curso.</p>
				
				<h2>Categoría de datos</h2>
				<p>Los datos que se tratan son de carácter identificativo. No se tratan categorías de datos especialmente protegidos.</p>
				
				<h2>¿Con qué finalidad tratamos tus datos personales?</h2>
				<p>Utilizamos tus datos únicamente para proporcionar nuestro servicio de formación, comunicarnos contigo en relación a la formación, solucionar posibles problemas, protegernos del fraude y del uso indebido, y mejorar y actualizar nuestros servicios.</p>
				
				<h2>¿Por cuánto tiempo conservaremos tus datos?</h2>
				<p>Los datos personales proporcionados se conservarán mientras no se solicite su supresión por tu parte o mientras tengamos obligación legal de conservarlos (por ejemplo en el caso de cursos bonificados con el Estado español, durante los años que pueden ser inspeccionados).</p>
				
				<h2>¿Cuál es la legitimación para el tratamiento de tus datos?</h2>
				<p>La base legal para el tratamiento de tus datos es la prestación del servicio y este consentimiento informado que nos otorgas para ello.</p>
				
				<h2>¿A qué destinatarios se comunicarán tus datos?</h2>
				<p>Tus datos no serán jamás cedidos a terceros, a excepción de que tu curso sea bonificado con el Estado Español por parte de tu empresa, en cuyo caso la Fundae tendrá acceso a los mismos durante el periodo que marca la ley.</p>
				
				<h2>¿Cuáles son tus derechos cuando nos facilitas tus datos?</h2>
				<p>Cualquier persona tiene derecho a obtener confirmación sobre si en Company, SL (nombre legal de la empresa que ofrece la formación) estamos tratando datos personales que les conciernen, o no.</p>
				
				<p>Las personas interesadas tienen derecho a acceder a sus datos personales, así como a solicitar la rectificación de los datos inexactos o, en su caso, solicitar su supresión cuando, entre otros motivos, los datos ya no sean necesarios para los fines que fueron recogidos.</p>
				
				<h2>Datos de contacto para ejercer tus derechos:</h2>
				
				<p>Dirección postal: Plaza Mayor, 1 - 36204 Vigo (PO)</p>
				
				<p>Correo electrónico: info@company.com</p>
				
				<p>o a través de cualquier medio de contacto disponible en nuestra página web.</p>
				
				
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