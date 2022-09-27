package com.gft.deutsche.nace.service;

import com.gft.deutsche.nace.dto.NaceDto;
import com.gft.deutsche.nace.entity.Nace;
import com.gft.deutsche.nace.repository.NaceRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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
    void findByOrderNace_Test() {
        String order = "aa";

        Nace nace = Nace.builder().order("aa").level("bb").code("cc").parent("dd")
                .description("ee").include("ff").rulings("gg").exclude("hh").reference("ii").build();
        Optional<Nace> naceOptional = Optional.of(nace);

        when(naceRepository.findByOrder(order)).thenReturn(naceOptional);

        NaceDto naceFound = naceService.findByOrder(order);
        Assertions.assertNotNull(naceFound);
    }




}
