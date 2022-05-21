package com.cosmo.video.controller;

import com.cosmo.video.dto.NftDto;
import com.cosmo.video.dto.VideoDto;
import com.cosmo.video.entity.Video;
import com.cosmo.video.service.VideoService;
import com.cosmo.video.utils.UserContextHolder;
import com.cosmo.video.vo.RequestVideo;
import com.cosmo.video.vo.ResponseVideo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/api/video")
@RequiredArgsConstructor
public class VideoAPIController {
    private static final Logger logger = LoggerFactory.getLogger(VideoAPIController.class);

    private final VideoService videoService;

    @GetMapping("/hello")
    public ResponseEntity<String> helloWorld() {
        return ResponseEntity.ok(videoService.helloWorld());
    }

    @PostMapping("/{userId}")
    public ResponseEntity<ResponseVideo> createVideo(
            @RequestBody RequestVideo requestVideo
            , @PathVariable String userId
            ) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        VideoDto videoDto = mapper.map(requestVideo, VideoDto.class);
        videoDto.setUserId(userId);
        VideoDto createdVideo = videoService.createVideo(videoDto);
        ResponseVideo responseVideo = mapper.map(createdVideo, ResponseVideo.class);
        return  ResponseEntity.status(HttpStatus.CREATED).body(responseVideo);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<ResponseVideo>> getVideos(
            @PathVariable String userId
    ) {

        Iterable<Video> videos = videoService.getAllVideosByUserId(userId);
        List<ResponseVideo> result = new ArrayList<>();
        videos.forEach( v -> {
            result.add(new ModelMapper().map(v, ResponseVideo.class));
        });
        return  ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
