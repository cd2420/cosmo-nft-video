package com.cosmo.video.video.repository;

import com.cosmo.video.video.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VideoRepository extends JpaRepository<Video, Long> {
    Optional<Video> findById(Long id);
}
