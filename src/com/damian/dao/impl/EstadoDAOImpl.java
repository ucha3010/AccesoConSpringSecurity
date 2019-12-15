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

import com.damian.converter.ConverterEstado;
import com.damian.dao.EstadoDAO;
import com.damian.dao.model.ModelEstado;
import com.damian.pojo.Estado;

@Repository
public class EstadoDAOImpl implements EstadoDAO {

	private JdbcTemplate jdbcTemplate;

	public EstadoDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private final String TABLA = "estado";
	private final String KEY = "idEst";

	@Autowired
	private ConverterEstado converterEstado;

	@Override
	public List<Estado> findAll() {

		String sql = "SELECT * FROM " + TABLA + " ORDER BY nombre ASC";

		return lista(sql);
	}

	@Override
	public Estado findById(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Estado>() {

			@Override
			public Estado extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return converterEstado.convertAll(mapeo(rs));
				}

				return null;
			}

		});
	}

	@Override
	public Estado findByIdModel(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Estado>() {

			@Override
			public Estado extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return converterEstado.convert(mapeo(rs));
				}

				return null;
			}

		});
	}

	@Override
	public int save(Estado estado) {
		if (estado.getIdEst() > 0) {
			return update(estado);
		} else {
			ModelEstado me = converterEstado.convert(estado);
			String sql = "INSERT INTO " + TABLA + " (nombre) VALUES (?)";
			return jdbcTemplate.update(sql, me.getNombre());
		}
	}

	@Override
	public int update(Estado estado) {
		ModelEstado me = converterEstado.convert(estado);
		String sql = "UPDATE " + TABLA + " SET nombre=? " + "WHERE " + KEY + "=?";
		return jdbcTemplate.update(sql, me.getNombre(), me.getIdEst());
	}

	@Override
	public int delete(int id) {

		String sql = "DELETE FROM " + TABLA + " WHERE " + KEY + "=?";
		return jdbcTemplate.update(sql, id);
	}

	private List<Estado> lista(String sql) {
		List<ModelEstado> mpList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ModelEstado.class));
		List<Estado> eList = new ArrayList<>();
		for (ModelEstado me : mpList) {
			eList.add(converterEstado.convertAll(me));
		}
		return eList;
	}

	private ModelEstado mapeo(ResultSet rs) throws SQLException {
		ModelEstado me = new ModelEstado();
		me.setIdEst(rs.getInt("idEst"));
		me.setNombre(rs.getString("nombre"));
		return me;
	}
}
