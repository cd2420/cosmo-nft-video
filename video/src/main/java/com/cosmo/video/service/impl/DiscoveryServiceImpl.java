package com.cosmo.video.service.impl;


import com.cosmo.video.service.DiscoveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class DiscoveryServiceImpl implements DiscoveryService {

    private final RestTemplate restTemplate;
    private final DiscoveryClient discoveryClient;

    public List getEurekaServices(){
       List<String> services = new ArrayList<String>();

        discoveryClient.getServices().forEach(serviceName -> {
            discoveryClient.getInstances(serviceName).forEach(instance->{
                services.add( String.format("%s:%s",serviceName,instance.getUri()));
            });
        });

        return services;
    }


}
