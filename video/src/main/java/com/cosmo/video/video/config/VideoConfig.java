package com.cosmo.video.video.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class VideoConfig {

    @Value("${example.property}")
    private String exampleProperty;
}
