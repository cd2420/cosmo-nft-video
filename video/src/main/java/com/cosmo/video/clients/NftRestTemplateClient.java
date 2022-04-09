package com.cosmo.video.clients;

import com.cosmo.video.dto.NftDto;
import com.cosmo.video.utils.UserContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class NftRestTemplateClient {

    @Autowired
    RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(NftRestTemplateClient.class);

    public NftDto getNft(Long nftId) {
        logger.debug(">>> In Video Service.getNft: {}. Thread Id: {}", UserContextHolder.getContext().getCorrelationId(), Thread.currentThread().getId());
        ResponseEntity<NftDto> restExchange = restTemplate.exchange(
                "http://localhost:8000/v1/api/nft/{nftId}"
                , HttpMethod.GET
                , null, NftDto.class, nftId
        );
        return restExchange.getBody();
    }
}
