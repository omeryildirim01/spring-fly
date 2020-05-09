package com.yildirimomer.springfly.service;

import com.yildirimomer.springfly.domain.dto.TicketDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Created by Omer YILDIRIM on 9.05.2020.
 * I-Luxus GmbH.
 * omer@i-luxus.de
 */
public interface TicketService {
    /**
     * to save an item into db
     * @param ticketDto
     * @return  TicketDto object
     */
    TicketDto save(TicketDto ticketDto);

    /**
     * to save  item collection
     * @param  ticketDtoList
     * @return
     */
    List<TicketDto> saveAll(List<TicketDto> ticketDtoList);

    /**
     * to delete an item using by item id
     * @param id
     */
    void delete(Long id);

    /**
     *  to Get all TicketDto records
     * @return TicketDto List
     */
    List<TicketDto> getAll() ;

    /**
     * get All items
     * @param pageable
     * @return Page
     */
    Page<TicketDto> getAll(Pageable pageable);

    /**
     *  to find records
     * @return item List
     */
    List<TicketDto> findByTicketNumber(Optional<String> name);

    /**
     * to find by id
     * @param id
     * @return
     */
    TicketDto findById(Long id);

}