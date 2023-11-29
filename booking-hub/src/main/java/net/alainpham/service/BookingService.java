package net.alainpham.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import net.alainpham.model.Availability;
import net.alainpham.model.Booking;
import net.alainpham.repo.BookingRepository;

@RestController
public class BookingService {
    
    @Autowired
    private BookingRepository bookingRepository;

    private RestTemplate restTemplate = new RestTemplate();
    
    @Value("${availability.service.url}")
    private String availabilityServiceUrl;

    private static final Logger logger = LoggerFactory.getLogger(BookingService.class);

    @PostMapping("/book")
    public ResponseEntity<Booking> book(@RequestBody Booking input){

        Availability availability = new Availability();
        availability.setHotel(input.getHotel());
        availability.setRoom(input.getRoom());
        availability.setBookingStartDate(input.getBookingStartDate());
        availability.setBookingEndDate(input.getBookingEndDate());

        /* check availability */
        Availability availResponse = restTemplate.postForObject(availabilityServiceUrl + "/check-availability", availability, Availability.class);
        logger.info(availResponse.toString());
        /* if available save the booking*/
        if (availResponse != null && !availResponse.isBooked()){
            bookingRepository.save(input);
            /* update availability */
            logger.info("updating avail");
            availability.setBooked(true);
            Availability availBlockResponse = restTemplate.postForObject(availabilityServiceUrl + "/availabilities", availability, Availability.class);
            logger.info(availBlockResponse.toString());
            
        }else {
            input.setRoom(null);
            input.setHotel(null);;
            logger.info("dates a booked, abandoning booking");
        }

        return new ResponseEntity<>(input, HttpStatus.OK);
    }
}
