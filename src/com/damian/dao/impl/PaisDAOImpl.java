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

import com.damian.converter.ConverterPais;
import com.damian.dao.PaisDAO;
import com.damian.dao.model.ModelPais;
import com.damian.pojo.Pais;

//@Repository
//@Transactional
public class PaisDAOImpl implements PaisDAO {

	private JdbcTemplate jdbcTemplate;

	public PaisDAOImpl(DataSource dataSource) {
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

	private final String TABLA = "paises";
	private final String KEY = "idPais";

	@Override
	public Pais findById(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Pais>() {

			@Override
			public Pais extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return ConverterPais.convert(mapeo(rs));
				}

				return null;
			}

		});

		// CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
		// Session session = getOpenSession();
		// CriteriaQuery<Pais> criteriaQuery = criteriaBuilder.createQuery(Pais.class);
		// Root<Pais> root = criteriaQuery.from(Pais.class);
		// criteriaQuery.select(root);
		// Predicate pEqPais = criteriaBuilder.equal(root.get("idPais"), id);
		// criteriaQuery.where(pEqPais);
		// List<Pais> paiss = session.createQuery(criteriaQuery).getResultList();
		// if(paiss != null && paiss.size() > 0) {
		// return paiss.get(0);
		// } else {
		// return null;
		// }

		// Criteria crit = getSession().createCriteria(Pais.class);
		// crit.add(Restrictions.eq("idUsr", id));
		// return (Pais) crit.uniqueResult();
	}

	@Override
	public List<Pais> findAll() {

		String sql = "SELECT * FROM " + TABLA;

		return lista(sql);

		// CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
		// Session session = getOpenSession();
		// CriteriaQuery<Pais> criteriaQuery = criteriaBuilder.createQuery(Pais.class);
		// Root<Pais> root = criteriaQuery.from(Pais.class);
		// criteriaQuery.select(root);
		// List<Pais> paiss = session.createQuery(criteriaQuery).getResultList();
		// return paiss;

		// return getSession().createQuery("from Pais").list();
	}

	@Override
	public void save(Pais pais) {

		ModelPais mp = ConverterPais.convert(pais);
		String sql = "INSERT INTO " + TABLA + " (nombreES, nombreEN)" + " VALUES (?, ?)";
		jdbcTemplate.update(sql, mp.getNombreES(), mp.getNombreEN());

		// getSession().save(pais);
	}

	@Override
	public void update(Pais pais) {

		ModelPais mp = ConverterPais.convert(pais);
		String sql = "UPDATE " + TABLA + "SET nombreES=?, nombreEN=? WHERE " + KEY + "=?";
		jdbcTemplate.update(sql, mp.getNombreES(), mp.getNombreEN(), mp.getIdPais());

		// getSession().update(pais);
	}

	@Override
	public void delete(Pais pais) {

		String sql = "DELETE FROM " + TABLA + " WHERE " + KEY + "=?";
		jdbcTemplate.update(sql, pais.getIdPais());

		// getSession().delete(pais);
	}

	@Override
	public List<Pais> findByPaisName(String paisName) {

		String sql = "SELECT * FROM " + TABLA + " WHERE nombreES LIKE '" + paisName + "'";

		return lista(sql);

		// CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
		// Session session = getOpenSession();
		// CriteriaQuery<Pais> criteriaQuery = criteriaBuilder.createQuery(Pais.class);
		// Root<Pais> root = criteriaQuery.from(Pais.class);
		// criteriaQuery.select(root);
		// Predicate pEqPais = criteriaBuilder.equal(root.get("nombreES"), paisName);
		// criteriaQuery.where(pEqPais);
		// List<Pais> paiss = session.createQuery(criteriaQuery).getResultList();
		// return paiss;
	}

	private List<Pais> lista(String sql) {

		List<ModelPais> mpList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ModelPais.class));
		List<Pais> pList = new ArrayList<>();
		for (ModelPais mp : mpList) {
			pList.add(ConverterPais.convert(mp));
		}

		return pList;
	}

	private ModelPais mapeo(ResultSet rs) throws SQLException {
		ModelPais mp = new ModelPais();
		mp.setIdPais(rs.getInt("idPais"));
		mp.setNombreES(rs.getString("nombreES"));
		mp.setNombreEN(rs.getString("nombreEN"));
		return mp;
	}

}
