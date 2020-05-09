package com.yildirimomer.springfly.service;

import com.yildirimomer.springfly.domain.dto.AirportDto;
import com.yildirimomer.springfly.domain.model.Airport;
import com.yildirimomer.springfly.repository.AirportRepository;
import com.yildirimomer.springfly.tools.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.*;

/**
 * Created by Omer YILDIRIM on 8.05.2020.
 * I-Luxus GmbH.
 * omer@i-luxus.de
 */
@Service
@RequiredArgsConstructor
public class AirportServiceImpl implements AirportService {

    private  final AirportRepository airportRepository;

    @Override
    @Transactional
    public AirportDto save(AirportDto airportDto) {
        Airport airport = ObjectMapper.map(airportDto,Airport.class);
        airport= airportRepository.save(airport);
        airportDto.setId(airport.getId());
        return airportDto;
    }

    /**
     * to save  item collection
     *
     * @param airportDtoList
     * @return saved object list
     */
    @Override
    public List<AirportDto> saveAll(List<AirportDto> airportDtoList) {
          Assert.notEmpty(airportDtoList,"Object list must not be empty");
          if (airportDtoList !=null){
              List<Airport> airportList = ObjectMapper.mapAll(airportDtoList,Airport.class);
              airportList = airportRepository.saveAll(airportList);
              List<AirportDto> savedObject = ObjectMapper.mapAll(airportList,AirportDto.class);
              return  savedObject;
          }
          return new ArrayList<AirportDto>();
    }

    /**
     * to delete specific object by using identity
     * @param id
     */
    @Override
    public void delete(Long id) {
       airportRepository.deleteById(id);
    }

    /**
     * to get all items
     * @return List of object
     */
    @Override
    public List<AirportDto> getAll() {
        List<Airport> airportList = airportRepository.findAll();
        List<AirportDto> airportDtoList = new ArrayList<>();
        if (airportList!=null && airportList.size()>0){
            airportDtoList = ObjectMapper.mapAll(airportList,AirportDto.class);
        }
        return airportDtoList;
    }

    /**
     * to get all items
     * @return page of object
     */
    @Override
    public Page<AirportDto> getAll(Pageable pageable) {
        Page<Airport> entityPage = airportRepository.findAll(pageable);
        Page<AirportDto> dtoPage = entityPage.map(item-> ObjectMapper.map(item,AirportDto.class));
        return dtoPage;
    }

    /**
     * to find Airport records
     *
     * @param name
     * @return Airport List
     */
    @Override
    public List<AirportDto> findByName(Optional<String> name) {
        List<Airport> airportList = airportRepository.findByName(name.orElse("_"));
        List<AirportDto> airportDtoList = ObjectMapper.mapAll(airportList,AirportDto.class);
        return airportDtoList;
    }

    /**
     * to find by id
     *
     * @param id
     * @return
     */
    @Override
    public AirportDto findById(Long id) {
        Airport airport = airportRepository.findById(id).orElse(null);
        AirportDto airportDto = null;
        if (airport !=null){
            airportDto = ObjectMapper.map(airport,AirportDto.class);
        }
        return airportDto;
    }
}
