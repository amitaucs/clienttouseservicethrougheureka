package com.amisoft.controller;

import com.amisoft.pojo.Welcome;
import com.amisoft.routing.WelcomeAmisoftClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by amitdatta on 20/05/17.
 */


@EnableCircuitBreaker
@EnableFeignClients
@EnableZuulProxy
@EnableDiscoveryClient
@RestController
public class AmisoftControllerClient {

    /*private final WelcomeAmisoftClient welcomeAmisoftClient;

    @Autowired
    public AmisoftControllerClient(WelcomeAmisoftClient amisoftClient) {
        this.welcomeAmisoftClient = amisoftClient;
    }*/



   private final RestTemplate restTemplate;

   @Autowired
   public AmisoftControllerClient(RestTemplate restTemplate){
       this.restTemplate = restTemplate;
   }

    public String fallback() {
        return "Ops...We will get back soon!";
    }

    @HystrixCommand(fallbackMethod = "fallback")
    @RequestMapping(method = RequestMethod.GET, value = "/amisoftproxy")
    String welcome() {


        ResponseEntity<String> responseEntity = this.restTemplate.exchange("http://amisoft-service/amisoft", HttpMethod.GET,null, String.class);
        return responseEntity.getBody();

       //return this.welcomeAmisoftClient.welcomeToAmisoft(name).getMsg();
    }
}

