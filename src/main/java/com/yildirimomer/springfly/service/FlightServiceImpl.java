package com.yildirimomer.springfly.service;

import com.yildirimomer.springfly.domain.dto.FlightDto;
import com.yildirimomer.springfly.domain.model.Flight;
import com.yildirimomer.springfly.repository.BookingRepository;
import com.yildirimomer.springfly.repository.FlightRepository;
import com.yildirimomer.springfly.tools.ObjectMapper;
import com.yildirimomer.springfly.tools.PriceCalculateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Omer YILDIRIM on 8.05.2020.
 * I-Luxus GmbH.
 * omer@i-luxus.de
 */
@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final BookingRepository bookingRepository;

    /**
     * To save item
     * @param flightDto
     * @return
     */
    @Override
    @Transactional
    public FlightDto save(FlightDto flightDto) {
        Flight flight = ObjectMapper.map(flightDto, Flight.class);
        flight= flightRepository.save(flight);
        flightDto.setId(flight.getId());
        return flightDto;
    }

    /**
     * to save  item collection
     *
     * @param flightDtoList
     * @return saved object list
     */
    @Override
    public List<FlightDto> saveAll(List<FlightDto> flightDtoList) {
        Assert.notEmpty(flightDtoList,"Object list must not be empty");
        if (flightDtoList !=null){
            List<Flight> flightList = ObjectMapper.mapAll(flightDtoList,Flight.class);
            flightList = flightRepository.saveAll(flightList);
            List<FlightDto> savedObject = ObjectMapper.mapAll(flightList,FlightDto.class);
            return  savedObject;
        }
        return new ArrayList<FlightDto>();
    }

    /**
     * to delete specific object by using identity
     * @param id
     */
    @Override
    public void delete(Long id) {
        flightRepository.deleteById(id);
    }

    /**
     * to get all items
     * @return List of object
     */
    @Override
    public List<FlightDto> getAll() {
        List<Flight> flightList = flightRepository.findAll();
        List<FlightDto> flightDtoList = new ArrayList<>();
        if (flightList!=null && flightList.size()>0){
            flightDtoList = ObjectMapper.mapAll(flightList,FlightDto.class);
        }
        return flightDtoList;
    }

    /**
     * to get all items
     * @return page of object
     */
    @Override
    public Page<FlightDto> getAll(Pageable pageable) {
        Page<Flight> entityPage = flightRepository.findAll(pageable);
        Page<FlightDto> dtoPage = entityPage.map(item-> ObjectMapper.map(item,FlightDto.class));
        return dtoPage;
    }

    /**
     * to find Flight records
     *
     * @param name
     * @return FlightDto List
     */
    @Override
    public List<FlightDto> findByName(Optional<String> name) {
        List<Flight> flightList = flightRepository.findByName(name.orElse("_"));
        List<FlightDto> flightDtoList = ObjectMapper.mapAll(flightList,FlightDto.class);
        return flightDtoList;
    }

    /**
     * to find Flight records by code
     *
     * @param code
     * @return FlightDto List
     */
    @Override
    public List<FlightDto> findByCode(Optional<String> code) {
        List<Flight> flightList = flightRepository.findByName(code.orElse("_"));
        List<FlightDto> flightDtoList = ObjectMapper.mapAll(flightList,FlightDto.class);
        return flightDtoList;
    }
    /**
     * to find  item by id
     *
     * @param flightId
     * @return find object
     */
    @Override
    public FlightDto findById(Long flightId) {
        Flight flight = flightRepository.findById(flightId).orElse(null);
        FlightDto flightDto = null;
        if (flight!=null){
            flightDto = ObjectMapper.map(flight,FlightDto.class);
        }
        return flightDto;
    }

    /**
     * to get updated ticket price , check booked count
     *
     * @param flightId
     * @return
     */
    @Override
    public Double getTicketPriceByFlightId(Long flightId) {
        Double retVal = 0.0;
        Flight flight = flightRepository.findById(flightId).orElse(null);
        if (flight !=null){
           int bookingCount = bookingRepository.findByFlightId(flight.getId()).size();
            retVal = PriceCalculateUtil.calculatePrice(bookingCount,flight.getQuota(),flight.getPrice());
        }
        return retVal;
    }

}