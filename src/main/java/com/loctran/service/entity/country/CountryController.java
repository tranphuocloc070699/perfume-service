package com.loctran.service.entity.country;

import com.loctran.service.common.ResponseDto;
import com.loctran.service.entity.country.dto.CreateCountryDto;
import com.loctran.service.entity.country.dto.UpsaveCountryDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * API for managing countries.
 * Provides endpoints for retrieving, creating, updating, and deleting countries.
 */
@Tag(name = "Country", description = "APIs for managing countries")
@RestController
@RequestMapping("country")
@RequiredArgsConstructor
public class CountryController {
    private final CountryService countryService;

  /**
   * Retrieves all countries.
   */
  @Operation(summary = "Get all countries", description = "Retrieves all countries")
  @GetMapping("")
  public ResponseEntity<ResponseDto> getAllCountry() {
    List<Country> countries = countryService.findAll();
    return ResponseEntity.ok(ResponseDto.getDataSuccess(countries));
  }

  /**
   * Retrieves a country by its ID.
   */
  @Operation(summary = "Get a country by ID", description = "Retrieves a country by its ID")
  @GetMapping("/{id}")
  public ResponseEntity<ResponseDto> getCountry(@PathVariable String id) {
    Country country = countryService.findById(Long.parseLong(id));
    return ResponseEntity.ok(ResponseDto.getDataSuccess(country));
  }

  /**
   * Creates a new country.
   */
  @Operation(summary = "Create a country", description = "Creates a new country")
  @PostMapping("")
  public ResponseEntity<ResponseDto> createCountry(@RequestBody UpsaveCountryDto dto) {
    Country country = countryService.create(dto);
    return ResponseEntity.ok(ResponseDto.createDataSuccess(country));
  }

  /**
   * Updates an existing country.
   */
  @Operation(summary = "Update a country", description = "Updates an existing country")
  @PutMapping("/{id}")
  public ResponseEntity<ResponseDto> updateCountry(@PathVariable("id") String id, @RequestBody UpsaveCountryDto dto) {
    Country country = countryService.updateById(Long.parseLong(id), dto);
    return ResponseEntity.ok(ResponseDto.updateDataSuccess(country));
  }

  /**
   * Adds a brand to a country.
   */
  @Operation(summary = "Add a brand to a country", description = "Adds a brand to a country")
  @PutMapping("/{id}/brand/{brandId}/add")
  public ResponseEntity<ResponseDto> addBrandToCountry(@PathVariable("id") String countryId, @PathVariable("brandId") String brandId) {
    Country country = countryService.addBrandToCountry(Long.parseLong(countryId), Long.parseLong(brandId));
    return ResponseEntity.ok(ResponseDto.updateDataSuccess(country));
  }

  /**
   * Removes a brand from a country.
   */
  @Operation(summary = "Remove a brand from a country", description = "Removes a brand from a country")
  @PutMapping("/{id}/brand/{brandId}/remove")
  public ResponseEntity<ResponseDto> removeBrandFromCountry(@PathVariable("id") String countryId, @PathVariable("brandId") String brandId) {
    Country country = countryService.removeBrandFromCountry(Long.parseLong(countryId), Long.parseLong(brandId));

    return ResponseEntity.ok(ResponseDto.updateDataSuccess(country));
  }

  /**
   * Deletes a country by its ID.
   */
  @Operation(summary = "Delete a country", description = "Deletes a country by its ID")
  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseDto> deleteCountry(@PathVariable("id") String id) {
    Country country = countryService.deleteById(Long.parseLong(id));
    return ResponseEntity.ok(ResponseDto.deleteDataSuccess(country));
  }
}
