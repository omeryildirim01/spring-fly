package com.yildirimomer.springfly.service;

import com.yildirimomer.springfly.domain.dto.AirlineCompanyDto;
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
public interface AirlineCompanyService {
    /**
     * to save an item into db
     * @param airlineCompanyDto
     * @return  AirlineCompany object
     */
    AirlineCompanyDto save(AirlineCompanyDto airlineCompanyDto);

    /**
     * to save  item collection
     * @param  airlineCompanyDtoList
     * @return
     */
    List<AirlineCompanyDto> saveAll(List<AirlineCompanyDto> airlineCompanyDtoList);

    /**
     * to delete an item using by item id
     * @param id
     */
    void delete(Long id);

    /**
     *  to Get all Airport records
     * @return Airport List
     */
    List<AirlineCompanyDto> getAll() ;

    /**
     * get All items
     * @param pageable
     * @return Page
     */
    Page<AirlineCompanyDto> getAll(Pageable pageable);

    /**
     *  to find records
     * @return item List
     */
    List<AirlineCompanyDto> findByName(Optional<String> name);

    /**
     * to find by id
     * @param id
     * @return
     */
    AirlineCompanyDto findById(Long id);

}