package com.cosmo.video.service.impl;

import com.cosmo.video.clients.NftDiscoveryClient;
import com.cosmo.video.clients.NftFeignClient;
import com.cosmo.video.clients.NftRestTemplateClient;
import com.cosmo.video.config.VideoConfig;
import com.cosmo.video.dto.NftDto;
import com.cosmo.video.repository.VideoRepository;
import com.cosmo.video.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {

    private final VideoRepository videoRepository;
    private final VideoConfig videoConfig;
    private final NftFeignClient nftFeignClient;
    private final NftRestTemplateClient nftRestTemplateClient;
    private final NftDiscoveryClient nftDiscoveryClient;

    private NftDto retrieveOrgInfo(Long nftId, String clientType){
        NftDto nftDto = null;

        switch (clientType) {
            case "feign":
                System.out.println("I am using the feign client");
                nftDto = nftFeignClient.getNft(nftId);
                break;
            case "rest":
                System.out.println("I am using the rest client");
                nftDto = nftRestTemplateClient.getNft(nftId);
                break;
            case "discovery":
                System.out.println("I am using the discovery client");
                nftDto = nftDiscoveryClient.getNft(nftId);
                break;
            default:
                nftDto = nftRestTemplateClient.getNft(nftId);
        }

        return nftDto;
    }


    @Override
    public String helloWorld() {
        return "hello World!!";
    }

    @Override
    @Transactional(readOnly = true)
    public NftDto getNft(Long nftId) {
        NftDto nftDto = retrieveOrgInfo(nftId, "discovery");

        return nftDto;
    }
}
