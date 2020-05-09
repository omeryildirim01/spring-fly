package com.yildirimomer.springfly;

import com.sun.xml.internal.ws.api.BindingIDFactory;
import com.yildirimomer.springfly.domain.dto.*;
import com.yildirimomer.springfly.domain.model.AirlineCompany;
import com.yildirimomer.springfly.domain.model.AirlineRoute;
import com.yildirimomer.springfly.domain.model.CreditCardType;
import com.yildirimomer.springfly.domain.model.Flight;
import com.yildirimomer.springfly.service.*;
import com.yildirimomer.springfly.tools.CardUtil;
import com.yildirimomer.springfly.tools.PriceCalculateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.smartcardio.Card;
import java.io.Console;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by Omer YILDIRIM on 8.05.2020.
 * I-Luxus GmbH.
 * omer@i-luxus.de
 */
@Component
public class DbInitializer implements CommandLineRunner {
    private AirportServiceImpl airportService;
    private AirlineCompanyServiceImpl airlineCompanyService;
    private AirlineRouteServiceImpl airlineRouteService;
    private FlightServiceImpl flightService;
    private CustomerServiceImpl customerService;
    private BookingServiceImpl bookingService;
    private TicketServiceImpl ticketService;
    /**
     *  Demo data first init test
     */
    @Autowired
    public DbInitializer(AirportServiceImpl airportService, AirlineCompanyServiceImpl airlineCompanyService
            ,AirlineRouteServiceImpl airlineRouteService, FlightServiceImpl flightService,
                         CustomerServiceImpl customerService,BookingServiceImpl bookingService,TicketServiceImpl ticketService) {
        this.airportService = airportService;
        this.airlineCompanyService = airlineCompanyService;
        this.airlineRouteService = airlineRouteService;
        this.flightService = flightService;
        this.customerService = customerService;
        this.bookingService = bookingService;
        this.ticketService = ticketService;
    }

    @Override
    public void run(String... args) throws Exception {
        prepareDummyDataForAirport();
        prepareDummyDataForAirlineCompanies();
        prepareDummyDataForAirlineRoute();
        prepareDummyDataForFlight();
        prepareDummyDataForCustomer();
        prepareDummyDataForBooking();
        prepareDummyDataForTicket();
    }

    public void checkCreditCardNumber(){
        String testNumber1 = "4221-1611-2233-0005";
        String testNumber2 = "4221,1611,2233,0005";

        String testNumber1Result = CardUtil.maskCardNumber(CardUtil.mask,testNumber1);
        String testNumber2Result = CardUtil.maskCardNumber(CardUtil.mask,testNumber2);

    }

    /**
     * Prepare Demo data first init test
     */
    public void prepareDummyDataForAirport(){
        try {
            List<AirportDto> airports = new ArrayList<AirportDto>();
            AirportDto airportDto1 = new AirportDto();
            airportDto1.setCity("Istanbul");
            airportDto1.setCode("IST");
            airportDto1.setName("Istanbul");
            airportDto1.setCountry("tr");
            airportDto1.setId(0L);

            AirportDto airportDto2 = new AirportDto();
            airportDto2.setCity("Istanbul");
            airportDto2.setCode("SAW");
            airportDto2.setName("Sabiha Gokcen");
            airportDto2.setCountry("tr");
            airportDto2.setId(0L);

            AirportDto airportDto3 = new AirportDto();
            airportDto3.setCity("Adana");
            airportDto3.setCode("ADN");
            airportDto3.setName("Adana");
            airportDto3.setCountry("tr");
            airportDto3.setId(0L);

            airports.add(airportDto1);
            airports.add(airportDto2);
            airports.add(airportDto3);
            airportService.saveAll(airports);
        }catch (Throwable e){
            e.printStackTrace();
        }
    }
    /**
     * Prepare Demo data first init test
     */
    public void prepareDummyDataForAirlineCompanies(){
        try {
            List<AirlineCompanyDto> airlineCompanyDtos = new ArrayList<AirlineCompanyDto>();
            AirlineCompanyDto dto1 = new AirlineCompanyDto();
            dto1.setId(0L);
            dto1.setAddress("Istanbul");
            dto1.setMail("mail@pegasus.com");
            dto1.setName("Pegasus");
            dto1.setTelephone("+9002169999999");

            AirlineCompanyDto dto2 = new AirlineCompanyDto();
            dto2.setId(0L);
            dto2.setAddress("Istanbul");
            dto2.setMail("mail@turkishairlines.com");
            dto2.setName("Turkish Airlines");
            dto2.setTelephone("+9002169999999");

            airlineCompanyDtos.add(dto1);
            airlineCompanyDtos.add(dto2);
            airlineCompanyService.saveAll(airlineCompanyDtos);
        }catch (Throwable e){
            e.printStackTrace();
        }
    }
    /**
     * Prepare Demo data first init test
     */
    public void prepareDummyDataForAirlineRoute(){
       try {
           List<AirportDto> airportDtoList = airportService.getAll();
           if (airportDtoList !=null && airportDtoList.size()>=2){
               AirlineRouteDto airlineRoute = new AirlineRouteDto();
               airlineRoute.setId(0L);
               airlineRoute.setCode("demo");
               airlineRoute.setName("Flight1");
               airlineRoute.setFlightDistance(2500);
               airlineRoute.setFlightTime(60000);
               airlineRoute.setArrivalAirport(airportDtoList.get(0));
               airlineRoute.setDepartureAirport(airportDtoList.get(1));
               airlineRouteService.save(airlineRoute);

               AirlineRouteDto airlineRoute2 = new AirlineRouteDto();
               airlineRoute2.setId(0L);
               airlineRoute2.setCode("demo2");
               airlineRoute2.setName("Flight2");
               airlineRoute2.setFlightDistance(2500);
               airlineRoute2.setFlightTime(60000);
               airlineRoute2.setArrivalAirport(airportDtoList.get(1));
               airlineRoute2.setDepartureAirport(airportDtoList.get(0));
               airlineRouteService.save(airlineRoute2);
           }
       }catch (Throwable e){
           e.printStackTrace();
       }
    }

    /**
     * Prepare Demo data first init test
     */
    public void prepareDummyDataForFlight() throws ParseException {
      try {

          List<AirlineRouteDto> airlineRouteList = airlineRouteService.getAll();
          List<AirlineCompanyDto> airlineCompanyList = airlineCompanyService.getAll();

          if (airlineRouteList !=null && airlineRouteList.size()>=1 && airlineCompanyList!=null && airlineCompanyList.size()>=1){
              FlightDto flightDto = new FlightDto();
              flightDto.setCode("flight123");
              flightDto.setAirlineCompany(airlineCompanyList.get(0));
              flightDto.setAirlineRoute(airlineRouteList.get(0));
              flightDto.setId(0L);
              flightDto.setName("Flight123");
              flightDto.setPrice(50);
              flightDto.setQuota(100);
              SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss a", Locale.ENGLISH);
              formatter.setTimeZone(TimeZone.getTimeZone("Turkey/Istanbul"));

              String dateInString1 = "01-01-2021 10:15:55 AM";
              String dateInString2 = "01-01-2021 12:15:55 AM";
              Date date1 = formatter.parse(dateInString1);
              Date date2 = formatter.parse(dateInString2);
              flightDto.setArrivalDate(date2);
              flightDto.setDepartureDate(date1);

              flightService.save(flightDto);
          }

      }catch (Throwable e){
          e.printStackTrace();
      }
    }
    /**
     * Prepare Demo data first init test
     */
    public void prepareDummyDataForCustomer(){
        try {
            List<CustomerDto> customerDtoList = new ArrayList<CustomerDto>();
            CustomerDto customerDto1 = new CustomerDto();
            customerDto1.setAddress("Istanbul");
            customerDto1.setMail("mail@mail.com");
            customerDto1.setName("Demo User1");
            customerDto1.setTelephone("555555555555");
            customerDto1.setId(0L);

            CustomerDto customerDto2 = new CustomerDto();
            customerDto2.setAddress("Istanbul");
            customerDto2.setMail("mail@mail.com");
            customerDto2.setName("Demo User2");
            customerDto2.setTelephone("555555555555");
            customerDto2.setId(0L);

            CustomerDto customerDto3 = new CustomerDto();
            customerDto3.setAddress("Istanbul");
            customerDto3.setMail("mail@mail.com");
            customerDto3.setName("Demo User3");
            customerDto3.setTelephone("555555555555");
            customerDto3.setId(0L);

            customerDtoList.add(customerDto1);
            customerDtoList.add(customerDto2);
            customerDtoList.add(customerDto3);
            customerService.saveAll(customerDtoList);
        }catch (Throwable e){
            e.printStackTrace();
        }
    }
    /**
     * Prepare Demo data first init test
     */
    public void prepareDummyDataForBooking(){
        int cardExpiryMonth = 2;
        int cardExpiryYear = 2021;
        String creditCardName= "bank";
        String creditCardNumber ="1234567812341234";
        CreditCardType cardType =  CreditCardType.VISA;
        List<CustomerDto> customerDtoList =customerService.getAll();
        CustomerDto customerDto = customerDtoList.get(0);
        List<FlightDto> flightDtoList = flightService.getAll();
        FlightDto flightDto = flightDtoList.get(0);

        if (customerDto !=null && flightDto !=null){
            BookingDto bookingDto = new BookingDto();
            bookingDto.setCreditCardExpiryMonth(cardExpiryMonth);
            bookingDto.setCreditCardExpiryYear(cardExpiryYear);
            bookingDto.setCreditCardName(creditCardName);
            bookingDto.setCreditCardNumber(creditCardNumber);
            bookingDto.setCustomer(customerDto);
            bookingDto.setFlight(flightDto);
            bookingDto.setBookingDate(new Date());
            bookingDto.setCreditCardType(cardType);
            bookingDto.setId(0L);

            bookingDto = bookingService.save(bookingDto);
            if (bookingDto.getId()>0){

            }
        }

    }
    /**
     * Prepare Demo data first init test
     */
    public void prepareDummyDataForTicket(){
       try {
           BookingDto bookingDto = bookingService.getAll().get(0);
           if (bookingDto !=null){
               TicketDto ticketDto = new TicketDto();
               ticketDto.setId(0L);
               ticketDto.setBooking(bookingDto);
               ticketDto.setGate("D22");
               ticketDto.setTicketNumber(java.util.UUID.randomUUID().toString());
               ticketDto.setCheckinDate(new Date());
               ticketDto= ticketService.save(ticketDto);
           }
       }catch (Throwable e){
           e.printStackTrace();
       }
    }

    public void testPriceCalculate(){
        Double basePrice = 100.0;
        int quota = 100;
        int bookedCount = 20;
        Double updatedPrice1 = 0.0;
        updatedPrice1 =  PriceCalculateUtil.calculatePrice(bookedCount,quota,basePrice);
    }
}
