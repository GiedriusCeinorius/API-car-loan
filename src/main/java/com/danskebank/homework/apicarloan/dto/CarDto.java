package com.danskebank.homework.apicarloan.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
public class CarDto {

  @NotBlank
  private String brand;
  @NotBlank
  private String model;
  @NotNull
  @Positive
  private BigDecimal price;
  @NotNull
  @Positive
  @Min(1)
  private Integer loanLength;
}
