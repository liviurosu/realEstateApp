package com.example.app.controllers;

import com.example.app.entities.RealEstate;
import com.example.app.models.Type;
import com.example.app.repositories.RealEstateRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MCVTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RealEstateRepository realEstateRepository;

    @Test
    public void test1(){
        RealEstate realEstate = new RealEstate();
        realEstate.setParking(true);
        realEstate.setType(Type.STUDIO);
        realEstate.setOwner("Test");
        realEstate.setMonthlyRate(200.0);

        when(realEstateRepository.save(realEstate))
                .thenReturn(realEstate);
        ObjectMapper mapper = new ObjectMapper();
        try {
            var requestBody = mapper.writeValueAsString(realEstate);
            mockMvc.perform(
                    post("/add")
                            .content(requestBody)
                            .contentType(MediaType.APPLICATION_JSON)
            ).andExpect(status().isCreated());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
