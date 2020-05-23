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

import com.damian.converter.ConverterPais;
import com.damian.dao.PaisDAO;
import com.damian.dao.model.ModelPais;
import com.damian.pojo.Pais;

@Repository
public class PaisDAOImpl implements PaisDAO {

	private JdbcTemplate jdbcTemplate;

	public PaisDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private final String TABLA = "paises";
	private final String KEY = "idPais";

	@Autowired
	private ConverterPais converterPais;

	@Override
	public Pais findById(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Pais>() {

			@Override
			public Pais extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return converterPais.convert(mapeo(rs));
				}

				return null;
			}

		});
	}

	@Override
	public List<Pais> findAll() {

		String sql = "SELECT * FROM " + TABLA;

		return lista(sql);
	}

	@Override
	public void save(Pais pais) {

		ModelPais mp = converterPais.convert(pais);
		String sql = "INSERT INTO " + TABLA
				+ " (nombreES, nombreEN, nombrePT, nombreFR, nombreIT, nombreGE, nombreCA, nombreEU)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, mp.getNombreES(), mp.getNombreEN(), mp.getNombrePT(), mp.getNombreFR(),
				mp.getNombreIT(), mp.getNombreGE(), mp.getNombreCA(), mp.getNombreEU());
	}

	@Override
	public void update(Pais pais) {

		ModelPais mp = converterPais.convert(pais);
		String sql = "UPDATE " + TABLA
				+ " SET nombreES=?, nombreEN=?, nombrePT=?, nombreFR=?, nombreIT=?, nombreGE=?, nombreCA=?, nombreEU=? WHERE "
				+ KEY + "=?";
		jdbcTemplate.update(sql, mp.getNombreES(), mp.getNombreEN(), mp.getNombrePT(), mp.getNombreFR(),
				mp.getNombreIT(), mp.getNombreGE(), mp.getNombreCA(), mp.getNombreEU(), mp.getIdPais());
	}

	@Override
	public void delete(int id) {

		String sql = "DELETE FROM " + TABLA + " WHERE " + KEY + "=?";
		jdbcTemplate.update(sql, id);
	}

	@Override
	public List<Pais> findByPaisName(String paisName) {

		String sql = "SELECT * FROM " + TABLA + " WHERE nombreES LIKE '" + paisName + "'";

		return lista(sql);
	}

	@Override
	public int getMaxId() {
		return jdbcTemplate.queryForObject("SELECT MAX(" + KEY + ") FROM " + TABLA, Integer.class);
	}

	private List<Pais> lista(String sql) {

		List<ModelPais> mpList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ModelPais.class));
		List<Pais> pList = new ArrayList<>();
		for (ModelPais mp : mpList) {
			pList.add(converterPais.convert(mp));
		}

		return pList;
	}

	private ModelPais mapeo(ResultSet rs) throws SQLException {
		ModelPais mp = new ModelPais();
		mp.setIdPais(rs.getInt("idPais"));
		mp.setNombreES(rs.getString("nombreES"));
		mp.setNombreEN(rs.getString("nombreEN"));
		mp.setNombrePT(rs.getString("nombrePT"));
		mp.setNombreFR(rs.getString("nombreFR"));
		mp.setNombreIT(rs.getString("nombreIT"));
		mp.setNombreGE(rs.getString("nombreGE"));
		mp.setNombreCA(rs.getString("nombreCA"));
		mp.setNombreEU(rs.getString("nombreEU"));
		return mp;
	}

}
