package com.cosmo.video.repository;

import com.cosmo.video.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VideoRepository extends JpaRepository<Video, Long> {
    Optional<Video> findById(Long id);

    Iterable<Video> findByUserId(String userId);
}
