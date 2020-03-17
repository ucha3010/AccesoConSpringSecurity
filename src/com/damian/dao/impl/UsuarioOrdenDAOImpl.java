package com.damian.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.damian.dao.UsuarioOrdenDAO;
import com.damian.dao.model.ModelUsuarioOrden;

@Repository
public class UsuarioOrdenDAOImpl implements UsuarioOrdenDAO {

	private JdbcTemplate jdbcTemplate;

	public UsuarioOrdenDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private final String TABLA = "usuarioorden";
	private final String KEY = "id";
	private final String KEY2 = "idUsr";
	private final String KEY3 = "tabla";

	@Override
	public ModelUsuarioOrden findByIdUsrTabla(int idUsr, String tabla) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY2 + "=" + idUsr + " AND " + KEY3 + "  LIKE '" + tabla
				+ "'";
		return jdbcTemplate.query(sql, new ResultSetExtractor<ModelUsuarioOrden>() {

			@Override
			public ModelUsuarioOrden extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return mapeo(rs);
				}

				return null;
			}

		});
	}

	@Override
	public int save(ModelUsuarioOrden uo) {
		String sql = "INSERT INTO " + TABLA + " (idUsr, tabla, columna, orden) VALUES (?, ?, ?, ?)";
		return jdbcTemplate.update(sql, uo.getIdUsr(), uo.getTabla(), uo.getColumna(), uo.getOrden());

	}

	@Override
	public int update(ModelUsuarioOrden uo) {
		String sql = "UPDATE " + TABLA + " SET idUsr=?, tabla=?, columna=?, orden=? " + "WHERE " + KEY + "=?";
		return jdbcTemplate.update(sql, uo.getIdUsr(), uo.getTabla(), uo.getColumna(), uo.getOrden(), uo.getId());
	}

	@Override
	public int getMaxId() {
		return jdbcTemplate.queryForObject("SELECT MAX(" + KEY + ") FROM " + TABLA, Integer.class);
	}

	private ModelUsuarioOrden mapeo(ResultSet rs) throws SQLException {
		ModelUsuarioOrden uo = new ModelUsuarioOrden();
		uo.setId(rs.getInt("id"));
		uo.setIdUsr(rs.getInt("idUsr"));
		uo.setTabla(rs.getString("tabla"));
		uo.setColumna(rs.getString("columna"));
		uo.setOrden(rs.getString("orden"));
		return uo;
	}
}
