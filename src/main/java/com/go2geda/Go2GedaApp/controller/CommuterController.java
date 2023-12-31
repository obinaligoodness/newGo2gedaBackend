package com.go2geda.Go2GedaApp.controller;

import com.go2geda.Go2GedaApp.dtos.request.AddressVerificationRequest;
import com.go2geda.Go2GedaApp.dtos.request.CommuterRegisterUserRequest;
import com.go2geda.Go2GedaApp.dtos.response.OkResponse;
import com.go2geda.Go2GedaApp.dtos.response.RegisterUserResponse;
import com.go2geda.Go2GedaApp.exceptions.NotFoundException;
import com.go2geda.Go2GedaApp.exceptions.UserNotFound;
import com.go2geda.Go2GedaApp.services.CommuterService;
import com.go2geda.Go2GedaApp.services.DriverService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/go2geda/commuter")
@CrossOrigin("*")
@AllArgsConstructor
public class CommuterController {
    private final CommuterService commuterService;
    private final DriverService driverService;
    @PostMapping("/register-commuter")
    public ResponseEntity<RegisterUserResponse> registerCommuter(@RequestBody  CommuterRegisterUserRequest request){
        return new ResponseEntity<>(commuterService.register(request), HttpStatus.OK);
    }

    @GetMapping("/getCommuter/{commuterId}")
    public ResponseEntity<RegisterUserResponse> getCommuter(@PathVariable Long commuterId){
        try {
            return new ResponseEntity<>(commuterService.findCommuterById(commuterId),HttpStatus.FOUND);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

//    @PatchMapping("/activate/{email}")
//    @PreAuthorize("hasAnyAuthority('DRIVER', 'COMMUTER')")
//    public ResponseEntity<OkResponse> activateAccount(@PathVariable String email){
//        return ResponseEntity.ok(commuterService.activateAccount(email));
//    }
//
    @PostMapping("/verify-address/{email}")
    public ResponseEntity<OkResponse> verifyAddress(AddressVerificationRequest addressVerificationRequest,
                                                    @PathVariable String email) throws UserNotFound {
        return ResponseEntity.ok(driverService.verifyAddress(addressVerificationRequest, email));
    }

}
