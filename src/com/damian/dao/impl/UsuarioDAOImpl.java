package com.damian.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.damian.dao.UsuarioDAO;
import com.damian.pojo.Usuario;
import com.damian.pojo.UsuarioRol;

@Repository
@Transactional
public class UsuarioDAOImpl implements UsuarioDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public CriteriaBuilder getCriteriaBuilder() {
		return sessionFactory.getCurrentSession().getCriteriaBuilder();
	}
	
	public Session getOpenSession() {
		return sessionFactory.openSession();
	}
	
	@Override
	public Usuario findById(int id) {
		
		List<Usuario> usuarios = findByIdEngine(id);
		if(usuarios != null && usuarios.size() > 0) {
			return usuarios.get(0);
		} else {
			return null;
		}

//		Criteria crit = getSession().createCriteria(Usuario.class);
//		crit.add(Restrictions.eq("idUsr", id));
//		return (Usuario) crit.uniqueResult();
	}
	

	@Override
	public List<Usuario> findByIdList(int id) {
		List<Usuario> usuarios = findByIdEngine(id);
		if(usuarios != null && !usuarios.isEmpty()) {
			List<UsuarioRol> roles = usuarios.get(0).getUsuarioRol();
			String rol = roles.get(0).getRol().getRol();
			// para evitar error con Lazy
		}
		return usuarios;
	}

	@Override
	public Usuario findByUsername(String usuario) {
		
		CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
		Session session = getOpenSession();
		CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder.createQuery(Usuario.class);
		Root<Usuario> root = criteriaQuery.from(Usuario.class);
		criteriaQuery.select(root);
		Predicate pEqUsuario = criteriaBuilder.equal(root.get("usuario"), usuario);
		criteriaQuery.where(pEqUsuario);
		List<Usuario> usuarios = session.createQuery(criteriaQuery).getResultList();
		if(usuarios != null && usuarios.size() > 0) {
			return usuarios.get(0);
		} else {
			return null;
		}
		
//		Criteria crit = getSession().createCriteria(Usuario.class)
//				.add(Restrictions.eqOrIsNull("usuario", usuario));		
//		return (Usuario) crit.uniqueResult();
	}

	@Override
	public List<Usuario> findAll() {
		
		CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
		Session session = getOpenSession();
		CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder.createQuery(Usuario.class);
		Root<Usuario> root = criteriaQuery.from(Usuario.class);
		criteriaQuery.select(root);
		criteriaQuery.orderBy(criteriaBuilder.asc(root.get("usuario")));
		List<Usuario> usuarios = session.createQuery(criteriaQuery).getResultList();
		return usuarios;
		
//		return getSession().createQuery("from Usuario").list();
	}

	@Override
	public void save(Usuario usuario) {
		if(usuario.getIdUsr() > 0) {
			getSession().update(usuario);
		} else {
			getSession().save(usuario);
		}
	}
	
	@Override
	public void update(Usuario usuario) {
		getSession().update(usuario);
	}
	
	@Override
	public void delete(Usuario usuario) {
		getSession().delete(usuario);
	}
	
	private List<Usuario> findByIdEngine(int id){
		CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
		Session session = getSession();
		CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder.createQuery(Usuario.class);
		Root<Usuario> root = criteriaQuery.from(Usuario.class);
		criteriaQuery.select(root);
		Predicate pEqUsuario = criteriaBuilder.equal(root.get("idUsr"), id);
		criteriaQuery.where(pEqUsuario);
		List<Usuario> usuarios = session.createQuery(criteriaQuery).getResultList();
		return usuarios;
	}

}
