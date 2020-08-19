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

import com.damian.converter.ConverterDireccionEmpresaPropia;
import com.damian.dao.DireccionEmpresaPropiaDAO;
import com.damian.dao.model.ModelDireccionEmpresaPropia;
import com.damian.pojo.DireccionEmpresaPropia;
import com.damian.utils.LocalLogger;

@Repository
public class DireccionEmpresaPropiaDAOImpl implements DireccionEmpresaPropiaDAO {

	private JdbcTemplate jdbcTemplate;

	public DireccionEmpresaPropiaDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private final String TABLA = "direccionempresapropia";
	private final String KEY = "idDirPropia";

	@Autowired
	private ConverterDireccionEmpresaPropia converterDireccionEmpresaPropia;

	@Override
	public List<DireccionEmpresaPropia> findAll() {

		String sql = "SELECT * FROM " + TABLA;

		List<ModelDireccionEmpresaPropia> mdepList = jdbcTemplate.query(sql,
				BeanPropertyRowMapper.newInstance(ModelDireccionEmpresaPropia.class));
		List<DireccionEmpresaPropia> depList = new ArrayList<>();
		for (ModelDireccionEmpresaPropia mdep : mdepList) {
			depList.add(converterDireccionEmpresaPropia.convertAll(mdep));
		}

		return depList;

	}

	@Override
	public DireccionEmpresaPropia findById(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<DireccionEmpresaPropia>() {

			@Override
			public DireccionEmpresaPropia extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return converterDireccionEmpresaPropia.convertAll(mapeo(rs));
				}

				return null;
			}

		});

	}

	@Override
	public DireccionEmpresaPropia findByIdModel(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<DireccionEmpresaPropia>() {

			@Override
			public DireccionEmpresaPropia extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return converterDireccionEmpresaPropia.convert(mapeo(rs));
				}

				return null;
			}

		});
	}

	@Override
	public List<DireccionEmpresaPropia> findByIdPropiaModel(int idPropia) {

		String sql = "SELECT * FROM " + TABLA + " WHERE idPropia=" + idPropia;

		List<ModelDireccionEmpresaPropia> mdepList = jdbcTemplate.query(sql,
				BeanPropertyRowMapper.newInstance(ModelDireccionEmpresaPropia.class));
		List<DireccionEmpresaPropia> dpList = new ArrayList<>();
		for (ModelDireccionEmpresaPropia mdep : mdepList) {
			dpList.add(converterDireccionEmpresaPropia.convert(mdep));
		}

		return dpList;
	}

	@Override
	public void save(DireccionEmpresaPropia direccionEmpresaPropia, HttpServletRequest request) {

//		if (direccionEmpresaPropia.getIdDirPropia() == 0) {
			ModelDireccionEmpresaPropia mde = converterDireccionEmpresaPropia.convert(direccionEmpresaPropia);
			String sql = "REPLACE INTO " + TABLA + " (" + KEY + ", tipoVia, nombreVia, numero, resto, cp, provincia, "
					+ "ciudad, pais_idPais, idPropia, modificadoPor, fechaModificacion)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			jdbcTemplate.update(sql, mde.getIdDirPropia(), mde.getTipoVia(), mde.getNombreVia(), mde.getNumero(), mde.getResto(), mde.getCp(),
					mde.getProvincia(), mde.getCiudad(), mde.getPais_idPais(), mde.getIdPropia(),
					mde.getModificadoPor(), mde.getFechaModificacion());
			LocalLogger.save(TABLA, mde, request);
//		} else {
//			update(direccionEmpresaPropia, request);
//		}

	}

	@Override
	public void update(DireccionEmpresaPropia direccionEmpresaPropia, HttpServletRequest request) {

		ModelDireccionEmpresaPropia mde = converterDireccionEmpresaPropia.convert(direccionEmpresaPropia);
		String sql = "UPDATE " + TABLA + " SET tipoVia=?, nombreVia=?, numero=?, resto=?, cp=?, provincia=?, "
				+ "ciudad=?, pais_idPais=?, idPropia=?, modificadoPor=?, fechaModificacion=? WHERE " + KEY + "=?";
		jdbcTemplate.update(sql, mde.getTipoVia(), mde.getNombreVia(), mde.getNumero(), mde.getResto(), mde.getCp(),
				mde.getProvincia(), mde.getCiudad(), mde.getPais_idPais(), mde.getIdPropia(), mde.getModificadoPor(),
				mde.getFechaModificacion(), mde.getIdDirPropia());
		LocalLogger.update(TABLA, mde, request);

	}

	@Override
	public void delete(int id, HttpServletRequest request) {

		Object object = findById(id);
		String sql = "DELETE FROM " + TABLA + " WHERE " + KEY + "=?";
		jdbcTemplate.update(sql, id);
		LocalLogger.delete(TABLA, object, request);
	}

	@Override
	public int getMaxId() {
		return jdbcTemplate.queryForObject("SELECT MAX(" + KEY + ") FROM " + TABLA, Integer.class);
	}

	private ModelDireccionEmpresaPropia mapeo(ResultSet rs) throws SQLException {
		ModelDireccionEmpresaPropia mde = new ModelDireccionEmpresaPropia();
		mde.setIdDirPropia(rs.getInt("idDirPropia"));
		mde.setTipoVia(rs.getString("tipoVia"));
		mde.setNombreVia(rs.getString("nombreVia"));
		mde.setNumero(rs.getString("numero"));
		mde.setResto(rs.getString("resto"));
		mde.setCp(rs.getString("cp"));
		mde.setProvincia(rs.getString("provincia"));
		mde.setCiudad(rs.getString("ciudad"));
		mde.setPais_idPais(rs.getInt("pais_idPais"));
		mde.setIdPropia(rs.getInt("idPropia"));
		mde.setModificadoPor(rs.getString("modificadoPor"));
		mde.setFechaModificacion(rs.getTimestamp("fechaModificacion"));
		return mde;
	}

}
