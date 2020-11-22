package com.damian.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.damian.converter.ConverterFoto;
import com.damian.dao.FotoDAO;
import com.damian.dao.model.ModelFoto;
import com.damian.pojo.Foto;
import com.damian.utils.LocalLogger;

@Repository
public class FotoDAOImpl implements FotoDAO {

	private JdbcTemplate jdbcTemplate;

	public FotoDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private final String TABLA = "foto";
	private final String KEY = "idFot";

	@Autowired
	private ConverterFoto converterFoto;

	@Override
	public Foto findByIdFot(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Foto>() {

			@Override
			public Foto extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return converterFoto.convert(mapeo(rs));
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
	public List<Foto> findByIdPropia(int idPropia) {
		String sql = "SELECT * FROM " + TABLA + " WHERE idPropia=" + idPropia;
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
	public List<Foto> findBySlide() {
		String sql = "SELECT * FROM " + TABLA + " WHERE slide = true";
		return lista(sql);
	}

	@Override
	public int save(Foto foto, HttpServletRequest request) {
		if (foto.getIdFot() > 0) {
			return update(foto, request);
		} else {
			ModelFoto mf = converterFoto.convert(foto);
			String sql = "INSERT INTO " + TABLA
					+ " (idUsr, idPro, idEmp, idPropia, idCat, idSub, idPais, idFor, idEst, idRol, nombre, ruta, descripcion, peso, principal, "
					+ "slide, extension, fechaCreacion, creadoPor, fechaModificacion, modificadoPor) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			int result = jdbcTemplate.update(sql, mf.getIdUsr(), mf.getIdPro(), mf.getIdEmp(), mf.getIdPropia(),
					mf.getIdCat(), mf.getIdSub(), mf.getIdPais(), mf.getIdFor(), mf.getIdEst(), mf.getIdRol(),
					mf.getNombre(), mf.getRuta(), mf.getDescripcion(), mf.getPeso(), mf.isPrincipal(), mf.isSlide(),
					mf.getExtension(), mf.getFechaCreacion(), mf.getCreadoPor(), mf.getFechaModificacion(),
					mf.getModificadoPor());
			LocalLogger.save(TABLA, mf, request);
			return result;
		}
	}

	@Override
	public int update(Foto foto, HttpServletRequest request) {
		ModelFoto mf = converterFoto.convert(foto);
		String sql = "UPDATE " + TABLA
				+ " SET idUsr=?, idPro=?, idEmp=?, idPropia=?, idCat=?, idSub=?, idPais=?, idFor=?, idEst=?, idRol=?, nombre=?, ruta=?, descripcion=?, "
				+ "peso=?, principal=?, slide=?, extension=?, fechaCreacion=?, creadoPor=?, fechaModificacion=?, modificadoPor=? "
				+ "WHERE " + KEY + "=?";
		int result = jdbcTemplate.update(sql, mf.getIdUsr(), mf.getIdPro(), mf.getIdEmp(), mf.getIdPropia(),
				mf.getIdCat(), mf.getIdSub(), mf.getIdPais(), mf.getIdFor(), mf.getIdEst(), mf.getIdRol(),
				mf.getNombre(), mf.getRuta(), mf.getDescripcion(), mf.getPeso(), mf.isPrincipal(), mf.isSlide(),
				mf.getExtension(), mf.getFechaCreacion(), mf.getCreadoPor(), mf.getFechaModificacion(),
				mf.getModificadoPor(), mf.getIdFot());
		LocalLogger.update(TABLA, mf, request);
		return result;
	}

	@Override
	public int delete(int id, HttpServletRequest request) {

		Object object = findByIdFot(id);
		String sql = "DELETE FROM " + TABLA + " WHERE " + KEY + "=?";
		int result = jdbcTemplate.update(sql, id);
		LocalLogger.delete(TABLA, object, request);
		return result;
	}

	@Override
	public int getMaxId() {
		return jdbcTemplate.queryForObject("SELECT MAX(" + KEY + ") FROM " + TABLA, Integer.class);
	}

	private List<Foto> lista(String sql) {
		List<ModelFoto> mfList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ModelFoto.class));
		List<Foto> fList = new ArrayList<>();
		for (ModelFoto mf : mfList) {
			fList.add(converterFoto.convert(mf));
		}
		return fList;
	}

	private ModelFoto mapeo(ResultSet rs) throws SQLException {
		ModelFoto mf = new ModelFoto();
		mf.setIdFot(rs.getInt("idFot"));
		mf.setIdUsr(rs.getInt("idUsr"));
		mf.setIdPro(rs.getInt("idPro"));
		mf.setIdEmp(rs.getInt("idEmp"));
		mf.setIdPropia(rs.getInt("idPropia"));
		mf.setIdCat(rs.getInt("idCat"));
		mf.setIdSub(rs.getInt("idSub"));
		mf.setIdPais(rs.getInt("idPais"));
		mf.setIdFor(rs.getInt("idFor"));
		mf.setIdEst(rs.getInt("idEst"));
		mf.setIdRol(rs.getInt("idRol"));
		mf.setNombre(rs.getString("nombre"));
		mf.setRuta(rs.getString("ruta"));
		mf.setDescripcion(rs.getString("descripcion"));
		mf.setPeso(rs.getInt("peso"));
		mf.setPrincipal(rs.getBoolean("principal"));
		mf.setSlide(rs.getBoolean("slide"));
		mf.setExtension(rs.getString("extension"));
		mf.setFechaCreacion(rs.getTimestamp("fechaCreacion"));
		mf.setCreadoPor(rs.getString("creadoPor"));
		mf.setFechaModificacion(rs.getTimestamp("fechaModificacion"));
		mf.setModificadoPor(rs.getString("modificadoPor"));

		return mf;
	}
}
