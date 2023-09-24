package com.travel.service;

import com.travel.bean.Customer;
import com.travel.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService
{
    @Autowired
    CustomerRepository repository;
    public Page<Customer> getAllCustomers(int page, int size, String sort, String sortOrder)
    {
        Sort.Direction direction = sortOrder.equalsIgnoreCase("ASC") ?
                Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sortBy = Sort.by(direction, sort);
        Pageable pageable = PageRequest.of(page, size, sortBy);

        return repository.findAll(pageable);
    }

    public Customer getCustomerByCustomerId(int customerId)
    {
        boolean exists = repository.existsById(customerId);

        if(exists)
        {
            return repository.getReferenceById(customerId);
        }
        else
        {
            return null;
        }
    }

    public Customer createCustomer(Customer myCustomer)
    {
        //First make sure it does not already exist
        Customer tempCustomer = repository.findByEmailAddress(myCustomer.getEmailAddress());
        if (tempCustomer.getEmailAddress().equals(myCustomer.getEmailAddress()))
        {
            return tempCustomer;
        }
        else
        {
            return repository.save(myCustomer);
        }
    }

    public int deleteCustomer(int customerId)
    {
        int result = 0;

        if(repository.existsById(customerId))
        {
            repository.deleteById(customerId);
            result = 1;
        }

        return result;
    }

    public Customer updateCustomer(Customer updatedCustomer)
    {
        if(repository.existsById(updatedCustomer.getCustomerId()))
        {
            return repository.save(updatedCustomer);
        }
        else
        {
            return null;
        }
    }
}
