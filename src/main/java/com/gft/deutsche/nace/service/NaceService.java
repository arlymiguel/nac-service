package com.gft.deutsche.nace.service;

import com.gft.deutsche.nace.dto.NaceDto;
import com.gft.deutsche.nace.entity.Nace;
import com.gft.deutsche.nace.exception.NoContentException;
import com.gft.deutsche.nace.mapper.NaceMapper;
import com.gft.deutsche.nace.repository.NaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class NaceService {

    private final NaceRepository naceRepository;

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

}
