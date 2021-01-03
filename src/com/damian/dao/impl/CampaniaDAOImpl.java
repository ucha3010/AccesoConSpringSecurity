package com.damian.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.damian.dao.CampaniaDAO;
import com.damian.pojo.Campania;
import com.damian.utils.LocalLogger;

@Repository
public class CampaniaDAOImpl implements CampaniaDAO {

	private JdbcTemplate jdbcTemplate;

	public CampaniaDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private final String TABLA = "campania";
	private final String KEY = "idCam";

	@Override
	public List<Campania> findAll() {

		String sql = "SELECT * FROM " + TABLA + " ORDER BY fechaInicio DESC";

		return lista(sql);
	}

	@Override
	public Campania findById(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Campania>() {

			@Override
			public Campania extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return mapeo(rs);
				}

				return null;
			}

		});
	}

	@Override
	public int save(Campania campania, HttpServletRequest request) {
		if (campania.getIdCam() > 0) {
			return update(campania, request);
		} else {
			String sql = "INSERT INTO " + TABLA
					+ " (nombre, fechaInicio, fechaFin, descuentoPor, descripcion) VALUES (?, ?, ?, ?, ?)";
			int result = jdbcTemplate.update(sql, campania.getNombre(), campania.getFechaInicio(),
					campania.getFechaFin(), campania.getDescuentoPor(), campania.getDescripcion());
			LocalLogger.save(TABLA, campania, request);
			return result;
		}
	}

	@Override
	public int update(Campania campania, HttpServletRequest request) {
		String sql = "UPDATE " + TABLA
				+ " SET nombre=?, fechaInicio=?, fechaFin=?, descuentoPor=?, descripcion=? WHERE " + KEY + "=?";
		int result = jdbcTemplate.update(sql, campania.getNombre(), campania.getFechaInicio(), campania.getFechaFin(),
				campania.getDescuentoPor(), campania.getDescripcion(), campania.getIdCam());
		LocalLogger.update(TABLA, campania, request);
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

	@Override
	public String getCampaignName(Integer idPro) {
		String sql = "SELECT C.nombre FROM administracionofertas A, campania C WHERE A.idPro = ? AND A.idCam <> 0 AND A.idCam = C.idCam";
		String nombre = null;
		try {
			nombre = (String) jdbcTemplate.queryForObject(sql, new Integer[] { idPro }, String.class);
		} catch (EmptyResultDataAccessException r) {

		}
		return nombre;
	}

	private List<Campania> lista(String sql) {
		List<Campania> cList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Campania.class));
		return cList;
	}

	private Campania mapeo(ResultSet rs) throws SQLException {
		Campania c = new Campania();
		c.setIdCam(rs.getInt("idCam"));
		c.setNombre(rs.getString("nombre"));
		c.setFechaInicio(rs.getTimestamp("fechaInicio"));
		c.setFechaFin(rs.getTimestamp("fechaFin"));
		c.setDescuentoPor(rs.getInt("descuentoPor"));
		c.setDescripcion(rs.getString("descripcion"));
		return c;
	}
}
