package com.damian.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.damian.pojo.Admin;
import com.damian.pojo.Direccion;

@Transactional
@Repository
public class DireccionDaoImpl implements DireccionDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void save(Direccion direccion) {
		getSession().save(direccion);		
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Direccion> findAll(Admin admin) {
		
		Criteria crit = getSession().createCriteria(Direccion.class)
				.setFetchMode("admin", FetchMode.JOIN)
				.add(Restrictions.eq("admin.idAd", admin.getIdAd()))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		return crit.list();
	}

}
