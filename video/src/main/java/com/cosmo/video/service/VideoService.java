package com.cosmo.video.service;

import com.cosmo.video.dto.NftDto;
import com.cosmo.video.dto.VideoDto;
import com.cosmo.video.entity.Video;

public interface VideoService {

    String helloWorld();

    VideoDto createVideo(VideoDto videoDto);

    Iterable<Video> getAllVideosByUserId(String userId);
}
