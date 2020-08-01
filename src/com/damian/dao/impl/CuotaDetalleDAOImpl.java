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

import com.damian.converter.ConverterCuotaDetalle;
import com.damian.dao.CuotaDetalleDAO;
import com.damian.dao.model.ModelCuotaDetalle;
import com.damian.pojo.CuotaDetalle;
import com.damian.utils.LocalLogger;

@Repository
public class CuotaDetalleDAOImpl implements CuotaDetalleDAO {

	private JdbcTemplate jdbcTemplate;

	public CuotaDetalleDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private final String TABLA = "cuotadetalle";
	private final String KEY = "idCuDe";

	@Autowired
	private ConverterCuotaDetalle converterCuotaDetalle;

	@Override
	public List<CuotaDetalle> findAll() {

		String sql = "SELECT * FROM " + TABLA;

		return lista(sql);
	}

	@Override
	public CuotaDetalle findById(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<CuotaDetalle>() {

			@Override
			public CuotaDetalle extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return converterCuotaDetalle.convertAll(mapeo(rs));
				}

				return null;
			}

		});
	}

	@Override
	public CuotaDetalle findByIdModel(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<CuotaDetalle>() {

			@Override
			public CuotaDetalle extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return converterCuotaDetalle.convert(mapeo(rs));
				}

				return null;
			}

		});
	}

	@Override
	public int save(CuotaDetalle cuotaDetalle, HttpServletRequest request) {
		if (cuotaDetalle.getIdCuDe() > 0) {
			return update(cuotaDetalle, request);
		} else {
			ModelCuotaDetalle mcd = converterCuotaDetalle.convert(cuotaDetalle);
			String sql = "INSERT INTO " + TABLA
					+ " (idCuo, importeSinInteres, importeInteres, importeCuota, fecha, capitalPendienteAntes, capitalPendienteDespues, numeroCuota)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			jdbcTemplate.update(sql, mcd.getIdCuo(), mcd.getImporteSinInteres(), mcd.getImporteInteres(),
					mcd.getImporteCuota(), mcd.getFecha(), mcd.getCapitalPendienteAntes(),
					mcd.getCapitalPendienteDespues(), mcd.getNumeroCuota());
			LocalLogger.save(TABLA, mcd, request);
			return getMaxId();
		}
	}

	@Override
	public int update(CuotaDetalle cuotaDetalle, HttpServletRequest request) {
		ModelCuotaDetalle mcd = converterCuotaDetalle.convert(cuotaDetalle);
		String sql = "UPDATE " + TABLA
				+ " SET idCuo=?, importeSinInteres=?, importeInteres=?, importeCuota=?, fecha=?, capitalPendienteAntes=?, capitalPendienteDespues=?, numeroCuota=? "
				+ "WHERE " + KEY + "=?";
		int result = jdbcTemplate.update(sql, mcd.getIdCuo(), mcd.getImporteSinInteres(), mcd.getImporteInteres(),
				mcd.getImporteCuota(), mcd.getFecha(), mcd.getCapitalPendienteAntes(), mcd.getCapitalPendienteDespues(),
				mcd.getNumeroCuota(), mcd.getIdCuDe());
		LocalLogger.update(TABLA, mcd, request);
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
	public List<CuotaDetalle> findByIdCuo(int idCuo) {

		String sql = "SELECT * FROM " + TABLA + " WHERE idCuo=" + idCuo;

		return lista(sql);
	}

	@Override
	public List<ModelCuotaDetalle> findModelByIdCuo(int idCuo) {

		String sql = "SELECT * FROM " + TABLA + " WHERE idCuo=" + idCuo;
		
		return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ModelCuotaDetalle.class));
	}

	private List<CuotaDetalle> lista(String sql) {
		List<ModelCuotaDetalle> mpList = jdbcTemplate.query(sql,
				BeanPropertyRowMapper.newInstance(ModelCuotaDetalle.class));
		List<CuotaDetalle> eList = new ArrayList<>();
		for (ModelCuotaDetalle mc : mpList) {
			eList.add(converterCuotaDetalle.convertAll(mc));
		}
		return eList;
	}

	private ModelCuotaDetalle mapeo(ResultSet rs) throws SQLException {
		ModelCuotaDetalle mc = new ModelCuotaDetalle();
		mc.setIdCuDe(rs.getInt("idCuDe"));
		mc.setIdCuo(rs.getInt("idCuo"));
		mc.setImporteSinInteres(rs.getDouble("importeSinInteres"));
		mc.setImporteInteres(rs.getDouble("importeInteres"));
		mc.setImporteCuota(rs.getDouble("importeCuota"));
		mc.setFecha(rs.getDate("fecha"));
		mc.setCapitalPendienteAntes(rs.getDouble("capitalPendienteAntes"));
		mc.setCapitalPendienteDespues(rs.getDouble("capitalPendienteDespues"));
		mc.setNumeroCuota(rs.getInt("numeroCuota"));
		return mc;
	}
}
