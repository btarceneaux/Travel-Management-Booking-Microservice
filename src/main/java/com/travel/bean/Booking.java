package com.travel.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Time;
import java.util.Date;
@Entity
public class Booking
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;
    private String pickUpAddress;
    private String destinationAddress;
    private Date bookingDate;
    private Time bookingTime;
    private int numberOfPassengers;

    public Booking()
    {

    }

    public Booking(int bookingId, String pickUpAddress, String destinationAddress, Date bookingDate, Time bookingTime, int numberOfPassengers)
    {
        this.bookingId = bookingId;
        this.pickUpAddress = pickUpAddress;
        this.destinationAddress = destinationAddress;
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
        this.numberOfPassengers = numberOfPassengers;
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