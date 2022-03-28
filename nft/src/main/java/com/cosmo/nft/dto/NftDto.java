package com.cosmo.nft.dto;

import com.cosmo.nft.entity.Nft;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NftDto {

    private Long id;
    private String name;

    public static NftDto from(Nft nft) {
        return NftDto.builder()
                .id(nft.getId())
                .name(nft.getName())
                .build();
    }
}
