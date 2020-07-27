package com.damian.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.damian.dao.EmpresaDAO;
import com.damian.dao.ProductoDAO;
import com.damian.dao.model.ModelProductoEmpresa;
import com.damian.pojo.Empresa;
import com.damian.pojo.Producto;
import com.damian.pojo.ProductoEmpresa;

@Component
public class ConverterProductoEmpresa {

	@Autowired
	private ProductoDAO productoDAO;

	@Autowired
	private EmpresaDAO empresaDAO;

	public ProductoEmpresa convertAll(ModelProductoEmpresa mpe) {

		ProductoEmpresa pe = convert(mpe);
		pe.setProducto(productoDAO.findByIdModel(mpe.getIdPro()));
		pe.setEmpresa(empresaDAO.findByIdModel(mpe.getIdEmp()));

		return pe;

	}

	public ProductoEmpresa convert(ModelProductoEmpresa mpe) {

		ProductoEmpresa pe = new ProductoEmpresa();
		pe.setFechaCreacion(mpe.getFechaCreacion());
		pe.setCreadoPor(mpe.getCreadoPor());
		Producto producto = new Producto();
		producto.setIdPro(mpe.getIdPro());
		pe.setProducto(producto);
		Empresa empresa = new Empresa();
		empresa.setIdEmp(mpe.getIdEmp());
		pe.setEmpresa(empresa);

		return pe;

	}

	public ModelProductoEmpresa convert(ProductoEmpresa pe) {

		ModelProductoEmpresa mpe = new ModelProductoEmpresa();
		if (pe.getProducto() != null) {
			mpe.setIdPro(pe.getProducto().getIdPro());
		}
		if (pe.getEmpresa() != null) {
			mpe.setIdEmp(pe.getEmpresa().getIdEmp());
		}
		mpe.setFechaCreacion(pe.getFechaCreacion());
		mpe.setCreadoPor(pe.getCreadoPor());

		return mpe;

	}

}
