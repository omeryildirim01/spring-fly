package com.yildirimomer.springfly.service;

import com.yildirimomer.springfly.domain.dto.AirportDto;
import com.yildirimomer.springfly.domain.dto.FlightDto;
import com.yildirimomer.springfly.domain.dto.TicketDto;
import com.yildirimomer.springfly.domain.model.Airport;
import com.yildirimomer.springfly.domain.model.Flight;
import com.yildirimomer.springfly.domain.model.Ticket;
import com.yildirimomer.springfly.repository.TicketRepository;
import com.yildirimomer.springfly.tools.ObjectMapper;
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
 * Created by Omer YILDIRIM on 9.05.2020.
 * I-Luxus GmbH.
 * omer@i-luxus.de
 */
@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService{

    private  final TicketRepository ticketRepository;

    /**
     * to save an item into db
     *
     * @param ticketDto
     * @return TicketDto object
     */
    @Override
    @Transactional
    public TicketDto save(TicketDto ticketDto) {
        Ticket ticket = ObjectMapper.map(ticketDto, Ticket.class);
        ticket= ticketRepository.save(ticket);
        ticketDto.setId(ticket.getId());
        return ticketDto;
    }

    /**
     * to save  item collection
     *
     * @param ticketDtoList
     * @return
     */
    @Override
    public List<TicketDto> saveAll(List<TicketDto> ticketDtoList) {
        Assert.notEmpty(ticketDtoList,"Object list must not be empty");
        if (ticketDtoList !=null){
            List<Ticket> ticketList = ObjectMapper.mapAll(ticketDtoList,Ticket.class);
            ticketList = ticketRepository.saveAll(ticketList);
            List<TicketDto> savedObject = ObjectMapper.mapAll(ticketList,TicketDto.class);
            return  savedObject;
        }
        return new ArrayList<TicketDto>();
    }

    /**
     * to delete an item using by item id
     *
     * @param id
     */
    @Override
    public void delete(Long id) {
        ticketRepository.deleteById(id);
    }

    /**
     * to Get all TicketDto records
     *
     * @return TicketDto List
     */
    @Override
    public List<TicketDto> getAll() {
        List<Ticket> ticketList = ticketRepository.findAll();
        List<TicketDto> ticketDtoList = new ArrayList<>();
        if (ticketList!=null && ticketList.size()>0){
            ticketDtoList = ObjectMapper.mapAll(ticketList,TicketDto.class);
        }
        return ticketDtoList;
    }

    /**
     * get All items
     *
     * @param pageable
     * @return Page
     */
    @Override
    public Page<TicketDto> getAll(Pageable pageable) {
        Page<Ticket> entityPage = ticketRepository.findAll(pageable);
        Page<TicketDto> dtoPage = entityPage.map(item-> ObjectMapper.map(item,TicketDto.class));
        return dtoPage;
    }

    /**
     * to find by id
     *
     * @param id
     * @return
     */
    @Override
    public TicketDto findById(Long id) {
        Ticket ticket = ticketRepository.findById(id).orElse(null);
        TicketDto ticketDto = null;
        if (ticket !=null){
            ticketDto = ObjectMapper.map(ticket,TicketDto.class);
        }
        return ticketDto;
    }

    /**
     * to find records
     *
     * @param ticketNumber
     * @return item List
     */
    @Override
    public List<TicketDto> findByTicketNumber(Optional<String> ticketNumber) {
        List<Ticket> ticketList = ticketRepository.findByTicketNumber(ticketNumber.orElse("_"));
        List<TicketDto> ticketDtoList = ObjectMapper.mapAll(ticketList,TicketDto.class);
        return ticketDtoList;
    }
}
