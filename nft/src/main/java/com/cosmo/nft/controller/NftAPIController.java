package com.cosmo.nft.controller;

import com.cosmo.nft.dto.NftDto;
import com.cosmo.nft.service.NftService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/api/nft")
@RequiredArgsConstructor
public class NftAPIController {

    private final NftService nftService;

    @GetMapping("/{nftId}")
    @Transactional(readOnly = true)
    public NftDto getNft(@PathVariable("nftId") Long nftId) {
        return nftService.getNft(nftId);
    }

    @PostMapping("")
    @Transactional
    public NftDto createNft() {
        return nftService.createNft();
    }
}
