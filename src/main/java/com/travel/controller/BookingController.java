package com.travel.controller;

import com.travel.bean.Booking;
import com.travel.bean.Customer;
import com.travel.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.travel.service.BookingService;
import java.util.Date;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BookingController
{
    @Autowired
    BookingService service;

    /**
     * Get all bookings by page
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/bookings")
    public ResponseDto getAllBookings(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                   @RequestParam(defaultValue = "pickUpAddress") String sort,
                                   @RequestParam(defaultValue = "ASC") String sortOrder)
    {
        ResponseDto response = new ResponseDto();
        Page<Booking> bookingPage = service.getAllBookings(page, size, sort, sortOrder);

        response.setMessage("All bookings were returned");
        response.setStatus(HttpStatus.OK.name());
        response.setTimestamp(new Date());
        response.setData(bookingPage);

        return response;
    }

    /**
     * Get booking by booking id
     */
    @GetMapping("/bookings/{bookingId}")
    public ResponseDto getBookingByBookingId(@PathVariable int bookingId)
    {
        ResponseDto response = new ResponseDto();
        Optional<Booking> optionalBooking = service.getBookingById(bookingId);

        if(optionalBooking.isPresent())
        {
            Booking myBooking = optionalBooking.get();
            response.setMessage("The booking was found");
            response.setStatus(HttpStatus.OK.name());
            response.setTimestamp(new Date());
            response.setData(myBooking);
        }
        else
        {
            response.setMessage("The booking does not exist.");
            response.setStatus(HttpStatus.EXPECTATION_FAILED.name());
            response.setTimestamp(new Date());
            response.setData(null);
        }

        return response;
    }

    /**
     * Create a booking
     * @param myBooking
     * @param customer
     * @return
     */
    @PostMapping("/createBooking")
    public ResponseDto createBooking(@RequestBody Booking myBooking, @RequestBody Customer customer)
    {
        ResponseDto response = new ResponseDto();

        //Add the customer to the booking
        myBooking.setRelatedCustomer(customer);

        Booking savedBooking = service.createBooking(myBooking);

        if(myBooking.getBookingId() > 0)
        {
            response.setMessage("The booking for " + customer.getEmailAddress() + "was successful.");
            response.setStatus(HttpStatus.OK.name());
            response.setTimestamp(new Date());
            response.setData(savedBooking);
        }
        else
        {
            response.setMessage("The booking for " + customer.getEmailAddress() + "was not successful.");
            response.setStatus(HttpStatus.EXPECTATION_FAILED.name());
            response.setTimestamp(new Date());
            response.setData(null);
        }

        return response;
    }

    /**
     * Update a booking
     * @param myBooking
     * @return
     */
    @PutMapping("/updateBooking")
    public ResponseDto updateBooking(@RequestBody Booking myBooking) //A saved booking already has a customer
    {
        ResponseDto response = new ResponseDto();

        Booking updatedBooking = service.updateBooking(myBooking);

        response.setMessage("The booking was updated successfully.");
        response.setStatus(HttpStatus.OK.name());
        response.setTimestamp(new Date());
        response.setData(updatedBooking);

        return response;
    }

    /**
     * Delete a booking by the bookingId.
     * @param bookingId
     * @return
     */
    @DeleteMapping("/deleteBooking/{bookingId}") //A deleted booking already has a customer
    public ResponseDto deleteBooking(@PathVariable int bookingId)
    {
        ResponseDto response = new ResponseDto();

        int result = service.deleteBooking(bookingId);
        if(result == 1)
        {
            response.setMessage("The booking was successfully deleted.");
            response.setStatus(HttpStatus.OK.name());
        }
        else
        {
            response.setMessage("The booking was not successfully deleted.");
            response.setStatus(HttpStatus.EXPECTATION_FAILED.name());
        }
        response.setTimestamp(new Date());
        response.setData(null);

        return response;
    }
}