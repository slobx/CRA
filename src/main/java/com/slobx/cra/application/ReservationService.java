package com.slobx.cra.application;

import com.slobx.cra.application.config.BadRequestException;
import com.slobx.cra.application.config.EntityNotFoundException;
import com.slobx.cra.application.dto.ReservationRequestDTO;
import com.slobx.cra.application.dto.ReservationResponseDTO;
import com.slobx.cra.application.intefaces.IReservationService;
import com.slobx.cra.application.mappers.IReservationMapper;
import com.slobx.cra.infrastructure.persistence.ICarRepository;
import com.slobx.cra.infrastructure.persistence.IReservationRepository;
import com.slobx.cra.infrastructure.persistence.IUserRepository;
import com.slobx.cra.infrastructure.persistence.entity.Car;
import com.slobx.cra.infrastructure.persistence.entity.Reservation;
import com.slobx.cra.infrastructure.persistence.entity.User;
import com.slobx.cra.infrastructure.utils.MessageCodeConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService implements IReservationService {

    private final ICarRepository carRepository;
    private final IUserRepository userRepository;
    private final IReservationRepository reservationRepository;

    private static final long secondsInTwoHours = 2*60*60;

    @Override
    public ReservationResponseDTO reserve(ReservationRequestDTO request) {

        //check if start of reservation is after end
        if(request.getStartOfReservation().isAfter(request.getEndOfReservation())) {
            throw new BadRequestException(MessageCodeConstants.RESERVATION_START_TIME_AFTER_END_TIME);
        }

        //check if start of reservation is more than 24h after now
        LocalDateTime dayAhead = LocalDateTime.now().plusDays(1);
        if(request.getStartOfReservation().isAfter(dayAhead)) {
            throw new BadRequestException(MessageCodeConstants.RESERVATION_START_OVERDUE);
        }

        //check if duration of reservation is more than two hours
        long seconds = ChronoUnit.SECONDS.between(request.getStartOfReservation(), request.getEndOfReservation());
        if(seconds > secondsInTwoHours) {
            throw new BadRequestException(MessageCodeConstants.RESERVATION_DURATION_OVERDUE);
        }

        Car car = carRepository.findById(request.getCarId()).orElse(null);
        if(car == null) {
            throw new EntityNotFoundException(Car.class, "id", request.getCarId().toString());
        }
        if(Boolean.FALSE.equals(car.getIsAvailable())) {
            throw new BadRequestException(MessageCodeConstants.CAR_NOT_AVAILABLE);
        }
        car.setIsAvailable(false);

        User user = userRepository.findByUserName(request.getUserName()).orElse(null);
        if(user == null) {
            throw new EntityNotFoundException(User.class, "userName", request.getUserName());
        }

        Reservation reservation = Reservation.builder()
                .startOfReservation(request.getStartOfReservation())
                .endOfReservation(request.getEndOfReservation())
                .car(car)
                .user(user)
                .build();

        return IReservationMapper.INSTANCE.entityToDTO(reservationRepository.save(reservation));
    }

    @Override
    public List<ReservationResponseDTO> getAllReservationForUser(String userName) {
        return reservationRepository.findAllByUserUserName(userName).stream().map(IReservationMapper.INSTANCE::entityToDTO).collect(Collectors.toList());
    }
}
