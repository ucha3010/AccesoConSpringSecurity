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

import com.damian.converter.ConverterRol;
import com.damian.dao.RolDAO;
import com.damian.dao.model.ModelRol;
import com.damian.pojo.Rol;
import com.damian.utils.LocalLogger;

@Repository
public class RolDAOImpl implements RolDAO {

	private JdbcTemplate jdbcTemplate;

	public RolDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private final String TABLA = "rol";
	private final String KEY = "idRol";

	@Autowired
	private ConverterRol converterRol;

	@Override
	public Rol findById(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Rol>() {

			@Override
			public Rol extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return converterRol.convertAll(mapeo(rs));
				}

				return null;
			}

		});
	}

	@Override
	public List<Rol> findAll() {

		String sql = "SELECT * FROM " + TABLA + " ORDER BY rol";

		return lista(sql);
	}

	@Override
	public void save(Rol rol, HttpServletRequest request) {
		ModelRol mr = converterRol.convert(rol);
		if (rol.getIdRol() > 0) {
			String sql = "UPDATE " + TABLA + " SET rol=?, modificadoPor=?, fechaModificacion=? WHERE " + KEY + "=?";
			jdbcTemplate.update(sql, mr.getRol(), mr.getModificadoPor(), mr.getFechaModificacion(), mr.getIdRol());
		} else {
			String sql = "INSERT INTO " + TABLA + " (rol, modificadoPor, fechaModificacion)" + " VALUES (?, ?, ?)";
			jdbcTemplate.update(sql, mr.getRol(), mr.getModificadoPor(), mr.getFechaModificacion());
		}
	}

	@Override
	public void delete(int id, HttpServletRequest request) {

		Object object = findById(id);
		String sql = "DELETE FROM " + TABLA + " WHERE " + KEY + "=?";
		jdbcTemplate.update(sql, id);
		LocalLogger.delete(TABLA, object, request);
	}

	@Override
	public List<Rol> findByRolName(String rolName) {

		String sql = "SELECT * FROM " + TABLA + " WHERE rol LIKE '" + rolName + "'";

		return lista(sql);
	}

	@Override
	public Rol findByIdModel(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Rol>() {

			@Override
			public Rol extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return converterRol.convert(mapeo(rs));
				}

				return null;
			}

		});
	}

	@Override
	public int getMaxId() {
		return jdbcTemplate.queryForObject("SELECT MAX(" + KEY + ") FROM " + TABLA, Integer.class);
	}

	private List<Rol> lista(String sql) {
		List<ModelRol> mrList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ModelRol.class));
		List<Rol> rList = new ArrayList<>();
		for (ModelRol mr : mrList) {
			rList.add(converterRol.convertAll(mr));
		}

		return rList;
	}

	private ModelRol mapeo(ResultSet rs) throws SQLException {
		ModelRol mr = new ModelRol();
		mr.setIdRol(rs.getInt("idRol"));
		mr.setRol(rs.getString("rol"));
		mr.setFechaModificacion(rs.getTimestamp("fechaModificacion"));
		mr.setModificadoPor(rs.getString("modificadoPor"));
		return mr;
	}

}
