package com.danskebank.homework.apicarloan.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Data
public class Affordability {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private BigDecimal affordability;

  @OneToOne(mappedBy = "affordability")
  private Applicant applicant;

  public Affordability(BigDecimal affordability, Applicant applicant) {
    this.affordability = affordability;
    this.applicant = applicant;
  }
}
