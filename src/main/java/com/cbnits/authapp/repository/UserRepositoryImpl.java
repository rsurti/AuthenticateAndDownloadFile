package com.cbnits.authapp.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import com.cbnits.authapp.model.UserData;

/**
 * @author cbnits-154
 *
 */
@Component
public class UserRepositoryImpl {

	@PersistenceContext
	private EntityManager entityManager;

	public List<UserData> findByUsername(String username) {
		String hql = "SELECT e FROM UserData e WHERE e.username = :username";
		TypedQuery<UserData> query = entityManager.createQuery(hql, UserData.class);
		query.setParameter("username", username);
		return query.getResultList();
	}
}