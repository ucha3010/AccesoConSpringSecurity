package com.damian.converter;

import org.springframework.stereotype.Component;

import com.damian.dao.model.ModelFoto;
import com.damian.pojo.Categoria;
import com.damian.pojo.Empresa;
import com.damian.pojo.Estado;
import com.damian.pojo.FormaPago;
import com.damian.pojo.Foto;
import com.damian.pojo.Pais;
import com.damian.pojo.Producto;
import com.damian.pojo.Rol;
import com.damian.pojo.Subcategoria;
import com.damian.pojo.Usuario;

@Component
public class ConverterFoto {

	public Foto convertAll(ModelFoto mf) {

		Foto f = convert(mf);

		return f;

	}

	public Foto convert(ModelFoto mf) {
		Foto f = new Foto(mf.getIdFot());

		if (mf.getIdUsr() != 0) {
			f.setUsuario(new Usuario(mf.getIdUsr()));
		}
		if (mf.getIdPro() != 0) {
			f.setProducto(new Producto(mf.getIdPro()));
		}
		if (mf.getIdEmp() != 0) {
			f.setEmpresa(new Empresa(mf.getIdEmp()));
		}
		if (mf.getIdCat() != 0) {
			f.setCategoria(new Categoria(mf.getIdCat()));
		}
		if (mf.getIdSub() != 0) {
			f.setSubcategoria(new Subcategoria(mf.getIdSub()));
		}
		if (mf.getIdPais() != 0) {
			f.setPais(new Pais(mf.getIdPais()));
		}
		if (mf.getIdFor() != 0) {
			f.setFormaPago(new FormaPago(mf.getIdFor()));
		}
		if (mf.getIdEst() != 0) {
			f.setEstado(new Estado(mf.getIdEst()));
		}
		if (mf.getIdRol() != 0) {
			f.setRol(new Rol(mf.getIdRol()));
		}
		f.setNombre(mf.getNombre());
		f.setRuta(mf.getRuta());
		f.setDescripcion(mf.getDescripcion());
		f.setPeso(mf.getPeso());
		f.setPrincipal(mf.isPrincipal());
		f.setSlide(mf.isSlide());
		f.setExtension(mf.getExtension());
		f.setFechaCreacion(mf.getFechaCreacion());
		f.setCreadoPor(mf.getCreadoPor());
		f.setFechaModificacion(mf.getFechaModificacion());
		f.setModificadoPor(mf.getModificadoPor());

		return f;
	}

	public ModelFoto convert(Foto f) {
		ModelFoto mf = new ModelFoto(f.getIdFot());

		if (f.getUsuario() != null) {
			mf.setIdUsr(f.getUsuario().getIdUsr());
		}
		if (f.getProducto() != null) {
			mf.setIdPro(f.getProducto().getIdPro());
		}
		if (f.getEmpresa() != null) {
			mf.setIdEmp(f.getEmpresa().getIdEmp());
		}
		if (f.getCategoria() != null) {
			mf.setIdCat(f.getCategoria().getIdCat());
		}
		if (f.getSubcategoria() != null) {
			mf.setIdSub(f.getSubcategoria().getIdSub());
		}
		if (f.getPais() != null) {
			mf.setIdPais(f.getPais().getIdPais());
		}
		if (f.getFormaPago() != null) {
			mf.setIdFor(f.getFormaPago().getIdFor());
		}
		if (f.getEstado() != null) {
			mf.setIdEst(f.getEstado().getIdEst());
		}
		if (f.getRol() != null) {
			mf.setIdRol(f.getRol().getIdRol());
		}
		mf.setNombre(f.getNombre());
		mf.setRuta(f.getRuta());
		mf.setDescripcion(f.getDescripcion());
		mf.setPeso(f.getPeso());
		mf.setPrincipal(f.isPrincipal());
		mf.setSlide(f.isSlide());
		mf.setExtension(f.getExtension());
		mf.setFechaCreacion(f.getFechaCreacion());
		mf.setCreadoPor(f.getCreadoPor());
		mf.setFechaModificacion(f.getFechaModificacion());
		mf.setModificadoPor(f.getModificadoPor());

		return mf;
	}

}
