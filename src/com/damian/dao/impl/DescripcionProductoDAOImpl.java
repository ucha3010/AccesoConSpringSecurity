package com.damian.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.damian.dao.DescripcionProductoDAO;
import com.damian.pojo.DescripcionProducto;
import com.damian.utils.LocalLogger;

@Repository
public class DescripcionProductoDAOImpl implements DescripcionProductoDAO {

	private JdbcTemplate jdbcTemplate;

	public DescripcionProductoDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private final String TABLA = "descripcionProducto";
	private final String KEY = "idPro";

	@Override
	public DescripcionProducto findById(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<DescripcionProducto>() {

			@Override
			public DescripcionProducto extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return mapeo(rs);
				}

				return null;
			}

		});
	}

	@Override
	public int save(DescripcionProducto descripcionProducto, HttpServletRequest request) {
		int result =  update(descripcionProducto, request);
		if(result == 0) {
			String sql = "INSERT INTO " + TABLA
					+ " (idPro, nombreES, nombreEN, nombrePT, nombreFR, nombreIT, nombreGE, nombreCA, nombreEU, modificadoPor, fechaModificacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			result = jdbcTemplate.update(sql, descripcionProducto.getIdPro(), descripcionProducto.getNombreES(),
					descripcionProducto.getNombreEN(), descripcionProducto.getNombrePT(),
					descripcionProducto.getNombreFR(), descripcionProducto.getNombreIT(),
					descripcionProducto.getNombreGE(), descripcionProducto.getNombreCA(),
					descripcionProducto.getNombreEU(), descripcionProducto.getModificadoPor(),
					descripcionProducto.getFechaModificacion());
		}
		LocalLogger.save(TABLA, descripcionProducto, request);
		return result;
	}

	@Override
	public int update(DescripcionProducto descripcionProducto, HttpServletRequest request) {
		String sql = "UPDATE " + TABLA
				+ " SET nombreES=?, nombreEN=?, nombrePT=?, nombreFR=?, nombreIT=?, nombreGE=?, nombreCA=?, nombreEU=?, modificadoPor=?, fechaModificacion=? "
				+ "WHERE " + KEY + "=?";
		int result = jdbcTemplate.update(sql, descripcionProducto.getNombreES(), descripcionProducto.getNombreEN(),
				descripcionProducto.getNombrePT(), descripcionProducto.getNombreFR(), descripcionProducto.getNombreIT(),
				descripcionProducto.getNombreGE(), descripcionProducto.getNombreCA(), descripcionProducto.getNombreEU(),
				descripcionProducto.getModificadoPor(), descripcionProducto.getFechaModificacion(),
				descripcionProducto.getIdPro());
		LocalLogger.update(TABLA, descripcionProducto, request);
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

	private DescripcionProducto mapeo(ResultSet rs) throws SQLException {
		DescripcionProducto d = new DescripcionProducto();
		d.setIdPro(rs.getInt("idPro"));
		d.setNombreES(rs.getString("nombreES"));
		d.setNombreEN(rs.getString("nombreEN"));
		d.setNombrePT(rs.getString("nombrePT"));
		d.setNombreFR(rs.getString("nombreFR"));
		d.setNombreIT(rs.getString("nombreIT"));
		d.setNombreGE(rs.getString("nombreGE"));
		d.setNombreCA(rs.getString("nombreCA"));
		d.setNombreEU(rs.getString("nombreEU"));
		d.setModificadoPor(rs.getString("modificadoPor"));
		d.setFechaModificacion(rs.getTimestamp("fechaModificacion"));
		return d;
	}
}
