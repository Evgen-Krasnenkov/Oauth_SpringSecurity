package evgen.service;

import evgen.model.User;

import java.util.List;

public interface UserService {
    User getById(Long id);
    User add(User user);
    User update(User user);
    void delete(Long id);
    List<User> findAll();
}
