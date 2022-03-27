package com.cosmo.video.clients;

import com.cosmo.video.dto.NftDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.ws.rs.Path;

@FeignClient("nftservice")
public interface NftFeignClient {
    @RequestMapping(
            method = RequestMethod.GET
            , value = "/v1/nft/{nftId}"
            , consumes = "application/json"
    )
    NftDto getNft(@PathVariable("nftId") Long nftId);
}
