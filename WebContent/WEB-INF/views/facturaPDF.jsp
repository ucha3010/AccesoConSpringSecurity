<%@ page contentType="application/pdf" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="net.sf.jasperreports.engine.*" %>
<%@ page import="net.sf.jasperreports.engine.data.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.damian.dao.model.ModelFactura" %>
<%@ page import="com.damian.dao.model.ModelCuotaDetalle" %>
<%
    try{
    	Map<String, Object> params = new HashMap<String, Object>();
    	ModelFactura factura = (ModelFactura) request.getAttribute("factura");
    	List<ModelCuotaDetalle> cuotaDetalleList = (List<ModelCuotaDetalle>) request.getAttribute("cuotaDetalleList");
    	params.put("idFac", factura.getIdFac());
    	params.put("ivaImporteTotal", factura.getIvaImporteTotal());
    	params.put("descuentoTotal", factura.getDescuentoTotal());
    	params.put("descuentoImporteTotal", factura.getDescuentoImporteTotal());
    	params.put("importeEnvioSinIva", factura.getImporteEnvioSinIva());
    	params.put("importeTotal", factura.getImporteTotal());
    	params.put("fechaCompra", factura.getFechaCompra());
    	params.put("observaciones", factura.getObservaciones());
    	params.put("cuotaDetalleList", cuotaDetalleList);
    	String jrxmlFile = session.getServletContext().getRealPath("/resources/report/facturaReport.jrxml");
    	InputStream input = new FileInputStream(new File(jrxmlFile));
    	JasperReport jasperReport = JasperCompileManager.compileReport(input);
    	JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,params, new JREmptyDataSource());
        OutputStream outputstream = response.getOutputStream();
    	JasperExportManager.exportReportToPdfStream(jasperPrint, outputstream);
        outputstream.flush();
        outputstream.close();
    }catch(Exception e){
        System.out.println("Error: "+e.getMessage());
    }
%>