package com.yildirimomer.springfly.service;

import com.yildirimomer.springfly.domain.dto.AirlineRouteDto;
import com.yildirimomer.springfly.domain.dto.FlightDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
/**
 * Created by Omer YILDIRIM on 8.05.2020.
 * I-Luxus GmbH.
 * omer@i-luxus.de
 */
public interface FlightService {
    /**
     * to save an item into db
     * @param flightDto
     * @return  FlightDto object
     */
    FlightDto save(FlightDto flightDto);

    /**
     * to save  item collection
     * @param  flightDtoList
     * @return
     */
    List<FlightDto> saveAll(List<FlightDto> flightDtoList);

    /**
     * to delete an item using by item id
     * @param id
     */
    void delete(Long id);

    /**
     *  to Get all FlightDto records
     * @return FlightDto List
     */
    List<FlightDto> getAll() ;

    /**
     * get All items
     * @param pageable
     * @return Page
     */
    Page<FlightDto> getAll(Pageable pageable);

    /**
     *  to find records
     * @return item List
     */
    List<FlightDto> findByName(Optional<String> name);

    /**
     *  to find records
     * @return item List
     */
    List<FlightDto> findByCode(Optional<String> code);

    /**
     * to save an item into db
     * @param flightId
     * @return  FlightDto object
     */
    FlightDto findById(Long flightId);

    /**
     * to get updated ticket price , check booked count
     * @param flightId
     * @return
     */
    Double getTicketPriceByFlightId(Long flightId);

}