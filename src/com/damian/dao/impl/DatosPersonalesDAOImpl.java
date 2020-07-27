package com.damian.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
import com.damian.utils.LocalLogger;

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
	public void save(DatosPersonales datosPersonales, HttpServletRequest request) {

		ModelDatosPersonales mdp = converterDatosPersonales.convert(datosPersonales);
		String sql = "INSERT INTO " + TABLA
				+ " (nombre, apellido1, apellido2, sexo, fechaNacimiento, nacionalidad_idPais, "
				+ "dni, email, telefono, observaciones, datospersonales_idUsr, modificadoPor, fechaModificacion)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, mdp.getNombre(), mdp.getApellido1(), mdp.getApellido2(), mdp.getSexo(),
				mdp.getFechaNacimiento(), mdp.getNacionalidad_idPais(), mdp.getDni(), mdp.getEmail(), mdp.getTelefono(),
				mdp.getObservaciones(), mdp.getDatospersonales_idUsr(), mdp.getModificadoPor(),
				mdp.getFechaModificacion());
		LocalLogger.save(TABLA, mdp, request);
	}

	@Override
	public void update(DatosPersonales datosPersonales, HttpServletRequest request) {

		ModelDatosPersonales mdp = converterDatosPersonales.convert(datosPersonales);
		String sql = "UPDATE " + TABLA + " SET nombre=?, apellido1=?, apellido2=?, "
				+ "sexo=?, fechaNacimiento=?, nacionalidad_idPais=?, " + "dni=?, email=?, telefono=?, "
				+ "observaciones=?, datospersonales_idUsr=?, modificadoPor=?, fechaModificacion=? WHERE " + KEY + "=?";
		jdbcTemplate.update(sql, mdp.getNombre(), mdp.getApellido1(), mdp.getApellido2(), mdp.getSexo(),
				mdp.getFechaNacimiento(), mdp.getNacionalidad_idPais(), mdp.getDni(), mdp.getEmail(), mdp.getTelefono(),
				mdp.getObservaciones(), mdp.getDatospersonales_idUsr(), mdp.getModificadoPor(),
				mdp.getFechaModificacion(), mdp.getIdDatosPers());
		LocalLogger.update(TABLA, mdp, request);
	}

	@Override
	public void delete(int id, HttpServletRequest request) {

		Object object = findById(id);
		String sql = "DELETE FROM " + TABLA + " WHERE " + KEY + "=?";
		jdbcTemplate.update(sql, id);
		LocalLogger.delete(TABLA, object, request);
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

	@Override
	public DatosPersonales findByUsrIdSearch(int datosUsrId) {

		String sql = "SELECT nombre, apellido1, apellido2 FROM " + TABLA + " WHERE datospersonales_idUsr=" + datosUsrId;
		return mapeoSearch(jdbcTemplate.queryForMap(sql));

	}

	private DatosPersonales mapeoSearch(Map<String, Object> dpMap) {

		DatosPersonales dp = new DatosPersonales();
		dp.setNombre((String) dpMap.get("nombre"));
		dp.setApellido1((String) dpMap.get("apellido1"));
		dp.setApellido2((String) dpMap.get("apellido2"));

		return dp;
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

	@Override
	public int getMaxId() {
		return jdbcTemplate.queryForObject("SELECT MAX(" + KEY + ") FROM " + TABLA, Integer.class);
	}

	private ModelDatosPersonales mapeo(ResultSet rs) throws SQLException {
		ModelDatosPersonales mdp = new ModelDatosPersonales();
		mdp.setIdDatosPers(rs.getInt("idDatosPers"));
		mdp.setNombre(rs.getString("nombre"));
		mdp.setApellido1(rs.getString("apellido1"));
		mdp.setApellido2(rs.getString("apellido2"));
		mdp.setSexo(rs.getString("sexo"));
		mdp.setFechaNacimiento(rs.getDate("fechaNacimiento"));
		mdp.setNacionalidad_idPais(rs.getInt("nacionalidad_idPais"));
		mdp.setDni(rs.getString("dni"));
		mdp.setEmail(rs.getString("email"));
		mdp.setTelefono(rs.getString("telefono"));
		mdp.setObservaciones(rs.getString("observaciones"));
		mdp.setDatospersonales_idUsr(rs.getInt("datospersonales_idUsr"));
		mdp.setModificadoPor(rs.getString("modificadoPor"));
		mdp.setFechaModificacion(rs.getTimestamp("fechaModificacion"));
		return mdp;
	}

}
