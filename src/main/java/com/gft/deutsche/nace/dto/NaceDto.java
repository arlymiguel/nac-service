package com.gft.deutsche.nace.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NaceDto {

    private Long id;
    @NotNull
    private String order;
    @NotNull
    private String level;
    @NotNull
    private String code;
    private String parent;
    @NotNull
    private String description;
    private String include;
    private String rulings;
    private String exclude;
    private String reference;

}
