package com.danskebank.homework.apicarloan.controller;

import com.danskebank.homework.apicarloan.controller.response.AffordabilityCalculationResponse;
import com.danskebank.homework.apicarloan.controller.response.CreditDecisionResponse;
import com.danskebank.homework.apicarloan.controller.response.QuoteCalculationResponse;
import com.danskebank.homework.apicarloan.domain.Applicant;
import com.danskebank.homework.apicarloan.domain.Car;
import com.danskebank.homework.apicarloan.dto.ApplicantDto;
import com.danskebank.homework.apicarloan.dto.CarDto;
import com.danskebank.homework.apicarloan.enums.ApiErrorCode;
import com.danskebank.homework.apicarloan.enums.MaritalStatus;
import com.danskebank.homework.apicarloan.exception.ApiException;
import com.danskebank.homework.apicarloan.mapper.ApplicantMapper;
import com.danskebank.homework.apicarloan.mapper.CarMapper;
import com.danskebank.homework.apicarloan.service.AffordabilityServiceFactory;
import com.danskebank.homework.apicarloan.service.CarLoanService;
import com.danskebank.homework.apicarloan.service.MarriedAffordabilityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ApiController.class)
class ApiControllerTest {

  @MockBean
  private AffordabilityServiceFactory affordabilityServiceFactory;
  @MockBean
  private CarLoanService carLoanService;
  @MockBean
  private ApplicantMapper applicantMapper;
  @MockBean
  private CarMapper carMapper;
  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  private MockMvc mockMvc;

  @Test
  void createAffordability_validRequest_returnAffordability() throws Exception {
    ApplicantDto applicantDto = getApplicantDto("MARRIED", 2, 2, BigDecimal.valueOf(200.50));
    Applicant applicant = getApplicant(applicantDto);
    MarriedAffordabilityService marriedAffordabilityService = Mockito.mock(MarriedAffordabilityService.class);
    when(applicantMapper.toEntity(applicantDto)).thenReturn(getApplicant(applicantDto));
    when(affordabilityServiceFactory.getAffordabilityService(applicant)).thenReturn(marriedAffordabilityService);
    when(marriedAffordabilityService.createAffordability()).thenReturn(new AffordabilityCalculationResponse(1L));

    mockMvc.perform(MockMvcRequestBuilders.post("/loan/affordabilityCalculation")
                    .content(objectMapper.writeValueAsString(applicantDto))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.affordabilityId").value(1))
            .andDo(print()).andReturn();
  }

  @Test
  void createAffordability_inValidRequestMaritalStatus_exceptionThrown() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/loan/affordabilityCalculation")
                    .content(objectMapper.writeValueAsString(getApplicantDto("WIDOW", 2, 2, BigDecimal.valueOf(200.50))))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Bad argument! maritalStatus"))
            .andDo(print()).andReturn();
  }

  @Test
  void createAffordability_inValidRequestNumberOfAdults_exceptionThrown() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/loan/affordabilityCalculation")
                    .content(objectMapper.writeValueAsString(getApplicantDto("MARRIED", 3, 2, BigDecimal.valueOf(200.50))))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Bad argument! adultNo"))
            .andDo(print()).andReturn();
  }

  @Test
  void createAffordability_inValidRequestNegativeIncomeAfterTax_exceptionThrown() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/loan/affordabilityCalculation")
                    .content(objectMapper.writeValueAsString(getApplicantDto("MARRIED", 2, 2, BigDecimal.valueOf(-200.50))))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Bad argument! incomeAfterTax"))
            .andDo(print()).andReturn();
  }

  @Test
  void createQuote_validRequest_quoteCreated() throws Exception {
    CarDto carDto = getCarDto("Ford", "Escort", BigDecimal.valueOf(9900), 6);
    Car car = getCar(carDto);
    when(carMapper.toEntity(carDto)).thenReturn(car);
    when(carLoanService.createQuote(car)).thenReturn(new QuoteCalculationResponse(1l, BigDecimal.valueOf(1.70)));

    mockMvc.perform(MockMvcRequestBuilders.post("/loan/quoteCalculation")
                    .content(objectMapper.writeValueAsString(carDto))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
            .andExpect(MockMvcResultMatchers.jsonPath("$.quote").value(1.70))
            .andDo(print()).andReturn();
  }

  @Test
  void createQuote_inValidRequestMissingBrand_exceptionThrown() throws Exception {
    CarDto carDto = getCarDto(null, "Escort", BigDecimal.valueOf(9900), 6);
    Car car = getCar(carDto);
    when(carMapper.toEntity(carDto)).thenReturn(car);
    when(carLoanService.createQuote(car)).thenReturn(new QuoteCalculationResponse(1l, BigDecimal.valueOf(1.70)));

    mockMvc.perform(MockMvcRequestBuilders.post("/loan/quoteCalculation")
                    .content(objectMapper.writeValueAsString(carDto))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Bad argument! brand"))
            .andDo(print()).andReturn();
  }

  @Test
  void createQuote_inValidRequestNegativePrice_exceptionThrown() throws Exception {
    CarDto carDto = getCarDto("Ford", "Escort", BigDecimal.valueOf(-9900), 6);
    Car car = getCar(carDto);
    when(carMapper.toEntity(carDto)).thenReturn(car);
    when(carLoanService.createQuote(car)).thenReturn(new QuoteCalculationResponse(1l, BigDecimal.valueOf(1.70)));

    mockMvc.perform(MockMvcRequestBuilders.post("/loan/quoteCalculation")
                    .content(objectMapper.writeValueAsString(carDto))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Bad argument! price"))
            .andDo(print()).andReturn();
  }

  @Test
  void getDecision_validRequest_decisionReturned() throws Exception {
    Long affordabilityId = 1L;
    Long quoteId = 1L;
    when(carLoanService.getDecision(affordabilityId, quoteId)).thenReturn(new CreditDecisionResponse("YES"));

    mockMvc.perform(MockMvcRequestBuilders.get("/loan/creditDecision/{affordabilityId}/{quoteId}", affordabilityId, quoteId))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.decision").value("YES"))
            .andDo(print()).andReturn();
  }

  @Test
  void getDecision_inValidRequestAffordabilityNotExist_exceptionThrown() throws Exception {
    Long affordabilityId = 1L;
    Long quoteId = 1L;
    when(carLoanService.getDecision(affordabilityId, quoteId)).thenThrow(new ApiException(ApiErrorCode.API_ERROR_002));

    mockMvc.perform(MockMvcRequestBuilders.get("/loan/creditDecision/{affordabilityId}/{quoteId}", affordabilityId, quoteId))
            .andExpect(status().isBadRequest())
            .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Affordability not found!"))
            .andDo(print()).andReturn();
  }

  @Test
  void getDecision_inValidRequestQuoteNotExist_exceptionThrown() throws Exception {
    Long affordabilityId = 1L;
    Long quoteId = 1L;
    when(carLoanService.getDecision(affordabilityId, quoteId)).thenThrow(new ApiException(ApiErrorCode.API_ERROR_003));

    mockMvc.perform(MockMvcRequestBuilders.get("/loan/creditDecision/{affordabilityId}/{quoteId}", affordabilityId, quoteId))
            .andExpect(status().isBadRequest())
            .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Quote not found!"))
            .andDo(print()).andReturn();
  }


  private Applicant getApplicant(ApplicantDto applicantDto) {
    Applicant applicant = new Applicant();
    applicant.setMaritalStatus(MaritalStatus.valueOf(applicantDto.getMaritalStatus()));
    applicant.setAdultNo(applicantDto.getAdultNo());
    applicant.setChildrenNo(applicantDto.getChildrenNo());
    applicant.setIncomeAfterTax(applicantDto.getIncomeAfterTax());
    return applicant;
  }

  private ApplicantDto getApplicantDto(String maritalStatus, Integer adultno, Integer childNo, BigDecimal incomeAfterTax) {
    ApplicantDto applicantDto = new ApplicantDto();
    applicantDto.setMaritalStatus(maritalStatus);
    applicantDto.setAdultNo(adultno);
    applicantDto.setChildrenNo(childNo);
    applicantDto.setIncomeAfterTax(incomeAfterTax);
    return applicantDto;
  }

  private CarDto getCarDto(String brand, String model, BigDecimal price, Integer loanLength) {
    CarDto carDto = new CarDto();
    carDto.setBrand(brand);
    carDto.setModel(model);
    carDto.setPrice(price);
    carDto.setLoanLength(loanLength);
    return carDto;
  }

  private Car getCar(CarDto carDto) {
    Car car = new Car();
    car.setBrand(carDto.getBrand());
    car.setModel(carDto.getModel());
    car.setPrice(carDto.getPrice());
    car.setLoanLength(carDto.getLoanLength());
    return car;
  }
}