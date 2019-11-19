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

import com.damian.converter.ConverterDireccion;
import com.damian.dao.DireccionDao;
import com.damian.dao.model.ModelDireccion;
import com.damian.pojo.Direccion;

//@Transactional
//@Repository
public class DireccionDaoImpl implements DireccionDao {

	private JdbcTemplate jdbcTemplate;

	public DireccionDaoImpl(DataSource dataSource) {
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

	private final String TABLA = "direccion";
	private final String KEY = "idDir";

	@Override
	public Direccion findById(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Direccion>() {

			@Override
			public Direccion extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return ConverterDireccion.convert(mapeo(rs));
				}

				return null;
			}

		});

		// CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
		// Session session = getOpenSession();
		// CriteriaQuery<Direccion> criteriaQuery =
		// criteriaBuilder.createQuery(Direccion.class);
		// Root<Direccion> root = criteriaQuery.from(Direccion.class);
		// criteriaQuery.select(root);
		// Predicate pEqDireccion = criteriaBuilder.equal(root.get("idDir"), idDir);
		// criteriaQuery.where(pEqDireccion);
		// List<Direccion> direcciones =
		// session.createQuery(criteriaQuery).getResultList();
		// if(direcciones != null && !direcciones.isEmpty()) {
		// return direcciones.get(0);
		// } else {
		// return null;
		// }

	}

	@Override
	public void save(Direccion direccion) {

		ModelDireccion md = ConverterDireccion.convert(direccion);
		if (direccion.getIdDir() > 0) {
			String sql = "INSERT INTO " + TABLA + " (tipoVia, nombreVia, numero, resto, "
					+ "cp, provincia, ciudad, pais, idDatosPers)" + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			jdbcTemplate.update(sql, md.getTipoVia(), md.getNombreVia(), md.getNumero(), md.getResto(), md.getCp(),
					md.getProvincia(), md.getCiudad(), md.getPais(), md.getIdDatosPers());
		} else {
			String sql = "UPDATE " + TABLA + "SET tipoVia=?, nombreVia=?, numero=?, resto=?, cp=?, provincia=?, "
					+ "ciudad=?, pais=?, idDatosPers=? WHERE " + KEY + "=?";
			jdbcTemplate.update(sql, md.getTipoVia(), md.getNombreVia(), md.getNumero(), md.getResto(), md.getCp(),
					md.getProvincia(), md.getCiudad(), md.getPais(), md.getIdDatosPers(), md.getIdDir());

		}

		// if(direccion.getIdDir() > 0) {
		// getSession().update(direccion);
		// } else {
		// getSession().save(direccion);
		// }
	}

	@Override
	public List<Direccion> findListFromUsuario(int idDatosPers) {

		String sql = "SELECT * FROM " + TABLA + " WHERE idDatosPers=" + idDatosPers;

		List<ModelDireccion> mdList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ModelDireccion.class));
		List<Direccion> dList = new ArrayList<>();
		for (ModelDireccion md : mdList) {
			dList.add(ConverterDireccion.convert(md));
		}

		return dList;

		// CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
		// Session session = getOpenSession();
		// CriteriaQuery<Direccion> criteriaQuery =
		// criteriaBuilder.createQuery(Direccion.class);
		// Root<Direccion> root = criteriaQuery.from(Direccion.class);
		// criteriaQuery.select(root);
		// Predicate pEqDireccion = criteriaBuilder.equal(root.get("datosPersonales"),
		// idDatosPers);
		// criteriaQuery.where(pEqDireccion);
		// List<Direccion> direcciones =
		// session.createQuery(criteriaQuery).getResultList();

		// return direcciones;

		// Criteria crit = getSession().createCriteria(Direccion.class)
		// .setFetchMode("admin", FetchMode.JOIN)
		// .add(Restrictions.eq("admin.idAd", admin.getIdAd()))
		// .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		//
		// return crit.list();
	}

	@Override
	public void delete(Direccion direccion) {

		String sql = "DELETE FROM " + TABLA + " WHERE " + KEY + "=?";
		jdbcTemplate.update(sql, direccion.getIdDir());
		// getSession().delete(direccion);
	}

	private ModelDireccion mapeo(ResultSet rs) throws SQLException {
		ModelDireccion md = new ModelDireccion();
		md.setIdDir(rs.getInt("idDir"));
		md.setTipoVia(rs.getString("tipoVia"));
		md.setNombreVia(rs.getString("nombreVia"));
		md.setNumero(rs.getString("numero"));
		md.setResto(rs.getString("resto"));
		md.setCp(rs.getString("cp"));
		md.setProvincia(rs.getString("provincia"));
		md.setCiudad(rs.getString("ciudad"));
		md.setPais(rs.getString("pais"));
		md.setIdDatosPers(rs.getInt("idDatosPers"));
		return null;
	}

}
