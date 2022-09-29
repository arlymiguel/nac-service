package com.nace.controller;

import com.nace.dto.NaceDto;
import com.nace.service.NaceService;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/nace")
public class NaceController {

    private final NaceService naceService;

    @Operation(summary = "Create a Nace")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Nace creation",
                    content = {@Content(
                            mediaType = "application/json", schema = @Schema(implementation = NaceDto.class)
                    )}),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @PostMapping()
    public NaceDto create(@RequestBody NaceDto nace) {
        return naceService.save(nace);
    }

    @Operation(summary = "Update a Nace")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Update a Nace",
                    content = {@Content(
                            mediaType = "application/json", schema = @Schema(implementation = NaceDto.class)
                    )}),
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @PutMapping("/{id}")
    public NaceDto udpate(@RequestBody @Valid NaceDto request, @PathVariable(value = "id") Long id) {
        return naceService.update(id, request);
    }

    @Operation(summary = "Get a Nace by Id")
    @GetMapping("/{id}")
    public NaceDto findById(@PathVariable(value = "id") Long id) {
        return naceService.findById(id);
    }

    @Operation(summary = "Get a Nace by Order")
    @GetMapping("order/{order}")
    public NaceDto findById(@PathVariable(value = "order") String order) {
        return naceService.findByOrder(order);
    }

    @Operation(summary = "Get all Nace")
    @GetMapping
    public List<NaceDto> findAll() {
        return naceService.findAll();
    }

    @Operation(summary = "Delete a Nace by Id")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable(value = "id") Long id) {
        naceService.removeById(id);
    }

}
