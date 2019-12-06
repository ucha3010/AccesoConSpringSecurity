package test.java;

import com.damian.service.UsuarioEmpresaService;
import com.damian.service.impl.UsuarioEmpresaServiceImpl;

public class TestUsuarioEmpresa {

	public static void main(String[] args) {
		UsuarioEmpresaService ues = new UsuarioEmpresaServiceImpl();
		ues.delete(58, 2);
	}

}
