package sirius.tinkoff.financialTracker.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import sirius.tinkoff.financialTracker.models.dto.UserDTO;
import sirius.tinkoff.financialTracker.services.UserService;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ControllerTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void getUser() throws Exception {
        when(userService.findAll()).thenReturn(List.of(
                new UserDTO().setUsername("u1").setId(1L),
                new UserDTO().setUsername("u2").setId(2L),
                new UserDTO().setUsername("u3").setId(3L)
        ));
        String expectedJson = "[{\"id\":1,\"username\":\"u1\"},{\"id\":2,\"username\":\"u2\"},{\"id\":3,\"username\":\"u3\"}]";
        mockMvc.perform(get("/users").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }

}