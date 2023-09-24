package com.travel.bean;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
@Entity
public class Booking
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int bookingId;
    private String pickUpAddress;
    private String destinationAddress;
    private Date bookingDate;
    private Time bookingTime;
    private int numberOfPassengers;

    @ManyToOne(cascade = CascadeType.ALL)
    private Customer relatedCustomer;

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

    public String getPickUpAddress()
    {
        return pickUpAddress;
    }

    public void setPickUpAddress(String pickUpAddress)
    {
        this.pickUpAddress = pickUpAddress;
    }

    public String getDestinationAddress()
    {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress)
    {
        this.destinationAddress = destinationAddress;
    }

    public Date getBookingDate()
    {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate)
    {
        this.bookingDate = bookingDate;
    }

    public Time getBookingTime()

    {
        return bookingTime;
    }

    public void setBookingTime(Time bookingTime)
    {
        this.bookingTime = bookingTime;
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

    @Override
    public String toString()
    {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", pickUpAddress='" + pickUpAddress + '\'' +
                ", destinationAddress='" + destinationAddress + '\'' +
                ", bookingDate=" + bookingDate +
                ", bookingTime=" + bookingTime +
                ", numberOfPassengers=" + numberOfPassengers +
                '}';
    }
}