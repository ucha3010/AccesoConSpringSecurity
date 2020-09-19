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

import com.damian.converter.ConverterTema;
import com.damian.dao.TemaDAO;
import com.damian.dao.model.ModelTema;
import com.damian.pojo.Tema;
import com.damian.utils.LocalLogger;

@Repository
public class TemaDAOImpl implements TemaDAO {

	private JdbcTemplate jdbcTemplate;

	public TemaDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private final String TABLA = "tema";
	private final String KEY = "idTem";

	@Autowired
	private ConverterTema converterTema;

	@Override
	public List<Tema> findAll() {

		String sql = "SELECT * FROM " + TABLA + " ORDER BY nombre ASC";

		return lista(sql);
	}

	@Override
	public Tema findById(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Tema>() {

			@Override
			public Tema extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return converterTema.convertAll(mapeo(rs));
				}

				return null;
			}

		});
	}

	@Override
	public int save(Tema tema, HttpServletRequest request) {
		if (tema.getIdTem() > 0) {
			return update(tema, request);
		} else {
			ModelTema mb = converterTema.convert(tema);
			String sql = "INSERT INTO " + TABLA + " (nombre) VALUES (?)";
			int result = jdbcTemplate.update(sql, mb.getNombre());
			LocalLogger.save(TABLA, mb, request);
			return result;
		}
	}

	@Override
	public int update(Tema tema, HttpServletRequest request) {
		ModelTema mb = converterTema.convert(tema);
		String sql = "UPDATE " + TABLA + " SET nombre=? WHERE " + KEY + "=?";
		int result = jdbcTemplate.update(sql, mb.getNombre(), mb.getIdTem());
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

	private List<Tema> lista(String sql) {
		List<ModelTema> mpList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ModelTema.class));
		List<Tema> eList = new ArrayList<>();
		for (ModelTema me : mpList) {
			eList.add(converterTema.convertAll(me));
		}
		return eList;
	}

	private ModelTema mapeo(ResultSet rs) throws SQLException {
		ModelTema mb = new ModelTema();
		mb.setIdTem(rs.getInt("idTem"));
		mb.setNombre(rs.getString("nombre"));
		return mb;
	}
}
