package com.cosmo.userservic.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseAccount {

    private String email;
    private String name;
    private String accountId;
    private List<ResponseVideo> videos;

}
