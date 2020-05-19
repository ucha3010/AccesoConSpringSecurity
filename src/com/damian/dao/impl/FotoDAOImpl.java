package com.damian.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.damian.dao.FotoDAO;
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

@Repository
public class FotoDAOImpl implements FotoDAO {

	private JdbcTemplate jdbcTemplate;

	public FotoDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private final String TABLA = "foto";
	private final String KEY = "idFot";

	@Override
	public Foto findByIdFot(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Foto>() {

			@Override
			public Foto extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return convert(mapeo(rs));
				}

				return null;
			}

		});
	}

	@Override
	public List<Foto> findByIdUsr(int idUsr) {
		String sql = "SELECT * FROM " + TABLA + " WHERE idUsr=" + idUsr;
		return lista(sql);
	}

	@Override
	public List<Foto> findByIdPro(int idPro) {
		String sql = "SELECT * FROM " + TABLA + " WHERE idPro=" + idPro;
		return lista(sql);
	}

	@Override
	public List<Foto> findByIdEmp(int idEmp) {
		String sql = "SELECT * FROM " + TABLA + " WHERE idEmp=" + idEmp;
		return lista(sql);
	}

	@Override
	public List<Foto> findByIdCat(int idCat) {
		String sql = "SELECT * FROM " + TABLA + " WHERE idCat=" + idCat;
		return lista(sql);
	}

	@Override
	public List<Foto> findByIdSub(int idSub) {
		String sql = "SELECT * FROM " + TABLA + " WHERE idSub=" + idSub;
		return lista(sql);
	}

	@Override
	public List<Foto> findByIdPais(int idPais) {
		String sql = "SELECT * FROM " + TABLA + " WHERE idPais=" + idPais;
		return lista(sql);
	}

	@Override
	public List<Foto> findByIdFor(int idFor) {
		String sql = "SELECT * FROM " + TABLA + " WHERE idFor=" + idFor;
		return lista(sql);
	}

	@Override
	public List<Foto> findByIdEst(int idEst) {
		String sql = "SELECT * FROM " + TABLA + " WHERE idEst=" + idEst;
		return lista(sql);
	}

	@Override
	public List<Foto> findByIdRol(int idRol) {
		String sql = "SELECT * FROM " + TABLA + " WHERE idRol=" + idRol;
		return lista(sql);
	}

	@Override
	public int save(Foto foto) {
		if (foto.getIdFot() > 0) {
			return update(foto);
		} else {
			ModelFoto mf = convert(foto);
			String sql = "INSERT INTO " + TABLA
					+ " (idUsr, idPro, idEmp, idCat, idSub, idPais, idFor, idEst, idRol, nombre, ruta, descripcion, peso, principal, "
					+ "extension, fechaCreacion, creadoPor, fechaModificacion, modificadoPor) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			return jdbcTemplate.update(sql, mf.getIdUsr(), mf.getIdPro(), mf.getIdEmp(), mf.getIdCat(), mf.getIdSub(),
					mf.getIdPais(), mf.getIdFor(), mf.getIdEst(), mf.getIdRol(), mf.getNombre(), mf.getRuta(), mf.getDescripcion(),
					mf.getPeso(), mf.isPrincipal(), mf.getExtension(), mf.getFechaCreacion(), mf.getCreadoPor(),
					mf.getFechaModificacion(), mf.getModificadoPor());
		}
	}

	@Override
	public int update(Foto foto) {
		ModelFoto mf = convert(foto);
		String sql = "UPDATE " + TABLA
				+ " SET idUsr=?, idPro=?, idEmp=?, idCat=?, idSub=?, idPais=?, idFor=?, idEst=?, idRol=?, nombre=?, ruta=?, descripcion=?, "
				+ "peso=?, principal=?, extension=?, fechaCreacion=?, creadoPor=?, fechaModificacion=?, modificadoPor=? "
				+ "WHERE " + KEY + "=?";
		return jdbcTemplate.update(sql, mf.getIdUsr(), mf.getIdPro(), mf.getIdEmp(), mf.getIdCat(), mf.getIdSub(),
				mf.getIdPais(), mf.getIdFor(), mf.getIdEst(), mf.getIdRol(), mf.getNombre(), mf.getRuta(), mf.getDescripcion(),
				mf.getPeso(), mf.isPrincipal(), mf.getExtension(), mf.getFechaCreacion(), mf.getCreadoPor(),
				mf.getFechaModificacion(), mf.getModificadoPor(), mf.getIdFot());
	}

	@Override
	public int delete(int id) {

		String sql = "DELETE FROM " + TABLA + " WHERE " + KEY + "=?";
		return jdbcTemplate.update(sql, id);
	}

	@Override
	public int getMaxId() {
		return jdbcTemplate.queryForObject("SELECT MAX(" + KEY + ") FROM " + TABLA, Integer.class);
	}

	private List<Foto> lista(String sql) {
		List<ModelFoto> mfList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ModelFoto.class));
		List<Foto> fList = new ArrayList<>();
		for (ModelFoto mf : mfList) {
			fList.add(convert(mf));
		}
		return fList;
	}

	private ModelFoto mapeo(ResultSet rs) throws SQLException {
		ModelFoto f = new ModelFoto();
		f.setIdFot(rs.getInt("idFot"));
		f.setIdUsr(rs.getInt("idUsr"));
		f.setIdPro(rs.getInt("idPro"));
		f.setIdEmp(rs.getInt("idEmp"));
		f.setIdCat(rs.getInt("idCat"));
		f.setIdSub(rs.getInt("idSub"));
		f.setIdPais(rs.getInt("idPais"));
		f.setIdFor(rs.getInt("idFor"));
		f.setIdEst(rs.getInt("idEst"));
		f.setIdRol(rs.getInt("idRol"));
		f.setNombre(rs.getString("nombre"));
		f.setRuta(rs.getString("ruta"));
		f.setDescripcion(rs.getString("descripcion"));
		f.setPeso(rs.getInt("peso"));
		f.setPrincipal(rs.getBoolean("principal"));
		f.setExtension(rs.getString("extension"));
		f.setFechaCreacion(rs.getTimestamp("fechaCreacion"));
		f.setCreadoPor(rs.getString("creadoPor"));
		f.setFechaModificacion(rs.getTimestamp("fechaModificacion"));
		f.setModificadoPor(rs.getString("modificadoPor"));

		return f;
	}

	private Foto convert(ModelFoto mf) {
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
		f.setExtension(mf.getExtension());
		f.setFechaCreacion(mf.getFechaCreacion());
		f.setCreadoPor(mf.getCreadoPor());
		f.setFechaModificacion(mf.getFechaModificacion());
		f.setModificadoPor(mf.getModificadoPor());

		return f;
	}

	private ModelFoto convert(Foto f) {
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
		mf.setExtension(f.getExtension());
		mf.setFechaCreacion(f.getFechaCreacion());
		mf.setCreadoPor(f.getCreadoPor());
		mf.setFechaModificacion(f.getFechaModificacion());
		mf.setModificadoPor(f.getModificadoPor());

		return mf;
	}
}
