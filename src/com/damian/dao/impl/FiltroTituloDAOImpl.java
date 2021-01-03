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

import com.damian.converter.ConverterFiltroTitulo;
import com.damian.dao.FiltroTituloDAO;
import com.damian.dao.model.ModelFiltroTitulo;
import com.damian.pojo.FiltroTitulo;
import com.damian.utils.LocalLogger;

@Repository
public class FiltroTituloDAOImpl implements FiltroTituloDAO {

	private JdbcTemplate jdbcTemplate;

	public FiltroTituloDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private final String TABLA = "filtroTitulo";
	private final String KEY = "idTitulo";

	@Autowired
	private ConverterFiltroTitulo converterFiltroTitulo;

	@Override
	public List<FiltroTitulo> findAll() {

		String sql = "SELECT * FROM " + TABLA + " ORDER BY nombreES ASC";

		return lista(sql, false);
	}

	@Override
	public FiltroTitulo findById(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<FiltroTitulo>() {

			@Override
			public FiltroTitulo extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return converterFiltroTitulo.convertAll(mapeo(rs));
				}

				return null;
			}

		});
	}

	@Override
	public FiltroTitulo findByIdModel(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<FiltroTitulo>() {

			@Override
			public FiltroTitulo extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return converterFiltroTitulo.convert(mapeo(rs));
				}

				return null;
			}

		});
	}

	@Override
	public int save(FiltroTitulo filtroTitulo, HttpServletRequest request) {
		if (filtroTitulo.getIdTitulo() > 0) {
			return update(filtroTitulo, request);
		} else {
			ModelFiltroTitulo mft = converterFiltroTitulo.convert(filtroTitulo);
			String sql = "INSERT INTO " + TABLA
					+ " (idSub, nombreES, nombreEN, nombrePT, nombreFR, nombreIT, nombreGE, nombreCA, nombreEU) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			int result = jdbcTemplate.update(sql, mft.getIdSub(), mft.getNombreES(), mft.getNombreEN(),
					mft.getNombrePT(), mft.getNombreFR(), mft.getNombreIT(), mft.getNombreGE(), mft.getNombreCA(),
					mft.getNombreEU());
			LocalLogger.save(TABLA, mft, request);
			return result;
		}
	}

	@Override
	public int update(FiltroTitulo filtroTitulo, HttpServletRequest request) {
		ModelFiltroTitulo mft = converterFiltroTitulo.convert(filtroTitulo);
		String sql = "UPDATE " + TABLA
				+ " SET idSub=?, nombreES=?, nombreEN=?, nombrePT=?, nombreFR=?, nombreIT=?, nombreGE=?, nombreCA=?, nombreEU=? "
				+ "WHERE " + KEY + "=?";
		int result = jdbcTemplate.update(sql, mft.getIdSub(), mft.getNombreES(), mft.getNombreEN(), mft.getNombrePT(),
				mft.getNombreFR(), mft.getNombreIT(), mft.getNombreGE(), mft.getNombreCA(), mft.getNombreEU(),
				mft.getIdTitulo());
		LocalLogger.update(TABLA, mft, request);
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
	public List<FiltroTitulo> findByIdSub(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE idSub = " + id;

		return lista(sql, false);
	}

	@Override
	public List<FiltroTitulo> findByIdSubModel(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE idSub = " + id;

		return lista(sql, true);
	}

	private List<FiltroTitulo> lista(String sql, boolean model) {
		List<ModelFiltroTitulo> mpList = jdbcTemplate.query(sql,
				BeanPropertyRowMapper.newInstance(ModelFiltroTitulo.class));
		List<FiltroTitulo> eList = new ArrayList<>();
		for (ModelFiltroTitulo me : mpList) {
			if (model) {
				eList.add(converterFiltroTitulo.convert(me));
			} else {
				eList.add(converterFiltroTitulo.convertAll(me));
			}
		}
		return eList;
	}

	private ModelFiltroTitulo mapeo(ResultSet rs) throws SQLException {
		ModelFiltroTitulo mft = new ModelFiltroTitulo();
		mft.setIdTitulo(rs.getInt("idTitulo"));
		mft.setIdSub(rs.getInt("idSub"));
		mft.setNombreES(rs.getString("nombreES"));
		mft.setNombreEN(rs.getString("nombreEN"));
		mft.setNombrePT(rs.getString("nombrePT"));
		mft.setNombreFR(rs.getString("nombreFR"));
		mft.setNombreIT(rs.getString("nombreIT"));
		mft.setNombreGE(rs.getString("nombreGE"));
		mft.setNombreCA(rs.getString("nombreCA"));
		mft.setNombreEU(rs.getString("nombreEU"));
		return mft;
	}
}
