package com.damian.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.damian.pojo.Roles;
import com.damian.pojo.Usuario;

@Repository
@Transactional
public class UsuarioDAOImpl implements UsuarioDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Usuario findByUsername(String usuario) {
		
		Criteria crit = getSession().createCriteria(Usuario.class)
				.add(Restrictions.eqOrIsNull("usuario", usuario));
		
		Usuario user = (Usuario) crit.uniqueResult();
		
		if(user != null) {
			Criteria crit2 = getSession().createCriteria(Roles.class)
					.setFetchMode("usuario", FetchMode.JOIN)
					.add(Restrictions.eq("usuario.usuario", usuario))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			user.setRoles((List<Roles>) crit.list());
		}
		
		return user;
		
//		List<Usuario> users = new ArrayList<Usuario>();
//
//		users = getSession()
//			.createQuery("from usuario where usuario like ?")
//			.setParameter(0, usuario).list();
//
//		if (users.size() > 0) {
//			return users.get(0);
//		} else {
//			return null;
//		}
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
