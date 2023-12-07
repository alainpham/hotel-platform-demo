package net.alainpham.service;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.martensigwart.fakeload.FakeLoad;
import com.martensigwart.fakeload.FakeLoadExecutor;
import com.martensigwart.fakeload.FakeLoadExecutors;
import com.martensigwart.fakeload.FakeLoads;
import com.martensigwart.fakeload.MemoryUnit;

import net.alainpham.model.Availability;
import net.alainpham.repo.AvailablilityRepository;


@RestController
public class AvailabilityService {
    

    private static final Logger logger = LoggerFactory.getLogger(AvailabilityService.class);

    Random generator = new Random();


    @Value("${app.processing.exectimeavg.ms}")
    double execTimeAvgMs;

    @Value("${app.processing.exectimestdev.ms}")
    double execTimeStdevMs;

    @Value("${app.processing.failurerate.percentage}")
    int failureRatePercentage;

    @Value("${app.processing.cpuusage.percentage}")
    int cpuUsagePercentage;

    @Value("${app.processing.memusage.mb}")
    int memUsageMB;

    @Autowired
    private AvailablilityRepository availablilityRepository;


    @PostMapping("/check-availability")
    public ResponseEntity<Availability> checkAvailability(@RequestBody Availability input){
        

        long randomExecTimeMs = Double.valueOf(Math.round(generator.nextGaussian()*execTimeStdevMs+execTimeAvgMs)).longValue() ;
        
        logger.info("check avail started");

        if (randomExecTimeMs>0){
            
            FakeLoad fakeload = FakeLoads.create()
            .lasting(randomExecTimeMs, TimeUnit.MILLISECONDS)
            .withCpu(cpuUsagePercentage)
            .withMemory(memUsageMB, MemoryUnit.MB);
            FakeLoadExecutor executor = FakeLoadExecutors.newDefaultExecutor();
            executor.execute(fakeload);
        }


        List<Availability> avail =  availablilityRepository.findByHotelAndRoomAndBookingEndDateGreaterThanAndBookingStartDateLessThan(
            input.getHotel(), 
            input.getRoom(), 
            input.getBookingStartDate(), 
            input.getBookingEndDate());

        int failNb = generator.nextInt(100);

        if (failNb < failureRatePercentage){
            try {
                throw new Exception("Availability Service has failed!");
            } catch (Exception e) {
                input.setBooked(true);
                logger.error("check ended with error");
                return new ResponseEntity<Availability>(input,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        if (avail.size() > 0 ){
            input = avail.get(0);
        }else{
            input.setBooked(false);
        }
        
        logger.info("check ended " + input.toString());

        return new ResponseEntity<>(input, HttpStatus.OK);

    }


    @Scheduled(fixedRate = 60000*5) // 10000 milliseconds = 10 seconds
    public void myScheduledMethod() {
        logger.info("reset avail DB");
        availablilityRepository.deleteAll();
    }

}
