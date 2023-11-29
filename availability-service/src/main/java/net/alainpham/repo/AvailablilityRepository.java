package net.alainpham.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import net.alainpham.model.Availability;

public interface  AvailablilityRepository extends PagingAndSortingRepository<Availability, Long>, CrudRepository<Availability,Long> {


    /*     SELECT b1.booking_id, b1.hotel, b1.room,
        b1.booking_start_date, b1.booking_end_date
    FROM Bookings b1
    WHERE b1.room = :roomId
    AND :inputStartDate < b1.booking_end_date
    AND :inputEndDate > b1.booking_start_date; */

    // List<Availability> findByHotelAndRoomAndBookingEndDateGreaterThanEqualAndBookingStartDateLessThanEqual(
    List<Availability> findByHotelAndRoomAndBookingEndDateGreaterThanAndBookingStartDateLessThan(
        @Param("hotel") String hotel,
        @Param("room") String room,
        @Param("inputStartDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date inputStartDate, 
        @Param("inputEndDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date inputEndDate);
}
