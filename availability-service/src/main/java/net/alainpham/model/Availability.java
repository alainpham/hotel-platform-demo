package net.alainpham.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Availability {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String hotel;
    private String room;
    
    @Temporal(TemporalType.DATE)
    private Date bookingStartDate;

    @Temporal(TemporalType.DATE)
    private Date bookingEndDate;

    private boolean booked;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getHotel() {
        return hotel;
    }
    public void setHotel(String hotel) {
        this.hotel = hotel;
    }
    public String getRoom() {
        return room;
    }
    public void setRoom(String room) {
        this.room = room;
    }
    public Date getBookingStartDate() {
        return bookingStartDate;
    }
    public void setBookingStartDate(Date bookingStartDate) {
        this.bookingStartDate = bookingStartDate;
    }
    public Date getBookingEndDate() {
        return bookingEndDate;
    }
    public void setBookingEndDate(Date bookingEndDate) {
        this.bookingEndDate = bookingEndDate;
    }
    public boolean isBooked() {
        return booked;
    }
    public void setBooked(boolean booked) {
        this.booked = booked;
    }
    @Override
    public String toString() {
        return "Availability [id=" + id + ", hotel=" + hotel + ", room=" + room + ", bookingStartDate="
                + bookingStartDate + ", bookingEndDate=" + bookingEndDate + ", booked=" + booked + "]";
    }
    

    
}
