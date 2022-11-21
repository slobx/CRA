package com.slobx.cra.presentation;

import com.slobx.cra.application.config.EntityNotFoundException;
import com.slobx.cra.application.dto.CarDTO;
import com.slobx.cra.application.intefaces.ICarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/car")
@RequiredArgsConstructor
@Tag(name = "Car Controller")
public class CarController {

    private final ICarService carService;

    @GetMapping(value = "/{carId}")
    @Operation(summary = "Get Car by Id")
    public ResponseEntity<CarDTO> getCar(@PathVariable("carId") Long carId) throws EntityNotFoundException {
        return new ResponseEntity<>(carService.findById(carId), HttpStatus.OK);
    }


    @GetMapping
    @Operation(summary = "Get all cars")
    public ResponseEntity<List<CarDTO>> getAllCars() {
        return new ResponseEntity<>(carService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Add new car")
    public ResponseEntity<CarDTO> addCar(@RequestBody @Valid CarDTO request) {
        return new ResponseEntity<>(carService.add(request), HttpStatus.CREATED);
    }

    @PutMapping
    @Operation(summary = "Update existing car")
    public ResponseEntity<CarDTO> updateCar(@RequestBody @Valid CarDTO request) {
        return new ResponseEntity<>(carService.update(request), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{carId}")
    @Operation(summary = "Delete existing car")
    public ResponseEntity<Void> deleteCar(@PathVariable("carId") Long carId) throws EntityNotFoundException {
        carService.delete(carId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
