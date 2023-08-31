package org.com.LocatingService.Locating;

import org.com.LocatingService.Interfaces.LocatingPluginService;
import org.example.CallCenterLocatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LocatingServiceProvider
{
    LocatingPluginService locatingPluginService;

    @Autowired
    public LocatingServiceProvider(CallCenterLocatingService callCenterLocatingService)
    {
        this.locatingPluginService = (LocatingPluginService) callCenterLocatingService;
    }

    @Bean
    public LocatingPluginService getLocatingPluginService() {
        String address1 = "227 Đường Nguyễn Văn Cừ, phường 4, Quận 5, Thành phố Hồ Chí Minh";
        locatingPluginService.getCoordinateFromAddress(address1);
        return locatingPluginService;
    }
}
