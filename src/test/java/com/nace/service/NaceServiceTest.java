package com.nace.service;

import com.nace.dto.NaceDto;
import com.nace.entity.Nace;
import com.nace.repository.NaceRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@Tag("unit")
@ExtendWith(MockitoExtension.class)
public class NaceServiceTest {

    @Mock
    private NaceRepository naceRepository;

    @InjectMocks
    private NaceService naceService;

    @BeforeEach
    void init() {
        naceService = new NaceService(naceRepository);
    }


    @Test
    @DisplayName("When send a NACE, it will be save in database")
    void createNace_Test() {

        NaceDto naceDto = NaceDto.builder().order("aa").level("bb").code("cc").parent("dd")
                .description("ee").include("ff").rulings("gg").exclude("hh").reference("ii").build();
        Nace nace = Nace.builder().order("aa").level("bb").code("cc").parent("dd")
                .description("ee").include("ff").rulings("gg").exclude("hh").reference("ii").build();

        when(naceRepository.save(any(Nace.class))).thenReturn(nace);

        NaceDto naceSaved = naceService.save(naceDto);
        Assertions.assertNotNull(naceSaved);
    }

    @Test
    @DisplayName("When send a NACE to update, it will be updated in database")
    void updateNace_Test() {

        NaceDto naceDto = NaceDto.builder().order("aa").level("bb").code("cc").parent("dd")
                .description("ee").include("ff").rulings("gg").exclude("hh").reference("ii").build();
        Nace nace = Nace.builder().order("aa").level("bb").code("cc").parent("dd")
                .description("ee").include("ff").rulings("gg").exclude("hh").reference("ii").build();
        Optional<Nace> naceOptional = Optional.of(nace);

        when(naceRepository.findById(1L)).thenReturn(naceOptional);
        when(naceRepository.save(any(Nace.class))).thenReturn(nace);

        NaceDto naceUpdated = naceService.update(1L, naceDto);
        Assertions.assertNotNull(naceUpdated);
    }

    @Test
    @DisplayName("When requested a Nace by Order, it will be returned it")
    void findByOrder_Test() {
        String order = "aa";

        Nace nace = Nace.builder().order("aa").level("bb").code("cc").parent("dd")
                .description("ee").include("ff").rulings("gg").exclude("hh").reference("ii").build();
        Optional<Nace> naceOptional = Optional.of(nace);

        when(naceRepository.findByOrder(order)).thenReturn(naceOptional);

        NaceDto naceFound = naceService.findByOrder(order);
        Assertions.assertNotNull(naceFound);
    }

    @Test
    @DisplayName("When requested a Nace by Id, it will be returned it")
    void findById_Test() {
        String order = "aa";

        Nace nace = Nace.builder().order("aa").level("bb").code("cc").parent("dd")
                .description("ee").include("ff").rulings("gg").exclude("hh").reference("ii").build();
        Optional<Nace> naceOptional = Optional.of(nace);

        when(naceRepository.findById(1L)).thenReturn(naceOptional);

        NaceDto naceFound = naceService.findById(1L);
        Assertions.assertNotNull(naceFound);
    }

    @Test
    @DisplayName("When requeste all NACE, it return all of them")
    void findAll_Test() {

        Nace nace = Nace.builder().order("aa").level("bb").code("cc").parent("dd")
                .description("ee").include("ff").rulings("gg").exclude("hh").reference("ii").build();

        List<Nace> naceList = new ArrayList<>();
        naceList.add(nace);
        when(naceRepository.findAll()).thenReturn(naceList);

        List<NaceDto> naceDtoList = naceService.findAll();
        Assertions.assertNotNull(naceDtoList);
    }

    @Test
    @DisplayName("When requested to delte a Nace by Id, it will be removed")
    void deleteById_Test() {

        Nace nace = Nace.builder().order("aa").level("bb").code("cc").parent("dd")
                .description("ee").include("ff").rulings("gg").exclude("hh").reference("ii").build();
        Optional<Nace> naceOptional = Optional.of(nace);

        when(naceRepository.findById(1L)).thenReturn(naceOptional);
        doNothing().when(naceRepository).deleteById(1L);

        naceService.removeById(1L);

        verify(naceRepository, times(1)).deleteById(1L);
    }


}
