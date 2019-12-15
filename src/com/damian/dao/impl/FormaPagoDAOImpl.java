package com.damian.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

		String sql = "SELECT * FROM " + TABLA + " ORDER BY nombre ASC";

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
	public int save(FormaPago formaPago) {
		if (formaPago.getIdFor() > 0) {
			return update(formaPago);
		} else {
			ModelFormaPago me = converterFormaPago.convert(formaPago);
			String sql = "INSERT INTO " + TABLA + " (nombre) VALUES (?)";
			return jdbcTemplate.update(sql, me.getNombre());
		}
	}

	@Override
	public int update(FormaPago formaPago) {
		ModelFormaPago me = converterFormaPago.convert(formaPago);
		String sql = "UPDATE " + TABLA + " SET nombre=? " + "WHERE " + KEY + "=?";
		return jdbcTemplate.update(sql, me.getNombre(), me.getIdFor());
	}

	@Override
	public int delete(int id) {

		String sql = "DELETE FROM " + TABLA + " WHERE " + KEY + "=?";
		return jdbcTemplate.update(sql, id);
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
		mf.setNombre(rs.getString("nombre"));
		return mf;
	}
}
