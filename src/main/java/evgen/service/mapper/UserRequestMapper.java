package evgen.service.mapper;

import evgen.model.Role;
import evgen.model.User;
import evgen.model.dto.UserRequestDto;
import org.springframework.stereotype.Service;

@Service
public class UserRequestMapper implements RequestDtoMapper<User, UserRequestDto> {
    @Override
    public User mapToModel(UserRequestDto dto) {
        User user = new User();
        user.setFirstname(dto.getFirstname());
        user.setLastname(dto.getLastname());
        user.setRole(Role.valueOf(dto.getRole()));
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }
}
