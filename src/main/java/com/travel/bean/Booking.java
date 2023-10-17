package com.travel.bean;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
@Entity
public class Booking
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;
    private Date bookingDateAndTime;
    private int numberOfPassengers;
    @OneToOne(cascade = CascadeType.DETACH)
    private Customer relatedCustomer;
    @OneToOne(cascade = CascadeType.ALL)
    private Address sourceAddress;

    @OneToOne(cascade = CascadeType.ALL)
    private Address destinationAddress;

    private double cost = 0;

    public Booking()
    {

    }

    public int getBookingId()
    {
        return bookingId;
    }

    public void setBookingId(int bookingId)
    {
        this.bookingId = bookingId;
    }

    public Date getBookingDateAndTime()
    {
        return bookingDateAndTime;
    }

    public void setBookingDateAndTime(Date bookingDateAndTime)
    {
        this.bookingDateAndTime = bookingDateAndTime;
    }

    public int getNumberOfPassengers()
    {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(int numberOfPassengers)
    {
        this.numberOfPassengers = numberOfPassengers;
    }

    public Customer getRelatedCustomer()
    {
        return relatedCustomer;
    }

    public void setRelatedCustomer(Customer relatedCustomer)
    {
        this.relatedCustomer = relatedCustomer;
    }

    public Address getSourceAddress()
    {
        return sourceAddress;
    }

    public void setSourceAddress(Address sourceAddress)
    {
        this.sourceAddress = sourceAddress;
    }

    public Address getDestinationAddress()
    {
        return destinationAddress;
    }

    public void setDestinationAddress(Address destinationAddress)
    {
        this.destinationAddress = destinationAddress;
    }

    public double getCost()
    {
        return cost;
    }

    public void setCost(double cost)
    {
        this.cost = cost;
    }
}