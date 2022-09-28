package com.nace.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
