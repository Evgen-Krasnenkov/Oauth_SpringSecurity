package evgen.service.mapper;

import evgen.model.User;
import evgen.model.dto.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserResponseMapper implements ResponseDtoMapper<UserResponseDto, User> {
    @Override
    public UserResponseDto mapToDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setFirstname(user.getFirstname());
        userResponseDto.setLastname(user.getLastname());
        userResponseDto.setRole(user.getRole().name());
        userResponseDto.setEmail(user.getEmail());
        return userResponseDto;
    }
}
