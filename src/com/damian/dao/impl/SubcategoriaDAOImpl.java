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

import com.damian.converter.ConverterSubcategoria;
import com.damian.dao.SubcategoriaDAO;
import com.damian.dao.model.ModelSubcategoria;
import com.damian.pojo.Subcategoria;
import com.damian.utils.LocalLogger;

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

		String sql = "SELECT * FROM " + TABLA + " ORDER BY nombreES ASC";

		return lista(sql, true);
	}

	@Override
	public Subcategoria findById(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Subcategoria>() {

			@Override
			public Subcategoria extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return converterSubcategoria.convertAll(mapeo(rs), true);
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
	public int save(Subcategoria subcategoria, HttpServletRequest request) {
		if (subcategoria.getIdSub() > 0) {
			return update(subcategoria, request);
		} else {
			ModelSubcategoria ms = converterSubcategoria.convert(subcategoria);
			String sql = "INSERT INTO " + TABLA
					+ " (nombreES, nombreEN, nombrePT, nombreFR, nombreIT, nombreGE, nombreCA, nombreEU, idCat, modificadoPor, fechaModificacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			int result = jdbcTemplate.update(sql, ms.getNombreES(), ms.getNombreEN(), ms.getNombrePT(),
					ms.getNombreFR(), ms.getNombreIT(), ms.getNombreGE(), ms.getNombreCA(), ms.getNombreEU(),
					ms.getIdCat(), ms.getModificadoPor(), ms.getFechaModificacion());
			LocalLogger.save(TABLA, ms, request);
			return result;
		}
	}

	@Override
	public int update(Subcategoria subcategoria, HttpServletRequest request) {
		ModelSubcategoria ms = converterSubcategoria.convert(subcategoria);
		String sql = "UPDATE " + TABLA
				+ " SET nombreES=?, nombreEN=?, nombrePT=?, nombreFR=?, nombreIT=?, nombreGE=?, nombreCA=?, nombreEU=?, idCat=?, modificadoPor=?, fechaModificacion=? "
				+ "WHERE " + KEY + "=?";
		int result = jdbcTemplate.update(sql, ms.getNombreES(), ms.getNombreEN(), ms.getNombrePT(), ms.getNombreFR(),
				ms.getNombreIT(), ms.getNombreGE(), ms.getNombreCA(), ms.getNombreEU(), ms.getIdCat(),
				ms.getModificadoPor(), ms.getFechaModificacion(), ms.getIdSub());
		LocalLogger.update(TABLA, ms, request);
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
	public List<Subcategoria> findByIdCatModel(int idCat) {

		String sql = "SELECT * FROM " + TABLA + " WHERE idCat = " + idCat + " ORDER BY nombreES ASC";

		return lista(sql, false);
	}

	@Override
	public int getMaxId() {
		return jdbcTemplate.queryForObject("SELECT MAX(" + KEY + ") FROM " + TABLA, Integer.class);
	}

	private List<Subcategoria> lista(String sql, boolean cargoCategoria) {
		List<ModelSubcategoria> msList = jdbcTemplate.query(sql,
				BeanPropertyRowMapper.newInstance(ModelSubcategoria.class));
		List<Subcategoria> sList = new ArrayList<>();
		for (ModelSubcategoria ms : msList) {
			sList.add(converterSubcategoria.convertAll(ms, cargoCategoria));
		}
		return sList;
	}

	private ModelSubcategoria mapeo(ResultSet rs) throws SQLException {
		ModelSubcategoria ms = new ModelSubcategoria();
		ms.setIdSub(rs.getInt("idSub"));
		ms.setNombreES(rs.getString("nombreES"));
		ms.setNombreEN(rs.getString("nombreEN"));
		ms.setNombrePT(rs.getString("nombrePT"));
		ms.setNombreFR(rs.getString("nombreFR"));
		ms.setNombreIT(rs.getString("nombreIT"));
		ms.setNombreGE(rs.getString("nombreGE"));
		ms.setNombreCA(rs.getString("nombreCA"));
		ms.setNombreEU(rs.getString("nombreEU"));
		ms.setIdCat(rs.getInt("idCat"));
		ms.setFechaModificacion(rs.getTimestamp("fechaModificacion"));
		ms.setModificadoPor(rs.getString("modificadoPor"));

		return ms;
	}
}
