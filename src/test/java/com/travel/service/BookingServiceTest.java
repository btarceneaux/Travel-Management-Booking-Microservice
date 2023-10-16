package com.travel.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.*;
import com.travel.bean.Customer;
import com.travel.bean.Booking;
import org.springframework.data.domain.Page;
import org.springframework.boot.test.context.SpringBootTest;

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
    @Disabled
    void createBooking()
    {
        Customer createdCustomer = new Customer();

        createdCustomer.setLastName("X");
        createdCustomer.setFirstName("Mr");
        createdCustomer.setEmailAddress("mrx@example.com");

        Date myDate = new Date();
        Booking myBooking = new Booking();
        myBooking.setRelatedCustomer(createdCustomer);
//        myBooking.setBookingDate(myDate);
//        myBooking.setBookingTime(new Time(myDate.getTime()));

        myBooking.setNumberOfPassengers(2);


        Booking booking = service.createBooking(myBooking);

        assertNotEquals(0, booking.getBookingId());
    }

    @Test
    @Order(2)
    @Disabled
    void getAllBookings()
    {
        Page<Booking> bookingPage = service.getAllBookings(1, 10, "pickUpAddress", "ASC");

        assertNotEquals(0, bookingPage.getTotalElements());
    }

    @Test
    @Order(3)
    @Disabled
    void updateBooking()
    {
        Optional<Booking> optionalBooking = service.getBookingById(1);

        if(optionalBooking.isPresent())
        {
            Booking myBooking = optionalBooking.get();


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
    @Disabled
    void deleteBooking()
    {
        Optional<Booking> optionalBooking = service.getBookingById(1);

        Booking myBooking = optionalBooking.get();
        int result = service.deleteBooking(myBooking.getBookingId());

        assertEquals(1, result);
    }
}