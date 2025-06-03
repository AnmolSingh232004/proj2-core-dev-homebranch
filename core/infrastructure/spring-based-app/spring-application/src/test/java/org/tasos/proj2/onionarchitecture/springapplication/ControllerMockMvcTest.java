package org.tasos.proj2.onionarchitecture.springapplication;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@Disabled
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
  //(secure = false)
//@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
@TestPropertySource(properties = "spring.security.enabled=false")
public class ControllerMockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetActivity1() throws Exception {
        System.out.println("testGetActivity1 starts...");
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/proj2/activities/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.title").value("UpperChest_S"))
                .andExpect(jsonPath("$.activitySubType").value("STRENGTH"))
                .andReturn();
        String string = result.getResponse().getContentAsString();
        System.out.println("Result: " + string);
    }

    @Test
    public void testPostActivity1() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/api/proj2/activities/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\" : \"title1\", \"activitySubType\" : \"activitySubType1\", \"activitytype\" : {\"id\" : \"1\"}}"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.CREATED.value()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("title1"));
    }

}