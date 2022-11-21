package com.slobx.cra.application.intefaces;

import com.slobx.cra.application.dto.CarDTO;

import java.util.List;

public interface ICarService {
    CarDTO add(CarDTO request);
    List<CarDTO> getAll();
    CarDTO findById(Long id);
    CarDTO update(CarDTO request);
    void delete(Long id);
}
