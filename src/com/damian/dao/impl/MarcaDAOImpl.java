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

import com.damian.converter.ConverterMarca;
import com.damian.dao.MarcaDAO;
import com.damian.dao.model.ModelMarca;
import com.damian.pojo.Marca;
import com.damian.utils.LocalLogger;

@Repository
public class MarcaDAOImpl implements MarcaDAO {

	private JdbcTemplate jdbcTemplate;

	public MarcaDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private final String TABLA = "marca";
	private final String KEY = "idMar";

	@Autowired
	private ConverterMarca converterMarca;

	@Override
	public Marca findById(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Marca>() {

			@Override
			public Marca extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return converterMarca.convert(mapeo(rs));
				}

				return null;
			}

		});
	}

	@Override
	public List<Marca> findAll() {

		String sql = "SELECT * FROM " + TABLA + " ORDER BY nombre";

		return lista(sql);
	}

	@Override
	public void save(Marca marca, HttpServletRequest request) {
		ModelMarca mr = converterMarca.convert(marca);
		if (marca.getIdMar() > 0) {
			String sql = "UPDATE " + TABLA + " SET nombre=? WHERE " + KEY + "=?";
			jdbcTemplate.update(sql, mr.getNombre(), mr.getIdMar());
			LocalLogger.update(TABLA, mr, request);
		} else {
			String sql = "INSERT INTO " + TABLA + " (nombre)" + " VALUES (?)";
			jdbcTemplate.update(sql, mr.getNombre());
			LocalLogger.save(TABLA, mr, request);
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
	public List<Marca> findByMarcaNombre(String nombre) {

		String sql = "SELECT * FROM " + TABLA + " WHERE marca LIKE '" + nombre + "'";

		return lista(sql);
	}

	@Override
	public int getMaxId() {
		return jdbcTemplate.queryForObject("SELECT MAX(" + KEY + ") FROM " + TABLA, Integer.class);
	}

	private List<Marca> lista(String sql) {
		List<ModelMarca> mrList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ModelMarca.class));
		List<Marca> rList = new ArrayList<>();
		for (ModelMarca mr : mrList) {
			rList.add(converterMarca.convertAll(mr));
		}

		return rList;
	}

	private ModelMarca mapeo(ResultSet rs) throws SQLException {
		ModelMarca mr = new ModelMarca();
		mr.setIdMar(rs.getInt("idMar"));
		mr.setNombre(rs.getString("nombre"));
		return mr;
	}

}
