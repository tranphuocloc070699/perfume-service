package com.loctran.service.entity.country;

import com.loctran.service.common.ResponseDto;
import com.loctran.service.entity.country.dto.CreateCountryDto;
import com.loctran.service.entity.country.dto.UpdateCountryDto;
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

@RestController
@RequestMapping("country")
@RequiredArgsConstructor
public class CountryController {
    private final CountryService countryService;

    @GetMapping("")
    public ResponseEntity<ResponseDto> getAllCountry() {
      List<Country> countries = countryService.findAll();
      ResponseDto responseDto = ResponseDto.builder().build();
      responseDto.setMessage("Lấy thông tin thành công");
      responseDto.setStatus(200);
      responseDto.setData(countries);
      return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getCountry(@PathVariable String id) {
      Country country = countryService.findById(Long.parseLong(id));
      ResponseDto responseDto = ResponseDto.builder().build();
      responseDto.setMessage("Lấy thông tin thành công");
      responseDto.setStatus(200);
      responseDto.setData(country);
      return ResponseEntity.ok(responseDto);
    }

    @PostMapping("")
    public ResponseEntity<ResponseDto> createCountry(@RequestBody CreateCountryDto dto) {
      Country country = countryService.save(dto);
      ResponseDto responseDto = ResponseDto.builder().build();
      responseDto.setMessage("Thêm quốc gia thành công");
      responseDto.setStatus(200);
      responseDto.setData(country);
      return ResponseEntity.ok(responseDto);
    }

  @PutMapping("/{id}")
  public ResponseEntity<ResponseDto> updateCountry(@PathVariable("id") String id, @RequestBody UpdateCountryDto dto) {
    Country country = countryService.update(Long.parseLong(id),dto);
    ResponseDto responseDto = ResponseDto.builder().build();
    responseDto.setMessage("Update quốc gia thành công");
    responseDto.setStatus(200);
    responseDto.setData(country);
    return ResponseEntity.ok(responseDto);
  }

  @PutMapping("/{id}/brand/{brandId}/add")
  public ResponseEntity<ResponseDto> addBrandToCountry(@PathVariable("id")String countryId,@PathVariable("brandId")String brandId) {
    Country country = countryService.addBrandToCountry(Long.parseLong(countryId),Long.parseLong(brandId));
    ResponseDto responseDto = ResponseDto.builder().build();
    responseDto.setMessage("Thêm thương hiệu vào quốc gia thành công");
    responseDto.setStatus(200);
    responseDto.setData(country);
    return ResponseEntity.ok(responseDto);
  }

  @PutMapping("/{id}/brand/{brandId}/remove")
  public ResponseEntity<ResponseDto> removeBrandFromCountry(@PathVariable("id")String countryId,@PathVariable("brandId")String brandId) {
    Country country = countryService.removeBrandFromCountry(Long.parseLong(countryId),Long.parseLong(brandId));
    ResponseDto responseDto = ResponseDto.builder().build();
    responseDto.setMessage("Thêm thương hiệu vào quốc gia thành công");
    responseDto.setStatus(200);
    responseDto.setData(country);
    return ResponseEntity.ok(responseDto);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseDto> deleteCountry(@PathVariable("id") String id) {
    Country country = countryService.deleteCountry(Long.parseLong(id));
    ResponseDto responseDto = ResponseDto.builder().build();
    responseDto.setMessage("Xóa quốc gia thành công");
    responseDto.setStatus(200);
    responseDto.setData(country);
    return ResponseEntity.ok(responseDto);
  }
}
