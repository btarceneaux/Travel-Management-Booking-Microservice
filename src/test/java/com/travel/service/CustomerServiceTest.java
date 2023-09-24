package com.travel.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.travel.bean.Customer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerServiceTest
{
    @Autowired
    CustomerService service;

    @Test
    public void getAllCustomers()
    {
        Page<Customer> customerPage = service.getAllCustomers(1, 10, "lastName", "ASC");
        assertEquals(1, customerPage.getContent().size());
    }
}