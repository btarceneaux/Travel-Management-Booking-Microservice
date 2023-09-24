package com.travel.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.*;
import com.travel.bean.Customer;
import com.travel.bean.Booking;
import org.springframework.data.domain.Page;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Order;
import java.sql.Time;
import java.util.Date;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BookingServiceTest
{
    @Autowired
    BookingService service;

    @Test
    @Order(1)
    void createBooking()
    {
        Customer createdCustomer = new Customer();

        createdCustomer.setLastName("X");
        createdCustomer.setFirstName("Mr");
        createdCustomer.setEmailAddress("mrx@example.com");

        Date myDate = new Date();
        Booking myBooking = new Booking();
        myBooking.setRelatedCustomer(createdCustomer);
        myBooking.setBookingDate(myDate);
        myBooking.setBookingTime(new Time(myDate.getTime()));
        myBooking.setPickUpAddress("123 1st Street, New Oreleans, LA.");
        myBooking.setNumberOfPassengers(2);
        myBooking.setDestinationAddress("456 4th Street, New Orlelans, LA.");

        Booking booking = service.createBooking(myBooking);

        assertNotEquals(0, booking.getBookingId());
    }

    @Test
    @Order(2)
    void getAllBookings()
    {
        Page<Booking> bookingPage = service.getAllBookings(1, 10, "pickUpAddress", "ASC");

        assertNotEquals(0, bookingPage.getTotalElements());
    }

    @Test
    @Order(3)
    void updateBooking()
    {
        Optional<Booking> optionalBooking = service.getBookingById(1);

        if(optionalBooking.isPresent())
        {
            Booking myBooking = optionalBooking.get();
            myBooking.setDestinationAddress("2500 West 51st Avenue, New Oreleans, LA.");

            Booking updatedBooking = service.updateBooking(myBooking);

            assertEquals("2500 West 51st Avenue, New Oreleans, LA.", updatedBooking.getDestinationAddress());
        }
        else
        {
            assert(false);
        }

    }

    @Test
    @Order(4)
    void deleteBooking()
    {
        Optional<Booking> optionalBooking = service.getBookingById(1);

        Booking myBooking = optionalBooking.get();
        int result = service.deleteBooking(myBooking.getBookingId());

        assertEquals(1, result);
    }
}