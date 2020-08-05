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

import com.damian.converter.ConverterEmpresaPropia;
import com.damian.dao.EmpresaPropiaDAO;
import com.damian.dao.model.ModelEmpresaPropia;
import com.damian.pojo.EmpresaPropia;
import com.damian.utils.LocalLogger;

@Repository
public class EmpresaPropiaDAOImpl implements EmpresaPropiaDAO {

	private JdbcTemplate jdbcTemplate;

	public EmpresaPropiaDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private final String TABLA = "empresapropia";
	private final String KEY = "idPropia";

	@Autowired
	private ConverterEmpresaPropia converterEmpresaPropia;

	@Override
	public List<EmpresaPropia> findAll() {

		String sql = "SELECT * FROM " + TABLA + " ORDER BY razonSocial ASC";

		return lista(sql);
	}

	@Override
	public EmpresaPropia findById(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<EmpresaPropia>() {

			@Override
			public EmpresaPropia extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return converterEmpresaPropia.convertAll(mapeo(rs));
				}

				return null;
			}

		});
	}

	@Override
	public EmpresaPropia findByIdModel(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<EmpresaPropia>() {

			@Override
			public EmpresaPropia extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return converterEmpresaPropia.convert(mapeo(rs));
				}

				return null;
			}

		});
	}

	@Override
	public int save(EmpresaPropia empresaPropia, HttpServletRequest request) {
		if (empresaPropia.getIdPropia() > 0) {
			return update(empresaPropia, request);
		} else {
			ModelEmpresaPropia me = converterEmpresaPropia.convert(empresaPropia);
			String sql = "INSERT INTO " + TABLA
					+ " (razonSocial, cif, telefono, fax, email, facturacion, idDirEmp) VALUES (?, ?, ?, ?, ?, ?, ?)";
			int result = jdbcTemplate.update(sql, me.getRazonSocial(), me.getCif(), me.getTelefono(), me.getFax(),
					me.getEmail(), me.isFacturacion(), me.getIdDirEmp());
			LocalLogger.save(TABLA, me, request);
			return result;
		}
	}

	@Override
	public int update(EmpresaPropia empresaPropia, HttpServletRequest request) {
		ModelEmpresaPropia me = converterEmpresaPropia.convert(empresaPropia);
		String sql = "UPDATE " + TABLA
				+ " SET razonSocial=?, cif=?, telefono=?, fax=?, email=?, facturacion=?, idDirEmp=? " + "WHERE " + KEY
				+ "=?";
		int result = jdbcTemplate.update(sql, me.getRazonSocial(), me.getCif(), me.getTelefono(), me.getFax(),
				me.getEmail(), me.isFacturacion(), me.getIdDirEmp(), me.getIdPropia());
		LocalLogger.update(TABLA, me, request);
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

	private List<EmpresaPropia> lista(String sql) {
		List<ModelEmpresaPropia> mpList = jdbcTemplate.query(sql,
				BeanPropertyRowMapper.newInstance(ModelEmpresaPropia.class));
		List<EmpresaPropia> eList = new ArrayList<>();
		for (ModelEmpresaPropia me : mpList) {
			eList.add(converterEmpresaPropia.convertAll(me));
		}
		return eList;
	}

	private ModelEmpresaPropia mapeo(ResultSet rs) throws SQLException {
		ModelEmpresaPropia me = new ModelEmpresaPropia();
		me.setIdPropia(rs.getInt("idPropia"));
		me.setRazonSocial(rs.getString("razonSocial"));
		me.setCif(rs.getString("cif"));
		me.setTelefono(rs.getString("telefono"));
		me.setFax(rs.getString("fax"));
		me.setEmail(rs.getString("email"));
		me.setFacturacion(rs.getBoolean("facturacion"));
		me.setIdDirEmp(rs.getInt("idDirEmp"));
		return me;
	}
}
