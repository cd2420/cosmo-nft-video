package com.cosmo.video.controller;

import com.cosmo.video.dto.NftDto;
import com.cosmo.video.service.VideoService;
import com.cosmo.video.utils.UserContextHolder;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/api/video")
@RequiredArgsConstructor
public class VideoAPIController {
    private static final Logger logger = LoggerFactory.getLogger(VideoAPIController.class);

    private final VideoService videoService;

    @GetMapping("/hello")
    public ResponseEntity<String> helloWorld() {
        return ResponseEntity.ok(videoService.helloWorld());
    }

    @GetMapping("/nft")
    public ResponseEntity<NftDto> getNft() {
        return ResponseEntity.ok(videoService.getNft(1L));
    }

    @GetMapping("/hystrix")
    public ResponseEntity<Void> getTestHystrix() {
        logger.info("VideoAPIController Correlation id: {}", UserContextHolder.getContext().getCorrelationId());
        videoService.testHystrix();
        return ResponseEntity.ok().build();
    }
}
