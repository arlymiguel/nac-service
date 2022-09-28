package com.nace.controller;

import com.nace.dto.NaceDto;
import com.nace.service.NaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/nace")
public class NaceController {

    private final NaceService naceService;

    @PostMapping()
    public NaceDto create(@RequestBody NaceDto nace) {
        return naceService.save(nace);
    }

    @PutMapping("/{id}")
    public NaceDto udpate(@RequestBody @Valid NaceDto request, @PathVariable(value = "id") Long id) {
        return naceService.update(id, request);
    }

    @GetMapping("/{id}")
    public NaceDto findById(@PathVariable(value = "id") Long id) {
        return naceService.findById(id);
    }

    @GetMapping("order/{order}")
    public NaceDto findById(@PathVariable(value = "order") String order) {
        return naceService.findByOrder(order);
    }

    @GetMapping
    public List<NaceDto> findAll() {
        return naceService.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable(value = "id") Long id) {
        naceService.removeById(id);
    }

}
