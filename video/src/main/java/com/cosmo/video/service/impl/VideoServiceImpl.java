package com.cosmo.video.service.impl;

import com.cosmo.video.clients.NftRestTemplateClient;
import com.cosmo.video.config.VideoConfig;
import com.cosmo.video.dto.NftDto;
import com.cosmo.video.repository.VideoRepository;
import com.cosmo.video.service.VideoService;
import com.cosmo.video.utils.UserContextHolder;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {
    private static final Logger logger = LoggerFactory.getLogger(VideoServiceImpl.class);

    private final VideoRepository videoRepository;
    private final VideoConfig videoConfig;
    private final NftRestTemplateClient nftRestTemplateClient;

    @Override
    public String helloWorld() {
        return "hello World!!";
    }

    @Override
    @Transactional(readOnly = true)
    @HystrixCommand
    public NftDto getNft(Long nftId) {
        return  nftRestTemplateClient.getNft(nftId);
    }

    @Override
    @HystrixCommand(//fallbackMethod = "buildFallbackLicenseList",
            threadPoolKey = "licenseByOrgThreadPool",
            threadPoolProperties =
                    {@HystrixProperty(name = "coreSize",value="30"),
                            @HystrixProperty(name="maxQueueSize", value="10")},
            commandProperties={
                    @HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="10"),
                    @HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="75"),
                    @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="7000"),
                    @HystrixProperty(name="metrics.rollingStats.timeInMilliseconds", value="15000"),
                    @HystrixProperty(name="metrics.rollingStats.numBuckets", value="5")}
    )
    public void testHystrix() {
        logger.info("LicenseService.getLicensesByOrg  Correlation id: {}", UserContextHolder.getContext().getCorrelationId());
        randomlyRunLong();
    }

    private void randomlyRunLong(){
        Random rand = new Random();

        int randomNum = rand.nextInt((3 - 1) + 1) + 1;

        if (randomNum==3) sleep();
    }

    private void sleep(){
        try {
            Thread.sleep(11000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
