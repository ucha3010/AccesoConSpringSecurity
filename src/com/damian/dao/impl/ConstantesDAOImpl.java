package com.damian.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.damian.converter.ConverterConstantes;
import com.damian.dao.ConstantesDAO;
import com.damian.dao.model.ModelConstantes;
import com.damian.pojo.Constantes;
import com.damian.utils.LocalLogger;

@Repository
public class ConstantesDAOImpl implements ConstantesDAO {

	private JdbcTemplate jdbcTemplate;

	public ConstantesDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private final String TABLA = "constantes";
	private final String KEY = "clave";

	@Autowired
	private ConverterConstantes converterConstantes;

	@Override
	public List<Constantes> findAll() {

		String sql = "SELECT * FROM " + TABLA + " ORDER BY " + KEY + " ASC";

		return lista(sql);
	}

	@Override
	public Constantes findByClave(String clave) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + " LIKE '" + clave + "'";
		return jdbcTemplate.query(sql, new ResultSetExtractor<Constantes>() {

			@Override
			public Constantes extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return converterConstantes.convertAll(mapeo(rs));
				}

				return null;
			}

		});
	}

	@Override
	public List<Constantes> findValorString(String valor) {

		String sql = "SELECT * FROM " + TABLA + " WHERE valorString100 LIKE '" + valor + "' ORDER BY " + KEY + " ASC";

		return lista(sql);
	}

	@Override
	public List<Constantes> findValorDouble(double valor) {

		String sql = "SELECT * FROM " + TABLA + " WHERE valorDouble = " + valor + " ORDER BY " + KEY + " ASC";

		return lista(sql);
	}

	@Override
	public int save(Constantes constantes, HttpServletRequest request) throws DuplicateKeyException {
		ModelConstantes mc = converterConstantes.convert(constantes);
		String sql = "INSERT INTO " + TABLA
				+ " (clave, valorDouble, valorString100, valorText, modificadoPor, fechaModificacion) VALUES (?, ?, ?, ?, ?, ?)";
		int result = 0;
		try {
			result = jdbcTemplate.update(sql, mc.getClave(), mc.getValorDouble(), mc.getValorString100(),
					mc.getValorText(), mc.getModificadoPor(), mc.getFechaModificacion());
			LocalLogger.save(TABLA, mc, request);
		} catch (DuplicateKeyException dke) {
			LocalLogger.logError("INSTENTO DE INSERCIÓN EN TABLA " + TABLA + " DE clave " + constantes.getClave()
					+ " PRODUCIENDO DuplicateKeyException", request);
			throw new DuplicateKeyException("INSTENTO DE INSERCIÓN EN TABLA " + TABLA + " DE clave "
					+ constantes.getClave() + " PRODUCIENDO DuplicateKeyException");
		}
		return result;
	}

	@Override
	public int update(Constantes constantes, HttpServletRequest request) {
		ModelConstantes mc = converterConstantes.convert(constantes);
		String sql = "UPDATE " + TABLA
				+ " SET valorDouble=?, valorString100=?, valorText=?, modificadoPor=?, fechaModificacion=? " + "WHERE "
				+ KEY + "=?";
		int result = jdbcTemplate.update(sql, mc.getValorDouble(), mc.getValorString100(), mc.getValorText(),
				mc.getModificadoPor(), mc.getFechaModificacion(), mc.getClave());
		LocalLogger.update(TABLA, mc, request);
		return result;
	}

	@Override
	public int delete(String clave, HttpServletRequest request) {

		Object object = findByClave(clave);
		String sql = "DELETE FROM " + TABLA + " WHERE " + KEY + "=?";
		int result = jdbcTemplate.update(sql, clave);
		LocalLogger.delete(TABLA, object, request);
		return result;
	}

	private List<Constantes> lista(String sql) {
		List<ModelConstantes> mpList = jdbcTemplate.query(sql,
				BeanPropertyRowMapper.newInstance(ModelConstantes.class));
		List<Constantes> eList = new ArrayList<>();
		for (ModelConstantes me : mpList) {
			eList.add(converterConstantes.convertAll(me));
		}
		return eList;
	}

	private ModelConstantes mapeo(ResultSet rs) throws SQLException {
		ModelConstantes me = new ModelConstantes();
		me.setClave(rs.getString("clave"));
		me.setValorDouble(rs.getDouble("valorDouble"));
		me.setValorString100(rs.getString("valorString100"));
		me.setValorText(rs.getString("valorText"));
		me.setModificadoPor(rs.getString("modificadoPor"));
		me.setFechaModificacion(rs.getTimestamp("fechaModificacion"));
		return me;
	}
}
