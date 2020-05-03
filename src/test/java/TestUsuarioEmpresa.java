package test.java;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Date;

import com.damian.service.UsuarioEmpresaService;
import com.damian.service.impl.UsuarioEmpresaServiceImpl;

public class TestUsuarioEmpresa {

	public static void main(String[] args) {
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
	}

	private static BigDecimal cortaADosDecimales(BigDecimal num) {
		BigDecimal cien = new BigDecimal(100, MathContext.DECIMAL64);
		BigDecimal salida = num.multiply(cien);
		long iPart = salida.longValue();
		salida = new BigDecimal(iPart, MathContext.DECIMAL64);
		return salida.divide(cien, 2, RoundingMode.HALF_UP);
	}

}
