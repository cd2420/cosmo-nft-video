package com.cosmo.nft.service.impl;

import com.cosmo.nft.dto.NftDto;
import com.cosmo.nft.entity.Nft;
import com.cosmo.nft.repository.NftRepository;
import com.cosmo.nft.service.NftService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NftServiceImpl implements NftService {

    private final NftRepository nftRepository;

    @Override
    public NftDto getNft(Long nftId) {

        return NftDto.from(nftRepository.getById(nftId));
    }

    @Override
    public NftDto createNft() {
        Nft nft = new Nft();
        nft.setName("hello world nft!!!!");
        return NftDto.from(nftRepository.save(nft));
    }
}
