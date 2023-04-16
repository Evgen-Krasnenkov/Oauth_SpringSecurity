package evgen.service;

import evgen.exception.ApplicationException;
import evgen.model.User;
import evgen.repo.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ApplicationException("Can't find a user by ID"));
    }

    @Override
    public User add(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        User retievedUser = userRepository.findById(user.getId()).orElseThrow(
                () -> new ApplicationException("Can't update user with id: " + user.getId()));
        retievedUser.setFirstname(user.getFirstname());
        retievedUser.setLastname(user.getLastname());
        retievedUser.setRole(user.getRole());
        retievedUser.setEmail(user.getEmail());
        return userRepository.save(retievedUser);
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(userRepository.findById(id).orElseThrow(
                () -> new ApplicationException("Can't delete user by ID: " + id)));
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }
}
