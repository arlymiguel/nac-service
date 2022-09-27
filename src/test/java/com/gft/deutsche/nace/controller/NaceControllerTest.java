package com.gft.deutsche.nace.controller;

import com.gft.deutsche.nace.dto.NaceDto;
import com.google.gson.Gson;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Tag("integration")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test1")
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


}
