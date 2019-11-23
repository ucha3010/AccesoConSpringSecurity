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

import com.damian.converter.ConverterRol;
import com.damian.dao.RolDAO;
import com.damian.dao.model.ModelRol;
import com.damian.pojo.Rol;

//@Repository
//@Transactional
public class RolDAOImpl implements RolDAO {

	private JdbcTemplate jdbcTemplate;

	public RolDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	// @Autowired
	// private SessionFactory sessionFactory;
	//
	// public Session getSession() {
	// return sessionFactory.getCurrentSession();
	// }
	//
	// public CriteriaBuilder getCriteriaBuilder() {
	// return sessionFactory.getCurrentSession().getCriteriaBuilder();
	// }
	//
	// public Session getOpenSession() {
	// return sessionFactory.openSession();
	// }

	private final String TABLA = "rol";
	private final String KEY = "idRol";

	@Override
	public Rol findById(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Rol>() {

			@Override
			public Rol extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return ConverterRol.convert(mapeo(rs));
				}

				return null;
			}

		});

		// CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
		// Session session = getSession();
		// CriteriaQuery<Rol> criteriaQuery = criteriaBuilder.createQuery(Rol.class);
		// Root<Rol> root = criteriaQuery.from(Rol.class);
		// criteriaQuery.select(root);
		// Predicate pEqRol = criteriaBuilder.equal(root.get("idRol"), id);
		// criteriaQuery.where(pEqRol);
		// List<Rol> rols = session.createQuery(criteriaQuery).getResultList();
		// if(rols != null && rols.size() > 0) {
		// return rols.get(0);
		// } else {
		// return null;
		// }

		// Criteria crit = getSession().createCriteria(Rol.class);
		// crit.add(Restrictions.eq("idUsr", id));
		// return (Rol) crit.uniqueResult();
	}

	@Override
	public List<Rol> findAll() {

		String sql = "SELECT * FROM " + TABLA;

		return lista(sql);

		// CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
		// Session session = getOpenSession();
		// CriteriaQuery<Rol> criteriaQuery = criteriaBuilder.createQuery(Rol.class);
		// Root<Rol> root = criteriaQuery.from(Rol.class);
		// criteriaQuery.select(root);
		// List<Rol> rols = session.createQuery(criteriaQuery).getResultList();
		// return rols;

		// return getSession().createQuery("from Rol").list();
	}

	@Override
	public void save(Rol rol) {
		ModelRol mr = ConverterRol.convert(rol);
		if (rol.getIdRol() > 0) {
			String sql = "UPDATE " + TABLA + "SET rol=? WHERE " + KEY + "=?";
			jdbcTemplate.update(sql, mr.getRol(), mr.getIdRol());
		} else {
			String sql = "INSERT INTO " + TABLA + " (rol)" + " VALUES (?)";
			jdbcTemplate.update(sql, mr.getRol());
		}
	}

	@Override
	public void delete(Rol rol) {

		String sql = "DELETE FROM " + TABLA + " WHERE " + KEY + "=?";
		jdbcTemplate.update(sql, rol.getIdRol());
		// getSession().delete(rol);
	}

	@Override
	public List<Rol> findByRolName(String rolName) {

		String sql = "SELECT * FROM " + TABLA + " WHERE rol LIKE '" + rolName + "'";

		return lista(sql);

		// CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
		// Session session = getOpenSession();
		// CriteriaQuery<Rol> criteriaQuery = criteriaBuilder.createQuery(Rol.class);
		// Root<Rol> root = criteriaQuery.from(Rol.class);
		// criteriaQuery.select(root);
		// Predicate pEqRol = criteriaBuilder.equal(root.get("rol"), rolName);
		// criteriaQuery.where(pEqRol);
		// List<Rol> rols = session.createQuery(criteriaQuery).getResultList();
		// return rols;
	}

	private List<Rol> lista(String sql) {
		List<ModelRol> mrList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ModelRol.class));
		List<Rol> rList = new ArrayList<>();
		for (ModelRol mr : mrList) {
			rList.add(ConverterRol.convert(mr));
		}

		return rList;
	}

	private ModelRol mapeo(ResultSet rs) throws SQLException {
		ModelRol mr = new ModelRol();
		mr.setIdRol(rs.getInt("idRol"));
		mr.setRol(rs.getString("rol"));
		return mr;
	}

}
