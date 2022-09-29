package com.nace.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.nace.dto.NaceDto;
import com.nace.entity.Nace;
import com.nace.exception.NoContentException;
import com.nace.mapper.NaceMapper;
import com.nace.repository.NaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NaceService {

    private final NaceRepository naceRepository;

    private final ObjectMapper objectMapper;

    public NaceDto save(NaceDto naceDto) {
        return NaceMapper.INSTANCE.toNaceDto( naceRepository.save( NaceMapper.INSTANCE.toNace(naceDto) ) );
    }

    public NaceDto update(Long id, NaceDto naceDto){
        Optional<Nace> nace = naceRepository.findById(id);
        if (nace.isPresent()) {
            Nace naceToUpdate = nace.get();
            naceToUpdate.setOrder(naceDto.getOrder());
            naceToUpdate.setLevel(naceDto.getLevel());
            naceToUpdate.setCode(naceDto.getCode());
            naceToUpdate.setParent(naceDto.getParent());
            naceToUpdate.setDescription(naceDto.getDescription());
            naceToUpdate.setInclude(naceDto.getInclude());
            naceToUpdate.setRulings(naceDto.getRulings());
            naceToUpdate.setExclude(naceDto.getExclude());
            naceToUpdate.setReference(naceDto.getReference());
            return NaceMapper.INSTANCE.toNaceDto( naceRepository.save( naceToUpdate ) ) ;
        } else {
            throw new NoContentException("Nace is not present");
        }
    }

    public NaceDto findById(Long id) {
        Optional<Nace> nace = naceRepository.findById(id);
        if (nace.isPresent()) {
            return NaceMapper.INSTANCE.toNaceDto( nace.get() ) ;
        } else {
            throw new NoContentException("Nace is not present");
        }
    }

    public NaceDto findByOrder(String order) {
        Optional<Nace> nace = naceRepository.findByOrder(order);
        if (nace.isPresent()) {
            return NaceMapper.INSTANCE.toNaceDto( nace.get() ) ;
        } else {
            throw new NoContentException("Nace is not present");
        }
    }

    public List<NaceDto> findAll () {
        return NaceMapper.INSTANCE.toNaceDtoList( naceRepository.findAll() );
    }

    public void removeById (Long id) {
        Optional<Nace> nace = naceRepository.findById(id);
        if (nace.isPresent()) {
            naceRepository.deleteById(id);
        } else {
            throw new NoContentException("Nace is not present");
        }
    }

    @SneakyThrows
    public NaceDto updatePartially(Long id, JsonPatch patch) {

        try {
            Optional<Nace> nace = naceRepository.findById(id);
            if (nace.isPresent()) {
                Nace naceDtoPatched = applyPatchToNace(patch, nace.get());
                Nace naceUpdated = naceRepository.save(naceDtoPatched);
                return NaceMapper.INSTANCE.toNaceDto(naceUpdated);
            } else {
                throw new NoContentException("Nace is not present");
            }
        } catch (JsonPatchException | JsonProcessingException e) {
            throw e;
        }
    }

    private Nace applyPatchToNace(
            JsonPatch patch, Nace targetNace) throws JsonPatchException, JsonProcessingException {

        JsonNode patched = patch.apply(objectMapper.convertValue(targetNace, JsonNode.class));
        return objectMapper.treeToValue(patched, Nace.class);

    }

}
