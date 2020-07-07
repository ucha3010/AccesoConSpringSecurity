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

import com.damian.converter.ConverterFacturaEnviarFacturar;
import com.damian.dao.FacturaEnviarFacturarDAO;
import com.damian.dao.model.ModelFacturaEnviarFacturar;
import com.damian.pojo.FacturaEnviarFacturar;
import com.damian.utils.LocalLogger;

@Repository
public class FacturaEnviarFacturarDAOImpl implements FacturaEnviarFacturarDAO {

	private JdbcTemplate jdbcTemplate;

	public FacturaEnviarFacturarDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private final String TABLA = "facturaenviarfacturar";
	private final String KEY = "idEnFa";

	@Autowired
	private ConverterFacturaEnviarFacturar converterFacturaEnviarFacturar;

	@Override
	public FacturaEnviarFacturar findById(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<FacturaEnviarFacturar>() {

			@Override
			public FacturaEnviarFacturar extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return converterFacturaEnviarFacturar.convert(mapeo(rs));
				}

				return null;
			}

		});
	}

	@Override
	public int save(FacturaEnviarFacturar facturaEnviarFacturar, HttpServletRequest request) {
		if (facturaEnviarFacturar.getIdEnFa() > 0) {
			return update(facturaEnviarFacturar, request);
		} else {
			ModelFacturaEnviarFacturar mfef = converterFacturaEnviarFacturar.convert(facturaEnviarFacturar);
			String sql = "INSERT INTO " + TABLA
					+ " (nombre, direccion, cp, ciudad, provincia, pais, telefono, facturar, " + "enviar, idFac) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			int result = jdbcTemplate.update(sql, mfef.getNombre(), mfef.getDireccion(), mfef.getCp(), mfef.getCiudad(),
					mfef.getProvincia(), mfef.getPais(), mfef.getTelefono(), mfef.isFacturar(), mfef.isEnviar(),
					mfef.getIdFac());
			LocalLogger.save(TABLA, facturaEnviarFacturar, request);
			return result;
		}
	}

	@Override
	public int update(FacturaEnviarFacturar facturaEnviarFacturar, HttpServletRequest request) {
		ModelFacturaEnviarFacturar mfef = converterFacturaEnviarFacturar.convert(facturaEnviarFacturar);
		String sql = "UPDATE " + TABLA
				+ " SET nombre=?, direccion=?, cp=?, ciudad=?, provincia=?, pais=?, telefono=?, facturar=?, "
				+ "enviar=?, idFac=? WHERE " + KEY + "=?";
		int result = jdbcTemplate.update(sql, mfef.getNombre(), mfef.getDireccion(), mfef.getCp(), mfef.getCiudad(),
				mfef.getProvincia(), mfef.getPais(), mfef.getTelefono(), mfef.isFacturar(), mfef.isEnviar(),
				mfef.getIdFac(), mfef.getIdEnFa());
		LocalLogger.update(TABLA, facturaEnviarFacturar, request);
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
	public List<FacturaEnviarFacturar> findByIdFac(int idFac) {

		String sql = "SELECT * FROM " + TABLA + " WHERE idFac = " + idFac;
		return lista(sql);
	}

	private List<FacturaEnviarFacturar> lista(String sql) {
		List<ModelFacturaEnviarFacturar> mfefList = jdbcTemplate.query(sql,
				BeanPropertyRowMapper.newInstance(ModelFacturaEnviarFacturar.class));
		List<FacturaEnviarFacturar> eList = new ArrayList<>();
		for (ModelFacturaEnviarFacturar mfef : mfefList) {
			eList.add(converterFacturaEnviarFacturar.convert(mfef));
		}
		return eList;
	}

	private ModelFacturaEnviarFacturar mapeo(ResultSet rs) throws SQLException {
		ModelFacturaEnviarFacturar mfef = new ModelFacturaEnviarFacturar();
		mfef.setIdEnFa(rs.getInt("idEnFa"));
		mfef.setNombre(rs.getString("nombre"));
		mfef.setDireccion(rs.getString("direccion"));
		mfef.setCp(rs.getString("cp"));
		mfef.setCiudad(rs.getString("ciudad"));
		mfef.setProvincia(rs.getString("provincia"));
		mfef.setPais(rs.getString("pais"));
		mfef.setTelefono(rs.getString("telefono"));
		mfef.setFacturar(rs.getBoolean("facturar"));
		mfef.setEnviar(rs.getBoolean("enviar"));
		mfef.setIdFac(rs.getInt("idFac"));
		return mfef;
	}

}
