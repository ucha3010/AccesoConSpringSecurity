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

import com.damian.dao.DatosPersonalesDAO;
import com.damian.pojo.DatosPersonales;

@Repository
@Transactional
public class DatosPersonalesDAOImpl implements DatosPersonalesDAO {
	
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
	public DatosPersonales findById(int id) {
		
		CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
		Session session = getOpenSession();
		CriteriaQuery<DatosPersonales> criteriaQuery = criteriaBuilder.createQuery(DatosPersonales.class);
		Root<DatosPersonales> root = criteriaQuery.from(DatosPersonales.class);
		criteriaQuery.select(root);
		Predicate pEqDatosPersonales = criteriaBuilder.equal(root.get("idDatosPers"), id);
		criteriaQuery.where(pEqDatosPersonales);
		List<DatosPersonales> datosPersonaless = session.createQuery(criteriaQuery).getResultList();
		if(datosPersonaless != null && datosPersonaless.size() > 0) {
			return datosPersonaless.get(0);
		} else {
			return null;
		}

//		Criteria crit = getSession().createCriteria(DatosPersonales.class);
//		crit.add(Restrictions.eq("idUsr", id));
//		return (DatosPersonales) crit.uniqueResult();
	}

	@Override
	public List<DatosPersonales> findAll() {
		
		CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
		Session session = getOpenSession();
		CriteriaQuery<DatosPersonales> criteriaQuery = criteriaBuilder.createQuery(DatosPersonales.class);
		Root<DatosPersonales> root = criteriaQuery.from(DatosPersonales.class);
		criteriaQuery.select(root);
		List<DatosPersonales> datosPersonaless = session.createQuery(criteriaQuery).getResultList();
		return datosPersonaless;
		
//		return getSession().createQuery("from DatosPersonales").list();
	}

	@Override
	public void save(DatosPersonales datosPersonales) {
		getSession().save(datosPersonales);
	}
	
	@Override
	public void update(DatosPersonales datosPersonales) {
		getSession().update(datosPersonales);
	}
	
	@Override
	public void delete(DatosPersonales datosPersonales) {
		getSession().delete(datosPersonales);
	}

	@Override
	public DatosPersonales findByUsrId(int datosUsrId) {
		
		CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
		Session session = getOpenSession();
		CriteriaQuery<DatosPersonales> criteriaQuery = criteriaBuilder.createQuery(DatosPersonales.class);
		Root<DatosPersonales> root = criteriaQuery.from(DatosPersonales.class);
		criteriaQuery.select(root);
		Predicate pEqDatosPersonales = criteriaBuilder.equal(root.get("usuario"), datosUsrId);
		criteriaQuery.where(pEqDatosPersonales);
		List<DatosPersonales> datosPersonaless = session.createQuery(criteriaQuery).getResultList();
		if(datosPersonaless != null && datosPersonaless.size() > 0) {
			return datosPersonaless.get(0);
		} else {
			return null;
		}
	}

}
