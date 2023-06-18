package com.pragma.plazoleta.infrastructure.input.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.plazoleta.application.dto.UserDto;
import com.pragma.plazoleta.application.handler.IUserHandler;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest
class AdminControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private IUserHandler userHandler;
    @Autowired
    private ObjectMapper objectMapper;
    private UserDto userDto;
    @BeforeEach
    void setUp(){
        userDto= new UserDto();
        userDto.setName("Ricardo");
        userDto.setLastName("Paez");
        userDto.setCellPhone("3183404089");
        userDto.setEmail("ricardo@hotmail.com");
        userDto.setDocumentId("1143859923");
        userDto.setDateBirth(LocalDate.of(1995,02,27));
        userDto.setPassword("RicardoPaez");
    }

    @Test
    void testSaveOwner() throws Exception {
        ResultActions response =mockMvc.perform(MockMvcRequestBuilders.post("/admin/owner")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)));

        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre", CoreMatchers.is(userDto.getName())));

    }
}