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

import com.damian.converter.ConverterCuota;
import com.damian.dao.CuotaDAO;
import com.damian.dao.model.ModelCuota;
import com.damian.pojo.Cuota;
import com.damian.utils.LocalLogger;

@Repository
public class CuotaDAOImpl implements CuotaDAO {

	private JdbcTemplate jdbcTemplate;

	public CuotaDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private final String TABLA = "cuota";
	private final String KEY = "idCuo";

	@Autowired
	private ConverterCuota converterCuota;

	@Override
	public List<Cuota> findAll() {

		String sql = "SELECT * FROM " + TABLA + " ORDER BY nombreES ASC";

		return lista(sql);
	}

	@Override
	public Cuota findById(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Cuota>() {

			@Override
			public Cuota extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return converterCuota.convertAll(mapeo(rs));
				}

				return null;
			}

		});
	}

	@Override
	public Cuota findByIdModel(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Cuota>() {

			@Override
			public Cuota extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return converterCuota.convert(mapeo(rs));
				}

				return null;
			}

		});
	}

	@Override
	public int save(Cuota cuota, HttpServletRequest request) {
		if (cuota.getIdCuo() > 0) {
			return update(cuota, request);
		} else {
			ModelCuota mc = converterCuota.convert(cuota);
			String sql = "INSERT INTO " + TABLA
					+ " (cantidadCuotas, comisionAperturaPor, comisionAperturaImp, interesPor, interesImp, modificadoPor, fechaModificacion) VALUES (?, ?, ?, ?, ?, ?, ?)";
			jdbcTemplate.update(sql, mc.getCantidadCuotas(), mc.getComisionAperturaPor(), mc.getComisionAperturaImp(),
					mc.getInteresPor(), mc.getInteresImp(), mc.getModificadoPor(), mc.getFechaModificacion());
			return getMaxId();
		}
	}

	@Override
	public int update(Cuota cuota, HttpServletRequest request) {
		ModelCuota mc = converterCuota.convert(cuota);
		String sql = "UPDATE " + TABLA
				+ " SET cantidadCuotas=?, comisionAperturaPor=?, comisionAperturaImp=?, interesPor=?, interesImp=?, modificadoPor=?, fechaModificacion=? "
				+ "WHERE " + KEY + "=?";
		return jdbcTemplate.update(sql, mc.getCantidadCuotas(), mc.getComisionAperturaPor(),
				mc.getComisionAperturaImp(), mc.getInteresPor(), mc.getInteresImp(), mc.getModificadoPor(),
				mc.getFechaModificacion(), mc.getIdCuo());
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

	private List<Cuota> lista(String sql) {
		List<ModelCuota> mpList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ModelCuota.class));
		List<Cuota> eList = new ArrayList<>();
		for (ModelCuota mc : mpList) {
			eList.add(converterCuota.convertAll(mc));
		}
		return eList;
	}

	private ModelCuota mapeo(ResultSet rs) throws SQLException {
		ModelCuota mc = new ModelCuota();
		mc.setIdCuo(rs.getInt("idCuo"));
		mc.setCantidadCuotas(rs.getInt("cantidadCuotas"));
		mc.setComisionAperturaPor(rs.getDouble("comisionAperturaPor"));
		mc.setComisionAperturaImp(rs.getDouble("comisionAperturaImp"));
		mc.setInteresPor(rs.getDouble("interesPor"));
		mc.setInteresImp(rs.getDouble("interesImp"));
		mc.setModificadoPor(rs.getString("modificadoPor"));
		mc.setFechaModificacion(rs.getTimestamp("fechaModificacion"));
		return mc;
	}
}
