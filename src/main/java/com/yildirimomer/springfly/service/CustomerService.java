package com.yildirimomer.springfly.service;

import com.yildirimomer.springfly.domain.dto.CustomerDto;
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
public interface CustomerService {
    /**
     * to save an item into db
     * @param customerDto
     * @return  CustomerDto object
     */
    CustomerDto save(CustomerDto customerDto);

    /**
     * to save  item collection
     * @param  customerDtoList
     * @return CustomerDto List
     */
    List<CustomerDto> saveAll(List<CustomerDto> customerDtoList);

    /**
     * to delete an item using by item id
     * @param id
     */
    void delete(Long id);

    /**
     *  to Get all FlightDto records
     * @return FlightDto List
     */
    List<CustomerDto> getAll() ;

    /**
     * get All items
     * @param pageable
     * @return Page
     */
    Page<CustomerDto> getAll(Pageable pageable);

    /**
     *  to find records
     * @return item List
     */
    List<CustomerDto> findByName(Optional<String> name);
    /**
     *  to find record by id
     * @return item
     */
    CustomerDto findById(Long id);
}