package com.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.travel.bean.Customer;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>
{
    Customer findByEmailAddress(String emailAddress);
}