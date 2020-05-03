<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- language maneja el idioma actual --%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.damian.utils.multilanguage" />
<html>
<head>
	<title><fmt:message key="label.Add.remove.stock" /></title>
	<c:import url="/WEB-INF/views/importHead.jsp" />
	
	<script type="text/javascript" src='<c:url value="/resources/js/validaciones.js" />'></script>
	<script type="text/javascript">
		function validar(){

			restablecer();
			var validado = true;			

			var decimales = ['precioFinal'];
			for(var i=0; i < decimales.length; i++){
				var nombreDecimal = document.getElementById(decimales[i]);
				var nombreDecimalError = document.getElementById(decimales[i]+'Error');
				nombreDecimal.value = cambiarComaPorPunto(nombreDecimal.value);
				if(!validarDecimal(nombreDecimal.value)){
					nombreDecimalError.innerHTML = "<fmt:message key='error.not.valid.value' />";
					nombreDecimalError.style.color="red";
					nombreDecimal.style.borderColor="red";
					validado = false;
				}
			}

			var cantidad = document.getElementById("cantidad");
			var precioFinal = document.getElementById("precioFinal");
			if((cantidad.value == '' || cantidad.value == 0) && (precioFinal.value == '' || precioFinal.value == '0.0' || precioFinal.value == '0' || precioFinal.value == '0.00')) {
				cantidad.style.borderColor="red";
				precioFinal.style.borderColor="red";
				var precioFinalError = document.getElementById('precioFinalError');
				precioFinalError.innerHTML = "<fmt:message key='error.not.valid.data' />";
				precioFinalError.style.color="red";
				validado = false;				
			}

			var cuotas = document.getElementsByClassName("installment-verify");
			var totalCuotas= 0;
			var cuotaText
			if(cuotas.length > 0 && validado){
				for (var i = 0; i < cuotas.length; i++) {
					cuotaText = cambiarComaPorPunto(cuotas[i].value);
					if(!validarDecimal(cuotaText)){
						cuotas[i].style.borderColor="red";
						validado = false;
					} else {
						totalCuotas += parseFloat(cuotaText);
					}
				}
				if(parseFloat(precioFinal.value) > totalCuotas) {
					var errorCuotas = document.getElementById("errorCuotas");
					errorCuotas.innerHTML = "<fmt:message key='error.total.installment.less.purchase' />";	
					errorCuotas.style.color="red";				
					validado = false;					
				}
			}
			
			if(validado){
				return true;
			} else {
				return false;
			}
		}
		function restablecer(){
			var errorSpan = document.getElementsByName('errorSpan');
			for (var i = 0; i < errorSpan.length; i++) {
				errorSpan[i].innerHTML='';
			}
			var campos = document.getElementsByClassName("form-validacion");
			for (var i = 0; i < campos.length; i++) {
				campos[i].style.borderColor="#ced4da";
			}
		}
		function mensaje(valor,max){
			if(valor == "true"){
				document.getElementById("queHacer").innerHTML="<fmt:message key='label.added' />";
				document.getElementById("avisoEliminar").style.display="none";
				document.getElementById("queSeHizo").innerHTML="<fmt:message key='label.paid' />";
			} else {
				document.getElementById("queHacer").innerHTML="<fmt:message key='label.removed' />";
				document.getElementById("avisoEliminar").style.display="inline";
				document.getElementById("queSeHizo").innerHTML="<fmt:message key='label.entered' />";				
			}
			document.getElementById("cantidad").max=max;
		}
		
		var primeraCuota;
		var sumarUltimaCuota;
		
		function cuotas(cb) {
			var selectCuotas = document.getElementById("cantidadCuotas");
			var detalleCuotas = document.getElementById("detalleCuotas");
			
			if(cb.checked){
				selectCuotas.style.visibility = "visible";
				detalleCuotas.style.visibility = "visible";
				var cuotasSel = document.getElementById("cuotasSel");
				cargarCuotas(cuotasSel.value);
			} else {
				selectCuotas.style.visibility = "hidden";
				detalleCuotas.style.visibility = "hidden";
			}
		}
		
		function cargarCuotas() {
			var num = document.getElementById("cuotasSel").value;
			sumarUltimaCuota = 0;
			primeraCuota = 0;
			var datos = '';
			var datosCuotas = document.getElementById("datosCuotas");
			var cuotasMedio = rellenarCuotas(num);
			
			for (var i = 0; i < num; i++) {
				datos = datos + 
				'<div class="row"><div class="col-xs-12">' +
				'<fmt:message key="label.Installment" /> ' + (i+1) + ':' +
				'</div></div>' +
				'<div class="row"><div class="col-xs-12">' +
				'<input type="hidden" name="cuotas['+i+'].numeroCuota" value="'+(i+1)+'" />' +
				'</div></div>' +
				'<div class="row margin-b-5"><div class="col-xs-12">' +
				'<fmt:message key="label.Date" /> <input type="date" name="cuotas['+i+'].fechaCompra" class="width-130" value="'+ calculaFecha(i) + '" />' +
				'</div></div>' +
				'<div class="row"><div class="col-xs-12">' +
				'<fmt:message key="label.Amount" /> <input type="text" name="cuotas['+i+'].importeTotal" class="width-80-align-rigth installment-verify form-validacion"';
				if(i == 0) {
					if(num == 1) {
						datos = datos + ' value="' + (cuotasMedio + sumarUltimaCuota + primeraCuota).toFixed(2) + '" ';						
					} else {
						datos = datos + ' value="' + (cuotasMedio + primeraCuota).toFixed(2) + '" ';							
					}
				} else if(i < num - 1){
					datos = datos + ' value="' + cuotasMedio.toFixed(2) + '" ';
				} else {
					datos = datos + ' value="' + (cuotasMedio + sumarUltimaCuota).toFixed(2) + '" ';
				}
				
				datos = datos + ' onclick="if (this.value==0.00) this.value=\'\'" onblur="if (this.value==\'\') this.value=0.00" />' +
				'</div></div>' +
				'<br/>';
				
			}
			datosCuotas.innerHTML=datos;
		}
		function rellenarCuotas(num) {
			
			var precioFinal = document.getElementById("precioFinal").value;
			var comisionApertura = document.getElementById("comision").value;
			var tae = document.getElementById("tae").value;
			
			if(comisionApertura != 0){
				primeraCuota = (precioFinal * comisionApertura) / 100;
				primeraCuota = redondear(primeraCuota, 2);
			}
			
			var cuotaMensual;
			if(precioFinal == 0){
				cuotaMensual = 0.0;
			} else if (precioFinal != 0 && tae == 0) {
				cuotaMensual = precioFinal / num;
			} else {				
				var interes = tae / 1200;				
				cuotaMensual = (precioFinal * ((1 + interes) ** num) * interes ) / (((1 + interes) ** num) - 1);
			}
			var residuo = cuotaMensual % 1;
			residuo *= 100;
			residuo = residuo % 1;
			var residuoRestar = residuo * 0.01;
			cuotaMensual -= residuoRestar;
			var residuoSumar = residuo * num;
			sumarUltimaCuota = Math.round(residuoSumar);
			sumarUltimaCuota *= 0.01;
		
			return cuotaMensual;
			
		}
		
		function calculaFecha(num) {

			var f = new Date();
			var mes = f.getMonth()+1+num+1;
			var anio = f.getFullYear();
			while(mes > 12) {
				mes -= 12;
			    anio += 1;
			}
			  
			return anio + '-' + rellenaCerosIzquierda(mes,2) + '-01';
		}

		function rellenaCerosIzquierda(entrada,cantidad){
			var salida = entrada + "";
			while (cantidad > salida.length) {
		    	salida = "0" + salida;
		    }
		    return salida;
		}
		function redondear(value, decimals) {
		  return Number(Math.round(value+'e'+decimals)+'e-'+decimals);
		}
	</script>
</head>
<body>
	<fmt:message key="language.name" var="nameColSelect"/>
	<div class="container">
		<c:import url="/WEB-INF/views/menu.jsp" />
		<div class="well well-sm text-center h2">
			<fmt:message key="label.Add.remove.stock" />
			<br>
			<fmt:message key="label.Product.description" />: <c:out value="${frontProductoStock[nameColSelect]}" />
		</div>
		<sf:form method="post" action="${pageContext.request.contextPath}/producto/stock/save" modelAttribute="frontProductoStock" onsubmit="return validar()">
			<sf:hidden path="idPro"/>
			<sf:hidden path="unidades"/>
			<div class="col-xs-9">
				<div class="row">		
					<div class="hidden-xs col-sm-1">
					</div>
					<div class="col-xs-12 col-sm-5">
						<div class="radio-inline">
							<label for="customRadioInline2">
								<sf:radiobutton id="customRadioInline2" name="customRadioInline1" class="custom-control-input" path="compra" value="true" onclick="mensaje(this.value,9999)" />
								<fmt:message key="label.Add" />
							</label>
						</div>
						<div class="radio-inline">
							<label for="customRadioInline1">
								<sf:radiobutton id="customRadioInline1" name="customRadioInline1" class="custom-control-input" path="compra" value="false" onclick="mensaje(this.value,${frontProductoStock.unidades})" />
								<fmt:message key="label.Remove" />
							</label>
						</div>
					</div>
					<div class="col-xs-3 col-sm-2">
						<div class="checkbox-inline">
							<label for="cuotasCheck">
								<input type='checkbox' class="form-check-input" onclick='cuotas(this);' id="cuotasCheck"> <fmt:message key="label.Installments" />
							</label>
						</div>
					</div>
					<div class="col-xs-9 col-sm-4">
	   					<div id="cantidadCuotas" style="visibility:hidden;">
				        	<sf:select path="cantidadCuotas" class="form-control" id="cuotasSel" onchange="cargarCuotas()" style="width:auto;">
					        	<c:forEach begin="1" end="12" varStatus="num">
				            		<sf:option value="${num.index}" label="${num.index}" />
								</c:forEach>
				        	</sf:select>
			        	</div>
		        	</div>
				</div>
				<br>
				<div class="row">		
					<div class="hidden-xs col-sm-1">
					</div>	
					<div class="col-xs-12 col-sm-10">
						<fmt:message key="label.It.will.be" />
						<span id="queHacer"><fmt:message key="label.added" /></span>
						<input type="number" step="1" class="width-80-align-rigth form-validacion" min="0" name="cantidad" id="cantidad"/>
						<fmt:message key="label.units" />
					</div>
					<div class="hidden-xs col-sm-1">
					</div>
				</div>
				<div class="row">		
					<div class="hidden-xs col-sm-1">
					</div>
					<div class="col-xs-12 col-sm-10" id="avisoEliminar" style="display: none">
						(<fmt:message key="label.Max.to.remove" /> ${frontProductoStock.unidades} <fmt:message key="label.units" />)
					</div>
					<div class="hidden-xs col-sm-1">
					</div>
				</div>
				<br>
				<div class="row">		
					<div class="hidden-xs col-sm-1">
					</div>	
					<div class="col-xs-12 col-sm-10">
						<fmt:message key="label.And.for.this" />
						<span id="queSeHizo"><fmt:message key="label.paid" /></span>
						<sf:input path="precioFinal" type="text" class="width-80-align-rigth form-validacion" id="precioFinal"/> €
						<fmt:message key="label.with.vat" />
						<input type="number" step="1" style="width:40px; text-align: right;" min="0" name="iva" id="iva" value="0"/> %
					</div>
					<div class="hidden-xs col-sm-1">
					</div>
				</div>
				<br>
				<div class="row">		
					<div class="hidden-xs col-sm-1">
					</div>	
					<div class="col-xs-12 col-sm-11">
						<fmt:message key="label.Observations" />
					</div>
				</div>
				<br>
				<div class="row">		
					<div class="hidden-xs col-sm-1">
					</div>	
					<div class="col-xs-12 col-sm-10">
						<sf:textarea path="observaciones" rows="5" cols="45" />
					</div>
					<div class="hidden-xs col-sm-1">
					</div>
				</div>
				<br>
				<div class="row">	
					<div class="hidden-xs col-sm-2">
					</div>
					<div class="col-xs-12 col-sm-10">
						<button type="submit" class="btn btn-primary margin-left-5porciento"><fmt:message key="Send" /></button>
						<button type="button" class="btn btn-primary margin-left-5porciento" onclick='location.href="<c:url value='/producto/all/null' />"'><fmt:message key="Cancel" /></button>
						<span id="precioFinalError" name="errorSpan"></span>
					</div>
				</div>
			</div>
				
			<div class="col-xs-3" id="detalleCuotas" style="visibility:hidden;">
			
				<div class="row">
					<div class="col-xs-12">
						<button type="button" class="btn btn-warning" onclick='cargarCuotas()'><fmt:message key="label.Update.installments" /></button>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12">
						<span id="errorCuotas" name="errorSpan"></span>
					</div>
				</div>
				<br/>
				<div class="row">
					<div class="col-xs-12">
						<label class="form-check-label" for="comisionAperturaPor"><fmt:message key="label.Opening.commission" /></label>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12">
						<sf:input type="text" path="comisionAperturaPor" class="width-50-align-rigth" id="comision" value="0.0" /> %
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12">
						<label class="form-check-label" for="interesPor"><fmt:message key="label.APR" /></label>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12">
						<sf:input type="text" path="interesPor" class="width-50-align-rigth" id="tae" value="0.0" /> %
					</div>
				</div>
				<br>
				<div id="datosCuotas"></div>
			</div>
		</sf:form>
		
		<footer>
			<c:import url="/WEB-INF/views/importFooter.jsp" />
		</footer>
	</div>
</body>
</html>