package com.yildirimomer.springfly.service;

import com.yildirimomer.springfly.domain.dto.CustomerDto;
import com.yildirimomer.springfly.domain.model.Customer;
import com.yildirimomer.springfly.repository.CustomerRepository;
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
public class CustomerServiceImpl implements CustomerService {

    private  final CustomerRepository customerRepository;

    /**
     * To save item
     * @param customerDto
     * @return CustomerDto
     */
    @Override
    @Transactional
    public CustomerDto save(CustomerDto customerDto) {
        Customer customer = ObjectMapper.map(customerDto, Customer.class);
        customer= customerRepository.save(customer);
        customerDto.setId(customer.getId());
        return customerDto;
    }

    /**
     * to save  item collection
     *
     * @param customerDtoList
     * @return saved object list
     */
    @Override
    public List<CustomerDto> saveAll(List<CustomerDto> customerDtoList) {
        Assert.notEmpty(customerDtoList,"Object list must not be empty");
        if (customerDtoList !=null){
            List<Customer> customerList = ObjectMapper.mapAll(customerDtoList,Customer.class);
            customerList = customerRepository.saveAll(customerList);
            List<CustomerDto> savedObject = ObjectMapper.mapAll(customerList,CustomerDto.class);
            return  savedObject;
        }
        return new ArrayList<CustomerDto>();
    }

    /**
     * to delete specific object by using identity
     * @param id
     */
    @Override
    public void delete(Long id) {
        customerRepository.deleteById(id);
    }

    /**
     * to get all items
     * @return List of object
     */
    @Override
    public List<CustomerDto> getAll() {
        List<Customer> customerList = customerRepository.findAll();
        List<CustomerDto> flightDtoList = new ArrayList<>();
        if (customerList!=null && customerList.size()>0){
            flightDtoList = ObjectMapper.mapAll(customerList,CustomerDto.class);
        }
        return flightDtoList;
    }

    /**
     * to get all items
     * @return page of object
     */
    @Override
    public Page<CustomerDto> getAll(Pageable pageable) {
        Page<Customer> entityPage = customerRepository.findAll(pageable);
        Page<CustomerDto> dtoPage = entityPage.map(item-> ObjectMapper.map(item,CustomerDto.class));
        return dtoPage;
    }

    /**
     * to find Customer records, search by name
     *
     * @param name
     * @return CustomerDto List
     */
    @Override
    public List<CustomerDto> findByName(Optional<String> name) {
        List<Customer> customerList = customerRepository.findByName(name.orElse("_"));
        List<CustomerDto> customerDtoList = ObjectMapper.mapAll(customerList,CustomerDto.class);
        return customerDtoList;
    }

    /**
     *  to find record by id
     * @return item
     */
    @Override
    public CustomerDto findById(Long id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        CustomerDto customerDto = null;
        if (customer !=null){
            customerDto = ObjectMapper.map(customer,CustomerDto.class);
        }
        return customerDto;
    }
}