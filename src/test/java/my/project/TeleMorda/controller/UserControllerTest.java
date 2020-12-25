package my.project.TeleMorda.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import my.project.TeleMorda.module.MyUser;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@TestPropertySource("/application-test.properties")
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper om;

    @Test
    void addNewUser() throws Exception {
        MyUser user = new MyUser("telesyn", "123");
        MvcResult result = mockMvc.perform(post("/admin/adduser")
                .contentType(APPLICATION_JSON)
                .content(om.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andReturn();
        String rrr = result.getResponse().getContentAsString();
        Assert.assertTrue(result.getResponse().getContentAsString().contains("-"));
    }
}