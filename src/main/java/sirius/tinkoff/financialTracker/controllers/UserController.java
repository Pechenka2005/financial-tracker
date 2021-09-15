package sirius.tinkoff.financialTracker.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import sirius.tinkoff.financialTracker.models.User;
import sirius.tinkoff.financialTracker.models.dto.CreateUserDTO;
import sirius.tinkoff.financialTracker.models.dto.UserDTO;
import sirius.tinkoff.financialTracker.services.UserService;

import java.util.List;

@RequestMapping("/users")
@RequiredArgsConstructor
@RestController
@Tag(name="User controller", description="Interaction with users")
public class UserController {

    private final UserService userService;

    @Operation(
            summary = "Getting a user by id"
    )
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO getUser(@PathVariable Long id) {
        return userService.findById(id);
    }

    @Operation(
            summary = "Getting all users"
    )
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> getUser() {
        return userService.findAll();
    }

    // the only method available without auth header
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO createUser(@RequestBody CreateUserDTO user) throws Exception {
        return userService.createUser(user);
    }

    @Operation(
            summary = "Update the specified User"
    )
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO updateUser(@RequestBody CreateUserDTO dto, @Parameter(hidden = true) @AuthenticationPrincipal User user) {
        return userService.updateUser(dto, user);
    }

    @Operation(
            summary = "Delete the specified User"
    )
    @DeleteMapping(value = "/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }
}
