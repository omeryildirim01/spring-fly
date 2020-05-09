package com.yildirimomer.springfly.service;

import com.yildirimomer.springfly.domain.dto.AirportDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
/**
 * Created by Omer YILDIRIM on 8.05.2020.
 * I-Luxus GmbH.
 * omer@i-luxus.de
 */
public interface AirportService {
    /**
     * to save an item into db
     * @param airportDto
     * @return  Airport object
     */
    AirportDto save(AirportDto airportDto);

    /**
     * to save  item collection
     * @param  airportDtoList
     * @return
     */
    List<AirportDto> saveAll(List<AirportDto> airportDtoList);

    /**
     * to delete an item using by item id
     * @param id
     */
    void delete(Long id);

    /**
     *  to Get all Airport records
     * @return Airport List
     */
    List<AirportDto> getAll() ;

    /**
     * get All items
     * @param pageable
     * @return Page
     */
    Page<AirportDto> getAll(Pageable pageable);

    /**
     *  to find Airport records
     * @return Airport List
     */
    List<AirportDto> findByName(Optional<String> name);

    /**
     * to find by id
     * @param id
     * @return
     */
    AirportDto findById(Long id);

}
