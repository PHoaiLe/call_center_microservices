package org.com.Receiver.Pickup;


import org.com.Receiver.Request.Requests.CallCenterPickupRequest;
import org.com.Receiver.Request.Requests.ClientAppPickupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/new_request/client")
    public boolean startPickUp(@RequestBody() ClientAppPickupRequest bookingRequest)
    {
        System.out.println("client app");
        pickupServices.sendToDataRoom(bookingRequest);
        return true;
    }

    @PostMapping(value = "/new_request/center")
    public boolean startPickUp(@RequestBody() CallCenterPickupRequest bookingRequest)
    {
        System.out.println("call center");
        pickupServices.sendToDataRoom(bookingRequest);
        return true;
    }
}
