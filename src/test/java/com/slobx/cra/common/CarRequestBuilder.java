package com.slobx.cra.common;

import com.slobx.cra.application.dto.CarDTO;
import com.slobx.cra.application.mappers.ICarMapper;
import com.slobx.cra.infrastructure.persistence.entity.Car;

import java.math.BigInteger;
import java.util.UUID;

public class CarRequestBuilder {

    public static CarDTO buildNewCarDTO() {
        return CarDTO.builder().make("test_make").model("test_model").uuid(getUuid()).build();
    }

    public static CarDTO buildUpdateCarDTO() {
        return CarDTO.builder().id(1L).make("updated_test_make").model("updated_test_model").uuid(getUuid()).build();
    }

    public static Car buildCarEntity() {
        return Car.builder().id(1L).isAvailable(true).make("test_make").model("test_model").uuid(getUuid()).build();
    }

    public static Car getMockUpdateCarEntity(CarDTO requestDTO) {

        Car response = ICarMapper.INSTANCE.dtoToEntity(requestDTO);
        response.setIsAvailable(true);
        response.setUuid(getUuid());
        response.setId(response.getId());

        return response;
    }

    private static String getUuid() {
        return "C" + String.format("%040d", new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16));
    }
}
