package com.cosmo.video.video.service.impl;

import com.cosmo.video.video.service.VideoService;
import org.springframework.stereotype.Service;

@Service
public class VideoServiceImpl implements VideoService {

    @Override
    public String helloWorld() {
        return "hello World!!";
    }
}
