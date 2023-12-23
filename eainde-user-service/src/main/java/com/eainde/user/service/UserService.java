package com.eainde.user.service;

import com.eainde.user.entity.User;
import com.eainde.user.repository.UserRepository;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final UserRepository repository;

  UserService(final UserRepository repository) {
    this.repository = repository;
  }

  public User findById(int id) {
    return repository.findById(id);
  }

  public List<User> findAll() {
    return repository.findAll();
  }

  public void add(User user) {
    repository.add(user);
  }

  public User update(User user) {
    return repository.update(user);
  }
}
