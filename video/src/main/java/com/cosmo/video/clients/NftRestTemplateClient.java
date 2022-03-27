package com.cosmo.video.clients;

import com.cosmo.video.dto.NftDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class NftRestTemplateClient {

    @Autowired
    RestTemplate restTemplate;

    public NftDto getNft(Long nftId) {
        ResponseEntity<NftDto> restExchange = restTemplate.exchange(
                "http://nftservice/v1/nft/{nftId}"
                , HttpMethod.GET
                , null, NftDto.class, nftId
        );
        return restExchange.getBody();
    }
}
