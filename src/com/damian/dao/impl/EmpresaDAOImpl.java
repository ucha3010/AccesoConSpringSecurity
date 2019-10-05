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

import com.damian.dao.EmpresaDAO;
import com.damian.pojo.Empresa;

@Repository
@Transactional
public class EmpresaDAOImpl implements EmpresaDAO {
	
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
	public Empresa findById(int id) {
		
		CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
		Session session = getSession();
		CriteriaQuery<Empresa> criteriaQuery = criteriaBuilder.createQuery(Empresa.class);
		Root<Empresa> root = criteriaQuery.from(Empresa.class);
		criteriaQuery.select(root);
		Predicate pEqEmpresa = criteriaBuilder.equal(root.get("idEmp"), id);
		criteriaQuery.where(pEqEmpresa);
		List<Empresa> empresas = session.createQuery(criteriaQuery).getResultList();
		if(empresas != null && empresas.size() > 0) {
			return empresas.get(0);
		} else {
			return null;
		}

//		Criteria crit = getSession().createCriteria(Empresa.class);
//		crit.add(Restrictions.eq("idUsr", id));
//		return (Empresa) crit.uniqueResult();
	}

	@Override
	public List<Empresa> findAll() {
		
		CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
		Session session = getOpenSession();
		CriteriaQuery<Empresa> criteriaQuery = criteriaBuilder.createQuery(Empresa.class);
		Root<Empresa> root = criteriaQuery.from(Empresa.class);
		criteriaQuery.select(root);
		List<Empresa> empresas = session.createQuery(criteriaQuery).getResultList();
		return empresas;
		
//		return getSession().createQuery("from Empresa").list();
	}

	@Override
	public void save(Empresa empresa) {
		if(empresa.getIdEmp() > 0) {
			getSession().update(empresa);
		} else {
			getSession().save(empresa);
		}
	}
	
	@Override
	public void update(Empresa empresa) {
		getSession().update(empresa);
	}
	
	@Override
	public void delete(Empresa empresa) {
		getSession().delete(empresa);
	}

	@Override
	public List<Empresa> findByEmpresaName(String empresaName) {
		
		CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
		Session session = getOpenSession();
		CriteriaQuery<Empresa> criteriaQuery = criteriaBuilder.createQuery(Empresa.class);
		Root<Empresa> root = criteriaQuery.from(Empresa.class);
		criteriaQuery.select(root);
		Predicate pEqEmpresa = criteriaBuilder.equal(root.get("nombreComercial"), empresaName);
		criteriaQuery.where(pEqEmpresa);
		List<Empresa> empresas = session.createQuery(criteriaQuery).getResultList();
		return empresas;
	}

}
