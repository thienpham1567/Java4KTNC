package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import controller.JpaUtils;
import model.User;

public class UserDAO {

	@Override
	protected void finalize() throws Throwable {
		EntityManager em = JpaUtils.getEntityManager();
		em.close();
		super.finalize();
	}

	public void create(User user) {
		EntityManager em = JpaUtils.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public void update(User user) {
		EntityManager em = JpaUtils.getEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(user);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public void delete(String id) throws Exception {
		EntityManager em = JpaUtils.getEntityManager();
		try {
			em.getTransaction().begin();
			User user = em.find(User.class, id);
			em.remove(user);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	public User findById(String id) {
		EntityManager em = JpaUtils.getEntityManager();
		User entity = em.find(User.class, id);
		return entity;
	}

//	public List<User> findAll() {
//		EntityManager em = JpaUtils.getEntityManager();
//		String jpql = "SELECT u FROM User u";
//		TypedQuery<User> query = em.createQuery(jpql, User.class);
//		List<User> list = query.getResultList();
//		return list;
//	}

	public List<User> findAll() {
		EntityManager em = JpaUtils.getEntityManager();
		TypedQuery<User> query = em.createNamedQuery("User.findAll", User.class);
		return query.getResultList();
	}

	public List<User> findAll(int page, int pageSize) {
		EntityManager em = JpaUtils.getEntityManager();
		TypedQuery<User> query = em.createNamedQuery("User.findAll", User.class);
		query.setFirstResult(page * pageSize);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}

	public User checkLogin(String id, String password) {
		EntityManager em = JpaUtils.getEntityManager();
		String jpql = "select u from User u where u.Id = :Id and u.Password = :Password";
		TypedQuery<User> query = em.createQuery(jpql, User.class);
		query.setParameter("id", id);
		query.setParameter("password", password);
		return query.getSingleResult();
	}

	public List<User> findByFullname(String fullname) {
		EntityManager em = JpaUtils.getEntityManager();
		String jpql = "select u from User u where u.Fullname like :Fullname";
		TypedQuery<User> query = em.createQuery(jpql, User.class);
		query.setParameter("fullname", "%" + fullname + "%");
		return query.getResultList();
	}

	public int count() {
		EntityManager em = JpaUtils.getEntityManager();
		String jpql = "select count( u) from User u ";
		Query query = em.createQuery(jpql);
		return ((Long) query.getSingleResult()).intValue();
	}
}
