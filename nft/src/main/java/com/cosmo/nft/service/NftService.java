package com.cosmo.nft.service;

import com.cosmo.nft.dto.NftDto;

public interface NftService {
    NftDto getNft(Long nftId);

    NftDto createNft();
}
