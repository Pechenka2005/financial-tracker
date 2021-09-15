package sirius.tinkoff.financialTracker.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import sirius.tinkoff.financialTracker.repository.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTestWithoutSecurity
public class UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void createUser() throws Exception {
        String requestJson = "{\"username\": \"createUser-test\"}";
        assertThat(userRepository.findByUsername("createUser-test")).isEmpty();
        mockMvc.perform(post("/users")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"username\": \"createUser-test\"}"));
        assertThat(userRepository.findByUsername("createUser-test")).isNotEmpty();
    }
}
