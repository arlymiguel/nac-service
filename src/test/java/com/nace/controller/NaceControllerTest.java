package com.nace.controller;

import com.google.gson.Gson;
import com.nace.dto.NaceDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Tag("integration")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class NaceControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Test
    @DisplayName("Creation of a NACE")
    void createNace_Test() throws Exception {
        NaceDto naceDto = NaceDto.builder().order("aa").level("bb").code("cc").parent("dd").description("ee").include("ff")
                .rulings("gg").exclude("hh").reference("ii").build();

        Gson gson = new Gson();
        String naceJSONFormat = gson.toJson(naceDto);

        mockMvc.perform(post("/api/v1/nace")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(naceJSONFormat))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("Update a NACE")
    void updateNace_Test() throws Exception {
        NaceDto naceDto = NaceDto.builder().order("aa").level("bb").code("cc").parent("dd").description("ee").include("ff")
                .rulings("gg").exclude("hh").reference("ii").build();

        Gson gson = new Gson();
        String naceJSONFormat = gson.toJson(naceDto);

        mockMvc.perform(put("/api/v1/nace/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(naceJSONFormat))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("When you try to find by an Id that is not present in database it will throw an exception")
    void findById_Error_Test() throws Exception {

        mockMvc.perform(get("/api/v1/nace/12")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

}
