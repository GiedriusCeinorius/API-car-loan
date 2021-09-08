package com.danskebank.homework.apicarloan.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
public class BankMargin {

  @Id
  private Long id;
  private BigDecimal bankMargin;
}
