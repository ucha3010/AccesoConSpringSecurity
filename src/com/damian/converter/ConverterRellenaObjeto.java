package com.damian.converter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.damian.dao.CategoriaDAO;
import com.damian.dao.CuotaDAO;
import com.damian.dao.CuotaDetalleDAO;
import com.damian.dao.DatosPersonalesDAO;
import com.damian.dao.DireccionDao;
import com.damian.dao.DireccionEmpresaDAO;
import com.damian.dao.EmpresaDAO;
import com.damian.dao.EstadoDAO;
import com.damian.dao.FacturaDAO;
import com.damian.dao.FormaPagoDAO;
import com.damian.dao.PaisDAO;
import com.damian.dao.ProductoDAO;
import com.damian.dao.ProductoEmpresaDAO;
import com.damian.dao.ProductoFacturaDAO;
import com.damian.dao.SubcategoriaDAO;
import com.damian.dao.UsuarioDAO;
import com.damian.dao.UsuarioEmpresaDAO;
import com.damian.dao.UsuarioRolDAO;
import com.damian.dao.model.ModelCategoria;
import com.damian.dao.model.ModelCuota;
import com.damian.dao.model.ModelDatosPersonales;
import com.damian.dao.model.ModelDireccion;
import com.damian.dao.model.ModelDireccionEmpresa;
import com.damian.dao.model.ModelEmpresa;
import com.damian.dao.model.ModelEstado;
import com.damian.dao.model.ModelFactura;
import com.damian.dao.model.ModelFormaPago;
import com.damian.dao.model.ModelProducto;
import com.damian.dao.model.ModelRol;
import com.damian.dao.model.ModelSubcategoria;
import com.damian.dao.model.ModelUsuario;
import com.damian.pojo.Categoria;
import com.damian.pojo.Cuota;
import com.damian.pojo.DatosPersonales;
import com.damian.pojo.Direccion;
import com.damian.pojo.DireccionEmpresa;
import com.damian.pojo.Empresa;
import com.damian.pojo.Estado;
import com.damian.pojo.Factura;
import com.damian.pojo.FormaPago;
import com.damian.pojo.Producto;
import com.damian.pojo.ProductoEmpresa;
import com.damian.pojo.ProductoFactura;
import com.damian.pojo.Rol;
import com.damian.pojo.Subcategoria;
import com.damian.pojo.Usuario;
import com.damian.pojo.UsuarioEmpresa;
import com.damian.pojo.UsuarioRol;
import com.damian.service.FotoService;

@Component
public class ConverterRellenaObjeto {

	@Autowired
	private DatosPersonalesDAO datosPersonalesDAO;

	@Autowired
	private CategoriaDAO categoriaDAO;
	
	@Autowired
	private ConverterDatosPersonales converterDatosPersonales;
	
	@Autowired
	private ConverterUsuario converterUsuario;
	
	@Autowired
	private CuotaDAO cuotaDAO;
	
	@Autowired
	private CuotaDetalleDAO cuotaDetalleDAO;

	@Autowired
	private DireccionDao direccionDao;

	@Autowired
	private DireccionEmpresaDAO direccionEmpresaDAO;

	@Autowired
	private EmpresaDAO empresaDAO;

	@Autowired
	private EstadoDAO estadoDAO;

	@Autowired
	private FacturaDAO facturaDAO;

	@Autowired
	private FormaPagoDAO formaPagoDAO;
	
	@Autowired
	private FotoService fotoService;

	@Autowired
	private PaisDAO paisDAO;

	@Autowired
	private ProductoDAO productoDAO;

	@Autowired
	private ProductoEmpresaDAO productoEmpresaDAO;

	@Autowired
	private ProductoFacturaDAO productoFacturaDAO;

	@Autowired
	private SubcategoriaDAO subcategoriaDAO;

	@Autowired
	private UsuarioDAO usuarioDAO;

	@Autowired
	private UsuarioEmpresaDAO usuarioEmpresaDAO;

	@Autowired
	private UsuarioRolDAO usuarioRolDAO;

	public void rellenaDatosPersonales(DatosPersonales dp, ModelDatosPersonales mdp) {

		Usuario u = usuarioDAO.findByIdModel(mdp.getDatospersonales_idUsr());
		List<UsuarioEmpresa> ueList = usuarioEmpresaDAO.findByIdUsrModel(mdp.getDatospersonales_idUsr());
		for (UsuarioEmpresa ue : ueList) {
			ue.getEmpresa().setDireccionesEmpresa(direccionEmpresaDAO.findByIdEmpModel(ue.getEmpresa().getIdEmp()));
		}
		if (!ueList.isEmpty()) {
			u.setUsuarioEmpresa(ueList);
		}
		u.setUsuarioRol(usuarioRolDAO.findByIdUsrModel(mdp.getDatospersonales_idUsr()));

		dp.setUsuario(u);
		dp.setDirecciones(direccionDao.findListFromUsuarioModel(dp.getIdDatosPers()));
	}

	public void rellenaDireccion(Direccion d, ModelDireccion md) {

		DatosPersonales dp = datosPersonalesDAO.findByIdModel(md.getIdDatosPers());
		Usuario u = usuarioDAO.findByIdModel(dp.getUsuario().getIdUsr());
		List<UsuarioEmpresa> ueList = usuarioEmpresaDAO.findByIdUsrModel(dp.getUsuario().getIdUsr());
		for (UsuarioEmpresa ue : ueList) {
			ue.getEmpresa().setDireccionesEmpresa(direccionEmpresaDAO.findByIdEmpModel(ue.getEmpresa().getIdEmp()));
		}
		if (!ueList.isEmpty()) {
			u.setUsuarioEmpresa(ueList);
		}
		u.setUsuarioRol(usuarioRolDAO.findByIdUsrModel(dp.getUsuario().getIdUsr()));
		if (dp.getNacionalidad() != null) {
			dp.setNacionalidad(paisDAO.findById(dp.getNacionalidad().getIdPais()));
		}
		dp.setUsuario(u);
		d.setDatosPersonales(dp);

	}

	public void rellenaDireccionEmpresa(DireccionEmpresa de, ModelDireccionEmpresa mde) {

		Empresa e = empresaDAO.findByIdModel(mde.getIdEmp());
		List<UsuarioEmpresa> ueList = usuarioEmpresaDAO.findByIdEmpModel(e.getIdEmp());
		for (UsuarioEmpresa ue : ueList) {
			DatosPersonales dp = datosPersonalesDAO.findByUsrIdModel(ue.getUsuario().getIdUsr());
			dp.setDirecciones(direccionDao.findListFromUsuarioModel(dp.getIdDatosPers()));
			ue.getUsuario().setDatosPersonales(dp);
			ue.getUsuario().setUsuarioRol(usuarioRolDAO.findByIdUsrModel(ue.getUsuario().getIdUsr()));
		}
		if (!ueList.isEmpty()) {
			e.setUsuarioEmpresa(ueList);
		}
		de.setEmpresa(e);

	}

	public void rellenaEmpresa(Empresa e, ModelEmpresa me) {

		List<UsuarioEmpresa> ueList = usuarioEmpresaDAO.findByIdEmpModel(e.getIdEmp());
		for (UsuarioEmpresa ue : ueList) {
			DatosPersonales dp = datosPersonalesDAO.findByUsrIdModel(ue.getUsuario().getIdUsr());
			dp.setDirecciones(direccionDao.findListFromUsuarioModel(dp.getIdDatosPers()));
			ue.getUsuario().setDatosPersonales(dp);
			ue.getUsuario().setUsuarioRol(usuarioRolDAO.findByIdUsrModel(ue.getUsuario().getIdUsr()));
		}
		if (!ueList.isEmpty()) {
			e.setUsuarioEmpresa(ueList);
		}
		e.setDireccionesEmpresa(direccionEmpresaDAO.findByIdEmpModel(me.getIdEmp()));
		e.setProductoEmpresaList(productoEmpresaDAO.findByIdEmp(e.getIdEmp()));

	}

	public void rellenaRol(Rol r, ModelRol mr) {

		List<UsuarioRol> urList = usuarioRolDAO.findByIdRolModel(mr.getIdRol());
		for (UsuarioRol ur : urList) {
			DatosPersonales dp = datosPersonalesDAO.findByUsrIdModel(ur.getUsuario().getIdUsr());
			dp.setDirecciones(direccionDao.findListFromUsuarioModel(dp.getIdDatosPers()));
			ur.getUsuario().setDatosPersonales(dp);
			List<UsuarioEmpresa> ueList = usuarioEmpresaDAO.findByIdUsrModel(dp.getUsuario().getIdUsr());
			for (UsuarioEmpresa ue : ueList) {
				ue.getEmpresa().setDireccionesEmpresa(direccionEmpresaDAO.findByIdEmpModel(ue.getEmpresa().getIdEmp()));
			}
			if (!ueList.isEmpty()) {
				ur.getUsuario().setUsuarioEmpresa(ueList);
			}
		}
		if (!urList.isEmpty()) {
			r.setUsuarioRol(urList);
		}

	}

	public void rellenaUsuario(Usuario u, ModelUsuario mu) {

		DatosPersonales dp = datosPersonalesDAO.findByUsrIdModel(mu.getIdUsr());
		if (dp != null) {
			dp.setDirecciones(direccionDao.findListFromUsuarioModel(dp.getIdDatosPers()));
			if (dp.getNacionalidad() != null) {
				dp.setNacionalidad(paisDAO.findById(dp.getNacionalidad().getIdPais()));
			}
			u.setDatosPersonales(dp);
			List<UsuarioEmpresa> ueList = usuarioEmpresaDAO.findByIdUsrModel(mu.getIdUsr());
			for (UsuarioEmpresa ue : ueList) {
				ue.getEmpresa().setDireccionesEmpresa(direccionEmpresaDAO.findByIdEmpModel(ue.getEmpresa().getIdEmp()));
			}
			if (!ueList.isEmpty()) {
				u.setUsuarioEmpresa(ueList);
			}
			u.setUsuarioRol(usuarioRolDAO.findByIdUsrModel(mu.getIdUsr()));
			u.setFotos(fotoService.findByIdUsr(mu.getIdUsr()));
		}
	}

	public Usuario rellenaUsuarioLista(ModelUsuario mu, ModelDatosPersonales mdp) {

		DatosPersonales dp = converterDatosPersonales.convert(mdp);
		if (dp.getNacionalidad().getIdPais() != 0) {
			dp.setNacionalidad(paisDAO.findById(dp.getNacionalidad().getIdPais()));
		}
		Usuario u = converterUsuario.convert(mu);
		u.setUsuarioRol(usuarioRolDAO.findByIdUsrModel(mu.getIdUsr()));
		u.setDatosPersonales(dp);
		
		return u;
	}

	public void rellenaProducto(Producto p, ModelProducto mp) {

		List<ProductoEmpresa> peList = productoEmpresaDAO.findByIdProModel(mp.getIdPro());
		for (ProductoEmpresa pe : peList) {
			pe.setEmpresa(empresaDAO.findById(pe.getEmpresa().getIdEmp()));
		}
		if (!peList.isEmpty()) {
			p.setProductoEmpresaList(peList);
		}

		List<ProductoFactura> pfList = productoFacturaDAO.findByIdProModel(mp.getIdPro());
		for (ProductoFactura pf : pfList) {
			pf.setFactura(facturaDAO.findById(pf.getFactura().getIdFac()));
		}
		if (!pfList.isEmpty()) {
			p.setProductoFacturaList(pfList);
		}

		p.setSubcategoria(subcategoriaDAO.findByIdModel(mp.getIdSub()));

	}

	public void rellenaEstado(Estado e, ModelEstado me) {

		e.setFacturas(facturaDAO.findByIdEstModel(me.getIdEst(), null, null));

	}

	public void rellenaFormaPago(FormaPago f, ModelFormaPago mf) {

		f.setFacturas(facturaDAO.findByIdForModel(mf.getIdFor()));

	}

	public void rellenaFactura(Factura f, ModelFactura mf) {

		f.setProductoFacturaList(productoFacturaDAO.findByIdFacModel(mf.getIdFac()));
		f.setEstado(estadoDAO.findById(mf.getIdEst()));
		f.setFormaPago(formaPagoDAO.findById(mf.getIdFor()));
		if(mf.getIdCuo() != 0) {
			f.setCuota(cuotaDAO.findById(mf.getIdCuo()));
		}

	}

	public void rellenaCategoria(Categoria c, ModelCategoria mc) {

		c.setSubcategorias(subcategoriaDAO.findByIdCatModel(mc.getIdCat()));

	}

	public void rellenaSubcategoria(Subcategoria s, ModelSubcategoria ms, boolean cargoCategoria) {

		if (cargoCategoria) {
			s.setCategoria(categoriaDAO.findByIdModel(ms.getIdCat()));
		}
		s.setProductos(productoDAO.findByIdSubModel(ms.getIdSub()));

	}

	public void rellenaCuota(Cuota c, ModelCuota mc) {
		
		c.setCuotaDetalles(cuotaDetalleDAO.findByIdCuo(mc.getIdCuo()));
		
	}

}
