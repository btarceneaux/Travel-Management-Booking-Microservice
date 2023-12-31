package com.travel.controller;

import com.travel.bean.Customer;
import com.travel.dto.ResponseDto;
import com.travel.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController
{
    @Autowired
    CustomerService service;

    /**
     * Get All Customers
     * @param page
     * @param size
     * @param sort
     * @param sortOrder
     * @return
     */
    @GetMapping("/customer")
    public List<Customer> getAllCustomers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                @RequestParam(defaultValue = "lastName") String sort,
                                @RequestParam(defaultValue = "ASC") String sortOrder)
    {
        return service.getAllCustomers(page, size, sort, sortOrder).toList();
    }

    @GetMapping("/customer/{customerId}")
    public ResponseDto getCustomerById(@PathVariable  int customerId)
    {
        ResponseDto response = new ResponseDto();
        Optional<Customer> optionalCustomer = service.getCustomerByCustomerId(customerId);
        if(optionalCustomer.isPresent())
        {
            Customer myCustomer = optionalCustomer.get();
            response.setMessage("The booking was found.");
            response.setStatus(HttpStatus.OK.value());
            response.setTimestamp(new Date());
            response.setData(myCustomer);
        }
        else
        {
            response.setMessage("The customer does not exist.");
            response.setStatus(HttpStatus.EXPECTATION_FAILED.value());
            response.setTimestamp(new Date());
            response.setData(null);
        }

        return response;
    }

    @PostMapping("/customer")
    public ResponseDto createCustomer(@RequestBody Customer myCustomer)
    {
        ResponseDto response = new ResponseDto();

        if((myCustomer.getFirstName() == null) ||
                (myCustomer.getLastName() == null) ||
                (myCustomer.getEmailAddress() == null))
        {
            response.setMessage("The customer was not successfully saved because one of the fields was blank.");
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setTimestamp(new Date());
            response.setData(null);

            return response;
        }

        Customer savedCustomer = service.createCustomer(myCustomer);

        if(savedCustomer.getCustomerId() > 0)
        {
            response.setMessage("The customer was successfully saved.");
            response.setStatus(HttpStatus.OK.value());
            response.setTimestamp(new Date());
            response.setData(savedCustomer);
        }
        else
        {
            response.setMessage("The customer was not successfully saved.");
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setTimestamp(new Date());
            response.setData(null);
        }

        return response;
    }

    /**
     * Update a customer.
     * @param myCustomer
     * @return
     */
    @PutMapping("/customer")
    public ResponseDto updateCustomer(@RequestBody Customer myCustomer)
    {
        ResponseDto response = new ResponseDto();

        Customer updatedCustomer = service.updateCustomer(myCustomer);

        response.setMessage("The customer was updated successfully.");
        response.setStatus(HttpStatus.OK.value());
        response.setTimestamp(new Date());
        response.setData(updatedCustomer);

        return response;
    }

    /**
     * Delete a customer.
     * @param customerId
     * @return
     */
    @DeleteMapping("/customer/{customerId}")
    public ResponseDto deleteCustomer(@PathVariable int customerId)
    {
        ResponseDto response = new ResponseDto();

        int result = service.deleteCustomer(customerId);
        if(result == 1)
        {
            response.setMessage("The customer was successfully deleted.");
            response.setStatus(HttpStatus.OK.value());
        }
        else
        {
            response.setMessage("The booking was not successfully deleted.");
            response.setStatus(HttpStatus.EXPECTATION_FAILED.value());
        }
        response.setTimestamp(new Date());
        response.setData(null);

        return response;
    }
}