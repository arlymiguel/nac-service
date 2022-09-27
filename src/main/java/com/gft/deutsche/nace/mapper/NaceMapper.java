package com.gft.deutsche.nace.mapper;

import com.gft.deutsche.nace.dto.NaceDto;
import com.gft.deutsche.nace.entity.Nace;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NaceMapper {

    NaceMapper INSTANCE = Mappers.getMapper(NaceMapper.class);

    Nace toNace(NaceDto naceDto);

    NaceDto toNaceDto(Nace nace);

    List<NaceDto> toNaceDtoList(List<Nace> naceList);

    @Named("getNace")
    default Nace getNace(Nace nac) {
        return Nace.builder()
                .order(nac.getOrder())
                .level(nac.getLevel())
                .code(nac.getCode())
                .parent(nac.getParent())
                .description(nac.getDescription())
                .include(nac.getDescription())
                .rulings(nac.getRulings())
                .exclude(nac.getExclude())
                .reference(nac.getReference())
                .build();
    }

}
