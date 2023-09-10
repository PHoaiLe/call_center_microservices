package org.com.Receiver.Pickup;


import jakarta.servlet.annotation.HttpConstraint;
import org.com.Receiver.Request.ExternalRequests.ExternalCallCenterPickupRequest;
import org.com.Receiver.Request.ExternalRequests.ExternalClientAppPickupRequest;
import org.com.Receiver.Request.ExternalRequests.ExternalGetCostRequest;
import org.com.Receiver.Request.Requests.AcceptPickupRequest;
import org.com.Receiver.Request.Requests.CallCenterPickupRequest;
import org.com.Receiver.Request.Requests.ClientAppPickupRequest;
import org.com.Receiver.Request.Requests.UpdateFCMToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController()
@RequestMapping("/pickup")
public class PickupRouter {


    private PickupServices pickupServices;

    @Autowired
    public PickupRouter(PickupServices pickupServices)
    {
        this.pickupServices = pickupServices;
    }

    @GetMapping("/greeting")
    public boolean greeting()
    {
        return true;
    }

    @PostMapping(value = "/client/cost",
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                    MediaType.MULTIPART_FORM_DATA_VALUE})
    public boolean getCost(ExternalGetCostRequest request)
    {
        System.out.println("Get cost: "+request);
        pickupServices.sendToDataRoom(request);
        return true;
    }

    @PostMapping(value = "/new_request/client",
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                    MediaType.MULTIPART_FORM_DATA_VALUE})
    public boolean startPickUp(ExternalClientAppPickupRequest bookingRequest)
    {
        System.out.println("client app");
        pickupServices.sendToDataRoom(bookingRequest);
        return true;
    }

    @PostMapping(value = "/new_request/center",
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE})
    public boolean startPickUp(ExternalCallCenterPickupRequest bookingRequest)
    {
        System.out.println("call center");
        pickupServices.sendToDataRoom(bookingRequest);
        return true;
    }

    @PostMapping(value = "/driver/accept",
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                    MediaType.MULTIPART_FORM_DATA_VALUE})
    public boolean acceptPickupRequest(AcceptPickupRequest acceptPickupRequest)
    {
        System.out.println(acceptPickupRequest);
        return true;
    }

    @PostMapping(value = "/driver/fcm_update",
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                    MediaType.MULTIPART_FORM_DATA_VALUE})
    public boolean updateFCMToken(UpdateFCMToken request)
    {

        return true;
    }
}
