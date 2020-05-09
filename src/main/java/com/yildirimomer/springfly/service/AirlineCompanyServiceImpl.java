package com.yildirimomer.springfly.service;

import com.yildirimomer.springfly.domain.dto.AirlineCompanyDto;
import com.yildirimomer.springfly.domain.model.AirlineCompany;
import com.yildirimomer.springfly.repository.AirlineCompanyRepository;
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
public class AirlineCompanyServiceImpl implements AirlineCompanyService {

    private  final AirlineCompanyRepository airlineCompanyRepository;

    /**
     * To save item
     * @param airlineCompanyDto
     * @return
     */
    @Override
    @Transactional
    public AirlineCompanyDto save(AirlineCompanyDto airlineCompanyDto) {
        AirlineCompany airlineCompany = ObjectMapper.map(airlineCompanyDto,AirlineCompany.class);
        airlineCompany= airlineCompanyRepository.save(airlineCompany);
        airlineCompanyDto.setId(airlineCompany.getId());
        return airlineCompanyDto;
    }

    /**
     * to save  item collection
     *
     * @param airlineCompanyDtoList
     * @return saved object list
     */
    @Override
    public List<AirlineCompanyDto> saveAll(List<AirlineCompanyDto> airlineCompanyDtoList) {
        Assert.notEmpty(airlineCompanyDtoList,"Object list must not be empty");
        if (airlineCompanyDtoList !=null){
            List<AirlineCompany> airlineCompanies = ObjectMapper.mapAll(airlineCompanyDtoList,AirlineCompany.class);
            airlineCompanies = airlineCompanyRepository.saveAll(airlineCompanies);
            List<AirlineCompanyDto> savedObject = ObjectMapper.mapAll(airlineCompanies,AirlineCompanyDto.class);
            return  savedObject;
        }
        return new ArrayList<AirlineCompanyDto>();
    }

    /**
     * to delete specific object by using identity
     * @param id
     */
    @Override
    public void delete(Long id) {
        airlineCompanyRepository.deleteById(id);
    }

    /**
     * to get all items
     * @return List of object
     */
    @Override
    public List<AirlineCompanyDto> getAll() {
        List<AirlineCompany> airlineCompanies = airlineCompanyRepository.findAll();
        List<AirlineCompanyDto> airlineCompanyDtos = new ArrayList<>();
        if (airlineCompanies!=null && airlineCompanies.size()>0){
            airlineCompanyDtos = ObjectMapper.mapAll(airlineCompanies,AirlineCompanyDto.class);
        }
        return airlineCompanyDtos;
    }

    /**
     * to get all items
     * @return page of object
     */
    @Override
    public Page<AirlineCompanyDto> getAll(Pageable pageable) {
        Page<AirlineCompany> entityPage = airlineCompanyRepository.findAll(pageable);
        Page<AirlineCompanyDto> dtoPage = entityPage.map(item-> ObjectMapper.map(item,AirlineCompanyDto.class));
        return dtoPage;
    }

    /**
     * to find Airport records
     *
     * @param name
     * @return Airport List
     */
    @Override
    public List<AirlineCompanyDto> findByName(Optional<String> name) {
        List<AirlineCompany> airlineCompanies = airlineCompanyRepository.findByName(name.orElse("_"));
        List<AirlineCompanyDto> airlineCompanyDtos = ObjectMapper.mapAll(airlineCompanies,AirlineCompanyDto.class);
        return airlineCompanyDtos;
    }

    /**
     * to find by id
     *
     * @param id
     * @return
     */
    @Override
    public AirlineCompanyDto findById(Long id) {
        AirlineCompany airlineCompany = airlineCompanyRepository.findById(id).orElse(null);
        AirlineCompanyDto airlineCompanyDto = null;
        if (airlineCompany !=null){
            airlineCompanyDto = ObjectMapper.map(airlineCompany,AirlineCompanyDto.class);
        }
        return airlineCompanyDto;
    }
}