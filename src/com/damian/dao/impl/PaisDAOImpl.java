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

import com.damian.dao.PaisDAO;
import com.damian.pojo.Pais;

@Repository
@Transactional
public class PaisDAOImpl implements PaisDAO {
	
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
	public Pais findById(int id) {
		
		CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
		Session session = getOpenSession();
		CriteriaQuery<Pais> criteriaQuery = criteriaBuilder.createQuery(Pais.class);
		Root<Pais> root = criteriaQuery.from(Pais.class);
		criteriaQuery.select(root);
		Predicate pEqPais = criteriaBuilder.equal(root.get("idPais"), id);
		criteriaQuery.where(pEqPais);
		List<Pais> paiss = session.createQuery(criteriaQuery).getResultList();
		if(paiss != null && paiss.size() > 0) {
			return paiss.get(0);
		} else {
			return null;
		}

//		Criteria crit = getSession().createCriteria(Pais.class);
//		crit.add(Restrictions.eq("idUsr", id));
//		return (Pais) crit.uniqueResult();
	}

	@Override
	public List<Pais> findAll() {
		
		CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
		Session session = getOpenSession();
		CriteriaQuery<Pais> criteriaQuery = criteriaBuilder.createQuery(Pais.class);
		Root<Pais> root = criteriaQuery.from(Pais.class);
		criteriaQuery.select(root);
		List<Pais> paiss = session.createQuery(criteriaQuery).getResultList();
		return paiss;
		
//		return getSession().createQuery("from Pais").list();
	}

	@Override
	public void save(Pais pais) {
		getSession().save(pais);
	}
	
	@Override
	public void update(Pais pais) {
		getSession().update(pais);
	}
	
	@Override
	public void delete(Pais pais) {
		getSession().delete(pais);
	}

	@Override
	public List<Pais> findByPaisName(String paisName) {
		
		CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
		Session session = getOpenSession();
		CriteriaQuery<Pais> criteriaQuery = criteriaBuilder.createQuery(Pais.class);
		Root<Pais> root = criteriaQuery.from(Pais.class);
		criteriaQuery.select(root);
		Predicate pEqPais = criteriaBuilder.equal(root.get("nombreES"), paisName);
		criteriaQuery.where(pEqPais);
		List<Pais> paiss = session.createQuery(criteriaQuery).getResultList();
		return paiss;
	}

}
