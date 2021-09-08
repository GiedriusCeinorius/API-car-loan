package com.danskebank.homework.apicarloan.service;

import com.danskebank.homework.apicarloan.domain.Car;
import com.danskebank.homework.apicarloan.domain.Quote;
import com.danskebank.homework.apicarloan.dto.QuoteCalculationResponse;
import com.danskebank.homework.apicarloan.repository.CarRepository;
import org.springframework.stereotype.Service;

@Service
public class CarLoanService {

  private final CarRepository carRepository;

  public CarLoanService(CarRepository carRepository) {
    this.carRepository = carRepository;
  }

  public QuoteCalculationResponse saveCarLoanRequest(Car car, Quote quote) {
    car.setQuote(quote);
    Car savedCar = carRepository.save(car);
    return new QuoteCalculationResponse(savedCar.getQuote().getId(), savedCar.getQuote().getQuote());
  }
}
