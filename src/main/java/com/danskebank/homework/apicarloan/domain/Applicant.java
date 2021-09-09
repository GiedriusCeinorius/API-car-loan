package com.danskebank.homework.apicarloan.domain;

import com.danskebank.homework.apicarloan.enums.MaritalStatus;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Entity
@Data
public class Applicant {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Enumerated(EnumType.STRING)
  private MaritalStatus maritalStatus;
  private Integer adultNo;
  private Integer childrenNo;
  private BigDecimal incomeAfterTax;
  @OneToOne(mappedBy = "applicant")
  private Affordability affordability;

}
