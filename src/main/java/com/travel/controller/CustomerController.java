package com.travel.controller;

import com.travel.bean.Customer;
import com.travel.dto.ResponseDto;
import com.travel.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Date;

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
    @GetMapping("/customers")
    public Page<Customer> getAllCustomers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                          @RequestParam(defaultValue = "lastName") String sort,
                                          @RequestParam(defaultValue = "ASC") String sortOrder)
    {
        return service.getAllCustomers(page, size, sort, sortOrder);
    }

    @GetMapping("/customers/{customerId}")
    public ResponseDto getCustomerById(@PathVariable  int customerId)
    {
        ResponseDto response = new ResponseDto();
        Customer myCustomer = service.getCustomerByCustomerId(customerId);

        if(myCustomer.getCustomerId() > 0)
        {
            response.setMessage("The booking was found.");
            response.setStatus(HttpStatus.OK.name());
            response.setTimestamp(new Date());
            response.setData(myCustomer);
        }
        else
        {
            response.setMessage("The customer does not exist.");
            response.setStatus(HttpStatus.EXPECTATION_FAILED.name());
            response.setTimestamp(new Date());
            response.setData(null);
        }

        return response;
    }

    @PostMapping("/createCustomer")
    public ResponseDto createCustomer(@RequestBody Customer myCustomer)
    {
        ResponseDto response = new ResponseDto();
        Customer savedCustomer = service.createCustomer(myCustomer);

        if(savedCustomer.getCustomerId() > 0)
        {
            response.setMessage("The customer was successfully saved.");
            response.setStatus(HttpStatus.OK.name());
            response.setTimestamp(new Date());
            response.setData(savedCustomer);
        }
        else
        {
            response.setMessage("The customer was not successfully saved.");
            response.setStatus(HttpStatus.EXPECTATION_FAILED.name());
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
    @PutMapping("/updateCustomer")
    public ResponseDto updateCustomer(@RequestBody Customer myCustomer)
    {
        ResponseDto response = new ResponseDto();

        Customer updatedCustomer = service.updateCustomer(myCustomer);

        response.setMessage("The customer was updated successfully.");
        response.setStatus(HttpStatus.OK.name());
        response.setTimestamp(new Date());
        response.setData(updatedCustomer);

        return response;
    }

    /**
     * Delete a customer.
     * @param customerId
     * @return
     */
    @DeleteMapping("/deleteCustomer/{customerId}")
    public ResponseDto deleteCustomer(@PathVariable int customerId)
    {
        ResponseDto response = new ResponseDto();

        int result = service.deleteCustomer(customerId);
        if(result == 1)
        {
            response.setMessage("The customer was successfully deleted.");
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