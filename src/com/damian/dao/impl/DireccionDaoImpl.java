package com.damian.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.damian.dao.DireccionDao;
import com.damian.pojo.Direccion;

@Transactional
@Repository
public class DireccionDaoImpl implements DireccionDao {
	
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
	public Direccion findById(int idDir) {
		
		CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
		Session session = getOpenSession();
		CriteriaQuery<Direccion> criteriaQuery = criteriaBuilder.createQuery(Direccion.class);
		Root<Direccion> root = criteriaQuery.from(Direccion.class);
		criteriaQuery.select(root);
		Predicate pEqDireccion = criteriaBuilder.equal(root.get("idDir"), idDir);
		criteriaQuery.where(pEqDireccion);
		List<Direccion> direcciones = session.createQuery(criteriaQuery).getResultList();
		if(direcciones != null && !direcciones.isEmpty()) {
			return direcciones.get(0);
		} else {
			return null;
		}

	}

	@Override
	public void save(Direccion direccion) {
		getSession().save(direccion);		
	}

	@Override
	public List<Direccion> findListFromUsuario(int idDatosPers) {
		
		CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
		Session session = getOpenSession();
		CriteriaQuery<Direccion> criteriaQuery = criteriaBuilder.createQuery(Direccion.class);
		Root<Direccion> root = criteriaQuery.from(Direccion.class);
		criteriaQuery.select(root);
		Predicate pEqDireccion = criteriaBuilder.equal(root.get("datosPersonales"), idDatosPers);
		criteriaQuery.where(pEqDireccion);
		List<Direccion> direcciones = session.createQuery(criteriaQuery).getResultList();
		
//		Criteria crit = getSession().createCriteria(Direccion.class)
//				.setFetchMode("admin", FetchMode.JOIN)
//				.add(Restrictions.eq("admin.idAd", admin.getIdAd()))
//				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//		
//		return crit.list();
		
		return direcciones;
	}

}
