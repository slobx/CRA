package com.slobx.cra.presentation;

import com.slobx.cra.application.dto.ReservationRequestDTO;
import com.slobx.cra.application.dto.ReservationResponseDTO;
import com.slobx.cra.application.intefaces.IReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/reservation")
@RequiredArgsConstructor
@Tag(name = "Reservation Controller")
public class ReservationController {

    private final IReservationService reservationService;

    @GetMapping(value = "/{userName}")
    @Operation(summary = "Get Reservation by user name")
    public ResponseEntity<List<ReservationResponseDTO>> getAllUserReservations(@PathVariable("userName") String userName) {
        return new ResponseEntity<>(reservationService.getAllReservationForUser(userName), HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Add new Reservation")
    public ResponseEntity<ReservationResponseDTO> addNewReservation(@RequestBody @Valid ReservationRequestDTO request) {
        return new ResponseEntity<>(reservationService.reserve(request), HttpStatus.CREATED);
    }
}
