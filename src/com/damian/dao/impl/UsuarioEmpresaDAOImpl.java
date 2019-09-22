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

import com.damian.dao.UsuarioEmpresaDAO;
import com.damian.pojo.UsuarioEmpresa;

@Repository
@Transactional
public class UsuarioEmpresaDAOImpl implements UsuarioEmpresaDAO {
	
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
	public List<UsuarioEmpresa> findAll() {
		
		CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
		Session session = getOpenSession();
		CriteriaQuery<UsuarioEmpresa> criteriaQuery = criteriaBuilder.createQuery(UsuarioEmpresa.class);
		Root<UsuarioEmpresa> root = criteriaQuery.from(UsuarioEmpresa.class);
		criteriaQuery.select(root);
		List<UsuarioEmpresa> usuarioEmpresas = session.createQuery(criteriaQuery).getResultList();
		return usuarioEmpresas;
		
//		return getSession().createQuery("from UsuarioEmpresa").list();
	}

	@Override
	public void save(UsuarioEmpresa usuarioEmpresa) {
		getSession().save(usuarioEmpresa);
	}
	
	@Override
	public void update(UsuarioEmpresa usuarioEmpresa) {
		getSession().update(usuarioEmpresa);
	}
	
	@Override
	public void delete(UsuarioEmpresa usuarioEmpresa) {
		getSession().delete(usuarioEmpresa);
	}

	@Override
	public List<UsuarioEmpresa> findByIdUsr(int idUsr) {

		CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
		Session session = getOpenSession();
		CriteriaQuery<UsuarioEmpresa> criteriaQuery = criteriaBuilder.createQuery(UsuarioEmpresa.class);
		Root<UsuarioEmpresa> root = criteriaQuery.from(UsuarioEmpresa.class);
		criteriaQuery.select(root);
		Predicate pEqUsuarioEmpresa = criteriaBuilder.equal(root.get("usuario"), idUsr);
		criteriaQuery.where(pEqUsuarioEmpresa);
		List<UsuarioEmpresa> usuarioEmpresas = session.createQuery(criteriaQuery).getResultList();
		System.out.println(usuarioEmpresas);
		return null;
		
	}

	@Override
	public List<UsuarioEmpresa> findByIdEmp(int idEmp) {

		CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
		Session session = getOpenSession();
		CriteriaQuery<UsuarioEmpresa> criteriaQuery = criteriaBuilder.createQuery(UsuarioEmpresa.class);
		Root<UsuarioEmpresa> root = criteriaQuery.from(UsuarioEmpresa.class);
		criteriaQuery.select(root);
		Predicate pEqUsuarioEmpresa = criteriaBuilder.equal(root.get("empresa"), idEmp);
		criteriaQuery.where(pEqUsuarioEmpresa);
		List<UsuarioEmpresa> usuarioEmpresas = session.createQuery(criteriaQuery).getResultList();
		return usuarioEmpresas;
	}

}
