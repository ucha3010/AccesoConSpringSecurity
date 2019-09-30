package com.damian.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.damian.dao.UsuarioRolDAO;
import com.damian.pojo.UsuarioRol;

@Repository
@Transactional
public class UsuarioRolDAOImpl implements UsuarioRolDAO {

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
	public List<UsuarioRol> findAll() {

		CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
		Session session = getOpenSession();
		CriteriaQuery<UsuarioRol> criteriaQuery = criteriaBuilder.createQuery(UsuarioRol.class);
		Root<UsuarioRol> root = criteriaQuery.from(UsuarioRol.class);
		criteriaQuery.select(root);
		List<UsuarioRol> usuarioRols = session.createQuery(criteriaQuery).getResultList();
		return usuarioRols;

		// return getSession().createQuery("from UsuarioRol").list();
	}

	@Override
	public void save(UsuarioRol usuarioRol) {
		getSession().save(usuarioRol);
	}

	@Override
	public void update(UsuarioRol usuarioRol) {
		getSession().update(usuarioRol);
	}

	@Override
	public void delete(UsuarioRol usuarioRol) {
		getSession().delete(usuarioRol);
	}

	@Override
	public List<UsuarioRol> findByIdUsr(int idUsr) {

		List<UsuarioRol> usuarioRols = findAll();
		List<UsuarioRol> byUsrId = new ArrayList<>();
		for (UsuarioRol ur : usuarioRols) {
			if (ur.getUsuario().getIdUsr() == idUsr) {
				byUsrId.add(ur);
			}
		}

		return byUsrId;

	}

	@Override
	public List<UsuarioRol> findByIdRol(int idRol) {

		List<UsuarioRol> usuarioRols = findAll();
		List<UsuarioRol> byRolId = new ArrayList<>();
		for (UsuarioRol ur : usuarioRols) {
			if (ur.getRol().getIdRol() == idRol) {
				byRolId.add(ur);
			}
		}

		return byRolId;

	}

}
