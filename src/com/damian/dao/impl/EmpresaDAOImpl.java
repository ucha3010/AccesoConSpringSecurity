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

import com.damian.converter.ConverterEmpresa;
import com.damian.dao.EmpresaDAO;
import com.damian.dao.model.ModelEmpresa;
import com.damian.pojo.Empresa;

//@Repository
//@Transactional
public class EmpresaDAOImpl implements EmpresaDAO {

	private JdbcTemplate jdbcTemplate;

	public EmpresaDAOImpl(DataSource dataSource) {
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

	private final String TABLA = "empresa";
	private final String KEY = "idEmp";

	@Override
	public Empresa findById(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Empresa>() {

			@Override
			public Empresa extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return ConverterEmpresa.convert(mapeo(rs));
				}

				return null;
			}

		});

		// List<Empresa> empresas = findByIdEngine(id);
		// if (empresas != null && empresas.size() > 0) {
		// return empresas.get(0);
		// } else {
		// return null;
		// }

		// Criteria crit = getSession().createCriteria(Empresa.class);
		// crit.add(Restrictions.eq("idUsr", id));
		// return (Empresa) crit.uniqueResult();
	}

	@Override
	public List<Empresa> findByIdList(int id) {
		List<Empresa> empresas = new ArrayList<>();
		empresas.add(findById(id));
		return empresas;
	}

	@Override
	public List<Empresa> findAll() {

		String sql = "SELECT * FROM " + TABLA;

		return lista(sql);

		// CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
		// Session session = getOpenSession();
		// CriteriaQuery<Empresa> criteriaQuery =
		// criteriaBuilder.createQuery(Empresa.class);
		// Root<Empresa> root = criteriaQuery.from(Empresa.class);
		// criteriaQuery.select(root);
		// List<Empresa> empresas = session.createQuery(criteriaQuery).getResultList();
		// return empresas;

		// return getSession().createQuery("from Empresa").list();
	}

	@Override
	public void save(Empresa empresa) {
		if (empresa.getIdEmp() > 0) {
			update(empresa);
		} else {
			ModelEmpresa me = ConverterEmpresa.convert(empresa);
			String sql = "INSERT INTO " + TABLA + " (nombreComercial, tipoSociedad, actividad, cif, email, paginaWeb, "
					+ "telefono, fax, observaciones)" + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			jdbcTemplate.update(sql, me.getNombreComercial(), me.getTipoSociedad(), me.getActividad(), me.getCif(),
					me.getEmail(), me.getPaginaWeb(), me.getTelefono(), me.getFax(), me.getObservaciones());
		}
	}

	@Override
	public void update(Empresa empresa) {
		ModelEmpresa me = ConverterEmpresa.convert(empresa);
		String sql = "UPDATE " + TABLA + "SET nombreComercial=?, tipoSociedad=?, actividad=?, "
				+ "cif=?, email=?, paginaWeb=?, " + "telefono=?, fax=?, observaciones=? " + "WHERE " + KEY + "=?";
		jdbcTemplate.update(sql, me.getNombreComercial(), me.getTipoSociedad(), me.getActividad(), me.getCif(),
				me.getEmail(), me.getPaginaWeb(), me.getTelefono(), me.getFax(), me.getObservaciones(), me.getIdEmp());
	}

	@Override
	public void delete(Empresa empresa) {

		String sql = "DELETE FROM " + TABLA + " WHERE " + KEY + "=?";
		jdbcTemplate.update(sql, empresa.getIdEmp());

		// getSession().delete(empresa);
	}

	@Override
	public List<Empresa> findByEmpresaName(String empresaName) {

		String sql = "SELECT * FROM " + TABLA + " WHERE nombreComercial LIKE '" + empresaName + "'";

		return lista(sql);

		// CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
		// Session session = getOpenSession();
		// CriteriaQuery<Empresa> criteriaQuery =
		// criteriaBuilder.createQuery(Empresa.class);
		// Root<Empresa> root = criteriaQuery.from(Empresa.class);
		// criteriaQuery.select(root);
		// Predicate pEqEmpresa = criteriaBuilder.equal(root.get("nombreComercial"),
		// empresaName);
		// criteriaQuery.where(pEqEmpresa);
		// List<Empresa> empresas = session.createQuery(criteriaQuery).getResultList();
		// return empresas;
	}

	private List<Empresa> lista(String sql) {
		List<ModelEmpresa> meList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ModelEmpresa.class));
		List<Empresa> eList = new ArrayList<>();
		for (ModelEmpresa me : meList) {
			eList.add(ConverterEmpresa.convert(me));
		}
		return eList;
	}

	private ModelEmpresa mapeo(ResultSet rs) throws SQLException {
		ModelEmpresa me = new ModelEmpresa();
		me.setIdEmp(rs.getInt("idEmp"));
		me.setNombreComercial(rs.getString("nombreComercial"));
		me.setTipoSociedad(rs.getString("tipoSociedad"));
		me.setActividad(rs.getString("actividad"));
		me.setCif(rs.getString("cif"));
		me.setEmail(rs.getString("email"));
		me.setPaginaWeb(rs.getString("paginaWeb"));
		me.setTelefono(rs.getString("telefono"));
		me.setFax(rs.getString("fax"));
		me.setObservaciones(rs.getString("observaciones"));
		return me;
	}

	// private List<Empresa> findByIdEngine(int id) {
	//
	// CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
	// Session session = getSession();
	// CriteriaQuery<Empresa> criteriaQuery =
	// criteriaBuilder.createQuery(Empresa.class);
	// Root<Empresa> root = criteriaQuery.from(Empresa.class);
	// criteriaQuery.select(root);
	// Predicate pEqEmpresa = criteriaBuilder.equal(root.get("idEmp"), id);
	// criteriaQuery.where(pEqEmpresa);
	// List<Empresa> empresas = session.createQuery(criteriaQuery).getResultList();
	// return empresas;
	// }
}
