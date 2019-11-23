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
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.damian.converter.ConverterUsuario;
import com.damian.dao.UsuarioDAO;
import com.damian.dao.model.ModelUsuario;
import com.damian.pojo.Usuario;

@Repository
@Transactional
public class UsuarioDAOImpl implements UsuarioDAO {

	private JdbcTemplate jdbcTemplate;

	public UsuarioDAOImpl(DataSource dataSource) {
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

	private final String TABLA = "usuario";
	private final String KEY = "idUsr";

	@Override
	public Usuario findById(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Usuario>() {

			@Override
			public Usuario extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return ConverterUsuario.convert(mapeo(rs));
				}

				return null;
			}

		});

		// List<Usuario> usuarios = findByIdEngine(id);
		// if (usuarios != null && usuarios.size() > 0) {
		// return usuarios.get(0);
		// } else {
		// return null;
		// }

		// Criteria crit = getSession().createCriteria(Usuario.class);
		// crit.add(Restrictions.eq("idUsr", id));
		// return (Usuario) crit.uniqueResult();
	}

	@Override
	public List<Usuario> findByIdList(int id) {

		List<Usuario> usuarios = new ArrayList<>();
		usuarios.add(findById(id));

		// List<Usuario> usuarios = findByIdEngine(id);
		// if (usuarios != null && !usuarios.isEmpty()) {
		// List<UsuarioRol> roles = usuarios.get(0).getUsuarioRol();
		// String rol = roles.get(0).getRol().getRol();
		// // para evitar error con Lazy
		// System.out.println(rol);
		// }
		return usuarios;
	}

	@Override
	public Usuario findByUsername(String usuario) {

		String sql = "SELECT * FROM " + TABLA + " WHERE usuario LIKE '" + usuario + "'";

		List<Usuario> usuarios = lista(sql);

		// CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
		// Session session = getOpenSession();
		// CriteriaQuery<Usuario> criteriaQuery =
		// criteriaBuilder.createQuery(Usuario.class);
		// Root<Usuario> root = criteriaQuery.from(Usuario.class);
		// criteriaQuery.select(root);
		// Predicate pEqUsuario = criteriaBuilder.equal(root.get("usuario"), usuario);
		// criteriaQuery.where(pEqUsuario);
		// List<Usuario> usuarios = session.createQuery(criteriaQuery).getResultList();
		if (usuarios != null && usuarios.size() > 0) {
			return usuarios.get(0);
		} else {
			return null;
		}

		// Criteria crit = getSession().createCriteria(Usuario.class)
		// .add(Restrictions.eqOrIsNull("usuario", usuario));
		// return (Usuario) crit.uniqueResult();
	}

	@Override
	public List<Usuario> findAll() {

		String sql = "SELECT * FROM " + TABLA;

		return lista(sql);

		// CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
		// Session session = getOpenSession();
		// CriteriaQuery<Usuario> criteriaQuery =
		// criteriaBuilder.createQuery(Usuario.class);
		// Root<Usuario> root = criteriaQuery.from(Usuario.class);
		// criteriaQuery.select(root);
		// criteriaQuery.orderBy(criteriaBuilder.asc(root.get("usuario")));
		// List<Usuario> usuarios = session.createQuery(criteriaQuery).getResultList();
		// return usuarios;

		// return getSession().createQuery("from Usuario").list();
	}

	@Override
	public void save(Usuario usuario) {
		if (usuario.getIdUsr() > 0) {
			update(usuario);
		} else {
			ModelUsuario mu = ConverterUsuario.convert(usuario);
			String sql = "INSERT INTO " + TABLA + " (usuario, clave, habilitado, fechaCreacion)"
					+ " VALUES (?, ?, ?, ?)";
			jdbcTemplate.update(sql, mu.getUsuario(), mu.getClave(), mu.isHabilitado(), mu.getFechaCreacion());
		}
	}

	@Override
	public void update(Usuario usuario) {
		ModelUsuario mu = ConverterUsuario.convert(usuario);
		String sql = "UPDATE " + TABLA + "SET usuario=?, clave=?, habilitado=?, fechaCreacion=? WHERE " + KEY + "=?";
		jdbcTemplate.update(sql, mu.getUsuario(), mu.getClave(), mu.isHabilitado(), mu.getFechaCreacion(),
				mu.getIdUsr());
	}

	@Override
	public void delete(Usuario usuario) {

		String sql = "DELETE FROM " + TABLA + " WHERE " + KEY + "=?";
		jdbcTemplate.update(sql, usuario.getIdUsr());

		// getSession().delete(usuario);
	}

	// private List<Usuario> findByIdEngine(int id) {
	// CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
	// Session session = getSession();
	// CriteriaQuery<Usuario> criteriaQuery =
	// criteriaBuilder.createQuery(Usuario.class);
	// Root<Usuario> root = criteriaQuery.from(Usuario.class);
	// criteriaQuery.select(root);
	// Predicate pEqUsuario = criteriaBuilder.equal(root.get("idUsr"), id);
	// criteriaQuery.where(pEqUsuario);
	// List<Usuario> usuarios = session.createQuery(criteriaQuery).getResultList();
	// return usuarios;
	// }

	private List<Usuario> lista(String sql) {
		List<ModelUsuario> muList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ModelUsuario.class));
		List<Usuario> uList = new ArrayList<>();
		for (ModelUsuario mu : muList) {
			uList.add(ConverterUsuario.convert(mu));
		}
		return uList;
	}

	private ModelUsuario mapeo(ResultSet rs) throws SQLException {
		ModelUsuario mu = new ModelUsuario();
		mu.setIdUsr(rs.getInt("idUsr"));
		mu.setUsuario(rs.getString("usuario"));
		mu.setClave(rs.getString("clave"));
		mu.setHabilitado(rs.getBoolean("habilitado"));
		mu.setFechaCreacion(rs.getTimestamp("fechaCreacion"));
		return mu;
	}

}
