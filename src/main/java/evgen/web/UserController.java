package evgen.web;

import evgen.model.User;
import evgen.model.dto.UserRequestDto;
import evgen.model.dto.UserResponseDto;
import evgen.service.UserService;
import evgen.service.mapper.UserRequestMapper;
import evgen.service.mapper.UserResponseMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserResponseMapper mapper;
    private final UserRequestMapper requestMapper;

    public UserController(UserService userService, UserResponseMapper mapper, UserRequestMapper requestMapper) {
        this.userService = userService;
        this.mapper = mapper;
        this.requestMapper = requestMapper;
    }
    @GetMapping("/users")
    @PreAuthorize("hasAnyRole()")
    public List<UserResponseDto> findAll(){
        return userService.findAll()
                .stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole()")
    public UserResponseDto getById(@PathVariable Long id){
        return mapper.mapToDto(userService.getById(id));
    }
    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto save(@RequestBody UserRequestDto userRequestDto){
        User addedUser = userService.add(requestMapper.mapToModel(userRequestDto));
        return mapper.mapToDto(addedUser);
    }
    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponseDto update(@PathVariable Long id, @RequestBody UserRequestDto userRequestDto){
        User user = requestMapper.mapToModel(userRequestDto);
        user.setId(id);
        return mapper.mapToDto(userService.update(user));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public void delete(@PathVariable Long id){
        userService.delete(id);
    }
}
