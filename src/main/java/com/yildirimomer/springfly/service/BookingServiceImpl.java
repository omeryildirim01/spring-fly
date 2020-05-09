package com.yildirimomer.springfly.service;

import com.yildirimomer.springfly.domain.dto.BookingDto;
import com.yildirimomer.springfly.domain.model.Booking;
import com.yildirimomer.springfly.domain.model.Customer;
import com.yildirimomer.springfly.repository.BookingRepository;
import com.yildirimomer.springfly.repository.CustomerRepository;
import com.yildirimomer.springfly.repository.FlightRepository;
import com.yildirimomer.springfly.tools.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Omer YILDIRIM on 9.05.2020.
 * I-Luxus GmbH.
 * omer@i-luxus.de
 */
@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService{

    private  final BookingRepository bookingRepository;

    /**
     * To save item
     * @param bookingDto
     * @return
     */
    @Override
    @Transactional
    public BookingDto save(BookingDto bookingDto) {
        Booking booking = ObjectMapper.map(bookingDto, Booking.class);
        booking= bookingRepository.save(booking);
        bookingDto.setId(booking.getId());
        return bookingDto;
    }

    /**
     * to save  item collection
     *
     * @param bookingDtoList
     * @return saved object list
     */
    @Override
    public List<BookingDto> saveAll(List<BookingDto> bookingDtoList) {
        Assert.notEmpty(bookingDtoList,"Object list must not be empty");
        if (bookingDtoList !=null){
            List<Booking> bookingList = ObjectMapper.mapAll(bookingDtoList,Booking.class);
            bookingList = bookingRepository.saveAll(bookingList);
            List<BookingDto> savedObject = ObjectMapper.mapAll(bookingList,BookingDto.class);
            return  savedObject;
        }
        return new ArrayList<BookingDto>();
    }


    /**
     * to delete an item using by item id
     *
     * @param id
     */
    @Override
    public void delete(Long id) {
        bookingRepository.deleteById(id);
    }

    /**
     * to Get all BookingDto records
     *
     * @return BookingDto List
     */
    @Override
    public List<BookingDto> getAll() {
        List<Booking> bookingList = bookingRepository.findAll();
        List<BookingDto> bookingDtoList = new ArrayList<>();
        if (bookingList!=null && bookingList.size()>0){
            bookingDtoList = ObjectMapper.mapAll(bookingList,BookingDto.class);
        }
        return bookingDtoList;
    }
    /**
     * to get all items
     * @return page of object
     */
    @Override
    public Page<BookingDto> getAll(Pageable pageable) {
        Page<Booking> entityPage = bookingRepository.findAll(pageable);
        Page<BookingDto> dtoPage = entityPage.map(item-> ObjectMapper.map(item,BookingDto.class));
        return dtoPage;
    }


    /**
     *
     * @param bookingDto
     * @return
     */
    @Override
    public BookingDto bookFlight(BookingDto bookingDto) {
        Booking booking = ObjectMapper.map(bookingDto, Booking.class);
        booking= bookingRepository.save(booking);
        bookingDto.setId(booking.getId());
        return bookingDto;
    }

    /**
     * to get booking by id
     *
     * @param id
     * @return
     */
    @Override
    public BookingDto getBooking(Long id) {
        Booking booking = bookingRepository.getOne(id);
        BookingDto bookingDto = ObjectMapper.map(booking,BookingDto.class);
        return bookingDto;
    }


    /**
     * to cancel booking
     *
     * @param id
     */
    @Override
    public void cancelBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public List<BookingDto> findByFlightId(Long id) {
        List<Booking> bookingList = bookingRepository.findByFlightId(id);
        List<BookingDto> bookingDtoList = new ArrayList<>();
        if (bookingList!=null && bookingList.size()>0){
            bookingDtoList = ObjectMapper.mapAll(bookingList,BookingDto.class);
        }
        return bookingDtoList;
    }


    /**
     * to find by id
     * @param id
     * @return
     */
    @Override
    public BookingDto findById(Long id) {
        Booking booking = bookingRepository.getOne(id);
        BookingDto bookingDto = ObjectMapper.map(booking,BookingDto.class);
        return bookingDto;
    }
}
