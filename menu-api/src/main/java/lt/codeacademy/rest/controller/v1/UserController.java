package lt.codeacademy.rest.controller.v1;

import lombok.Data;
import lt.codeacademy.rest.entities.Role;
import lt.codeacademy.rest.entities.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@Deprecated
@RestController("UserController.v1")
@RequestMapping("/v1/user")
public class UserController {

    @GetMapping
    public UserDto getUser(@AuthenticationPrincipal User user) {
        return new UserDto(user);
    }

    @Data
    private static class UserDto {
        private String name;
        private String lastName;
        private Set<String> roles;

        UserDto(User user) {
            this.name = user.getName();
            this.lastName = user.getLastName();
            this.roles = user.getRoles().stream()
                    .map(Role::getRole)
                    .collect(Collectors.toSet());
        }
    }

}
