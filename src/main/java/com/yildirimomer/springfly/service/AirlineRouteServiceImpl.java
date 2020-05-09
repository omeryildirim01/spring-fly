package com.yildirimomer.springfly.service;

import com.yildirimomer.springfly.domain.dto.AirlineRouteDto;
import com.yildirimomer.springfly.domain.model.AirlineRoute;
import com.yildirimomer.springfly.repository.AirlineRouteRepository;
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
 * Created by Omer YILDIRIM on 8.05.2020.
 * I-Luxus GmbH.
 * omer@i-luxus.de
 */

@Service
@RequiredArgsConstructor
public class AirlineRouteServiceImpl implements AirlineRouteService {

    private  final AirlineRouteRepository airlineRouteRepository;

    /**
     * To save item
     * @param airlineRouteDto
     * @return
     */
    @Override
    @Transactional
    public AirlineRouteDto save(AirlineRouteDto airlineRouteDto) {
        AirlineRoute airlineRoute = ObjectMapper.map(airlineRouteDto, AirlineRoute.class);
        airlineRoute= airlineRouteRepository.save(airlineRoute);
        airlineRouteDto.setId(airlineRoute.getId());
        return airlineRouteDto;
    }

    /**
     * to save  item collection
     *
     * @param airlineRouteDtoList
     * @return saved object list
     */
    @Override
    public List<AirlineRouteDto> saveAll(List<AirlineRouteDto> airlineRouteDtoList) {
        Assert.notEmpty(airlineRouteDtoList,"Object list must not be empty");
        if (airlineRouteDtoList !=null){
            List<AirlineRoute> airlineRouteList = ObjectMapper.mapAll(airlineRouteDtoList,AirlineRoute.class);
            airlineRouteList = airlineRouteRepository.saveAll(airlineRouteList);
            List<AirlineRouteDto> savedObject = ObjectMapper.mapAll(airlineRouteList,AirlineRouteDto.class);
            return  savedObject;
        }
        return new ArrayList<AirlineRouteDto>();
    }

    /**
     * to delete specific object by using identity
     * @param id
     */
    @Override
    public void delete(Long id) {
        airlineRouteRepository.deleteById(id);
    }

    /**
     * to get all items
     * @return List of object
     */
    @Override
    public List<AirlineRouteDto> getAll() {
        List<AirlineRoute> airlineRouteList = airlineRouteRepository.findAll();
        List<AirlineRouteDto> airlineRouteDtos = new ArrayList<>();
        if (airlineRouteList!=null && airlineRouteList.size()>0){
            airlineRouteDtos = ObjectMapper.mapAll(airlineRouteList,AirlineRouteDto.class);
        }
        return airlineRouteDtos;
    }

    /**
     * to get all items
     * @return page of object
     */
    @Override
    public Page<AirlineRouteDto> getAll(Pageable pageable) {
        Page<AirlineRoute> entityPage = airlineRouteRepository.findAll(pageable);
        Page<AirlineRouteDto> dtoPage = entityPage.map(item-> ObjectMapper.map(item,AirlineRouteDto.class));
        return dtoPage;
    }

    /**
     * to find Airport records
     *
     * @param name
     * @return AirlineRouteDto List
     */
    @Override
    public List<AirlineRouteDto> findByName(Optional<String> name) {
        List<AirlineRoute> airlineRouteList = airlineRouteRepository.findByName(name.orElse("_"));
        List<AirlineRouteDto> airlineRouteDtos = ObjectMapper.mapAll(airlineRouteList,AirlineRouteDto.class);
        return airlineRouteDtos;
    }

    /**
     * to find Airport records by code
     *
     * @param code
     * @return AirlineRouteDto List
     */
    @Override
    public List<AirlineRouteDto> findByCode(Optional<String> code) {
        List<AirlineRoute> airlineRouteList = airlineRouteRepository.findByName(code.orElse("_"));
        List<AirlineRouteDto> airlineRouteDtos = ObjectMapper.mapAll(airlineRouteList,AirlineRouteDto.class);
        return airlineRouteDtos;
    }

    /**
     * to find by id
     *
     * @param id
     * @return
     */
    @Override
    public AirlineRouteDto findById(Long id) {
        AirlineRoute airlineRoute = airlineRouteRepository.findById(id).orElse(null);
        AirlineRouteDto airlineRouteDto = null;
        if (airlineRoute !=null){
            airlineRouteDto = ObjectMapper.map(airlineRoute,AirlineRouteDto.class);
        }
        return airlineRouteDto;
    }
}