package com.yildirimomer.springfly.service;

import com.yildirimomer.springfly.domain.dto.AirlineRouteDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
/**
 * Created by Omer YILDIRIM on 8.05.2020.
 * I-Luxus GmbH.
 * omer@i-luxus.de
 */
public interface AirlineRouteService {
    /**
     * to save an item into db
     * @param airlineRouteDto
     * @return  AirlineRouteDto object
     */
    AirlineRouteDto save(AirlineRouteDto airlineRouteDto);

    /**
     * to save  item collection
     * @param  airlineRouteDtoList
     * @return
     */
    List<AirlineRouteDto> saveAll(List<AirlineRouteDto> airlineRouteDtoList);

    /**
     * to delete an item using by item id
     * @param id
     */
    void delete(Long id);

    /**
     *  to Get all AirlineRoute records
     * @return AirlineRoute List
     */
    List<AirlineRouteDto> getAll() ;

    /**
     * get All items
     * @param pageable
     * @return Page
     */
    Page<AirlineRouteDto> getAll(Pageable pageable);

    /**
     *  to find records
     * @return item List
     */
    List<AirlineRouteDto> findByName(Optional<String> name);

    /**
     *  to find records
     * @return item List
     */
    List<AirlineRouteDto> findByCode(Optional<String> code);

    /**
     * to find by id
     * @param id
     * @return
     */
    AirlineRouteDto findById(Long id);

}