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

import com.damian.dao.RolDAO;
import com.damian.pojo.Rol;

@Repository
@Transactional
public class RolDAOImpl implements RolDAO {
	
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
	public Rol findById(int id) {
		
		CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
		Session session = getOpenSession();
		CriteriaQuery<Rol> criteriaQuery = criteriaBuilder.createQuery(Rol.class);
		Root<Rol> root = criteriaQuery.from(Rol.class);
		criteriaQuery.select(root);
		Predicate pEqRol = criteriaBuilder.equal(root.get("idRol"), id);
		criteriaQuery.where(pEqRol);
		List<Rol> rols = session.createQuery(criteriaQuery).getResultList();
		if(rols != null && rols.size() > 0) {
			return rols.get(0);
		} else {
			return null;
		}

//		Criteria crit = getSession().createCriteria(Rol.class);
//		crit.add(Restrictions.eq("idUsr", id));
//		return (Rol) crit.uniqueResult();
	}

	@Override
	public List<Rol> findAll() {
		
		CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
		Session session = getOpenSession();
		CriteriaQuery<Rol> criteriaQuery = criteriaBuilder.createQuery(Rol.class);
		Root<Rol> root = criteriaQuery.from(Rol.class);
		criteriaQuery.select(root);
		List<Rol> rols = session.createQuery(criteriaQuery).getResultList();
		return rols;
		
//		return getSession().createQuery("from Rol").list();
	}

	@Override
	public void save(Rol rol) {
		getSession().save(rol);
	}
	
	@Override
	public void update(Rol rol) {
		getSession().update(rol);
	}
	
	@Override
	public void delete(Rol rol) {
		getSession().delete(rol);
	}

	@Override
	public List<Rol> findByRolName(String rolName) {
		
		CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
		Session session = getOpenSession();
		CriteriaQuery<Rol> criteriaQuery = criteriaBuilder.createQuery(Rol.class);
		Root<Rol> root = criteriaQuery.from(Rol.class);
		criteriaQuery.select(root);
		Predicate pEqRol = criteriaBuilder.equal(root.get("rol"), rolName);
		criteriaQuery.where(pEqRol);
		List<Rol> rols = session.createQuery(criteriaQuery).getResultList();
		return rols;
	}

}
