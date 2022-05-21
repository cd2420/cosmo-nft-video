package com.cosmo.video.service.impl;

import com.cosmo.video.dto.NftDto;
import com.cosmo.video.dto.VideoDto;
import com.cosmo.video.entity.Video;
import com.cosmo.video.repository.VideoRepository;
import com.cosmo.video.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {

    private final VideoRepository videoRepository;

    @Override
    public String helloWorld() {
        return "hello World!!";
    }

    @Override
    @Transactional
    public VideoDto createVideo(VideoDto videoDto) {
        videoDto.setVideoId(UUID.randomUUID().toString());
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Video video = mapper.map(videoDto, Video.class);
        Video result = videoRepository.save(video);
        return mapper.map(result, VideoDto.class);
    }

    @Override
    public Iterable<Video> getAllVideosByUserId(String userId) {
        return videoRepository.findByUserId(userId);
    }


}
