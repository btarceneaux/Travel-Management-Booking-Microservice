package com.travel.service;

import com.travel.bean.Booking;
import com.travel.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingService
{
    @Autowired
    BookingRepository repository;

    public Page<Booking> getAllBookings(int page, int size, String sort, String sortOrder)
    {
        Sort.Direction direction = sortOrder.equalsIgnoreCase("ASC") ?
                Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sortBy = Sort.by(direction, sort);
        Pageable pageable = PageRequest.of(page, size, sortBy);

        return repository.findAll(pageable);
    }

    public Optional<Booking> getBookingById(int bookingId)
    {
        boolean exists = repository.existsById(bookingId);

        return repository.findById(bookingId);
    }

    public Booking createBooking(Booking myBooking)
    {
        return repository.save(myBooking);
    }

    public int deleteBooking(int bookingId)
    {
        //First determine whether record is in the database.
        int result = 0;
        boolean exists = repository.existsById(bookingId);

        if(exists)
        {
            repository.deleteById(bookingId);
            result = 1;
        }

        return result;
    }

    public Booking updateBooking(Booking updatedBooking)
    {

        if(repository.existsById(updatedBooking.getBookingId()))
        {
            return repository.save(updatedBooking);
        }
        else
        {
            return null;
        }
    }
}
