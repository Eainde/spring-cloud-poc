package com.eainde.user.repository;

import com.eainde.user.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserRepository {
  @PersistenceContext private final EntityManager entityManager;

  UserRepository(final EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public User findById(int id) {
    return entityManager
        .createQuery("Select o from User o where o.userId=:id", User.class)
        .setParameter("id", id)
        .getSingleResult();
  }

  public List<User> findAll() {
    return entityManager.createQuery("Select o from User o", User.class).getResultList();
  }

  @Transactional
  public void add(User user) {
    entityManager.persist(user);
  }

  @Transactional
  public User update(User user) {
    return entityManager.merge(user);
  }
}
