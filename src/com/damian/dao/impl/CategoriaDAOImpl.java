package com.damian.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.damian.converter.ConverterCategoria;
import com.damian.dao.CategoriaDAO;
import com.damian.dao.model.ModelCategoria;
import com.damian.pojo.Categoria;

@Repository
public class CategoriaDAOImpl implements CategoriaDAO {

	private JdbcTemplate jdbcTemplate;

	public CategoriaDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private final String TABLA = "categoria";
	private final String KEY = "idCat";

	@Autowired
	private ConverterCategoria converterCategoria;

	@Override
	public List<Categoria> findAll() {

		String sql = "SELECT * FROM " + TABLA + " ORDER BY nombre ASC";

		return lista(sql);
	}

	@Override
	public Categoria findById(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Categoria>() {

			@Override
			public Categoria extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return converterCategoria.convertAll(mapeo(rs));
				}

				return null;
			}

		});
	}

	@Override
	public Categoria findByIdModel(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Categoria>() {

			@Override
			public Categoria extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return converterCategoria.convert(mapeo(rs));
				}

				return null;
			}

		});
	}

	@Override
	public int save(Categoria categoria) {
		if (categoria.getIdCat() > 0) {
			return update(categoria);
		} else {
			ModelCategoria me = converterCategoria.convert(categoria);
			String sql = "INSERT INTO " + TABLA + " (nombre) VALUES (?)";
			return jdbcTemplate.update(sql, me.getNombre());
		}
	}

	@Override
	public int update(Categoria categoria) {
		ModelCategoria me = converterCategoria.convert(categoria);
		String sql = "UPDATE " + TABLA + " SET nombre=? " + "WHERE " + KEY + "=?";
		return jdbcTemplate.update(sql, me.getNombre(), me.getIdCat());
	}

	@Override
	public int delete(int id) {

		String sql = "DELETE FROM " + TABLA + " WHERE " + KEY + "=?";
		return jdbcTemplate.update(sql, id);
	}

	private List<Categoria> lista(String sql) {
		List<ModelCategoria> mpList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ModelCategoria.class));
		List<Categoria> eList = new ArrayList<>();
		for (ModelCategoria me : mpList) {
			eList.add(converterCategoria.convertAll(me));
		}
		return eList;
	}

	private ModelCategoria mapeo(ResultSet rs) throws SQLException {
		ModelCategoria me = new ModelCategoria();
		me.setIdCat(rs.getInt("idCat"));
		me.setNombre(rs.getString("nombre"));
		return me;
	}
}
