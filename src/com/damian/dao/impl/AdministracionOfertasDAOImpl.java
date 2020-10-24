package com.damian.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.damian.dao.AdministracionOfertasDAO;
import com.damian.pojo.AdministracionOfertas;
import com.damian.utils.LocalLogger;

@Repository
public class AdministracionOfertasDAOImpl implements AdministracionOfertasDAO {

	private JdbcTemplate jdbcTemplate;

	public AdministracionOfertasDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private final String TABLA = "administracionofertas";
	private final String KEY = "idPro";

	@Override
	public List<AdministracionOfertas> findAll() {

		String sql = "SELECT * FROM " + TABLA + " ORDER BY " + KEY + " ASC";

		return lista(sql);
	}

	@Override
	public AdministracionOfertas findById(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<AdministracionOfertas>() {

			@Override
			public AdministracionOfertas extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return mapeo(rs);
				}

				return null;
			}

		});
	}

	@Override
	public List<AdministracionOfertas> findByOfertas(int cantMax) {

		return lista(generarSql("Oferta", cantMax));
	}

	@Override
	public int getMaxOrdenOferta() {
		return maximo("Oferta");
	}

	@Override
	public List<AdministracionOfertas> findByProductosPopulares(int cantMax) {

		return lista(generarSql("Popular", cantMax));
	}

	@Override
	public int getMaxOrdenPopulares() {
		return maximo("Popular");
	}

	@Override
	public List<AdministracionOfertas> findByNovedades(int cantMax) {

		return lista(generarSql("Novedades", cantMax));
	}

	@Override
	public int getMaxOrdenNovedades() {
		return maximo("Novedades");
	}

	@Override
	public List<AdministracionOfertas> findByCampania(int idCam, int cantMax) {

		String sql = "SELECT * FROM " + TABLA + " WHERE idCam " + (idCam == 0 ? "!" : "") + "= " + idCam + " ORDER BY "
				+ KEY + " DESC";
		if (cantMax > 0) {
			sql = sql.concat(" LIMIT 1," + cantMax);
		}

		return lista(sql);
	}

	@Override
	public int save(AdministracionOfertas administracionOfertas, HttpServletRequest request) {
		String sql = "INSERT INTO " + TABLA
				+ " (idPro, booleanOferta, ordenOferta, precioSinOferta, precioConOferta, fecha, booleanPopular, ordenPopular, booleanNovedades, ordenNovedades, idCam) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int result = jdbcTemplate.update(sql, administracionOfertas.getIdPro(), administracionOfertas.isBooleanOferta(),
				administracionOfertas.getOrdenOferta(), administracionOfertas.getPrecioSinOferta(),
				administracionOfertas.getPrecioConOferta(), administracionOfertas.getFecha(),
				administracionOfertas.isBooleanPopular(), administracionOfertas.getOrdenPopular(),
				administracionOfertas.isBooleanNovedades(), administracionOfertas.getOrdenNovedades(),
				administracionOfertas.getIdCam());
		LocalLogger.save(TABLA, administracionOfertas, request);
		return result;
	}

	@Override
	public int update(AdministracionOfertas administracionOfertas, HttpServletRequest request) {
		String sql = "UPDATE " + TABLA
				+ " SET booleanOferta=?, ordenOferta=?, precioSinOferta=?, precioConOferta=?, fecha=?, booleanPopular=?, ordenPopular=?, "
				+ "booleanNovedades=?, ordenNovedades=?, idCam=? WHERE " + KEY + "=?";
		int result = jdbcTemplate.update(sql, administracionOfertas.isBooleanOferta(),
				administracionOfertas.getOrdenOferta(), administracionOfertas.getPrecioSinOferta(),
				administracionOfertas.getPrecioConOferta(), administracionOfertas.getFecha(),
				administracionOfertas.isBooleanPopular(), administracionOfertas.getOrdenPopular(),
				administracionOfertas.isBooleanNovedades(), administracionOfertas.getOrdenNovedades(),
				administracionOfertas.getIdCam(), administracionOfertas.getIdPro());
		LocalLogger.update(TABLA, administracionOfertas, request);
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
	public List<AdministracionOfertas> findOrderedByOrdenOferta(int ordenOferta) {

		String igual = "";
		if (ordenOferta > 0) {
			igual = "=";
		}
		String sql = "SELECT * FROM " + TABLA + " WHERE ordenOferta >" + igual + " " + ordenOferta
				+ " AND booleanOferta = 1 ORDER BY ordenOferta ASC";

		return lista(sql);
	}

	@Override
	public List<AdministracionOfertas> findOrderedByOrdenPopular(int ordenPopular) {

		String igual = "";
		if (ordenPopular > 0) {
			igual = "=";
		}
		String sql = "SELECT * FROM " + TABLA + " WHERE ordenPopular >" + igual + " " + ordenPopular
				+ " AND booleanPopular = 1 ORDER BY ordenPopular ASC";

		return lista(sql);
	}

	@Override
	public List<AdministracionOfertas> findOrderedByOrdenNovedades(int ordenNovedades) {

		String igual = "";
		if (ordenNovedades > 0) {
			igual = "=";
		}
		String sql = "SELECT * FROM " + TABLA + " WHERE ordenNovedades >" + igual + " " + ordenNovedades
				+ " AND booleanNovedades = 1 ORDER BY ordenNovedades ASC";

		return lista(sql);
	}

	private List<AdministracionOfertas> lista(String sql) {
		List<AdministracionOfertas> aoList = jdbcTemplate.query(sql,
				BeanPropertyRowMapper.newInstance(AdministracionOfertas.class));
		return aoList;
	}

	private AdministracionOfertas mapeo(ResultSet rs) throws SQLException {
		AdministracionOfertas c = new AdministracionOfertas();
		c.setIdPro(rs.getInt("idPro"));
		c.setBooleanOferta(rs.getBoolean("booleanOferta"));
		c.setOrdenOferta(rs.getInt("ordenOferta"));
		c.setPrecioSinOferta(rs.getDouble("precioSinOferta"));
		c.setPrecioConOferta(rs.getDouble("precioConOferta"));
		c.setFecha(rs.getTimestamp("fecha"));
		c.setBooleanPopular(rs.getBoolean("booleanPopular"));
		c.setOrdenPopular(rs.getInt("ordenPopular"));
		c.setBooleanNovedades(rs.getBoolean("booleanNovedades"));
		c.setOrdenNovedades(rs.getInt("ordenNovedades"));
		c.setIdCam(rs.getInt("idCam"));
		return c;
	}

	private String generarSql(String tipo, int cantMax) {
		String sql = "SELECT * FROM " + TABLA + " WHERE boolean" + tipo + " = true";
		if (cantMax > 0) {
			sql = sql.concat(" AND orden" + tipo + " <= " + cantMax);
		}
		sql = sql.concat(" ORDER BY orden" + tipo + " ASC");
		return sql;
	}

	private int maximo(String type) {
		Integer max = jdbcTemplate.queryForObject(
				"SELECT MAX(orden" + type + ") FROM " + TABLA + " WHERE boolean" + type + " = 1", Integer.class);
		if (max == null) {
			return 0;
		} else {
			return max;
		}
	}
}
