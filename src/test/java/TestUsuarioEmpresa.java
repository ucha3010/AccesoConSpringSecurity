package test.java;

import java.io.File;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;

public class TestUsuarioEmpresa {

	public static void main(String[] args) {
		try {
		BigDecimal cienConVeintiCinco = new BigDecimal(100.25, MathContext.DECIMAL64);
		BigDecimal veinte = new BigDecimal(30, MathContext.DECIMAL64);
		System.out.println(cienConVeintiCinco.remainder(veinte));

		double num = 371.25;
		double div = 1;
		double modVal;

		// Usual mod
		modVal = num % div;
		System.out.println(modVal);

		// Using big decimal
		BigDecimal bd = new BigDecimal(num);
		modVal = bd.remainder(new BigDecimal(div)).doubleValue();
		System.out.println(modVal);

		BigDecimal number = new BigDecimal("31.54");
		long iPart = number.longValue();
		BigDecimal fraccion = number.remainder(BigDecimal.ONE);

		System.out.println("Integer part = " + iPart);
		System.out.println("Fractional part = " + fraccion);

		BigDecimal conComa = new BigDecimal(256.25064, MathContext.DECIMAL64);
		System.out.println("conComa: " + conComa);
		System.out.println("conComa: " + cortaADosDecimales(conComa));
		System.out.println("conComa: " + conComa.divide(BigDecimal.ONE, 2, RoundingMode.DOWN));
		
		Date date = new Date();
		System.out.println(date.getTime());
		
		System.out.println("***********************************************************");
		int a = 235;
		int b = 100;
		int c = (int) Math.ceil(a/b);
		int d = (int) Math.ceil((double)a/(double)b);
		System.out.println(c + ", " + d);
		
		System.out.println("***********************************************************");
		String sql = "SELECT * FROM usuario ORDER BY usuario ASC LIMIT 0,10";
		System.out.println(sql.indexOf("BY usuario"));
		
//		LocalLogger.log(TestUsuarioEmpresa.class.getName(), "usuario", "el primer mensaje a poner en el archivo");
//		LocalLogger.save("tablaUsuario", "usuario.toString()");
//		LocalLogger.update("tablaUsuario", "usuario.toString()");
//		LocalLogger.delete("usuario", "tablaUsuario", 15);
//		LocalLogger.logInUser("usuario_logueado");
//		LocalLogger.logError(TestUsuarioEmpresa.class.getName(), "te mandaste una cagada");
		
		System.out.println(getDate(new Date().getTime(),"yyyyMMdd"));
		System.out.println("***********************************************************");
		
		Locale locale = new Locale("es", "ES", "AL");
		String basePath = new File("").getAbsolutePath();
		System.out.println(basePath);
		System.out.println("***********************************************************");
		
		Properties properties = System.getProperties();
		Enumeration<Object> enumeration = properties.keys();
		for (int i = 0; i < properties.size(); i++) {
		    Object obj = enumeration.nextElement();
		    System.out.println("Key: "+obj+"\tOutPut= "+System.getProperty(obj.toString()));
		}
		System.out.println("***********************************************************");
		System.out.println(System.getProperty("user.dir"));
		
		} catch (Exception e) {
			
		}
		
	}
	
	public static String getDate(long milliSeconds, String dateFormat){
	    SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTimeInMillis(milliSeconds);
	    return formatter.format(calendar.getTime());
	}

	private static BigDecimal cortaADosDecimales(BigDecimal num) {
		BigDecimal cien = new BigDecimal(100, MathContext.DECIMAL64);
		BigDecimal salida = num.multiply(cien);
		long iPart = salida.longValue();
		salida = new BigDecimal(iPart, MathContext.DECIMAL64);
		return salida.divide(cien, 2, RoundingMode.HALF_UP);
	}

}
