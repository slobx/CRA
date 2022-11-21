package com.slobx.cra.application.mappers;

import com.slobx.cra.application.dto.CarDTO;
import com.slobx.cra.infrastructure.persistence.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ICarMapper {

    ICarMapper INSTANCE = Mappers.getMapper(ICarMapper.class);

    Car dtoToEntity(CarDTO carDTO);
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "id", ignore = true)
    void updateEntity(CarDTO carDTO, @MappingTarget Car car);

    CarDTO entityToDTO(Car car);
}
