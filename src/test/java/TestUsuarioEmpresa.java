package test.java;

import java.math.BigDecimal;
import java.math.MathContext;

import com.damian.service.UsuarioEmpresaService;
import com.damian.service.impl.UsuarioEmpresaServiceImpl;

public class TestUsuarioEmpresa {

	public static void main(String[] args) {
		BigDecimal cien = new BigDecimal(100.25, MathContext.DECIMAL64);
		BigDecimal veinte = new BigDecimal(30, MathContext.DECIMAL64);
		System.out.println(cien.remainder(veinte));
		
		double num = 371.25;
	      double div = 1;
	      double modVal;

	      // Usual mod
	      modVal = num%div;
	       System.out.println(modVal);
	     
	      //Using big decimal
	      BigDecimal bd = new BigDecimal(num);
	      modVal = bd.remainder(new BigDecimal(div)).doubleValue();
	      System.out.println(modVal);
	      
	      BigDecimal number = new BigDecimal("31.54");
	      long iPart = number.longValue();
	      BigDecimal fraccion = number.remainder(BigDecimal.ONE);

	      System.out.println("Integer part = " + iPart);
	      System.out.println("Fractional part = " + fraccion);
	}

}
