package com.slobx.cra.application.mappers;

import com.slobx.cra.application.dto.ReservationResponseDTO;
import com.slobx.cra.infrastructure.persistence.entity.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IReservationMapper {

    IReservationMapper INSTANCE = Mappers.getMapper(IReservationMapper.class);

    ReservationResponseDTO entityToDTO(Reservation reservation);
}
