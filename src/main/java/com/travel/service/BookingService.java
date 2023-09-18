package com.travel.service;

import com.travel.bean.Booking;
import com.travel.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService
{
    @Autowired
    BookingRepository repository;

    public List<Booking> getAllBookings()
    {
        List<Booking> bookingList = repository.findAll();

        return bookingList;
    }

    public Booking getBookingById(int bookingId)
    {
        boolean exists = repository.existsById(bookingId);

        if (exists)
        {
            Booking myBooking = repository.getReferenceById(bookingId);
            return myBooking;
        }
        else
        {
            return null;
        }
    }

    public int createBooking(Booking myBooking)
    {
        int result = 0;

        Booking savedBooking = repository.save(myBooking);
        if(savedBooking.getBookingId() > 0)
        {
            result = 1;
        }

        return result;
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

    public int updateBooking(Booking updatedBooking)
    {
        int result = 0;
        if(repository.existsById(updatedBooking.getBookingId()))
        {
            repository.save(updatedBooking);
            result = 1;
        }

        return result;
    }
}
