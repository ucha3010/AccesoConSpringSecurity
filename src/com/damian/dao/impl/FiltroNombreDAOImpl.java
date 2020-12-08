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

import com.damian.converter.ConverterFiltroNombre;
import com.damian.dao.FiltroNombreDAO;
import com.damian.dao.model.ModelFiltroNombre;
import com.damian.pojo.FiltroNombre;
import com.damian.utils.LocalLogger;

@Repository
public class FiltroNombreDAOImpl implements FiltroNombreDAO {

	private JdbcTemplate jdbcTemplate;

	public FiltroNombreDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private final String TABLA = "filtronombre";
	private final String KEY = "idNombre";

	@Autowired
	private ConverterFiltroNombre converterFiltroNombre;

	@Override
	public List<FiltroNombre> findAll() {

		String sql = "SELECT * FROM " + TABLA + " ORDER BY nombreES ASC";

		return lista(sql, false);
	}

	@Override
	public FiltroNombre findById(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<FiltroNombre>() {

			@Override
			public FiltroNombre extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return converterFiltroNombre.convertAll(mapeo(rs));
				}

				return null;
			}

		});
	}

	@Override
	public FiltroNombre findByIdModel(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<FiltroNombre>() {

			@Override
			public FiltroNombre extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return converterFiltroNombre.convert(mapeo(rs));
				}

				return null;
			}

		});
	}

	@Override
	public int save(FiltroNombre filtroNombre, HttpServletRequest request) {
		if (filtroNombre.getIdNombre() > 0) {
			return update(filtroNombre, request);
		} else {
			ModelFiltroNombre mfn = converterFiltroNombre.convert(filtroNombre);
			String sql = "INSERT INTO " + TABLA
					+ " (idTitulo, nombreES, nombreEN, nombrePT, nombreFR, nombreIT, nombreGE, nombreCA, nombreEU) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			int result = jdbcTemplate.update(sql, mfn.getIdTitulo(), mfn.getNombreES(), mfn.getNombreEN(),
					mfn.getNombrePT(), mfn.getNombreFR(), mfn.getNombreIT(), mfn.getNombreGE(), mfn.getNombreCA(),
					mfn.getNombreEU());
			LocalLogger.save(TABLA, mfn, request);
			return result;
		}
	}

	@Override
	public int update(FiltroNombre filtroNombre, HttpServletRequest request) {
		ModelFiltroNombre mfn = converterFiltroNombre.convert(filtroNombre);
		String sql = "UPDATE " + TABLA
				+ " SET idTitulo=?, nombreES=?, nombreEN=?, nombrePT=?, nombreFR=?, nombreIT=?, nombreGE=?, nombreCA=?, nombreEU=? "
				+ "WHERE " + KEY + "=?";
		int result = jdbcTemplate.update(sql, mfn.getIdTitulo(), mfn.getNombreES(), mfn.getNombreEN(),
				mfn.getNombrePT(), mfn.getNombreFR(), mfn.getNombreIT(), mfn.getNombreGE(), mfn.getNombreCA(),
				mfn.getNombreEU(), mfn.getIdNombre());
		LocalLogger.update(TABLA, mfn, request);
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
	public List<FiltroNombre> findByIdTituloModel(int idTitulo) {

		String sql = "SELECT * FROM " + TABLA + " WHERE idTitulo = " + idTitulo;

		return lista(sql, true);
	}

	private List<FiltroNombre> lista(String sql, boolean model) {
		List<ModelFiltroNombre> mpList = jdbcTemplate.query(sql,
				BeanPropertyRowMapper.newInstance(ModelFiltroNombre.class));
		List<FiltroNombre> eList = new ArrayList<>();
		for (ModelFiltroNombre mfn : mpList) {
			if (model) {
				eList.add(converterFiltroNombre.convert(mfn));
			} else {
				eList.add(converterFiltroNombre.convertAll(mfn));
			}
		}
		return eList;
	}

	private ModelFiltroNombre mapeo(ResultSet rs) throws SQLException {
		ModelFiltroNombre mfn = new ModelFiltroNombre();
		mfn.setIdNombre(rs.getInt("idNombre"));
		mfn.setIdTitulo(rs.getInt("idTitulo"));
		mfn.setNombreES(rs.getString("nombreES"));
		mfn.setNombreEN(rs.getString("nombreEN"));
		mfn.setNombrePT(rs.getString("nombrePT"));
		mfn.setNombreFR(rs.getString("nombreFR"));
		mfn.setNombreIT(rs.getString("nombreIT"));
		mfn.setNombreGE(rs.getString("nombreGE"));
		mfn.setNombreCA(rs.getString("nombreCA"));
		mfn.setNombreEU(rs.getString("nombreEU"));
		return mfn;
	}
}
