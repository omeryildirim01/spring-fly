package com.yildirimomer.springfly.service;

import com.yildirimomer.springfly.domain.dto.BookingDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Omer YILDIRIM on 9.05.2020.
 * I-Luxus GmbH.
 * omer@i-luxus.de
 */
public interface BookingService {

    /**
     * to save an item into db
     * @param bookingDto
     * @return  BookingDto object
     */
    BookingDto save(BookingDto bookingDto);

    /**
     * to save  item collection
     * @param  bookingList
     * @return
     */
    List<BookingDto> saveAll(List<BookingDto> bookingList);

    /**
     * to delete an item using by item id
     * @param id
     */
    void delete(Long id);

    /**
     *  to Get all BookingDto records
     * @return BookingDto List
     */
    List<BookingDto> getAll();

    /**
     * get All items
     * @param pageable
     * @return Page
     */
    Page<BookingDto> getAll(Pageable pageable);

    /**
     * to book a flight by user
     * @param bookingDto
     * @return
     */
    BookingDto bookFlight(BookingDto bookingDto);

    /**
     * to get booking by id
     * @param id
     * @return
     */
    BookingDto getBooking(Long id);

    /**
     * to cancel booking
     * @param id
     */
    void cancelBooking(Long id);

    /**
     * to find by flight id
     * @param id
     * @return
     */
    List<BookingDto> findByFlightId(Long id);

    /**
     * to find by booking id
     * @param id
     * @return
     */
    BookingDto findById(Long id);
}
