package com.slobx.cra.application;

import com.slobx.cra.application.config.BadRequestException;
import com.slobx.cra.application.dto.ReservationRequestDTO;
import com.slobx.cra.application.dto.ReservationResponseDTO;
import com.slobx.cra.infrastructure.persistence.ICarRepository;
import com.slobx.cra.infrastructure.persistence.IReservationRepository;
import com.slobx.cra.infrastructure.persistence.IUserRepository;
import com.slobx.cra.infrastructure.persistence.entity.Reservation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Optional;

import static com.slobx.cra.common.CarRequestBuilder.buildCarEntity;
import static com.slobx.cra.common.ReservationRequestBuilder.buildReservationEntity;
import static com.slobx.cra.common.ReservationRequestBuilder.buildReservationRequestDTO;
import static com.slobx.cra.common.UserRequestBuilder.buildUserEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class ReservationServiceTest {

    private ReservationService reservationService;

    @MockBean
    private ICarRepository carRepository;
    @MockBean
    private IUserRepository userRepository;
    @MockBean
    private IReservationRepository reservationRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        reservationService = new ReservationService(carRepository, userRepository, reservationRepository);
    }

    @Test
    public void getAllReservationsForUser_getAll_retrieveAllReservationFromDB() {
        when(reservationRepository.findAllByUserUserName(any(String.class))).thenReturn(Collections.singletonList(buildReservationEntity()));
        assertThat(reservationService.getAllReservationForUser(any(String.class))).isNotNull()
                .extracting(ReservationResponseDTO::getId, ReservationResponseDTO::getStartOfReservation, ReservationResponseDTO::getEndOfReservation,
                ReservationResponseDTO::getCar, ReservationResponseDTO::getUser)
                .isNotNull();
    }

    @Test
    public void makeReservation_addRowToDB() {
        ReservationRequestDTO mockReserveRequest = buildReservationRequestDTO();

        when(carRepository.findById(mockReserveRequest.getCarId())).thenReturn(Optional.of(buildCarEntity()));
        when(userRepository.findByUserName(mockReserveRequest.getUserName())).thenReturn(Optional.of(buildUserEntity()));
        when(reservationRepository.save(any(Reservation.class))).thenReturn(buildReservationEntity());

        ReservationResponseDTO mockReserveEntity = reservationService.reserve(mockReserveRequest);
        assertThat(mockReserveEntity.getStartOfReservation().equals(mockReserveRequest.getStartOfReservation())).isTrue();
        assertThat(mockReserveEntity.getEndOfReservation().equals(mockReserveRequest.getEndOfReservation())).isTrue();
        assertThat(mockReserveEntity.getCar().getId().equals(mockReserveRequest.getCarId())).isTrue();
        assertThat(mockReserveEntity.getUser().getUserName().equals(mockReserveRequest.getUserName())).isTrue();
    }

    @Test(expected = BadRequestException.class)
    public void makeReservation_givenReservationStartTimeAfterEndTime_throwsBadRequestException() {
        ReservationRequestDTO mockReserveRequest = buildReservationRequestDTO();
        mockReserveRequest.setStartOfReservation(mockReserveRequest.getStartOfReservation().plusHours(5));

        when(carRepository.findById(mockReserveRequest.getCarId())).thenReturn(Optional.of(buildCarEntity()));
        when(userRepository.findByUserName(mockReserveRequest.getUserName())).thenReturn(Optional.of(buildUserEntity()));
        when(reservationRepository.save(any(Reservation.class))).thenReturn(buildReservationEntity());

        ReservationResponseDTO mockReserveEntity = reservationService.reserve(mockReserveRequest);
        assertThat(mockReserveEntity.getStartOfReservation().equals(mockReserveRequest.getStartOfReservation())).isTrue();
        assertThat(mockReserveEntity.getEndOfReservation().equals(mockReserveRequest.getEndOfReservation())).isTrue();
        assertThat(mockReserveEntity.getCar().getId().equals(mockReserveRequest.getCarId())).isTrue();
        assertThat(mockReserveEntity.getUser().getUserName().equals(mockReserveRequest.getUserName())).isTrue();
    }

    @Test(expected = BadRequestException.class)
    public void makeReservation_givenReservationStartTimeOverdue_throwsBadRequestException() {
        ReservationRequestDTO mockReserveRequest = buildReservationRequestDTO();
        mockReserveRequest.setStartOfReservation(mockReserveRequest.getStartOfReservation().plusDays(1));

        when(carRepository.findById(mockReserveRequest.getCarId())).thenReturn(Optional.of(buildCarEntity()));
        when(userRepository.findByUserName(mockReserveRequest.getUserName())).thenReturn(Optional.of(buildUserEntity()));
        when(reservationRepository.save(any(Reservation.class))).thenReturn(buildReservationEntity());

        ReservationResponseDTO mockReserveEntity = reservationService.reserve(mockReserveRequest);
        assertThat(mockReserveEntity.getStartOfReservation().equals(mockReserveRequest.getStartOfReservation())).isTrue();
        assertThat(mockReserveEntity.getEndOfReservation().equals(mockReserveRequest.getEndOfReservation())).isTrue();
        assertThat(mockReserveEntity.getCar().getId().equals(mockReserveRequest.getCarId())).isTrue();
        assertThat(mockReserveEntity.getUser().getUserName().equals(mockReserveRequest.getUserName())).isTrue();
    }

    @Test(expected = BadRequestException.class)
    public void makeReservation_givenReservationDurationOverdue_throwsBadRequestException() {
        ReservationRequestDTO mockReserveRequest = buildReservationRequestDTO();
        mockReserveRequest.setStartOfReservation(mockReserveRequest.getStartOfReservation().plusHours(3));

        when(carRepository.findById(mockReserveRequest.getCarId())).thenReturn(Optional.of(buildCarEntity()));
        when(userRepository.findByUserName(mockReserveRequest.getUserName())).thenReturn(Optional.of(buildUserEntity()));
        when(reservationRepository.save(any(Reservation.class))).thenReturn(buildReservationEntity());

        ReservationResponseDTO mockReserveEntity = reservationService.reserve(mockReserveRequest);
        assertThat(mockReserveEntity.getStartOfReservation().equals(mockReserveRequest.getStartOfReservation())).isTrue();
        assertThat(mockReserveEntity.getEndOfReservation().equals(mockReserveRequest.getEndOfReservation())).isTrue();
        assertThat(mockReserveEntity.getCar().getId().equals(mockReserveRequest.getCarId())).isTrue();
        assertThat(mockReserveEntity.getUser().getUserName().equals(mockReserveRequest.getUserName())).isTrue();
    }
}
