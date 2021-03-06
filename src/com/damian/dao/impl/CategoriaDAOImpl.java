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

import com.damian.converter.ConverterCategoria;
import com.damian.dao.CategoriaDAO;
import com.damian.dao.model.ModelCategoria;
import com.damian.pojo.Categoria;
import com.damian.utils.LocalLogger;

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

		String sql = "SELECT * FROM " + TABLA + " ORDER BY nombreES ASC";

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
	public int save(Categoria categoria, HttpServletRequest request) {
		if (categoria.getIdCat() > 0) {
			return update(categoria, request);
		} else {
			ModelCategoria mc = converterCategoria.convert(categoria);
			String sql = "INSERT INTO " + TABLA
					+ " (nombreES, nombreEN, nombrePT, nombreFR, nombreIT, nombreGE, nombreCA, nombreEU, modificadoPor, fechaModificacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			int result = jdbcTemplate.update(sql, mc.getNombreES(), mc.getNombreEN(), mc.getNombrePT(),
					mc.getNombreFR(), mc.getNombreIT(), mc.getNombreGE(), mc.getNombreCA(), mc.getNombreEU(),
					mc.getModificadoPor(), mc.getFechaModificacion());
			LocalLogger.save(TABLA, mc, request);
			return result;
		}
	}

	@Override
	public int update(Categoria categoria, HttpServletRequest request) {
		ModelCategoria mc = converterCategoria.convert(categoria);
		String sql = "UPDATE " + TABLA
				+ " SET nombreES=?, nombreEN=?, nombrePT=?, nombreFR=?, nombreIT=?, nombreGE=?, nombreCA=?, nombreEU=?, modificadoPor=?, fechaModificacion=? "
				+ "WHERE " + KEY + "=?";
		int result = jdbcTemplate.update(sql, mc.getNombreES(), mc.getNombreEN(), mc.getNombrePT(), mc.getNombreFR(),
				mc.getNombreIT(), mc.getNombreGE(), mc.getNombreCA(), mc.getNombreEU(), mc.getModificadoPor(),
				mc.getFechaModificacion(), mc.getIdCat());
		LocalLogger.update(TABLA, mc, request);
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
		me.setNombreES(rs.getString("nombreES"));
		me.setNombreEN(rs.getString("nombreEN"));
		me.setNombrePT(rs.getString("nombrePT"));
		me.setNombreFR(rs.getString("nombreFR"));
		me.setNombreIT(rs.getString("nombreIT"));
		me.setNombreGE(rs.getString("nombreGE"));
		me.setNombreCA(rs.getString("nombreCA"));
		me.setNombreEU(rs.getString("nombreEU"));
		me.setModificadoPor(rs.getString("modificadoPor"));
		me.setFechaModificacion(rs.getTimestamp("fechaModificacion"));
		return me;
	}
}
