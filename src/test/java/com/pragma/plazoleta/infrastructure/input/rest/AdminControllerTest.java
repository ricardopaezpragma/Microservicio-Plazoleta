package com.pragma.plazoleta.infrastructure.input.rest;

import com.pragma.plazoleta.application.handler.IDishHandler;
import com.pragma.plazoleta.application.handler.IUserHandler;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;


@WebMvcTest
@AutoConfigureMockMvc
class AdminControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private IUserHandler userHandler;

    @MockBean
    IDishHandler dishHandler;
    @MockBean
    AdminController adminController;

    @Test
    public void testSaveOwner_HU1_valid() throws Exception {
        MediaType textPlainUtf8 = new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8);
        String userDto = "{\"name\": \"Ricardo\", " +
                "\"lastName\": \"Paez\", " +
                "\"cellPhone\": \"3183404089\", " +
                "\"documentId\": \"1143859923\", " +
                "\"dateBirth\": \"2000-05-21\", " +
                "\"password\": \"3183404089\", " +
                "\"email\" : \"bob@domain.com\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/owner")
                        .content(userDto)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testSaveOwner_HU1_invalid() throws Exception {
        String userDto = "{\"name\": \"\", " +
                "\"lastName\": \"\", " +
                "\"cellPhone\": \"qwertyuiop\", " +
                "\"documentId\": \"rpoas\", " +
                "\"dateBirth\": \"\", " +
                "\"password\": \"\", " +
                "\"email\" : \"\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/owner")
                        .content(userDto)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Is.is("Nombre requerido.")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", Is.is("Apellido requerido.")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password", Is.is("Contraseña  requerido")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.documentId", Is.is("Solo se permiten números")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dateBirth", Is.is("Fecha de nacimiento requerido")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Is.is("Correo requerido")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cellPhone", Is.is("Solo se permiten números")))
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testCrateRestaurant_HU2_valid() throws Exception {
        MediaType textPlainUtf8 = new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8);
        String userDto = "{\"name\": \"McDonalds\", " +
                "\"address\": \"Calle falsa #123\", " +
                "\"ownerId\": \"16\", " +
                "\"phone\": \"0180002344\", " +
                "\"urlLogo\": \"https://cdn-icons-png.flaticon.com/512/732/732217.png\", " +
                "\"nit\" : \"000112222\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/restaurant")
                        .content(userDto)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testCrateRestaurant_HU2_invalid() throws Exception {
        String userDto = "{\"name\": \"\", " +
                "\"address\": \"\", " +
                "\"ownerId\": \"null\", " +
                "\"phone\": \"qwertyuiop\", " +
                "\"urlLogo\": \"\", " +
                "\"nit\" : \"RPAS\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/restaurant")
                        .content(userDto)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Is.is("El nombre no puede estar vacio o compuesto solo de números.")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address", Is.is("Dirección requerida.")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone", Is.is("Solo se permiten números")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nit", Is.is("Solo se permiten números")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ownerId", Is.is("Doc Identidad requerido.")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.urlLogo", Is.is("url del logo requerida.")))
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON));
    }

}