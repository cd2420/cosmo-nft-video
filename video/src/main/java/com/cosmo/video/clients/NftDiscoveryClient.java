package com.cosmo.video.clients;

import com.cosmo.video.dto.NftDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NftDiscoveryClient {

    private final DiscoveryClient discoveryClient;

    public NftDto getNft(Long nftId) {
        RestTemplate restTemplate = new RestTemplate();
        List<ServiceInstance> instances = discoveryClient.getInstances("nftservice");

        if (instances.size() == 0)  return null;
        String serviceUri = String.format("%s/v1/api/nft/%s",instances.get(0).getUri().toString(), nftId);
        ResponseEntity<NftDto> restExchange = restTemplate.exchange(
                serviceUri
                , HttpMethod.GET
                , null, NftDto.class, nftId
        );
        return restExchange.getBody();
    }
}
