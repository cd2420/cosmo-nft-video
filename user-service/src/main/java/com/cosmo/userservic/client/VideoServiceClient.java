package com.cosmo.userservic.client;

import com.cosmo.userservic.vo.ResponseVideo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "video-service")
public interface VideoServiceClient {

    @GetMapping("/v1/api/video/{userId}")
    List<ResponseVideo> getVideos(@PathVariable String userId);
}
