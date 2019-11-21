package com.damian.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.damian.converter.ConverterDireccionEmpresa;
import com.damian.dao.DireccionEmpresaDAO;
import com.damian.dao.model.ModelDireccionEmpresa;
import com.damian.pojo.DireccionEmpresa;

public class DireccionEmpresaDAOImpl implements DireccionEmpresaDAO {

	private JdbcTemplate jdbcTemplate;

	public DireccionEmpresaDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private final String TABLA = "direccionempresa";
	private final String KEY = "idDirEmp";

	@Override
	public DireccionEmpresa findById(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<DireccionEmpresa>() {

			@Override
			public DireccionEmpresa extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return ConverterDireccionEmpresa.convert(mapeo(rs));
				}

				return null;
			}

		});

	}

	@Override
	public List<DireccionEmpresa> findAll() {

		String sql = "SELECT * FROM " + TABLA;

		List<ModelDireccionEmpresa> mdpList = jdbcTemplate.query(sql,
				BeanPropertyRowMapper.newInstance(ModelDireccionEmpresa.class));
		List<DireccionEmpresa> dpList = new ArrayList<>();
		for (ModelDireccionEmpresa mdp : mdpList) {
			dpList.add(ConverterDireccionEmpresa.convert(mdp));
		}

		return dpList;

	}

	@Override
	public void save(DireccionEmpresa direccionEmpresa) {

		ModelDireccionEmpresa mdp = ConverterDireccionEmpresa.convert(direccionEmpresa);
		String sql = "INSERT INTO " + TABLA + " (tipoVia, nombreVia, numero, resto, cp, provincia, "
				+ "ciudad, pais, idEmp)" + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, mdp.getTipoVia(), mdp.getNombreVia(), mdp.getNumero(), mdp.getResto(), mdp.getCp(),
				mdp.getProvincia(), mdp.getCiudad(), mdp.getPais(), mdp.getIdEmp());

	}

	@Override
	public void update(DireccionEmpresa direccionEmpresa) {

		ModelDireccionEmpresa mdp = ConverterDireccionEmpresa.convert(direccionEmpresa);
		String sql = "UPDATE " + TABLA + "SET tipoVia=?, nombreVia=?, numero=?, " + "resto=?, cp=?, provincia=?, "
				+ "ciudad=?, pais=?, idEmp=? " + "WHERE " + KEY + "=?";
		jdbcTemplate.update(sql, mdp.getTipoVia(), mdp.getNombreVia(), mdp.getNumero(), mdp.getResto(), mdp.getCp(),
				mdp.getProvincia(), mdp.getCiudad(), mdp.getPais(), mdp.getIdEmp(), mdp.getIdDirEmp());

	}

	@Override
	public void delete(DireccionEmpresa direccionEmpresa) {

		String sql = "DELETE FROM " + TABLA + " WHERE " + KEY + "=?";
		jdbcTemplate.update(sql, direccionEmpresa.getIdDirEmp());

	}

	@Override
	public List<DireccionEmpresa> findByIdEmp(int idEmp) {

		String sql = "SELECT * FROM " + TABLA + " WHERE idEmp=" + idEmp;

		List<ModelDireccionEmpresa> mdpList = jdbcTemplate.query(sql,
				BeanPropertyRowMapper.newInstance(ModelDireccionEmpresa.class));
		List<DireccionEmpresa> dpList = new ArrayList<>();
		for (ModelDireccionEmpresa mdp : mdpList) {
			dpList.add(ConverterDireccionEmpresa.convert(mdp));
		}

		return dpList;

	}

	private ModelDireccionEmpresa mapeo(ResultSet rs) throws SQLException {
		ModelDireccionEmpresa mde = new ModelDireccionEmpresa();
		mde.setIdDirEmp(rs.getInt("idDirEmp"));
		mde.setTipoVia(rs.getString("tipoVia"));
		mde.setNombreVia(rs.getString("nombreVia"));
		mde.setNumero(rs.getString("numero"));
		mde.setResto(rs.getString("resto"));
		mde.setCp(rs.getString("cp"));
		mde.setProvincia(rs.getString("provincia"));
		mde.setCiudad(rs.getString("ciudad"));
		mde.setPais(rs.getString("pais"));
		mde.setIdEmp(rs.getInt("idEmp"));
		return mde;
	}

}
