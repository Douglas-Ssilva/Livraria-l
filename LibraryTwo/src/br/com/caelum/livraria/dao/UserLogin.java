package br.com.caelum.livraria.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.caelum.livraria.modelo.User;

public class UserLogin {
	
	public boolean login(User user) {
		User user2= null;
		EntityManager em = new JPAUtil().getEntityManager();
		Query query = em.createQuery("select u from User u where u.email= :email and u.senha= :senha");
		query.setParameter("email", user.getEmail());
		query.setParameter("senha", user.getSenha());
		
		try {
			user2 = (User) query.getSingleResult();
		}catch(NoResultException e) {
			return false;
		}finally {
			em.close();
		}

		return user2 != null;
	}

}
