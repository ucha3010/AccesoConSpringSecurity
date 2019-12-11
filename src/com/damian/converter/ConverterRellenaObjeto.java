package com.damian.converter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.damian.dao.DatosPersonalesDAO;
import com.damian.dao.DireccionDao;
import com.damian.dao.DireccionEmpresaDAO;
import com.damian.dao.EmpresaDAO;
import com.damian.dao.ProductoEmpresaDAO;
import com.damian.dao.UsuarioDAO;
import com.damian.dao.UsuarioEmpresaDAO;
import com.damian.dao.UsuarioRolDAO;
import com.damian.dao.model.ModelDatosPersonales;
import com.damian.dao.model.ModelDireccion;
import com.damian.dao.model.ModelDireccionEmpresa;
import com.damian.dao.model.ModelEmpresa;
import com.damian.dao.model.ModelProducto;
import com.damian.dao.model.ModelRol;
import com.damian.dao.model.ModelUsuario;
import com.damian.pojo.DatosPersonales;
import com.damian.pojo.Direccion;
import com.damian.pojo.DireccionEmpresa;
import com.damian.pojo.Empresa;
import com.damian.pojo.Producto;
import com.damian.pojo.ProductoEmpresa;
import com.damian.pojo.Rol;
import com.damian.pojo.Usuario;
import com.damian.pojo.UsuarioEmpresa;
import com.damian.pojo.UsuarioRol;

@Component
public class ConverterRellenaObjeto {

	@Autowired
	private DatosPersonalesDAO datosPersonalesDAO;

	@Autowired
	private DireccionDao direccionDao;

	@Autowired
	private DireccionEmpresaDAO direccionEmpresaDAO;

	@Autowired
	private EmpresaDAO empresaDAO;

	@Autowired
	private UsuarioDAO usuarioDAO;

	@Autowired
	private UsuarioEmpresaDAO usuarioEmpresaDAO;

	@Autowired
	private UsuarioRolDAO usuarioRolDAO;

	@Autowired
	private ProductoEmpresaDAO productoEmpresaDAO;

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
			u.setDatosPersonales(dp);
			List<UsuarioEmpresa> ueList = usuarioEmpresaDAO.findByIdUsrModel(mu.getIdUsr());
			for (UsuarioEmpresa ue : ueList) {
				ue.getEmpresa().setDireccionesEmpresa(direccionEmpresaDAO.findByIdEmpModel(ue.getEmpresa().getIdEmp()));
			}
			if (!ueList.isEmpty()) {
				u.setUsuarioEmpresa(ueList);
			}
			u.setUsuarioRol(usuarioRolDAO.findByIdUsrModel(mu.getIdUsr()));
		}
	}

	public void rellenaProducto(Producto p, ModelProducto mp) {

		List<ProductoEmpresa> peList = productoEmpresaDAO.findByIdProModel(mp.getIdPro());
		for (ProductoEmpresa pe : peList) {
			pe.setEmpresa(empresaDAO.findById(pe.getEmpresa().getIdEmp()));
		}
		if (!peList.isEmpty()) {
			p.setProductoEmpresaList(peList);
		}

	}

}
