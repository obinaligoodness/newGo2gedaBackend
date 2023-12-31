package com.go2geda.Go2GedaApp.controller;


import com.go2geda.Go2GedaApp.data.models.Notification;
import com.go2geda.Go2GedaApp.data.models.Trip;
import com.go2geda.Go2GedaApp.dtos.request.AcceptAndRejectRequest;
import com.go2geda.Go2GedaApp.dtos.request.CreateTripRequest;
import com.go2geda.Go2GedaApp.dtos.response.AcceptRequestNotificationResponse;
import com.go2geda.Go2GedaApp.dtos.response.BookingNotificationResponse;
import com.go2geda.Go2GedaApp.dtos.response.OkResponse;
import com.go2geda.Go2GedaApp.dtos.response.RejectRequestNotificationResponse;
import com.go2geda.Go2GedaApp.exceptions.NotFoundException;
import com.go2geda.Go2GedaApp.services.TripService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trip")
@AllArgsConstructor
@CrossOrigin("*")
public class TripController {
    private final TripService tripService;
    @PostMapping("/createTrip")
    public ResponseEntity<OkResponse> createATrip(@RequestBody  CreateTripRequest createTripRequest) {
        try {
            return new ResponseEntity<>(tripService.createTrip(createTripRequest), HttpStatus.OK);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/cancelTrip/{tripId}")
    public ResponseEntity<OkResponse> cancelATrip(@PathVariable Long tripId){
        try {
            return new ResponseEntity<>(tripService.cancelTrip(tripId),HttpStatus.OK);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/searchByPickup/{from}")
    public ResponseEntity<List<Trip>> searchTripByFrom(@PathVariable String from){
        try {
            return new ResponseEntity<>(tripService.searchTripByFrom(from),HttpStatus.FOUND);
        } catch (NotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/searchByTo/{to}")
    public ResponseEntity<List<Trip>> searchTripByTo(@PathVariable String to) {
        try {
            return new ResponseEntity<>(tripService.searchTripByTo(to), HttpStatus.FOUND);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/startTrip/{tripId}")
    public ResponseEntity<OkResponse> startTrip(@PathVariable Long tripId) {
        try {
            return new ResponseEntity<>(tripService.startTrip(tripId), HttpStatus.OK);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/endTrip/{tripId}")
    public ResponseEntity<OkResponse> endTrip(@PathVariable Long tripId) {
        try {
            return new ResponseEntity<>(tripService.endTrip(tripId), HttpStatus.OK);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/viewTrip/{tripId}")
    public ResponseEntity<Trip> viewTrip(@PathVariable Long tripId) {
        try {
            return new ResponseEntity<>(tripService.viewTrip(tripId), HttpStatus.OK);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/viewBookedTrip/{commuterId}")
    public ResponseEntity<List<Trip>> viewCommuterBookedTrip(@PathVariable Long commuterId) {
        try {
            return new ResponseEntity<>(tripService.viewCommuterBookedTrips(commuterId), HttpStatus.OK);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/acceptTrip")
    public ResponseEntity<AcceptRequestNotificationResponse> acceptTripRequest(@RequestBody AcceptAndRejectRequest acceptAndRejectRequest) {
        try {
            return new ResponseEntity<>(tripService.acceptTripRequest(acceptAndRejectRequest), HttpStatus.OK);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/rejectTrip")
    public ResponseEntity<RejectRequestNotificationResponse> rejectTripRequest(@RequestBody AcceptAndRejectRequest acceptAndRejectRequest) {
        try {
            return new ResponseEntity<>(tripService.rejectTripRequest(acceptAndRejectRequest), HttpStatus.OK);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/bookTrip")
    public ResponseEntity<BookingNotificationResponse> bookTrip(@RequestBody AcceptAndRejectRequest acceptAndRejectRequest) {
        try {
            return new ResponseEntity<>(tripService.bookTrip(acceptAndRejectRequest), HttpStatus.ACCEPTED);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/searchTripBy/from={From}&to={To}")
    public ResponseEntity<List<Trip>> searchTripByFromAndTo(@PathVariable String From, @PathVariable String To){
        try {
            return new ResponseEntity<>(tripService.searchTripByFromAndTo(From, To), HttpStatus.OK);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/driver-Trip/{id}")
    public ResponseEntity<List<Trip>> getDriversTrip(@PathVariable Long id){
        return new ResponseEntity<>(tripService.getDriversTrips(id),HttpStatus.OK);
    }

    @GetMapping("/trip-requests/{driverId}")
    public ResponseEntity<List<Notification>> getTripRequest(@PathVariable Long driverId){
        return new ResponseEntity<>(tripService.getTripRequests(driverId),HttpStatus.OK);
    }

    @GetMapping("/viewDriverTrips/{driverId}")
    public ResponseEntity<List<Trip>> driverTrips(@PathVariable Long driverId){
        try {
            return new ResponseEntity<>(tripService.driverTripHistory(driverId), HttpStatus.OK);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/viewCommuterTrips/{commuterId}")
    public ResponseEntity<List<Trip>> commuterTrips(@PathVariable Long commuterId){
        try {
            return new ResponseEntity<>(tripService.commuterTripHistory(commuterId), HttpStatus.OK);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
