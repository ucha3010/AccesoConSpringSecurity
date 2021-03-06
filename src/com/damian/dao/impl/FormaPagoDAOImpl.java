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

import com.damian.converter.ConverterFormaPago;
import com.damian.dao.FormaPagoDAO;
import com.damian.dao.model.ModelFormaPago;
import com.damian.pojo.FormaPago;
import com.damian.utils.LocalLogger;

@Repository
public class FormaPagoDAOImpl implements FormaPagoDAO {

	private JdbcTemplate jdbcTemplate;

	public FormaPagoDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private final String TABLA = "formaPago";
	private final String KEY = "idFor";

	@Autowired
	private ConverterFormaPago converterFormaPago;

	@Override
	public List<FormaPago> findAll() {

		String sql = "SELECT * FROM " + TABLA + " ORDER BY nombreES ASC";

		return lista(sql);
	}

	@Override
	public FormaPago findById(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<FormaPago>() {

			@Override
			public FormaPago extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return converterFormaPago.convertAll(mapeo(rs));
				}

				return null;
			}

		});
	}

	@Override
	public FormaPago findByIdModel(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<FormaPago>() {

			@Override
			public FormaPago extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return converterFormaPago.convert(mapeo(rs));
				}

				return null;
			}

		});
	}

	@Override
	public int save(FormaPago formaPago, HttpServletRequest request) {
		if (formaPago.getIdFor() > 0) {
			return update(formaPago, request);
		} else {
			ModelFormaPago mfp = converterFormaPago.convert(formaPago);
			String sql = "INSERT INTO " + TABLA
					+ " (nombreES, nombreEN, nombrePT, nombreFR, nombreIT, nombreGE, nombreCA, nombreEU, modificadoPor, fechaModificacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			int result = jdbcTemplate.update(sql, mfp.getNombreES(), mfp.getNombreEN(), mfp.getNombrePT(),
					mfp.getNombreFR(), mfp.getNombreIT(), mfp.getNombreGE(), mfp.getNombreCA(), mfp.getNombreEU(),
					mfp.getModificadoPor(), mfp.getFechaModificacion());
			LocalLogger.save(TABLA, mfp, request);
			return result;
		}
	}

	@Override
	public int update(FormaPago formaPago, HttpServletRequest request) {
		ModelFormaPago mfp = converterFormaPago.convert(formaPago);
		String sql = "UPDATE " + TABLA
				+ " SET nombreES=?, nombreEN=?, nombrePT=?, nombreFR=?, nombreIT=?, nombreGE=?, nombreCA=?, nombreEU=?, modificadoPor=?, fechaModificacion=? "
				+ "WHERE " + KEY + "=?";
		int result = jdbcTemplate.update(sql, mfp.getNombreES(), mfp.getNombreEN(), mfp.getNombrePT(), mfp.getNombreFR(),
				mfp.getNombreIT(), mfp.getNombreGE(), mfp.getNombreCA(), mfp.getNombreEU(), mfp.getModificadoPor(),
				mfp.getFechaModificacion(), mfp.getIdFor());
		LocalLogger.update(TABLA, mfp, request);
		return result;
	}

	@Override
	public int delete(int id, HttpServletRequest request) {

		Object object = findById(id);
		String sql = "DELETE FROM " + TABLA + " WHERE " + KEY + "=?";
		int result = jdbcTemplate.update(sql, id);
		LocalLogger.delete(TABLA, object, request);
		return result;
	}

	@Override
	public int getMaxId() {
		return jdbcTemplate.queryForObject("SELECT MAX(" + KEY + ") FROM " + TABLA, Integer.class);
	}

	private List<FormaPago> lista(String sql) {
		List<ModelFormaPago> mpList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ModelFormaPago.class));
		List<FormaPago> eList = new ArrayList<>();
		for (ModelFormaPago mf : mpList) {
			eList.add(converterFormaPago.convertAll(mf));
		}
		return eList;
	}

	private ModelFormaPago mapeo(ResultSet rs) throws SQLException {
		ModelFormaPago mf = new ModelFormaPago();
		mf.setIdFor(rs.getInt("idFor"));
		mf.setNombreES(rs.getString("nombreES"));
		mf.setNombreEN(rs.getString("nombreEN"));
		mf.setNombrePT(rs.getString("nombrePT"));
		mf.setNombreFR(rs.getString("nombreFR"));
		mf.setNombreIT(rs.getString("nombreIT"));
		mf.setNombreGE(rs.getString("nombreGE"));
		mf.setNombreCA(rs.getString("nombreCA"));
		mf.setNombreEU(rs.getString("nombreEU"));
		mf.setModificadoPor(rs.getString("modificadoPor"));
		mf.setFechaModificacion(rs.getTimestamp("fechaModificacion"));
		return mf;
	}
}
