package com.danskebank.homework.apicarloan.dto;

import com.danskebank.homework.apicarloan.enums.MaritalStatus;
import com.danskebank.homework.apicarloan.validation.ValueOfEnum;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
public class ApplicantDto {

  @ValueOfEnum(enumClass = MaritalStatus.class)
  private String maritalStatus;
  @Min(1)
  @Max(2)
  private Integer adultNo;
  @NotNull
  private Integer childrenNo;
  @NotNull
  @Positive
  private BigDecimal incomeAfterTax;
}
