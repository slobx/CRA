package com.slobx.cra.application;

import com.slobx.cra.application.config.EntityNotFoundException;
import com.slobx.cra.application.dto.CarDTO;
import com.slobx.cra.application.intefaces.ICarService;
import com.slobx.cra.application.mappers.ICarMapper;
import com.slobx.cra.infrastructure.persistence.ICarRepository;
import com.slobx.cra.infrastructure.persistence.entity.Car;
import com.slobx.cra.infrastructure.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarService implements ICarService {

    private final ICarRepository carRepository;

    @Override
    public CarDTO add(CarDTO request) {
        Car car = ICarMapper.INSTANCE.dtoToEntity(request);
        car.setUuid(Utils.getUuid());
        car.setIsAvailable(true);
        return ICarMapper.INSTANCE.entityToDTO(carRepository.save(car)) ;
    }

    @Override
    public List<CarDTO> getAll() {
        List<Car> allCars = carRepository.findAll();
        return allCars.stream().map(ICarMapper.INSTANCE::entityToDTO).collect(Collectors.toList());
    }

    @Override
    public CarDTO findById(Long id) {
        Car car = carRepository.findById(id).orElse(null);
        if(car == null) {
            throw new EntityNotFoundException(Car.class, "id", id.toString());
        }
        return ICarMapper.INSTANCE.entityToDTO(car);
    }

    @Override
    public CarDTO update(CarDTO request) {
        Car car = carRepository.findById(request.getId()).orElse(null);
        if(car == null) {
            throw new EntityNotFoundException(Car.class, "id", request.getId().toString());
        }
        ICarMapper.INSTANCE.updateEntity(request, car);
        return ICarMapper.INSTANCE.entityToDTO(carRepository.save(car)) ;
    }

    @Override
    public void delete(Long id) {
        carRepository.deleteById(id);
    }
}
