package com.damian.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.damian.pojo.Usuario;

@Repository
@Transactional
public class UsuarioDAOImpl implements UsuarioDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Usuario findByUsername(String usuario) {
		
		Criteria crit = getSession().createCriteria(Usuario.class)
				.add(Restrictions.eqOrIsNull("usuario", usuario));
		
		return (Usuario) crit.uniqueResult();
	}

	@Override
	public void save(Usuario usuario) {
		getSession().save(usuario);
	}

	@Override
	public List<Usuario> findAll() {
		return getSession().createQuery("from Usuario").list();
	}

}
