package com.travel.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import com.travel.bean.Customer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerServiceTest
{
    @Autowired
    CustomerService service;

    @Test
    @Order(1)
    public void createTestCustomer()
    {
        Customer createdCustomer = new Customer();

        createdCustomer.setLastName("X");
        createdCustomer.setFirstName("Mr");
        createdCustomer.setEmailAddress("mrx@example.com");

        Customer myCustomer = service.createCustomer(createdCustomer);
        assertNotEquals(0, myCustomer.getCustomerId());
    }

    @Test
    @Order(2)
    public void getAllCustomers()
    {
        Page<Customer> customerPage = service.getAllCustomers(1, 10, "lastName", "ASC");
        long totalItems = customerPage.getTotalElements();

        System.out.println("Total items are " + totalItems);

        assertNotEquals(0, totalItems);
    }

    @Test
    @Order(3)
    public void updateCustomer()
    {
        Customer myCustomer;
        Optional<Customer> optionalCustomer = service.getCustomerByCustomerId(1);
        if(optionalCustomer.isPresent())
        {
            myCustomer = optionalCustomer.get();
            myCustomer.setEmailAddress("mrx@yahoo.com");
            Customer updatedCustomer = service.updateCustomer(myCustomer);
            assertEquals(1, updatedCustomer.getCustomerId());
        }
    }

    @Test
    @Order(4)
    public void deleteCustomer()
    {
        int result = service.deleteCustomer(1);
        assertEquals(1, result);
    }
}