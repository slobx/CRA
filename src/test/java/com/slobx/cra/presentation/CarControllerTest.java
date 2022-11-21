package com.slobx.cra.presentation;

import com.slobx.cra.application.CarService;
import com.slobx.cra.application.dto.CarDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static com.slobx.cra.common.CarRequestBuilder.buildNewCarDTO;
import static com.slobx.cra.common.CarRequestBuilder.buildUpdateCarDTO;
import static com.slobx.cra.infrastructure.utils.Utils.mapToJson;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CarControllerTest {

    private final Long carId = 1L;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService carService;

    @Test
    public void getCarById_testEndpoint_shouldReturnStatusOK() throws Exception {
        when(carService.findById(carId)).thenReturn(new CarDTO());
        mockMvc.perform(get(String.format("/api/car/%d", carId)).header("Origin", "*").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void getAllCars_testEndpoint_shouldReturnStatusOK() throws Exception {
        when(carService.getAll()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/api/car").header("Origin", "*").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void addCar_testEndpoint_shouldReturnStatusCreated() throws Exception {
        CarDTO request = buildNewCarDTO();
        when(carService.add(request)).thenReturn(buildUpdateCarDTO());
        mockMvc.perform(post("/api/car").header("Origin", "*").accept(MediaType.APPLICATION_JSON)
                .content(mapToJson(request)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }

    @Test
    public void updateCar_testEndpoint_shouldReturnStatusOK() throws Exception {
        CarDTO request = buildUpdateCarDTO();
        when(carService.add(request)).thenReturn(buildUpdateCarDTO());
        mockMvc.perform(put("/api/car").header("Origin", "*").accept(MediaType.APPLICATION_JSON)
                .content(mapToJson(request)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void deleteCar_testEndpoint_shouldReturnStatusOK() throws Exception {
        doNothing().when(carService).delete(carId);
        mockMvc.perform(delete(String.format("/api/car/%d", carId)).header("Origin", "*").accept(MediaType.APPLICATION_JSON)
               .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
}
