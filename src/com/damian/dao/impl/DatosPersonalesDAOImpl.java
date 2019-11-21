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

import com.damian.converter.ConverterDatosPersonales;
import com.damian.dao.DatosPersonalesDAO;
import com.damian.dao.model.ModelDatosPersonales;
import com.damian.pojo.DatosPersonales;

//@Repository
//@Transactional
public class DatosPersonalesDAOImpl implements DatosPersonalesDAO {

	private JdbcTemplate jdbcTemplate;

	public DatosPersonalesDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	// @Autowired
	// private SessionFactory sessionFactory;

	// public Session getSession() {
	// return sessionFactory.getCurrentSession();
	// }

	// public CriteriaBuilder getCriteriaBuilder() {
	// return sessionFactory.getCurrentSession().getCriteriaBuilder();
	// }

	// public Session getOpenSession() {
	// return sessionFactory.openSession();
	// }

	private final String TABLA = "datospersonales";
	private final String KEY = "idDatosPers";

	@Override
	public DatosPersonales findById(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<DatosPersonales>() {

			@Override
			public DatosPersonales extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return ConverterDatosPersonales.convert(mapeo(rs));
				}

				return null;
			}

		});

		// CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
		// Session session = getOpenSession();
		// CriteriaQuery<DatosPersonales> criteriaQuery =
		// criteriaBuilder.createQuery(DatosPersonales.class);
		// Root<DatosPersonales> root = criteriaQuery.from(DatosPersonales.class);
		// criteriaQuery.select(root);
		// Predicate pEqDatosPersonales = criteriaBuilder.equal(root.get("idDatosPers"),
		// id);
		// criteriaQuery.where(pEqDatosPersonales);
		// List<DatosPersonales> datosPersonaless =
		// session.createQuery(criteriaQuery).getResultList();
		// if(datosPersonaless != null && datosPersonaless.size() > 0) {
		// return datosPersonaless.get(0);
		// } else {
		// return null;
		// }

		// Criteria crit = getSession().createCriteria(DatosPersonales.class);
		// crit.add(Restrictions.eq("idUsr", id));
		// return (DatosPersonales) crit.uniqueResult();
	}

	@Override
	public List<DatosPersonales> findAll() {

		String sql = "SELECT * FROM " + TABLA;

		List<ModelDatosPersonales> mdpList = jdbcTemplate.query(sql,
				BeanPropertyRowMapper.newInstance(ModelDatosPersonales.class));
		List<DatosPersonales> dpList = new ArrayList<>();
		for (ModelDatosPersonales mdp : mdpList) {
			dpList.add(ConverterDatosPersonales.convert(mdp));
		}

		return dpList;

		// CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
		// Session session = getOpenSession();
		// CriteriaQuery<DatosPersonales> criteriaQuery =
		// criteriaBuilder.createQuery(DatosPersonales.class);
		// Root<DatosPersonales> root = criteriaQuery.from(DatosPersonales.class);
		// criteriaQuery.select(root);
		// List<DatosPersonales> datosPersonaless =
		// session.createQuery(criteriaQuery).getResultList();
		// return datosPersonaless;

		// return getSession().createQuery("from DatosPersonales").list();
	}

	@Override
	public void save(DatosPersonales datosPersonales) {

		ModelDatosPersonales mdp = ConverterDatosPersonales.convert(datosPersonales);
		String sql = "INSERT INTO " + TABLA + " (nombre, apellido1, apellido2, sexo, fechaNacimiento, nacionalidad, "
				+ "dni, email, telefono, observaciones, datospersonales_idUsr)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, mdp.getNombre(), mdp.getApellido1(), mdp.getApellido2(), mdp.getSexo(),
				mdp.getFechaNacimiento(), mdp.getNacionalidad(), mdp.getDni(), mdp.getEmail(), mdp.getTelefono(),
				mdp.getObservaciones(), mdp.getDatospersonales_idUsr());

		// getSession().save(datosPersonales);
	}

	@Override
	public void update(DatosPersonales datosPersonales) {

		ModelDatosPersonales mdp = ConverterDatosPersonales.convert(datosPersonales);
		String sql = "UPDATE " + TABLA + "SET nombre=?, apellido1=?, apellido2=?, "
				+ "sexo=?, fechaNacimiento=?, nacionalidad=?, " + "dni=?, email=?, telefono=?, "
				+ "observaciones=?, datospersonales_idUsr=? " + "WHERE " + KEY + "=?";
		jdbcTemplate.update(sql, mdp.getNombre(), mdp.getApellido1(), mdp.getApellido2(), mdp.getSexo(),
				mdp.getFechaNacimiento(), mdp.getNacionalidad(), mdp.getDni(), mdp.getEmail(), mdp.getTelefono(),
				mdp.getObservaciones(), mdp.getDatospersonales_idUsr(), mdp.getIdDatosPers());

		// getSession().update(datosPersonales);
	}

	@Override
	public void delete(DatosPersonales datosPersonales) {

		String sql = "DELETE FROM " + TABLA + " WHERE " + KEY + "=?";
		jdbcTemplate.update(sql, datosPersonales.getIdDatosPers());

		// getSession().delete(datosPersonales);
	}

	@Override
	public DatosPersonales findByUsrId(int datosUsrId) {

		String sql = "SELECT * FROM " + TABLA + " WHERE datospersonales_idUsr=" + datosUsrId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<DatosPersonales>() {

			@Override
			public DatosPersonales extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return ConverterDatosPersonales.convert(mapeo(rs));
				}

				return null;
			}

		});

		// CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
		// Session session = getOpenSession();
		// CriteriaQuery<DatosPersonales> criteriaQuery =
		// criteriaBuilder.createQuery(DatosPersonales.class);
		// Root<DatosPersonales> root = criteriaQuery.from(DatosPersonales.class);
		// criteriaQuery.select(root);
		// Predicate pEqDatosPersonales = criteriaBuilder.equal(root.get("usuario"),
		// datosUsrId);
		// criteriaQuery.where(pEqDatosPersonales);
		// List<DatosPersonales> datosPersonaless =
		// session.createQuery(criteriaQuery).getResultList();
		// if(datosPersonaless != null && datosPersonaless.size() > 0) {
		// return datosPersonaless.get(0);
		// } else {
		// return null;
		// }
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
		mdp.setDatospersonales_idUsr(rs.getInt("idDatosPers"));
		return mdp;
	}

}
