<%@ page contentType="application/pdf"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ page import="net.sf.jasperreports.engine.*"%>
<%@ page import="net.sf.jasperreports.engine.data.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.damian.pojo.front.ImpresionFactura"%>
<%@ page import="com.damian.pojo.front.ImpresionProducto"%>
<%@ page import="com.damian.dao.model.ModelCuotaDetalle"%>
<%
	try {
		Map<String, Object> params = new HashMap<String, Object>();

		ImpresionFactura factura = (ImpresionFactura) request.getAttribute("factura");
		params.put("fechaCompra", factura.getFechaCompra());
		params.put("idFac", factura.getIdFac());
		params.put("entrega_nombre", factura.getEntrega_nombre());
		params.put("entrega_direccion1", factura.getEntrega_direccion1());
		params.put("entrega_direccion2", factura.getEntrega_direccion2());
		params.put("entrega_cp", factura.getEntrega_cp());
		params.put("entrega_ciudad", factura.getEntrega_ciudad());
		params.put("entrega_provincia", factura.getEntrega_provincia());
		params.put("entrega_pais", factura.getEntrega_pais());
		params.put("entrega_telefono", factura.getEntrega_telefono());
		params.put("factura_nombre", factura.getFactura_nombre());
		params.put("factura_direccion1", factura.getFactura_direccion1());
		params.put("factura_direccion2", factura.getFactura_direccion2());
		params.put("factura_cp", factura.getFactura_cp());
		params.put("factura_ciudad", factura.getFactura_ciudad());
		params.put("factura_provincia", factura.getFactura_provincia());
		params.put("factura_pais", factura.getFactura_pais());
		params.put("factura_telefono", factura.getFactura_telefono());
		params.put("formaPago_nombre", factura.getFormaPago_nombre());
		params.put("factura_observaciones", factura.getFactura_observaciones());
		params.put("totalSinIvaEnvDescfac", factura.getTotalSinIvaEnvDescfac());
		params.put("importeEnvioSinIva", factura.getImporteEnvioSinIva());
		params.put("descuentoTotal", factura.getDescuentoTotal());
		params.put("descuentoImporteFactura", factura.getDescuentoImporteFactura());
		params.put("totalSinIvaConDescfac", factura.getTotalSinIvaConDescfac());
		params.put("envioIvaImp", factura.getEnvioIvaImp());
		params.put("productosIvaImp", factura.getProductosIvaImp());
		params.put("ivaImporteTotal", factura.getIvaImporteTotal());
		params.put("importeTotal", factura.getImporteTotal());
		params.put("hayCuotas", factura.isHayCuotas());
		params.put("idCuo", factura.getIdCuo());
		params.put("comisionAperturaPor", factura.getComisionAperturaPor());
		params.put("interesPor", factura.getInteresPor());
		params.put("primerRenglon", factura.getPrimerRenglon());
		params.put("segundoRenglon", factura.getSegundoRenglon());
		params.put("tercerRenglon", factura.getTercerRenglon());
		
		params.put("impresionProductoList", factura.getImpresionProductoList());

		if (factura.isHayCuotas()) {
			List<ModelCuotaDetalle> cuotaDetalleList = (List<ModelCuotaDetalle>) request
					.getAttribute("cuotaDetalleList");
			params.put("cuotaDetalleList", cuotaDetalleList);
		}

		String jrxmlFile = session.getServletContext().getRealPath("/resources/report/" + factura.getJrxml() + ".jrxml");
		InputStream input = new FileInputStream(new File(jrxmlFile));
		JasperReport jasperReport = JasperCompileManager.compileReport(input);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
		OutputStream outputstream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outputstream);
		outputstream.flush();
		outputstream.close();
	} catch (Exception e) {
		System.out.println("Error: " + e.getMessage());
	}
%>