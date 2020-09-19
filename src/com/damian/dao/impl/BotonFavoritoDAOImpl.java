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

import com.damian.converter.ConverterBotonFavorito;
import com.damian.dao.BotonFavoritoDAO;
import com.damian.dao.model.ModelBotonFavorito;
import com.damian.pojo.BotonFavorito;
import com.damian.utils.LocalLogger;

@Repository
public class BotonFavoritoDAOImpl implements BotonFavoritoDAO {

	private JdbcTemplate jdbcTemplate;

	public BotonFavoritoDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private final String TABLA = "botonfavorito";
	private final String KEY = "idBot";

	@Autowired
	private ConverterBotonFavorito converterBotonFavorito;

	@Override
	public List<BotonFavorito> findAll() {

		String sql = "SELECT * FROM " + TABLA + " ORDER BY nombre ASC";

		return lista(sql);
	}

	@Override
	public BotonFavorito findById(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<BotonFavorito>() {

			@Override
			public BotonFavorito extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return converterBotonFavorito.convertAll(mapeo(rs));
				}

				return null;
			}

		});
	}

	@Override
	public int save(BotonFavorito botonFavorito, HttpServletRequest request) {
		if (botonFavorito.getIdBot() > 0) {
			return update(botonFavorito, request);
		} else {
			ModelBotonFavorito mb = converterBotonFavorito.convert(botonFavorito);
			String sql = "INSERT INTO " + TABLA + " (nombre) VALUES (?)";
			int result = jdbcTemplate.update(sql, mb.getNombre());
			LocalLogger.save(TABLA, mb, request);
			return result;
		}
	}

	@Override
	public int update(BotonFavorito botonFavorito, HttpServletRequest request) {
		ModelBotonFavorito mb = converterBotonFavorito.convert(botonFavorito);
		String sql = "UPDATE " + TABLA + " SET nombre=? WHERE " + KEY + "=?";
		int result = jdbcTemplate.update(sql, mb.getNombre(), mb.getIdBot());
		LocalLogger.update(TABLA, mb, request);
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

	private List<BotonFavorito> lista(String sql) {
		List<ModelBotonFavorito> mpList = jdbcTemplate.query(sql,
				BeanPropertyRowMapper.newInstance(ModelBotonFavorito.class));
		List<BotonFavorito> eList = new ArrayList<>();
		for (ModelBotonFavorito me : mpList) {
			eList.add(converterBotonFavorito.convertAll(me));
		}
		return eList;
	}

	private ModelBotonFavorito mapeo(ResultSet rs) throws SQLException {
		ModelBotonFavorito mb = new ModelBotonFavorito();
		mb.setIdBot(rs.getInt("idBot"));
		mb.setNombre(rs.getString("nombre"));
		return mb;
	}
}
