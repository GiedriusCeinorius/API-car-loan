package com.danskebank.homework.apicarloan.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@ToString
public class Affordability {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private BigDecimal affordability;
  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "applicant_id")
  private Applicant applicant;
  @JsonManagedReference
  @ToString.Exclude
  @OneToMany(mappedBy = "affordability", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Quote> quotes = new ArrayList<>();

  public Affordability(BigDecimal affordability, Applicant applicant) {
    this.affordability = affordability;
    this.applicant = applicant;
  }
}
