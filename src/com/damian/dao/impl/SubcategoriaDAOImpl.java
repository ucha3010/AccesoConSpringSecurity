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

import com.damian.converter.ConverterSubcategoria;
import com.damian.dao.SubcategoriaDAO;
import com.damian.dao.model.ModelSubcategoria;
import com.damian.pojo.Subcategoria;

@Repository
public class SubcategoriaDAOImpl implements SubcategoriaDAO {

	private JdbcTemplate jdbcTemplate;

	public SubcategoriaDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private final String TABLA = "subcategoria";
	private final String KEY = "idSub";

	@Autowired
	private ConverterSubcategoria converterSubcategoria;

	@Override
	public List<Subcategoria> findAll() {

		String sql = "SELECT * FROM " + TABLA + " ORDER BY nombre ASC";

		return lista(sql);
	}

	@Override
	public Subcategoria findById(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Subcategoria>() {

			@Override
			public Subcategoria extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return converterSubcategoria.convertAll(mapeo(rs));
				}

				return null;
			}

		});
	}

	@Override
	public Subcategoria findByIdModel(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Subcategoria>() {

			@Override
			public Subcategoria extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return converterSubcategoria.convert(mapeo(rs));
				}

				return null;
			}

		});
	}

	@Override
	public int save(Subcategoria subcategoria) {
		if (subcategoria.getIdSub() > 0) {
			return update(subcategoria);
		} else {
			ModelSubcategoria ms = converterSubcategoria.convert(subcategoria);
			String sql = "INSERT INTO " + TABLA + " (nombre, idCat) VALUES (?, ?)";
			return jdbcTemplate.update(sql, ms.getNombre(), ms.getIdCat());
		}
	}

	@Override
	public int update(Subcategoria subcategoria) {
		ModelSubcategoria ms = converterSubcategoria.convert(subcategoria);
		String sql = "UPDATE " + TABLA + " SET nombre=?, idCat=? " + "WHERE " + KEY + "=?";
		return jdbcTemplate.update(sql, ms.getNombre(), ms.getIdCat(), ms.getIdSub());
	}

	@Override
	public int delete(int id) {

		String sql = "DELETE FROM " + TABLA + " WHERE " + KEY + "=?";
		return jdbcTemplate.update(sql, id);
	}

	@Override
	public List<Subcategoria> findByIdCatModel(int idCat) {

		String sql = "SELECT * FROM " + TABLA + " WHERE idCat = " + idCat + " ORDER BY nombre ASC";

		return lista(sql);
	}

	@Override
	public int getMaxId() {
		return jdbcTemplate.queryForObject("SELECT MAX(" + KEY + ") FROM " + TABLA, Integer.class);
	}

	private List<Subcategoria> lista(String sql) {
		List<ModelSubcategoria> msList = jdbcTemplate.query(sql,
				BeanPropertyRowMapper.newInstance(ModelSubcategoria.class));
		List<Subcategoria> sList = new ArrayList<>();
		for (ModelSubcategoria ms : msList) {
			sList.add(converterSubcategoria.convertAll(ms));
		}
		return sList;
	}

	private ModelSubcategoria mapeo(ResultSet rs) throws SQLException {
		ModelSubcategoria me = new ModelSubcategoria();
		me.setIdSub(rs.getInt("idSub"));
		me.setNombre(rs.getString("nombre"));
		me.setIdCat(rs.getInt("idCat"));

		return me;
	}
}
