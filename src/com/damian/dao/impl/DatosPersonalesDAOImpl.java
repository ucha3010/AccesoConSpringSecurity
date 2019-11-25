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

import com.damian.converter.ConverterDatosPersonales;
import com.damian.dao.DatosPersonalesDAO;
import com.damian.dao.model.ModelDatosPersonales;
import com.damian.pojo.DatosPersonales;

@Repository
public class DatosPersonalesDAOImpl implements DatosPersonalesDAO {

	private JdbcTemplate jdbcTemplate;

	public DatosPersonalesDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private final String TABLA = "datospersonales";
	private final String KEY = "idDatosPers";

	@Autowired
	private ConverterDatosPersonales converterDatosPersonales;

	@Override
	public DatosPersonales findById(int id) {

		ModelDatosPersonales mdp = findModelById(id);
		if (mdp != null) {
			return converterDatosPersonales.convertAll(mdp);
		} else {
			return null;
		}
	}

	@Override
	public List<DatosPersonales> findAll() {

		String sql = "SELECT * FROM " + TABLA;

		List<ModelDatosPersonales> mdpList = jdbcTemplate.query(sql,
				BeanPropertyRowMapper.newInstance(ModelDatosPersonales.class));
		List<DatosPersonales> dpList = new ArrayList<>();
		for (ModelDatosPersonales mdp : mdpList) {
			dpList.add(converterDatosPersonales.convertAll(mdp));
		}

		return dpList;
	}

	@Override
	public void save(DatosPersonales datosPersonales) {

		ModelDatosPersonales mdp = converterDatosPersonales.convert(datosPersonales);
		String sql = "INSERT INTO " + TABLA + " (nombre, apellido1, apellido2, sexo, fechaNacimiento, nacionalidad, "
				+ "dni, email, telefono, observaciones, datospersonales_idUsr)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, mdp.getNombre(), mdp.getApellido1(), mdp.getApellido2(), mdp.getSexo(),
				mdp.getFechaNacimiento(), mdp.getNacionalidad(), mdp.getDni(), mdp.getEmail(), mdp.getTelefono(),
				mdp.getObservaciones(), mdp.getDatospersonales_idUsr());
	}

	@Override
	public void update(DatosPersonales datosPersonales) {

		ModelDatosPersonales mdp = converterDatosPersonales.convert(datosPersonales);
		String sql = "UPDATE " + TABLA + " SET nombre=?, apellido1=?, apellido2=?, "
				+ "sexo=?, fechaNacimiento=?, nacionalidad=?, " + "dni=?, email=?, telefono=?, "
				+ "observaciones=?, datospersonales_idUsr=? " + "WHERE " + KEY + "=?";
		jdbcTemplate.update(sql, mdp.getNombre(), mdp.getApellido1(), mdp.getApellido2(), mdp.getSexo(),
				mdp.getFechaNacimiento(), mdp.getNacionalidad(), mdp.getDni(), mdp.getEmail(), mdp.getTelefono(),
				mdp.getObservaciones(), mdp.getDatospersonales_idUsr(), mdp.getIdDatosPers());
	}

	@Override
	public void delete(DatosPersonales datosPersonales) {

		String sql = "DELETE FROM " + TABLA + " WHERE " + KEY + "=?";
		jdbcTemplate.update(sql, datosPersonales.getIdDatosPers());
	}

	@Override
	public DatosPersonales findByUsrId(int datosUsrId) {

		String sql = "SELECT * FROM " + TABLA + " WHERE datospersonales_idUsr=" + datosUsrId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<DatosPersonales>() {

			@Override
			public DatosPersonales extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return converterDatosPersonales.convertAll(mapeo(rs));
				}

				return null;
			}

		});
	}

	private ModelDatosPersonales mapeo(ResultSet rs) throws SQLException {
		ModelDatosPersonales mdp = new ModelDatosPersonales();
		mdp.setIdDatosPers(rs.getInt("idDatosPers"));
		mdp.setNombre(rs.getString("nombre"));
		mdp.setApellido1(rs.getString("apellido1"));
		mdp.setApellido2(rs.getString("apellido2"));
		mdp.setSexo(rs.getString("sexo"));
		mdp.setFechaNacimiento(rs.getDate("fechaNacimiento"));
		mdp.setNacionalidad(rs.getString("nacionalidad"));
		mdp.setDni(rs.getString("dni"));
		mdp.setEmail(rs.getString("email"));
		mdp.setTelefono(rs.getString("telefono"));
		mdp.setObservaciones(rs.getString("observaciones"));
		mdp.setDatospersonales_idUsr(rs.getInt("datospersonales_idUsr"));
		return mdp;
	}

	@Override
	public ModelDatosPersonales findModelById(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<ModelDatosPersonales>() {

			@Override
			public ModelDatosPersonales extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return mapeo(rs);
				}

				return null;
			}

		});
	}

	@Override
	public DatosPersonales findByIdModel(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<DatosPersonales>() {

			@Override
			public DatosPersonales extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return converterDatosPersonales.convert(mapeo(rs));
				}

				return null;
			}

		});
	}

	@Override
	public DatosPersonales findByUsrIdModel(int datosUsrId) {

		String sql = "SELECT * FROM " + TABLA + " WHERE datospersonales_idUsr=" + datosUsrId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<DatosPersonales>() {

			@Override
			public DatosPersonales extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return converterDatosPersonales.convert(mapeo(rs));
				}

				return null;
			}

		});

	}

}
