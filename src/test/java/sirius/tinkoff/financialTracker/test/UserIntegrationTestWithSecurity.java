package sirius.tinkoff.financialTracker.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import sirius.tinkoff.financialTracker.models.User;
import sirius.tinkoff.financialTracker.repository.UserRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTestWithSecurity
public class UserIntegrationTestWithSecurity {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void createUser() throws Exception {
        User user = userRepository.save(new User().setUsername("create-user-sec-test"));
        mockMvc.perform(get("/users/" + user.getId())
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isUnauthorized());
        mockMvc.perform(get("/users/" + user.getId())
                        .header("Authorization", "create-user-sec-test")
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"username\": \"create-user-sec-test\"}"));
    }
}
