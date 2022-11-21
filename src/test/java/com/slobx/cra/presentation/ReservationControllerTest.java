package com.slobx.cra.presentation;

import com.slobx.cra.application.ReservationService;
import com.slobx.cra.application.dto.ReservationRequestDTO;
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

import java.util.Collections;

import static com.slobx.cra.common.ReservationRequestBuilder.buildReservationRequestDTO;
import static com.slobx.cra.common.ReservationRequestBuilder.buildReservationResponseDTO;
import static com.slobx.cra.infrastructure.utils.Utils.mapToJson;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ReservationControllerTest {

    private final String testUserName = "test_user_name";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservationService reservationService;

    @Test
    public void getAllUserReservations_testEndpoint_shouldReturnStatusOK() throws Exception {
        when(reservationService.getAllReservationForUser(testUserName)).thenReturn(Collections.singletonList(buildReservationResponseDTO()));
        mockMvc.perform(get(String.format("/api/reservation/%s", testUserName)).header("Origin", "*").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void addNewReservation_testEndpoint_shouldReturnStatusOK() throws Exception {
        ReservationRequestDTO request = buildReservationRequestDTO();
        when(reservationService.reserve(buildReservationRequestDTO())).thenReturn(buildReservationResponseDTO());
        mockMvc.perform(post("/api/reservation").header("Origin", "*").accept(MediaType.APPLICATION_JSON)
                .content(mapToJson(request)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }
}
