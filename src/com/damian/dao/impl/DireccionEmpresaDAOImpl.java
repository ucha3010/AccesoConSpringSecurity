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

import com.damian.converter.ConverterDireccionEmpresa;
import com.damian.dao.DireccionEmpresaDAO;
import com.damian.dao.model.ModelDireccionEmpresa;
import com.damian.pojo.DireccionEmpresa;
import com.damian.utils.LocalLogger;

@Repository
public class DireccionEmpresaDAOImpl implements DireccionEmpresaDAO {

	private JdbcTemplate jdbcTemplate;

	public DireccionEmpresaDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private final String TABLA = "direccionempresa";
	private final String KEY = "idDirEmp";

	@Autowired
	private ConverterDireccionEmpresa converterDireccionEmpresa;

	@Override
	public DireccionEmpresa findById(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<DireccionEmpresa>() {

			@Override
			public DireccionEmpresa extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return converterDireccionEmpresa.convertAll(mapeo(rs));
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
			dpList.add(converterDireccionEmpresa.convertAll(mdp));
		}

		return dpList;

	}

	@Override
	public void save(DireccionEmpresa direccionEmpresa, HttpServletRequest request) {

		if (direccionEmpresa.getIdDirEmp() == 0) {
			ModelDireccionEmpresa mde = converterDireccionEmpresa.convert(direccionEmpresa);
			String sql = "INSERT INTO " + TABLA + " (tipoVia, nombreVia, numero, resto, cp, provincia, "
					+ "ciudad, pais_idPais, idEmp, modificadoPor, fechaModificacion)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			jdbcTemplate.update(sql, mde.getTipoVia(), mde.getNombreVia(), mde.getNumero(), mde.getResto(), mde.getCp(),
					mde.getProvincia(), mde.getCiudad(), mde.getPais_idPais(), mde.getIdEmp(), mde.getModificadoPor(),
					mde.getFechaModificacion());
			LocalLogger.save(TABLA, mde, request);
		} else {
			update(direccionEmpresa, request);
		}

	}

	@Override
	public void update(DireccionEmpresa direccionEmpresa, HttpServletRequest request) {

		ModelDireccionEmpresa mde = converterDireccionEmpresa.convert(direccionEmpresa);
		String sql = "UPDATE " + TABLA + " SET tipoVia=?, nombreVia=?, numero=?, " + "resto=?, cp=?, provincia=?, "
				+ "ciudad=?, pais_idPais=?, idEmp=?, modificadoPor=?, fechaModificacion=? WHERE " + KEY + "=?";
		jdbcTemplate.update(sql, mde.getTipoVia(), mde.getNombreVia(), mde.getNumero(), mde.getResto(), mde.getCp(),
				mde.getProvincia(), mde.getCiudad(), mde.getPais_idPais(), mde.getIdEmp(), mde.getModificadoPor(),
				mde.getFechaModificacion(), mde.getIdDirEmp());
		LocalLogger.update(TABLA, mde, request);

	}

	@Override
	public void delete(int id, HttpServletRequest request) {

		Object object = findById(id);
		String sql = "DELETE FROM " + TABLA + " WHERE " + KEY + "=?";
		jdbcTemplate.update(sql, id);
		LocalLogger.delete(TABLA, object, request);
	}

	@Override
	public List<DireccionEmpresa> findByIdEmp(int idEmp) {

		String sql = "SELECT * FROM " + TABLA + " WHERE idEmp=" + idEmp;

		List<ModelDireccionEmpresa> mdpList = jdbcTemplate.query(sql,
				BeanPropertyRowMapper.newInstance(ModelDireccionEmpresa.class));
		List<DireccionEmpresa> dpList = new ArrayList<>();
		for (ModelDireccionEmpresa mdp : mdpList) {
			dpList.add(converterDireccionEmpresa.convertAll(mdp));
		}

		return dpList;

	}

	@Override
	public DireccionEmpresa findByIdModel(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<DireccionEmpresa>() {

			@Override
			public DireccionEmpresa extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return converterDireccionEmpresa.convert(mapeo(rs));
				}

				return null;
			}

		});
	}

	@Override
	public List<DireccionEmpresa> findByIdEmpModel(int idEmp) {

		String sql = "SELECT * FROM " + TABLA + " WHERE idEmp=" + idEmp;

		List<ModelDireccionEmpresa> mdpList = jdbcTemplate.query(sql,
				BeanPropertyRowMapper.newInstance(ModelDireccionEmpresa.class));
		List<DireccionEmpresa> dpList = new ArrayList<>();
		for (ModelDireccionEmpresa mdp : mdpList) {
			dpList.add(converterDireccionEmpresa.convert(mdp));
		}

		return dpList;
	}

	@Override
	public int getMaxId() {
		return jdbcTemplate.queryForObject("SELECT MAX(" + KEY + ") FROM " + TABLA, Integer.class);
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
		mde.setPais_idPais(rs.getInt("pais_idPais"));
		mde.setIdEmp(rs.getInt("idEmp"));
		mde.setModificadoPor(rs.getString("modificadoPor"));
		mde.setFechaModificacion(rs.getTimestamp("fechaModificacion"));
		return mde;
	}

}
