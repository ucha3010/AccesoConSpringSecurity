<%@ page contentType="application/pdf" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="net.sf.jasperreports.engine.*" %>
<%@ page import="net.sf.jasperreports.engine.data.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.damian.pojo.Factura" %>
<%
    try{
    	List<Map<String, Object>> factura = (List<Map<String, Object>>) request.getAttribute("factura");
    	JRDataSource jrDataSource = new JRBeanCollectionDataSource(factura);
    	String jrxmlFile = session.getServletContext().getRealPath("/resources/report/facturaReport.jrxml");
    	InputStream input = new FileInputStream(new File(jrxmlFile));
    	JasperReport jasperReport = JasperCompileManager.compileReport(input);
    	JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, jrDataSource);
        OutputStream outputstream = response.getOutputStream();
    	JasperExportManager.exportReportToPdfStream(jasperPrint, outputstream);
        outputstream.flush();
        outputstream.close();
    }catch(Exception e){
        System.out.println("Error: "+e.getMessage());
    }
%>