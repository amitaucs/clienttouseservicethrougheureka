package com.amisoft.routing;

import com.amisoft.pojo.Welcome;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by amitdatta on 20/05/17.
 */


@EnableFeignClients
@FeignClient("amisoft-service")
public interface WelcomeAmisoftClient {

    @RequestMapping(method = RequestMethod.GET, value = "/amisoft/{name}")
    Welcome welcomeToAmisoft(@PathVariable("name") String name);

}

