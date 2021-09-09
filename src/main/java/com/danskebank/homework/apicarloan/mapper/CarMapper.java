package com.danskebank.homework.apicarloan.mapper;

import com.danskebank.homework.apicarloan.domain.Car;
import com.danskebank.homework.apicarloan.dto.CarDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarMapper {

  CarDto toDto(Car car);

  Car toEntity(CarDto carDto);
}
