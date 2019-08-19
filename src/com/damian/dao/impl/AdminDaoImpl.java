package com.damian.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.damian.dao.AdminDao;
import com.damian.pojo.Admin;

@Transactional
@Repository
public class AdminDaoImpl implements AdminDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void save(Admin admin) {
		getSession().save(admin);
	}

	@SuppressWarnings({"unchecked","rawtypes"})
	@Override
	public List<Admin> findAll() {
		Query query = getSession().createQuery("from Admin");
		return query.list();
	}

	@SuppressWarnings("deprecation")
	@Override
	public Admin findById(int id) {
		Criteria crit = getSession().createCriteria(Admin.class);
		crit.add(Restrictions.eq("idAd", id));
		return (Admin) crit.uniqueResult();
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<Admin> findByNombre(String nombre) {

		Criteria crit = getSession().createCriteria(Admin.class);
		crit.add(Restrictions.like("nombre", "%" + nombre + "%"));
		
		return crit.list();
	}

	@Override
	public void update(Admin admin) {
		
		getSession().update(admin);
		
	}

	@Override
	public void delete(Admin admin) {
		getSession().delete(admin);
	}
	
	

}
