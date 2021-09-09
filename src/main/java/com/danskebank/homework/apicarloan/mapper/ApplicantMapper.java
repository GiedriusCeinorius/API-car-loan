package com.danskebank.homework.apicarloan.mapper;

import com.danskebank.homework.apicarloan.domain.Applicant;
import com.danskebank.homework.apicarloan.dto.ApplicantDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ApplicantMapper {

  ApplicantDto toDto(Applicant applicant);

  @Mapping(source = "applicantDto", target = "maritalStatus", qualifiedByName = "resolveMaritalStatus")
  Applicant toEntity(ApplicantDto applicantDto);

  @Named("resolveMaritalStatus")
  default String resolveMaritalStatus(ApplicantDto applicantDto) {
    return applicantDto.getMaritalStatus().toUpperCase();
  }
}
