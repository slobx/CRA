package com.slobx.cra.application;

import com.slobx.cra.application.dto.CarDTO;
import com.slobx.cra.infrastructure.persistence.ICarRepository;
import com.slobx.cra.infrastructure.persistence.entity.Car;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Optional;

import static com.slobx.cra.common.CarRequestBuilder.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class CarServiceTest {

    private CarService carService;

    @MockBean
    private ICarRepository carRepository;

    private final Long carId = 1L;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        carService = new CarService(carRepository);
    }

    @Test
    public void getCarById_getById_retrieveSpecificRowFromDB() {
        when(carRepository.findById(carId)).thenReturn(Optional.of(buildCarEntity()));
        assertThat(carService.findById(carId)).isNotNull().extracting(CarDTO::getMake, CarDTO::getModel,
                CarDTO::getUuid).isNotNull();
    }

    @Test
    public void getAllCars_getAll_retrieveAllRowsFromDB() {
        when(carRepository.findAll()).thenReturn(Collections.singletonList(buildCarEntity()));
        assertThat(carService.getAll()).isNotNull().extracting(CarDTO::getMake, CarDTO::getModel,
                CarDTO::getUuid).isNotNull();
    }

    @Test
    public void updateCar_update_updateRowInDB() {
        CarDTO mockUpdateRequest = buildUpdateCarDTO();
        when(carRepository.findById(mockUpdateRequest.getId())).thenReturn(Optional.of(buildCarEntity()));
        when(carRepository.save(any(Car.class))).thenReturn(getMockUpdateCarEntity(mockUpdateRequest));

        CarDTO mockUpdatedEntity = carService.update(buildUpdateCarDTO());
        assertThat(mockUpdatedEntity.getMake().equals(mockUpdateRequest.getMake())).isTrue();
        assertThat(mockUpdatedEntity.getModel().equals(mockUpdateRequest.getModel())).isTrue();
        assertThat(mockUpdatedEntity.getId().equals(mockUpdateRequest.getId())).isTrue();
        assertThat(mockUpdatedEntity.getUuid().equals(mockUpdateRequest.getUuid())).isFalse();
    }

    // nothing to test here except code coverage, method is void and relies on JPA to
    // delete the entity
    @Test
    public void deleteCar_delete_removeRowFromDB() {
        doNothing().when(carRepository).deleteById(carId);
        carService.delete(carId);
    }
}
