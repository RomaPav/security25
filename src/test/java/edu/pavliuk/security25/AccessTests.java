package edu.pavliuk.security25;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.pavliuk.security25.movie.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
    @author romat
    @project security25
    @class AccessTests
    @version 1.0.0
    @since 19.04.2025 - 14.21
*/
@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class AccessTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    void beforeAll() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithAnonymousUser
    public void whenAnonymThenStatusUnauthorized() throws Exception {
        mockMvc.perform(get("/api/v1/movies/"))
                .andExpect(status()
                        .isUnauthorized());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
    void whenGetAllAsAdminThenStatusOk() throws Exception {
        mockMvc.perform(get("/api/v1/movies/"))
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(username = "user", password = "user", roles = {"USER"})
    void whenGetAllAsUserThenStatusOk() throws Exception {
        mockMvc.perform(get("/api/v1/movies/"))
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(username = "unknown", password = "unknown", roles = {"UNKNOWN"})
    void whenGetAllAsUnknownThenStatusOk() throws Exception {
        mockMvc.perform(get("/api/v1/movies/"))
                .andExpect(status().isOk());
    }



    @Test
    @WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
    void whenGetByIdAsAdminThenStatusOk() throws Exception {
        mockMvc.perform(get("/api/v1/movies/1"))
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(username = "user", password = "user", roles = {"USER"})
    void whenGetByIdAsUserThenStatusOk() throws Exception {
        mockMvc.perform(get("/api/v1/movies/1"))
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(username = "unknown", password = "unknown", roles = {"UNKNOWN"})
    void whenGetByIdAsUnknownThenStatus403() throws Exception {
        mockMvc.perform(get("/api/v1/movies/1"))
                .andExpect(status().isForbidden());
    }


    @Test
    @WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
    void whenPostAsAdminThenStatusOk() throws Exception {
        Movie movie = new Movie("5", "Title5", "Description5");
        mockMvc.perform(post("/api/v1/movies/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(movie)))
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(username = "user", password = "user", roles = {"USER"})
    void whenPostAsUserThenStatus403() throws Exception {
        Movie movie = new Movie("5", "Title5", "Description5");
        mockMvc.perform(post("/api/v1/movies/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(movie)))
                .andExpect(status().isForbidden());
    }
    @Test
    @WithMockUser(username = "unknown", password = "unknown", roles = {"UNKNOWN"})
    void whenPostAsUnknownThenStatus403() throws Exception {
        Movie movie = new Movie("5", "Title5", "Description5");
        mockMvc.perform(post("/api/v1/movies/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(movie)))
                .andExpect(status().isForbidden());
    }



    @Test
    @WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
    void whenPutAsAdminThenStatusOk() throws Exception {
        Movie movie = new Movie("5", "Title5Updated", "Description5Updated");
        mockMvc.perform(put("/api/v1/movies/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(movie)))
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(username = "user", password = "user", roles = {"USER"})
    void whenPutAsUserThenStatus403() throws Exception {
        Movie movie = new Movie("5", "Title5Updated", "Description5Updated");
        mockMvc.perform(put("/api/v1/movies/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(movie)))
                .andExpect(status().isForbidden());
    }
    @Test
    @WithMockUser(username = "unknown", password = "unknown", roles = {"UNKNOWN"})
    void whenPutAsUnknownThenStatus403() throws Exception {
        Movie movie = new Movie("5", "Title5Updated", "Description5Updated");
        mockMvc.perform(put("/api/v1/movies/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(movie)))
                .andExpect(status().isForbidden());
    }



    @Test
    @WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
    void whenDeleteAsAdminThenStatusOk() throws Exception {
        mockMvc.perform(delete("/api/v1/movies/5"))
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(username = "user", password = "user", roles = {"USER"})
    void whenDeleteAsUserThenStatus403() throws Exception {
        mockMvc.perform(delete("/api/v1/movies/5"))
                .andExpect(status().isForbidden());
    }
    @Test
    @WithMockUser(username = "unknown", password = "unknown", roles = {"UNKNOWN"})
    void whenDeleteAsUnknownThenStatus403() throws Exception {
        mockMvc.perform(delete("/api/v1/movies/5"))
                .andExpect(status().isForbidden());
    }


    @Test
    @WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
    void whenGetForAdminAsAdminThenStatusOk() throws Exception {
        mockMvc.perform(get("/api/v1/movies/admin"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
    void whenGetForUnknownAsAdminThenStatusOk() throws Exception {
        mockMvc.perform(get("/api/v1/movies/unknown"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
    void whenGetForStrangerAsAdminThenStatusOk() throws Exception {
        mockMvc.perform(get("/api/v1/movies/stranger"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
    void whenGetForUserAsAdminThenStatus403() throws Exception {
        mockMvc.perform(get("/api/v1/movies/user"))
                .andExpect(status().isForbidden());
    }


    @Test
    @WithMockUser(username = "user", password = "user", roles = {"USER"})
    void whenGetForAdminAsUserThenStatus403() throws Exception {
        mockMvc.perform(get("/api/v1/movies/admin"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = {"USER"})
    void whenGetForUnknownAsUserThenStatusOk() throws Exception {
        mockMvc.perform(get("/api/v1/movies/unknown"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = {"USER"})
    void whenGetForStrangerAsUserThenStatusOk() throws Exception {
        mockMvc.perform(get("/api/v1/movies/stranger"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = {"USER"})
    void whenGetForUserAsUserThenStatusOk() throws Exception {
        mockMvc.perform(get("/api/v1/movies/user"))
                .andExpect(status().isOk());
    }


    @Test
    @WithMockUser(username = "unknown", password = "unknown", roles = {"UNKNOWN"})
    void whenGetForAdminAsUnknownThenStatus403() throws Exception {
        mockMvc.perform(get("/api/v1/movies/admin"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "unknown", password = "unknown", roles = {"UNKNOWN"})
    void whenGetForUnknownAsUnknownThenStatus403() throws Exception {
        mockMvc.perform(get("/api/v1/movies/unknown"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "unknown", password = "unknown", roles = {"UNKNOWN"})
    void whenGetForStrangerAsUnknownThenStatusOk() throws Exception {
        mockMvc.perform(get("/api/v1/movies/stranger"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "unknown", password = "unknown", roles = {"UNKNOWN"})
    void whenGetForUserAsAdminUnknownStatus403() throws Exception {
        mockMvc.perform(get("/api/v1/movies/user"))
                .andExpect(status().isForbidden());
    }

}
