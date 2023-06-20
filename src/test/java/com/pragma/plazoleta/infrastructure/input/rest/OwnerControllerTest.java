package com.pragma.plazoleta.infrastructure.input.rest;

import com.pragma.plazoleta.application.handler.IDishHandler;
import com.pragma.plazoleta.application.handler.IRestaurantHandler;
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

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest
@AutoConfigureMockMvc
class OwnerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private IUserHandler userHandler;
    @MockBean
    private IDishHandler dishHandler;
    @MockBean
    private OwnerController ownerController;
    @MockBean
    private IRestaurantHandler restaurantHandler;

    @Test
    public void createDish_HU3_valid() throws Exception {
        MediaType textPlainUtf8 = new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8);
        String userDto = "{\n" +
                "    \"name\": \"BicMac\",\n" +
                "    \"categoryId\": 1,\n" +
                "    \"description\": \"Hamburguesa doble carne\",\n" +
                "    \"price\": 20000,\n" +
                "    \"restaurantId\": 5,\n" +
                "    \"urlImage\": \"https://cache-backend-mcd.mcdonaldscupones.com/media/image/product$kPXQR3r0/200/200/original?country=co\"\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/owner/dish")
                        .content(userDto)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void createDish_HU3_invalid() throws Exception {
        MediaType textPlainUtf8 = new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8);
        String userDto = "{\"name\": \"\", " +
                "\"categoryId\": \"null\", " +
                "\"description\": \"\", " +
                "\"price\": \"-13333\", " +
                "\"restaurantId\": \"5\", " +
                "\"urlImage\" : \"\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/owner/dish")
                        .content(userDto)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Is.is("Nombre requerido.")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", Is.is("Descripción requerida.")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price", Is.is("El precio tiene que ser mayor a 0 y ser un número entero.")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.categoryId", Is.is("Id de categoria requerido.")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.urlImage", Is.is("Url de la imagen requerido.")))
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void updateDish_HU4_valid() throws Exception {
        MediaType textPlainUtf8 = new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8);
        String userDto = "{\n" +
                "    \"id\": 8,\n" +
                "    \"description\": \"Super Hamburguesa\",\n" +
                "    \"price\": \"25000\"\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.put("/owner/dish")
                        .content(userDto)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void updateDish_HU4_invalid() throws Exception {
        MediaType textPlainUtf8 = new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8);
        String userDto = "{\n" +
                "    \"id\": null,\n" +
                "    \"description\": \"\",\n" +
                "    \"price\": \"-25000\"\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.put("/owner/dish")
                        .content(userDto)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is("Id requerido para modificar.")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", Is.is("Descripción requerida.")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price", Is.is("El precio tiene que ser mayor a 0 y ser un número entero.")))
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON));
    }
}